package com.huobi.perhack.db;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.perhack.quant.Kline;
import com.perhack.util.SqliteHelper;

import cn.hutool.core.date.DateUtil;

public class HuobiSqliteHelper {

	private SqliteHelper mSqliteHelper;
	
    private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public HuobiSqliteHelper() {
		// TODO Auto-generated constructor stub
		try {
			mSqliteHelper = new SqliteHelper(System.getProperty("user.dir") + "/src/main/resources/datahub.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create1minklineTable() {
        try {
        	String sql = "create table cw1minkline(ts text,respts text,"
        			+ "open real,high real,low real,close real,amount real,vol real,count real)";
			mSqliteHelper.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertInto1minkline(Kline kline) {
		
		try {
			String sql = "insert into cw1minkline values('" + DateUtil.date(kline.getTs()*1000).toString() 
					+ "','" + DateUtil.date(kline.getRespTs()).toString() + "',"
					+ kline.getOpen() + "," + kline.getHigh() + "," + kline.getLow() + "," + kline.getClose() + ","
					+ kline.getAmount() + "," + kline.getVol() + "," + kline.getCount() + ")";
			logger.info(sql);
			mSqliteHelper.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
