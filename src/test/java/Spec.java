import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class Spec {
    static String login = "test@test34324234234.com";
    static String password = "2Password";
    public static RequestSpecification request = with()
            .baseUri("https://demowebshop.tricentis.com")
            .log().all()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParam("Email", login)
            .formParam("Password", password);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
//            .expectBody(containsString("success"))
            .build();
}
