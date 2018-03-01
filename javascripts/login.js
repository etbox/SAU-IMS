(function(window) {
  'use strict';
  var $ = window.jQuery;

  function showLog() { /*把登陆界面显示出来*/
    var target0 = document.getElementById('login');
    target0.style.display = 'block';
  }

  function hiddenLog() { /*隐藏界面*/
    var target0 = document.getElementById('login');
    target0.style.display = 'none';
  }

  function checkUserName(obj) {
    var username = obj;
    var notOK = document.getElementById('nameNotNull');
    var notOKimg = document.getElementById('F').style;
    var OKimg = document.getElementById('T').style;

    if (username.trim().length === 0) {
      notOK.style.display = 'block';

      // console.log(notOK.firstChild.data);

      OKimg.display = 'none';
      notOKimg.display = 'block';
      notOKimg.left = '68%';
      notOKimg.top = '95px';
    } else {
      notOK.style.display = 'none';
      notOKimg.display = 'none';
      OKimg.display = 'block';
      OKimg.left = '68%';
      OKimg.top = '95px';
    }
  }

  function checkPassword(obj) {
    var password = obj;
    var notOK = document.getElementById('pswNotNull');
    var notOKimg = document.getElementById('F1');
    var OKimg = document.getElementById('T1');
    if (password.trim().length === 0) {
      notOK.style.display = 'block';

      OKimg.style.display = 'none';
      notOKimg.style.display = 'block';
      notOKimg.style.left = '68%';
      notOKimg.style.top = '160px';
    } else {
      notOK.style.display = 'none';

      notOKimg.style.display = 'none';
      OKimg.style.display = 'block';
      OKimg.style.left = '68%';
      OKimg.style.top = '160px';
    }
  }



  function sendLoginData() {
    'use strict';
    var formData = {};
    // var $formElement = $('#LoginBoard');
    var formElement = document.querySelector('#LoginBoard');
    $(formElement).serializeArray().forEach(function(item) {
      formData[item.name] = item.value;
    });
    var json = JSON.stringify(formData);
    // console.log(formData);

    var url = 'login/go';
    // var json = JSON.stringify({
    //   "userName": "s19961234@126.com",
    //   "password": "123456",
    //   "captcha": "cabce"
    // });
    sendAjax(url, json)
      .done(getResponse);
  }

  function sendAjax(url, json) {
    'use strict';
    return $.ajax({
      type: 'post',
      data: json,
      url: url,
      headers: {
        'Content-type': 'application/json;charset=UTF-8'
      },
      dataType: 'json'
    });
  }

  function getResponse(response) {
    'use strict';
    var notOK = document.getElementById('pswNotNull');
    var notOKimg = document.getElementById('F1');
    if (response.code === 0) {
      // TODO: 链接未定
    } else {
      notOKimg.style.display = 'block';
      notOKimg.style.left = '68%';
      notOKimg.style.top = '160px';
      notOK.style.display = 'block';
      notOK.firstChild.data = response.msg;
    }
  }

  function addHandler(id, action, func) {
    'use strict';
    var domID = document.querySelector(`#${id}`);
    domID.addEventListener(action, function(event) {
      event.preventDefault();
      func(domID.value);
    });
  }

  function init() {
    'use strict';
    addHandler('logNum', 'blur', checkUserName);
    addHandler('psw', 'blur', checkPassword);
    addHandler('x', 'click', hiddenLog);
    addHandler('showLogin', 'click', showLog);
    addHandler('Loga', 'click', sendLoginData);
  }




  hiddenLog();
  init();
}(window));