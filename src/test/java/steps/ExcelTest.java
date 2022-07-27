package steps;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {
    public static void main(String[] args) {

        String path = "/Users/zarina/MindtekCucumberAutomation/src/test/resources/testData/TestData1.xlsx";

        try {
            FileInputStream input = new FileInputStream(path); // test data in our
            Workbook workbook = new XSSFWorkbook(input); //
            Sheet sheet1=workbook.getSheet("Sheet1");
            String firstName=sheet1.getRow(1).getCell(0).toString();
            System.out.println(firstName);
            firstName=sheet1.getRow(2).getCell(0).toString();
            System.out.println(firstName);

            sheet1.getRow(2).getCell(1).setCellValue("Kerimbaev");

            sheet1.createRow(3).createCell(0).setCellValue("Kim");
            sheet1.getRow(3).createCell(1).setCellValue("Yang");

            int numberOfRow =sheet1.getPhysicalNumberOfRows();
            System.out.println(numberOfRow);

            int numberOfCellRow1= sheet1.getRow(1).getPhysicalNumberOfCells();
            System.out.println(numberOfCellRow1);

            for(int i=0; i<numberOfCellRow1; i++){
                System.out.print(sheet1.getRow(1).getCell(i).toString()+ ",");
            }

            FileOutputStream output=new FileOutputStream(path);
            workbook.write(output);
            System.out.println(sheet1.getRow(2).getCell(1).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
