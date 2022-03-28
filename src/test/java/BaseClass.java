import org.apache.commons.math3.analysis.function.Add;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static WebDriver driver;
    public static XSSFWorkbook wbook;
    public static XSSFSheet sheet;
    private final String FILEPATH = "C:\\Users\\vypant\\IdeaProjects\\MainAssignment\\src\\data\\excel.xlsx";
    @BeforeTest
    public void DataSetUp() throws IOException{
        FileInputStream fis = new FileInputStream(FILEPATH);
        wbook = new XSSFWorkbook(fis);
        sheet = wbook.getSheet("Sheet1");
    }
    @Test
    public static void addCustomer() throws InterruptedException {
        AddCustomer.addCustomerFun();
    }
    @AfterTest
    public void DataTearDown() throws IOException {
        wbook.close();
    }
    @BeforeMethod
    public void SetUp(Method method){
        System.setProperty("webdriver.chrome.driver", "C:\\SdetSoftwares\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }
    public void TearDown(){
        driver.quit();
    }

}