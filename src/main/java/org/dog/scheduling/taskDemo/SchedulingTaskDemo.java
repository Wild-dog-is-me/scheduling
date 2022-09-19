package org.dog.scheduling.taskDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author Odin
 * @Date 2022/9/17 17:50
 * @Description:
 */

@Component("schedulingTaskDemo")
public class SchedulingTaskDemo {

    public void taskWithParams(String params) {
        System.out.println("执行有参任务" + params);
    }

    public void taskWithOutParams() {
        System.out.println("执行无参任务");
    }

}
