package com.eatspro.pintuan.infrastructure.dao;

import com.eatspro.pintuan.infrastructure.dao.po.GroupBuyDiscount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 折扣配置Dao
 */
@Mapper
public interface IGroupBuyDiscountMapper {

    List<GroupBuyDiscount> queryGroupBuyDiscountList();

    GroupBuyDiscount queryGroupBuyActivityDiscountByDiscountId(String discountId);

    List<Map<String, Object>> listDiscounts();
}
