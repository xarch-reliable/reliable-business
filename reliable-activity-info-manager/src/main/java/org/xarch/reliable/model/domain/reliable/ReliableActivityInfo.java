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

	@JsonProperty("creator_openid")
	private String creatorOpenid;

	@JsonProperty("creator_headimgurl")
	private String creatorHeadimgurl;
	
	@JsonProperty("creator_nickname")
	private String creatorNickname;
	
	@JsonProperty("create_date")
	private String createDate;

	@JsonProperty("theme")
	private String theme;

	@JsonProperty("date")
	private String date;

	@JsonProperty("time")
	private String time;

	@JsonProperty("location")
	private String location;

	@JsonProperty("baozhenghb")
	private String baozhenghb;

	@JsonProperty("clear_method")
	private String clearMethod;

	@JsonProperty("check_method")
	private String[] checkMethod;

	@JsonProperty("details")
	private String details;

	@JsonProperty("status")
	private String status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public String[] getCheckMethod() {
		return checkMethod;
	}

	public void setCheckMethod(String[] checkMethod) {
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

	public String getCreatorOpenid() {
		return creatorOpenid;
	}

	public void setCreatorOpenid(String creatorOpenid) {
		this.creatorOpenid = creatorOpenid;
	}

	public String getCreatorHeadimgurl() {
		return creatorHeadimgurl;
	}

	public void setCreatorHeadimgurl(String creatorHeadimgurl) {
		this.creatorHeadimgurl = creatorHeadimgurl;
	}

	public String getCreatorNickname() {
		return creatorNickname;
	}

	public void setCreatorNickname(String creatorNickname) {
		this.creatorNickname = creatorNickname;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
