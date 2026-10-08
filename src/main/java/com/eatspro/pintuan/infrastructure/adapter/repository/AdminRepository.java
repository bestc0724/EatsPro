package com.eatspro.pintuan.infrastructure.adapter.repository;

import com.eatspro.pintuan.domain.admin.adapter.repository.IAdminRepository;
import com.eatspro.pintuan.infrastructure.dao.IGroupBuyActivityMapper;
import com.eatspro.pintuan.infrastructure.dao.IGroupBuyOrderListMapper;
import com.eatspro.pintuan.infrastructure.dao.IGroupBuyOrderMapper;
import com.eatspro.pintuan.infrastructure.dao.ISkuMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminRepository implements IAdminRepository {
    @Resource
    private IGroupBuyActivityMapper groupBuyActivityMapper;

    @Resource
    private IGroupBuyOrderMapper groupBuyOrderMapper;

    @Resource
    private IGroupBuyOrderListMapper groupBuyOrderListMapper;

    @Resource
    private ISkuMapper skuMapper;

    @Override
    public Map<String, Object> listActivities(Integer status, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Map<String, Object>> records =
                groupBuyActivityMapper.listActivitiesByStatus(status, offset, pageSize);
        int total = groupBuyActivityMapper.countActivitiesByStatus(status);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", total);
        result.put("records", records);
        return result;
    }


    @Override
    public void createActivity(Map<String, Object> params) {
        long activityId = System.currentTimeMillis() / 1000;
        Object productType = params.get("productType");
        if (productType == null) {
            params.put("productType", 0);
        }
        groupBuyActivityMapper.createGroupBuyActivity(activityId, params);

    }


    @Override
    public Map<String, Object> listGroupOrders(String teamId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Map<String, Object>> records =
                groupBuyActivityMapper.listGroupOrders(teamId, offset, pageSize);
        int total = groupBuyActivityMapper.countGroupOrders(teamId);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", total);
        result.put("records", records);
        return result;
    }

    @Override
    public Map<String, Object> getStatistics() {
        int activityCount = groupBuyActivityMapper.countActivities();
        int activeTeamCount = groupBuyOrderMapper.countActiveTeam();
        int completeTeamCount = groupBuyOrderMapper.countCompleteTeam();
        int totalOrders = groupBuyOrderListMapper.queryAllOrders();
        Map<String, Object> result = new HashMap<>();
        result.put("activityCount", activityCount);
        result.put("activeTeamCount", activeTeamCount);
        result.put("completeTeamCount", completeTeamCount);
        result.put("totalOrders", totalOrders);
        return result;
    }

    @Override
    public List<Map<String, Object>> listProducts() {
        return skuMapper.listProducts();
    }
}
