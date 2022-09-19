package org.dog.scheduling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Author Odin
 * @Date 2022/9/16 15:48
 * @Description:
 */

@Configuration
public class SchedulingConfig {

  @Bean
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

    taskScheduler.setPoolSize(4);
    taskScheduler.setRemoveOnCancelPolicy(true);
    taskScheduler.setThreadNamePrefix("TaskSchedulerThreadPool-");
    return taskScheduler;
  }
}
