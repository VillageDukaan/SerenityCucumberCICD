package cucumbersteps;

import io.restassured.RestAssured;
import net.thucydides.core.annotations.Steps;
import utils.TestUtils;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberserenity.UserSerenitySteps;


public class UserSteps {
	
	static String username = TestUtils.getRandomValue();
	static String email = null;
	
	@Steps
	UserSerenitySteps steps;
	
	@When("^I create a new user with (.*) (.*) (.*) (.*) (.*) (.*)$")
	public void i_create_a_user_with(String _username, String firstName, String lastName, String _email, String password, String phone) {
		username = username + _username;
		email = TestUtils.getRandomValue() + _email;
		
		steps.createUser(username, firstName, lastName, email, password, phone)
		.assertThat()
		.statusCode(200);
	}
	
	@Then("^I verify that the user with (.*) is registered$")
	public void verify_user_created(String _username){
		steps.getUserByUsername(username)
		.assertThat()
		.statusCode(200);
	}

	
	@When("I login using (.*) and (.*)")
	public void i_login_using(String _username, String password) {
	    steps.loginUser(username, password)
	    .assertThat()
	    .statusCode(200);
	}
	
	@When("^I change user with (.*) (.*) (.*) (.*) (.*) (.*)$")
	public void i_change_user(String _username, String firstName, String lastName, String _email, String password, String phone) {
		email = TestUtils.getRandomValue() + _email;
		
		steps.updateUser(username, lastName, firstName, _email, password, phone)
		.assertThat()
		.statusCode(200);
	}
	
	@Then("^I verify that the user with (.*) info (.*) are updated$")
	public void verify_user_updated(String _username, String firstName){
		HashMap<String, Object> actualValue = steps.getUpdatedUserInfoByUsername(username);
		assertThat(actualValue, hasValue(firstName));
	}

	@When("^I hit the delete endpoint with (.*)$")
	public void i_hit_the_delete_endpoint(String _username) {
		steps.deleteUser(username)
		.assertThat()
		.statusCode(200);
	}
	
	@Then("^I verify that the user with (.*) is removed from system$")
	public void verify_user_deleted(String _username){
		
		steps.getUserByUsername(username)
		.assertThat()
		.statusCode(404);
	}
}
