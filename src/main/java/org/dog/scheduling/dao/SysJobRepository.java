package org.dog.scheduling.dao;

import org.dog.scheduling.model.SysJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/9/17 11:44
 * @Description:
 */

public interface SysJobRepository extends JpaRepository<SysJob, Integer> {

    List<SysJob> findAllByJobStatus(Integer status);

}
