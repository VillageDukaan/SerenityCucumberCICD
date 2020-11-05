package testbase;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class TestBase {
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
	}
}
