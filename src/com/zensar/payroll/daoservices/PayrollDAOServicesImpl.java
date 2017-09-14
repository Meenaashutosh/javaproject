package com.zensar.payroll.daoservices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.beans.BankDetails;
import com.zensar.payroll.beans.Salary;
import com.zensar.payroll.exceptions.PayrollServicesDownException;
import com.zensar.payroll.serviceprovider.ServiceProvider;
public class PayrollDAOServicesImpl implements PayrollDAOServices {
	private Connection conn;
	public PayrollDAOServicesImpl() throws PayrollServicesDownException{
		conn = ServiceProvider.getConnection();
	}

	@Override
	public int insertAssociate(Associate associate) throws SQLException {
		try{
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into Associate(yearlyInvestmentUnder80c,firstName,lastName,department,designation,pancard,emailId) values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, associate.getYearlyInvestmentUnder80c());
			pstmt.setString(2, associate.getFirstName());
			pstmt.setString(3, associate.getLastName());
			pstmt.setString(4, associate.getDepartment());
			pstmt.setString(5, associate.getDesignation());
			pstmt.setString(6, associate.getPancard());
			pstmt.setString(7, associate.getEmailId());
			pstmt.executeUpdate();
			ResultSet rs = conn.prepareStatement("select max(associateId) from Associate").executeQuery();
			rs.next();
			int assoiateId = rs.getInt(1);
			pstmt = conn.prepareStatement("insert into salary (associateId,basicSalary) values(?,?)");
			pstmt.setInt(1, assoiateId);
			pstmt.setInt(2, associate.getSalary().getBasicSalary());
			pstmt.executeUpdate();
			pstmt=conn.prepareStatement("insert into BankDetails(associateId,accountNo,bankName,ifscCode) values(?,?,?,?)");
			pstmt.setInt(1, assoiateId);
			pstmt.setInt(2, associate.getBankDetails().getAccountNo());
			pstmt.setString(3, associate.getBankDetails().getBankname());
			pstmt.setString(4, associate.getBankDetails().getIfscCode());
			pstmt.executeUpdate();
			conn.commit();
			return assoiateId;
		}
		catch(SQLException e ){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public boolean updateAssociate(Associate associate) throws SQLException {
		try{
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update Associate set yearlyInvestmentUnder80C=?,firstName=?,lastName=?,department=?,designation=?,pancard=?,emailId=?  where associateId =  "+associate.getAssociateId());
			pstmt.setInt(1, associate.getYearlyInvestmentUnder80c());
			pstmt.setString(2, associate.getFirstName());
			pstmt.setString(3, associate.getLastName());
			pstmt.setString(4, associate.getDepartment());
			pstmt.setString(5, associate.getDesignation());
			pstmt.setString(6, associate.getPancard());
			pstmt.setString(7, associate.getEmailId());
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("update Salary set basicSalary=?,hra=?,conveyanceAllowance=?,otherAllowances=?,personalAllowance=?,monthlyTax=?,ePf=?,companyPf=?,gratuity=?,grossSalary=?,netSalary=? where associateId="+associate.getAssociateId());
			pstmt.setInt(1, associate.getSalary().getBasicSalary());
			pstmt.setInt(2, associate.getSalary().getHra());
			pstmt.setInt(3, associate.getSalary().getConveyanceAllowance());
			pstmt.setInt(4, associate.getSalary().getOtherAllowances());
			pstmt.setInt(5, associate.getSalary().getPersonalAllowance());
			pstmt.setInt(6, associate.getSalary().getMonthlyTax());
			pstmt.setInt(7, associate.getSalary().getePf());
			pstmt.setInt(8, associate.getSalary().getCompanyPf());
			pstmt.setInt(9, associate.getSalary().getGratuity());
			pstmt.setInt(10, associate.getSalary().getGrossSalary());
			pstmt.setInt(11, associate.getSalary().getNetSalary());
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("update BankDetails set accountNo=?,bankName=?,ifscCode=? where associateId= "+associate.getAssociateId());
			pstmt.setInt(1, associate.getBankDetails().getAccountNo());
			pstmt.setString(2, associate.getBankDetails().getBankname());
			pstmt.setString(3, associate.getBankDetails().getIfscCode());
			pstmt.executeUpdate();
			conn.commit();
			return true;

		}catch(SQLException e){
			e.printStackTrace(); throw e;
		}
	}
	@Override
	public boolean deleteAssociate(int associateId) throws SQLException {
		try{
			PreparedStatement pstmt = conn.prepareStatement("delete from Associate where assocaiteId =?");
			pstmt.setInt(1,associateId);
			pstmt.executeUpdate();
			return true;
		}
		catch( SQLException e){
			conn.rollback();
			throw e ;
		}
	}
	@Override
	public Associate getAssociate(int associateId) throws SQLException {
		ResultSet rs = conn.prepareStatement("select * from Associate a , Salary s , Bankdetails b where a.associateId=s.associateId and b.associateId ="+associateId).executeQuery();
		if(rs.next())
			return new Associate(rs.getInt("associateID"), rs.getInt("yearlyInvestmentUnder80C"),rs.getString("firstName"), rs.getString("lastName"), rs.getString("department"), rs.getString("designation"), rs.getString("pancard"), rs.getString("emailId"),new Salary(rs.getInt("basicSalary"), rs.getInt("hra"), rs.getInt("conveyanceAllowance"), rs.getInt("otherAllowances"), rs.getInt("personalAllowance"), rs.getInt("monthlyTax"), rs.getInt("ePf"), rs.getInt("companyPf"), rs.getInt("gratuity"), rs.getInt("grossSalary"), rs.getInt("netSalary")),new BankDetails(rs.getString("ifscCode"),rs.getString("bankName"), rs.getInt("accountNo")));
		return null;
	}
	@Override
	public List<Associate> getAssociates() throws SQLException {
		List<Associate> associates = new ArrayList<>();
			ResultSet rs = conn.prepareStatement("select * from Associate a , salary s , bankdetails b where a.associateId=s.associateId and a.associateId = b.associateId").executeQuery();
			while(rs.next())
				associates.add(new Associate(rs.getInt("associateID"), rs.getInt("yearlyInvestmentUnder80C"),rs.getString("firstName"), rs.getString("lastName"), rs.getString("department"), rs.getString("designation"), rs.getString("pancard"), rs.getString("emailId"),new Salary(rs.getInt("basicSalary"), rs.getInt("hra"), rs.getInt("conveyanceAllowance"), rs.getInt("otherAllowances"), rs.getInt("personalAllowance"), rs.getInt("monthlyTax"), rs.getInt("ePf"), rs.getInt("companyPf"), rs.getInt("gratuity"), rs.getInt("grossSalary"), rs.getInt("netSalary")),new BankDetails(rs.getString("ifscCode"),rs.getString("bankName"), rs.getInt("accountNo"))));
			return associates;
	}
}
