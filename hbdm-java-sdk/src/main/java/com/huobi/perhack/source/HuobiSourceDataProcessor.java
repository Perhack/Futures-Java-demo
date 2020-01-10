package com.huobi.perhack.source;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huobi.perhack.db.HuobiSqliteHelper;
import com.perhack.quant.Kline;
import com.perhack.quant.Position;
import com.perhack.quant.Trade;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class HuobiSourceDataProcessor {

	//cont={"ch":"market.BTC_CW.kline.1min","ts":1578451755925,"tick":{"id":1578451740,"mrid":37231739342,"open":8358.77,"close":8355.6,"high":8358.77,"low":8355.31,"amount":7.7066961436138261309626983043537242768,"vol":644,"count":23}}

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private HuobiSqliteHelper mHuobiSqliteHelper = new HuobiSqliteHelper();

	
	public void getAndStoreData(String response) {
		// TODO Auto-generated method stub
		
				
		String[] chs = JSONUtil.parseObj(response).getStr("ch").split("\\.");
		
		String channel = chs[2];
		String contractType = chs[1];
    	
    	JSONObject tick = JSONUtil.parseObj(response).getJSONObject("tick");
    	long respTs = JSONUtil.parseObj(response).getLong("ts");
    	
    	switch (channel) {
		case "kline":
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
			kline.setContractType(contractType);
			kline.setChannel(channel);
			kline.setPeriod(chs[3]);
			mHuobiSqliteHelper.insertIntoKline(kline);
			break;
		case "depth":
			Position position = new Position();
			position.setChannel(channel);
			position.setAsks(tick.getStr("asks"));
			position.setBids(tick.getStr("bids"));
			position.setRespTs(respTs);
			position.setTs(tick.getLong("id"));
			position.setContractType(contractType);
			position.setStep(chs[3]);
			position.setTest(response);
			mHuobiSqliteHelper.insertIntoPosition(position);
			break;
		case "trade":
			Trade trade = new Trade();
			trade.setRespTs(respTs);
			trade.setTs(tick.getLong("id"));
			trade.setContractType(contractType);
			
			JSONArray tradeList = tick.getJSONArray("data");

			for(Object obj:tradeList) {
				JSONObject jsonObject = JSONUtil.parseObj(obj);
				trade.setAmount(jsonObject.getLong("amount"));
				trade.setPrice(jsonObject.getDouble("price"));
				trade.setDirection(jsonObject.getStr("direction"));
				mHuobiSqliteHelper.insertIntoTrade(trade);
			}
			break;
			
		default:
			break;
		}
		
		return ;
	}
	
}
