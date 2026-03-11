
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class BookAppointment {

    public static void main(String[] args) {

        // Setup Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://staging-app.yourdost.com/");
        System.out.println("Page Title: " + driver.getTitle());

       // I agree pop-up
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'I agree') or contains(text(),'OK')]")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieBtn);
            System.out.println("Cookie popup handled.");
        } catch (Exception e) {
            System.out.println("No cookie popup present.");
        }

        // click login
        WebElement loginBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'Login')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

        System.out.println("Login button clicked successfully.");

        // username
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("emailOrUsername")));
        username.clear();
        username.sendKeys("rajeevyd1");
        System.out.println("Username entered: " + username.getAttribute("value"));

      // password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='password' and @type='password']")));
        password.clear();
        password.sendKeys("Rajeevyd@1");
        System.out.println("Password length: " + password.getAttribute("value").length() + " chars");

    // click submit
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='login' or contains(text(),'LOGIN') or contains(text(),'LOGIN')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);

        System.out.println("Login submitted.");

        // error message

        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'The username and password do not match.') or contains(text(),'incorrect') or contains(text(),'wrong')]")
            ));

            String errorText = errorMsg.getText();
            System.out.println("Error Message Displayed: " + errorText);
            System.out.println("Test Passed - Error message displayed correctly.");

        } catch (Exception e) {
            System.out.println("⚠ No error message found. Possibly login success.");
        }


        // Clicking Book Appointment
        WebElement AppointBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(@class,'OrganizationOfferingCard_actionButtonDesktop__zCQZK') and contains(text(), 'BOOK APPOINTMENT')]")));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", AppointBtn);


        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", AppointBtn);


        // close browser

        System.out.println("Press Enter to close...");
        new java.util.Scanner(System.in).nextLine();

        driver.quit();
        System.out.println("Browser closed successfully.");
    }
}
