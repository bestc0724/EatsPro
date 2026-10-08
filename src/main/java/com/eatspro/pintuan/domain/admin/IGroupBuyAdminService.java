package com.eatspro.pintuan.domain.admin;

import java.util.List;
import java.util.Map;

/**
 * 拼团管理服务接口
 */
public interface IGroupBuyAdminService {

    Map<String, Object> listActivities(Integer status, int page, int pageSize);

    void createActivity(Map<String, Object> params);

    void updateActivityStatus(Long activityId, Integer status);

    Map<String, Object> listGroupOrders(String teamId, int page, int pageSize);

    List<Map<String, Object>> getTeamMembers(String teamId);

    List<Map<String, Object>> listDiscounts();

    //
    Map<String, Object> getStatistics();

    List<Map<String, Object>> listProducts();
}
