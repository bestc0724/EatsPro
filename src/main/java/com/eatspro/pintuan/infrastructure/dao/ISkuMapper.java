package com.eatspro.pintuan.infrastructure.dao;

import com.eatspro.pintuan.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 商品查询
 */
@Mapper
public interface ISkuMapper {

    Sku querySkuByGoodsId(String goodsId);

    List<Map<String, Object>> listProducts();
}
