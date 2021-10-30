package google.pages;

import java.util.List;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import google.base.BaseClass;

public class UsersPage extends BaseClass {

	private final Page page;

	String adminMenuButton = "[id='mainMenu']>ul>li:nth-child(1)";
	String userMenuButton = "[id='menu_admin_UserManagement']";
	String usersButton = "[id='menu_admin_viewSystemUsers']";
	String userRoleDropDownBox = "[id='searchSystemUser_userType']";
	String searchButton = "[id='searchBtn']";
	String userNamesList = "//*[@id='customerList']//*[@id='tableWrapper']//tbody/tr/td[2]/a";
	String userRolesList = "//*[@id='customerList']//*[@id='tableWrapper']//tbody/tr/td[3]";

	public UsersPage(Page page) {
		this.page = page;
	}

	public void gotoUsersPage() {
		page.waitForSelector(adminMenuButton).click();
		page.waitForSelector(userMenuButton).click();
		page.waitForSelector(usersButton).click();
	}

	public boolean selectUserAndSearch() {
		boolean isUserRoleMatch = false;
		page.waitForSelector(userRoleDropDownBox);
		page.selectOption(userRoleDropDownBox, "1");
		page.waitForSelector(searchButton).click();
		page.waitForSelector(userNamesList);
		List<ElementHandle> userNames = page.querySelectorAll(userNamesList);
		for (int a = 0; a < userNames.size(); a++) {
			System.out.println("User Name - " + (a + 1) + " : " + userNames.get(a).innerText());
		}
		System.out.println("___________________________________");
		List<ElementHandle> userRoles = page.querySelectorAll(userRolesList);
		for (int a = 0; a < userRoles.size(); a++) {
			System.out.println("User Role - " + (a + 1) + " : " + userRoles.get(a).innerText());
			if (userRoles.get(a).innerText().equalsIgnoreCase("Admin")) {
				isUserRoleMatch = true;
			}
		}
		System.out.println("___________________________________");
		return isUserRoleMatch;
	}
}
