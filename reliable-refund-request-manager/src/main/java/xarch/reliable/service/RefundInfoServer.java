package xarch.reliable.service;

import java.util.Map;

public interface RefundInfoServer {
	

	public String setRefundInfo(String payid, Map<String, Object> refunddata);

	public Map<String, Object> getRefundInfo(String payid);

}
