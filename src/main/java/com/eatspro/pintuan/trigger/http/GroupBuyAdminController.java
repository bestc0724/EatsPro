package com.eatspro.pintuan.trigger.http;

import com.eatspro.common.result.Result;
import com.eatspro.pintuan.domain.admin.IGroupBuyAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 拼团管理 - 管理端API
 * 路径: /api/v1/gbm/admin/*
 * 前端通过 nginx /api/gbm/ → Gateway → 本服务
 */
@RestController
@RequestMapping("/api/v1/gbm/admin")
@Slf4j
public class GroupBuyAdminController {

    @Resource
    private IGroupBuyAdminService groupBuyAdminService;

    /**
     * 拼团活动分页查询
     */
    @GetMapping("/activities")
    public Result<Map<String, Object>> listActivities(
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return Result.success(groupBuyAdminService.listActivities(status, page, pageSize));
    }

    /**
     * 创建拼团活动
     */
    @PostMapping("/activity")
    public Result<?> createActivity(@RequestBody Map<String, Object> params) {
        groupBuyAdminService.createActivity(params);
        return Result.success();
    }

    /**
     * 切换活动状态
     */
    @PutMapping("/activity/{activityId}/status")
    public Result<?> toggleActivity(@PathVariable Long activityId, @RequestBody Map<String, Object> params) {
        Integer status = (Integer) params.get("status");
        groupBuyAdminService.updateActivityStatus(activityId, status);
        return Result.success();
    }

    /**
     * 拼团订单分页查询
     */
    @GetMapping("/orders")
    public Result<Map<String, Object>> listOrders(
            @RequestParam(required = false) String teamId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return Result.success(groupBuyAdminService.listGroupOrders(teamId, page, pageSize));
    }

    /**
     * 团成员列表
     */
    @GetMapping("/team/{teamId}/members")
    public Result<?> teamMembers(@PathVariable String teamId) {
        return Result.success(groupBuyAdminService.getTeamMembers(teamId));
    }

    /**
     * 折扣配置列表
     */
    @GetMapping("/discounts")
    public Result<?> discountList() {
        return Result.success(groupBuyAdminService.listDiscounts());
    }

    /**
     * 拼团概览统计
     */
    @GetMapping("/statistics")
    public Result<Map<String, Object>> statistics() {
        return Result.success(groupBuyAdminService.getStatistics());
    }

    /**
     * 所有可用拼团商品列表（从 SKU + sc_sku_activity 联表查询）
     */
    @GetMapping("/products")
    public Result<List<Map<String, Object>>> listProducts() {
        return Result.success(groupBuyAdminService.listProducts());
    }
}
