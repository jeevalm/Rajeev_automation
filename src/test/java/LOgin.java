
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class LOgin {

    public static void main(String[] args) {

        // Setup Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://staging-app.yourdost.com/");
        System.out.println("Page Title: " + driver.getTitle());

        // ==========================
        // 1️⃣ Handle Cookie Popup
        // ==========================
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept') or contains(text(),'I agree') or contains(text(),'OK')]")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieBtn);
            System.out.println("Cookie popup handled.");
        } catch (Exception e) {
            System.out.println("No cookie popup present.");
        }

        // ==========================
        // 2️⃣ Click Login/Signup Button
        // ==========================
        WebElement loginBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'Login')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

        System.out.println("Login button clicked successfully.");

        // ==========================
        // 3️⃣ Enter Username
        // ==========================
        // Username - matches screenshot placeholder "Email or Username"
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[contains(@placeholder,'Email') or contains(@placeholder,'Username') or contains(@name,'email') or contains(@name,'username') or contains(@id,'Username')]")));
        username.clear();
        username.sendKeys("rajeevyd1");
        System.out.println("Username entered");


        // ==========================
        // 4️⃣ Enter Password
        // ==========================
        // Password - screenshot shows lock icon, standard type='password'
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='password' or contains(@placeholder,'Password') or contains(@name,'password') or contains(@id,'Password')]")));
        password.clear();
        password.sendKeys("WrongPassword123");
        System.out.println("Password entered");
        // intentionally wrong

        // ==========================
        // 5️⃣ Click Submit
        // ==========================
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' or contains(text(),'Login') or contains(text(),'LOGIN')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);

        System.out.println("Login submitted.");

        // ==========================
        // 6️⃣ Validate Error Message
        // ==========================
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Invalid') or contains(text(),'incorrect') or contains(text(),'wrong')]")
            ));

            String errorText = errorMsg.getText();
            System.out.println("Error Message Displayed: " + errorText);
            System.out.println("✅ Test Passed - Error message displayed correctly.");

        } catch (Exception e) {
            System.out.println("⚠ No error message found. Possibly login success.");
        }

        // ==========================
        // 7️⃣ Close Browser
        // ==========================
        driver.quit();
        System.out.println("Browser closed successfully.");
    }
}
