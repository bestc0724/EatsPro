package com.eatspro.pintuan.domain.trade.service.refund.factory;

import com.eatspro.pintuan.domain.trade.model.entity.*;
import com.eatspro.pintuan.domain.trade.service.refund.filter.DataNodeFilter;
import com.eatspro.pintuan.domain.trade.service.refund.filter.RefundOrderNodeFilter;
import com.eatspro.pintuan.domain.trade.service.refund.filter.UniqueRefundNodeFilter;
import com.eatspro.pintuan.types.design.AbstractChainHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 交易退单工程
 *
 * 2025/7/30 09:58
 */
@Slf4j
@Service
public class TradeRefundRuleFilterFactory {

    @Bean("tradeRefundRuleFilter")
    public AbstractChainHandler<TradeRefundCommandEntity, TradeRefundRuleFilterFactory.DynamicContext, TradeRefundBehaviorEntity> tradeRefundRuleFilter(
            DataNodeFilter dataNodeFilter,
            UniqueRefundNodeFilter uniqueRefundNodeFilter,
            RefundOrderNodeFilter refundOrderNodeFilter) {

        // 手动组装责任链
        dataNodeFilter.setNext(uniqueRefundNodeFilter);
        uniqueRefundNodeFilter.setNext(refundOrderNodeFilter);

        return dataNodeFilter;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {

        private MarketPayOrderEntity marketPayOrderEntity;

        private GroupBuyTeamEntity groupBuyTeamEntity;

    }

}
