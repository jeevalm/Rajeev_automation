import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Example {

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);  // Selenium Manager auto downloads driver

        driver.get("https://staging-app.yourdost.com/");
        System.out.println(driver.getTitle());

        System.out.println("Press Enter to close...");
        new java.util.Scanner(System.in).nextLine();

        driver.quit();
    }
}