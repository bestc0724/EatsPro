package com.eatspro.pintuan.types.design;

/**
 * 抽象策略路由器 - 替代 xfg-wrench 的 AbstractMultiThreadStrategyRouter
 *
 * @param <T> 请求参数类型
 * @param <D> 动态上下文类型
 * @param <R> 返回结果类型
 */
public abstract class AbstractStrategyRouter<T, D, R> implements StrategyHandler<T, D, R> {

    /**
     * 获取下一个处理器
     */
    public abstract StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

    /**
     * 默认实现：先处理，再路由到下一个
     * 这里是理解难点，router方法是调用的这里的apply方法；
     * 而实现类中调用的apply方法是这个类子类中重写的apply方法，重写的apply方法调用了dApply方法；
     * doApply方法中实现处理逻辑，然后调用了router方法，router方法就是这里的apply方法；
     * get方法是一直到实现类中才实现的，然后router方法调用，到下一个责任链节点
     * 画图实现这个责任链的继承、调用逻辑
     */
    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        StrategyHandler<T, D, R> next = get(requestParameter, dynamicContext);
        if (next != null) {
            return next.apply(requestParameter, dynamicContext);
        }
        return null;
    }
}
