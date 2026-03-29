package com.devopscapstone.simple_bank;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleBankUITest {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	void setupTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");

		driver = new ChromeDriver(options);
		// Initialize a wait of up to 5 seconds
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@AfterEach
	void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	void testDepositAndWithdrawalUI() {
		driver.get("http://localhost:" + port + "/");

		// 1. Make the Deposit
		WebElement depositInput = driver.findElement(By.id("deposit-input"));
		WebElement depositBtn = driver.findElement(By.id("deposit-btn"));
		depositInput.sendKeys("150.00");
		depositBtn.click();

		// Wait for the DOM to update to 150.00 before asserting
		wait.until(ExpectedConditions.textToBe(By.cssSelector(".balance span"), "150.00"));
		WebElement balanceSpan = driver.findElement(By.cssSelector(".balance span"));
		assertEquals("150.00", balanceSpan.getText());

		// 2. Make the Withdrawal
		WebElement withdrawInput = driver.findElement(By.id("withdraw-input"));
		WebElement withdrawBtn = driver.findElement(By.id("withdraw-btn"));
		withdrawInput.sendKeys("50.00");
		withdrawBtn.click();

		// Wait for the DOM to update to 100.00 before asserting
		wait.until(ExpectedConditions.textToBe(By.cssSelector(".balance span"), "100.00"));
		balanceSpan = driver.findElement(By.cssSelector(".balance span"));
		assertEquals("100.00", balanceSpan.getText());
	}
}