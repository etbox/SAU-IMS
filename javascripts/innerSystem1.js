(function() {
  'use strict';
  var $ = window.jQuery;

  var Row = function(i, id) { //行构造函数
    var $li = $('<li></li>', {
      'class': 'message-item pointer',
      'id': '' + id
    });
    var $div0 = $('<div></div>', {
      'class': 'cell-unread'
    });

    var $div1 = $('<div></div>', {
      'class': 'message-item-unread unread',
      'data-message-item': 'unread'
    });
    $div0.append($div1);

    var $div2 = $('<div></div>', {
      'class': 'cell-message-info'
    });
    var $div3 = $('<div></div>', {
      'class': 'message-item-title'
    });
    var $span = $('<span></span>', {
      'data-message-item': 'title',
      'class': 'messageTitle' + i
    });
    $div3.append($span);
    var $div4 = $('<div></div>', {
      'class': 'message-item-option'
    });
    var $input = $('<input></input>', {
      'type': 'checkbox',
      'data-message-item': 'option'
    });
    $div4.append($input);
    var $div5 = $('<div></div>', {
      'class': 'message-item-sender'
    });
    var $span1 = $('<span></span>', {
      'ata-message-item': 'sender',
      'class': 'messageSender' + i
    });
    $div5.append($span1);
    var $div6 = $('<div></div>', {
      'class': 'message-item-time'
    });
    var $span2 = $('<span></span>', {
      'data-message-item': 'time',
      'class': 'messageTime' + i
    });
    $div6.append($span2);

    $div2.append($div3);
    $div2.append($div4);
    $div2.append($div5);
    $div2.append($div6);
    $li.append($div0);
    $li.append($div2);

    return $li;

  };

   var json = {}; //全局
function getNewsData() { //从服务器获取数据

    $.ajax(
      {
        url: '/sauims/json/msg/allMsg.json',
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





  var load = function() { //加载

    var messageId; //没错 这就是真正的数据 // FIXME: 变量未使用
    var messageTitle;
    var releaseTime; // FIXME: 变量未使用
    var releaseName; // FIXME: 变量未使用
    var readFlag; // FIXME: 变量未使用
	
    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      messageId = json.data[i].messageId; //没错 这就是真正的数据
      messageTitle = json.data[i].messageTitle;
      releaseTime = json.data[i].releaseTime;
      releaseName = json.data[i].releaseName;
      readFlag = json.data[i].readFlag;

      /*获取数据后操作dom*/
      $('#News').append(Row(i, json.data[i].messageId));
      $('.messageTitle' + i).text(messageTitle);
      $('.senderName' + i).text(releaseName); // FIXME: releaseName为定义！！！
      $('.sendTime' + i).text(releaseTime); // FIXME: releaseTime为定义！！！

    }
  };


}());
