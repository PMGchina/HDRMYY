moduleName = 'bookingReexamination';
var columnNames = ['ID','就诊卡号码','所选复查项目','总价','目标时间','创建日期','可用状态','处理状态'];
var attributeNames = ['id','medicalCard','chosenReexaminationItems','totalAmount','targetTime','createDate','available','process'];

loadPageableDataUrl = 'bookingReexaminationsByPage';
loadPageableDataCallback = function(data) {
	data = data.pageableData;
	var dtos = data.dtos;
	var value = [];
	for(var i=0;i<data.numberOfElements;i++) {
		value[i] = parseValuesOnEachRow(dtos[i]);
	}
	return generateDefaultDataTable(columnNames,attributeNames,value,true,moduleName);
}

function parseValuesOnEachRow(obj) {
	var items = obj.chosenReexaminationItems;
	
	var contentOfItems = [];
	for(var r in items) {
		contentOfItems.push(items[r].name);
	}
	
	return [obj.id,
		obj.medicalCard,
		contentOfItems.join(','),
		obj.totalAmount,
		formatDate(obj.targetTime),
		formatDate(obj.createDate),
		obj.available ? '启用' : '弃用',
		obj.process ? '已处理' : '未处理']
}
