moduleName = 'doctor';
var columnNames = ['ID','名称','医生编号','性别','职称','是否专家','科室编号','可用状态','创建日期','最后修改日期'];
var attributeNames = ['id','name','code','gender','title','expert','departmentCode','available','createDate','modifyDate'];

loadPageableDataUrl = 'doctorsByPage';
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
		obj.gender ? '男' : '女',
		obj.title,
		obj.expert ? '专家' : '普通医生',
		obj.departmentCode,
		obj.available ? '启用' : '弃用',
		formatDate(obj.createDate),
		formatDate(obj.modifyDate)]
}