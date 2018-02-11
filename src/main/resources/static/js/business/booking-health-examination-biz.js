moduleName = 'bookingHealthExamination';
//tableContainer中表格显示列的中文名称
var columnNames = ['ID','姓名','性别','出生日期','联系电话','所选套餐','所选单项','目标时间','创建日期','可用状态','处理状态'];
//tableContainer中表格每列对应的业务模型实体类的属性名
var attributeNames = ['id','name','gender','birthday','phoneNumber','chosenExaminationPackages','chosenExaminationItems','targetTime','createDate','available','process'];
//提交add、update或copy表单时的表单验证规则

loadPageableDataUrl = 'bookingHealthExaminationsByPage'; //加载分页数据对应的后台请求映射URL
loadPageableDataCallback = function(data) { //pagination-utils.js 获取完分页数据后自动生成数据表格
	data = data.pageableData;
	var dtos = data.dtos;
	var value = [];
	for(var i=0;i<data.numberOfElements;i++) {
		value[i] = parseValuesOnEachRow(dtos[i]);
	}
	return generateDefaultDataTable(columnNames,attributeNames,value,true,moduleName);
}

/**
 * 如何将输入参数obj的值赋值给tableContainer中每条记录的属性
 * @param obj - tableContainer中每条记录针对的业务模型实体类对象实例
 * @returns 格式化后记载着tableContainer中一条记录中每列属性的数组
 */
function parseValuesOnEachRow(obj) {
	var packages = obj.chosenExaminationPackages;
	var items = obj.chosenExaminationItems;
	
	var contentOfPackages = [];
	for(var r in packages) {
		contentOfPackages.push(packages[r].name);
	}
	
	var contentOfItems = [];
	for(var r in items) {
		contentOfItems.push(items[r].name);
	}
	
	return [obj.id,
		obj.name,
		obj.gender ? '男' : '女',
		formatDate(obj.birthday),
		obj.phoneNumber,
		contentOfPackages.join(','),
		contentOfItems.join(','),
		formatDate(obj.targetTime,false),
		formatDate(obj.createDate),
		obj.available ? '启用' : '弃用',
		obj.process ? '已处理' : '未处理']
}