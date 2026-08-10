package testCase;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class TestCase1 {
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();

		LaunchOptions lp = new LaunchOptions();
		lp.setHeadless(false);

		Browser browser = playwright.webkit().launch(lp);
		BrowserContext context_1 = browser.newContext();
		Page page = context_1.newPage();
		page.navigate("https://practicetestautomation.com/practice-test-login/");

		// Fill login form
		page.getByLabel("username").fill("student");
		page.getByLabel("password").fill("Password123");

		System.out.println("Username: " + page.getByLabel("username").inputValue());
		System.out.println("Password: " + page.getByLabel("password").inputValue());
		System.out.println("Credential Entered");

		page.locator("#submit").click();

		// Wait for navigation to complete
		page.waitForLoadState(LoadState.NETWORKIDLE);

		// Check if login was successful
		System.out.println("Current URL: " + page.url());

		// ✓ CORRECT: Check if URL changed to success page
		if(page.url().contains("logged-in-successfully")) {
			System.out.println("Logged In Successfully ✓");

			page.getByText("Log out").click();
			System.out.println("Logged Out successfully");
			System.out.println("Test Passed ✓");
		}
		else {
			// Check for error message if login failed
			try {
				if (page.locator("#error").isVisible()) {
					String errorMessage = page.locator("#error").innerText();
					System.out.println("Error: " + errorMessage);

					if (errorMessage.contains("Your username is invalid!")) {
						System.out.println("Invalid Username");
					} else if (errorMessage.contains("Your password is invalid!")) {
						System.out.println("Invalid Password");
					}
				}
			} catch (Exception e) {
				System.out.println("No error message found");
			}
			System.out.println("Login Failed ✗");
		}

		page.waitForTimeout(2000);
		playwright.close();
	}
}
