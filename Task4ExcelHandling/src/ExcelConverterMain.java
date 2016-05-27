import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConverterMain {

	public static void main(String[] args) {
		final String inputFile = "res/inputData.xlsx";
		final String dataSheetName = "Data";
		Map<Integer, ExcelDataRow> rowContent = new HashMap<Integer, ExcelDataRow>();
		
		rowContent = mapAndConvertInputData(inputFile, dataSheetName, rowContent);
		createAndFillWorkBook(dataSheetName, rowContent);
	}

	private static void createAndFillWorkBook(final String dataSheetName, Map<Integer, ExcelDataRow> rowContent) {
		XSSFWorkbook wb = new XSSFWorkbook();
	    XSSFSheet sheet = wb.createSheet(dataSheetName);
	    for(int i = 0; i <= rowContent.size() ; i++){
	    	XSSFRow row = sheet.createRow(i);
		    XSSFCell cellName = row.createCell(0);
		    XSSFCell cellSurname = row.createCell(1);
		    XSSFCell address = row.createCell(2);
		    XSSFCell city = row.createCell(3);
		    XSSFCell country = row.createCell(4);
		    if(i == 0){
		    	//header row
		    	cellName.setCellValue("Name");
		    	cellSurname.setCellValue("Surname");
		    	address.setCellValue("Address(street, house number)");
		    	city.setCellValue("City");
		    	country.setCellValue("Country");
		    } else {
		    	//data rows
		    	cellName.setCellValue(rowContent.get(i).getName());
		    	cellSurname.setCellValue(rowContent.get(i).getSurName());
		    	address.setCellValue(rowContent.get(i).getAddress());
		    	city.setCellValue(rowContent.get(i).getCity());
		    	country.setCellValue(rowContent.get(i).getCountry());
		    }
	    }

		try {
		    FileOutputStream fileOut = new FileOutputStream("res/outputData.xlsx");
		    wb.write(fileOut);
		    fileOut.close();
		    wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Map<Integer, ExcelDataRow> mapAndConvertInputData(final String inputFile, final String dataSheetName,
			Map<Integer, ExcelDataRow> rowContent) {
		ExcelDataConverter converter = new ExcelDataConverter();
		Map<Integer, ExcelDataRow> rowContentConverted = new HashMap<Integer, ExcelDataRow>();
		
		try {
			XSSFWorkbook inputDataWorkBook = new XSSFWorkbook(new FileInputStream(inputFile));
			XSSFSheet inputDataSheet = inputDataWorkBook.getSheet(dataSheetName);
			XSSFRow row;
			int rowCount = inputDataSheet.getPhysicalNumberOfRows();
			
			//get data from workbook and put it into 
			for(int i = 1; i<rowCount; i++){
				ExcelDataRow dataRow = new ExcelDataRow();
				row = inputDataSheet.getRow(i);
				if(row != null){
					dataRow.setName(row.getCell(0).getStringCellValue());
					dataRow.setSurName(row.getCell(1).getStringCellValue());
					dataRow.setAddress(row.getCell(2).getStringCellValue());
					dataRow.setCity(row.getCell(3).getStringCellValue());
					dataRow.setCountry(row.getCell(4).getStringCellValue());
					rowContent.put(i, dataRow);			
					rowContentConverted.put(i, converter.convertData(dataRow));
				}
			}
			inputDataWorkBook.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowContentConverted;
	}

}
