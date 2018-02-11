moduleName = 'reexaminationItem';
var columnNames = ['ID','名称','编号','价格','可用状态','创建日期','最后修改日期'];
var attributeNames = ['id','name','code','price','available','createDate','modifyDate'];

loadPageableDataUrl = 'reexaminationItemsByPage';
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
		obj.price,
		obj.available ? '启用' : '弃用',
		formatDate(obj.createDate),
		formatDate(obj.modifyDate)]
}