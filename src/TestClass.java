import java.io.File;
import java.io.IOException;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;


public class TestClass {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter Main:");
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");

        //Create an object of File class to open xlsx file
        File file = new File("C:\\Users\\Wayne-PC\\IdeaProjects\\Wayne_Pud_Test\\src\\TestData.xlsx");
        System.out.println(file.getPath());
        List<String[]> list = new ArrayList<String[]>();





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


        emailinput.sendKeys("waynepud@guru.com");
        pwinput.sendKeys("123456");
        submit.click();

        System.out.println("End Test");






//        if (file.isFile())
//        {
//            System.out.println("File found");
//            //Create an object of FileInputStream class to read excel file
//            FileInputStream inputStream = new FileInputStream(file);
//
//            Workbook testworkbook = null;
//            testworkbook = new XSSFWorkbook(inputStream);
//
//            //Read sheet inside the workbook by its name
//            Sheet sheet1 = testworkbook.getSheet("Sheet1");
//
//            //Find number of rows in excel file
//            int rowCount = sheet1.getLastRowNum()-sheet1.getFirstRowNum();
//            System.out.println("Rows: " + rowCount);
//
//            //Create a loop over all the rows of excel file to read it
//            for (int i = 1; i < rowCount+1; i++) {
//                Row row = sheet1.getRow(i);
//                String[] array = new String[10];
//                //Create a loop to print cell values in a row
//                for (int j = 0; j < row.getLastCellNum(); j++) {
//                    //Print Excel data in console
//                    //System.out.println(row.getCell(j).getStringCellValue());
//                    array[j] = row.getCell(j).getStringCellValue();
//                }
//
//                list.add(array);
//            }
//        }
//        else
//        {
//            System.out.println("Excel file could not be found.");
//        }

    }
}
