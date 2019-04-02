package cn.sanleny.study.springcloud.ribbon.sample.future;

import java.util.concurrent.*;

/**
 * 自定义 TimeOutFallBack
 * 1：修改为请求服务端。
 * 2:定义熔断机制，你可以根据失败数量来确定是否开启熔断
 * 3:定义一个定时任务的线程池，然后开始按照固定时间去判断熔断器的状态，然后确定是否打开
 * 4:定义一个队列去完成半开启状态。
 * @Author: sanleny
 * @Date: 2019-04-02
 * @Description: cn.sanleny.study.springcloud.ribbon.sample.future
 * @Version: 1.0
 */
public class TimeOutFallBack {



    public static void main(String args[]){

        final RandomCommand command = new RandomCommand();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            public String call() throws Exception {
                return command.run();
            }
        });

        String result;
        try {
            //如果在100毫秒内返回，我们就返回一个正常结果，不然，返回降级方法
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            //返回降级方法.
            result =command.fallBack();
        }
        System.out.println("执行的结果是:"+result);
        executorService.shutdown();

    }
}
