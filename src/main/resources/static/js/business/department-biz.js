moduleName = 'department';
var columnNames = ['ID','名称','科室编号','可用状态','创建日期','最后修改日期'];
var attributeNames = ['id','name','code','available','createDate','modifyDate'];

loadPageableDataUrl = 'departmentsByPage';
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
		obj.name,
		obj.code,
		obj.available ? '启用' : '弃用',
		formatDate(obj.createDate),
		formatDate(obj.modifyDate)]
}