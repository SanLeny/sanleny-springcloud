package cn.sanleny.study.springcloud.ribbon.sample.command;

import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: sanleny
 * @Date: 2019-04-02
 * @Description: cn.sanleny.study.springcloud.ribbon.sample.command
 * @Version: 1.0
 */
public class CommandForIndex extends HystrixCommand<Object> {

    private RestTemplate template;
    private String id;

    public CommandForIndex(String id,RestTemplate template) {
        // java代码配置， 只针对这个命令
        super(Setter
             // 必填项。指定命令分组名，主要意义是用于统计
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("Client-Group"))
             //依赖名称（如果是服务调用，这里就写具体的接口名， 如果是自定义的操作，就自己命令），默认是command实现类的类名。 熔断配置就是根据这个名称
            .andCommandKey(HystrixCommandKey.Factory.asKey("ConsumerController"))
             //线程池命名，默认是 HystrixCommandGroupKey 的名称。 线程池配置就是根据这个名称
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("Client-ThreadPool"))
            // command 熔断相关参数配置
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    // 配置隔离方式：默认采用线程池隔离。还有一种信号量隔离方式,
                    // .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                    // 超时时间500毫秒，默认超时时间为1秒
                    .withExecutionTimeoutInMilliseconds(100)
            )
            // 设置线程池参数
            .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                    // 线程池大小
                    .withCoreSize(1)
                    //允许最大的缓冲区大小
//                  .withMaxQueueSize(2)
            )
        );
        this.template = template;
        this.id = id;
    }


    protected String run() {
        System.out.println("###command start #######" + Thread.currentThread().toString());
        String result =  template.getForObject("http://client/user?id="+id+"",String.class);
        System.out.println("###command end #######" + Thread.currentThread().getName() + ">>>>>>> 执行结果:" + result);
        return result;
    }

    @Override
    protected Object getFallback() {
        System.out.println("###降级啦####" + Thread.currentThread().toString());
        return "出錯了，我降級了";
        //降级的处理：
        //1.返回一个固定的值
        //2.去查询缓存
        //3.调用一个备用接口
    }
}
