// Mock.mock( template )
let template = {
  'code': 0,
  'msg': '',
  'data': [
    {
      'clubId': 123,
      'clubView': '摄影协会.png',
      'clubName': '摄影协会',
      'description': '定格光影，留住光阴',
      'menbers': 65,
      'likeNumber': 32
    },
    {
      'clubId': 124,
      'clubView': '棋牌社.png',
      'clubName': '棋牌社',
      'description': '人生是棋局，谁人为棋子',
      'menbers': 65,
      'likeNumber': 32
    }
  ]
};
let Data = Mock.mock(template);

// $('<pre>').text(JSON.stringify(Data, null, 4)).appendTo('body');


// $('.clublist').click(function() {
for (let j = 0; j < 5; j++) {
  for (let i = 0; i < Data.data.length; i++) {
    let $li = $(`
      <!-- li begin -->
      <li>
        <!--  -->
        <div class="shade"></div>
        <!--  -->
        <div class="cover pos">
          <a href="" target="_blank" title="">
            <img src="./images/${Data.data[i].clubView}" alt="${Data.data[i].clubView}">
          </a>
        </div>
        <div class="info">
          <h4 class="ta">${Data.data[i].clubName}</h4>
          <div class="msg mtn cl ta">
            ${Data.data[i].description}
          </div>
          <p class="mtn cl">
            <img src="./images/member.png" alt="成员数" class="icon-member">
            <span class="members">${Data.data[i].menbers}人</span>
            <img src="./images/heart.png" alt="喜欢数" class="icon-heart">
            <span class="likeNumber">${Data.data[i].likeNumber}人</span>
          </p>
        </div>
      </li>`);
    $li.appendTo('ul');
  }
}
// });
