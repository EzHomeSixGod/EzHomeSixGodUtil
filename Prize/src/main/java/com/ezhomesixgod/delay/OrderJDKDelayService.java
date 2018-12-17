package com.ezhomesixgod.delay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author renyang
 * @description 延时任务 JDK方式
 *
 * 优点:效率高,任务触发时间延迟低。
 * 缺点:(1)服务器重启后，数据全部消失，怕宕机
 *    (2)集群扩展相当麻烦
 *    (3)因为内存条件限制的原因，比如下单未付款的订单数太多，那么很容易就出现OOM异常
 *    (4)代码复杂度较高
 *
 * @createTime 2018-12-17 10:02
 */
public class OrderJDKDelayService implements Delayed {

    private long timeout;

    private String orderId;

    OrderJDKDelayService(String orderId, long timeout) {
        this.orderId = orderId;
        this.timeout = timeout + System.nanoTime();
    }
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this)
            return 0;
        OrderJDKDelayService t = (OrderJDKDelayService) other;
        long d = (getDelay(TimeUnit.NANOSECONDS) - t
                .getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    void print() {
        System.out.println(orderId+"编号的订单要删除啦。。。。");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<String> list = new ArrayList<String>();
        list.add("00000001");
        list.add("00000002");
        list.add("00000003");
        list.add("00000004");
        list.add("00000005");
        DelayQueue<OrderJDKDelayService> queue = new DelayQueue<OrderJDKDelayService>();
        long start = System.currentTimeMillis();
        for(int i = 0;i<5;i++){
            //延迟三秒取出
            queue.put(new OrderJDKDelayService(list.get(i),
                    TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS)));
            try {
                queue.take().print();
                System.out.println("After " +
                        (System.currentTimeMillis()-start) + " MilliSeconds");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
