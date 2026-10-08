package com.eatspro.pintuan.api;

import com.eatspro.pintuan.api.dto.LockMarketPayOrderRequestDTO;
import com.eatspro.pintuan.api.dto.LockMarketPayOrderResponseDTO;
import com.eatspro.pintuan.api.dto.RefundMarketPayOrderRequestDTO;
import com.eatspro.pintuan.api.dto.RefundMarketPayOrderResponseDTO;
import com.eatspro.pintuan.api.dto.SettlementMarketPayOrderRequestDTO;
import com.eatspro.pintuan.api.dto.SettlementMarketPayOrderResponseDTO;
import com.eatspro.pintuan.api.response.Response;

/**
 * 营销交易服务接口
 */
public interface IMarketTradeService {

    /**
     * 营销锁单
     *
     * @param requestDTO 锁单商品信息
     * @return 锁单结果信息
     */
    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO requestDTO);

    /**
     * 营销结算
     *
     * @param requestDTO 结算商品信息
     * @return 结算结果信息
     */
    Response<SettlementMarketPayOrderResponseDTO> settlementMarketPayOrder(SettlementMarketPayOrderRequestDTO requestDTO);

    /**
     * 营销拼团退单
     *
     * @param requestDTO 退单请求信息
     * @return 退单结果信息
     */
    Response<RefundMarketPayOrderResponseDTO> refundMarketPayOrder(RefundMarketPayOrderRequestDTO requestDTO);

}
