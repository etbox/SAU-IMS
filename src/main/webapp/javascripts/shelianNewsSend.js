(function(){
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
function getNewsData() { //从服务器获取数据

    $.ajax(
      {
        url: 'sau/msg/old?offset=1&limit=10',
        type: 'get',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
      })
      .done(function(Json) {
        console.log('success'); //操作
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
  // var Json = {};

  function load() { //加载
    json = getNewsData(); //获取服务器数据

    var auditMsgId; //没错 这就是真正的数据
    var auditTitle;
    var registerTime;
    var auditState; // FIXME: 变量未使用
    /*json = { //测试用
      'code': 0,
      'msg': '',
      'data': [
        {
          'messageId': 234,
          'messageTitle': '张三',
          'sendTime': 5343388883333,
          'messageType': 0

        }
      ]
    };*/
    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      auditMsgId = json.data[i].messageId; //没错 这就是真正的数据
      auditTitle = json.data[i].messageTitle;
      var unixTimestamp = new Date(json.data[i].sendTime);
      registerTime = unixTimestamp.toLocaleString();

      auditState = json.data[i].messageType;


      /*获取数据后操作dom*/
      $('#middleSide').append(row(i, json.data[i].auditMsgId));
      $('#MTITLE' + i).text(auditMsgId);
      $('#WRITER' + i).text(auditTitle);
      $('#MTIME' + i).text(registerTime);


    }
  }
 load();

 

  function refresh() { //刷新按钮
    $('#middleSide').children('div').remove();
    load();

  }




var checkID;

  function addNewsClick(json) {
    for (var i = 0; i < json.data.length; i++) {
      $('#' + json.data[i].messageId).click(function() {
        $.ajax(
          {
            url: '/sau/msg/old/' + this.id + '',
            type: 'get',
            headers: {
              'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            dataType: 'json',

          })
          .done(function(json) {
            checkID = json.data[i].messageId;
            news(json.data[0].messageTitle, '', json.data[0].sendTime, json.data[0].messageContent);


          })
          .fail(function() {
            // json
            console.log('error');
          })
          .always(function() {
            console.log('complete');
          });
      
      });
    }
  }
 addNewsClick(json);

  function news(a, b, c, d) {
    $('#rightHeadTitle').text(a);
    $('#bossName').text(a);
    var unixTimestamp = new Date(c);
    var submitTime = unixTimestamp.toLocaleString();
    $('#rightHeadTime').text(submitTime);

    $('#zhuceneirong').text(d);

  }



 
  function getSearchData() {
    $.ajax(
      {
        url: '/sau/msg/old/search?findContent='+$('.search-bar').val()+'&offset=1&limit=-1',
        type: 'get',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        dataType: 'json',
       
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
  function search() {
    searchData = getSearchData();
  

    $('.middleSide').children('div').remove();
    var auditMsgId; //没错 这就是真正的数据
    var auditTitle;
    var registerTime;
   // var auditState; // FIXME: 变量未使用
    if (searchData.code === 0) {
      for (var i = 0; i < searchData.data.length; i++) { //i的长度是json的 data的长度
        auditMsgId = searchData.data[i].messageId; //没错 这就是真正的数据
        auditTitle = searchData.data[i].messageTitle;
        var unixTimestamp = new Date(searchData.data[i].sendTime);
        registerTime = unixTimestamp.toLocaleString();

        auditState = searchData.data[i].messageType;


        /*获取数据后操作dom*/
        $('#middleSide').append(row(i, searchData.data[i].auditMsgId));
        $('#MTITLE' + i).text(auditMsgId);
        $('#WRITER' + i).text(auditTitle);
        $('#MTIME' + i).text(registerTime);
      }
    }

    addNewsClick(searchData);

  }


function sendPerson () {
  $.ajax({
    url: 'sau/clubs?messageType=2',
    type: 'POST',
    dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
    data: {
    "messageTitle": $("#biaoti").val(),
    "messageContent": $("#neirong").val(),
    "sendTime":1523266240332,
    "publishedObject":$("#shoujianren").val()},
  })
  .done(function() {
    alert("发送成功");
    close();
  })
  .fail(function() {
    console.log("error");
  })
  .always(function() {
    console.log("complete");
  });
  
  
}

function sendGroup() {
  $.ajax({
    url: 'sau/msg/new/group',
    type: 'POST',
    dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
    data: {
    "messageTitle": $("#biaoti").val(),
    "messageContent": $("#neirong").val(),
    "sendTime":1523266240332,
    "publishedObject ":""
},
  })
  .done(function() {
    alert("发送成功");
    close();
  })
  .fail(function() {
    console.log("error");
  })
  .always(function() {
    console.log("complete");
  });
  
}

function sendAll () {
  $.ajax({
    url: 'sau/msg/new/all',
    type: 'POST',
    dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
    data: {
    "messageTitle": $("#biaoti").val(),
    "messageContent": $("#neirong").val(),
    "sendTime":1523266240332,
    "publishedObject":""
},
  })
  .done(function() {
   alert("发送成功");
    close();
  })
  .fail(function() {
    console.log("error");
  })
  .always(function() {
    console.log("complete");
  });
  

}


function  contact() {
  $.ajax({
    url: 'sau/clubs?messageType=2',
    type: 'GET',
    dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
   
  })
  .done(function() {
    console.log("success");
  })
  .fail(function() {
    console.log("error");
  })
  .always(function() {
    console.log("complete");
  });
  

}

      function edit(){
        document.getElementById("RightHead").style.display="none";
        document.getElementById("rightSide").style.display="none";
        document.getElementById("rightEditTitle").style.display="block";
        document.getElementById("rightSideEdit").style.display="block";
     
       
      }
       function close(){
        document.getElementById("RightHead").style.display="block";
        document.getElementById("rightSide").style.display="block";
        document.getElementById("rightEditTitle").style.display="none";
        document.getElementById("rightSideEdit").style.display="none";
     
       
      }



      function addHandler(id,action,func){
        var  domID=document.querySelector(`#${id}`);
        domID.addEventListener(action,function(event){
            event.preventDefault();
            func(domID.value);

        });
      }
      function addHandler(id,action,func,x){
        var  domID=document.querySelector(`#${id}`);
        domID.addEventListener(action,function(event){
            event.preventDefault();
            func(x);

        });
      }


   
      function init(){
        addHandler('add','click',edit);  //添加按钮这样写方便管理sendPicture0
        addHandler('sendPic3','click',close);
        addHandler('sendPicture0','click',sendPerson);
        addHandler('sendPicture1','click',sendGroup);
        addHandler('sendPicture2','click',sendAll);
        addHandler('peoplePic','click',contact);
  
      }
init();

}());


