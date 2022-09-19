package org.dog.scheduling.controller;

import org.dog.scheduling.model.RespBean;
import org.dog.scheduling.model.SysJob;
import org.dog.scheduling.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/9/17 18:34
 * @Description:
 */
@RestController
@RequestMapping("/jobs")
public class SysJobController {

    @Autowired
    SysJobService sysJobService;

    @GetMapping("/")
    public List<SysJob> getAllJobs() {
        return sysJobService.getAllJobs();
    }

    @PostMapping("/")
    public RespBean addJob(@RequestBody SysJob sysJob) {
        Boolean flag = sysJobService.addJob(sysJob);
        if (flag) {
            return RespBean.ok("作业添加成功");
        }
        return RespBean.error("作业重复，添加失败");
    }

    @PutMapping("/")
    public RespBean updateJob(@RequestBody SysJob sysJob) {
        Boolean flag = sysJobService.updateJob(sysJob);
        if (flag) {
            return RespBean.ok("作业更新成功");
        }
        return RespBean.error("作业更新失败");
    }

    @DeleteMapping("/")
    public RespBean deleteJobs(Integer id) {
        Boolean flag = sysJobService.deleteJobsById(id);
        if (flag) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
