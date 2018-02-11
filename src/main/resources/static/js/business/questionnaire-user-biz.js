moduleName = 'questionnaireUser';
var columnNames = ['ID','问卷名称','用户名称','答题时间'];
var attributeNames = ['id','questionnaireName','nickname','createDate'];
var buttonsOnEachRow = [];

loadPageableDataUrl = 'questionnaireUsersByPage';
loadPageableDataCallback = function(data) {
	data = data.pageableData;
	var value = [];
	for(var i=0;i<data.numberOfElements;i++) {
		value[i] = parseValuesOnEachRow(data.content[i]);
	}
	return generateDefaultDataTable(columnNames,attributeNames,value,true,moduleName);
}

function parseValuesOnEachRow(obj) {
	return [obj.id,
		obj.questionnaire.name,
		obj.user.nickname,
		formatDate(obj.createDate),
		buttonsOnEachRow]
}