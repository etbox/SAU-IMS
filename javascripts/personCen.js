(function(){
    'use strict';
      function showHead(){   /*把换头像界面显示出来*/
      var target=document.getElementById("headChange");
      target.style.display="block";
             
}
    function hiddenHead(){/*隐藏界面*/
      var target=document.getElementById("headChange");
      target.style.display="none";
}

     function showEdit(){  
      var target=document.getElementById("rightEdit");
      target.style.display="block";
             
}
    function hiddenEdit(){/*隐藏界面*/
      var target=document.getElementById("rightEdit");
      target.style.display="none";
      
}
function changeHead(){

  var x="url("+document.getElementsByName('file')[0].value+")";
  alert(x);
   document.getElementById("zhezhao").src=x;
       

}
function hideShuoming(){
     var target=document.getElementById("shuoming");
      target.style.display="none";

}
function addHandler(id, action, func) {
  var domID = document.querySelector(`#${id}`);
  domID.addEventListener(action, function(event) {
    event.preventDefault();
    func(domID.value);
  });
}

function init() {
  
  addHandler('headEdit', 'click',showHead);
  addHandler('x', 'click',hiddenHead);
  addHandler('titleEdit', 'click',showEdit);
  addHandler('save', 'click',hiddenEdit);
  addHandler('up', 'click',changeHead);
  addHandler('shuoming', 'click',hideShuoming);

 
}
hiddenHead();

init();
     }());