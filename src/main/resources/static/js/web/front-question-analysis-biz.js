$(document).ready(function (e) {
	$.get("questionnaireAnswer", afterLoadQuestionnaire);
});

//调入问卷Questionnaire之后
var afterLoadQuestionnaire = function (data) {
	$('#analysisModal').find('.modal-body').find('ul').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.all.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="loadAnalysis(' + data.all[i].id + ')"><strong>'
			  + data.all[i].name + '</strong><span>&rsaquo;</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item">无已发布的问卷!</li>');
	}
	$('#analysisModal').find('.modal-body').append(ul);
	$('#analysisModal').modal('show');
};


//调入回答内容
function loadAnalysis(id) {
	modalSwitch = false;
	$('#analysisModal').modal('hide');
	$.get("analysisLoad?id=" + id, afterLoadAnalysis);
}

var afterLoadAnalysis = function (data) {
	$('.container-fluid').children('.eachQues').remove();
	$('#questionnaire').find('.panel-heading').find('span').text(data.questionnaire.name); //更改问卷标题
	$('#questionnaire').find('.panel-body').text(data.questionnaire.description); //更改问卷说明

	for (var i = 0, ilen = data.questions.length; i < ilen; i++) {
		var eachQues = '<div class="eachQues"></div>';
		eachQues = $(eachQues).append('<div class="page-header my-panel-success"><span>'
		  + (i + 1) + '. ' + data.questions[i].stem + '</span></div>');
		var eachAnswer = '<div class="eachAnswer"></div>';
		var distrbRow = '<div class="row"></div>';
		var distrbRowDiv = '<div class="col-xs-12 col-sm-12 my-col-progbar"></div>';
		var distrbRowProg = '<div class="progress"></div>';
		for (var j = 0, jlen = data.pieChartData[i].length; j < jlen; j++) {
			var answerRow = '<div class="row"></div>';
			answerRow = $(answerRow).append(
			  '<div class="col-xs-7 col-sm-6 my-col-option"><span>' + data.pieChartData[i][j].content + '</span></div>');
			answerRow = $(answerRow).append(
			  '<div class="col-xs-5 col-sm-6 my-col-progbar"><div class="progress progress-striped active">'
			  + '<div class="progress-bar progress-bar-success" role="progressbar" style="width:'
			  + data.pieChartData[i][j].percentage + '%">' + data.pieChartData[i][j].percentage + '%</div></div></div>');
			eachAnswer = $(eachAnswer).append(answerRow);

			distrbRowProg = $(distrbRowProg).append('<div class="progress-bar progress-bar-warning" role="progressbar" style="width:'
			  + data.pieChartData[i][j].percentage + '%">' + data.pieChartData[i][j].percentage + '%</div>');
		}
		distrbRowDiv = $(distrbRowDiv).append(distrbRowProg);
		distrbRow = $(distrbRow).append(distrbRowDiv);
		eachAnswer = $(eachAnswer).append(distrbRow);
		eachQues = $(eachQues).append(eachAnswer);
		$('#btn-back').before(eachQues);
	}
	$('#btn-back').css('display', 'block');
};
