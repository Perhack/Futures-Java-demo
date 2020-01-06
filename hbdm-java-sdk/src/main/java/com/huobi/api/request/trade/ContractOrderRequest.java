package com.huobi.api.request.trade;

import com.huobi.api.enums.DirectionEnum;
import com.huobi.api.enums.OffsetEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 备注
 * 如果contract_code填了值，那就按照contract_code去下单，如果contract_code没有填值，则按照symbol+contract_type去下单。
 * <p>
 * 对手价下单price价格参数不用传，对手价下单价格是买一和卖一价,optimal_5：最优5档、optimal_10：最优10档、optimal_20：最优20档下单price价格参数不用传，"limit":限价，"post_only":只做maker单 需要传价格。
 * <p>
 * 开平方向
 * 开多：买入开多(direction用buy、offset用open)
 * <p>
 * 平多：卖出平多(direction用sell、offset用close)
 * <p>
 * 开空：卖出开空(direction用sell、offset用open)
 * <p>
 * 平空：买入平空(direction用buy、offset用close)
 */
@Data
@AllArgsConstructor
@Builder
public class ContractOrderRequest {
    private String symbol;//"BTC","ETH"...
    private String contractType;//合约类型 ("this_week":当周 "next_week":下周 "quarter":季度)
    private String contractCode;//BTC180914
    private Long clientOrderId;//客户自己填写和维护，必须为数字
    private BigDecimal price;//价格
    private Long volume;//委托数量(张)
    private DirectionEnum direction;//"buy":买 "sell":卖
    private OffsetEnum offset;//"open":开 "close":平
    private Integer leverRate;//杠杆倍数[“开仓”若有10倍多单，就不能再下20倍多单]
    private String orderPriceType;//订单报价类型 "limit":限价 "opponent":对手价 "post_only":只做maker单,post only下单只受用户持仓数量限制,optimal_5：最优5档、optimal_10：最优10档、optimal_20：最优20档
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public Long getClientOrderId() {
		return clientOrderId;
	}
	public void setClientOrderId(Long clientOrderId) {
		this.clientOrderId = clientOrderId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public DirectionEnum getDirection() {
		return direction;
	}
	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}
	public OffsetEnum getOffset() {
		return offset;
	}
	public void setOffset(OffsetEnum offset) {
		this.offset = offset;
	}
	public Integer getLeverRate() {
		return leverRate;
	}
	public void setLeverRate(Integer leverRate) {
		this.leverRate = leverRate;
	}
	public String getOrderPriceType() {
		return orderPriceType;
	}
	public void setOrderPriceType(String orderPriceType) {
		this.orderPriceType = orderPriceType;
	}
    
}
