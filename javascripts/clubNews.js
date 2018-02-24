     (function(){
        'use strict';

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
        var myChart2 = echarts.init(document.getElementById('Boypic2'));

        // 指定图表的配置项和数据
        var option2 = {
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
        myChart2.setOption(option2);   
     }());

    