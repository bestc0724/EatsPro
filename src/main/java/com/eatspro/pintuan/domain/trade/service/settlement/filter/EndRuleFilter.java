package com.eatspro.pintuan.domain.trade.service.settlement.filter;

import com.eatspro.pintuan.domain.trade.model.entity.GroupBuyTeamEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeSettlementRuleCommandEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeSettlementRuleFilterBackEntity;
import com.eatspro.pintuan.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import com.eatspro.pintuan.types.design.AbstractChainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 结束节点
 */
@Slf4j
@Service
public class EndRuleFilter extends AbstractChainHandler<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> {

    @Override
    public TradeSettlementRuleFilterBackEntity handle(TradeSettlementRuleCommandEntity requestParameter, TradeSettlementRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("结算规则过滤-结束节点{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());

        // 获取上下文对象
        GroupBuyTeamEntity groupBuyTeamEntity = dynamicContext.getGroupBuyTeamEntity();

        // 返回封装数据
        return TradeSettlementRuleFilterBackEntity.builder()
                .teamId(groupBuyTeamEntity.getTeamId())
                .activityId(groupBuyTeamEntity.getActivityId())
                .targetCount(groupBuyTeamEntity.getTargetCount())
                .completeCount(groupBuyTeamEntity.getCompleteCount())
                .lockCount(groupBuyTeamEntity.getLockCount())
                .status(groupBuyTeamEntity.getStatus())
                .validStartTime(groupBuyTeamEntity.getValidStartTime())
                .validEndTime(groupBuyTeamEntity.getValidEndTime())
                .notifyConfigVO(groupBuyTeamEntity.getNotifyConfigVO())
                .build();
    }

}
