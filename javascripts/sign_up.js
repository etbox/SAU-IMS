(function(window) {
  'use script';
  var $ = window.jQuery;

  function checkEmail() {
    var target = document.getElementById('email');
    var T = document.getElementById('T').style;
    var F = document.getElementById('F').style;
    var word = document.getElementById('mailWrong');
    if (target.value === '') {
      T.display = 'none';
      F.display = 'block';
      F.left = '750px';
      F.top = '152px';
      word.style.display = 'block';
    } else if (!/.+@.\..+$/.test(target.value)) {
      T.display = 'none';
      F.display = 'block';
      F.left = '750px';
      F.top = '152px';
      word.style.display = 'block';
      word.firstChild.data = '请输入正确的邮箱';
    } else {
      F.display = 'none';
      T.display = 'block';
      T.left = '750px';
      T.top = '152px';
      word.style.display = 'none';
    }
  }

  function checkPW() {
    var target = document.getElementById('pw');
    var T = document.getElementById('T1').style;
    var F = document.getElementById('F1').style;
    var word = document.getElementById('PWWrong').style;
    if (target.value === '') {
      T.display = 'none';
      F.display = 'block';
      F.left = '750px';
      F.top = '265px';
      word.display = 'block';
    } else {
      F.display = 'none';
      T.display = 'block';
      T.left = '750px';
      T.top = '265px';
      word.display = 'none';
    }
  }

  function checkSame() {
    var target = document.getElementById('repw');
    var target0 = document.getElementById('pw');
    var T = document.getElementById('T2').style;
    var F = document.getElementById('F2').style;
    var word = document.getElementById('notSame');
    if (target0.value === '') {
      T.display = 'none';
      F.display = 'block';
      F.left = '750px';
      F.top = '325px';

      word.style.display = 'block';
      word.firstChild.data = '请先输入密码';
    } else {
      if (target.value !== target0.value) {
        T.display = 'none';
        F.display = 'block';
        F.left = '750px';
        F.top = '325px';

        word.style.display = 'block';
        word.firstChild.data = '两次输入密码不同';
      } else {
        F.display = 'none';
        T.display = 'block';
        T.left = '750px';
        T.top = '325px';
        word.display = 'none';
      }
    }
  }

  function verifySend() {
    var target = document.getElementById('email');

    var T = document.getElementById('T').style;
    var F = document.getElementById('F').style;
    var word = document.getElementById('mailWrong').style;
    if (target.value === '') {
      T.display = 'none';
      F.display = 'block';
      F.left = '750px';
      F.top = '152px';
      word.display = 'block';
    } else {
      $.ajax({
        type: 'get',
        url: 'reg/code',
        // headers: {'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        dataType: 'json',
        data: {
          'email': target.value
        }
      });
      forbidClick(60);
    }
  }

  function forbidClick(second) {
    var target = document.getElementById('verifySend');

    if (second > 0) {
      target.style.cursor = 'not-allowed';
      target.value = '再次发送(' + second + '秒)';
      window.setTimeout(function() {
        forbidClick(second - 1);
      }, 1000);
    } else {
      target.style.cursor = 'pointer';
      target.value = '发送验证码';
    }
  }

  function normalSignUp() {
    var formData = {};
    var formElement = document.querySelector('#normalSignUp');
    $(formElement).serializeArray().forEach(function(item) {
      formData[item.name] = item.value;
    });
    var json = JSON.stringify(formData);
    var url = 'reg/person';
    sendAjax(url, json)
      .done(getResponse);
  }

  function sendAjax(url, json) {
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
    var F = document.getElementById('F2').style;
    var word = document.getElementById('notSame');
    if (response.code === 0) {
      // TODO: 链接未定
    } else {
      F.display = 'block';
      F.left = '750px';
      F.top = '325px';

      word.style.display = 'block';
      word.firstChild.data = response.msg;
    }
  }

  function addHandler(id, action, func) {
    var domID = document.querySelector(`#${id}`);
    domID.addEventListener(action, function(event) {
      event.preventDefault();
      func(domID.value);
    });
  }

  function init() {
    addHandler('email', 'blur', checkEmail);
    addHandler('pw', 'blur', checkPW);
    addHandler('repw', 'blur', checkSame);
    addHandler('verifySend', 'click', verifySend);
    addHandler('signButton', 'click', normalSignUp);
  }
  init();

}(window));