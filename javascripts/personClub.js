
(function(){
    'use strict';
      function show(x){
       var x= document.getElementById("movebar"+x).style;
       x.animation="myfirst 1s";
       x.width="330px";
       x.opacity="0.7";


      }
  function backshow(x){    
        var x= document.getElementById("movebar"+x).style;
        x.animation="mythird 1s";
        x.width="0px";
        x.opacity="1";
      }
  function stopshow(x){
        var x= document.getElementById("movebar"+x).style;
        x.animation="mysecond 1s";
        x.width="330px";
        x.opacity="1";
        x.backgroundColor="#8fc9fb";
      }


  function addHandler(id, action, func,x) {
  var domID = document.querySelector(`#${id}`);
  domID.addEventListener(action, function(event) {
    event.preventDefault();
    func(x);
  });
}

 

      function init(){
        addHandler('m','mouseover',show,'0'); 
        addHandler('m','click',stopshow,'0'); 
        addHandler('m','mouseout',backshow,'0'); 
        addHandler('m1','mouseover',show,'1'); 
        addHandler('m1','click',stopshow,'1'); 
        addHandler('m1','mouseout',backshow,'1'); 
        addHandler('m2','mouseover',show,'2'); 
        addHandler('m2','click',stopshow,'2'); 
        addHandler('m2','mouseout',backshow,'2'); 
        addHandler('m3','mouseover',show,'3'); 
        addHandler('m3','click',stopshow,'3'); 
        addHandler('m3','mouseout',backshow,'3'); 
      }

      init();
  
   


     
        //绘图

        //---------------------------------------------------------------------
       // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('Boypic1'));

        // 指定图表的配置项和数据
        var option = {
            color: ['#37a2fe','#8dcaea','#327aa7'],
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:['男','女']
    },
    series: [
        {
            name:'男女比例',
            type:'pie',
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
            data:[
                {value:335, name:'男'},
                {value:310, name:'女'},
                
            ]
        }
    ]
};


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);   


        //绘图二
        //----------------------------------------------------------------
  
        // 基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('Boypic2'));

        // 指定图表的配置项和数据
        var option1 = {
            color: ['#37a2fe','#8dcaea','#327aa7'],
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:['大一','大二','大三']
    },
    series: [
        {
            name:'年级比例',
            type:'pie',
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
            data:[
                {value:335, name:'大一'},
                {value:310, name:'大二'},
                {value:310, name:'大三'},
                
            ]
        }
    ]
};


        // 使用刚指定的配置项和数据显示图表。
        myChart1.setOption(option1); 

}());


  




