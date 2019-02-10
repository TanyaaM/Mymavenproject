//functions present which we are using
package com.Base;

import java.util.Hashtable;

public class ExcelUtil extends BaseTest {
public static Object[][] getTestData(Xlsx_Reader xls,String testCaseName) {
		
		String SheetName="TestData";
		int i=xls.getRowCount(SheetName);
		System.out.println(i);
	
	int testStartRowNum=1;
	
while(!xls.getCellData(SheetName, 0, testStartRowNum).equals(testCaseName))
		{
	testStartRowNum++;  //totl no of rows
		}
	colSRowNum=testStartRowNum;
//System.out.println("test start row num: "+ testStartRowNum);
	
	//calculate rows of data

	int colStartRowNum=testStartRowNum+1;
	int dataStartRowNum=testStartRowNum+2;
	int rows=0;
	

 while(!xls.getCellData(SheetName,0,dataStartRowNum+rows).equals(""))
 {
	 
	rows++; 
 }

//System.out.println("total rows are:"+rows);

//calculate no. of cols

int cols=0;

while(!xls.getCellData(SheetName,cols,colStartRowNum).equals(""))
		{
	cols++;
	 }
   System.out.println(cols);
	//Object data[][]=new Object[rows][cols];
   
	Object data[][]=new Object[rows][1];
	
	Hashtable<String,String>table=null;
	for(int rNum=0;rNum<rows;rNum++)
	{
		table=new Hashtable<String,String>();
		for(int cNum=0;cNum<cols;cNum++)
		{
			
			String key=xls.getCellData(SheetName,cNum,colStartRowNum);
			String value=xls.getCellData(SheetName,cNum,dataStartRowNum+rNum);
			System.out.println(value);
			table.put(key, value);
		}
		//String x=String.valueOf(dataStartRowNum+rNum);
		//table.put("Row",x);
		data[rNum][0]=table;
		System.out.println();
	}
	
	return data;
	
}
public static boolean isRunnable(String testName,Xlsx_Reader xls){
	  String sheet="TestCases";
	  int rows=xls.getRowCount(sheet);
	  for(int r=2;r<=rows;r++)
	  {
		  String tName=xls.getCellData(sheet, "TCID", r);
		  if(tName.equals(testName))
		  {
			  String Runmode=xls.getCellData(sheet, "Runmode", r);
			  if(Runmode.equals("Y"))
			  {
				  return true;
			  }else
			  {
				  return false;
			  }
				  
		  }
			  
	  }
	return false;
 }


 }
	



