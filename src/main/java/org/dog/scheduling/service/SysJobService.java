package org.dog.scheduling.service;

import org.dog.scheduling.config.CronTaskRegistrar;
import org.dog.scheduling.config.SchedulingRunnable;
import org.dog.scheduling.dao.SysJobRepository;
import org.dog.scheduling.model.SysJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/9/17 13:27
 * @Description:
 */

@Service
public class SysJobService {

    @Autowired
    SysJobRepository sysJobRepository;

    @Autowired
    CronTaskRegistrar cronTaskRegistrar;

    public List<SysJob> getAllJobs() {
        return sysJobRepository.findAll();
    }

    public Boolean addJob(SysJob sysJob) {
        /**
         * 首先查询所有的定时任务
         */
        List<SysJob> all = sysJobRepository.findAll();
        for (SysJob job : all) {
            if (job.equals(sysJob)) {
                //作业重复，添加失败
                return false;
            }
        }
        /**
         * 添加
         */
        SysJob sj = sysJobRepository.save(sysJob);
        if (sj != null) {
            //添加成功，如果新加的job是开启状态，就顺便开启
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            if (sj.getJobStatus() == 1) {
                cronTaskRegistrar.addCronTask(schedulingRunnable, sysJob.getCronExpression());
            }
            //添加成功
            return true;
        }
        return false;
    }

    public List<SysJob> getJobsByStatus(int status) {
        return sysJobRepository.findAllByJobStatus(status);
    }

    public Boolean updateJob(SysJob sysJob) {
        SysJob job = sysJobRepository.saveAndFlush(sysJob);
        if (job != null) {
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            if (sysJob.getJobStatus() == 1) {
                cronTaskRegistrar.addCronTask(schedulingRunnable, sysJob.getCronExpression());
            } else {
                cronTaskRegistrar.removeCronTask(schedulingRunnable);
            }
            return true;
        }
        return false;
    }

    public Boolean deleteJobsById(Integer id) {
        SysJob sysJob = sysJobRepository.findById(id).get();
        SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
        cronTaskRegistrar.removeCronTask(schedulingRunnable);
        sysJobRepository.delete(sysJob);
        return true;
    }
}
