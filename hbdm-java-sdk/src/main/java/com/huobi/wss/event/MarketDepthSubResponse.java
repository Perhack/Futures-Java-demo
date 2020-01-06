package com.huobi.wss.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class MarketDepthSubResponse {

    /**
     * ch : market.BTC_CQ.depth.step0
     * ts : 1489474082831
     * tick : {"mrid":269073229,"id":1539843937,"bids":[[9999.9101,1],[9992.3089,2]],"asks":[[10010.98,10],[10011.39,15]],"ts":1539843937417,"version":1539843937,"ch":"market.BTC_CQ.depth.step0"}
     */

    private String ch;
    private Long ts;
    private TickBean tick;

    @Data
    @AllArgsConstructor
    public static class TickBean {
        /**
         * mrid : 269073229
         * id : 1539843937
         * bids : [[9999.9101,1],[9992.3089,2]]
         * asks : [[10010.98,10],[10011.39,15]]
         * ts : 1539843937417
         * version : 1539843937
         * ch : market.BTC_CQ.depth.step0
         */

        private Long mrid;
        private Long id;
        private Long ts;
        private Long version;
        private String ch;
        private List<List<BigDecimal>> bids;
        private List<List<BigDecimal>> asks;
		public Long getMrid() {
			return mrid;
		}
		public void setMrid(Long mrid) {
			this.mrid = mrid;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getTs() {
			return ts;
		}
		public void setTs(Long ts) {
			this.ts = ts;
		}
		public Long getVersion() {
			return version;
		}
		public void setVersion(Long version) {
			this.version = version;
		}
		public String getCh() {
			return ch;
		}
		public void setCh(String ch) {
			this.ch = ch;
		}
		public List<List<BigDecimal>> getBids() {
			return bids;
		}
		public void setBids(List<List<BigDecimal>> bids) {
			this.bids = bids;
		}
		public List<List<BigDecimal>> getAsks() {
			return asks;
		}
		public void setAsks(List<List<BigDecimal>> asks) {
			this.asks = asks;
		}
        
    }

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public TickBean getTick() {
		return tick;
	}

	public void setTick(TickBean tick) {
		this.tick = tick;
	}
    
    
}
