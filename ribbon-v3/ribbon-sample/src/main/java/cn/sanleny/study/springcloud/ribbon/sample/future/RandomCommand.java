package cn.sanleny.study.springcloud.ribbon.sample.future;

import java.util.Random;

/**
 * @Author: sanleny
 * @Date: 2019-04-02
 * @Description: cn.sanleny.study.springcloud.ribbon.sample.future
 * @Version: 1.0
 */
public class RandomCommand implements Command<String> {

    private Random random = new Random();

    public String run() {
        //超时降级 休眠时间
        int sleepTime = random.nextInt(200);
        System.out.println("休眠时间:"+sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "正常返回结果.....";
    }

    public String fallBack() {
        return "出错了，我要降级";
    }
}
