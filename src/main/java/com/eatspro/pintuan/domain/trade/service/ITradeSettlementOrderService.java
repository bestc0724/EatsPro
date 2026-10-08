package com.eatspro.pintuan.domain.trade.service;

import com.eatspro.pintuan.domain.trade.model.entity.NotifyTaskEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradePaySettlementEntity;
import com.eatspro.pintuan.domain.trade.model.entity.TradePaySuccessEntity;

import java.util.Map;

/**
 * 拼团交易结算服务接口
 */
public interface ITradeSettlementOrderService {

    /**
     * 营销结算
     *
     * @param tradePaySuccessEntity 交易支付订单实体对象
     * @return 交易结算订单实体
     */
    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccessEntity) throws Exception;

}
