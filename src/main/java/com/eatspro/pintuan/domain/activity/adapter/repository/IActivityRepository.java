package com.eatspro.pintuan.domain.activity.adapter.repository;

import com.eatspro.pintuan.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.eatspro.pintuan.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.eatspro.pintuan.domain.activity.model.valobj.SCSkuActivityVO;
import com.eatspro.pintuan.domain.activity.model.valobj.SkuVO;
import com.eatspro.pintuan.domain.activity.model.valobj.TeamStatisticVO;

import java.util.List;

/**
 * 活动仓储
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId);

    boolean isTagCrowdRange(String tagId, String userId);

    boolean downgradeSwitch();

    boolean cutRange(String userId);

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByOwner(Long activityId, String userId, Integer ownerCount);

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByRandom(Long activityId, String userId, Integer randomCount);

    TeamStatisticVO queryTeamStatisticByActivityId(Long activityId);

    /**
     * 查询所有可用的拼团商品（SKU 已关联到活动的）
     */
    List<java.util.Map<String, Object>> queryAllAvailableGroupBuyProducts();

}
