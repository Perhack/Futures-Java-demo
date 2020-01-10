package com.huobi.perhack.db;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.perhack.quant.Kline;
import com.perhack.quant.Position;
import com.perhack.quant.Trade;
import com.perhack.util.SqliteHelper;

import cn.hutool.core.date.DateUtil;

public class HuobiSqliteHelper {

	private SqliteHelper mSqliteHelper;
	
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private String sqls = "";
    
    private int sqlCount = 0;
    
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
	
	public void insertIntoKline(Kline kline) {
		

		String sql = "insert into kline values('" + DateUtil.date(kline.getTs()*1000).toString() 
				+ "','" + DateUtil.date(kline.getRespTs()).toString() + "',"
				+ kline.getOpen() + "," + kline.getHigh() + "," + kline.getLow() + "," + kline.getClose() + ","
				+ kline.getAmount() + "," + kline.getVol() + "," + kline.getCount() + ",'" + kline.getContractType()
				+ "','" + kline.getPeriod() +  "')";
		
		sqls += sql + ";";
		sqlCount ++;
		if(sqlCount >= 5010) {
			mSqliteHelper.executeBatch(sqls);
			sqls = "";
			sqlCount = 0;
		}
//			logger.info(sql);
//			mSqliteHelper.executeUpdate(sql);

	}
	
	public void insertIntoPosition(Position position) {
		

		String sql = "insert into position values('" + DateUtil.date(position.getTs()*1000).toString() 
				+ "','" + DateUtil.date(position.getRespTs()).toString() + "','"+ position.getAsks() + "','" + 
				position.getBids() + "','" + position.getContractType() + "','" + position.getStep() +   "')";
		sqls += sql + ";";
//		logger.info(sql);
		sqlCount ++;

		if(sqlCount >= 5010) {
			mSqliteHelper.executeBatch(sqls);
			sqls = "";
			sqlCount = 0;
		}
//		logger.info(sql);
//		mSqliteHelper.executeUpdate(sql);

	}
	
	public void insertIntoTrade(Trade trade) {
		

		String sql = "insert into trade values('" + DateUtil.date(trade.getTs()*1000).toString()
				+ "','" + DateUtil.date(trade.getRespTs()).toString()+ "'," + trade.getPrice() + "," + trade.getAmount() 
				+ ",'" +  trade.getContractType() + "','" + trade.getDirection() +  "')";
		sqls += sql + ";";
		sqlCount ++;

		if(sqlCount >= 5010) {
			mSqliteHelper.executeBatch(sqls);
			sqls = "";
			sqlCount = 0;
		}
//		logger.info(sql);
//		mSqliteHelper.executeUpdate(sql);

	}
	
}
