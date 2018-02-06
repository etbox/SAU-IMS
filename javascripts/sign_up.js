(function() {
  'use script';

  function checkEmail() {
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

    } else if (target.value !== '') {
      F.display = 'none';
      T.display = 'block';
      T.left = '750px';
      T.top = '152px';
      word.display = 'none';
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

    } else if (target.value !== '') {
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
    var word = document.getElementById('notSame').style;
    if (target.value != target0.value) {
      T.display = 'none';
      F.display = 'block';
      F.left = '750px';
      F.top = '325px';
      word.display = 'block';

    } else if (target.value === target0.value) {
      F.display = 'none';
      T.display = 'block';
      T.left = '750px';
      T.top = '325px';
      word.display = 'none';
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
  }
  init();

}());
