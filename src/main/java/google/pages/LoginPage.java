package google.pages;

import com.microsoft.playwright.Page;

import google.base.BaseClass;

public class LoginPage extends BaseClass {

	private final Page page;

	String usernameBox = "[id='txtUsername']";
	String passwordBox = "[id='txtPassword']";
	String loginButton = "//*[@id='btnLogin']";
	String welcomeProfileButton = "//*[@id='welcome']";

	public LoginPage(Page page) {
		this.page = page;
	}

	public boolean login(String username, String password) {
		boolean isLoginSuccess = false;
		page.fill(usernameBox, username);
		page.fill(passwordBox, password);
		page.click(loginButton);
		page.waitForSelector(welcomeProfileButton).isVisible();
		boolean isEnabled = page.isEnabled("input");
		if (isEnabled) {
			isLoginSuccess = true;
		}
		return isLoginSuccess;
	}
}
