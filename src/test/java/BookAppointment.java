
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

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
        username.sendKeys("rajeevyd6");
        System.out.println("Username entered: " + username.getAttribute("value"));

      // password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='password' and @type='password']")));
        password.clear();
        password.sendKeys("Rajeevyd@6");
        System.out.println("Password length: " + password.getAttribute("value").length() + " chars");

    // click submit
//        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//button[@type='login' or contains(text(),'LOGIN') or contains(text(),'LOGIN')]")
//        ));
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);


        driver.findElement(By.xpath("//button[@type='login' or contains(text(),'LOGIN') or contains(text(),'LOGIN')]")).click();
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
        System.out.println("Book Appointment Button is clicked");

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", AppointBtn);


        // SF Flow
        WebElement appointMode = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(@class,'alignment-continue-1')]//span[text()='CONTINUE']/ancestor::button")
                ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", appointMode);
        System.out.println("SF flow button is clicked");

        // CATEGORY DROPDOWN
        WebElement categoryDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//mat-label[text()='Category']/following::mat-select[1]")
                ));
        categoryDropdown.click();

        // SELECT CATEGORY
        WebElement category = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//mat-option//span[normalize-space()='Relationship']")
                ));
        category.click();
        System.out.println("Category selected");


        // SUBCATEGORY DROPDOWN
        WebElement subCategoryDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//mat-label[text()='Sub Category']/following::mat-select[1]")
                ));
        subCategoryDropdown.click();

        // SELECT SUBCATEGORY
        WebElement subCategory = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//mat-option//span[normalize-space()='Friends']")
                ));
        subCategory.click();
        System.out.println("Subcategory selected");


        // CONTINUE BUTTON
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[.//span[normalize-space()='CONTINUE']]")
                ));
        continueBtn.click();
        System.out.println("Continue clicked");


//        driver.findElement(By.xpath("//*[contains(@class,'font-bold color-light-grey font-1 mode') and contains(text(), ' VIDEO CALL ')]")).click();


//        driver.findElement(By.xpath("//button[.//span[normalize-space()=' SELECT ']]")).click();

        //

        WebElement videoSelect = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(.,' VIDEO CALL ')]//button[.//text()[contains(.,' SELECT ')]]")
        ));

        videoSelect.click();
        System.out.println("VIDEO CALL selected");

        // slot selection

        WebElement slotDate = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='card-info-wrapper']/mat-card/div[1]/button[2]//div[contains(@class,'week-day')]")
        ));
        slotDate.click();
        System.out.println("Slot day clicked");

        WebElement slot = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'01:00 PM') and (contains(.,'PM') or contains(.,'01'))]")
        ));
        slot.click();
        System.out.println("Slot is selected");

        // check box for non-Indian number
        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Please select incase of Non-Indian Number')]")));
        checkBox.click();



        // selecting city
        WebElement selectCity = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-form-field[.//mat-label[contains(text(),'Select City')]]//mat-select")
        ));
        selectCity.click();
        System.out.println("Slect city dropdown selected");
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({block: 'center'});", selectCity);

//        selectCity.click();




        WebElement cityOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//mat-option//span[normalize-space()='Bangalore']")
        ));

        cityOption.click();

        System.out.println("City Selected");


        // consent checkbox
//        WebElement consentcheckbox = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//mat-checkbox//span[contains(text(),'I hereby consent')]")
//        ));
//        consentcheckbox.click();


        List<WebElement> checkboxes = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//input[@type='checkbox']")
                )
        );
        checkboxes.get(1).click();
        System.out.println("Consent check Box clicked");

        // Book Appointment clicked
        WebElement BkVdApmnt = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[contains(text(),' BOOK APPOINTMENT ')]]")
        ));
        BkVdApmnt.click();
        System.out.println("Booked the video appointment");

        WebElement consentAgreeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("consent-agree-btn")));
        consentAgreeBtn.click();
        // close browser

        System.out.println("Press Enter to close...");
        new java.util.Scanner(System.in).nextLine();

        driver.quit();
        System.out.println("Browser closed successfully.");
    }
}
