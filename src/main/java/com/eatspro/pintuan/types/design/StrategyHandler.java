package com.eatspro.pintuan.types.design;

/**
 * 策略处理器接口 - 替代 xfg-wrench 的 StrategyHandler
 *
 * @param <T> 请求参数类型
 * @param <D> 动态上下文类型
 * @param <R> 返回结果类型
 */
public interface StrategyHandler<T, D, R> {

    /**
     * 处理策略
     *
     * @param requestParameter 请求参数
     * @param dynamicContext   动态上下文
     * @return 处理结果
     * @throws Exception 处理异常
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;
}
