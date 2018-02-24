(function(){
  'use strict';




function show(x){
        document.getElementById("movebar"+x).style.animation="myfirst 1s";
        document.getElementById("movebar"+x).style.width="312px";
        document.getElementById("movebar"+x).style.opacity="0.5";

      }
  function backshow(x){    
        document.getElementById("movebar"+x).style.animation="mythird 1s";
        document.getElementById("movebar"+x).style.width="10px";
        document.getElementById("movebar"+x).style.opacity="1";
      }
  function stopshow(x){
        document.getElementById("movebar"+x).style.animation="mysecond 1s";
        document.getElementById("movebar"+x).style.width="312px";
        document.getElementById("movebar"+x).style.opacity="1";
        document.getElementById("movebar"+x).style.backgroundColor="#8fc9fb";
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
        addHandler('movebar0','mouseover',show,'0'); 
        addHandler('movebar0','click',stopshow,'0'); 
        addHandler('movebar0','mouseout',backshow,'0'); 
        addHandler('movebar1','mouseover',show,'1'); 
        addHandler('movebar1','click',stopshow,'1'); 
        addHandler('movebar1','mouseout',backshow,'1'); 
        addHandler('movebar2','mouseover',show,'2'); 
        addHandler('movebar2','click',stopshow,'2'); 
        addHandler('movebar2','mouseout',backshow,'2'); 
        addHandler('movebar3','mouseover',show,'3'); 
        addHandler('movebar3','click',stopshow,'3'); 
        addHandler('movebar3','mouseout',backshow,'3'); 
       }
       init();

       }());