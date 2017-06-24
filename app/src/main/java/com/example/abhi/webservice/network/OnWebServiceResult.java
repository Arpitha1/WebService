package com.example.abhi.webservice.network;

import com.example.abhi.webservice.utils.CommonUtilities;

public interface OnWebServiceResult {
	public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}
