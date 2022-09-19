package org.dog.scheduling.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Odin
 * @Date 2022/9/16 19:16
 * @Description:核心配置
 */

@Component
public class CronTaskRegistrar implements DisposableBean {

  /**
   * 变量中保存着所有的定时任务
   */
  private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

  @Resource
  TaskScheduler taskScheduler;

  public TaskScheduler taskScheduler() {
    return this.taskScheduler;
  }

  /**
   * 添加一个定时任务
   * @param task
   * @param cronException
   */
  public void addCronTask(Runnable task, String cronException) {
    addCronTask(new CronTask(task,cronException));
  }

  private void addCronTask(CronTask cronTask) {
    if (cronTask != null) {
      Runnable runnable = cronTask.getRunnable();
      if (this.scheduledTasks.containsKey(runnable)) {
        /**
         * 说明要添加的定时任务已经存在
         * 先把已经存在的定时任务移除，然后在添加新的定时任务
         */
        removeCronTask(runnable);
      }
      /**
       * 添加一个新的定时任务
       */
      this.scheduledTasks.put(runnable, scheduleCronTask(cronTask));
    }
  }

  private ScheduledTask scheduleCronTask(CronTask cronTask) {
    ScheduledTask scheduledTask = new ScheduledTask();
    scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
    return scheduledTask;
  }

  public void removeCronTask(Runnable runnable) {
    /**
     * 从map集合中删除
     */
    ScheduledTask task = this.scheduledTasks.remove(runnable);
    /**
     * 取消已经开始的定时任务
     */
    if (task != null) {
      task.cancel();
    }
  }

  @Override
  public void destroy() throws Exception {
    /**
     * 让所有定时任务停止执行
     */
    for (ScheduledTask task : this.scheduledTasks.values()) {
      task.cancel();
    }
    /**
     * 清空集合
     */
    this.scheduledTasks.clear();
  }
}
