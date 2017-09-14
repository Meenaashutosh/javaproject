package com.zensar.payroll.services;

import java.sql.SQLException;
import java.util.List;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.beans.BankDetails;
import com.zensar.payroll.beans.Salary;
import com.zensar.payroll.daoservices.PayrollDAOServices;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.PayrollServicesDownException;
import com.zensar.payroll.serviceprovider.ServiceProvider;

public class PayrollServicesImpl implements PayrollServices {
	private PayrollDAOServices daoServices;

	public PayrollServicesImpl() throws PayrollServicesDownException {
		daoServices = ServiceProvider.getBankingDAOServices();
	}

	@Override
	public int acceptAssociateDetails(String firstName, String lastName,
			String department, String emailId, String designation,
			String pancard, int yearlyInvestmentUnder80C, int basicSalary,
			int accountNo, String bankName, String ifscCode)
			throws PayrollServicesDownException {
		try {
			return daoServices.insertAssociate(new Associate(
					yearlyInvestmentUnder80C, firstName, lastName, department,
					designation, pancard, emailId, new Salary(basicSalary), new BankDetails(ifscCode, bankName, accountNo)));
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll Services Down", e);
		}

	}

	@Override
	public int calculateNetSalary(int associateId)
			throws AssociateDetailsNotFoundException,
			PayrollServicesDownException {
		try {
			double annualPackage = 0, annualTax = 0, monthlyTax = 0, grossSalary = 0, nonTaxAbleAmount = 0;
			Associate associate = this.getAssociateDetails(associateId);
			associate.getSalary().setHra(
					(associate.getSalary().getBasicSalary() * 40) / 100);
			associate.getSalary().setConveyanceAllowance(
					(associate.getSalary().getBasicSalary() * 30) / 100);
			associate.getSalary().setPersonalAllowance(
					(associate.getSalary().getBasicSalary() * 30) / 100);
			associate.getSalary().setGratuity(
					(associate.getSalary().getBasicSalary() * 18) / 100);
			associate.getSalary().setePf(
					(associate.getSalary().getBasicSalary() * 12) / 100);
			associate.getSalary().setCompanyPf(1800);
			grossSalary = associate.getSalary().getBasicSalary()
					+ associate.getSalary().getHra()
					+ associate.getSalary().getConveyanceAllowance()
					+ associate.getSalary().getPersonalAllowance()
					+ associate.getSalary().getePf()
					+ associate.getSalary().getCompanyPf();
			associate.getSalary().setGrossSalary((int) (grossSalary));
			annualPackage = grossSalary * 12;
			associate.setYearlyInvestmentUnder80c(associate
					.getYearlyInvestmentUnder80c()
					+ associate.getSalary().getePf()
					* 12 + associate.getSalary().getCompanyPf() * 12);
			if (associate.getYearlyInvestmentUnder80c() <= 150000)
				nonTaxAbleAmount = associate.getYearlyInvestmentUnder80c();
			else
				nonTaxAbleAmount = 150000;
			nonTaxAbleAmount += 250000;
			if (annualPackage <= 250000)
				annualTax = 0;
			else if (annualPackage > 250000 && annualPackage <= 500000)
				annualTax = (annualPackage - nonTaxAbleAmount) * 5 / 100;
			else if (annualPackage > 500000 && annualPackage <= 1000000)
				annualTax = (500000 - nonTaxAbleAmount) * 0.05
						+ (annualPackage - 500000) * 20 / 100;
			else if (annualPackage > 100000)
				annualTax = (500000 - nonTaxAbleAmount) * 0.05 + 100000
						+ +(annualPackage - 1000000) * 30 / 100;
			monthlyTax = annualTax / 12;
			System.out.println(annualTax);
			System.out.println(annualPackage);
			associate.getSalary().setMonthlyTax((int) monthlyTax);
			associate.getSalary().setNetSalary(
					(int) ((grossSalary - (monthlyTax
							+ associate.getSalary().getePf() + associate
							.getSalary().getCompanyPf()))));
			daoServices.updateAssociate(associate);
			return associate.getSalary().getNetSalary();
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll Services Down", e);
		}

	}

	@Override
	public Associate getAssociateDetails(int associateId)
			throws AssociateDetailsNotFoundException,
			PayrollServicesDownException {
		try {
			if (daoServices.getAssociate(associateId) == null)
				throw new AssociateDetailsNotFoundException(
						"Associate details with id " + associateId
								+ "not found");
			return daoServices.getAssociate(associateId);
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll Services Down", e);
		}
	}

	@Override
	public List<Associate> getAllAssociatesDetails()
			throws PayrollServicesDownException {
		try {
			return daoServices.getAssociates();
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll Services Down", e);
		}
	}

}
