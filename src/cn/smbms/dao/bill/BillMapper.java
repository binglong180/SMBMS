package cn.smbms.dao.bill;

import java.util.List;

import cn.smbms.bean.Bill;

public interface BillMapper {
	public List<Bill> getBillList(Bill bill);
}
