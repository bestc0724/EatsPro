package com.eatspro.pintuan.infrastructure.dao;

import com.eatspro.pintuan.infrastructure.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人群标签明细
 */
@Mapper
public interface ICrowdTagsDetailMapper {

    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);

}
