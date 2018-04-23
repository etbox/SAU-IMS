(function() {
  'use strict';
  var $ = window.jQuery;
 var json = {}; //全局
  function row(i, id) {
    var $div0 = $('<div></div>', {
      'class': 'm',
      'id': '' + id
    });
    var $div1 = $('<div></div>', {
      'class': 'movebar'

    });
    var $div2 = $('<div></div>', {
      'class': 'MTITLE',
      'id': 'MTITLE' + i
    });
    var $div3 = $('<div></div>', {
      'class': 'WRITER',
      'id': 'WRITER' + i
    });
    var $div4 = $('<div></div>', {
      'class': 'MTIME',
      'id': 'MTIME' + i
    });
    var $input = $('<input></input>', {
      'class': 'MCHECK',
      'type': 'checkbox',
      'id': 'MCHECK' + i
    });

    $div0.append($div1);
    $div0.append($div2);
    $div0.append($div3);
    $div0.append($div4);
    $div0.append($input);

    return $div0; 

  }
    function getNewsData() { //从服务器获取数据

    $.ajax(
      {
        url: '/club/audit/join',
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
 

    var auditMsgId; //没错 这就是真正的数据
    var auditTitle;
    var registerTime;
    var auditState; // FIXME: 变量未使用
    /*json = { //测试用
      'code': 0,
      'msg': '',
      'data': [
        {
          'auditMsgId': 234,
          'auditTitleregisterTime': '张三',
          'registerTime': 5343388883333,
          'auditState': 1

        },
        {
          'auditMsgId': 235,
          'auditTitle': '李四',
          'registerTime': 5343388883333,
          'auditState': 1

        }

      ]
    };*/
    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      auditMsgId = json.data[i].auditMsgId; //没错 这就是真正的数据
      auditTitle = json.data[i].auditTitle;
      var unixTimestamp = new Date(json.data[i].registerTime);
      registerTime = unixTimestamp.toLocaleString();

      auditState = json.data[i].auditState;


      /*获取数据后操作dom*/
      $('#middleSide').append(row(i, json.data[i].auditMsgId));
      $('#MTITLE' + i).text(auditMsgId);
      $('#WRITER' + i).text(auditTitle);
      $('#MTIME' + i).text(registerTime);


    }
  }

  var checkID;

  function addNewsClick(json) {
    for (var i = 0; i < json.data.length; i++) {
      $('#' + json.data[i].auditMsgId).click(function() {
        $.ajax(
          {
            url: '/club/audit/join/{' + this.id + '}',
            type: 'get',
            headers: {
              'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            dataType: 'json',

          })
          .done(function(json) {
            checkID = json.data[i].auditMsgId;
            news(json.data[0].auditId, '', json.data[0].registerTime, json.data[0].realName, json.data[0].email, json.data[0].phone, json.data[0].description);


          })
          .fail(function() {
            // json
            console.log('error');
          })
          .always(function() {
            console.log('complete');
          });
        /*  var JSON1={    'data':[
                       {'auditId':234,
                       'userName':'s19961234@126.com',
            'registerTime': 5343442321213,
          'personLogo': 'a.jpg',
          'realName': '张三',
          'studentId': '151611222',
          'gender': '男',
          'birthday': 5343442321213,
          'phone': '18888888888',
          'email': 's19961234@126.com',
          'departmentName': '金融学院与统计学院',
          'majorName': '信息与计算科学',
          'address': '8#110'

}]}



           news(JSON1.data[0].auditId,'',JSON1.data[0].registerTime,JSON1.data[0].realName,JSON1.data[0].email,JSON1.data[0].phone,JSON1.data[0].description);

          */
      });
    }
  }
  addNewsClick(json);

  function news(a, b, c, d, e, f, g) {
    $('#rightHeadTitle').text(a);
    $('#clubName1').text(a);
    $('#clubType1').text(a);
    var unixTimestamp = new Date(c);
    var submitTime = unixTimestamp.toLocaleString();
    $('#rightHeadTime').text(submitTime);

    $('#bossName').text(d);
    $('#mailboxName').text(e);
    $('#telNum').text(f);
    $('#Introduce').text(g);

  }



  function refresh() { //刷新按钮
    $('#middleSide').children('div').remove();
    load();

  }


  function agree() {
    $.ajax(
      {
        url: '/club/audit/join/{' + checkID + '}',
        type: 'post',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
        data: {
          'auditState': 1,
          '_method': 'put'
        },
      })
      .done(function() {
        console.log('success');
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });


  }

  function disagree() {

    $.ajax(
      {
        url: '/club/audit/join/{' + checkID + '}',
        type: 'post',
        dataType: 'json',
        data: {
          'auditState': 0,
          'auditResult': $('#neirong').val(),
          '_method': 'put'
        },
      })
      .done(function() {
        console.log('success');
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });

  }
var searchData1={};
  
  function getSearchData() {
    $.ajax(
      {
        url: '/club/audit/join/search',
        type: 'get',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
        data: {
          'findContent': '' + $('.search-bar').val()
        },
      })
      .done(function(searchData) {
        earchData1=searchData;
        search();
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });


  }
   

  function search() {
    
  

    $('.middleSide').children('div').remove();
    var auditMsgId; //没错 这就是真正的数据
    var auditTitle;
    var registerTime;
   // var auditState; // FIXME: 变量未使用
    if (searchData.code === 0) {
      for (var i = 0; i < searchData.data.length; i++) { //i的长度是json的 data的长度
        auditMsgId = searchData.data[i].auditMsgId; //没错 这就是真正的数据
        auditTitle = searchData.data[i].auditTitle;
        var unixTimestamp = new Date(searchData.data[i].registerTime);
        registerTime = unixTimestamp.toLocaleString();

        auditState = searchData.data[i].auditState;


        /*获取数据后操作dom*/
        $('#middleSide').append(row(i, searchData.data[i].auditMsgId));
        $('#MTITLE' + i).text(auditMsgId);
        $('#WRITER' + i).text(auditTitle);
        $('#MTIME' + i).text(registerTime);
      }
    }

    addNewsClick(searchData1);

  }


  function addHandler(id, action, func, x) {
    var domID = document.querySelector(`#${id}`);
    domID.addEventListener(action, function(event) {
      event.preventDefault();
      func(x);
    });
  }


  function init() {
    addHandler('refresh', 'click', refresh);
    addHandler('agree', 'click', agree);
    addHandler('disagree', 'click', disagree);
    addHandler('search', 'click', getSearchData);
  }
  init();

}());
