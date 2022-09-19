package org.dog.scheduling.config;

import org.dog.scheduling.model.SysJob;
import org.dog.scheduling.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/9/17 11:46
 * @Description:
 */

@Component
public class InitTask implements CommandLineRunner {

    @Autowired
    CronTaskRegistrar cronTaskRegistrar;

    @Autowired
    SysJobService sysJobService;

    @Override
    public void run(String... args) throws Exception {
        List<SysJob> list = sysJobService.getJobsByStatus(1);
        for (SysJob sysJob : list) {
            cronTaskRegistrar.addCronTask(new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams()), sysJob.getCronExpression());
        }
    }
}
