package com.eatspro.pintuan.domain.trade.service.lock.factory;

import com.eatspro.pintuan.domain.trade.model.entity.GroupBuyActivityEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.eatspro.pintuan.domain.trade.service.lock.filter.ActivityUsabilityRuleFilter;
import com.eatspro.pintuan.domain.trade.service.lock.filter.TeamStockOccupyRuleFilter;
import com.eatspro.pintuan.domain.trade.service.lock.filter.UserTakeLimitRuleFilter;
import com.eatspro.pintuan.types.design.AbstractChainHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 交易规则过滤工厂
 */
@Slf4j
@Service
public class TradeLockRuleFilterFactory {

    private static final String teamStockKey = "group_buy_market_team_stock_key_";

    @Bean("tradeRuleFilter")
    public AbstractChainHandler<TradeLockRuleCommandEntity, DynamicContext, TradeLockRuleFilterBackEntity> tradeRuleFilter(
            ActivityUsabilityRuleFilter activityUsabilityRuleFilter,
            UserTakeLimitRuleFilter userTakeLimitRuleFilter,
            TeamStockOccupyRuleFilter teamStockOccupyRuleFilter) {

        // 手动组装责任链
        activityUsabilityRuleFilter.setNext(userTakeLimitRuleFilter);
        userTakeLimitRuleFilter.setNext(teamStockOccupyRuleFilter);

        return activityUsabilityRuleFilter;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {

        private GroupBuyActivityEntity groupBuyActivity;

        private Integer userTakeOrderCount;

        public String generateTeamStockKey(String teamId) {
            if (StringUtils.isBlank(teamId)) return null;
            return TradeLockRuleFilterFactory.generateTeamStockKey(groupBuyActivity.getActivityId(), teamId);
        }

        public String generateRecoveryTeamStockKey(String teamId) {
            if (StringUtils.isBlank(teamId)) return null;
            return TradeLockRuleFilterFactory.generateRecoveryTeamStockKey(groupBuyActivity.getActivityId(), teamId);
        }

    }

    public static String generateTeamStockKey(Long activityId, String teamId){
        return teamStockKey + activityId + "_" + teamId;
    }

    public static String generateRecoveryTeamStockKey(Long activityId, String teamId) {
        return teamStockKey + activityId + "_" + teamId + "_recovery";
    }

}
