package com.perhack.quant;

public class Position {
	
	private long ts;
	
	private long respTs;
	
	private String asks;
	
	private String bids;
	
	private String contractType;
	
	private String channel;
	
	private String step;
	
	private String test;

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public long getRespTs() {
		return respTs;
	}

	public void setRespTs(long respTs) {
		this.respTs = respTs;
	}

	public String getAsks() {
		return asks;
	}

	public void setAsks(String asks) {
		this.asks = asks;
	}

	public String getBids() {
		return bids;
	}

	public void setBids(String bids) {
		this.bids = bids;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "Position [ts=" + ts + ", respTs=" + respTs + ", asks=" + asks + ", bids=" + bids + ", contractType="
				+ contractType + ", channel=" + channel + ", step=" + step + "]";
	}
}
