package com.huobi.perhack.source;

import java.util.List;

import com.perhack.quant.Kline;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class HuobiSourceDataExtractor {

	//cont={"ch":"market.BTC_CW.kline.1min","ts":1578451755925,"tick":{"id":1578451740,"mrid":37231739342,"open":8358.77,"close":8355.6,"high":8358.77,"low":8355.31,"amount":7.7066961436138261309626983043537242768,"vol":644,"count":23}}

	public static Kline getKLineData(String cont){
		
    	JSONObject tick = JSONUtil.parseObj(cont).getJSONObject("tick");
    	long respTs = JSONUtil.parseObj(cont).getLong("ts");
    	
		Kline kline = new Kline();
		kline.setTs(tick.getLong("id"));	//ID，实际对应当前时间的分钟
		kline.setRespTs(respTs);			//响应生成时间，目前看和K线时间一致
		kline.setOpen(tick.getDouble("open"));
		kline.setClose(tick.getDouble("close"));
		kline.setHigh(tick.getDouble("high"));
		kline.setLow(tick.getDouble("low"));
		kline.setAmount(tick.getDouble("amount"));	//成交量(币), 即 sum(每一笔成交量(张)*单张合约面值/该笔成交价)
		kline.setVol(tick.getDouble("vol"));	//成交量张数
		kline.setCount(tick.getDouble("count"));	//成交笔数
		
		
//    	logger.info(DateUtil.date(respTs).toString());
//		logger.info(DateUtil.date(kline.getTs()*1000).toString());
//		logger.info(kline.toString());

		return kline;
		
	}
	
}
