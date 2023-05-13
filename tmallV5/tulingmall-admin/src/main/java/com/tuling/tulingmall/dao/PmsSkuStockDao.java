package com.tuling.tulingmall.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tuling.tulingmall.model.PmsSkuStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 自定义商品sku库存Dao
 * Created on 2018/4/26.
 */
@DS("goods")
@Repository
public interface PmsSkuStockDao {
    /**
     * 批量插入操作
     */
    int insertList(@Param("list")List<PmsSkuStock> skuStockList);

    /**
     * 批量插入或替换操作
     */
    int replaceList(@Param("list")List<PmsSkuStock> skuStockList);
}
