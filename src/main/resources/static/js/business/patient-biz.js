moduleName = 'patient';
var columnNames = ['ID','患者姓名','就诊卡号码','电话号码','可用状态','创建日期','最后修改日期'];
var attributeNames = ['id','name','medicalCard','phoneNumber','available','createDate','modifyDate'];

loadPageableDataUrl = 'patientsByPage';
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
		obj.medicalCard,
		obj.phoneNumber,
		obj.available ? '启用' : '弃用',
		formatDate(obj.createDate),
		formatDate(obj.modifyDate)]
}