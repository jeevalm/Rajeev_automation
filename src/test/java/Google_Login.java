import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Google_Login {

	
	public static void main(String[] args) {

        // Setup Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://staging-app.yourdost.com/");
        System.out.println("Page Title: " + driver.getTitle());
        
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'I agree') or contains(text(),'OK')]")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieBtn);
            System.out.println("Cookie popup handled.");
        } catch (Exception e) {
            System.out.println("No cookie popup present.");
        }
        
//		LOgin Button clicked
        
        WebElement loginBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'Login')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

        System.out.println("Login button clicked successfully.");
        
        
        
        
//        WebElement googleLogin = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//button[contains(.,'Google') and contains(@class,'LoginPopup_googleLoginButton__fuJda')]")));
//        googleLogin.click();
        
        driver.findElement(By.xpath("//button[normalize-space()='Google']")).click();
        System.out.println("Google login button is clicked");
        
//        WebElement gmailSelection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//[contains(text(),'rajeevmahendrakar@yourdost.com') and contains(@class,'yAlK0b']")));
//        gmailSelection.click();
        
        driver.findElement(By.xpath("//div[normalize-space()='rajeevmahendrakar@yourdost.com']")).click();
        System.out.println("Gmail selected");
        
        System.out.println("Press Enter to close...");
        new java.util.Scanner(System.in).nextLine();

        driver.quit();
        System.out.println("Browser closed successfully.");
        
        
 }
}
