var contentPathKey = 'currentContentDivPath';
var isPopState = true;
var solidSharpPath = localStorage.getItem(contentPathKey);

/**
 * 焦点在iframe框外,按F5时刷新iframe框内
 */
$("body").bind("keydown", function () {
	if (event.keyCode === 116) {
		enableGotoCurrentPage = true;
		gotoPageNumber(getCurrentPageNumber() + 1);
		enableGotoCurrentPage = false;
		return false;
	}
});

window.addEventListener("popstate", function () {
	if (isPopState) {
		if (solidSharpPath) {
			loadContent(solidSharpPath);
		} else {
			$(window).attr('location', "adminIndex");
		}
	}
});

window.onload = function () {
	if (solidSharpPath) {
		loadContent(solidSharpPath);
	}
};

/**
 * 用jQuery load方法 替换iFrame
 * @param url
 * 2017/8/22
 */
function loadContent(name) {
	if (name) {
		var leftPath = location.pathname;
		leftPath = leftPath.substring(0, leftPath.indexOf("#"));
		var sharpPath = (name.indexOf("admin/") === -1) ? name : name.substr(name.indexOf("admin/") + 6);

		if (name.indexOf('logout') !== -1) {
			$(window).attr('location', "adminIndex");
		} else {
			isPopState = false;
			
			//在进入下个目标模块之前，处理被遗弃的全局变量，以免带入下个模块 
			handleDesertedGlobalVariables(name);
			
			$.get(name, function(data) {
				$(".indexContentBody").html(data);
				localStorage.setItem(contentPathKey, sharpPath);
				history.replaceState({}, 0, leftPath);
				isPopState = true;
			});
		}
	}
}

/**
 * 在进入下个目标模块之前，处理被遗弃的全局变量，以免带入下个模块
 * @param url - 即将进入的模块url
 */
function handleDesertedGlobalVariables(url) {
	//除了问卷管理模块以外，所有其他模块不需要使用 loadPageableDataAfter 方法去设置table中数据的状态
	if (url.indexOf("questionnaire/adminList") === -1) {
		loadPageableDataAfter = null;
	}

	beforeAdd = beforeUpdate = beforeDelete = beforeCopy = null;
	updateBefore = copyBefore = deleteBefore = null;
	addModalWidth = updateModalWidth = copyModalWidth = null;
	addModalHeight = updateModalHeight = copyModalHeight = null;
}