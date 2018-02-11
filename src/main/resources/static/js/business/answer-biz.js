moduleName = 'answer';
//tableContainer中表格显示列的中文名称
var columnNames = ['ID','问题题目','答案','可选备注','答题日期'];
//tableContainer中表格每列对应的业务模型实体类的属性名
var attributeNames = ['id','stem','content','optionalRemark','createDate'];
//tableContainer中表格每列需要的按钮
var buttonsOnEachRow = [];
//提交add、update或copy表单时的表单验证规则
var validateRules = [];

loadPageableDataUrl = 'answersByPage'; //加载分页数据对应的后台请求映射URL
loadPageableDataCallback = function(data) { //pagination-utils.js 获取完分页数据后自动生成数据表格
	data = data.pageableData;
	var value = [];
	for(var i=0;i<data.numberOfElements;i++) {
		value[i] = parseValuesOnEachRow(data.content[i]);
	}
	return generateDefaultDataTable(columnNames,attributeNames,value,true,moduleName);
}

function parseValuesOnEachRow(obj) {
	return [obj.id,
		obj.questionId,
		obj.content,
		obj.optionalRemark,
		formatDate(obj.createDate),
		buttonsOnEachRow]
}
