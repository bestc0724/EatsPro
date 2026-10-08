package com.eatspro.pintuan.domain.trade.service.settlement.filter;

import com.eatspro.pintuan.domain.trade.adapter.repository.ITradeRepository;
import com.eatspro.pintuan.domain.trade.model.entity.MarketPayOrderEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeSettlementRuleCommandEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradeSettlementRuleFilterBackEntity;
import com.eatspro.pintuan.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import com.eatspro.pintuan.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import com.eatspro.pintuan.types.design.AbstractChainHandler;
import com.eatspro.pintuan.types.enums.ResponseCode;
import com.eatspro.pintuan.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 外部交易单号过滤；外部交易单号是否为退单
 */
@Slf4j
@Service
public class OutTradeNoRuleFilter extends AbstractChainHandler<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeSettlementRuleFilterBackEntity handle(TradeSettlementRuleCommandEntity requestParameter, TradeSettlementRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("结算规则过滤-外部单号校验{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());

        // 查询拼团信息
        MarketPayOrderEntity marketPayOrderEntity = repository.queryMarketPayOrderEntityByOutTradeNo(requestParameter.getUserId(), requestParameter.getOutTradeNo());

        if (null == marketPayOrderEntity || TradeOrderStatusEnumVO.CLOSE.equals(marketPayOrderEntity.getTradeOrderStatusEnumVO())) {
            log.error("不存在的外部交易单号或用户已退单，不需要做支付订单结算:{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
            throw new AppException(ResponseCode.E0104);
        }

        dynamicContext.setMarketPayOrderEntity(marketPayOrderEntity);

        return null;
    }

}
