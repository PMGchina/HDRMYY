moduleName = 'scheduling';
var columnNames = ['ID','医生编号','工作日','可用状态','创建日期','最后修改日期'];
var attributeNames = ['id','doctorCode','workday','available','createDate','modifyDate'];

loadPageableDataUrl = 'schedulingsByPage';
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
		obj.doctorCode,
		formatDate(obj.workday),
		obj.available ? '启用' : '弃用',
		formatDate(obj.createDate),
		formatDate(obj.modifyDate)]
}