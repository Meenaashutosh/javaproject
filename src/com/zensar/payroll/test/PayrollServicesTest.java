package com.zensar.payroll.test;
import java.util.ArrayList;
import java.util.Collections;
import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.beans.Salary;
import com.zensar.payroll.daoservices.PayrollDAOServicesImpl;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.PayrollServicesDownException;
import com.zensar.payroll.services.PayrollServices;
import com.zensar.payroll.services.PayrollServicesImpl;

public class PayrollServicesTest {
	private static  PayrollServices payrollServices;

	/*@BeforeClass
	public static void setUpData() throws PayrollServicesDownException{
		payrollServices = new PayrollServicesImpl();
	}
	@Before 
	public void  setUpTestData(){
		PayrollDAOServicesImpl.associates.put(101,new  Associate(101, 150000, "fazal", "ahmed", "training", "pt", "ADCFFD", "fgbgfq", new Salary(50000, 142, 524, 524, 254, 254, 1800, 254, 254, 25400, 83910)));
		PayrollDAOServicesImpl.ASSOCIATE_COUNTER_ID++;
	}

	@Test(expected=AssociateDetailsNotFoundException.class)
	public void testGetAssociateDetailsForInvalidId() throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		payrollServices.getAssociateDetails(1765);
	}

	@Test
	public void testGetAssociateDetailsForValidId() throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		Associate expectdAssociate = new Associate(101, 150000, "fazal", "ahmed", "training", "pt", "ADCFFD", "fgbgfq", new Salary(50000, 142, 524, 524, 254, 254, 1800, 254, 254, 25400, 83910));
		Associate actualAssociate= payrollServices.getAssociateDetails(101);
		Assert.assertEquals(expectdAssociate, actualAssociate);
	}	

	@Test(expected=AssociateDetailsNotFoundException.class)
	public void testCalculateNetSalaryForInvalidAssociateId() throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		payrollServices.calculateNetSalary(162);
	}

	@Test
	public void testCalculateNetSalaryForValidAssociateId() throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		int expectdNetSal=83910;
		int actualNetSalary=payrollServices.calculateNetSalary(101);
		Assert.assertEquals(expectdNetSal	, actualNetSalary);
	}

	@Test
	public void testAcceptAssociateDetailsForValidData() throws PayrollServicesDownException{
		int expectdIAssociateId =102;
		int actualAssociateId=payrollServices.acceptAssociateDetails("fd", "cdf", "dd", "dd", "dd", "dd", 1500, 254400, 141, "hdcf", "dc");
		Assert.assertEquals(expectdIAssociateId	, actualAssociateId);
	}

	@Test
	public  void testGetAllAssociateDetails() throws PayrollServicesDownException{
		ArrayList<Associate>expectdList= new ArrayList<>();
		expectdList.add(new Associate(101, 150000, "fazal", "ahmed", "training", "pt", "ADCFFD", "fgbgfq", new Salary(50000, 142, 524, 524, 254, 254, 1800, 254, 254, 25400, 83910)));
	
		ArrayList<Associate>actualList= (ArrayList<Associate>) payrollServices.getAllAssociatesDetails();
		
		Collections.sort(expectdList, new AssociateComparator());
		Collections.sort(actualList,new AssociateComparator());
		Assert.assertEquals(expectdList	, actualList);
		
	}

	@After
	public void tearDownTestData(){
		PayrollDAOServicesImpl.associates.remove(101);
		PayrollDAOServicesImpl.ASSOCIATE_COUNTER_ID=101;
	}



	@AfterClass
	public static void tearDownTestEnv(){
		payrollServices=null;
	}*/
}
