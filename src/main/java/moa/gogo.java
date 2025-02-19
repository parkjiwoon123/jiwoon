	package moa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class gogo {
    public static gogoVO scrapeData(int siteId, String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
        options.addArguments("--user-agent=" + userAgent);
        options.addArguments("referer=https://www.google.com/");

        WebDriver driver = new ChromeDriver(options);
        driver.get(url);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement nameElement, priceElement;
        String siteName;

        switch (siteId) {
            case 1: // Coupang
                nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"contents\"]/div/div[1]/div[3]/div[3]/h2")));
                priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"contents\"]/div/div[1]/div[3]/div[5]/div[1]/div/div[2]/span[1]/strong")));
                siteName = "Coupang";
                break;
            case 2: // GMarket
                nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"itemcase_basic\"]/div/h1")));
                priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"itemcase_basic\"]/div/div[5]/span[2]/strong")));
                siteName = "GMarket";
                break;
            case 3: // 3번 사이트
                nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"layBodyWrap\"]/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[2]/h1")));
                priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"layBodyWrap\"]/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[3]/div/div/ul/li/dl[1]/dd/strong/span[1]")));
                siteName = "Site3";
                break;
            default:
                driver.quit();
                throw new IllegalArgumentException("Invalid siteId: " + siteId);
        }

        String name = nameElement.getText();
        // Assuming price is formatted as a number with optional decimal places
        double price = Double.parseDouble(priceElement.getText().replaceAll("[^0-9.]", "")); 
        System.out.println(name+price);

        gogoVO result = new gogoVO();
        result.setName(name);
        result.setPrice(price);
        result.setUrl(url);
        result.setSite(siteName);

        driver.quit();
        return result;
    }
}
