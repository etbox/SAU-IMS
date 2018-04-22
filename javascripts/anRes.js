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
    var $div5 = $('<div></div>', {
      'class': 'MPASS',
      'id': 'MPASS' + i

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
    $div0.append($div5);
    $div0.append($input);

    return $div0;

  }




  function getNewsData(){       //从服务器获取数据

     $.ajax({
          url: '/club/ann',
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

    var auditMsgId; //没错 这就是真正的数据 // FIXME: 变量未使用
    var auditTitle;
    var submitTime;
    var auditState;
    var registerName;
   /* json = { //测试用
      'code': 0,
      'msg': '',
      'data': [
        {
          'auditMsgId': 1,
          'auditTitle': '2017年',
          'registerTime': 497967667567,
          'auditState': 2
        },
        {
          'auditMsgId': 2,
          'auditTitle': '2016年',

          'registerTime': 497967667567,
          'auditState': 0
        }


      ]
    };*/
    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      auditMsgId = json.data[i].auditMsgId; //没错 这就是真正的数据
      auditTitle = json.data[i].auditTitle;
      var unixTimestamp = new Date(json.data[i].registerTime);
      submitTime = unixTimestamp.toLocaleString();

      auditState = json.data[i].auditState;


      /*获取数据后操作dom*/
      $('#middleSide').append(row(i, json.data[i].auditMsgId));
      $('#MTITLE' + i).text(auditTitle);
      $('#WRITER' + i).text(registerName);
      $('#MTIME' + i).text(submitTime);
      if (auditState === 0) {
        $('#MPASS' + i).text('(未通过)');
      }
      if (auditState === 1) {
        $('#MPASS' + i).text('(通过)');
      }
      if (auditState === 2) {
        $('#MPASS' + i).text('(待审)');
      }

    }
  }
  load();

  // var checkID;

  function addNewsClick(json) {
    for (var i = 0; i < json.data.length; i++) {
      $('#' + json.data[i].auditMsgId).click(function() {
        $.ajax({
            url: '/club/ann/{'+this.id+'}',
            type: 'get',
            headers: {'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            dataType: 'json',

        })
        .done(function(JSON1) {
              checkID=json.data[i].auditMsgId;
               news(JSON1.data[0].auditMsgId,'',JSON1.data[0].submitTime,JSON1.data[0].auditMsgId,JSON1.data[0].description,JSON1.data[0].auditResult,JSON1.data[0].auditState);

        })
        .fail(function() {
            console.log('error');
        })
        .always(function() {
            console.log('complete');
        });*/
       /* var JSON1 = {
          'data': [{
            'auditMsgId': 3232,
            'submitTime': 53432765656,
            'description': '这是本社团某年度注册的信息的描述',
            'fileName': 'a.doc',
            'auditState': 0,
            'auditResult': '否决的原因'
          }, {
            'auditMsgId': 33645532,
            'submitTime': 53432765656,
            'description': '这是本社团某年度注册的信息的描述',
            'fileName': 'a.doc',
            'auditState': 1,
            'auditResult': '否决的原因'
          }]
        };

        news(JSON1.data[0].auditMsgId, '', JSON1.data[0].submitTime, JSON1.data[0].auditMsgId, JSON1.data[0].description, JSON1.data[0].auditResult, JSON1.data[0].auditState);
      });*/
    }
  }
  addNewsClick(json);

  function news(a, b, c, d, e, f, g) {
    $('#rightHeadTitle').text(a);
    var unixTimestamp = new Date(c);
    var submitTime = unixTimestamp.toLocaleString();
    $('#rightHeadTime').text(submitTime);
    $('#bossName').text(d);
    $('#zhuceneirong').text(e);
    $('.pass').text('否决的原因');
    if (g === 0) {
      $('.passTitle').text('审核未通过');
    }
    if (g === 1) {
      $('.passTitle').text('审核通过');
    }
    if (g === 2) {
      $('.passTitle').text('待审');
    }
  }


  function search() {
    var searchData=getSearchData();

    $('#middleSide').children('div').remove();
    var auditMsgId; //没错 这就是真正的数据 // FIXME: 变量未使用
    var auditTitle;
    var submitTime;
    var auditState;
    if (searchData.code === 0) {
      for (var i = 0; i < searchData.data.length; i++) { //i的长度是json的 data的长度
        auditMsgId = searchData.data[i].auditMsgId; //没错 这就是真正的数据
        auditTitle = searchData.data[i].auditTitle;
        var unixTimestamp = new Date(searchData.data[i].registerTime);
        submitTime = unixTimestamp.toLocaleString();

        auditState = searchData.data[i].auditState;


        /*获取数据后操作dom*/
        $('#middleSide').append(row(i, searchData.data[i].auditMsgId));
        $('#MTITLE' + i).text(auditTitle);
        // $('#WRITER' + i).text(registerName);
        $('#MTIME' + i).text(submitTime);
        if (auditState === 0) {
          $('#MPASS' + i).text('(未通过)');
        }
        if (auditState === 1) {
          $('#MPASS' + i).text('(通过)');
        }
        if (auditState === 2) {
          $('#MPASS' + i).text('(待审)');
        }
      }
    }

    addNewsClick(searchData);

  }

  function getSearchData() {
    $.ajax(
      {
        url: '/club/ann/search',
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

  function refresh() { //刷新按钮
    $('#middleSide').children('div').remove();
    load();

  }



  function sendCheck() {
    $.ajax(
      {
        url: '/club/ann/one',
        type: 'POST',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
        data: {
          'description': '' + $('#shoujianren').val(),
          'fileName': '' + $('#neirong').val()
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

    document.getElementById('RightHead').style.display = 'block';
    document.getElementById('rightSide').style.display = 'block';
    document.getElementById('rightEditTitle').style.display = 'none';
    document.getElementById('rightSideEdit').style.display = 'none';
  }

  function edit() {
    document.getElementById('RightHead').style.display = 'none';
    document.getElementById('rightSide').style.display = 'none';
    document.getElementById('rightEditTitle').style.display = 'block';
    document.getElementById('rightSideEdit').style.display = 'block';
  }

  function addHandler(id, action, func) {
    var domID = document.querySelector(`#${id}`);
    domID.addEventListener(action, function(event) {
      event.preventDefault();
      func(domID.value);

    });
  }

  function init() {
    addHandler('refresh', 'click', refresh);
    addHandler('search', 'click', search);
    addHandler('add', 'click', edit);
    addHandler('sendPic', 'click', sendCheck);
  }
  init();

}());
