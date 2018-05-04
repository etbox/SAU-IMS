<h2>校社联管理系统API文档</h2>
这里暂时存放接口文档。<br/><br/>
下面是postman测试接口生成的在线API文档，可以由后端实时更新。
<br/>
<hr/>
<h4>首页功能</h4>
公开接口文档地址 
<a target="_blank" href="https://documenter.getpostman.com/view/3892535/collection/RVnVDzVM#b6d57746-5c52-432a-89b6-d3c14da26e0d">
https://documenter.getpostman.com/view/3892535/collection/RVnVDzVM#b6d57746-5c52-432a-89b6-d3c14da26e0d
</a>
<h4>图片资源</h4>
公开接口文档地址 
<a target="_blank" href="https://documenter.getpostman.com/view/3892535/collection/RVnVDzVS">
https://documenter.getpostman.com/view/3892535/collection/RVnVDzVS
</a>
<h4>安全功能接口测试</h4>
公开接口文档地址 
<a target="_blank" href="https://documenter.getpostman.com/view/3892535/collection/RVnVDzVU">
https://documenter.getpostman.com/view/3892535/collection/RVnVDzVU
</a>

<h4>接受消息功能</h4>
公开接口文档地址 
<a target="_blank" href="https://documenter.getpostman.com/view/3892535/collection/RVnVDzVV">
https://documenter.getpostman.com/view/3892535/collection/RVnVDzVV
</a>


<h4>普通用户端/社团成员端</h4>
公开接口文档地址 
<a target="_blank" href="https://documenter.getpostman.com/view/3892535/collection/RVnVDzVW">
https://documenter.getpostman.com/view/3892535/collection/RVnVDzVW
</a>

<h4>校社联管理端</h4>
公开接口文档地址 
<a target="_blank" href="https://documenter.getpostman.com/view/3892535/collection/RVnVDzZm">
https://documenter.getpostman.com/view/3892535/collection/RVnVDzZm
</a>


<h4>社团管理端</h4>
公开接口文档地址 
<a target="_blank" href="https://documenter.getpostman.com/view/3892535/collection/RVnVDzZn">
https://documenter.getpostman.com/view/3892535/collection/RVnVDzZn
</a>

<h4>注意事项</h4>
公开注意事项地址
<a target="_blank" href="https://documenter.getpostman.com/view/3892535/collection/RVu4F9Ak">
https://documenter.getpostman.com/view/3892535/collection/RVu4F9Ak
</a>

关于项目的注意事项，不用于请求<br/>
<font color="red"><h4>前端</h4></font><br/>
(1)如果用户还没登录进行，消息查询等非法操作， 后端会返回一个首页页面。<br/>
(2)查询内容为空是，是返回全部内容的<br/>
(3)年度审核的标题必须按照规定（年份+社团名）填写，前后端都要做校验，不然提交年度审核那个功能会有问题。而且目前是强制这一年审核上一年的年度注册。<br/>
(4)在查询所有列表类数据时或或者搜索时，发送的offset参数是表示页码，默认在后端以分页形式实现，前端可以利用这个实现分页或瀑布流。<br/>
(5)在实现搜索的时候，前端如果发现搜索内容为空，最后自己将发送一次加载全部数据，不要将空的搜索内容发送到后端。<br/>
(6)后端返回的时间数据会损失精度，最多精确到秒，毫秒后忽略。（问题不大，不影响正常交互）<br/>
(7)获取个人用户头像的时候是要通过指定链接获取的(不用再获取图片名)，而获取校社联和社团的头像和展示图是通过确定链接，然后再将后端返回头像图名或展示图名加到图片资源后面。<br/>
(8)在首页不能执行点赞功能，只能查看，要进行点赞功能，必须让个人用户进入到他管理中心中的社团信息中。<br/>

<hr/>
<font color="red"><h4>后端</h4></font><br/>
(1)有关消息的接受和发送一律用user_Id;<br/>
(2)请求delete时，后端使用Model类来接受参数。<br/>
(3)社团或校社联通过session.getUserId得到的org_id,普通用户得到的是person_id;而通过getAccId方法得到的都是User_id。<br/>
(4)社团的年度注册审核不能删除，校社联的可以。通过在audit_state加一个值，如果是3则是删除。社团看不了这个值。<br/>
(5)年度审核的标题必须按照规定（年份+社团名）填写，前后端都要做校验，不然提交年度审核那个功能会有问题。<br/>
(6)如果前端用formData发送数据，后端使用@ModelArrite类接受；如果前端使用json文本数据，后端使用@requestBody类接受。<br/>
(7)person_info表内的logo字段默认是空，如果没有自己上传过头像，则是空的。<br/>
