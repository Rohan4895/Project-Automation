package Login;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Testdata {
	@DataProvider	
	public String[][] Logindata() throws Exception{
		File datafile = new File ("C:\\Users\\Rohan kokare\\Downloads\\sql.csv");
		FileInputStream fis = new FileInputStream(datafile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		String[][] data = new String[7][2]; 
		for(int i=1;i<=6;i++)
		{		System.out.println();
			for(int j=0;j<2;j++)
			{System.out.println();
			//DataFormatter df = new DataFormatter();
				data[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		wb.close();
		fis.close();
		for(String[]datar:data)
		{
			System.out.println(Arrays.toString(datar));
		}
		return data;
	}
}
