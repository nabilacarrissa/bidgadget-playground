package com.bidgadget;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class E2ERobotTest {

    @Test
    void robotShouldRunEndToEnd() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {

            System.out.println("Membuka halaman bidding...");
            driver.get("http://localhost:8080/bid.php?auction_id=101&item=MacBook");

            Thread.sleep(3000);

            System.out.println("Mengisi jumlah penawaran...");

            WebElement input = driver.findElement(By.id("bid_amount"));
            input.click();

            Thread.sleep(1000);

            // Ketik perlahan seperti manusia
            input.sendKeys("3");
            Thread.sleep(500);

            input.sendKeys("0");
            Thread.sleep(500);

            input.sendKeys("0");
            Thread.sleep(500);

            input.sendKeys("0");
            Thread.sleep(500);

            input.sendKeys("0");

            Thread.sleep(3000);

            System.out.println("Klik tombol Kirim Penawaran...");

            driver.findElement(By.id("submitBid")).click();

            WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement result =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("status-message")
                            )
                    );

            String text = result.getText();

            System.out.println("HASIL ROBOT: " + text);

            Thread.sleep(3000);

            assertTrue(
                    text.contains("ACCEPTED")
                    || text.contains("REJECTED"),
                    "Status bidding tidak ditemukan"
            );

            System.out.println("Test berhasil!");

            Thread.sleep(3000);

        } finally {

            System.out.println("Menutup browser...");
            Thread.sleep(2000);

            driver.quit();
        }
    }
}