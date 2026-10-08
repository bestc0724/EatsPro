package com.eatspro.pintuan.api;

import com.eatspro.pintuan.api.dto.GoodsMarketRequestDTO;
import com.eatspro.pintuan.api.dto.GoodsMarketResponseDTO;
import com.eatspro.pintuan.api.response.Response;

/**
 * 营销首页服务接口
 */
public interface IMarketIndexService {

    /**
     * 查询拼团营销配置
     *
     * @param goodsMarketRequestDTO 营销商品信息
     * @return 营销配置信息
     */
    Response<GoodsMarketResponseDTO> queryGroupBuyMarketConfig(GoodsMarketRequestDTO goodsMarketRequestDTO);

}
