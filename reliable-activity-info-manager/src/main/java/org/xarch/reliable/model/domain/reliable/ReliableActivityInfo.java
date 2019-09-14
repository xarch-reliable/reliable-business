package org.xarch.reliable.model.domain.reliable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 	活动信息
 * 
 * @author wancy
 *
 */
@Entity
public class ReliableActivityInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // [必须的传入参数，使用自动生成策略]
	@GeneratedValue // [自动生成策略，自动编号排序]
	private Long id;

	// 活动ID
	@JsonProperty("actid")
	private String actid;

	//创建者openid
	@JsonProperty("creator_openid")
	private String creatorOpenid;

	//创建者头像
	@JsonProperty("creator_headimgurl")
	private String creatorHeadimgurl;
	
	//创建者昵称
	@JsonProperty("creator_nickname")
	private String creatorNickname;
	
	//创建日期
	@JsonProperty("create_date")
	private String createDate;

	//活动主题
	@JsonProperty("theme")
	private String theme;

	//活动日期
	@JsonProperty("date")
	private String date;

	//开始时间
	@JsonProperty("time")
	private String time;

	//活动地点
	@JsonProperty("location")
	private String location;

	//保证红包
	@JsonProperty("baozhenghb")
	private String baozhenghb;

	//结算方法
	@JsonProperty("clear_method")
	private String clearMethod;

	//签到方法
	@JsonProperty("check_method")
	private String[] checkMethod;

	//活动详情
	@JsonProperty("details")
	private String details;

	//是否结算
	@JsonProperty("clear")
	private String clear;
	
	//预订单
	@JsonProperty("pay_order")
	private String payOrder;
	
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

	public String getClear() {
		return clear;
	}

	public void setClear(String clear) {
		this.clear = clear;
	}

	public String getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
	}
}
