package org.xarch.reliable.config.event;

public enum ReliableDataType {

	getActinfoListByOpenid,	// 通过openid获取所有参加活动的信息
	getActidListByOpenid,	// 通过openid获取当前用户所有参与活动的actid
	getOpenidListByActid,	// 通过actid获取当前活动所有参与者的openid
	getActinfoByActid,		// 通过actid获取当前活动的信息
	setOpenid2ActidList,
	setActid2OpenidList,
	setActinfoByBody,
	setactclear,
	getactclear,
	setactstatus,
	addactpartnumber,
	checkOpenid2ActidList,
	checkActid2OpenidList,
	setOrderRequest,
	getOrderRequest,
	setOrderResponse,
	getOrderResponse,
	setRefundRequest,
	getRefundRequest,
	setRefundResponse,
	getRefundResponse,
	
}
