package MyPackage;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import com.jayway.jsonpath.JsonPath;

@Listeners(MyPackage.ListenerTest.class)
public class RestAssuredTest{
	
	private String baseURI;
	
	@BeforeTest
	public void setBaseURI() {
		RestAssured.baseURI = "https://reqres.in/api/users";
		baseURI = RestAssured.baseURI;

	}
	@Test(priority = 1)
	public void getRequest() {
		RestAssured.given().queryParam("page", 2).log().all().when().get(baseURI).then().statusCode(200);
	}
	
	@Test (priority = 2)
	public void postRequest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "morpheus");
		jsonObject.put("job", "leader");
		Response response = RestAssured.given().header("Content-Type","application/json").log().all().when().body(jsonObject.toString()).post(baseURI);
		
		String body = response.body().asString();
		Assert.assertEquals(response.getStatusCode(), 201, "POST Call status failed");
		Assert.assertEquals(JsonPath.read(body,"name"),"morpheus","POST call response not as expected for name");
		Assert.assertEquals(JsonPath.read(body,"job"),"leader","POST call response not as expected for job");
		Assert.assertFalse(JsonPath.read(body,"createdAt").equals(null),"POST call response not as expected for createdAt attribute");

	}

	@Test (priority = 3)
	public void putRequest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "morpheus");
		jsonObject.put("job", "leader");
		Response response = RestAssured.given().queryParam("page", 2).header("Content-Type","application/json").log().all().when().body(jsonObject.toString()).put(baseURI);
		
		String body = response.body().asString();
		Assert.assertEquals(response.getStatusCode(), 200, "POST Call status failed");
		Assert.assertEquals(JsonPath.read(body,"name"),"morpheus","PUT call response not as expected for name");
		Assert.assertEquals(JsonPath.read(body,"job"),"leader","PUT call response not as expected for job");
		Assert.assertFalse(JsonPath.read(body,"updatedAt").equals(null),"POST call response not as expected for updatedAt attribute");

	}
	@Test(priority = 4)
	public void deleteRequest() {
		RestAssured.given().queryParam("page", 2).log().all().when().delete(baseURI).then().statusCode(204);
	}
}
