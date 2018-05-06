(function() {
  'use strict';
  var $ = window.jQuery;
  /**
   * 注册消息id，用于提交注册消息的全局变量
   */
  var checkID;

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


  var json = {};

  function getNewsData() { //从服务器获取数据
    $.ajax({
        url: '/sauims/json/club/ann' + '/allReg.json',
        type: 'get',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
      })
      .done(function(Json) {
        if (Json.code != 0) {
          alert(Json.msg);
        } else {
          console.log('success'); //操作
          load(Json);
          addNewsClick(Json);
          loadFirstAnnReg(Json);
        }
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });

  }
  getNewsData();

  function load(json) { //加载
    var auditMsgId; //没错 这就是真正的数据 // FIXME: 变量未使用
    var auditTitle;
    var submitTime;
    var auditState;
    var registerName;
    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      auditMsgId = json.data[i].auditMsgId; //没错 这就是真正的数据
      auditTitle = json.data[i].registerTitle;

      //将时间规范化
      Date.prototype.toLocaleString = function() {
        return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate() + " " ;
      };
      submitTime = new Date(json.data[i].registerTime).toLocaleString();
      registerName = json.data[i].registerName;
      auditState = json.data[i].auditState;

      /*获取数据后操作dom*/
      $('#middleSide').append(row(i, json.data[i].auditMsgId));
      $('#MTITLE' + i).text(auditTitle);
      $('#WRITER' + i).text('by '+registerName);
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

  /**
   * 加载第一个的年度注册消息
   * @param  {json数组} json [全部历史年度注册消息]
   */
  function loadFirstAnnReg(json) {
        $.ajax({
            url: '/sauims/json/club/ann/' + json.data[0].auditMsgId + '.json',
            type: 'get',
            headers: {
              'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            dataType: 'json',

          })
          .done(function(JSON1) {
            if (JSON1.code != 0) {
              alert(JSON1.msg);
            } else {
              checkID = JSON1.data.auditMsgId;
              news(JSON1.data.clubName, '', JSON1.data.submitTime, JSON1.data.adminName, JSON1.data.description, JSON1.data.auditResult, JSON1.data.auditState);
              var fileURL = '/sauims/json/club/ann/' + checkID + '/file' + '/test.docx';
              document.getElementById('fujian').href=fileURL;
            }
          })
          .fail(function() {
            console.log('error');
          })
          .always(function() {
            console.log('complete');
          });
    }


  function addNewsClick(json) {
    for (var i = 0; i < json.data.length; i++) {
      $('#' + json.data[i].auditMsgId).click(function() {
        $.ajax({
            url: '/sauims/json/club/ann/' + this.id + '.json',
            type: 'get',
            headers: {
              'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            dataType: 'json',

          })
          .done(function(JSON1) {
            if (JSON1.code != 0) {
              alert(JSON1.msg);
            } else {
              checkID = JSON1.data.auditMsgId;
              news(JSON1.data.clubName, '', JSON1.data.submitTime, JSON1.data.adminName, JSON1.data.description, JSON1.data.auditResult, JSON1.data.auditState);
              var fileURL = '/sauims/json/club/ann/' + checkID + '/file' + '/test.docx';
              document.getElementById('fujian').href=fileURL;
            }
          })
          .fail(function() {
            console.log('error');
          })
          .always(function() {
            console.log('complete');
          });
      });
    }
  }

  function news(a, b, c, d, e, f, g) {
    $('#rightHeadTitle').text(a);
    //将时间规范化
    Date.prototype.toLocaleString = function() {
      return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 " + this.getHours() + ":" + this.getMinutes();
    };
    var submitTime = new Date(c).toLocaleString();
    $('#rightHeadTime').text(submitTime);
    $('#bossName').text(d);
    $('.pass').text(f);
    $('#zhuceneirong').text(e);
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

  function getSearchData() {
    $('#middleSide').children('div').remove();
    $.ajax({
        url: '/sauims/json/club/ann/search' + '/' + $('.searchBar').val() + '.json',
        type: 'get',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
        data: null
      })
      .done(function(searchData) {
        if (searchData.code != 0) {
          alert(searchData.msg);
        } else {
          search(searchData);
        }
      })
      .fail(function() {
        console.log('error');
      })
      .always(function() {
        console.log('complete');
      });
  }

  function search(json) {
    var auditMsgId; //没错 这就是真正的数据 // FIXME: 变量未使用
    var auditTitle;
    var submitTime;
    var auditState;
    var registerName;
    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      auditMsgId = json.data[i].auditMsgId; //没错 这就是真正的数据
      auditTitle = json.data[i].registerTitle;

      //将时间规范化
      Date.prototype.toLocaleString = function() {
        return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate() + " " ;
      };
      submitTime = new Date(json.data[i].registerTime).toLocaleString();
      registerName = json.data[i].registerName;
      auditState = json.data[i].auditState;

      /*获取数据后操作dom*/
      $('#middleSide').append(row(i, json.data[i].auditMsgId));
      $('#MTITLE' + i).text(auditTitle);
      $('#WRITER' + i).text('by '+registerName);
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
    addNewsClick(json);
  }

  function refresh() { //刷新按钮
    $('#middleSide').children('div').remove();
    getNewsData();

  }

  function sendCheck() {
    $.ajax({
        url: '/sauims/json/club/ann/one' + '/success.json',
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
    addHandler('search', 'click', getSearchData);
  }
  init();

}());