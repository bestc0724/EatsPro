package com.eatspro.pintuan.domain.activity.service.trial.node;

import com.eatspro.pintuan.domain.activity.model.entity.MarketProductEntity;
import com.eatspro.pintuan.domain.activity.model.entity.TrialBalanceEntity;
import com.eatspro.pintuan.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.eatspro.pintuan.domain.activity.model.valobj.SkuVO;
import com.eatspro.pintuan.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.eatspro.pintuan.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.eatspro.pintuan.types.design.StrategyHandler;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 正常结束节点
 */
@Slf4j
@Service
public class EndNode extends AbstractGroupBuyMarketSupport {

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-EndNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        // 拼团活动配置
        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = dynamicContext.getGroupBuyActivityDiscountVO();

        // 商品信息
        SkuVO skuVO = dynamicContext.getSkuVO();

        // 折扣金额
        BigDecimal deductionPrice = dynamicContext.getDeductionPrice();
        // 支付金额
        BigDecimal payPrice = dynamicContext.getPayPrice();

        // 返回空结果
        return TrialBalanceEntity.builder()
                .goodsId(skuVO.getGoodsId())
                .goodsName(skuVO.getGoodsName())
                .originalPrice(skuVO.getOriginalPrice())
                .deductionPrice(deductionPrice)
                .payPrice(payPrice)
                .targetCount(groupBuyActivityDiscountVO.getTarget())
                .startTime(groupBuyActivityDiscountVO.getStartTime())
                .endTime(groupBuyActivityDiscountVO.getEndTime())
                .isVisible(dynamicContext.isVisible())
                .isEnable(dynamicContext.isEnable())
                .groupBuyActivityDiscountVO(groupBuyActivityDiscountVO)
                .build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }

}
