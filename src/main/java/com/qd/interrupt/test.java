package com.qd.interrupt;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止模式
 */

@Slf4j(topic = "c.test")
public class test {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt=new TwoPhaseTermination();
        tpt.start();
        Thread.sleep(3500);
        tpt.stop();
    }

}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination{
     private Thread monitor;
     //启动监控线程
     public void start(){
      monitor= new Thread(()->{
          while (true){
              //获取当前线程
              Thread current = Thread.currentThread();
              //判断线程是否被打断
              if (current.isInterrupted()){
                  log.debug("料理后事");
                  break;
              }
              try {
                  Thread.sleep(1000); //情况1 被打断
                  log.debug("执行监控记录"); // 情况2 被打断
              } catch (InterruptedException e) {
                  e.printStackTrace();
                  //sleep 被打断后会清除打断标记 需重新标记
                  current.interrupt();
              }
          }

      });
       monitor.start();
     }

     //停止监控线程
     public void stop(){
         monitor.interrupt();
     }
}
