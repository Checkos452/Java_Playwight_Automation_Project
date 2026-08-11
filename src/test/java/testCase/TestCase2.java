package testCase;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;


public class TestCase2 {
public static void main(String[] args){
    Playwright playwright = Playwright.create();

    LaunchOptions lp = new LaunchOptions();
    lp.setHeadless(false);

    Browser browser = playwright.chromium().launch(lp);
    BrowserContext context_1 = browser.newContext();
    Page page = context_1.newPage();
    page.navigate("https://alternative-barcode.netlify.app/");

    System.out.println("Website Opened");
    //page.locator(".continue-prompt-text").click();

    page.locator("#codesInput").fill("8978512164");
    page.locator("#generateBtn").click();
    System.out.println("barcode generated");
    page.locator("#viewOne").click();
    System.out.println("View changed to One");
    System.out.println("Test Passed");

    //will next work on pmi and plu codes automation and add conditinal statements aswell.



    page.waitForTimeout(2000);
    playwright.close();
}

}
