package com.eatspro.pintuan.infrastructure.dao;

import com.eatspro.pintuan.infrastructure.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 渠道商品活动配置关联表Dao
 */
@Mapper
public interface ISCSkuActivityMapper {

    SCSkuActivity querySCSkuActivityBySCGoodsId(SCSkuActivity scSkuActivity);

}
