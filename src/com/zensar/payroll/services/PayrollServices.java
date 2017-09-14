package com.zensar.payroll.services;
import java.util.List;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.PayrollServicesDownException;

public interface PayrollServices {
	public int acceptAssociateDetails(String firstName,String lastName,String department,String emailId,
		String designation,String pancard,int yearlyInvestmentUnder80C,int basicSalary,int accountNo,String bankName,String ifscCode) throws PayrollServicesDownException;
	
	public int calculateNetSalary(int associateId)throws AssociateDetailsNotFoundException,PayrollServicesDownException;
	public Associate getAssociateDetails(int associateId) throws AssociateDetailsNotFoundException,PayrollServicesDownException;
	public List<Associate> getAllAssociatesDetails()throws PayrollServicesDownException;

}
