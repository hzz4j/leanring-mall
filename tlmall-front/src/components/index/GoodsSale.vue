<template>
  <div class="goods-sale">
    <div  v-for="(item,i) in goodsItems.length" :key="i">
      <!-- <poster :postItem="postItems[i]"></poster> -->
      <goods-box :goodsItem="goodsItems[i]" ></goods-box>  
    </div>
  </div>
</template>

<script>
//import Poster from './Poster'   // 分类推荐
import GoodsBox from './GoodsBox'  // 分类推荐对应的推进商品

export default {
  props: ["goodsItems"],
  data () {
    return {
      postItems:[]
    }
  },

   // 生命周期钩子函数
  mounted() {
    this.init();
  }, 
  methods:{
    init(){
      // 获取分类推荐、对应的推荐商品
       // this.axios.get("/home/goods_sale").then((res) => { 

           
       // });
    }
  },
  components: {
    //'Poster': Poster,
    'GoodsBox': GoodsBox
  }
}
</script>

<style lang="scss">
  .goods-sale {
      position: relative;
      
    text-align: center;
      width: 1226px;
      height: auto;
      margin: 0 auto;
      display: flex;
      flex-direction: column;
  }
  .goods-sale *{    box-sizing: border-box;}
</style>