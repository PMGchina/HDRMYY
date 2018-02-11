moduleName = 'questionnaire';
addModalWidth = updateModalWidth = copyModalWidth = 800;
var publishStatus = ['未发布','已发布'];
var availableStatus = ['不可用','可用']
//当前用户操作的行为 - add、update、delete等等
var currentAction = null;
//当前用户能够操作的所有行为
var actions = {'add': {'key':'add','url':'singleAdd'},'update':{'key':'update','url':'singleUpdate'},'copy':{'key':'copy','url':'singleAdd'},'analysis':{'key':'analysis','url':'analysis'},'publish':{'key':'publish','url':'publish'},'unpublish':{'key':'unpublish','url':'unpublish'},'available':{'key':'available','url':'available'},'unavailable':{'key':'unavailable','url':'unavailable'}};
//初始化完毕后记录表单所有属性值，用于后期提交修改时判断表单属性值是否被修改
var initializedFormData = {};
//tableContainer中表格显示列的中文名称
var columnNames = ['ID','名称','答题人数','发布状态','可用状态','创建日期','最后修改日期','操作'];
//tableContainer中表格每列对应的业务模型实体类的属性名
var attributeNames = ['id','name','numberOfAnswers','published','available','createDate','modifyDate','operation'];
//tableContainer中表格每列需要的按钮
var buttonsOnEachRow = ['分析#am-icon-pie-chart#am-btn am-btn-default am-btn-xs am-text-secondary#analysis(event,this,?id)','update?','copy?','delete?'];
//提交add、update或copy表单时的表单验证规则
var validateRules = [
	{name: 'name', required: true, minLength: 2, maxLength: 50},
	{name: 'description', maxLength: 200}
];
/*****************************************************************************************
 *************************************** LIST PAGE ***************************************
 *****************************************************************************************/
loadPageableDataUrl = 'questionnairesByPage'; //加载分页数据对应的后台请求映射URL
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
		$('#add_description').val(CKEDITOR.instances.add_description.getData());
		currentAction = actions.add;
		submitForm(validateForm(currentAction.key,validateRules),function(){$('#closeAdd').click();initRowButtonsStatus();});
	});	
	$('#submitUpdate').click(function() {
		$('#update_description').val(CKEDITOR.instances.update_description.getData());
		currentAction = actions.update;
		submitForm(validateForm(currentAction.key,validateRules),function(){$('#closeUpdate').click();initRowButtonsStatus();});
	});
	$('#submitCopy').click(function() {
		$('#copy_description').val(CKEDITOR.instances.copy_description.getData());
		currentAction = actions.copy;
		submitForm(validateForm(currentAction.key,validateRules),function(){$('#closeCopy').click();initRowButtonsStatus();});
	});
	$('#closeUpdate').click(function() {doCheckAll(false)});
	$('#closeCopy').click(function() {doCheckAll(false)});
	
	registerCustomInlineButtons();
	$('button#publishButton').click(publishButtonClickFunction);
	$('button#unpublishButton').click(unpublishButtonClickFunction);
	$('button#availableButton').click(availableButtonClickFunction);
	$('button#unavailableButton').click(unavailableButtonClickFunction);
	initCKEditor(['add_description', 'update_description', 'copy_description']);
};

loadPageableDataAfter = initRowButtonsStatus;

/**
 * 初始化update表单和copy表单数据
 */
initUpdateForm = function(obj) {initForm(actions.update.key,obj)} //basic-button-utils.js
initCopyForm = function(obj) {initForm(actions.copy.key,obj)} //basic-button-utils.js

updateBefore = function(obj) {
	if (obj.published) {
		warn('当前问卷已经发布，不可修改!');
	} else {
		var result;
		$.posty(generateFullRequestPath('checkUpdatedIds'),{'id':obj.id},function(data) {
			if (data.isAnswered) {
				warn('当前问卷已被回答，不可修改!');
				result = false;
			} else {
				result = true;
			}
		});
		return result;
	}
}

deleteBefore = function(ids) {
	var result;
	$.posty(generateFullRequestPath('checkDeletedIds'),{'ids':ids.split(',')},function(data) {
		if (data.isPublished) {
			warn('当前问卷已经发布，不可删除!');
			result = false;
		} else {
			result = true;
		}
	});
	return result;
}

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
	
	CKEDITOR.instances[formKey + '_description'].setData(obj.description);
	$('#'+formKey+'_description').val(obj.description);
	
	if (formKey == actions.update.key) {
		$('#'+formKey+'_numberOfAnswers').val(obj.numberOfAnswers);
		$('#'+formKey+'Form').find('input[name=published][value='+(obj.published)+']').uCheck('check');
		$('#'+formKey+'Form').find('input[name=available][value='+(obj.available)+']').uCheck('check');
	} else {
		$('#'+formKey+'Form').find('input[name=published][value='+(false)+']').uCheck('check');
		$('#'+formKey+'Form').find('input[name=available][value='+(false)+']').uCheck('check');
	}
	
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
		obj.numberOfAnswers,
		publishStatus[+obj.published],
		availableStatus[+obj.available],
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

/**
 * 初始化分析按钮状态
 * 当问卷为发布状态时，分析按钮为可用状态，否则为不可用状态
 */
function initRowButtonsStatus() {
	$('div#tableContainer tbody tr').each(function () {
		var count = Number($(this).find('td[name=numberOfAnswers]').html())
		$('.customButton' + $(this).attr('id'))[0].disabled = count == 0;
		$('.defaultButton' + $(this).attr('id'))[0].disabled = count > 0;
		$('.defaultButton' + $(this).attr('id'))[2].disabled = count > 0;
	});
};

/**
 * 分析当前行问卷
 * 对当前行问卷进行分析，分析内容包括回答数量，该问卷中每道题目回答情况等
 * @param e - 单击分析按钮事件
 * @param btn - 分析按钮
 * @param id - 当前行对应的问卷id
 */
function analysis(e, btn, id) {
	currentAction = actions.analysis;
	popModal(currentAction.key,800);
	
	$.gety(generateFullRequestPath(currentAction.url + '?id=' + id), function(data) {
		$('#numberOfAnswersSpan').html(data.questionnaire.numberOfAnswers);
		
		var content = [];
		var questions = data.questions;
		var countOfQuestions = questions.length;
		for(var i in questions) {
			var question = questions[i];
			var count = Number(i) + 1;
			content.push('<p style="text-align: left;padding-left: 10px">');
			content.push(count);
			content.push('. ');
			content.push(question.stem);
			content.push('</p>');
			content.push('<div id="pieChart' + count + '" style="min-width:800px;height:400px"></div>');
		}
		
		$('#questionsAnalysisDiv').html(content.join(''));
		
		// 饼状图渐变色设置
	    Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
	        return {
	            radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
	            stops: [
	                [0, color],
	                [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
	            ]
	        };
	    });
		
		var pieChartData = data.pieChartData;
		for (var i=1; i<countOfQuestions+1; i++) {
			
			//根据问题类型判断展示与否
			var questionTypeName = questions[i-1].type.name;
			if (questionTypeName == '问答题' || questionTypeName == '矩阵题') {
				var currentDiv = $('#pieChart' + i);
				currentDiv.html('<b>问题题或矩阵题无法显示统计数据...</>');
				currentDiv.height(20);
				continue;
			}
			
			var answersUnderOneQuestion = pieChartData[i-1];
			var pieData = [];
			for(var j in answersUnderOneQuestion) {
				var answer = answersUnderOneQuestion[j];
				pieData.push({name: answer.content, y:answer.percentage, count: answer.count})
			}
			
			new Highcharts.Chart({
				chart: {
					type: 'pie',
					renderTo: 'pieChart' + i
				},
				title: {
					text: null
				},
		        tooltip: {
		            pointFormat: '<b>{point.percentage:.1f}%</b>'
		        },
				legend: {
					layout: 'vertical',
					backgroundColor: '#FFFFFF',
					floating: true,
					align: 'left',
					verticalAlign: 'top',
					labelFormatter: function () {
						return this.name + ' ('+ this.count+ '/' +this.percentage+'%)';
					}
				},
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                    	color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                },
						showInLegend: true
		            }
		        },
		        series: [{
		        	data: pieData
		        }]
		    });
		}
	});
}

/**
 * 自定义功能按钮 - 将特定属性改成特定的值，并将table中旧数据更新
 * @param attribute - 属性名
 * @param status - 对应的值
 */
function buttonClickFunction(attribute,status) {
	var count = countCheckedbox();
	if (count == 0) {
		warn('请选中一条记录!');
	} else {
		var inputData = getCheckedIds();
		var ids = inputData.split(',');
		$.posty(generateFullRequestPath(currentAction.url),{'ids':ids},function(data) {
			for (var r in ids) setDataInRow(ids[r],attribute,status);
		});
	}
}

/**
 * 发布按钮单击事件
 */
function publishButtonClickFunction() {
	currentAction = actions.publish;
	buttonClickFunction('published',publishStatus[+true]);
	initRowButtonsStatus();
}

/**
 * 取消发布按钮单击事件
 */
function unpublishButtonClickFunction() {
	currentAction = actions.unpublish;
	buttonClickFunction('published',publishStatus[+false]);
	initRowButtonsStatus();
}

/**
 * 设为可用按钮单击事件
 */
function availableButtonClickFunction() {
	currentAction = actions.available;
	buttonClickFunction('available',availableStatus[+true]);
}

/**
 * 设为不可用按钮单击事件
 */
function unavailableButtonClickFunction() {
	currentAction = actions.unavailable;
	buttonClickFunction('available',availableStatus[+false]);
}