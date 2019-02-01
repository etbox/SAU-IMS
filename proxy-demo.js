const express = require('express');
const path = require('path');
const request = require('request');
// 配置静态文件服务中间件
// server地址
const app = express();
let serverUrl = 'http://kanlon.ink';
app.use(express.static(path.join(__dirname, './src')));
app.use('/', function (req, res) {
  let url = serverUrl + req.url;
  req.pipe(request(url)).pipe(res);
});
app.listen(3000, function () {
  console.log('server is running at port 3000');
});