// vue.config.js
module.exports = {
  devServer: {
    proxy: {
      '/': {
        target: '//kanlon.ink', // 正式环境
        changeOrigin: true,
        // autoRewrite: true,
        // cookieDomainRewrite: true,
        // pathRewrite: {
        //   '^/api/': '/'
        // }
      }
    }
  }
}