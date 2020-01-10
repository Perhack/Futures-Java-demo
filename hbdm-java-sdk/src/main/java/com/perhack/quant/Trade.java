package com.perhack.quant;

public class Trade {

	private long ts;
	
	private long respTs;
	
	private double price;
	
	private long amount;
	
	private String contractType;
		
	private String direction;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Trade [ts=" + ts + ", respTs=" + respTs + ", price=" + price + ", amount=" + amount + ", contractType="
				+ contractType + ", direction=" + direction + "]";
	}
	
	
}
