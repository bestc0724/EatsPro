package com.eatspro.pintuan.domain.trade.service.lock.filter;

import com.eatspro.pintuan.domain.trade.adapter.repository.ITradeRepository;
import com.eatspro.pintuan.domain.trade.model.entity.GroupBuyActivityEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.eatspro.pintuan.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import com.eatspro.pintuan.types.design.AbstractChainHandler;
import com.eatspro.pintuan.types.enums.ResponseCode;
import com.eatspro.pintuan.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户参与限制，规则过滤
 */
@Slf4j
@Service
public class UserTakeLimitRuleFilter extends AbstractChainHandler<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeLockRuleFilterBackEntity handle(TradeLockRuleCommandEntity requestParameter, TradeLockRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-用户参与次数校验{} activityId:{}", requestParameter.getUserId(), requestParameter.getActivityId());

        GroupBuyActivityEntity groupBuyActivity = dynamicContext.getGroupBuyActivity();

        // 查询用户在一个拼团活动上参与的次数
        Integer count = repository.queryOrderCountByActivityId(requestParameter.getActivityId(), requestParameter.getUserId());

        if (null != groupBuyActivity.getTakeLimitCount() && count >= groupBuyActivity.getTakeLimitCount()) {
            log.info("用户参与次数校验，已达可参与上限 activityId:{}", requestParameter.getActivityId());
            throw new AppException(ResponseCode.E0103);
        }

        dynamicContext.setUserTakeOrderCount(count);

        // 走到下一个责任链节点
        return null;
    }

}
