package org.xarch.reliable.model.domain.reliable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ReliableActivityInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // [必须的传入参数，使用自动生成策略]
	@GeneratedValue // [自动生成策略，自动编号排序]
	private Long id;
	
	@JsonProperty("actid")
	private String actid;

	@JsonProperty("creator")
	private String creator;

	@JsonProperty("create_time")
	private String createTime;

	@JsonProperty("theme")
	private String theme;

	@JsonProperty("start_time")
	private String startTime;

	@JsonProperty("localtion")
	private String localtion;

	@JsonProperty("baozhenghb")
	private String baozhenghb;

	@JsonProperty("clear_method")
	private String clearMethod;

	@JsonProperty("check_method")
	private String checkMethod;

	@JsonProperty("details")
	private String details;

	@JsonProperty("status")
	private String status;

	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getLocaltion() {
		return localtion;
	}

	public void setLocaltion(String localtion) {
		this.localtion = localtion;
	}

	public String getBaozhenghb() {
		return baozhenghb;
	}

	public void setBaozhenghb(String baozhenghb) {
		this.baozhenghb = baozhenghb;
	}

	public String getClearMethod() {
		return clearMethod;
	}

	public void setClearMethod(String clearMethod) {
		this.clearMethod = clearMethod;
	}

	public String getCheckMethod() {
		return checkMethod;
	}

	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
