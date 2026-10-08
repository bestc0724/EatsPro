package com.eatspro.pintuan.api;

import com.eatspro.pintuan.api.response.Response;

/**
 * DCC 动态配置中心
 */
public interface IDCCService {

    Response<Boolean> updateConfig(String key, String value);

}
