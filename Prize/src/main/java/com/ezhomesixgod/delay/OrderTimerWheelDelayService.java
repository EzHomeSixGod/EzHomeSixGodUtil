package com.ezhomesixgod.delay;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @author renyang
 * @description Netty 时间片
 *
 * 优点:效率高,任务触发时间延迟时间比delayQueue低，代码复杂度比delayQueue低。
 * 缺点:(1)服务器重启后，数据全部消失，怕宕机
 *    (2)集群扩展相当麻烦
 *    (3)因为内存条件限制的原因，比如下单未付款的订单数太多，那么很容易就出现OOM异常
 * @createTime 2018-12-17 10:08
 */
public class OrderTimerWheelDelayService {

    static class MyTimerTask implements TimerTask {
        boolean flag;
        public MyTimerTask(boolean flag){
            this.flag = flag;
        }
        public void run(Timeout timeout) throws Exception {
            // TODO Auto-generated method stub
            System.out.println("要去数据库删除订单了。。。。");
            this.flag =false;
        }
    }
    public static void main(String[] argv) {
        MyTimerTask timerTask = new MyTimerTask(true);
        Timer timer = new HashedWheelTimer();
        timer.newTimeout(timerTask, 5, TimeUnit.SECONDS);
        int i = 1;
        while(timerTask.flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(i+"秒过去了");
            i++;
        }
    }
}
