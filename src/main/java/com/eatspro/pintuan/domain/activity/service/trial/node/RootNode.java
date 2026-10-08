package com.eatspro.pintuan.domain.activity.service.trial.node;

import com.eatspro.pintuan.domain.activity.model.entity.MarketProductEntity;
import com.eatspro.pintuan.domain.activity.model.entity.TrialBalanceEntity;
import com.eatspro.pintuan.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.eatspro.pintuan.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.eatspro.pintuan.types.enums.ResponseCode;
import com.eatspro.pintuan.types.exception.AppException;
import com.eatspro.pintuan.types.design.StrategyHandler;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 根节点
 */
@Slf4j
@Service
public class RootNode extends AbstractGroupBuyMarketSupport {

    @Resource
    private SwitchNode switchNode;

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-RootNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));
        // 参数判断
        if (StringUtils.isBlank(requestParameter.getUserId()) || StringUtils.isBlank(requestParameter.getGoodsId()) ||
                StringUtils.isBlank(requestParameter.getSource()) || StringUtils.isBlank(requestParameter.getChannel())) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return switchNode;
    }

}
