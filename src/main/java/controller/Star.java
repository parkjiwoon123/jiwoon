package controller;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Star {

    public static void main(String[] args) {
        StarVO result = testWebScraping();
        System.out.println("Result: " + result);
    }

    public static StarVO testWebScraping() {
        // WebDriver 설정
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu");
        WebDriver driver = new ChromeDriver(options);

        // 스크래핑할 URL 리스트
        List<String> urls = new ArrayList<>();
        urls.add("https://place.map.kakao.com/16414503");
        urls.add("https://place.map.kakao.com/8083654");
        urls.add("https://place.map.kakao.com/20553565");
        urls.add("https://place.map.kakao.com/2026803402");

        // 각 URL에서 가져온 평점을 저장할 리스트
        List<String> ratings = new ArrayList<>();

        // WebDriver를 사용하여 각 URL에서 평점을 가져옴
        for (String url : urls) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                driver.get(url);

                // CSS 선택자를 사용하여 평점 요소를 찾음
                String cssSelector = "#mArticle > div.cont_essential > div:nth-child(1) > div.place_details > div > div > a:nth-child(3) > span.color_b";
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));

                // 평점 값을 리스트에 추가
                ratings.add(element.getText());
                System.out.println("URL: " + url + " - Rating: " + element.getText());
            } catch (Exception e) {
                System.out.println("Error while processing URL: " + url);
                e.printStackTrace();
                ratings.add("N/A"); // 에러 발생 시 평점을 "N/A"로 설정
            }
        }

        // WebDriver 종료
        driver.quit();

        // StarVO 객체를 생성하여 평점들을 반환
        return new StarVO(getRating(ratings, 0), getRating(ratings, 1), getRating(ratings, 2), getRating(ratings, 3));
    }

    // 리스트에서 인덱스에 해당하는 평점을 가져오는 메서드
    private static String getRating(List<String> ratings, int index) {
        if (index < ratings.size()) {
            return ratings.get(index);
        }
        return "N/A"; // 해당 인덱스의 평점이 없는 경우
    }
}
