package org.dog.scheduling.config;

import java.util.concurrent.ScheduledFuture;

/**
 * @Author Odin
 * @Date 2022/9/16 16:33
 * @Description:
 */


public class ScheduledTask {

  /**
   * 定时任务执行结果
   */
  volatile ScheduledFuture<?> future;

  public void cancel() {
    ScheduledFuture<?> future = this.future;
    if (future != null) {
      future.cancel(true);
    }
  }

}
