# SAU-IMS
大创项目：校社联管理系统

建立这条分支为了方便到时候没问题的合并这条分支先到back-end,然后再合并到dev，最后合并到maste

工作进度:
<h4>2018/2/16</h4>
1、继续完善服务层接口，预计完成时间为5天<br/>
2、各个发送功能测试正常，redis服务器暂时正常<br/>
3、修复若干个bug<br/>
4、未来加入投票系统，定时任务功能支持<br/>
5、预计一个星期后开始编写点赞功能(所有事先设计的功能都已完成)<br/>


<h4>2018/2/7</h4>
1、大幅修改项目文件结构，包结构，测试运行正常<br/>
2、新增redis支持，到时申请redis服务器<br/>
3、重写服务层接口，完成首页部分接口编写（按接口文档序号）<br/>
4、清空controller层方法内代码，只保留方法<br/>
5、session移向服务层，并新增session通用操作<br/>
6、大幅修改dao层，逻辑不变<br/>
7、清除大量无意义工具类，部分合并，但未全部修改<br/>
8、将service api分离出来，为将来open-api/rpc-service-api？<br/>
9、将大部分类的操作抽象为BaseXXX接口<br/>

<h4>2018/1/27</h4>
1、大幅修改Dao层，更加具有结构化<br/>
2、删除pojo里的json包<br/>
3、添加了用户工厂类，但尚未实现<br/>

<h4>2018/1/21</h4>
1、完成邮箱等手机发送工具类<br/>
2、完成验证码工具类<br/>
3、添加ip工作类<br/>
4、部分工具类的正则验证尚未完成<br/>
5、其他层尚未完成<br/>

