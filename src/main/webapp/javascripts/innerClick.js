(function() {
  'use strict';
  var $ = window.jQuery;

  function addNewsClick(json) {
    for (var i = 0; i < json.data.length; i++) {
      $('#' + json.data[i].messageId).click(function() {
        $.ajax(
          {
            url: '/msg/{' + this.id + '}',
            type: 'get',
            headers: {
              'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            dataType: 'json',
            data: json.data[i],
          })
          .done(function(json1) { //返回来的只是特定id的json 所以下标是0
            news(json1.data[0].senderName, json1.data[0].sendTime, json1.data[0].messageTitle, json1.data[0].messageContent);
          })
          .fail(function() {
            console.log('error');
          })
          .always(function() {
            console.log('complete');
          });
        /*	JSON1={    'data':[
					     {' messageId': 232,
  					  'messageTitle': '乒乓球比赛',
  					  'releaseName': '李四',
    				  'releaseTime': '2017-10-10 12:3:2',
   					  'messageContent': '这是公告的具体内容'},

   					  ]}



			 news(JSON1.data[0].releaseName,JSON1.data[0].releaseTime,JSON1.data[0].messageTitle,JSON1.data[0].messageContent);*/


      });
    }
  }
  addNewsClick(json); // FIXME: json没定义！！！


  function news(a, b, c, d) { //点击新闻显示
    $(`[data-message-detail='
      sender ']`).text(a);
    $(`[data-message-detail='
      time ']`).text(b);
    $(`[data-message-detail='
      title ']`).text(c);
    $('.para-indent').text(d);
  }

  function delMessage() {
    var delJson = {
      '_method': 'delete',
      'deleteMsgIds': ''

    };
    var arry = $('#News input');
    for (var i = 0; i < arry.length; i++) { //获取所需删除的新闻 然后把信息写进json里 发出去给服务器
      if (arry[i].checked) {
        var x = i;
        //var str='{ \'messageId\':'+$('#News').find('li').eq(x).attr('id')+'}';
        delJson.deleteMsgIds += $('#News').find('li').eq(x).attr('id') + ',';
        //delJson.deleteMsgIds.unshift(str);


      }

    }

    $.ajax(
      {
        url: '/msg',
        type: 'post',
        headers: {
          'Content-type': 'application/json;charset=UTF-8'
        },
        dataType: 'json',
        data: delJson,
      })
      .done(function() {
        $('#News').find('li').eq(x).hide(); //成功后删除所选div
      })
      .fail(function() {
        alert('error');
      })
      .always(function() {
        console.log('complete');
      });

  }


  function refresh() { //刷新按钮
    $('#News').children('li').remove();
    load(); // FIXME: load没定义！！！

  }
    var searchData1 ={};
 function getSearchData() {
    $.ajax(
      {
        url: '/msg/search',
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
        searchData1=searchData;
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
    
    /*searchData={           //测试用
                   'code':0,
    				'msg': '',
    				'data':[
    							{
    									'messageId': 232,
       									'messageTitle': '乒乓球比赛',
       									'releaseTime': '2017-09-08 22:23:30',
        								'releaseName': '乒乓球协会',
         								'readFlag': 1
    							},{
    									'messageId': 233,
          								'messageTitle': '羽毛球比赛',
         								'sendTime': '2017-09-08 22:23:30',
        								'senderName': '羽毛球协会',
          								'readFlag': 0
    							   }
    						]	}*/

    $('#News').children('li').remove();
    var messageId; // FIXME: 变量未使用
    var messageTitle;
    var releaseTime; // FIXME: 变量未使用
    var releaseName; // FIXME: 变量未使用
    var readFlag; // FIXME: 变量未使用
    if (searchData.code === 0) {
      for (var i = 0; i < searchData.data.length; i++) {
        messageId = searchData.data[i].messageId;
        messageTitle = searchData.data[i].messageTitle;
        releaseTime = searchData.data[i].releaseTime;
        releaseName = searchData.data[i].releaseName;
        readFlag = searchData.data[i].readFlag;

        /*获取数据后操作dom*/
        $('#News').append(Row(i, searchData.data[i].messageId)); // FIXME: Row为定义！！！
        $('.messageTitle' + i).text(messageTitle);
        $('.messageSender' + i).text(releaseName); // FIXME: releaseName为定义！！！
        $('.messageTime' + i).text(releaseTime); // FIXME: releaseTime为定义！！！
      }
    }

    addNewsClick(searchData1);

  }


 


  function addHandler(id, action, func) { //事件监听器
    var domID = document.querySelector(`#${id}`);
    domID.addEventListener(action, function(event) {
      event.preventDefault();
      func(domID.value);
    });
  }

  function init() {
    addHandler('deleteButton', 'click', delMessage);
    addHandler('refresh', 'click', refresh);
    addHandler('search', 'click', getSearchData);
  }
  init();
}());
