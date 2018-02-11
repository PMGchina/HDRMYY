var radioCount = 0, checkboxCount = 0, starCount = 0;

$(document).ready(function (e) {
	$.get("questionnaireEdit", afterLoadQuestionnaire);	//调入问卷Questionnaire(有效未发布)
	//调入题类并装配
	$.get("questionType", function (data) {
		$('.makeQuesArea').find('.dropdown').children('ul').remove();
		var ul = '<ul class="dropdown-menu" role="menu">';
		for (i = 0, l = data.all.length; i < l; i++) {
			ul = $(ul).append('<li onclick="makeQues(' + data.all[i].id + ',\'' + data.all[i].name + '\')">' + data.all[i].name + '</li>');
		}
		$('.makeQuesArea').find('.dropdown').append(ul);
	});

	//上移
	$(document).on("click", ".moveUpBtn", function () {
		var eachQuesIndex = $(this).parent(".fnBtns").parent(".eachQues").index();
		if (eachQuesIndex > 1) {
			var eachQues = $(this).parent(".fnBtns").parent(".eachQues");
			var prevHtml = eachQues.prev().html();
			var curHtml = eachQues.html();
			eachQues.prev().html(curHtml);
			eachQues.html(prevHtml);
			//序号
			eachQues.children(".eachQuesUl").find(".titleNo").text(eachQuesIndex);
			eachQues.prev().children(".eachQuesUl").find(".titleNo").text(eachQuesIndex - 1);
			eachQues.prev().find(".fnBtns").remove();
			eachQues.prev().css({"border": "1px solid #fff"});
		} else {
			mdAlert("提醒:", "到最顶头了!");
		}
	});

	//下移
	$(document).on("click", ".moveDownBtn", function () {
		var eachQuesLength = $(".newQuesArea").children(".eachQues").length;
		var eachQuesIndex = $(this).parent(".fnBtns").parent(".eachQues").index();
		if (eachQuesIndex < eachQuesLength) {
			var eachQues = $(this).parent(".fnBtns").parent(".eachQues");
			var nextHtml = eachQues.next().html();
			var curHtml = eachQues.html();
			eachQues.next().html(curHtml);
			eachQues.html(nextHtml);
			//序号
			eachQues.children(".eachQuesUl").find(".titleNo").text(eachQuesIndex);
			eachQues.next().children(".eachQuesUl").find(".titleNo").text(eachQuesIndex + 1);
			eachQues.next().find(".fnBtns").remove();
			eachQues.next().css({"border": "1px solid #fff"});
		} else {
			mdAlert("提醒:", "到最底了!");
		}
	});

	//编辑
	$(document).on("click", ".editBtn", function (e) {
		//编辑的时候禁止其他操作
		$(this).siblings().hide();
		var danxHtml = $(".danx").html();
		var duoxHtml = $(".duox").html();
		var wendHtml = $(".wend").html();
		var pfenHtml = $(".pfen").html();
		var juznHtml = $(".juzn").html();
		//接受编辑内容的容器
		var eachQues = $(this).parent(".fnBtns").parent(".eachQues");
		var quesEditArea = eachQues.find(".quesEditArea");
		var quesType = quesEditArea.attr("data-qu-type");
		//本题选项的个数
		var quesUlLiLength = eachQues.children(".eachQuesUl").children("li").length;
		//单选题 / 多选题==========
		if (quesType === "1" || quesType === "2") {
			if (quesType === "1") {
				quesEditArea.show().html(danxHtml);
			} else {
				quesEditArea.show().html(duoxHtml);
			}
			//本题选项的个数
			var itemsInObj = quesEditArea.find(".items").children(".itemsIn");
			var itemsInLength = itemsInObj.length;
			var itemsInHtml = itemsInObj.html();

			//添加选项
			for (var i = itemsInLength; i < quesUlLiLength - 1; i++) {
				quesEditArea.find(".items").append('<div class="row text-center itemsIn">' + itemsInHtml + '</div>');
			}
			itemsInObj = quesEditArea.find(".items").children(".itemsIn");
			//本题标题
			var titleText = eachQues.find(".eachQuesUl").children("li").eq(0).find(".titleText").text();
			quesEditArea.find(".inputTextarea").val(titleText);
			//是否必答
			titleText = eachQues.find(".eachQuesUl").children("li").eq(0).find(".titleAnswer").text();
			if (titleText === "*") {
				quesEditArea.find(".mustAnswer input").attr("checked", "checked");
			}
			//遍历本题项目的文字
			var liCount = 0;
			eachQues.find(".eachQuesUl").children("li").each(function () {
				//可填空框
				var checkMayFill = $(this).find("input").hasClass("quesFill");
				if (checkMayFill) {
					var itemFillIndex = $(this).index();
					itemsInObj.eq(itemFillIndex - 1).find(".itemFill input").attr("checked", "checked");
				}
				//每题选项
				if (liCount > 0) {
					var optionText = $(this).find("span").text();
					itemsInObj.eq(liCount - 1).find(".optionText").val(optionText);
				}
				liCount++;
			});
		}

		//问答题 / 评分题==========
		if (quesType === "3" || quesType === "4") {
			quesEditArea.show().html(wendHtml);
			var liObj = eachQues.find(".eachQuesUl").children("li").eq(0);
			quesEditArea.find(".inputTextarea").val(liObj.find(".titleText").text());  //本题标题
			if (liObj.find(".titleAnswer").text() === "*") {   //是否必答
				quesEditArea.find(".mustAnswer input").attr("checked", "checked");
			}
		}

		//矩阵题
		if (quesType === "5") {
			quesEditArea.show().html(juznHtml);
			//本题标题
			var titleText = eachQues.find(".eachQuesUl").find(".titleText").text();
			quesEditArea.find(".inputTextarea").val(titleText);
			//是否必答
			titleText = eachQues.find(".eachQuesUl").find(".titleAnswer").text();
			if (titleText === "*") {
				quesEditArea.find(".juznMustAnswer input").attr("checked", "checked");
			}
			//左标题
			var trObj = eachQues.find(".eachQuesUl .juzn_table tr");
			var leftTextarea = "", td_text = "";
			trObj.each(function () {
				td_text = $(this).find("td").eq(0).text();
				if (td_text !== "") {
					leftTextarea += (leftTextarea === "" ? "" : ",") + td_text;
				}
			});
			quesEditArea.find(".leftTextarea").val(leftTextarea);

			//遍历: 选项标题,可填空,单选/多选
			var columnTitles = new Array();
			trObj.eq(0).find("td").each(function () {
				columnTitles.push({title: $(this).text()});
			});

			var danORduo = "", curType = "";
			var itemFills = new Array();
			trObj.eq(1).find("td input").each(function () {
				curType = $(this).attr("type");
				if (curType === "text") {
					itemFills.push({itemFill: "checked"});
				} else {
					itemFills.push({itemFill: ""});
					if (danORduo === "") {
						danORduo = curType;
					}
				}
			});

			//单选/多选
			var danOrduoEditObj = quesEditArea.find("table tbody tr").eq(0).find("td").eq(1).find(".danORduo");
			if (danORduo === "checkbox") {
				danOrduoEditObj.eq(1).attr("checked", "checked");
			} else {
				danOrduoEditObj.eq(0).attr("checked", "checked");
			}

			//选项标题,可填空
			var itemsInObj = quesEditArea.find(".items").children(".itemsIn");
			var itemsInLength = itemsInObj.length;
			var itemsInHtml = itemsInObj.eq(0).html();
			for (var i = itemsInLength, l = itemFills.length; i < l; i++) {
				quesEditArea.find(".items").append("<div class='itemsIn'>" + itemsInHtml + "</div>");
			}
			itemsInObj = quesEditArea.find(".items").children(".itemsIn");
			for (var i = 0, l = itemFills.length; i < l; i++) {
				itemsInObj.eq(i).find(".juznOptionMark").val(columnTitles[i + 1].title);
				if (itemFills[i].juznItemFill === "checked") {
					itemsInObj.eq(i).find(".juznItemFill input").attr("checked", "checked");
					itemsInObj.eq(i).find(".juznItemFill input").removeAttr("disabled");
				}
				if (i === l - 1) {
					itemsInObj.eq(i).find(".juznItemFill input").removeAttr("disabled");
				}
			}
		}
	});

	//删除题
	$(document).on("click", ".deleteBtn", function () {
		var eachQues = $(this).parent(".fnBtns").parent(".eachQues");
		var cid = eachQues.attr("data-qu-id");
		if (cid === "0") {
			fnDeleteQues(eachQues);
		} else {
			mdConfirm('请确认:', '确实要删除远程的此题吗？', null, function () {
				fnAJAX("questionDelete", "DELETE", {questionId: cid}, function (data) {
					if (data.count > 0) {
						fnDeleteQues(eachQues);
					}
				}, true);  //先删除远程数据成功时
			});
		}
	});

	//增加选项
	$(document).on("click", ".addItem", function () {
		var items = $(this).parent(".my-col").parent(".row").parent(".container").prev(".items");
		items.append('<div class="row text-center itemsIn">' + items.children(".itemsIn").html() + '</div>');
	});

	//增加选项(矩阵)
	$(document).on("click", ".juznAddItem", function () {
		var items = $(this).prev(".items");
		items.append('<div class="itemsIn">' + items.children(".itemsIn").html() + '</div>');
		var itemsInLast = $(this).prev(".items").find(".itemsIn").last();
		if (itemsInLast.prev().find(".juznItemFill input").is(":checked")) {
			itemsInLast.find(".juznItemFill input").attr("checked", "checked");
		}
		itemsInLast.prev().find(".juznItemFill input").removeAttr("checked");
		itemsInLast.prev().find(".juznItemFill input").attr("disabled", "disabled");
		itemsInLast.find(".juznItemFill input").removeAttr("disabled");
	});

	//删除项
	$(document).on("click", ".delItem", function () {
		var thisItem = $(this).parent(".my-col");
		var items = thisItem.parent(".itemsIn").parent(".items");
		if (items.find(".itemsIn").length > 1) {
			thisItem.parent(".itemsIn").remove();
		} else {
			mdAlert("警告:", "只剩一行,不能删除!");
		}
	});

	//删除项(矩阵)
	$(document).on("click", ".juznItemDel", function () {
		var items = $(this).parent(".itemsIn").parent(".items");
		if (items.find(".itemsIn").length > 1) {
			if ($(this).parent(".itemsIn").index() === items.find(".itemsIn").last().index()) {
				items.find(".itemsIn").last().prev().find(".juznItemFill input").removeAttr("disabled");
			}
			$(this).parent(".itemsIn").remove();
		} else {
			mdAlert("警告:", "只剩一行,不能删除!");
		}
	});

	//取消编辑button
	$(document).on("click", ".cancel", function () {
		$(this).parent(".editOverBtns").parent(".quesEditArea").empty().hide();
		$(".eachQues").css({
			"border": "1px solid #fff"
		});
		$(".fnBtns").remove();
	});

	//完成编辑button
	$(document).on("click", ".finish", function () {
		var quesEditArea = $(this).parent(".editOverBtns").parent(".quesEditArea"); //编辑题目区
		var quesType = quesEditArea.attr("data-qu-type"); //获取题目类型
		var eachQuesUl = quesEditArea.parent(".eachQues").children(".eachQuesUl");
		switch (quesType) {
			case "1":   //单选
			case "2":   //多选
				//编辑本题选项的个数
				var quesUlLiLength = eachQuesUl.children("li").length - 1;
				//本题标题
				var getQuesTitle = quesEditArea.find(".inputTextarea").val();
				eachQuesUl.children("li").eq(0).find(".titleText").text(getQuesTitle);
				//是否必答
				eachQuesUl.children("li").eq(0).find(".titleAnswer").text(quesEditArea.find(".mustAnswer input").is(':checked') ? "*" : "");
				//删除已有选项
				for (var opt = quesUlLiLength; opt > 0; opt--) {
					eachQuesUl.children("li").eq(opt).remove();
				}

				//遍历每题项目的文字
				var curInputName = "", curInputType = "";
				if (quesType === "1") {
					radioCount++;
					curInputName = "a" + radioCount;
					curInputType = "radio";
				} else if (quesType === "2") {
					checkboxCount++;
					curInputName = "b" + checkboxCount;
					curInputType = "checkbox";
				}
				quesEditArea.children(".items").children(".itemsIn").each(function () {
					//题选项
					var itemText = $(this).find(".optionText").val(); //获取填写信息
					eachQuesUl.append('<li class="lipadding"><label><input name="' + curInputName + '" type="' + curInputType + '" value=""><span>' + itemText + '</span></label></li>');
					if ($(this).find(".itemFill input").is(':checked')) {
						eachQuesUl.children("li").eq($(this).index() + 1).find("label").after("<input type='text' class='quesFill'>");
					}
				});
				break;

			case "3":   //问答
			case "4":   //评分
				var getQuesTitle = quesEditArea.find(".inputTextarea").val(); //获取评分题目
				eachQuesUl.children("li").eq(0).find(".titleText").text(getQuesTitle);
				//是否必答
				eachQuesUl.children("li").eq(0).find(".titleAnswer").text(quesEditArea.find(".mustAnswer input").is(':checked') ? "*" : "");
				if (quesType === "4") {
					eachQuesUl.find("li").eq(1).find(".starClean").click();
				}
				break;

			case "5":   //矩阵
				eachQuesUl.children("li").eq(1).children("table").children("tbody").empty();
				var danOrDuo = quesEditArea.find(".danORduo:checked").val();
				var getQuesTitle = quesEditArea.find("textarea.inputText.inputTextarea").val();	//获取标题
				//是否必答
				eachQuesUl.eq(0).find(".titleAnswer").text(quesEditArea.find(".juznMustAnswer input").is(':checked') ? "*" : "");
				eachQuesUl.find(".titleText").text(getQuesTitle);

				var x_iteam = new Array(); //获取 横向选项
				var y_iteam = "," + quesEditArea.find(".leftTextarea").val();	//左标题

				quesEditArea.find(".items").children(".itemsIn").each(function () {
					var columnTitle = $(this).find(".juznOptionMark").val(); //获取填写信息
					var checkbox = $(this).find(".juznItemFill input").is(':checked');
					x_iteam.push({name: columnTitle, checkbox: checkbox});
				});

				var y_items = y_iteam.split(",");
				var td = "", curInputName = "", curInputType = "";
				for (var y = 0, yl = y_items.length; y < yl; y++) {  //行
					if (danOrDuo === "0") {
						radioCount++;
						curInputName = "a" + radioCount;
						curInputType = "radio";
					} else {
						checkboxCount++;
						curInputName = "b" + checkboxCount;
						curInputType = "checkbox";
					}
					td = '<td>' + y_items[y] + '</td>';
					for (var x = 0, xl = x_iteam.length; x < xl; x++) {   //列
						if (y_items[y] === "") {
							td += '<td>' + x_iteam[x].name + '</td>';
						} else {
							if (x_iteam[x].checkbox) {
								td += '<td style="width:5%"><input type="text" value=""></td>'; //可填空
							} else {
								td += '<td><input name="' + curInputName + '" type="' + curInputType + '" value=""></td>';
							}
						}
					}
					eachQuesUl.children("li").eq(1).children("table").children("tbody").append('<tr>' + td + '</tr>');
				}
				break;
		}
		quesEditArea.find(".cancel").click();	//触发-取消编辑
	});
});

function makeQues(index, quesTypeTip) {
	if (index === 0)
		return;
	index = index + "";
	quesTypeTip = "【" + quesTypeTip + "】";

	var quesOrder = $(".newQuesArea").find(".eachQues").length + 1;
	if (quesOrder === 1) {
		if (!$(".newQuesArea").find("div").hasClass("questionnaire")) {
			var questionnaire = '<div class="questionnaire" data-qn-id="0"></div>';
			questionnaire = $(questionnaire).append('<input type="text" value="" placeholder="请输入问卷题目..."><textarea value="" placeholder="请输入问卷描述..."></textarea>');
			$(".newQuesArea").append(questionnaire);
		}
	}

	var eachQues = '<div class="eachQues" data-qu-id="0"></div>';
	var eachQuesUl = '<ul class="eachQuesUl"></ul>';
	eachQuesUl = $(eachQuesUl).append('<li class="quesTitle"><span class="titleNo">' + quesOrder
			+ '</span>. <span class="titleText">请编辑此题目...</span>'
			+ '<span class="titleTip">' + quesTypeTip + '</span><span class="titleAnswer">*</span></li>');

	if (index !== "5") {
		if (index === "1") {
			radioCount++;
			var li_left = '<li class="lipadding"><label><input name="a' + radioCount + '" type="radio" value=""><span>选项';
			var li_right = '</span></label></li>';
			eachQuesUl = $(eachQuesUl).append(li_left + 1 + li_right);
			eachQuesUl = $(eachQuesUl).append(li_left + 2 + li_right);
		} else if (index === "2") {
			checkboxCount++;
			var li_left = '<li class="lipadding"><label><input name="b' + checkboxCount + '" type="checkbox" value=""><span>选项';
			var li_right = '</span></label></li>';
			eachQuesUl = $(eachQuesUl).append(li_left + 1 + li_right);
			eachQuesUl = $(eachQuesUl).append(li_left + 2 + li_right);
			eachQuesUl = $(eachQuesUl).append(li_left + 3 + li_right);
		} else if (index === "3") {
			eachQuesUl = $(eachQuesUl).append('<li class="lipadding"><textarea></textarea></li>');
		} else if (index === "4") {
			starCount++;
			eachQuesUl = $(eachQuesUl).append('<li class="lipadding"><div id="star' + starCount
					+ '" class="block clearfix"><div class="star_score"></div><span class="fenshu"></span><span class="starClean" onclick="starClean('
					+ starCount + ')">重&nbsp置</span></div></li>');
		}
	} else {
		radioCount++;
		eachQuesUl = $(eachQuesUl).append('<li class="lipadding"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="juzn_table"><tbody><tr>'
				+ '<td></td><td>选项1</td><td>选项2</td><td>选项3</td></tr><tr><td>询问1</td>'
				+ '<td><input name="a' + radioCount + '" type="radio" value=""></td>'
				+ '<td><input name="a' + radioCount + '" type="radio" value=""></td>'
				+ '<td><input name="a' + radioCount + '" type="radio" value=""></td>'
				+ '</tr></tbody></table></li>');
	}
	eachQues = $(eachQues).append(eachQuesUl);
	eachQues = $(eachQues).append('<div class="quesEditArea" data-qu-type="' + index + '"></div>');
	mouseHover(eachQues);
	$(".newQuesArea").append(eachQues);
	if (index === "4") {
		scoreFun($("#star" + starCount));
	}
}

function mouseHover(obj) {  //鼠标移上去显示按钮
	$(obj).hover(function () {
		var fnBtnsHtml = '<div id="fnBtns" class="fnBtns">';
		fnBtnsHtml += '<a href="javascript:void(0)" class="moveUpBtn">上移</a>';
		fnBtnsHtml += '<a href="javascript:void(0)" class="moveDownBtn">下移</a>';
		fnBtnsHtml += '<a href="javascript:void(0)" class="editBtn">编辑</a>';
		fnBtnsHtml += '<a href="javascript:void(0)" class="deleteBtn">删除</a></div>';
		$(this).css({
			"border": "1px solid #0099ff", "border-radius": "8px"
		});
		$(this).children(".eachQuesUl").after(fnBtnsHtml);
	}, function () {
		$(this).css({
			"border": "1px solid #fff"
		});
		$(this).children(".fnBtns").remove();
		//$(this).children(".quesEditArea").hide();
	});
}

//starScore
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

//删除某一个题
var fnDeleteQues = function (eachQues) {
	var beforTitleNo = eachQues.find(".titleNo").text();
	var reNo = 1;
	eachQues.parent(".newQuesArea").find(".eachQues").each(function () { //重新编号
		if ($(this).find(".titleNo").text() !== beforTitleNo) {
			$(this).find(".titleNo").text(reNo);
			reNo++;
		}
	});
	eachQues.remove();
	if ($(".newQuesArea").find(".eachQues").length === 0) {
		$(".questionnaire").remove();
	}
};

//调入问卷期刊Questionnaire之后
var afterLoadQuestionnaire = function (data) {
	$('#editModal').find('.modal-body').find('ul').remove();
	var ul = '<ul class="list-group"></ul>';
	var len = data.all.length;
	if (len > 0) {
		for (var i = 0; i < len; i++) {
			ul = $(ul).append('<li class="list-group-item" onclick="loadQuestion('
					+ data.all[i].id + ')"><strong>'
					+ data.all[i].name + '</strong><span>&rsaquo;</span></li>');
		}
	} else {
		ul = $(ul).append('<li class="list-group-item">无有效未发布的问卷!</li>');
	}
	$('#editModal').find('.modal-body').append(ul);
	$('#editModal').modal('show');
};

//调入每题Question
function loadQuestion(qnId) {
	modalSwitch = false;
	$('#editModal').modal('hide');
	fnAJAX("questionLoad", "POST", {questionnaireId: qnId}, afterLoadQuestion, true);
}

var afterLoadQuestion = function (data) {
	if (data === null) {
		mdAlert("异常:", "问卷调入异常!");
	} else {
		var Qu = data.load;
		var Qn = Qu[0].questionnaire;

		$(".newQuesArea").empty();
		var radioCount = 0, checkboxCount = 0, starCount = 0;

		var QnHtml = '<div class="questionnaire" data-qn-id="' + (Qn.id === null ? 0 : Qn.id) + '"></div>';
		QnHtml = $(QnHtml).append('<input type="text" value="" placeholder="请输入问卷题目..." disabled><textarea value="" placeholder="请输入问卷描述..." disabled></textarea>');
		$(".newQuesArea").append(QnHtml);
		$(".newQuesArea").children(".questionnaire").find("input").val(Qn.name);
		$(".newQuesArea").children(".questionnaire").find("textarea").val(Qn.description);

		//遍历每个题
		for (var i = 0, il = Qu.length; i < il; i++) {
			var eachQues = '<div class="eachQues" data-qu-id="' + (Qu[i].id === null ? 0 : Qu[i].id) + '"></div>';
			var eachQuesUl = '<ul class="eachQuesUl"></ul>';
			var tipIndex = Qu[i].stem.indexOf("【");
			eachQuesUl = $(eachQuesUl).append('<li class="quesTitle"><span class="titleNo">'
					+ Qu[i].sort + '</span>. <span class="titleText">'
					+ Qu[i].stem.substring(0, tipIndex) + '</span><span class="titleTip">'
					+ Qu[i].stem.substr(tipIndex) + '</span><span class="titleAnswer">'
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

			mouseHover(eachQues); //只有编辑时有用

			$(".newQuesArea").append(eachQues);
			if (quesType === 4) {
				scoreFun($("#star" + starCount));
			}
		}
	}
};

//提交问卷
function submitQuestionnaire(isReturn) {
	var Qn = {id: null, name: "", description: "", questions: []};
	var QnObj = $(".newQuesArea").children(".questionnaire");
	Qn.name = QnObj.find("input").val();
	if (Qn.name) {
		Qn.id = parseInt(QnObj.attr("data-qn-id")) || null;
		Qn.description = QnObj.find("textarea").val();

		$(".newQuesArea").children(".eachQues").each(function () {
			var quesType = parseInt($(this).find(".quesEditArea").attr("data-qu-type"));
			var Qu = {id: null, type: {id: null}, sort: 0, stem: "", required: true, content: null};
			Qu.id = parseInt($(this).attr("data-qu-id")) || null;
			var QuObj = $(this).children("ul").children("li");
			Qu.type.id = quesType;
			Qu.sort = parseInt(QuObj.eq(0).find(".titleNo").text());
			Qu.stem = QuObj.eq(0).find(".titleText").text() + QuObj.eq(0).find(".titleTip").text();
			if (QuObj.eq(0).find(".titleAnswer").text() === "") {
				Qu.required = false;
			}

			var row = [];
			var temp = "";
			if (quesType === 1 || quesType === 2) {
				$(this).children("ul").children("li:gt(0)").each(function () {
					row.push($(this).find("label span").text() + ($(this).find("input").hasClass("quesFill") ? "#@#" : ""));
				});
				Qu.content = row.join(",");
			} else if (quesType === 3 || quesType === 4) {
			} else if (quesType === 5) {
				QuObj.eq(1).children("table").children("tbody").find("tr").each(function () {
					var cloumn = [];
					if ($(this).index() === 0) {
						$(this).find("td").each(function () {
							temp = $(this).text();
							if (temp.indexOf(",") > -1) {
								temp = commaToBigComma(temp);
								$(this).text(temp);
							}
							cloumn.push(temp);
						});
					} else {
						$(this).find("td").each(function () {
							if ($(this).index() === 0) {
								cloumn.push($(this).text());
							} else {
								var typeObj = $(this).find("input");
								if (typeObj.is(":radio")) {
									cloumn.push("0");
								} else if (typeObj.is(":checkbox")) {
									cloumn.push("1");
								} else if (typeObj.is(":text")) {
									cloumn.push("2");
								}
							}
						});
					}
					row.push(cloumn.join(","));
				});
				Qu.content = row.join("#@#");
			}
			Qn.questions.push(Qu);
		});
		if (isReturn) {
			return JSON.stringify(Qn);
		} else {
			if (Qn.id === null) {
				fnAJAX("addWhole", "POST", Qn, function (data) {
					mdAlert("成功:", "问卷提交成功! " + data.created);
				}, true);
			} else {
				fnAJAX("updateWhole", "POST", Qn, function (data) {
					mdAlert("成功:", "问卷更新成功! " + data.update + "个");
				}, true);
			}
		}
	} else {
		mdAlert("提醒:", "问卷题目,为空!");
	}
}

function commaToBigComma(content) {
	var array = content.split(",");
	return array.join("，");
}


//==============================
function fnAJAX(url, method, json, callback, isUseData) {
	$.ajax({
		headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
		type: method,
		url: url,
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
