(function() {
  'use strict';
  var $ = window.jQuery;
  var echarts = window.echarts;
  var json = {};
  function row(i, id) {
    var $div0 = $('<div></div>', {
      'class': 'm',
      'id': '' + id
    });
    var $img = $('<img></img>', {
      'class': 'MHEAD',
      'src': '/sauims/resource/logo/'
    });
    var $div1 = $('<div></div>', {
      'class': 'WRITER',
      'id': 'WRITER' + i
    });
    var $div2 = $('<div></div>', {
      'class': 'NUM',
      'id': 'NUM' + i
    });
    var $a = $('<a></a>', {
      'href': ''
    });
    var $img1 = $('<img></img>', {
      'class': 'LIKE',
      'src': './images/heart.png'
    });
    $a.append($img1);
    $div0.append($img);
    $div0.append($div1);
    $div0.append($div2);
    $div0.append($a);

    return $div0;

  }

  function getNewsData(){       //从服务器获取数据

      return $.ajax({
          url: '/sauims/json/sau/club/allClub.json',
          type: 'get',
          headers: {'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'},
          dataType: 'json',
      })
      .done(function(Json) {
          console.log('success');//操作
		  console.log(Json);
          if (Json.code != 0) {
          alert(data.msg); // FIXME: data为定义！！！
        }
        json=Json;
        load();
		addNewsClick(json);
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
   

    var clubId; //没错 这就是真正的数据 // FIXME: 变量未使用
    var clubName;
    var members; // FIXME: 变量未使用
    var likeNumber;

    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      clubId = json.data[i].orgId; //没错 这就是真正的数据
      clubName = json.data[i].orgName;
      members = json.data[i].members;
      likeNumber = json.data[i].likeClick;

      /*获取数据后操作dom*/
      $('.middleSide').append(row(i, json.data[i].orgId));
	  $('.MHEAD').attr("src","http://localhost:8080/resource/logo/"+json.data[i].logo);
      $('#WRITER' + i).text(clubName);
      $('#NUM' + i).text(likeNumber);
	  
    }
  }


  function addNewsClick(json) {
    for (var i = 0; i < json.data.length; i++) {
      $('#' + json.data[i].clubId).click(function() {
        $.ajax({
            url: '/sau/club/{'+this.id+'}',
            type: 'get',
            headers: {'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            dataType: 'json',

        })
        .done(function(JSON1) {
                news(JSON1.data[0].clubName,'',JSON1.data[0].foundTime,JSON1.data[0].adminName,JSON1.data[0].email,JSON1.data[0].phone,JSON1.data[0].description);
        })
        .fail(function() {
            console.log('error');
        })
        .always(function() {
            console.log('complete');
        });
    /*    var JSON1 = {
          'data': [{
            'clubId': 234,
            'clubName': '乒乓球协会',
            'clubLogo': 'a.jpg',
            'description': '一群爱好乒乓球的人，社团内有不定时举办各种活动',
            'adminName': '李四',
            'email': 's19961234@126.com',
            'phone': '18316821383',
            'foundTime': '2010-10-10',
            'members': 100
          }]
        };



        news(JSON1.data[0].clubName, '', JSON1.data[0].foundTime, JSON1.data[0].adminName, JSON1.data[0].email, JSON1.data[0].phone, JSON1.data[0].description);
*/

      });
    }
  }
  

  function news(a, b, c, d, e, f, g) {
    $('.rightHeadTitle').text(a);
    $('.rightHeadIntroduce').text(b);
    $('.rightHeadTime').text(c);

    $('#bossName').text(d);
    $('#mailboxName').text(e);
    $('#telNum').text(f);
    $('#Introduce').text(g);

  }






  function refresh() { //刷新按钮
    $('.middleSide').children('div').remove();
    load();

  }
 var searchData1={};
  function getSearchData() { // FIXME: 变量未使用
    $.ajax(
      {
        url: '/sau/club/search',
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
    
  /*  var searchData = { //测试用
      'code': 0,
      'msg': '',
      'data': [{
        'clubId': 234,
        'clubName': '乒乓球协会',
        'clubLogo': 'a.jpg',
        'description': '一群爱好乒乓球的人，社团内有不定时举办各种活动',
        'adminName': '李四',
        'email': 's19961234@126.com',
        'phone': '18316821383',
        'foundTime': '2010-10-10',
        'members': 100
      }]
    };*/

    $('.middleSide').children('div').remove();
    var clubId; //没错 这就是真正的数据 // FIXME: 变量未使用
    var clubName;
    //var members; // FIXME: 变量未使用
    var likeNumber;
    if (searchData.code === 0) {
      for (var i = 0; i < searchData.data.length; i++) { //i的长度是json的 data的长度
        clubId = searchData.data[i].clubId; //没错 这就是真正的数据
        clubName = searchData.data[i].clubName;
        members = searchData.data[i].members;
        likeNumber = searchData.data[i].likeNumber;


        /*获取数据后操作dom*/
        $('.middleSide').append(row(i, searchData.data[i].clubId));
        $('#WRITER' + i).text(clubName);
        $('#NUM' + i).text(likeNumber);
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
    addHandler('search', 'click', getSearchData);

  }

  init();





  //绘图

  //---------------------------------------------------------------------
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.getElementById('Boypic1'));

  // 指定图表的配置项和数据
  var option = {
    color: ['#37a2fe', '#8dcaea', '#327aa7'],
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      x: 'left',
      data: ['男', '女']
    },
    series: [{
      name: '男女比例',
      type: 'pie',
      radius: ['50%', '70%'],
      avoidLabelOverlap: false,
      label: {
        normal: {
          show: false,
          position: 'center'
        },
        emphasis: {
          show: true,
          textStyle: {
            fontSize: '30',
            fontWeight: 'bold'
          }
        }
      },
      labelLine: {
        normal: {
          show: false
        }
      },
      data: [
        {
          value: 335,
          name: '男'
        },
        {
          value: 310,
          name: '女'
        },

      ]
    }]
  };


  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);


  //绘图二
  //----------------------------------------------------------------

  // 基于准备好的dom，初始化echarts实例
  var myChart1 = echarts.init(document.getElementById('Boypic2'));

  // 指定图表的配置项和数据
  var option1 = {
    color: ['#37a2fe', '#8dcaea', '#327aa7'],
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      x: 'left',
      data: ['大一', '大二', '大三']
    },
    series: [{
      name: '年级比例',
      type: 'pie',
      radius: ['50%', '70%'],
      avoidLabelOverlap: false,
      label: {
        normal: {
          show: false,
          position: 'center'
        },
        emphasis: {
          show: true,
          textStyle: {
            fontSize: '30',
            fontWeight: 'bold'
          }
        }
      },
      labelLine: {
        normal: {
          show: false
        }
      },
      data: [
        {
          value: 335,
          name: '大一'
        },
        {
          value: 310,
          name: '大二'
        },
        {
          value: 310,
          name: '大三'
        },

      ]
    }]
  };


  // 使用刚指定的配置项和数据显示图表。
  myChart1.setOption(option1);

}());
