package com.devopscapstone.simple_bank;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleBankUITest {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeEach
	void setupTest() {
		ChromeOptions options = new ChromeOptions();
		// The headless argument is CRUCIAL for GitHub Actions since it has no monitor
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");

		// Selenium Manager automatically handles the driver setup.
		driver = new ChromeDriver(options);
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

		WebElement depositInput = driver.findElement(By.id("deposit-input"));
		WebElement depositBtn = driver.findElement(By.id("deposit-btn"));

		depositInput.sendKeys("150.00");
		depositBtn.click();

		WebElement balanceSpan = driver.findElement(By.cssSelector(".balance span"));
		assertEquals("150.00", balanceSpan.getText());

		WebElement withdrawInput = driver.findElement(By.id("withdraw-input"));
		WebElement withdrawBtn = driver.findElement(By.id("withdraw-btn"));
		withdrawInput.sendKeys("50.00");
		withdrawBtn.click();

		balanceSpan = driver.findElement(By.cssSelector(".balance span"));
		assertEquals("100.00", balanceSpan.getText());
	}
}