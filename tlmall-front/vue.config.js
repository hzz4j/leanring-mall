module.exports = {
  devServer:{
    host:'0.0.0.0',
    port:8080,
    proxy:{
      '/home/*':{
       //target:'http://localhost:8887',
      //  target:'http://192.168.65.214:8887',
       target:'http://192.168.65.214:8887',
        changeOrigin:true,
        // pathRewrite:{
        //   '/home':''
        // }
      },
        '/pms/*':{
           //target:'http://localhost:8866',
            // target:'http://192.168.65.220:8866',
            target:'http://192.168.65.214:8888',
            changeOrigin:true,
        }
        ,
        '/cart/*':{
            //target:'http://localhost:8855',
            // target:' http://192.168.65.165:8855',
            target:' http://192.168.65.214:8888',
            changeOrigin:true,
        }
        ,
        '/sso/*':{
            //target:'http://localhost:8877',
            // target:'http://192.168.65.152:8877',
            target:'http://192.168.65.214:8888',
            changeOrigin:true,
        }
        ,
        '/member/*':{
            //target:'http://localhost:8877',
            // target:'http://192.168.65.152:8877',
            target:'http://192.168.65.214:8888',
            changeOrigin:true,
        } ,
        '/order/*':{
            //target:'http://localhost:8844',
            // target:'http://192.168.65.221:8844',
            target:'http://192.168.65.214:8888',
            changeOrigin:true,
        },
        '/static/qrcode/*':{
          //target:'http://localhost:8844',
          // target:'http://192.168.65.221:8844',
          target:'http://192.168.65.221:8844',
          changeOrigin:true,
        },
        '/es/*':{
            //target:'http://localhost:8054',
            // target:'http://192.168.65.220:8054',
            target:'http://192.168.65.152:8054',
            changeOrigin:true,
            pathRewrite:{
              '/es':''
            }
        }
    }
  },
  // publicPath:'/app',
  // outputDir:'dist',
  // indexPath:'index2.html',
  // lintOnSave:false,
  productionSourceMap:true,
  chainWebpack:(config)=>{
    config.plugins.delete('prefetch');
  }
}