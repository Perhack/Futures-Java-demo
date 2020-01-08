package com.huobi.perhack.waterfall;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.huobi.api.response.market.MarketHistoryKlineResponse;
import com.huobi.api.service.market.MarketAPIServiceImpl;
import com.huobi.perhack.db.HuobiSqliteHelper;
import com.huobi.perhack.source.HuobiSourceDataExtractor;
import com.huobi.wss.event.MarketDepthSubResponse;
import com.huobi.wss.event.MarketDetailSubResponse;
import com.huobi.wss.event.MarketKLineSubResponse;
import com.huobi.wss.handle.WssMarketHandle;
import com.perhack.quant.Kline;
import com.perhack.util.SqliteHelper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.db.Db;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class Waterfall {

    private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private MarketAPIServiceImpl mService = new MarketAPIServiceImpl();
	
    private String URL = "wss://www.btcgateway.pro/ws";//合约站行情请求以及订阅地址
//    private String URL = "wss://www.hbdm.com/ws";//合约站行情请求以及订阅地址

    
    WssMarketHandle wssMarketHandle = new WssMarketHandle(URL);
	
    public void test1() throws URISyntaxException, InterruptedException {
        List<String> channels = Lists.newArrayList();
        channels.add("market.BTC_CW.kline.1min");
        channels.add("market.BTC_CW.kline.5min");
        channels.add("market.BTC_CW.kline.15min");
        channels.add("market.BTC_CW.kline.30min");
        channels.add("market.BTC_CW.kline.60min");
        channels.add("market.BTC_CW.kline.4hour");
        channels.add("market.BTC_CW.kline.1day");
        channels.add("market.BTC_CW.kline.1week");
        channels.add("market.BTC_CW.kline.1mon");



        wssMarketHandle.sub(channels, response -> {
            logger.info("kLineEvent用户收到的数据===============:{}", JSON.toJSON(response));
            Long currentTimeMillis = System.currentTimeMillis();
            MarketKLineSubResponse event = JSON.parseObject(response, MarketKLineSubResponse.class);
            logger.info("kLineEvent的ts为：{},当前的时间戳为：{},时间间隔为：{}毫秒", event.getTs(), currentTimeMillis, currentTimeMillis - event.getTs());
        });
        Thread.sleep(Integer.MAX_VALUE);


    }
	
    
    public void test2() throws URISyntaxException, InterruptedException {
        List<String> channels = Lists.newArrayList();
        //channels.add("market.BTC_CW.depth.step0");
        channels.add("market.BTC_CW.depth.step11");
        wssMarketHandle.sub(channels, response -> {
            logger.info("depthEvent用户收到的数据===============:{}", JSON.toJSON(response));
            Long currentTimeMillis = System.currentTimeMillis();
            MarketDepthSubResponse event = JSON.parseObject(response, MarketDepthSubResponse.class);
            logger.info("depthEvent的ts为：{},当前的时间戳为：{},时间间隔为：{}毫秒", event.getTs(), currentTimeMillis, currentTimeMillis - event.getTs());
            logger.info("数据大小为:{}", event.getTick().getAsks().size());
        });
        Thread.sleep(Integer.MAX_VALUE);

    }
    
    public void test3() throws URISyntaxException, InterruptedException {
        List<String> channels = Lists.newArrayList();
        channels.add("market.BTC_CW.detail");
        channels.add("market.BTC_NW.detail");
        wssMarketHandle.sub(channels, response -> {
            logger.info("detailEvent用户收到的数据===============:{}", JSON.toJSON(response));
            Long currentTimeMillis = System.currentTimeMillis();
            MarketDetailSubResponse event = JSON.parseObject(response, MarketDetailSubResponse.class);
            logger.info("detailEvent的ts为：{},当前的时间戳为：{},时间间隔为：{}毫秒", event.getTs(), currentTimeMillis, currentTimeMillis - event.getTs());
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
    
    public void test4() throws URISyntaxException, InterruptedException {
        List<String> channels = Lists.newArrayList();
        channels.add("market.BTC_CW.trade.detail");
        channels.add("market.BTC_NW.trade.detail");
        wssMarketHandle.sub(channels, response -> {
            logger.info("订阅TradeDetail数据用户收到的数据===============:{}", JSON.toJSON(response));
            Long currentTimeMillis = System.currentTimeMillis();
            MarketDetailSubResponse event = JSON.parseObject(response, MarketDetailSubResponse.class);
            logger.info("detailEvent的ts为：{},当前的时间戳为：{},时间间隔为：{}毫秒", event.getTs(), currentTimeMillis, currentTimeMillis - event.getTs());
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
    
	public void run() throws URISyntaxException{
		
        List<String> channels = Lists.newArrayList();
        channels.add("market.BTC_CW.kline.1min");
//        channels.add("market.BTC_CW.kline.5min");
//        channels.add("market.BTC_CW.kline.15min");
//        channels.add("market.BTC_CW.kline.30min");
//        channels.add("market.BTC_CW.kline.60min");
//        channels.add("market.BTC_CW.kline.4hour");
//        channels.add("market.BTC_CW.kline.1day");
//        channels.add("market.BTC_CW.kline.1week");
//        channels.add("market.BTC_CW.kline.1mon");

        wssMarketHandle.sub(channels, response -> {
        	Kline kline = HuobiSourceDataExtractor.getKLineData(response);
        	
        	
        	HuobiSqliteHelper mHuobiSqliteHelper = new HuobiSqliteHelper();
//        	mHuobiSqliteHelper.create1minklineTable();
        	mHuobiSqliteHelper.insertInto1minkline(kline);;
        	

        });
		
	}
    
	
	
	public static void main(String []args) throws URISyntaxException{
		Waterfall mWaterfall = new Waterfall();
		mWaterfall.run();
	}
	
}
