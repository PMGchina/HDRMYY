var radioCount = 0, checkboxCount = 0, starCount = 0;

$(document).ready(function (e) {
	$.get("questionnaireAnswer", afterLoadQuestionnaire);	//调入问卷期刊Questionnaire
});

//评分starScore
function scoreFun(obj, opts) {
	var defaults = {
		fen_d: 16,
		ScoreGrade: 10,
		types: [],
		nameScore: "fenshu",
		parent: "star_score",
		attitude: "attitude"
	};
	var options = $.extend({}, defaults, opts);
	var countScore = obj.find("." + options.nameScore);
	var startParent = obj.find("." + options.parent);
	var atti = obj.find("." + options.attitude);
	var now_cli;
	var fen_cli;
	var atu;
	var fen_d = options.fen_d;
	var len = options.ScoreGrade;
	startParent.width(fen_d * (len - 2));
	var preA = (5 / len);
	for (var i = 0; i < len; i++) {
		var newSpan = $("<a href='javascript:void(0)'></a>");
		newSpan.css({"left": 0, "width": fen_d * (i + 1), "z-index": len - i});
		newSpan.appendTo(startParent);
	}
	startParent.find("a").each(function (index, element) {
		$(this).click(function () {
			now_cli = index;
			show(index, $(this));
		});
		$(this).mouseenter(function () {
			show(index, $(this));
		});
		$(this).mouseleave(function () {
			if (now_cli >= 0) {
				var scor = preA * (parseInt(now_cli) + 1);
				startParent.find("a").removeClass("clibg");
				startParent.find("a").eq(now_cli).addClass("clibg");
				var ww = fen_d * (parseInt(now_cli) + 1);
				startParent.find("a").eq(now_cli).css({"width": ww, "left": "0"});
				if (countScore) {
					countScore.text(scor + '分');
				}
			} else {
				startParent.find("a").removeClass("clibg");
				if (countScore) {
					countScore.text("");
				}
			}
		});
	});
	function show(num, obj) {
		var n = parseInt(num) + 1;
		var lefta = num * fen_d;
		var ww = fen_d * n;
		var scor = preA * n;
		atu = options.types[parseInt(num)];
		obj.find("a").removeClass("clibg");
		obj.addClass("clibg");
		obj.css({"width": ww, "left": "0"});
		countScore.text(scor + '分');
		atti.text(atu);
	}
}
function starClean(index) {
	var obj = $("#star" + index);
	obj.find(".star_score").empty();
	obj.find(".fenshu").text("");
	scoreFun(obj);
	obj.find(".starClean").attr("text-decoration", "none");
}

//调入问卷Questionnaire之后
var afterLoadQuestionnaire = function (data) {
	$('#answerModal').find('.modal-body').find('ul').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.all.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="loadQuestion(' + data.all[i].id + ')"><strong>'
					+ data.all[i].name + '</strong><span>&rsaquo;</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item">无已发布的问卷!</li>');
	}
	$('#answerModal').find('.modal-body').append(ul);
	$('#answerModal').modal('show');
};

//调入问卷每题Question
function loadQuestion(qnId) {
	modalSwitch = false;
	fnAJAX("questionLoad", "POST", true, {questionnaireId: qnId}, afterLoadQuestion, true);
//	fnAJAX("answerCheck", "POST", true, {questionnaireId: qnId}, function (data) {
//		if (data.check) {
//			mdAlert("提醒:", "对不起，您已经回答过了!");
//		} else {
//			$('#answerModal').modal('hide');
//			fnAJAX("questionLoad", "POST", true, {questionnaireId: qnId}, afterLoadQuestion, true);
//		}
//	}, true);
}

var afterLoadQuestion = function (data) {
	$('#answerModal').modal('hide');
	if (data === null) {
		mdAlert("异常:", "问卷调入异常!");
	} else {
		var Qu = data.load;
		var Qn = Qu[0].questionnaire;
		$(".newQuesArea").empty();
		var radioCount = 0, checkboxCount = 0, starCount = 0;
		var QnHtml = '<div class="questionnaire" data-qn-id="' + (Qn.id === null ? 0 : Qn.id) + '"></div>';
		var QnUl = '<ul class="list-group"></ul>';
		QnUl = $(QnUl).append('<li class="list-group-item my-panel-info qnTitle">' + Qn.name + '</li>');
		QnUl = $(QnUl).append('<li class="list-group-item qnDescription">' + Qn.description + '</li>');
		QnHtml = $(QnHtml).append(QnUl);
		$(".newQuesArea").append(QnHtml);
		//调入科室(同步)
		fnAJAX("answerDepartment", "POST", false, {}, function (data) {
			var row = '<div class="row ks-row"></div>';
			var col = '<div class="col-xs-11"></div>';
			var kesh = '<select class="form-control my-panel-warning ks-select"></select>';
			kesh = $(kesh).append('<option>请选择科室...</option>');
			for (var i = 0, l = data.all.length; i < l; i++) {
				kesh = $(kesh).append('<option>' + data.all[i].name + '</option>');
			}
			col = $(col).append(kesh);
			row = $(row).append(col);
			row = $(row).append('<div class="col-xs-1 ks-must">*</div>');
			$(".newQuesArea").append(row);
			//侦听
			$('.ks-select').change(function () {
				fnAJAX("answerCheck", "POST", true, {questionnaireId: Qn.id, departmentName: $(this).val()}, function (data) {
					if (data.check) {
						mdAlert("提醒:", "对不起，您对此科室的问卷，已答过了!");
					}
				}, true);
			});
		}, true);

		//遍历每个题
		for (var i = 0, il = Qu.length; i < il; i++) {
			var eachQues = '<div class="eachQues" data-qu-id="' + (Qu[i].id === null ? 0 : Qu[i].id) + '"></div>';
			var eachQuesUl = '<ul class="eachQuesUl"></ul>';
			var tipIndex = Qu[i].stem.indexOf("【");
			eachQuesUl = $(eachQuesUl).append('<li class="quesTitle"><span class="titleNo">'
					+ Qu[i].sort + '</span>. <span class="titleText">'
//				+ Qu[i].stem.substring(0, tipIndex) + '</span><span class="titleTip">'
//				+ Qu[i].stem.substr(tipIndex) + '</span><span class="titleAnswer">'
					+ Qu[i].stem.substring(0, tipIndex) + '</span><span class="titleAnswer">'
					+ (Qu[i].required ? "*" : "") + '</span></li>');

			var quesType = Qu[i].type.id;
			if (quesType === 1) {
				radioCount++;
				var cntArr = Qu[i].content.split(",");
				var idx = 0, optionText = "", isAddInput;
				for (j = 0, jl = cntArr.length; j < jl; j++) {
					idx = cntArr[j].indexOf("#@#");
					if (idx < 0) {
						optionText = cntArr[j];
						isAddInput = false;
					} else {
						optionText = cntArr[j].substring(0, idx);
						isAddInput = true;
					}
					eachQuesUl = $(eachQuesUl).append('<li class="lipadding"><label><input name="a'
							+ radioCount + '" type="radio" value=""><span>'
							+ optionText + '</span></label>'
							+ (isAddInput ? "<input type='text' class='quesFill'>" : "") + '</li>');
				}

			} else if (quesType === 2) {
				checkboxCount++;
				var cntArr = Qu[i].content.split(",");
				var idx = 0, optionText = "", isAddInput = false;
				for (j = 0, jl = cntArr.length; j < jl; j++) {
					idx = cntArr[j].indexOf("#@#");
					if (idx < 0) {
						optionText = cntArr[j];
					} else {
						optionText = cntArr[j].substring(0, idx);
						isAddInput = true;
					}
					eachQuesUl = $(eachQuesUl).append('<li class="lipadding"><label><input name="b'
							+ checkboxCount + '" type="checkbox" value=""><span>'
							+ optionText + '</span></label>'
							+ (isAddInput ? "<input type='text' class='quesFill'>" : "") + '</li>');
				}
			} else if (quesType === 3) {
				eachQuesUl = $(eachQuesUl).append('<li class="lipadding"><textarea></textarea></li>');
			} else if (quesType === 4) {
				starCount++;
				eachQuesUl = $(eachQuesUl).append('<li class="lipadding"><div id="star' + starCount
						+ '" class="block clearfix"><div class="star_score"></div><span class="fenshu"></span><span class="starClean" onclick="starClean('
						+ starCount + ')">重&nbsp置</span></div></li>');
			} else if (quesType === 5) {
				radioCount++;
				checkboxCount++;
				var cntRow = Qu[i].content.split("#@#");
				var trs = "";
				for (j = 0, jl = cntRow.length; j < jl; j++) {
					var cntCol = cntRow[j].split(",");
					var td = "";
					for (var k = 0, kl = cntCol.length; k < kl; k++) {
						if (j === 0) {
							td += '<td>' + cntCol[k] + '</td>';
						} else {
							if (k === 0) {
								td += '<td>' + cntCol[k] + '</td>';
							} else {
								if (cntCol[k] === "0") {
									td += '<td><input name="a' + radioCount + '" type="radio" value=""></td>';
								} else if (cntCol[k] === "1") {
									td += '<td><input name="a' + radioCount + '" type="checkbox" value=""></td>';
								} else if (cntCol[k] === "2") {
									td += '<td style="width:5%"><input type="text" value=""></td>';
								}
							}
						}
					}
					trs += "<tr>" + td + "</tr>";
				}
				eachQuesUl = $(eachQuesUl).append('<li><table width="100%" border="0" cellspacing="0" cellpadding="0" class="juzn_table"><tbody>' + trs + '</tbody></table></li>');
			}
			eachQues = $(eachQues).append(eachQuesUl);
			eachQues = $(eachQues).append('<div class="quesEditArea" data-qu-type="' + quesType + '"></div>');
			$(".newQuesArea").append(eachQues);
			if (quesType === 4) {
				scoreFun($("#star" + starCount));
			}
		}
	}
};

//提交答案
function submitAnswer() {
	var answer = {questionnaireId: null, departmentName: null, answers: []};
	answer.questionnaireId = parseInt($(".newQuesArea").find(".questionnaire").attr("data-qn-id")) || null;
	//检查选择科室是否为空
	answer.departmentName = $(".ks-select").val() || null;
	if (answer.departmentName === "请选择科室...") {
		mdAlert("提醒:", "科室未选!");
	} else {
		fnAJAX("answerCheck", "POST", true, {questionnaireId: answer.questionnaireId, departmentName: answer.departmentName}, function (data) {
			if (data.check) {
				mdAlert("提醒:", "对不起，您对此科室的问卷，已答过了!");
			} else {
				//遍历必答题是否回答
				var checkAns = []; //[{sort:1, required:true, isAnswer:false}]
				$(".newQuesArea").children(".eachQues").each(function () {
					var ans = {content: "", optionalRemark: "", questionId: null};
					ans.questionId = parseInt($(this).attr("data-qu-id")) || null;
					var checkAnswer = {sort: "", required: false, isAnswer: false};
					checkAnswer.sort = $(this).children("ul").find("li").eq(0).find(".titleNo").text();
					if ($(this).children("ul").find("li").eq(0).find(".titleAnswer").text() !== "") {
						checkAnswer.required = true;
					}
					var rowContent = [], rowRemark = [], temp = "";
					var quesType = parseInt($(this).find(".quesEditArea").attr("data-qu-type"));
					if (quesType === 1 || quesType === 2) {
						$(this).children("ul").children("li:gt(0)").each(function () {
							if ($(this).find("label input").is(":checked")) {
								rowContent.push($(this).find("label span").text());
							}
							temp = $(this).find("input:text").val();
							if (typeof (temp) === "undefined") {
								rowRemark.push("");
							} else {
								if (temp === "") {
									rowRemark.push(" ");
								} else {
									if (temp.indexOf(",") > -1) {
										temp = commaToBigComma(temp);
										$(this).find("input:text").val(temp);
									}
									rowRemark.push(temp);
								}
							}
						});
						ans.content = rowContent.join("#@#");
						ans.optionalRemark = rowRemark.join("#@#");
					} else if (quesType === 3) {
						ans.content = $(this).children("ul").find("li").eq(1).find("textarea").val();
					} else if (quesType === 4) {
						ans.content = $(this).children("ul").find("li").eq(1).find(".fenshu").text();
					} else if (quesType === 5) {
						var header = [];
						$(this).children("ul").find("li").eq(1).children("table").children("tbody").find("tr").each(function () {
							if ($(this).index() === 0) {
								$(this).find("td").each(function () {
									header.push($(this).text());
								});
							} else {
								var colContent = [], colRemark = [], idx = 0;
								$(this).find("td").each(function () {
									idx = $(this).index();
									if (idx === 0) {
										colRemark.push($(this).text());
									} else {
										if ($(this).find("input").is(":text")) {
											temp = $(this).find("input").val();
											if (temp.indexOf(",") > -1) {
												temp = commaToBigComma(temp);
												$(this).find("input").val(temp);
											}
											colRemark.push(temp);
										} else {
											if ($(this).find("input").is(":checked")) {
												colContent.push(header[idx]);
											}
										}
									}
								});
								rowContent.push(colContent.join(","));
								rowRemark.push(colRemark.join(","));
							}
						});
						ans.content = rowContent.join("#@#");
						ans.optionalRemark = rowRemark.join("#@#");
					}
					answer.answers.push(ans);
					if (quesType === 5) {
						if (ans.content) {
							var isChkOk = true;
							var contentArr = ans.content.split("#@#");
							for (var i = 0, len = contentArr.length; i < len; i++) {
								if (!contentArr[i]) {
									isChkOk = false;
									break;
								}
							}
							checkAnswer.isAnswer = isChkOk;
						}
					} else {
						if (ans.content) {
							checkAnswer.isAnswer = true;
						}
					}
					checkAns.push(checkAnswer);
				});
				if (checkAnswerIsAll(checkAns)) {
					fnAJAX("answerAdd", "POST", true, answer, afterSubmitAnswer, true);
				}
			}
		}, true);
	}
}

function checkAnswerIsAll(checkArray) {
	var rtn = false;
	var alarm = "";
	for (var i = 0, len = checkArray.length; i < len; i++) {
		if (checkArray[i].required) {
			if (!checkArray[i].isAnswer) {
				alarm += "第 " + checkArray[i].sort + " 题 必答, 还未答!<br>";
			}
		}
	}
	if (alarm) {
		mdAlert("通知:", alarm);
	} else {
		rtn = true;
	}
	return rtn;
}

var afterSubmitAnswer = function (data) {
	if (data.count === -1) {
		mdAlert("提醒:", "对不起，您已经回答过了！");
	} else {
		mdDialog("成功:", "感谢您的参与，回答成功！");
		//显示回答统计提示
	}
};

//==============================
function fnAJAX(url, method, async, json, callback, isUseData) {
	$.ajax({
		headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
		type: method,
		url: url,
		async: async,
		data: JSON.stringify(json),
		success: function (data) {
			if (callback) {
				if (isUseData) {
					callback(data);
				} else {
					callback();
				}
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.responseText);
		}
	});
}
