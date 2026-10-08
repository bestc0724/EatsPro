package com.eatspro.pintuan.types.design;

/**
 * 抽象责任链处理器 - 替代 xfg-wrench 的 BusinessLinkedList / LinkArmory
 *
 * @param <T> 请求参数类型
 * @param <D> 动态上下文类型
 * @param <R> 返回结果类型
 */
public abstract class AbstractChainHandler<T, D, R> {

    private AbstractChainHandler<T, D, R> next;

    /**
     * 执行过滤器链
     */
    public R doFilter(T request, D context) throws Exception {
        R result = handle(request, context);
        if (next != null && result == null) {
            return next.doFilter(request, context);
        }
        return result;
    }

    /**
     * 具体的处理逻辑 - 子类实现
     */
    protected abstract R handle(T request, D context) throws Exception;

    /**
     * 设置下一个处理器
     */
    public void setNext(AbstractChainHandler<T, D, R> next) {
        this.next = next;
    }

    /**
     * 获取下一个处理器
     */
    public AbstractChainHandler<T, D, R> getNext() {
        return next;
    }
}
