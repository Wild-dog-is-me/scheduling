package org.dog.scheduling.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @Author Odin
 * @Date 2022/9/15 20:30
 * @Description:
 */

@Entity(name = "o_sys_job")
public class SysJob {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer jobId;

  private String beanName;

  private String methodName;

  private String methodParams;

  private String cronExpression;

  private Integer jobStatus;

  private String remark;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SysJob sysJob = (SysJob) o;
    return Objects.equals(beanName, sysJob.beanName) &&
            Objects.equals(methodName, sysJob.methodName) &&
            Objects.equals(methodParams, sysJob.methodParams) &&
            Objects.equals(cronExpression, sysJob.cronExpression);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beanName, methodName, methodParams, cronExpression);
  }

  public Integer getJobId() {
    return jobId;
  }

  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }

  public String getBeanName() {
    return beanName;
  }

  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public String getMethodParams() {
    return methodParams;
  }

  public void setMethodParams(String methodParams) {
    this.methodParams = methodParams;
  }

  public String getCronExpression() {
    return cronExpression;
  }

  public void setCronExpression(String cronExpression) {
    this.cronExpression = cronExpression;
  }

  public Integer getJobStatus() {
    return jobStatus;
  }

  public void setJobStatus(Integer jobStatus) {
    this.jobStatus = jobStatus;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}

