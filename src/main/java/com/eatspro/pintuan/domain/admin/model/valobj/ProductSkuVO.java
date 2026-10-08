package com.eatspro.pintuan.domain.admin.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* 参与拼团商品原始价格查询
*/
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSkuVO {
    private Long goodsId;
    private String goodsName;
    private Integer originalPrice;
    private Long activityId;
}
