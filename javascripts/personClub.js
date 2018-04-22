(function() {
  'use strict';
  var $ = window.jQuery;
  var echarts = window.echarts;

  function row(i, id) {
    var $div0 = $('<div></div>', {
      'class': 'm',
      'id': '' + id
    });
    var $img = $('<img></img>', {
      'class': 'MHEAD',
      'src': './images/touxiang.png'
    });
    var $div1 = $('<div></div>', {
      'class': 'WRITER',
      'id': 'WRITER' + i
    });
    var $div2 = $('<div></div>', {
      'class': 'NUM',
      'id': 'NUM' + i
    });

    var $img1 = $('<img></img>', {
      'class': 'LIKE',
      'id': 'LIKE' + id,
      'src': ''
    });

    $div0.append($img);
    $div0.append($div1);
    $div0.append($div2);
    $div0.append($img1);

    return $div0;

  }


  function getNewsData(){       //从服务器获取数据

      return $.ajax({
          url: '/member/club',
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

    var clubId; //没错 这就是真正的数据 // FIXME: 变量未使用
    var clubName;
  //  var members; // FIXME: 变量未使用
    var likeNumber;
  /*  json = { //测试用
      'code': 0,
      'msg': '',
      'data': [
        {
          'orgId': 232,
          'clubName': '乒乓球协会',
          'members': 100,
          'likeClick': 20,
          'isClick': 0
        },
        {
          'orgId': 233,
          'clubName': '羽毛球',
          'members': 100,
          'likeClick': 100,
          'isClick': 1
        },
        {
          'orgId': 236,
          'clubName': '羽毛球',
          'members': 100,
          'likeClick': 100,
          'isClick': 0
        }

      ]
    };*/
    for (var i = 0; i < json.data.length; i++) { //i的长度是json的 data的长度
      clubId = json.data[i].orgId; //没错 这就是真正的数据
      clubName = json.data[i].clubName;
      members = json.data[i].members;
      likeNumber = json.data[i].likeClick;

      /*获取数据后操作dom*/
      $('.middleSide').append(row(i, json.data[i].orgId));
      if (json.data[i].isClick === 1) {
        $('#LIKE' + json.data[i].orgId).attr('src', './images/heartGrey.png');
      }
      if (json.data[i].isClick === 0) {
        $('#LIKE' + json.data[i].orgId).attr('src', './images/heart.png');
      }

      $('#WRITER' + i).text(clubName);
      $('#NUM' + i).text(likeNumber);




      if (json.data[i].isClick === 1) {
        $('#' + 'LIKE' + json.data[i].orgId).click(function() {
          var x = this.parentNode.id;
          $.ajax(
            {
              url: '/member/club/{' + x + '}/star',
              type: 'get',
              dataType: 'json',
              data: {
                'isClick ': 0
              }

            })
            .done(function(JSON1) {
              $('#' + x).children('.LIKE').attr('src', './images/heart.png');
              $('#' + x).children('.NUM').text(JSON1.data.likeClick);

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
  }
  load();

  function addNewsClick(json) {
    var checkID = '';
    var JSONSAVE = {};
    for (var i = 0; i < json.data.length; i++) {
      $('#' + json.data[i].orgId).click(function() {
        checkID = this.id;
        $('.join').show();

        $.ajax({
            url: '/sau/club/{'+this.id+'}',
            type: 'get',
            headers: {'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            dataType: 'json',

        })
        .done(function(JSON1) {
               JSONSAVE=JSON1;
               news(JSON1.data[0].orgName,'',JSON1.data[0].foundTime,JSON1.data[0].adminName,JSON1.data[0].email,JSON1.data[0].phone,JSON1.data[0].description);
        })
        .fail(function() {
            console.log('error');
        })
        .always(function() {
            console.log('complete');
        });

        $('#join').click(function() {
          $.ajax(
            {
              url: '/member/club/{' + checkID + '}/join',
              type: 'POST',
              dataType: 'json',
              data: JSONSAVE,
            })
            .done(function(Json) {
              if (Json.code === 0) {
                alert('提交成功');
              }

            })
            .fail(function(Json) {
              if (Json.code === 1) {
                alert(Json.msg);
              }
            })
            .always(function() {
              console.log('complete');
            });

        });
      });
    }
  }
  addNewsClick(json);

  function news(a, b, c, d, e, f, g) { // FIXME: 变量未使用
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

  function search() {
    var searchData=getSearchData();
    /*var searchData = { //测试用
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
    var members; // FIXME: 变量未使用
    var likeNumber;
    if (searchData.code === 0) {
      for (var i = 0; i < searchData.data.length; i++) { //i的长度是json的 data的长度
        clubId = searchData.data[i].orgId; //没错 这就是真正的数据
        clubName = searchData.data[i].clubName;
        members = searchData.data[i].members;
        likeNumber = searchData.data[i].likeClick;

        /*获取数据后操作dom*/
        $('.middleSide').append(row(i, searchData.data[i].orgId));
        if (searchData.data[i].avaliable === 1) {
          $('#LIKE' + searchData.data[i].orgId).attr('src', './images/heartGrey.png');
        }
        if (searchData.data[i].avaliable === 0) {
          $('#LIKE' + searchData.data[i].orgId).attr('src', './images/heart.png');
        }

        $('#WRITER' + i).text(clubName);
        $('#NUM' + i).text(likeNumber);
      }
    }

    addNewsClick(searchData);

  }





  function getSearchData() { // FIXME: 变量未使用
    $.ajax(
      {
        url: '/member/club/search',
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

  function addHandler(id, action, func, x) {
    var domID = document.querySelector(`#${id}`);
    domID.addEventListener(action, function(event) {
      event.preventDefault();
      func(x);
    });
  }



  function init() {
    addHandler('refresh', 'click', refresh);
    addHandler('search', 'click', search);

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
