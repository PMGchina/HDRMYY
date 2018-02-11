//==============================
function submitAjax(url, type, json, callback, isUseData) {
  $.ajax({
    headers: {'Accept': 'application/json', 'Content-Type': 'application/json;charset=UTF-8'},
    type: type,
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
