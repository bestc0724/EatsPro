package com.eatspro.pintuan.infrastructure.dao;

import com.eatspro.pintuan.infrastructure.dao.po.GroupBuyActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 拼团活动Dao
 */
@Mapper
public interface IGroupBuyActivityMapper {

    List<GroupBuyActivity> queryGroupBuyActivityList();

    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity groupBuyActivityReq);

    GroupBuyActivity queryValidGroupBuyActivityId(Long activityId);

    GroupBuyActivity queryGroupBuyActivityByActivityId(Long activityId);


    List<Map<String, Object>> listActivitiesByStatus(Integer status, int offset, int pageSize);

    int countActivitiesByStatus(Integer status);

    void createGroupBuyActivity(long activityId, Map<String, Object> params);

    void updateActivityStatus(Long activityId, Integer status);

    List<Map<String, Object>> listGroupOrders(String teamId, int offset, int pageSize);

    int countGroupOrders(String teamId);

    int countActivities();
}
