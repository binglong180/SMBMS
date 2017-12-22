package cn.smbms.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.smbms.bean.Bill;
import cn.smbms.dao.bill.BillMapper;

public class BillMapperTest {
	static SqlSession sqlSession = null;
	List<Bill> list = new ArrayList<Bill>();
	static Logger logger = Logger.getLogger(BillMapperTest.class);

	@Test
	public void getBillMapperTest() {
		sqlSession = MyBatisUtil.createSqlSession();
		try {
			Bill bill = new Bill();
			bill.setProductName("洗发水");
			list = sqlSession.getMapper(BillMapper.class).getBillList(bill);
		} catch (Exception e) {

		} finally {
			MyBatisUtil.closeSQLSession(sqlSession);
		}
		for (Bill bill : list) {
			logger.debug("商品名称：" + bill.getProductName() +
					"，供货商："+ bill.getProviderName() + 
					"，是否付款："+ bill.getIsisPaymentStates(bill.getIsPayment()) + "。");
		}
	}
}
