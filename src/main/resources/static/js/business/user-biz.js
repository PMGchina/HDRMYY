moduleName = 'user';
//初始化完毕后记录表单所有属性值，用于后期提交修改时判断表单属性值是否被修改
var initializedFormData = {};
//tableContainer中表格显示列的中文名称
var columnNames = ['ID','昵称','用户名','密码','邀请码','状态','注册日期','最后修改日期','操作'];
//tableContainer中表格每列对应的业务模型实体类的属性名
var attributeNames = ['id','nickname','username','password','code','status','createDate','modifyDate','operation'];
//tableContainer中表格每列需要的按钮
var buttonsOnEachRow = ['update?','copy?','delete?'];
//提交add、update或copy表单时的表单验证规则
var usernameReg = /^[a-zA-Z\d]\w{4,11}[a-zA-Z\d]$/;
var nicknameReg = /^[a-zA-Z0-9·\u4e00-\u9fa5]{2,12}$/;
var validateRules = [
	{name: 'username', required: true, unique: 'checkUsernameUnique', regex: [{rule: usernameReg,	warn: '用户名必须为长度6至12位之间以字母、特殊字符(·)或数字字符组成的字符串!'}]},
	{name: 'password',	required: true, minLength: 6, maxLength : 16}, {name: 'repassword', required: true,	equal: 'password'},
	{name: 'nickname', required: true, unique: 'checkNicknameUnique', regex: [{rule: nicknameReg, warn: '昵称必须为长度2至12位之间以字母、特殊字符(·)、汉字或数组字符组成的字符串!'}]},
];
/*****************************************************************************************
 *************************************** LIST PAGE ***************************************
 *****************************************************************************************/
loadPageableDataUrl = 'usersByPage'; //加载分页数据对应的后台请求映射URL
loadPageableDataCallback = function(data) { //pagination-utils.js 获取完分页数据后自动生成数据表格
	data = data.pageableData;
	var value = [];
	for (var i = 0; i < data.numberOfElements; i++) {
		value[i] = parseValuesOnEachRow(data.content[i]);
	}
	return generateDefaultDataTable(columnNames,attributeNames,value,false,moduleName);
};
/*****************************************************************************************
 ************************************** OTHER PAGES **************************************
 *****************************************************************************************/
documentReady = function() { //pagination-utils.js 绑定所有表单单击(目的为提交表单)事件
	$('#submitAdd').click(function() {
		if (isPhotoOversize('add')) return;
		submitForm(validateForm('add',validateRules),'singleAdd','addForm',function() {
			$('#cancelAdd').click();
			resetForm('add');
		});
	});
	$('#submitUpdate').click(function() {
		if (isPhotoOversize('update')) return;
		var updateValidateRules = jQuery.extend(true, {}, validateRules);
		updateValidateRules[0] = null;
		var isByUpdate = true;
		submitForm(validateForm('update',updateValidateRules),'singleUpdate','updateForm',function() {
			$('#closeUpdate').click();
			resetForm('update');
		}, isByUpdate);
	});
	$('#submitCopy').click(function() {
		if (isPhotoOversize('copy')) return;
		submitForm(validateForm('copy',validateRules),'singleAdd','copyForm',function() {
			$('#closeCopy').click();
			resetForm('copy');
		});
	});
	$('#closeUpdate').click(function() {doCheckAll(false)});
	$('#closeCopy').click(function() {doCheckAll(false)});

	//为add、update、copy页面初始化cropper对象
	initCroppers();
};

/**
 * 初始化update表单和copy表单数据
 */
initAddForm = function(obj) {
	$('#add_crop').prop('disabled', true);
}
initUpdateForm = function(obj) {
	initForm('update', obj)
}
initCopyForm = function(obj) {
	initForm('copy', obj)
}
deleteBefore = function(obj) {
	checkUsed(obj)
}

/**
 * 提交表单
 * @param validateFormFunction - 保存数据之前需要验证表单的方法
 * @param url - 提交至后台的URL
 * @param formId - 提交的表单Id
 * @param callback - 保存成功后的回调方法
 * @param isByUpdate - 是否提交的是update表单
 */
function submitForm(validateFormFunction, url, formId, callback, isByUpdate) {
	if (validateFormFunction) { //最后要清空ADD与COPY FORM
		saveData(url, formId, function(data) {
			fresh4NewData(data, function() {
				callback();
			}, isByUpdate);
		});
	}
}

/**
 * 保存add、update或copy表单提交的数据
 * @param url - 提交至后台的URL
 * @param formId - 提交的表单Id
 * @param sucFunc - 保存成功后的回调方法
 */
function saveData(url, formId, sucFunc) {
	//在更新记录时判断表单数据是否更改
	var savingFormData = $('#' + formId).serializeObject();
	if (formId.indexOf('update') < 0
			|| JSON.stringify(savingFormData) !== JSON
					.stringify(initializedFormData)) {
		var formData = formatNonstandardData2JSON(savingFormData);
		formData = JSON.parse(formData);
		formData.photo = $('#' + formId.replace('Form', '') + '_photo')[0].src;
		formData = formatNonstandardData2JSON(formData);

		$.ajax({
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			type: "POST",
			url: generateFullRequestPath(url),
			data: formData,
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.responseText);
			},
			success: function(data) {
				sucFunc(data);
			}
		});
	} else {
		sucFunc(null);
	}
}

/**
 * 当用户操作完增加、修改或复制记录后对tableContainer中的数据进行更新
 * @param data - 更改后的数据
 * @param callback - 回调方法
 * @param isByUpdate - 是否是update后进入此方法
 */
function fresh4NewData(data, callback, isByUpdate) {
	if (data != null) {
		if (isByUpdate) {
			$('table#dataTable').find('tr#' + data.updated.id).html(
					generateDefaultDataTableTd(attributeNames,
							parseValuesOnEachRow(data.updated)));
		} else {
			var last = getLastRecord();
			var total = getTotalRecords();
			var size = getHowManyRecords();

			//新纪录是否能够直接在最后一页显示,而不需要增加一页
			if (total % size > 0 || total == 0) {
				//当前页是否为最后一页
				if (last == total) {
					$('table#dataTable tbody').append(
							generateDefaultDataTableTr(attributeNames,
									parseValuesOnEachRow(data.created)));
					last++;
				}
				total++;
				if (total == 1)
					setFirstRecord(last);
				setLastRecord(last);
				setTotalRecords(total)
				gotoPageNumber(getLastPageNumber());
			} else {
				//无论当前页在不在最后一页,最后一页也没有任何空间可以显示新纪录
				//涉及到增加页码按钮,需要重新生成页码按钮,则需要重新刷新页面
				initPageableData(getLastPageNumber(), true, size);
			}
		}
	}
	callback();
}

/**
 * 初始化update与copy表单，当用户打开此模态窗口时
 * @param formKey - update或copy
 * @param obj - 初始化表单所用的数据对象
 */
function initForm(formKey, obj) {
	//账户信息
	$('#' + formKey + '_id').val(obj.id);
	$('#' + formKey + '_username').val(obj.username);
	$('#' + formKey + '_nickname').val(obj.nickname);
	$('#' + formKey + '_password').val(obj.password);
	$('#' + formKey + '_repassword').val(obj.password);
	$('#' + formKey + '_code').val(obj.code);
	$('#' + formKey + 'Form').find('input[name=status][value=' + (obj.status) + ']').uCheck('check');

	//给update或是copy页面的用户头像赋值
	if (obj.photo) {
		var img = new Image();
		img.src = obj.photo;
		img.id = formKey + '_photo';
		$('#' + formKey + '_photo').parent().html(img);
	} else {
		$('#' + formKey + '_photo').attr('src', '');
	}
	
	$('#' + formKey + '_crop').prop('disabled', true);

	//初始化完毕后记录表单所有属性值，用于后期提交修改时判断表单属性值是否被修改
	if (formKey === "update")
		initializedFormData = $('#' + formKey + 'Form').serializeObject();
}

/**
 * 如何将输入参数obj的值赋值给tableContainer中每条记录的属性
 * @param obj - tableContainer中每条记录针对的业务模型实体类对象实例
 * @returns 格式化后记载着tableContainer中一条记录中每列属性的数组
 */
function parseValuesOnEachRow(obj) {
	return [
		obj.id, 
		obj.nickname, 
		obj.username, 
		obj.password,
		obj.code, 
		obj.status == 1 ? '可用' : '冻结',
		formatDate(obj.createDate, true),
		formatDate(obj.modifyDate, true),
		buttonsOnEachRow
	]
}

/**
 * 重置表单输入框、日期控件、图片控件
 * @param formKey - 表单关键字(add)
 */
function resetForm(formKey) {
	$('#' + formKey + 'Form')[0].reset();
	$('#' + formKey + '_photo').attr('src', '');
}

/*****************************************************************************************
 ***************************************** IMAGE *****************************************
 *****************************************************************************************/
/**
 * 为add、update、copy页面初始化cropper对象
 */
function initCroppers() {
	if (window.URL || window.webkitURL) {
		// 初始化
		$('img[id$=_inputImage]').cropper({
			dragMode : 'none',
			aspectRatio : '1',
			cropBoxResizable : false
		});
		// 事件代理绑定事件
		$('div[id$=_inputFile]').on('click', '[data-method]', function() {
			var formKey = $(this).attr('id').split('_')[0];
			var $image = $('#' + formKey + '_photo');
			var croppedCanvas = $image.cropper('getCroppedCanvas');
			var roundedCanvas = getRoundedCanvas(croppedCanvas);
			var img = canvasToImage(roundedCanvas, formKey + '_photo');
			$image.parent().html(img);
			$('#' + formKey + '_crop').prop('disabled', true);
			initializedFormData = {};
		})
	} else {
		$('img[id$=_inputImage]').prop('disabled', true).parent().addClass('disabled');
	}
}

/**
 * input[type=file]对象的change事件
 * 重新初始化cropper，以保证新选中的图片能够被预览并且被剪切
 * @param formKey - add、update、copy
 */
function inputImage(formKey) {
	var files = $('#' + formKey + '_inputImage')[0].files;
	if (files && files.length) {
		var file = files[0];
		var blobURL = URL.createObjectURL(file);
		$('#' + formKey + '_photo').cropper('destroy');
		$('#' + formKey + '_photo').cropper({dragMode : 'none',	aspectRatio : '1', cropBoxResizable : false});
		$('#' + formKey + '_photo').cropper('replace', blobURL);
		$('#' + formKey + '_inputImage').val('');
		$('#' + formKey + '_crop').prop('disabled', false);
	}
};

/**
 * 判断用户截取的头像是否超过1MB限制
 */
function isPhotoOversize(formKey) {
	if (getImageSize($('#'+formKey+'_photo').attr('src')) > 1) {
		warn('截取的用户头像大小不能超过1MB!');
		return true;
	}
	return false;
}
