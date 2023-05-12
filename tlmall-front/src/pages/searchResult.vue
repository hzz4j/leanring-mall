<!--
 * @FileDescription: 商品搜索页
 * @Author: 徐庶
 * @Date: 2021年4月21日 
 -->
<template>
  <div class="index">
    <nav-header></nav-header>

    <div class="JD_banner w">
      <div class="JD_nav">
        <div class="JD_selector">
          <!--手机商品筛选-->
          <div class="title">
            <h3>
              <b>{{ keyword }}</b>

              <Tag
                v-for="(tag, i) in tags"
                :key="tag.i"
                closable
                @close="removeTag(i)"
                :type="tag.type"
              >
                {{ tag.text }}
              </Tag>
              <em>商品筛选</em>
            </h3>
            <div class="st-ext">
              共&nbsp;<span>{{ result.total }}</span
              >个商品
            </div>
          </div>
          <div class="JD_nav_logo">
            <!--品牌-->
            <div class="JD_nav_wrap">
              <div
                class="sl_key"
                v-if="result.brands && result.brands.length > 0"
              >
                <span>品牌：</span>
              </div>
              <div
                class="sl_value"
                v-if="result.brands && result.brands.length > 0"
              >
                <div class="sl_value_logo">
                  <ul>
                    <li
                      v-for="(item, i) in result.brands"
                      :key="i"
                      @click="addBrand(item)"
                    >
                      <a href="javascript:void(0)">
                        <img :src="item.brandImg" :alt="item.brandName" width="118px" height="50px" />
                        <div>{{ item.brandName }}</div>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="sl_ext">
                <a href="#" v-if="result.brands && result.brands.length > 16">
                  更多
                  <i
                    style="
                      background: url('/imgs/so/search.ele.png') no-repeat 3px
                        7px;
                    "
                  ></i>
                  <b
                    style="
                      background: url('/imgs/so/search.ele.png') no-repeat 3px -44px;
                    "
                  ></b>
                </a>
                <!-- <a href="#">
                  多选
                  <i>+</i>
                  <span>+</span>
                </a> -->
              </div>
            </div>
            <!--价格 
            <div-- class="JD_pre">
              <div class="sl_key">
                <span>价格：</span>
              </div>
              <div class="sl_value">
                <ul>
                  <li><a href="#">0-499</a></li>
                  <li><a href="#">500-999</a></li>
                  <li><a href="#">1000-1699</a></li>
                  <li><a href="#">1700-2799</a></li>
                  <li><a href="#">2800-4499</a></li>
                  <li><a href="#">4500-11999</a></li>
                  <li><a href="#">12000以上</a></li>
                  <li class="sl_value_li">
                    <input type="text" />
                    <p>-</p>
                    <input type="text" />
                    <a href="#">确定</a>
                  </li>
                </ul>
              </div>
            </div-->
            <!-- 类型 -->
            <div class="JD_pre">
              <div class="sl_key">
                <span>分类：</span>
              </div>
              <div class="sl_value">
                <ul>
                  <li v-for="(item, i) in result.categorys" :key="i">
                    <a href="javascript:void(0)" @click="addCate(item)">{{
                      item.categoryName
                    }}</a>
                  </li>
                </ul>
              </div>
            </div>
            <!--属性-->
            <div
              class="JD_pre"
              v-for="(item, i) in result.attrs.slice(0, 2)"
              :key="i"
            >
              <div class="sl_key">
                <span>{{ item.attrName }}：</span>
              </div>
              <div class="sl_value">
                <ul>
                  <li v-for="(citem, j) in item.attrValue" :key="j">
                    <a
                      href="javascript:void(0)"
                      @click="addAttr(item.attrId, citem)"
                      >{{ citem }}</a
                    >
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <transition name="fade" mode="out-in">
            <div v-if="!logic.showMoreAttr" class="moreAttr">
              <div
                class="JD_pre"
                v-for="(item, i) in result.attrs.slice(3)"
                :key="i"
              >
                <div class="sl_key">
                  <span>{{ item.attrName }}：</span>
                </div>
                <div class="sl_value">
                  <ul>
                    <li v-for="(citem, j) in item.attrValue" :key="j">
                      <a href="#">{{ citem }}</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </transition>
          <div class="JD_show" v-if="result.attrs.length > 2">
            <a href="javascript:void(0)">
              <span v-on:click="logic.showMoreAttr = !logic.showMoreAttr">
                <span v-if="logic.showMoreAttr"> 更多选项 </span>
                <span v-else> 折叠选项 </span>
                （<span v-for="(item, i) in result.attrs.slice(3)" :key="i"
                  >{{ item.attrName }}
                </span>
                等）
              </span>
            </a>
          </div>
        </div>
        <!--排序-->
        <div class="JD_banner_main">
          <!--综合排序-->
          <div class="JD_con_right">
            <div class="filter">
              <!--综合排序-->
              <div class="filter_top">
                <div class="filter_top_left">
                  <a
                    :class="{ active: logic.sortObj.by === '' }"
                    href="javascript:;"
                    @click="addSort('')"
                    >综合排序  
                    </a>
                  <a
                    :class="{ active: logic.sortObj.by === 'salecount' }"
                    href="javascript:;"
                    @click="addSort('salecount')"
                    >销量
                    <span v-if="logic.sortObj.by === 'salecount'">{{logic.sortObj.type==="desc"?"↓":"↑"}}</span>
                    </a>
                  <a
                    :class="{ active: logic.sortObj.by === 'price' }"
                    href="javascript:;"
                    @click="addSort('price')"
                    >价格
                    <span v-if="logic.sortObj.by === 'price' ">{{logic.sortObj.type==="desc"?"↓":"↑"}}</span>
                    </a
                  >
                  <a
                    :class="{ active: logic.sortObj.by === 'putawayDate' }"
                    href="javascript:;"
                    @click="addSort('putawayDate')"
                    >上架时间 
                    <span v-if="logic.sortObj.by === 'putawayDate'">{{logic.sortObj.type==="desc"?"↓":"↑"}}</span>
                    </a>
                </div>

                <div
                  id="J_selectorPrice"
                  class="f-price"
                  :class="{ 'f-price-focus': logic.proceFocus }"
                  @mousemove="logic.proceFocus = true"
                  @mouseout="logic.proceFocus = false"
                >
                  <div class="f-price-set">
                    <div class="fl">
                      <input
                        v-if="logic.prices.begin === '￥'"
                        @click="logic.prices.begin = ''"
                        type="text"
                        class="input-txt"
                        autocomplete="off"
                        v-model="logic.prices.begin"
                        value="￥"
                        style="color: #ccc"
                      />
                      <input
                        v-else
                        type="text"
                        class="input-txt"
                        autocomplete="off"
                        v-model="logic.prices.begin"
                        style="color: rgb(51, 51, 51)"
                        value=""
                        @keyup="
                          logic.prices.begin = isNaN(
                            parseInt(logic.prices.begin)
                          )
                            ? ''
                            : parseInt(logic.prices.begin)
                        "
                      />
                    </div>
                    <em>-</em>
                    <div class="fl">
                      <input
                        v-if="logic.prices.end === '￥'"
                        @click="logic.prices.end = ''"
                        type="text"
                        class="input-txt"
                        autocomplete="off"
                        v-model="logic.prices.end"
                        style="color: #ccc"
                      />
                      <input
                        v-else
                        type="text"
                        class="input-txt"
                        autocomplete="off"
                        v-model="logic.prices.end"
                        style="color: rgb(51, 51, 51)"
                        @keyup="
                          logic.prices.end = isNaN(parseInt(logic.prices.end))
                            ? ''
                            : parseInt(logic.prices.end)
                        "
                      />
                    </div>
                  </div>
                  <div class="f-price-edit">
                    <a
                      href="javascript:;"
                      class="item1 J-price-cancle"
                      @click="clearPrice()"
                      >清空</a
                    >
                    <a
                      href="javascript:;"
                      @click="addPrice()"
                      class="item2 J-price-confirm"
                      >确定</a
                    >
                  </div>
                </div>

                <div class="filter_top_right">
                  <Checkbox @change="addStore">仅显示有货</Checkbox>
                </div>
              </div>

              <!--商品列表-->
              <div class="goods-content">
                <div class="goods-one">
                  <div
                    class="goods-item brick-item"
                    v-for="(product, i) in result.products"
                    :key="i"
                  >
                    <a :href="'/#/detail/' + product.id" target="_blank"
                      ><img :src="product.pic" :alt="product.subTitle" />
                      <h3 class="name ellipsis" v-html="product.name"></h3>
                      <span class="desc ellipsis">{{ product.subTitle }}</span>
                      <div
                        class="thumbs"
                        v-if="product.skus && product.skus.length > 0"
                      >
                        <ul class="thumb-list">
                          <li class="active">
                            <a href="javascript: void(0);"
                              ><img
                                src="https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0d5f3f2e83adde294aac05ca3ea72c14.jpg"
                                width="34"
                                height="34"
                              />
                            </a>
                          </li>
                        </ul>
                      </div>
                      <div class="price">
                        <span>{{ product.price}}元</span
                        ><!----><del v-if="product.originalPrice">{{product.originalPrice}}元</del>
                      </div></a
                    >
                  </div>
                </div>
              </div>
              <center style="margin: 10px auto; font-size: 18px">
                <Pagination
                  background
                  :current-page="result.pageNum"
                  :page-size="params.pageSize"
                  layout="prev, pager, next, jumper"
                  :total="result.total"
                >
                </Pagination>
              </center>
            </div>
          </div>
        </div>
      </div>
    </div>
    <nav-footer></nav-footer>
  </div>
</template>
<script>
import NavHeader from "./../components/NavHeader";
import NavFooter from "./../components/NavFooter";
import { Checkbox, Pagination, Tag } from "element-ui";
import Qs from "qs";
export default {
  name: "searchResult",
  components: {
    NavHeader,
    NavFooter,
    Checkbox,
    Pagination,
    Tag,
  },
  data() {
    return {
      productList: [],
      logic: {
        proceFocus: false, // 鼠标移入样式
        showMoreAttr: true,
        prices: {
          begin: "￥",
          end: "￥",
        },
        sortObj: {
          by: "",
          type: "asc",
        },
      },
      params: {
        price: "",
        keyword: this.keyword,
        brandId: [],
        category3Id: "",
        sort: {},
        hasStock: "",
        attrs: [],
        pageNum: 1,
        pageSize: 20,
      },
      result: {
        attrs: [],
      },
      keyword: "",
      tags: [], //{ type:"warn、scussce 标签样式类型" text:"显示文字",value:"移除的标识",name:"对应this.params的属性名（比如品牌brandId，类型category3Id、属性attrs等）"}
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      let params = this.$route.params;
      Object.assign(this, params);
      this.params.keyword=this.keyword
      window.console.log(this);
      window.console.log(params);
      this.doSearch();
    }, 
    doSearch() {
      this.axios
        .post(
          "/es/searchList",
          Qs.stringify(this.params),
          { headers: { "Content-Type": "application/x-www-form-urlencoded" } }
        )
        .then((res) => {
          window.console.log(res);
          Object.assign(this.result, res);
          //this.result.attrs=res.attrs;
          //this.productList = res.list;
        });
    },
    buyIt(id) {
      this.$router.push(`/product/${id}`);
    },
    /**
    * @description: 添加条件标签
    * @param text 标签显示文本
    * @param value 标签标示
    * @param name 对应this.params的属性名（比如品牌brandId，类型category3Id、属性attrs等）
    * @return void 
    */
    addTag(text, value, name) {
      this.tags.push({ text, type: "warning", name, value });
    },
    removeTag(i) {
      if (i < 0 || i > this.tags.length) {
        return;
      }
      const tag = this.tags[i];
      this.tags.splice(i, 1);
      //获得tag对应的参数
      let param = this.params[tag.name];
      if (param instanceof Array) {
        // 移除掉参
        this.params[tag.name].splice(
          this.params[tag.name].findIndex((item) => item === tag.value),
          1
        );
      } else {
        this.params[tag.name] = "";
      }
    },
    // 单选的条件 只能存在一个
    changeTag(text, value, name) {
      let i = this.tags.findIndex((tag) => tag.name === name);
      this.removeTag(i);
      this.addTag(text, value, name);
      this.params[name] = value;
    },
    addBrand(item) {
      // 不包含
      if (!this.params.brandId.includes(item.brandId)) {
        // 添加参数
        this.params.brandId.push(item.brandId);

        // 添加tag
        this.addTag(item.brandName, item.brandId, "brandId");
      }
    },
    addCate(item) {
      if (this.params.category3Id != item.categoryId) {
        this.changeTag(item.categoryName, item.categoryId, "category3Id");
      }
    },
    addAttr(attrId, attrValue) {
      // 不包含
      const value = attrId + "_" + attrValue;
      if (!this.params.attrs.includes(value)) {
        // 添加参数
        this.params.attrs.push(value);

        // 添加tag
        this.addTag(attrValue, value, "attrs");
      }
    },
    addPrice() {
      const begin = isNaN(parseInt(this.logic.prices.begin))
        ? ""
        : parseInt(this.logic.prices.begin);
      const end = isNaN(parseInt(this.logic.prices.end))
        ? ""
        : parseInt(this.logic.prices.end);
      const price = begin + "_" + end;

      if (this.params.price != price) {
        // 存在
        this.changeTag(`价格:${begin}-${end}元`, price, "price");
      }
    },
    clearPrice() {
      this.removeTag(this.tags.findIndex((tag) => tag.name === "price"));
      this.logic.prices = {};
    },
    addStore(value) {
      if (value) {
        this.params.hasStock = 1;
        this.addTag("仅显示有货", 1, "hasStock");
      } else {
        let i = this.tags.findIndex((tag) => tag.name === "hasStock");
        this.removeTag(i);
      }
    },
    addSort(value) {
      // 重复点击则切换排序
      if (this.logic.sortObj.by === value) {
        this.logic.sortObj.type =
          this.logic.sortObj.type === "asc" ? "desc" : "asc";
      }
      else{
        // 默认升序
        this.logic.sortObj.type ="asc";
      }
      this.logic.sortObj.by = value;
      this.params.sort = this.logic.sortObj.by + "_" + this.logic.sortObj.type;
    }, 
  },
  watch: {
    params: {
      handler() { 
        this.doSearch();
      },
      deep: true, //深度侦听器
    },
  },
};
</script>
<style lang="scss">
@import "./../assets/scss/config.scss";
@import "./../assets/scss/mixin.scss";
.index {
  //手机
  .JD_ipone {
    width: 1226px;
    margin: 0 auto;
    .JD_ipone_bar {
      height: 24px;
      line-height: 24px;
      .JD_ipone_one {
        float: left;
        margin-right: 5px;
        a {
          text-decoration: none;
          background: #fff;
        }
      }
      .b:hover {
        .qqq {
          border: 1px solid #e23a3a;
          border-bottom: 1px solid #fff;
        }
      }
      .c:hover {
        .qqq {
          border: 1px solid #e23a3a;
          border-bottom: 1px solid #fff;
        }
      }
      .a {
        font-size: 20px;
        font-weight: 900;
      }
      .c,
      .b {
        position: relative;
        a {
          display: inline-block;
          height: 22px;
          padding: 0 4px 0 8px;
          border: 1px solid #ddd;
          line-height: 22px;
          vertical-align: top;
          font-size: 14px;
          position: relative;
          //z-index: 10;
        }
        div {
          position: absolute;
          top: 23px;
          left: 0;
          width: 400px;
          padding: 10px 0;
          border: 1px solid #e23a3a;
          background: #fff;
          display: none;
          a {
            border: 0;
            display: block;
            float: left;
            margin-right: 5px;
          }
          a:hover {
            color: #e23a3a;
          }
        }
      }
      i {
        float: left;
        margin-right: 5px;
      }
    }
  }
  //商品筛选和排序
  .JD_banner {
    margin-top: 15px;
    width: 100%;
    background-color: #f5f5f5;
    .JD_nav {
      width: 1226px;
      margin: 0 auto;
      .JD_selector {
        border-top: 1px solid #ddd;
        background: #fff;
        margin-bottom: 10px;
        .title {
          border-bottom: 1px solid #ddd;
          background: #f1f1f1;
          line-height: 34px;
          height: 34px;
          overflow: hidden;
          zoom: 1;
          h3 {
            float: left;
            padding-left: 10px;
            font-size: 14px;
            b {
              color: #e4393c;
              margin-right: 5px;
            }
            em {
              font-style: normal;
              margin: 0px 5px;
            }
          }
          .st-ext {
            float: left;
            padding-left: 20px;
            font-size: 13px;
            color: #999;
          }
        }
        .JD_nav_logo,
        .moreAttr {
          width: auto;
          height: auto;
          .sl_key {
            float: left;
            width: 100px;
            padding-left: 10px;
            color: #666;
            font-size: 14px;
          }
          .sl_value {
            margin-left: 110px;
            padding-right: 130px;
            padding-left: 10px;
            overflow: hidden;
            a:hover {
              color: red;
            }
          }
          .sl_ext {
            position: absolute;
            top: 6px;
            right: 10px;
            width: 120px;
            line-height: 25px;
            overflow: hidden;
            a {
              font-size: 12px;
              text-decoration: none;
            }
            a:nth-child(1):hover {
              color: #e23a3a;
              i {
                border: 1px solid #e23a3a;
              }
              b {
                border: 1px solid #e23a3a;
              }
            }
            a:nth-child(2):hover {
              border: 1px solid #e23a3a;
              color: #e23a3a;
            }
            a:nth-child(1) {
              float: left;
              position: relative;
              width: 50px;
              height: 22px;
              background: #fff;
              color: #333;
              margin-right: 10px;
              i {
                position: absolute;
                top: 3px;
                right: 0;
                display: block;
                width: 20px;
                height: 20px;
                border: 1px solid #ddd;
              }
              b {
                position: absolute;
                top: 24px;
                right: 0;
                display: block;
                width: 20px;
                height: 20px;
                border: 1px solid #ddd;
              }
            }
            a:nth-child(2) {
              line-height: 20px;
              border: 1px solid #ddd;
              padding: 2px 3px 2px 18px;
              position: relative;
              background: #f8f8f8;
              color: #333;
              i {
                position: absolute;
                display: block;
                font-style: normal;
                left: 3px;
                top: -1px;
                width: 13px;
                height: 20px;
                font-size: 20px;
              }
              span {
                position: absolute;
                display: block;
                font-style: normal;
                left: 3px;
                top: 13px;
                font-size: 20px;
                width: 13px;
                height: 20px;
                color: #e23a3a;
              }
            }
          }
          .JD_nav_wrap {
            padding-bottom: 13px;
            position: relative;
            line-height: 34px;
            border-bottom: 1px dashed #eee;
            .sl_value {
              .sl_value_logo {
                ul {
                  padding-top: 10px;
                  margin-bottom: 10px;
                  zoom: 1;
                  height: 50px;
                  li {
                    float: left;
                    width: 116px;
                    height: 48px;
                    border: 1px solid #ddd;
                    margin: 0px 10px 0 0;
                    text-align: center;
                    a {
                      position: relative;
                      img {
                        width: 115px;
                        height: 48px;
                      }
                      div {
                        position: relative;
                        top: -62px;
                        left: -4px;
                        background: #fff;
                        border: 2px solid #e23a3a;
                        width: 116px;
                        height: 46px;
                        line-height: 48px;
                        font-size: 12px;
                        color: #e23a3a;
                        display: none;
                      }
                    }
                  }
                  li:hover {
                    div {
                      display: block;
                    }
                  }
                }
              }
            }
          }
          .JD_pre {
            overflow: hidden;
            position: relative;
            line-height: 34px;
            border-bottom: 1px dashed #eee;
            ul {
              float: left;
              overflow: hidden;
              position: relative;
              height: 30px;
              li {
                float: left;
                margin-bottom: 4px;
                height: 26px;
                line-height: 26px;
                a {
                  color: #666;
                  text-decoration: none;
                  font-size: 13px;
                  padding: 10px 20px;
                  line-height: 34px;
                }
              }
              .sl_value_li {
                width: 135px;
                margin-right: 0;
                p {
                  line-height: 32px;
                  display: block;
                  float: left;
                  color: #ddd;
                }
                a {
                  display: inline-block;
                  line-height: 14px;
                  border-radius: 2px;
                  background: #f7f7f7;
                  text-align: center;
                  text-decoration: none;
                  border: 1px solid #ddd;
                  padding: 4px 6px;
                  color: #666;
                  margin-left: 5px;
                  margin-top: 5px;
                }
                input {
                  width: 30px;
                  float: left;
                  height: 19px;
                  margin-top: 7px;
                  line-height: 19px;
                  border: 1px solid #ccc;
                }
              }
            }
          }
        }
      }
      .JD_banner_main {
        overflow: hidden;
        width: 1226px;
        .JD_con_left {
          width: 210px;
          float: left;
          .JD_con_left_bar {
            .JD_con_one {
              border: 1px solid #ddd;
              margin-bottom: 10px;
              .mt {
                line-height: 36px;
                padding: 0 10px;
                h3 {
                  font-size: 14px;
                  float: left;
                }
                span {
                  float: right;
                  font-size: 12px;
                  color: #999;
                  margin-right: 10px;
                }
              }
              .mc {
                ul {
                  li {
                    margin-top: 15px;
                    img {
                      width: 200px;
                    }
                    em {
                      font-style: normal;
                    }
                    a:nth-child(2) {
                      color: #666;
                      text-decoration: none;
                      height: 36px;
                      line-height: 18px;
                      margin-bottom: 10px;
                      overflow: hidden;
                      display: block;
                      padding: 0 10px;
                      em {
                        font-size: 12px;
                      }
                    }
                    a:nth-child(2):hover {
                      color: #e23a3a;
                    }
                    .mc_price {
                      height: 16px;
                      line-height: 16px;
                      margin-bottom: 4px;
                      overflow: hidden;
                      padding: 0 10px;
                      .price {
                        float: left;
                        margin-right: 6px;
                        color: #e4393c;
                        font-weight: 700;
                        font-size: 14px;
                      }
                      .mc-ico {
                        i {
                          float: left;
                          height: 16px;
                          line-height: 16px;
                          padding: 0 3px;
                          overflow: hidden;
                          text-align: center;
                          font-style: normal;
                          font-size: 12px;
                          background: #e23a3a;
                          color: #fff;
                          cursor: default;
                          border-radius: 2px;
                          margin-right: 5px;
                        }
                      }
                    }
                    .mc_rev {
                      padding: 0 10px;
                      color: #999;
                      font-size: 12px;
                      .number {
                        color: #005aa0;
                        text-decoration: none;
                      }
                    }
                  }
                }
              }
            }
          }
        }
        .JD_con_right {
          width: 100%;
          .filter {
            position: relative;
            z-index: 4;
            border-top: 1px solid #ddd;
            margin-bottom: 5px;
            .filter_top {
              padding: 6px 8px;
              border-bottom: 1px solid #e7e3e7;
              background: #f9f9f9;
              height: 25px;
              .filter_top_left {
                float: left;
                margin-right: 13px;
                a {
                  float: left;
                  padding: 0 10px;
                  height: 23px;
                  border: 1px solid #ccc;
                  line-height: 23px;
                  margin-right: -1px;
                  background: #fff;
                  color: #333;
                  font-size: 12px;
                  text-decoration: none;
                }
                a:hover {
                  border: 1px solid #e4393c;
                  color: #e4393c;
                }
                a.active {
                  color: #fff;
                  border-color: #e4393c;
                  background: #e4393c;
                }
              }
              .filter_top_right {
                float: right;
                .fp-text {
                  float: left;
                  line-height: 23px;
                  margin-right: 10px;
                  b {
                    color: #e4393c;
                    font-weight: 700;
                  }
                  em {
                    font-weight: 400;
                    font-style: normal;
                  }
                  i {
                    font-weight: 400;
                    font-style: normal;
                  }
                }
                a {
                  text-decoration: none;
                  padding: 0;
                  background: #f1f1f1;
                  color: #ccc;
                  cursor: default;
                }
                .prev,
                .next {
                  float: left;
                  display: block;
                  width: 46px;
                  height: 23px;
                  border: 1px solid #ddd;
                  background: #fff;
                  line-height: 23px;
                  text-align: center;
                  font-size: 16px;
                  color: #aaa;
                }
                .prev {
                  padding: 0;
                  background: #f1f1f1;
                  color: #ccc;
                  cursor: default;
                }
              }

              .f-price {
                position: relative;
                float: left;
                width: 132px;
                height: 25px;
                margin-right: 12px;
                .f-price-set {
                  width: 100%;
                  height: 100%;
                  overflow: hidden;
                  .input-txt,
                  em {
                    float: left;
                    display: inline;
                  }
                  .input-txt {
                    width: 52px;
                    border-color: #ccc;
                    color: #333;
                    float: left;
                    display: inline;
                    height: 25px;
                    line-height: 17px;
                    border: 1px solid #ccc;
                    padding: 3px;
                  }
                  .input-txt:focus {
                    outline-color: transparent;
                  }
                  em {
                    width: 12px;
                    line-height: 23px;
                    color: #333;
                    text-align: center;
                  }
                }
                .f-price-edit {
                  display: none;
                  width: 132px;
                  height: 25px;
                  padding: 37px 6px 6px;
                  border: 1px solid #999;
                  position: absolute;
                  background: #fff;
                  box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
                  .item1 {
                    position: absolute;
                    top: 37px;
                    left: 6px;
                    width: 36px;
                    height: 25px;
                    line-height: 25px;
                    text-align: center;
                    color: #005aa0;
                  }
                  .item2 {
                    position: absolute;
                    top: 37px;
                    right: 7px;
                    width: 38px;
                    height: 17px;
                    line-height: 17px;
                    padding: 3px;
                    border: 1px solid #ccc;
                    text-align: center;
                    background: #f7f7f7;
                    color: #333;
                  }
                }
              }
              .f-price-focus {
                z-index: 6;
                .f-price-set {
                  position: relative;
                  z-index: 5;
                }
                .f-price-edit {
                  display: block;
                  zoom: 1;
                  top: -7px;
                  left: -7px;
                }
              }
            }

            .rig_tab {
              padding: 15px 10px;
              overflow: hidden;
              & > div:hover {
                box-shadow: 0 0 1px 0.5px #666;

                .ico {
                  display: block;
                }
              }

              & > div {
                padding: 5px;
                padding-top: 15px;
                float: left;
                margin-right: 10px;
                height: 405px;
                width: 220px;
                position: relative;

                .ico {
                  display: none;
                  position: absolute;
                  top: 230px;
                  right: 10px;
                  text-align: center;
                  width: 70px;
                  line-height: 30px;
                  color: white;
                  font-size: 14px;
                  background: rgba(0, 0, 0, 0.5);

                  a {
                    color: white;
                  }
                }

                .ico:hover {
                  color: red;

                  a {
                    color: red;
                  }
                }

                .da {
                  text-align: center;

                  img {
                    width: 205px;
                    height: 210px;
                  }
                }

                & > ul {
                  overflow: hidden;

                  li:first-child {
                    border: 2px solid red;
                  }

                  li {
                    overflow: hidden;
                    margin-right: 3px;
                    border: 1px solid #ccc;
                    float: left;

                    img {
                      margin: 3px;
                      float: left;
                      width: 25px;
                      height: 25px;
                    }
                  }
                }

                .tab_R {
                  color: red;
                  font-size: 18px;
                  font-weight: 800;
                  margin: 5px;

                  span {
                    display: none;
                  }

                  span:first-child {
                    display: block;
                  }
                }

                .tab_JE {
                  margin: 2px;

                  a:first-child {
                    display: block;
                  }

                  a {
                    display: none;
                    color: #666;
                    font-size: 12px;
                    height: auto;
                    overflow: hidden;
                    text-decoration: none;
                  }

                  a:hover {
                    color: red;
                  }
                }

                .tab_PI {
                  line-height: 20px;
                  color: #a7a7a7;
                  font-size: 12px;

                  span {
                    color: #646fb0;
                    font-size: 15px;
                  }

                  a {
                    margin-left: 25px;
                    font-size: 12px;
                    text-decoration: none;
                    color: #646fb0;
                  }
                }

                .tab_CP {
                  line-height: 25px;
                  margin-bottom: 5px;
                  a {
                    font-size: 12px;
                    color: #999999;
                    text-decoration: none;
                  }
                }

                .tab_FO {
                  font-size: 14px;
                  position: relative;

                  p {
                    float: left;
                    font-size: 12px;
                    height: 16px;
                    line-height: 16px;
                    padding: 0 3px;
                    margin-right: 3px;
                    overflow: hidden;
                    text-align: center;
                    cursor: default;
                    border-radius: 2px;
                  }

                  p:first-child {
                    background: #e23a3a;
                    color: #fff;
                  }
                  p:last-child {
                    border: 1px solid #e23a3a;
                    color: #e23a3a;
                  }

                  p:hover {
                    span {
                      display: block;
                    }
                  }

                  span {
                    width: 100%;
                    margin-top: 5px;
                    padding-left: 11px;
                    line-height: 40px;
                    display: inline-block;
                    color: #666;
                    box-shadow: 0 0 1px 0.5px #666;
                    display: none;
                    position: absolute;
                    top: 20px;
                    left: -5px;
                    z-index: 20;
                    background: #fff;
                  }
                }
              }
            }
            .filter_page {
              width: 100%;
              em {
                font-style: normal;
              }
              .page_wrap {
                margin-left: 355px;
                .page_span1 {
                  a {
                    font-size: 14px;
                    display: block;
                    float: left;
                    text-decoration: none;
                    height: 36px;
                    background: #f0f0f0;
                    line-height: 36px;
                    padding: 0 14px;
                    margin-right: 5px;
                    border: 1px solid #ddd;
                  }
                  a:nth-child(1) {
                    color: #ccc;
                    background: #fff;
                  }
                }
                .page_span2 {
                  em {
                    float: left;
                    line-height: 38px;
                    font-size: 14px;
                    b {
                      font-weight: bold;
                    }
                  }
                  input {
                    float: left;
                    width: 30px;
                    height: 30px;
                    margin: 0 3px;
                    line-height: 30px;
                    font-size: 14px;
                    text-align: center;
                    border: 1px solid #ccc;
                    padding: 3px;
                  }
                  a {
                    float: left;
                    height: 27px;
                    margin-left: 10px;
                    font-size: 14px;
                    line-height: 27px;
                    display: inline-block;
                    border-radius: 2px;
                    background: #f7f7f7;
                    text-align: center;
                    text-decoration: none;
                    cursor: pointer;
                    border: 1px solid #ddd;
                    padding: 4px 13px 5px;
                    color: #666;
                    //background-color: #f7f7f7;
                    background-repeat: repeat-x;
                  }
                }
              }
            }

            .goods-content .goods-one {
              margin-left: -14px;
              width: 1240px;
              display: flex;
              flex-wrap: wrap;
              .goods-item {
                background: #fff;
                height: 300px;
                padding: 20px 0;
                width: 234px;
                margin-left: 14px;
                margin-top: 14px;
              }
            }

            .goods-two .goods-item-sm {
              height: 300px;
              width: 234px;
              margin-left: 14px;
              a {
                display: flex;
                justify-content: space-between;
                height: 143px;
                background: #fff;
                padding: 30px 20px 30px 30px;
                margin-bottom: 14px;
                img {
                  width: 80px;
                  height: 80px;
                  margin: 0;
                }
              }
            }

            .goods-two .goods-item-bg {
              height: 300px;
              width: 496px;
              display: flex;
              a {
                display: block;
                width: 234px;
                background: #fff;
                margin-left: 14px;
                padding: 20px 0;
              }

              .brick-more {
                display: flex;
                justify-content: space-between;
                height: 143px;
                padding: 30px 20px 30px 30px;
                img {
                  width: 80px;
                  height: 80px;
                  margin: 0;
                }
              }
            }

            .goods-content {
              text-align: center;
              a {
                display: block;
                img {
                  width: 160px;
                  height: 160px;
                  margin: 0 auto 18px;
                }

                .name {
                  font-size: 14px;
                  font-weight: 400;
                  color: #333;
                  margin: 0 10px 2px;
                }

                .desc {
                  display: block;
                  margin: 0 10px 10px;
                  height: 18px;
                  font-size: 12px;
                  color: #b0b0b0;
                }

                .thumbs {
                  width: 100%;
                  height: 37px;
                  overflow: hidden;
                  .thumb-list {
                    margin: 0;
                    padding: 0;
                    font-size: 0;
                    li {
                      display: inline-block;
                      width: 34px;
                      height: 34px;
                      margin: 0 4px;
                      border: 1px solid #e0e0e0;
                      cursor: pointer;
                      -webkit-transition: border-color 0.2s linear;
                      transition: border-color 0.2s linear;
                    }
                    li:hover {
                      border-color: #ff6700;
                    }
                    img {
                      width: 34px;
                      height: 34px;
                    }
                  }
                }

                .price {
                  margin: 0 10px 14px;
                  font-size: 14px;
                  color: #ff6700;
                  del {
                    color: #b0b0b0;
                    margin-left: 8px;
                  }
                }
              }
            }
          }
        }
      }
      .JD_show {
        width: 1226px;
        margin: 0 auto;
        text-align: center;
        a {
          text-decoration: none;
          display: block;
          width: 100%;
          border-top: 1px solid #ddd;
          font-size: 14px;
          > span {
            display: block;
            padding-top: 5px;
            width: 291px;
            height: 25px;
            border: 1px solid #ddd;
            border-top: 0;
            margin: -2px auto;
            background: #fff;
            color: #666;
          }
        }
        a:hover {
          border-top: 1px solid #e23a3a;
          > span {
            border: 1px solid #e23a3a;
            border-top: 0;
            color: #e23a3a;
          }
        }
      }
    }
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>