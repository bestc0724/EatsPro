package com.eatspro.pintuan.infrastructure.dao;

import com.eatspro.pintuan.infrastructure.dao.po.CrowdTags;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人群标签
 */
@Mapper
public interface ICrowdTagsMapper {

    void updateCrowdTagsStatistics(CrowdTags crowdTagsReq);

}
