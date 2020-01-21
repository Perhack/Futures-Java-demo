package com.perhack.quant;

public class Detail {

	private long ts;
	
	private long respTs;
	
	private double open;
	
	private double close;
	
	private double high;
	
	private double low;
	
	private double vol;
	
	private double count;
	
	private double amount;
	
	private String contractType;
	
	private String channel;
	
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

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getVol() {
		return vol;
	}

	public void setVol(double vol) {
		this.vol = vol;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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



	@Override
	public String toString() {
		return "Kline [ts=" + ts + ", respTs=" + respTs + ", open=" + open + ", close=" + close + ", high=" + high
				+ ", low=" + low + ", vol=" + vol + ", count=" + count + ", amount=" + amount + ", contractType="
				+ contractType + ", quotesType=" + channel  + "]";
	}
	
	
	
}
