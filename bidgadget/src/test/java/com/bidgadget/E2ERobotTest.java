package com.bidgadget;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class E2ERobotTest {

    @Test
    void robotShouldRunEndToEnd() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // 1. buka halaman
            driver.get("http://localhost:8000/bid.php?auction_id=101&item=MacBook");

            Thread.sleep(1000);

            // 2. isi bid
            WebElement input = driver.findElement(By.id("bid_amount"));
            input.clear();
            input.sendKeys("30000");

            // 3. submit
            driver.findElement(By.id("submitBid")).click();

            Thread.sleep(2000);

            // 4. ambil hasil
            WebElement result = driver.findElement(By.id("status-message"));

            String text = result.getText();
            System.out.println("HASIL ROBOT: " + text);

            // 5. validasi
            assertTrue(
                text.contains("ACCEPTED") || text.contains("REJECTED")
            );

        } finally {
            driver.quit();
        }
    }
}