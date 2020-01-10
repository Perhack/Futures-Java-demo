package com.huobi.perhack.waterfall;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.huobi.api.service.market.MarketAPIServiceImpl;
import com.huobi.perhack.db.HuobiSqliteHelper;
import com.huobi.perhack.source.HuobiSourceDataProcessor;
import com.huobi.wss.event.MarketDepthSubResponse;
import com.huobi.wss.event.MarketDetailSubResponse;
import com.huobi.wss.handle.WssMarketHandle;

public class Waterfall {

    private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private MarketAPIServiceImpl mService = new MarketAPIServiceImpl();
	
    private String URL = "wss://www.btcgateway.pro/ws";//合约站行情请求以及订阅地址
//    private String URL = "wss://www.hbdm.com/ws";//合约站行情请求以及订阅地址

    
    WssMarketHandle wssMarketHandle = new WssMarketHandle(URL);
	
    private HuobiSourceDataProcessor mHuobiSourceDataProcessor = new HuobiSourceDataProcessor();
    
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
        channels.add("market.BTC_CW.kline.5min");
        channels.add("market.BTC_CW.kline.15min");
        channels.add("market.BTC_CW.kline.30min");
        channels.add("market.BTC_CW.kline.60min");
        channels.add("market.BTC_CW.kline.4hour");
        channels.add("market.BTC_CW.kline.1day");
        channels.add("market.BTC_CW.kline.1week");
        channels.add("market.BTC_CW.kline.1mon");

        channels.add("market.BTC_NW.kline.1min");
        channels.add("market.BTC_NW.kline.5min");
        channels.add("market.BTC_NW.kline.15min");
        channels.add("market.BTC_NW.kline.30min");
        channels.add("market.BTC_NW.kline.60min");
        channels.add("market.BTC_NW.kline.4hour");
        channels.add("market.BTC_NW.kline.1day");
        channels.add("market.BTC_NW.kline.1week");
        channels.add("market.BTC_NW.kline.1mon");
        
        channels.add("market.BTC_CQ.kline.1min");
        channels.add("market.BTC_CQ.kline.5min");
        channels.add("market.BTC_CQ.kline.15min");
        channels.add("market.BTC_CQ.kline.30min");
        channels.add("market.BTC_CQ.kline.60min");
        channels.add("market.BTC_CQ.kline.4hour");
        channels.add("market.BTC_CQ.kline.1day");
        channels.add("market.BTC_CQ.kline.1week");
        channels.add("market.BTC_CQ.kline.1mon");
        
        channels.add("market.BTC_CW.depth.step0");
        channels.add("market.BTC_CW.depth.step1");
        channels.add("market.BTC_CW.depth.step2");
        channels.add("market.BTC_CW.depth.step3");
        channels.add("market.BTC_CW.depth.step4");
        channels.add("market.BTC_CW.depth.step5");
        channels.add("market.BTC_CW.depth.step6");
        channels.add("market.BTC_CW.depth.step7");
        channels.add("market.BTC_CW.depth.step8");
        channels.add("market.BTC_CW.depth.step9");
        channels.add("market.BTC_CW.depth.step10");
        channels.add("market.BTC_CW.depth.step11");

        channels.add("market.BTC_NW.depth.step0");
        channels.add("market.BTC_NW.depth.step1");
        channels.add("market.BTC_NW.depth.step2");
        channels.add("market.BTC_NW.depth.step3");
        channels.add("market.BTC_NW.depth.step4");
        channels.add("market.BTC_NW.depth.step5");
        channels.add("market.BTC_NW.depth.step6");
        channels.add("market.BTC_NW.depth.step7");
        channels.add("market.BTC_NW.depth.step8");
        channels.add("market.BTC_NW.depth.step9");
        channels.add("market.BTC_NW.depth.step10");
        channels.add("market.BTC_NW.depth.step11");
        
        channels.add("market.BTC_CQ.depth.step0");
        channels.add("market.BTC_CQ.depth.step1");
        channels.add("market.BTC_CQ.depth.step2");
        channels.add("market.BTC_CQ.depth.step3");
        channels.add("market.BTC_CQ.depth.step4");
        channels.add("market.BTC_CQ.depth.step5");
        channels.add("market.BTC_CQ.depth.step6");
        channels.add("market.BTC_CQ.depth.step7");
        channels.add("market.BTC_CQ.depth.step8");
        channels.add("market.BTC_CQ.depth.step9");
        channels.add("market.BTC_CQ.depth.step10");
        channels.add("market.BTC_CQ.depth.step11");
        
        channels.add("market.BTC_CW.trade.detail");
        channels.add("market.BTC_NW.trade.detail");
        channels.add("market.BTC_CQ.trade.detail");

        wssMarketHandle.sub(channels, response -> {
//        	logger.info(response);
        	mHuobiSourceDataProcessor.getAndStoreData(response);
        	
        	
        });
		
	}
    
	
	
	public static void main(String []args) throws URISyntaxException{
		Waterfall mWaterfall = new Waterfall();
		mWaterfall.run();
	}
	
}
