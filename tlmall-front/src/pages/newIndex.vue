<template>
  <div class="index-page">
    <menu-banner
      :banners="banners"
      :menus="menus"
      :products="products"
    ></menu-banner>
    <div class="gray-page">
      <flash-sale :flashPromotions="flashPromotions"></flash-sale>
      <goods-sale 
      :goodsItems="goodsItems" ></goods-sale>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import MenuBanner from "../components/index/MenuBanner";
import GoodsSale from "../components/index/GoodsSale";
import FlashSale from "../components/index/FlashSale";
import "font-awesome/css/font-awesome.min.css";
export default {
  data() {
    return {
      banners: [],      // 广告
      products: [],     // 分类菜单的推荐商品
      menus: [],        // 分类菜单
      goodsItems:[],    // 商品列表
      flashPromotions:[]     // 秒杀商品
    };
  },
  components: {
    MenuBanner: MenuBanner,
    GoodsSale: GoodsSale,
    FlashSale: FlashSale,
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.axios
        .get("/home/productCateList/0")
        .then((res) => {
          return Promise.resolve(res);
        })
        // 由于接口没有提供一级分类对应的商品，所以再查二级分类吧，效率肯定是不好的
        .then((cateList) => { 
          let allAxios = cateList.map((cate) =>
            this.axios.get("/home/productCateList/" + cate.id)
          );
          // 并行一起请求ajax
          this.axios
            .all(allAxios)
            .then(
              this.axios.spread((...args) => {
                // 执行完成 将二级分类加入一级分类中
                return Promise.resolve(cateList.map((cate,i) => { 
                  // 将二级分类加入到一级分类中
                  cate["children"]=args[i].map(child=>child.id);
                  return cate;
                }));
              })
            )
            // 拿到了组合了一级分类和二级分类的信息
            .then((cateList) => {
               this.menuBannerHandler(cateList); 
            });
        });

      this.axios.get("/home/content").then((res) => {
        this.banners = res.advertiseList;
        this.flashPromotions= res.homeFlashPromotion;
        //{value: '高品质多功能头戴耳机', desc: '我的私人KTV', newPrice: '199', oldPrice: '249', topColor: '#ffac13', src: 'https://i8.mifile.cn/a1/pms_1551343984.14546114.jpg', url: 'https://www.mi.com/seckill/'},
        
        // this.products = [
        //   res.newProductList.slice(0, 4),
        //   res.hotProductList.slice(0, 4),
        // ]; 
      });

    },
    menuBannerHandler(cateList){
        this.axios
                .get("/home/recommendProductList", {
                  params: { pageSize: 99999 },
                })
                .then((res) => {
                  // 处理类型 
                  for (let i = 0; i < cateList.length; i++) {
                    const cate = cateList[i];
                    this.menus.push({
                      value: cate.name,
                      type: cate.id,
                      url: "/#/searchResult/" + cate.name,
                    });

                    // 处理类型对应的商品  
                    for (let i = 0; i < res.length; i++) {
                      let prodcut = res[i]; 
                      if(cate.children && cate.children.includes(prodcut.productCategoryId)){
                          
                        if (!this.products[cate.id]) {
                          this.products[cate.id] = new Array();
                        }
                        this.products[cate.id].push(prodcut);
                      }
                    }
                  }
                  
                  this.goodsSaleHandler(cateList,this.products);
                });
    },
    goodsSaleHandler(cates,products){
      for (let index = 0; index < cates.length; index++) {
              const cate =  cates[index];
              // this.postItems.push({
              //   src:cate.pic,
              //   url:cate.url
              // });

              let goods=[]; 
              let productList=products[cate.id]; 
              if(!productList) continue;
              for (let j = 0; j < productList.length; j++) {
                const product = productList[j];
                goods.push({
                  value:product.name,
                  desc:product.subTitle,
                  sub:product.sub==0?true:false,
                  newPrice:product.price,
                  oldPrice:product.price<product.originalPrice?product.originalPrice:null,
                  src:product.pic,
                  url:"/#/detail/"+product.id
                })
              }


              this.goodsItems.push({
                boxTitle:cate.name ,
                url: '/#/searchResult/'+cate.name,
                listData:{
                  goods:goods
                }
              }) 

            }
    },
    ...mapActions(["saveUserName"]),
  },
};
</script>

<style lang="scss">
.index-page {
  .gray-page {
    display: flex;
    flex-direction: column;
    width: 100%;
    height: auto;
    background-color: #f5f5f5;
  }
}
</style>