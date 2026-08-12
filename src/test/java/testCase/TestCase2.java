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

    //Pmi qr code
    page.locator("#modePMIBtn").click();
    System.out.println("PMI Section Opened");
    page.locator("#pmiF2").fill("9874651231");
    page.locator("#pmiF4").fill("8451789465");
    page.locator("#pmiF6").fill("DK8946");
    page.locator("#pmiCreateBtn").click();
    System.out.println("SKU - Serial number - Material Code Entered");

    page.locator("#generateBtn").click();
    System.out.println("Code Generated");
    System.out.println("PMI Test Passed");

    //plu code
    page.locator("#modePLUBtn").click();
    page.locator("#pluSkuInput").fill("3146009846131");
    page.locator("#pluWeightInput").fill("314600");
    System.out.println("SKU + weight Entered");
    page.locator("#pluCreateBtn").click();


    page.locator("#generateBtn").click();
    System.out.println("Code Generated");
    System.out.println("PLU Test Passed");

    page.waitForTimeout(3000);
    playwright.close();
}

}
