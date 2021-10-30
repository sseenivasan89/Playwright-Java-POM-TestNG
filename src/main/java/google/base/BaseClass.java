package google.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class BaseClass {
	Playwright playwright;
	BrowserType browserType;
	Browser browser;
	BrowserContext context;
	protected Page page;

	public void launchPlaywright(String browserName, String headless) {
		playwright = Playwright.create();
		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("msedge")
				|| browserName.equalsIgnoreCase("chromium")) {
			browserType = playwright.chromium();
		} else if (browserName.equalsIgnoreCase("webkit")) {
			browserType = playwright.webkit();
		}
		if (headless.equalsIgnoreCase("true")) {
			browser = browserType.launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(true));
		} else {
			browser = browserType.launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(false));
		}
		context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1400, 700));
		page = browser.newPage();
		System.out.println("**** Browser Name and Version is : " + browserName + " : " + browser.version());
	}

	public void launchApplication(String url) {
		page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
		page.waitForSelector("[id='txtUsername']");
	}

	public void closePlaywright() {
		page.close();
		browser.close();
		playwright.close();
	}
}
