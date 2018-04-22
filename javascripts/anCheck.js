(function() {
  'use strict';
  var $ = window.jQuery;

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
function getNewsData(){       //从服务器获取数据

     $.ajax({
          url: '/sau/audit/ann',
          type: 'get',
          headers: {'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'},
          dataType: 'json',
      })
      .done(function(Json) {
          console.log('success');//操作
          return Json;
      })
      .fail(function() {
          console.log('error');
      })
      .always(function() {
          console.log('complete');
      });

  }
  var json = {};
  

  function load() { //加载
    json=getNewsData();    //获取服务器数据

    var registerName; //没错 这就是真正的数据
    var registerTitle;
    var registerTime;
    //var auditState; // FIXME: 变量未使用
   /* json = { //测试用
      'code': 0,
      'msg': '',
      'data': [
        {
          'auditMsgId': 1,
          'registerTitle': '乒乓球协会',
          'registerName': '张三',
          'registerTime': '2017',
          'auditState': 2
        }
      ]
    };*/
    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      registerTitle = json.data[i].registerTitle; //没错 这就是真正的数据
      registerName = json.data[i].registerName;
      registerTime = json.data[i].registerTime;
      auditState = json.data[i].auditState;


      /*获取数据后操作dom*/
      $('#middleSide').append(row(i, json.data[i].auditMsgId));
      $('#MTITLE' + i).text(registerTitle);
      $('#WRITER' + i).text(registerName);
      $('#MTIME' + i).text(registerTime);


    }
  }
  load();

  var checkID;

  function addNewsClick(json) {
    for (var i = 0; i < json.data.length; i++) {
      $('#' + json.data[i].auditMsgId).click(function() {
        $.ajax({
            url: '/sau/audit/ann/{'+this.id+'}',
            type: 'get',
            headers: {'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            dataType: 'json',

        })
        .done(function(JSON1) {
              checkID=json.data[i].auditMsgId;
                 news(JSON1.data[0].clubName,'',JSON1.data[0].submitTime,JSON1.data[0].adminName,JSON1.data[0].description);

        })
        .fail(function() {
            console.log('error');
        })
        .always(function() {
            console.log('complete');
        });
     /*   var JSON1 = {
          'data': [{
            'auditMsgId': 3232,
            'clubName': '2017年乒乓球协会',
            'adminName': '张三',
            'submitTime': '2017',
            'description': '这是一个审核的具体内容描述',
            'fileName': 'a.doc'
          }]
        };



        news(JSON1.data[0].clubName, '', JSON1.data[0].submitTime, JSON1.data[0].adminName, JSON1.data[0].description);
*/

      });
    }
  }
  addNewsClick(json);

  function news(a, b, c, d, e) {
    $('#rightHeadTitle').text(a);
    $('#rightHeadTime').text(c);
    $('#bossName').text(d);
    $('#zhuceneirong').text(e);


  }

  

  function refresh() { //刷新按钮
    $('#middleSide').children('div').remove();
    load();

  }


  function agree() {
    $.ajax(
      {
        url: '/sau/audit/ann/{' + checkID + '}',
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
      .done(function(data) {
        if (data.code != 0) {
          alert('data.msg');
        }
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
        url: '/sau/audit/ann/{' + checkID + '}',
        type: 'post',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
        data: {
          'auditState': 0,
          'auditResult': $('#neirong').val(),
          '_method': 'put'
        },
      })
      .done(function(data) {
        if (data.code != 0) {
          alert('data.msg');
        }
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });

  }

  function search() {
    var searchData=getSearchData();

    $('#middleSide').children('div').remove();
    var registerTitle; //没错 这就是真正的数据
    var registerName;
    var registerTime;
   // var auditState; // FIXME: 变量未使用
    if (searchData.code === 0) {
      for (var i = 0; i < searchData.data.length; i++) { //i的长度是json的 data的长度
        //i的长度是json的 data的长度
        registerTitle = searchData.data[i].registerTitle; //没错 这就是真正的数据
        registerName = searchData.data[i].registerName;
        registerTime = searchData.data[i].registerTime;
        auditState = searchData.data[i].auditState;


        /*获取数据后操作dom*/
        $('#middleSide').append(row(i, searchData.data[i].auditMsgId));
        $('#MTITLE' + i).text(registerTitle);
        $('#WRITER' + i).text(registerName);
        $('#MTIME' + i).text(registerTime);
      }
    }

    addNewsClick(searchData);

  }

  function getSearchData() {
    $.ajax(
      {
        url: '/sau/audit/ann/search',
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
        return searchData;
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });


  }

  function file() {
    $.ajax(
      {
        url: '/sau/audit/ann/{' + checkID + '}/file/online',
        type: 'GET',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
      })
      .done(function(x) {
        $('#FUPIC1').write(x);
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });

    $.ajax(
      {
        url: '/sau/audit/ann/{' + checkID + '}/file',
        type: 'GET',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',

      })
      .done(function(x) {
        $('#FUPIC1').write(x);
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });
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
    addHandler('search', 'click', search);
    addHandler('fujian', 'click', file);
  }
  init();

}());
