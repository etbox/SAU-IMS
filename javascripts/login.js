function showLog() { /*把登陆界面显示出来*/

  var target = document.getElementById('back');
  var target1 = document.getElementById('LoginBoard');
  var target2 = document.getElementById('x');
  target.style.display = 'block';
  target1.style.display = 'block';
  target2.style.display = 'block';
}

function hiddenLog() { /*隐藏界面*/
  var target = document.getElementById('back');
  var target1 = document.getElementById('LoginBoard');
  var target2 = document.getElementById('x');
  target.style.display = 'none';
  target1.style.display = 'none';
  target2.style.display = 'none';
}

function checkUserName(obj) {
  var username = obj;
  var checkUserNameResult = document.getElementById('logNum');
  var pw = document.getElementById('psw');
  var notok = document.getElementById('nameNotNull');
  var ok = document.getElementById('nameNotNull');
  var notokimg = document.getElementById('F');
  var okimg = document.getElementById('T');

  if (username.trim().length == 0) {
    notok.style.display = 'block';
    notokimg.style.display = 'block';
    notokimg.style.left = '68%';
    notokimg.style.top = '95px';
    // obj.focus();
  } else {
    notok.style.display = 'none';
    notokimg.style.display = 'none';
    okimg.style.display = 'block';
    okimg.style.left = '68%';
    okimg.style.top = '95px';

  }
}

function checkPassword(obj) {
  var password = obj;
  var checkUserNameResult = document.getElementById('psw');
  var notok = document.getElementById('pswNotNull');
  var ok = document.getElementById('pswNotNull');
  var Namenotok = document.getElementById('nameNotNull');
  var notokimg = document.getElementById('F1');
  var okimg = document.getElementById('T1');
  if (password.trim().length == 0) {
    notok.style.display = 'block';
    notokimg.style.display = 'block';
    notokimg.style.left = '68%';
    notokimg.style.top = '156px';
    // obj.focus();
  } else {
    notok.style.display = 'none';
    notokimg.style.display = 'none';
    okimg.style.display = 'block';
    okimg.style.left = '68%';
    okimg.style.top = '157px';
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
  addHandler('logNum', 'blur', checkUserName);
  addHandler('psw', 'blur', checkPassword);
  addHandler('x', 'click', hiddenLog);
  addHandler('showLogin', 'click', showLog);
}

hiddenLog();
init();
