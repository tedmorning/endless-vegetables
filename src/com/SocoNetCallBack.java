package com;

public class SocoNetCallBack extends NetCallBack {

	public int getLang() {
		return NetConfig.lang;
	}

	public String getLoginUrl() {
		return NetConfig.LOGIN_URL;
	}

	public String getVersion() {
		return NetConfig.getVersion();
	}

	public void debug(String msg) {
		System.out.print(msg);
	}
}
