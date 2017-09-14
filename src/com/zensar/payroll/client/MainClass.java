package com.zensar.payroll.client;

import java.util.Scanner;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.PayrollServicesDownException;
import com.zensar.payroll.serviceprovider.ServiceProvider;
import com.zensar.payroll.services.PayrollServices;

public class MainClass {

	public static void main(String[] args) {
		try{
		PayrollServices payrollServices ;
		payrollServices = ServiceProvider.getBankingServices();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1.Insert");
			System.out.println("2.calculate net salary");
			System.out.println("3.get associate details");
			System.out.println("4.get all associate details");
			System.out.println("5. exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("enter first name:");
				String firstName = sc.next();
				System.out.println("enter last name:");
				String lastName = sc.next();
				System.out.println("enter emailID");
				String emailId = sc.next();
				System.out.println("enter department:");
				String department = sc.next();
				System.out.println("enter designation:");
				String designation = sc.next();
				System.out.println("enter pancard");
				String pancard = sc.next();
				System.out.println("yearlyInvestmentUnder80C");
				int yearlyInvestmentUnder80C = sc.nextInt();
				System.out.println("basicSalary");
				int basicSalary = sc.nextInt();
				System.out.println("enter account number");
				int accountNo = sc.nextInt();
				System.out.println("enter Bank name:");
				String bankName = sc.next();
				System.out.println("enter IFSCCode");
				String ifscCode = sc.next();
				System.out.println(payrollServices.acceptAssociateDetails(
						firstName, lastName, emailId, department, designation,
						pancard, yearlyInvestmentUnder80C, basicSalary,
						 accountNo, bankName, ifscCode));
				break;
			case 2:
				System.out.println("enter associate ID");
				int associateId = sc.nextInt();
				System.out.println(payrollServices.calculateNetSalary(associateId));
				break;
			case 3:
				System.out.println("enter associate ID");
				int AssociateId = sc.nextInt();
				System.out.println(payrollServices.getAssociateDetails(AssociateId));
				break;
			case 4:
				for (Associate associate : payrollServices.getAllAssociatesDetails())
				System.out.println(associate);
				break;
			case 5:
				System.out.println("exit");
				break;

			default:
				System.out.println("enter correct choice");
				break;
			}
		} while (true);
	}
	catch(PayrollServicesDownException e ){System.out.println("Please try again");}
	catch(AssociateDetailsNotFoundException e ){System.out.println("Associate id not found");}
	}
}
