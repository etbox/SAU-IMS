(function() {
  'use strict';
  var $ = window.jQuery;
 var json = {}; //全局

  function getNewsData() { //从服务器获取数据 // FIXME: 变量未使用

    $.ajax(
      {
        url: '/member/center/info',
        type: 'get',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
      })
      .done(function(Json) {
        console.log('success'); //操作
         if (Json.code != 0) {
          alert(data.msg); // FIXME: data为定义！！！
        }
        json=Json;
       load();
       
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });

  }
getNewsData();


  function load() { //加载
   


    var userName;
    var realName;
   // var logo; // FIXME: 变量未使用
    var studentId;
    var gender;
    var birthday;
   // var phone; // FIXME: 变量未使用
    var joinTime;
    var leaveTime;
    var departmentName;
    var majorName;
    var address;
   // var clubs = []; // FIXME: 变量未使用
   // var job = []; // FIXME: 变量未使用
  /*  json = { //测试用
      'code': 0,
      'msg': '',
      'data': {
        'nickName': 'abc@qq.com',
        'realName': '李四',
        'logo': '头像.jpg',
        'studentId': '151612220',
        'gender': '男',
        'birthday': 15978767876,
        'phone': '18316821333',
        'enrollmentYear': 15908998787,
        'leaveTime': 15908998787,
        'department': '金融学院与统计学院',
        'major': '信息与计算科学',
        'address': '8#111',
        'clubs': [{
          'clubName': '乒乓球协会',
          'clubDuty': 0,
          'userState': 1
        }, {
          'clubName': '羽毛球协会',
          'clubDuty': 1,
          'userState': 1
        }]


      }
    };*/

    userName = json.data.nickName;
    realName = json.data.realName;
    logo = json.data.logo;
    studentId = json.data.studentId;
    gender = json.data.gender;
    var unixTimestamp = new Date(json.data.birthday);
    birthday = unixTimestamp.toLocaleString();

    // phone=json.data.phone;

    var unixTimestamp = new Date(json.data.enrollmentYear); // FIXME: 变量重复定义
    joinTime = unixTimestamp.toLocaleString();

    //  var unixTimestamp = new Date(json.data.leaveTime) ;
    // leaveTime= unixTimestamp.toLocaleString();


    departmentName = json.data.department;
    majorName = json.data.major;
    address = json.data.address;


    $('.rightHeadTitle').text(userName);
    $('#clubNum').text(userName);
    $('#Name').text(realName);
    $('#schoolNUM').text(studentId);
    $('#Gender').text(gender);
    $('#birthday').text(birthday);
    //$('#Tel').text(phone);

    $('#leaveTime').text(leaveTime);
    $('#joinTime').text(joinTime);
    $('#collageName').text(departmentName);
    $('#majorName').text(majorName);
    $('#domName').text(address);
    $('#TOUXIANG').attr('src', '' + json.data.logo);
    $('#zhezhao').attr('src', '' + json.data.logo);

    $('#clubNum1').val(userName);
    $('#Name1').val(realName);
    $('#schoolNUM1').val(studentId);
    $('#Gender1').val(gender);
    $('#birthday1').val(birthday);
    // $('#Tel1').val(phone);
    $('#joinTime1').val(joinTime);
    $('#leaveTime1').val(leaveTime);
    $('#collageName1').val(departmentName);
    $('#majorName1').val(majorName);
    $('#domName1').val(address);
    /*   var saveClub='';
       var saveJob='';
       for(var i=0;i<json.data.clubs.length;i++){

        clubs[i]=json.data.clubs[i].clubName;
        if(json.data.clubs[i].clubDuty===0){
          job[i]='普通';
        }
        if(json.data.clubs[i].clubDuty===1){
          job[i]='干事';
        }
        if(json.data.clubs[i].clubDuty===2){
          job[i]='部长';
        }
        saveClub=saveClub+'  '+clubs[i];
        saveJob=saveJob+'  '+job[i];
         $('#guishuName').text(saveClub);
         $('#jobName').text(saveJob);
           $('#guishuName1').val(saveClub);
         $('#jobName1').val(saveJob);


                }*/


  }











  function showHead() { /*把换头像界面显示出来*/
    var target = document.getElementById('headChange');
    target.style.display = 'block';

  }

  function hiddenHead() { /*隐藏界面*/
    var target = document.getElementById('headChange');
    target.style.display = 'none';
  }

  function showEdit() {
    var target = document.getElementById('rightEdit');
    target.style.display = 'block';

  }

  function hiddenEdit() { /*隐藏界面*/
    var inputjson = {
      'realName': '' + $('#clubNum1').val(),
      'studentID': '' + $('#schoolNUM1').val(),
      'gender': '' + $('#schoolNUM1').val(),
      'birthday': Date($('#birth1').val()),
      'enrollmentYear': '' + $('#joinTime').text(joinTime), // FIXME: joinTime为定义
      'description': 'hahha',
      //'phone': ''+ $('#Tel1').val(),
      // 'clubDuty':''+ $('#jobName1').val(),
      'department': '' + $('#collageName1').val(),
      'major': '' + $('#majorName1').val(),
      'address': '' + $('#domName1').val(),
      '_method': 'put'

    };
    $.ajax(
      {
        url: '/member/center/info/edit',
        type: 'post',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
        data: inputjson,
      })
      .done(function(editJson) {


        $('#Name').text(editJson.data.nickName);
        $('#schoolNUM').text(editJson.data.studentId);
        $('#Gender').text(editJson.data.gender);
        $('#birthday').text(editJson.data.birthday);
        //  $('#Tel').text(editJson.data.phone);
        $('#collageName').text(editJson.data.department);
        $('#majorName').text(editJson.data.major);
        $('#domName').text(editJson.data.address);
        $('#zhezhao').attr('src', '' + editJson.data.logo);

        $('#Name1').text(editJson.data.realName);
        $('#schoolNUM1').text(editJson.data.studentId);
        $('#Gender1').text(editJson.data.gender);
        $('#birthday1').text(editJson.data.birthday);
        //  $('#Tel1').text(editJson.data.phone);
        $('#collageName1').text(editJson.data.departmente);
        $('#majorName1').text(editJson.data.major);
        $('#domName1').text(editJson.data.address);

      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });



    var target = document.getElementById('rightEdit');
    target.style.display = 'none';

  }

  function changeHead() {
    $.ajax(
      {
        url: '/member/center/info/edit/head',
        type: 'post',
        dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
        data: `<input type='file' name='file' id='file'>`
      })
      .done(function(Json) {
        console.log('success'); //操作
        $('#zhezhao').attr('src', '' + Json.data.logo);
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });



  }

  function hideShuoming() {
    var target = document.getElementById('shuoming');
    target.style.display = 'none';

  }









  function addHandler(id, action, func) {
    var domID = document.querySelector(`#${id}`);
    domID.addEventListener(action, function(event) {
      event.preventDefault();
      func(domID.value);
    });
  }

  function init() {

    addHandler('headEdit', 'click', showHead);
    addHandler('x', 'click', hiddenHead);
    addHandler('titleEdit', 'click', showEdit);
    addHandler('save', 'click', hiddenEdit);
    addHandler('up', 'click', changeHead);
    addHandler('shuoming', 'click', hideShuoming);


  }
  hiddenHead();

  init();
}());
