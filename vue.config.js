// vue.config.js
module.exports = {
  devServer: {
    proxy: {
      '/': {
        target: 'http://kanlon.ink', // 正式环境
        changeOrigin: true,
        autoRewrite: true,
        cookieDomainRewrite: true,
        pathRewrite: {
          '^/': ''
        },
        ws: false
      }
    }
  }
}