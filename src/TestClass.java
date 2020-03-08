import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.testng.Assert;
// Import modules required for Excel
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TestClass {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter Main:");
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");

        //Create an object of File class to open xlsx file
        //File file = new File("C:\\Users\\Wayne-PC\\IdeaProjects\\Wayne_Pud_Test\\src\\TestData.xlsx");
        File file = new File("TestData.xlsx");
        System.out.println(file.getPath());
        List<String[]> list = new ArrayList<String[]>();

        if (file.isFile())
        {
            System.out.println("Deleting TestData.xlsx");
            file.delete();
        }

        System.out.println("Creating TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");
        Random rand = new Random();

        int rowCount = 0;

        for (int r=0; r<1; r++) {
            Row row = sheet.createRow(r);
            System.out.println("Row #: " + r);
            int randomNum = rand.nextInt(10000);
            Object[] data = {"waynepud@guru.com", "123456"};

            int columnCount = 0;

            for (Object field : data) {
                Cell cell = row.createCell(columnCount++);
                System.out.println("Column #: " + columnCount + ", Value: " + (field).toString());
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }


        try (FileOutputStream outputStream = new FileOutputStream("TestData.xlsx")) {
            workbook.write(outputStream);

        }

        System.out.println("Read TestData.xlsx");
        List<String[]> list1 = new ArrayList<String[]>();

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);

        Workbook testdataworkbook = null;
        testdataworkbook = new XSSFWorkbook(inputStream);

        //Read sheet inside the workbook by its name
        Sheet sheet1 = testdataworkbook.getSheet("Sheet1");

        //Find number of rows in excel file
        int readRowCount = sheet1.getLastRowNum()+1;
        System.out.println("Rows: " + readRowCount);

        //Create a loop over all the rows of excel file to read it
        for (int i = 0; i < readRowCount+0; i++) {
            Row row = sheet1.getRow(i);
            String[] array = new String[10];
            //Create a loop to print cell values in a row
            for (int j = 0; j < row.getLastCellNum(); j++) {
                //Print Excel data in console
                //System.out.println(row.getCell(j).getStringCellValue());
                if(row.getCell(j).getCellType() == CellType.NUMERIC)
                {
                    array[j] = String.valueOf(row.getCell(j).getNumericCellValue());
                }
                else{
                    array[j] = row.getCell(j).getStringCellValue();
                }

            }

            list1.add(array);
        }

        for(int i=0; i<list1.size(); i++){
            System.out.println(list1.get(i)[0] +","+ list1.get(i)[1]);
        }


        // Execute test
        System.out.println("========== TEST DATA ===========");

        //System.out.println(list.get(i)[0] +","+ list.get(i)[1] +", "+ list.get(i)[2]);

        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://demo.guru99.com/test/login.html";
        driver.manage().window().maximize();
        // declaration and instantiation of objects/variables


        driver.get(baseUrl);

        WebElement emailinput = driver.findElement(By.id("email"));
        WebElement pwinput = driver.findElement(By.id("passwd"));
        WebElement submit = driver.findElement(By.id("SubmitLogin"));


        emailinput.sendKeys(list1.get(0)[0]);
        pwinput.sendKeys(list1.get(0)[1]);
        submit.click();


        String expectedTitle = "Successfully Logged in...";

        WebElement divElement = driver.findElement(By.className("error-copy"));
        String str = divElement.getText();
        System.out.println(str);

        if(str == expectedTitle)
        {
            System.out.println("Pass");
        }
        else
        {
            System.out.println("Fail");
        }

        System.out.println("End Test");

        driver.quit();

    }
}
