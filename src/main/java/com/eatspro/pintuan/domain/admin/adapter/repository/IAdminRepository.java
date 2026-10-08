package com.eatspro.pintuan.domain.admin.adapter.repository;

import java.util.List;
import java.util.Map;

public interface IAdminRepository {
    /**
     * 分页查询活动列表
     */
    Map<String, Object> listActivities(Integer status, int offset, int pageSize);


    /**
     * 创建活动
     */
    void createActivity(Map<String, Object> params);


    /**
     * 分页查询拼团订单
     */
    Map<String, Object> listGroupOrders(String teamId, int offset, int pageSize);



    /**
     * 统计概览数据
     */
    Map<String, Object> getStatistics();

    /**
     * 查询商品列表
     */
    List<Map<String, Object>> listProducts();
}
