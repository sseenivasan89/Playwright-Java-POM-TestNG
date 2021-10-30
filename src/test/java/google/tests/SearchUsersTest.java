package google.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import google.base.BaseClass;
import google.pages.LoginPage;
import google.pages.UsersPage;

public class SearchUsersTest extends BaseClass {

	LoginPage loginPage;
	UsersPage usersPage;

	@BeforeClass
	@Parameters({ "url", "browserName" , "headless"})
	public void playwrightStartup(@Optional("https://opensource-demo.orangehrmlive.com/") String url,
			@Optional("chrome") String browserName, @Optional("false") String headless) {
		launchPlaywright(browserName, headless);
		launchApplication(url);
	}

	@Test(priority = 1)
	@Parameters({ "username", "password" })
	public void loginTest(@Optional("Admin") String username, @Optional("admin123") String password) {
		loginPage = new LoginPage(page);
		boolean isLoginSuccess = loginPage.login(username, password);
		Assert.assertEquals(isLoginSuccess, true);
	}

	@Test(priority = 2, dependsOnMethods = "loginTest")
	public void searchUsersTest() {
		usersPage = new UsersPage(page);
		usersPage.gotoUsersPage();
		boolean isUserRoleMatch = usersPage.selectUserAndSearch();
		Assert.assertEquals(isUserRoleMatch, true);
	}

	@AfterClass
	public void playwrightClose() {
		closePlaywright();
	}
}
