package org.xarch.reliable.sevice;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BillinfoServer {
	
	public Map<String, Object> setBillinfo(String openid, Map<String, Object> billdata);

	public Map<String, Object> getBillinfo(String openid);
	
	
	

}
