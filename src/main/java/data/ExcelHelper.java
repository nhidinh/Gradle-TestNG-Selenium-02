package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import utilities.data.InitData;
import utilities.logger.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * User: Nhi Dinh
 * Date: 19/12/2018
 */
public class ExcelHelper
{
    private static final String currentDir = System.getProperty("user.dir");
    private static String macPath = currentDir + "//src//test//resources//" ;
    private static String windowsPath = currentDir +  "\\src\\test\\resources\\";

    private static Platform platform = InitData.PLATFORM;
    private static String testDataExcelPath;

    private static XSSFWorkbook excelWorkBook;
    private static XSSFSheet excelSheet;
    private static XSSFCell cell;
    private static XSSFRow row;

    public static int rowNumber;
    public static int columnNumber;

    public static int getRowNumber() {
        return rowNumber;
    }

    public static void setRowNumber(int rowNumber) {
        ExcelHelper.rowNumber = rowNumber;
    }

    public static int getColumnNumber() {
        return columnNumber;
    }

    public static void setColumnNumber(int columnNumber) {
        ExcelHelper.columnNumber = columnNumber;
    }

    public static void setDataFileLocation(String testDataExcelFileName){
        switch (platform) {
            case MAC:
                testDataExcelPath = macPath+testDataExcelFileName;
                Log.info("Data File Location on MAC: " + testDataExcelPath + "\n");
                break;
            case WINDOWS:
                testDataExcelPath = windowsPath+testDataExcelFileName;
                Log.info("Data File Location on WINDOWS: " + testDataExcelPath + "\n");
                break;
            default:
                Log.info("Data File not found! Check your file name!\n");
                break;
        }
    }

    public static void setExcelFileSheet(String sheetName){
        try{
            //Open The Excel File:
            Log.info("Opening the data file");
            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath);
            Log.info("Getting Excel File");
            excelWorkBook = new XSSFWorkbook(ExcelFile);
            Log.info("Setting Excel File Sheet");
            excelSheet = excelWorkBook.getSheet(sheetName);
            Log.info("Complete setting Excel File Sheet");
        }catch (Exception e){
            Log.error("FAILED to set Excel File Sheet");
            Log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static int getNumberOfRow(){
        return excelSheet.getPhysicalNumberOfRows();
    }

    public static String getCellData(int rowNumber, int colNumber){
        Log.info("Getting Cell Data...");
        cell = excelSheet.getRow(rowNumber).getCell(colNumber);
        DataFormatter formatter = new DataFormatter();
        Log.info("Return Cell Data Value: " + cell);
        return formatter.formatCellValue(cell);
    }

    public static XSSFRow getRowData(int rowNumber) {
        Log.info("Getting Row Data...");
        row = excelSheet.getRow(rowNumber);
        Log.info("Return Row data value");
        return row;
    }

    public static void setCellData(String value, int rowNumber, int colNumber){
        try{
            row = excelSheet.getRow(rowNumber);
            cell = row.getCell(colNumber);

            Log.info("Setting Cell Data...");
            if(cell == null){
                cell = row.createCell(colNumber);
                cell.setCellValue(value);
            }else {
                cell.setCellValue(value);
            }
            FileOutputStream outputFile = new FileOutputStream(testDataExcelPath);

            Log.info("Writing Data to file: "+outputFile);
            excelWorkBook.write(outputFile);
            outputFile.flush();
            outputFile.close();

            Log.info("Complete writing file");
        } catch (IOException e) {
            Log.error("FAILED to write file");
            Log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static int getCellIndexByText(String text){
        Log.info("Getting cell index by text: " + text);
        DataFormatter formatter = new DataFormatter();
        row = excelSheet.getRow(0);
        int cellIndex = -1;
        if(row == null){
            Log.error("Header row is empty");
        }
        for(Cell cell:row){
            String textCell = formatter.formatCellValue(cell);
            if(textCell.equals(text)){
                cellIndex= cell.getColumnIndex();
                Log.info("Found Cell with column index: "+ cell.getColumnIndex());
            }
        }
        if (cellIndex != -1){
            return cellIndex;
        }else {
            Log.info("No cell is found in header");
            System.out.println("No cell is found in header");
        }
        return cellIndex;
    }
}
