package com.eatspro.pintuan.domain.trade.service.settlement.factory;

import com.eatspro.pintuan.domain.trade.model.entity.GroupBuyTeamEntity;
import com.eatspro.pintuan.domain.trade.model.entity.MarketPayOrderEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeSettlementRuleCommandEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeSettlementRuleFilterBackEntity;
import com.eatspro.pintuan.domain.trade.service.settlement.filter.EndRuleFilter;
import com.eatspro.pintuan.domain.trade.service.settlement.filter.OutTradeNoRuleFilter;
import com.eatspro.pintuan.domain.trade.service.settlement.filter.SCRuleFilter;
import com.eatspro.pintuan.domain.trade.service.settlement.filter.SettableRuleFilter;
import com.eatspro.pintuan.types.design.AbstractChainHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 交易结算规则过滤工厂
 */
@Slf4j
@Service
public class TradeSettlementRuleFilterFactory {

    @Bean("tradeSettlementRuleFilter")
    public AbstractChainHandler<TradeSettlementRuleCommandEntity,
            DynamicContext, TradeSettlementRuleFilterBackEntity> tradeSettlementRuleFilter(
            SCRuleFilter scRuleFilter,
            OutTradeNoRuleFilter outTradeNoRuleFilter,
            SettableRuleFilter settableRuleFilter,
            EndRuleFilter endRuleFilter) {

        // 手动组装责任链
        scRuleFilter.setNext(outTradeNoRuleFilter);
        outTradeNoRuleFilter.setNext(settableRuleFilter);
        settableRuleFilter.setNext(endRuleFilter);

        return scRuleFilter;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        // 订单营销实体对象
        private MarketPayOrderEntity marketPayOrderEntity;
        // 拼团组队实体对象
        private GroupBuyTeamEntity groupBuyTeamEntity;
    }

}
