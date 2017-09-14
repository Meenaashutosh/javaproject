package com.zensar.payroll.beans;

public class BankDetails {
	private String ifscCode,bankName;
	private int accountNo;
    public BankDetails(){}
	public BankDetails(String ifscCode, String bankname, int accountNo) {
		super();
		this.ifscCode = ifscCode;
		this.bankName = bankname;
		this.accountNo = accountNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankname() {
		return bankName;
	}
	public void setBankname(String bankname) {
		this.bankName = bankname;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNo;
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result
				+ ((ifscCode == null) ? 0 : ifscCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankDetails other = (BankDetails) obj;
		if (accountNo != other.accountNo)
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (ifscCode == null) {
			if (other.ifscCode != null)
				return false;
		} else if (!ifscCode.equals(other.ifscCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+"ifscCode=" + ifscCode + ", bankname=" + bankName
				+ ", accountNo=" + accountNo;
	}

}
