package cucumberserenity;

import java.util.HashMap;

import org.junit.BeforeClass;

import com.petstore.model.PetClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSerenitySteps {
	
	@Step("Creating user with username: {0}, firstName: {1}, lastName: {2}, email: {3}, password: {4}, phone: {5}")
	public ValidatableResponse createUser(String username, String firstName, String lastName, String email, String password, String phone) {
		PetClass user = new PetClass();
		
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		
		return SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when()
		.body(user)
		.post("/user")
		.then();
	}
	
	@Step("Get information of the user with username: {0}")
	public ValidatableResponse getUserByUsername(String username){
		return SerenityRest
		.rest()
		.given()
		.when()
		.get("/user/" + username).then();
	}
	
	@Step("Get updated information of the user with username: {0}")
	public HashMap<String,Object> getUpdatedUserInfoByUsername(String username){
		String p1 = "findAll{it.username=='";
		String p2 = "'}.get(0)";
		return SerenityRest
		.rest()
		.given()
		.when()
		.get("/user/" + username)
		.then()
		.statusCode(200)
		.extract()
		.path(p1+username+p2);
	}
	
	@Step("Update user information username: {0}, firstName: {1}, lastName: {2}, email: {3}, password: {4}, phone: {5}")
	public ValidatableResponse updateUser(String username, String lastName, String firstName, String email, String password, String phone) {
		PetClass user = new PetClass();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		
		return SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when()
		.body(user)
		.put("/user/" + username)
		.then();
	}
	
	@Step("Login user with username: {0}, password: {1}")
	public ValidatableResponse loginUser(String username, String password) {
		PetClass user = new PetClass();
		user.setUsername(username);
		user.setPassword(password);
		
		return SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when()
		.body(user)
		.get("/user/login?username=" + username + "&password=" + password)
		.then();
	}
	
	@Step("Deleting user information with username: {0}")
	public  ValidatableResponse deleteUser(String username) {
		return SerenityRest.rest().given()
				.when()
				.delete("/user/" + username)
				.then();
	}
	
}
