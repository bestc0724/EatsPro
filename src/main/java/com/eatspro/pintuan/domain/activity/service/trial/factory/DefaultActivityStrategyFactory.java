package com.eatspro.pintuan.domain.activity.service.trial.factory;

import com.eatspro.pintuan.domain.activity.model.entity.MarketProductEntity;
import com.eatspro.pintuan.domain.activity.model.entity.TrialBalanceEntity;
import com.eatspro.pintuan.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.eatspro.pintuan.domain.activity.model.valobj.SkuVO;
import com.eatspro.pintuan.domain.activity.service.trial.node.RootNode;
import com.eatspro.pintuan.types.design.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 活动策略工厂
 */
@Service
public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        // 拼团活动营销配置值对象
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;
        // 商品信息
        private SkuVO skuVO;
        // 折扣金额
        private BigDecimal deductionPrice;
        // 支付金额
        private BigDecimal payPrice;
        // 活动可见性限制（默认可见，由TagScope限制时改为false）
        private boolean visible = true;
        // 活动可参与性（默认可参与，由TagScope限制时改为false）
        private boolean enable = true;
    }

}
