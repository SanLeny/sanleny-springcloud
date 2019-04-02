package cn.sanleny.study.springcloud.ribbon.sample.future;

/**
 * @Author: sanleny
 * @Date: 2019-04-02
 * @Description: cn.sanleny.study.springcloud.ribbon.sample.future
 * @Version: 1.0
 */
public interface Command<T> {

    T run();

    T fallBack();

}
