(function(){
  'use strict';
    function show(x){
        var y=document.getElementById("movebar"+x).style;
        y.animation="myfirst 1s";
        y.width="411px";
        y.opacity="1";

      }
  function backshow(x){    
        var y=document.getElementById("movebar"+x).style;
        y.animation="mythird 1s";
        y.width="18px";
        y.opacity="1";
      }
  function stopshow(x){
         var y=document.getElementById("movebar"+x).style;
         y.animation="mysecond 1s";
         y.style.width="411px";
         y.style.opacity="1";
       
      }

      function edit(){
        document.getElementById("RightHead").style.display="none";
        document.getElementById("rightSide").style.display="none";
        document.getElementById("rightEditTitle").style.display="block";
        document.getElementById("rightSideEdit").style.display="block";
     
       
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
        addHandler('addEdit','click',edit);  //添加按钮这样写方便管理
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

}());


