package cn.smbms.bean;

import java.util.Date;
import java.util.List;

public class Address {
	private Integer id;// 主键ID
	private String contact;// 联系人姓名
	private String addressDesc;// 收货地址明细
	private String postCode;// 邮编
	private String tel;// )联系人电话
	private Integer createdBy;// 创建者
	private Date creationDate;// 创建时间
	private Integer modifyBy;// 修改者
	private Date modifyDate;// 修改时间
	private Integer userId;// 用户ID
	
	public Address() {
		super();
	}
	public Address(Integer id, String contact, String addressDesc,
			String postCode, String tel, Integer createdBy, Date creationDate,
			Integer modifyBy, Date modifyDate, Integer userId) {
		super();
		this.id = id;
		this.contact = contact;
		this.addressDesc = addressDesc;
		this.postCode = postCode;
		this.tel = tel;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddressDesc() {
		return addressDesc;
	}
	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", contact=" + contact + ", addressDesc="
				+ addressDesc + ", postCode=" + postCode + ", tel=" + tel
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate
				+ ", userId=" + userId + "]";
	}
	
}
