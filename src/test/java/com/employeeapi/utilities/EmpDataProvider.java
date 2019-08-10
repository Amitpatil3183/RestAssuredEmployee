package com.employeeapi.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class EmpDataProvider {

	
	public static String XLSHEETPATH = "E:/restassuredemployeeproject/RestassuredAPITesting_Employee_Project/src/test/java/com/employeeapi/utilities/EmpTestData.xlsx";
	
	@DataProvider(name = "empdataprovider")
	public static String [][] getEmpData() throws IOException{
		
		
		//Read data from Excel
		String XLPATH = System.getProperty("user.dir")+"src/test/java/com/employeeapi/utilities/EmpTestData.xlsx";
		int rownum =XLUtils.getRowCount(XLPATH, "Sheet1");
		int columncount = XLUtils.getCellCount(XLPATH, "Sheet1", 1);
		
		String empdata[][] = new String [rownum][columncount];
		
		for (int i=1; i<= rownum; i++) {
			
			for (int j=0; j<columncount ; j++) {
				
				empdata[i-1][j] = XLUtils.getCellData(XLPATH, "Sheet1", i, j);
			}
		}
		//String empData[][] = { {"ABvnn1","6900","11"}, {"ABPuoo2","3090","11"}, {"ABPkkj3","9900","11"}};
		return (empdata);

}

	
}
