package com.perhack.waterfall;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.huobi.api.response.market.MarketHistoryKlineResponse;
import com.huobi.api.service.market.MarketAPIServiceImpl;
import com.huobi.wss.event.MarketDepthSubResponse;
import com.huobi.wss.event.MarketDetailSubResponse;
import com.huobi.wss.event.MarketKLineSubResponse;
import com.huobi.wss.handle.WssMarketHandle;

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
    
	public void run(){
		
//		MarketHistoryKlineResponse kline = mService.getMarketHistoryKline("BCT_CQ","1min",150);
//		
//		System.out.println(JSON.toJSONString(kline));
		
		try {
			test4();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
	public static void main(String []args){
		
		Waterfall mWaterfall = new Waterfall();
		mWaterfall.run();
		
	}
	
}
