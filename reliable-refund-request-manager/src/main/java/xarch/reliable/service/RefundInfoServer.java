package xarch.reliable.service;

import java.util.Map;

public interface RefundInfoServer {
	

	public String setRefundInfo(String out_refund_no, Map<String, Object> refunddata);

	public Map<String, Object> getRefundInfo(String out_refund_no);

}
