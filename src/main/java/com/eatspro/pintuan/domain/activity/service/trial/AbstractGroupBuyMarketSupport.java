package com.eatspro.pintuan.domain.activity.service.trial;

import com.eatspro.pintuan.domain.activity.adapter.repository.IActivityRepository;
import com.eatspro.pintuan.domain.activity.model.entity.MarketProductEntity;
import com.eatspro.pintuan.domain.activity.model.entity.TrialBalanceEntity;
import com.eatspro.pintuan.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.eatspro.pintuan.types.design.AbstractStrategyRouter;
import com.eatspro.pintuan.types.design.StrategyHandler;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 抽象的拼团营销支撑类
 */
public abstract class AbstractGroupBuyMarketSupport
        extends AbstractStrategyRouter<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    protected long timeout = 5000;

    @Resource
    protected IActivityRepository repository;

    protected final StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> defaultStrategyHandler = (requestParameter, dynamicContext) -> null;

    /**
     * 执行具体业务逻辑（子类实现）
     */
    protected abstract TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception;

    /**
     * 路由到下一个节点
     */
    protected TrialBalanceEntity router(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return super.apply(requestParameter, dynamicContext);
    }

    /**
     * 多线程并行处理（可选覆盖）
     */
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 缺省的方法
    }

    /**
     * 流程：多线程加载数据 → 执行业务逻辑(doApply)
     * doApply 内部通过 router() 获取下一节点并调用其 apply()
     */
    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        // 1. 多线程并行加载数据
        multiThread(requestParameter, dynamicContext);
        // 2. 执行当前节点的业务逻辑
        return doApply(requestParameter, dynamicContext);
    }

}
