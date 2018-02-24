  
(function(){
  'use strict';



function show(x){
         var z=document.getElementById("movebar"+x).style;
         z.animation="myfirst 1s";
         z.width="312px";
         z.style.opacity="0.5";

      }
  function backshow(x){ 
       var z=document.getElementById("movebar"+x).style;   
       z.animation="mythird 1s";
       z.width="10px";
       z.opacity="1";
      }
  function stopshow(x){
        var z=document.getElementById("movebar"+x).style;   
        z.animation="mysecond 1s";
        z.width="313px";
        z.opacity="1";
        z.backgroundColor="#8fc9fb";
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


