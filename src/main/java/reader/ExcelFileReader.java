package reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Factory.PlaywrightFactory;

import java.util.HashMap;
import java.util.Map;

public class ExcelFileReader extends PlaywrightFactory {
	FileInputStream stream;
	XSSFWorkbook workBook;
	XSSFSheet sheet;
	Map<String, Integer> map = new HashMap<>();

	public ExcelFileReader() {
		try {
			stream = new FileInputStream( ".\\src\\test\\resources\\config\\testdata.xlsx");
			workBook = new XSSFWorkbook(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	    // Your code goes here
	    System.out.println("Hello, World!");
	    ExcelFileReader A = new ExcelFileReader();
	    System.out.println(Arrays.deepToString(A.getTestData("LoginData")));
	    System.out.println(Arrays.deepToString(A.getTestDataDdt("Sheet1")));
	}

	/**
	 * This Method will read the excel and return a given cell value
	 * 
	 * @param sheetnumber
	 * @param row
	 * @param column
	 * @return
	 */
	public String getCellValue(int sheetnumber, int row, int column) {
		try {
			sheet = workBook.getSheetAt(sheetnumber);
			String data = sheet.getRow(row).getCell(column).getStringCellValue();
			return data;
		} catch (NullPointerException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method is used to read Excel data using Hash map which will store the
	 * data in key and value format
	 * 
	 * @param excelKey : String parameter for the test Data key
	 * @return
	 * 
	 */
	public String getExcelvalueForKey(int sheetNo, String excelKey) {

		sheet = workBook.getSheetAt(sheetNo);

		HashMap<String, String> testDataMap = new HashMap<>();

		for (Row row : sheet) {
			if (row.getCell(0) != null && row.getCell(1) != null) {
				String key = row.getCell(0).getStringCellValue();
				String value = row.getCell(1).getStringCellValue();
				testDataMap.put(key, value);
			}
		}
		for (String key : testDataMap.keySet()) {
			String value = testDataMap.get(key);
			if (key.equalsIgnoreCase(excelKey)) {
				return value;
			}
		}
		return null;
	}

	/**
	 * This method will read the data from excel by selecting SheetName and column
	 * number
	 * 
	 * @param sheetName
	 * @param ColumnNo
	 * @return
	 */
	public String getExcellColumnValue(String sheetName,int rowIndex, String columnName) {
		String cellvalue = null;
		try {
			sheet = workBook.getSheet(sheetName);

				Row row = sheet.getRow(rowIndex);
				
				if (columnName.equals("username")) {
			
					cellvalue = row.getCell(0).getStringCellValue();

				} else if (columnName.equals("password")) {
					cellvalue = row.getCell(1).getStringCellValue();
				}
							

			// Close the workbook and fileInputStream
			//workBook.close();
			//stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellvalue;

	}
	
	
	
	
	
	
	
	
	
	public int getLastrow(String sheetName) {
		
			sheet = workBook.getSheet(sheetName);
			return sheet.getLastRowNum();
		
	}
	
	public String[] getData(String sheetName, int columnIndex) { 
		  sheet = workBook.getSheet(sheetName);

		    if (sheet == null) {
		     //   System.out.println("Sheet '" + sheetName + "' not found");
		        return new String[0]; // Return an empty 2D array
		    }

		    int lastRow = getLastRowForColumn(sheet,columnIndex);
		    String[] data = new String[lastRow];

		    for (int i = 0; i < lastRow; i++) {
		        Row row = sheet.getRow(i + 1);

		        // Check for null row, which may occur if there are empty rows in the sheet
		        if (row != null) {
		        	
		            data[i] = getCellValueAsString(row.getCell(columnIndex)).replace("E14", "0").replace(".", "");
		        } else {
		            data[i] = ""; // or handle this case as needed
		        }
		      //  System.out.println(data[i][0]);
		    }
		    return data;
	}
	
	 public  int getLastRowForColumn(Sheet sheet, int columnIndex) {
        int lastRow = 0;
        for (Row row : sheet) {
            if (row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
                lastRow = row.getRowNum();
            }
        }
        return lastRow;
    }
		    
	 
		    
	
	 



	
	
	/**
	 * this utility will read username and password from the specified Worksheet name
	 */
	public String[][] getUserLoginTestData(String sheetName) { 
		if(sheet==null) {
		try {
			
			stream = new FileInputStream( ".\\src\\test\\resources\\config\\testdata.xlsx");
			workBook = new XSSFWorkbook(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		sheet = workBook.getSheet(sheetName);

		int rowCount =sheet.getLastRowNum();
		//System.out.println(rowCount);
		
		String data[][] = null;
		data = new String[rowCount][2]; // Assuming 5 columns: username and password and Scenariotype 
		for (int i = 0; i <rowCount; i++) {
	//for(Row row : sheet) {	
			//XSSFRow row = sheet.getRow(i+1);
		Row row = sheet.getRow(i+1);
		//int i = row.getRowNum();
		
			String username = getCellValueAsString(row.getCell(0));
			String password = getCellValueAsString(row.getCell(1));


			data[i][0] = username;
			data[i][1] = password;

//			
		}
		return data;
	}
	
	
	private String getCellValueAsString(Cell cell) {
	    if (cell != null) {
	        return cell.toString().trim();
	    } else {
	        return ""; 
	        }
	}
	
	public Object[][] getTestData(String sheetName) { 

		Object data[][] = null;

			sheet = workBook.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {

				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

					data[i][j] = sheet.getRow(i + 1).getCell(j).toString().trim();

				}
			}
		return data;
	}
		public Object[][]getTestDataDdt(String sheetName) { 

			Object data[][] = null;

				sheet = workBook.getSheet(sheetName);

				data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

				for (int i = 0; i < sheet.getLastRowNum(); i++) {

					for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

						data[i][j] = sheet.getRow(i + 1).getCell(j).toString().trim();

					}
				}
			return data;
	}
	
	
	
		
	}
