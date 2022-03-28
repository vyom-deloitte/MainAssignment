import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class AddCustomer {
    public static WebDriver driver = BaseClass.driver;
    static String prev;
    static String now;
    public static void addCustomerFun() throws InterruptedException {
        //click on bank manager login
        driver.findElement(By.cssSelector("button[ng-click='manager()']")).click();
        Thread.sleep(2000);
        //click on add customer
        driver.findElement(By.xpath("//button[normalize-space()='Add Customer']")).click();
        Thread.sleep(2000);
        //Add First name field
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("fds");
        Thread.sleep(2000);
        //Add Second name field
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("asdasd");
        Thread.sleep(2000);
        //Add Post Code field
        driver.findElement(By.xpath("//input[@placeholder='Post Code']")).sendKeys("21323");
        Thread.sleep(2000);
        //Press on button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        String val = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println(val);
        //go to open account page
        openAccountFun();
        //click on home
        goToHome();
        //go to customer login
        customerLogin();
        //deposit
        deposit();
        //withdraw
        withdraw();
    }
    public static void withdraw() throws InterruptedException {
        prev = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > strong:nth-child(2)")).getText();
        //click on withdraw button
        driver.findElement(By.xpath("//button[normalize-space()='Withdrawl']")).click();
        //enter amount
        Thread.sleep(2000);
        int amount = 500;
        driver.findElement(By.xpath("//input[@placeholder='amount']")).sendKeys("500");
        Thread.sleep(2000);
        //CLick withdraw
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(2000);

        now = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > strong:nth-child(2)")).getText();
        if(Integer.parseInt(prev) - amount == Integer.parseInt(now)){
            System.out.println("Correct withdrawal");
        }else{
            System.out.println("Wrong withdrawal");
        }
    }
    public static void deposit() throws InterruptedException {
        //get prev amount
        prev = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > strong:nth-child(2)")).getText();
        //click on deposit
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[normalize-space()='Deposit']")).click();
        //enter amount
        Thread.sleep(2000);
        int amount = 1000;
        driver.findElement(By.xpath("//input[@placeholder='amount']")).sendKeys("1000");
        System.out.println(driver.findElement(By.xpath("//input[@placeholder='amount']")).getText());
        Thread.sleep(2000);
        //click on deposit
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        //get new balance
        now = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > strong:nth-child(2)")).getText();
        Thread.sleep(2000);
        System.out.println(now);
        if((Integer.parseInt(now) - amount) == Integer.parseInt(prev))
        {
            System.out.println("Correct deposit");
        }else{
            System.out.println("Wrong Deposit");
        }
    }
    public static void customerLogin() throws InterruptedException {
        //click customer login
        driver.findElement(By.xpath("//button[normalize-space()='Customer Login']")).click();
        Select yourName = new Select(driver.findElement(By.xpath("//select[@id='userSelect']")));
        yourName.selectByVisibleText("fds asdasd");
        Thread.sleep(2000);
        //click Login
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

    }
    public static void goToHome() {
        //click on home button
        driver.findElement(By.xpath("//button[normalize-space()='Home']")).click();
    }
    public static void openAccountFun() throws InterruptedException {
        //go to open account
        driver.findElement(By.cssSelector(".btn.btn-lg.tab[ng-class='btnClass2']")).click();
        //click on customer dropdown
        Thread.sleep(2000);
        Select customer = new Select(driver.findElement(By.xpath("//select[@id='userSelect']")));
        //select user
        customer.selectByVisibleText("fds asdasd");
        Thread.sleep(2000);
        //select currency
        Select currency = new Select(driver.findElement(By.xpath("//select[@id='currency']")));
        currency.selectByVisibleText("Rupee");
        Thread.sleep(2000);
        //Click process
        driver.findElement(By.xpath("//button[normalize-space()='Process']")).click();
        Thread.sleep(2000);
        //get the alert message
        String val = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println(val);
    }
}
