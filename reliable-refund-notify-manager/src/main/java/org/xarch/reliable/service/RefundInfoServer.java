package org.xarch.reliable.service;

import java.util.Map;

public interface RefundInfoServer {

	public Map<String, Object> setRefundInfo(String out_refund_no, Map<String, Object> notifydata);

	public Map<String, Object> getRefundInfo(String out_refund_no);

}
