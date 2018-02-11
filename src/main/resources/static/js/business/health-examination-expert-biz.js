moduleName = 'healthExaminationExpert';
addModalWidth = updateModalWidth = copyModalWidth = 800;
//当前用户操作的行为 - add、update、delete等等
var currentAction = null;
//当前用户能够操作的所有行为
var actions = {'add': {'key':'add','url':'singleAdd'},'update':{'key':'update','url':'singleUpdate'},'copy':{'key':'copy','url':'singleAdd'}};
//初始化完毕后记录表单所有属性值，用于后期提交修改时判断表单属性值是否被修改
var initializedFormData = {};
//tableContainer中表格显示列的中文名称
var columnNames = ['ID','名称','职称','是否可用','创建日期','最后修改日期','操作'];
//tableContainer中表格每列对应的业务模型实体类的属性名
var attributeNames = ['id','name','title','available','createDate','modifyDate','operation'];
//tableContainer中表格每列需要的按钮
var buttonsOnEachRow = ['update?','copy?','delete?'];
//提交add、update或copy表单时的表单验证规则
var validateRules = [
	{name: 'name', required: true, minLength: 2, maxLength: 10},
	{name: 'title', maxLength: 100},
	{name: 'imageUrl', maxLength: 65535},
	{name: 'description', maxLength: 65535}
];
/*****************************************************************************************
 *************************************** LIST PAGE ***************************************
 *****************************************************************************************/
loadPageableDataUrl = 'healthExaminationExpertsByPage'; //加载分页数据对应的后台请求映射URL
loadPageableDataCallback = function(data) { //pagination-utils.js 获取完分页数据后自动生成数据表格
	data = data.pageableData;
	var value = [];
	for(var i=0;i<data.numberOfElements;i++) {
		value[i] = parseValuesOnEachRow(data.content[i]);
	}
	return generateDefaultDataTable(columnNames,attributeNames,value,false,moduleName);
}
/*****************************************************************************************
 ************************************** OTHER PAGES **************************************
 *****************************************************************************************/
documentReady = function() { //pagination-utils.js 绑定所有表单单击(目的为提交表单)事件
	$('#submitAdd').click(function() {
		$('#add_imageUrl').val(CKEDITOR.instances.add_imageUrl.getData());
		$('#add_description').val(CKEDITOR.instances.add_description.getData());
		currentAction = actions.add;
		submitForm(validateForm(currentAction.key,validateRules),function(){$('#closeAdd').click()});
	});	
	$('#submitUpdate').click(function() {
		$('#update_imageUrl').val(CKEDITOR.instances.update_imageUrl.getData());
		$('#update_description').val(CKEDITOR.instances.update_description.getData());
		currentAction = actions.update;
		submitForm(validateForm(currentAction.key,validateRules),function(){$('#closeUpdate').click()});
	});
	$('#submitCopy').click(function() {
		$('#copy_imageUrl').val(CKEDITOR.instances.copy_imageUrl.getData());
		$('#copy_description').val(CKEDITOR.instances.copy_description.getData());
		currentAction = actions.copy;
		submitForm(validateForm(currentAction.key,validateRules),function(){$('#closeCopy').click()});
	});
	$('#closeUpdate').click(function() {doCheckAll(false)});
	$('#closeCopy').click(function() {doCheckAll(false)});
	
	initCKEditor(['add_imageUrl', 'update_imageUrl', 'copy_imageUrl', 'add_description', 'update_description', 'copy_description']);
};

/**
 * 初始化update表单和copy表单数据
 */
initUpdateForm = function(obj) {initForm('update',obj)} //basic-button-utils.js
initCopyForm = function(obj) {initForm('copy',obj)} //basic-button-utils.js

/**
 * 获取当前操作的表单对象
 * @returns 当前表单JQuery对象
 */
function getCurrentForm() {
	return $('#' + currentAction.key + 'Form');
}

/**
 * 提交表单
 * @param validateFormFunction - 保存数据之前需要验证表单的方法
 * @param url - 提交至后台的URL
 * @param callback - 保存成功后的回调方法
 */
function submitForm(validateFormFunction,callback) {
	if(validateFormFunction) { //最后要清空ADD与COPY FORM
		saveData(function(data){
			fresh4NewData(data,function(){callback()});
		});
	}
}

/**
 * 保存add、update或copy表单提交的数据
 * @param url - 提交至后台的URL
 * @param sucFunc - 保存成功后的回调方法
 */
function saveData(sucFunc) {
	//在更新记录时判断表单数据是否更改
	var savingFormData = $(getCurrentForm()).serializeObject();
	if (JSON.stringify(savingFormData) != JSON.stringify(initializedFormData)) {
		$.ajax({
		    headers: {
		        'Accept': 'application/json',
		        'Content-Type': 'application/json'
		    },
		    cache: false,
	        type: "POST",
	        url: generateFullRequestPath(currentAction.url),
	        data: formatNonstandardData2JSON(savingFormData),
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
 */
function fresh4NewData(data,callback) {
	if (data != null) {
		if (currentAction == actions.update) {
			$('table#dataTable').find('tr#'+data.updated.id)
			.html(generateDefaultDataTableTd(
					attributeNames,parseValuesOnEachRow(data.updated)));
		} else {
			var last = getLastRecord(); 
			var total = getTotalRecords();
			var size = getHowManyRecords();
			
			//新纪录是否能够直接在最后一页显示,而不需要增加一页
			if (total % size > 0 || total == 0) {
				//当前页是否为最后一页
				if (last == total) {
		    		$('table#dataTable tbody')
		    			.append(generateDefaultDataTableTr(
		    					attributeNames,parseValuesOnEachRow(data.created)));
					last++;
				} 
				total++;
				if (total == 1) setFirstRecord(last);
				setLastRecord(last);
				setTotalRecords(total)
				gotoPageNumber(getLastPageNumber());
			} else {
				//无论当前页在不在最后一页,最后一页也没有任何空间可以显示新纪录
				//涉及到增加页码按钮,需要重新生成页码按钮,则需要重新刷新页面
				initPageableData(getLastPageNumber(),true,size);
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
function initForm(formKey,obj) {
	$('#'+formKey+'_id').val(obj.id);
	$('#'+formKey+'_name').val(obj.name);
	$('#'+formKey+'_title').val(obj.title);
	
	CKEDITOR.instances[formKey + '_imageUrl'].setData(obj.imageUrl);
	$('#'+formKey+'_imageUrl').val(obj.imageUrl);
	
	CKEDITOR.instances[formKey + '_description'].setData(obj.description);
	$('#'+formKey+'_description').val(obj.description);
	
	$('#'+formKey+'Form').find('input[name=available][value='+(obj.available)+']').uCheck('check');
	
	//初始化完毕后记录表单所有属性值，用于后期提交修改时判断表单属性值是否被修改
	initializedFormData = $('#'+formKey + 'Form').serializeObject();
}

/**
 * 如何将输入参数obj的值赋值给tableContainer中每条记录的属性
 * @param obj - tableContainer中每条记录针对的业务模型实体类对象实例
 * @returns 格式化后记载着tableContainer中一条记录中每列属性的数组
 */
function parseValuesOnEachRow(obj) {
	return [obj.id,
		obj.name,
		obj.title,
		obj.available ? '是' : '否',
		formatDate(obj.createDate),
		formatDate(obj.modifyDate),
		buttonsOnEachRow]
}

/**
 * 初始化所有页面中的富文本编辑器控件
 * @param id - 所有控件的id，如果不唯一则需要传入数组
 */
function initCKEditor(id) {
  var toolbar = {toolbar: [{name: 'document', items: ['Source', '-', 'NewPage', 'Preview']}, ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo'], {name: 'basicstyles', items: ['Bold', 'Italic']}, { name: 'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar']}]};
  if (!isArray(id))
    id = new Array(id);
  for (var r in id)
	  CKFinder.setupCKEditor(CKEDITOR.replace(id[r], toolbar), projectPath + '/static/ckfinder/');	  
}
