package com.eatspro.pintuan.domain.admin;

import com.eatspro.pintuan.domain.admin.adapter.repository.IAdminRepository;
import com.eatspro.pintuan.infrastructure.dao.IGroupBuyActivityMapper;
import com.eatspro.pintuan.infrastructure.dao.IGroupBuyDiscountMapper;
import com.eatspro.pintuan.infrastructure.dao.IGroupBuyOrderListMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GroupBuyAdminServiceImpl implements IGroupBuyAdminService {

    @Resource
    private IAdminRepository adminRepository;

    @Resource
    private IGroupBuyActivityMapper groupBuyActivityMapper;

    @Resource
    private IGroupBuyOrderListMapper groupBuyOrderListMapper;

    @Resource
    private IGroupBuyDiscountMapper groupBuyDiscountMapper;

    @Override
    public Map<String, Object> listActivities(Integer status, int page, int pageSize) {
        return adminRepository.listActivities(status, page, pageSize);
    }

    @Override
    public void createActivity(Map<String, Object> params) {
        adminRepository.createActivity(params);
        log.info("Created group-buy activity: {}",
                params.get("activityName"));
    }

    @Override
    public void updateActivityStatus(Long activityId, Integer status) {
        groupBuyActivityMapper.updateActivityStatus(activityId, status);
        log.info("Updated activity {} status to {}", activityId, status);
    }

    @Override
    public Map<String, Object> listGroupOrders(String teamId, int page, int pageSize) {
        return adminRepository.listGroupOrders(teamId, page, pageSize);
    }

    @Override
    public List<Map<String, Object>> getTeamMembers(String teamId) {
        return groupBuyOrderListMapper.getTeamMembers(teamId);
    }

    @Override
    public List<Map<String, Object>> listDiscounts() {
        return groupBuyDiscountMapper.listDiscounts();
    }

    @Override
    public Map<String, Object> getStatistics() {
        return adminRepository.getStatistics();
    }

    @Override
    public List<Map<String, Object>> listProducts() {
        return adminRepository.listProducts();
    }
}
