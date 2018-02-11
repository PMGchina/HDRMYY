moduleName = 'bookingRegistration';
var columnNames = ['ID','就诊卡号码','科室编号','医生编号','目标时间','创建日期','可用状态','处理状态'];
var attributeNames = ['id','medicalCard','departmentCode','doctorCode','targetTime','createDate','available','process'];

loadPageableDataUrl = 'bookingRegistrationsByPage';
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
		obj.medicalCard,
		obj.departmentCode,
		obj.doctorCode,
		formatDate(obj.targetTime),
		formatDate(obj.createDate),
		obj.available ? '启用' : '弃用',
		obj.process ? '已处理' : '未处理']
}
