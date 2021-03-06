package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZPCheat {
    public static String cheatDataURL = "https://admin.zingplay.com/en/tools/deploy_docker/details/luckynine/socket_server/qc2/0";

    private static WebDriver driver;
    private static WebDriverWait wait;

    public ZPCheat() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + System.getProperty("user.dir") + "\\ChromeProfile\\");
        driver = new ChromeDriver(options);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        wait = new WebDriverWait(driver, 15);
    }

    public static void cheatData(int testCaseId) throws Exception {

        driver.get(cheatDataURL);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("form-manage-test-case-button")));
        driver.findElement(By.id("form-manage-test-case-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + testCaseId + "']/parent::*/td/span[@id='run-button']")));
        driver.findElement(By.xpath("//td[text()='" + testCaseId + "']/parent::*/td/span[@id='upload-button']")).click();

    }

    public static void closeWeb() throws Exception{
//        driver.close();
        Runtime.getRuntime().exec("TASKKILL /F /IM chrome.exe");
    }


}
