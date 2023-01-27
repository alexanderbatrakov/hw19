import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import lombok.AddToCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DemoShopAPITestsGroovy {

    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
        Configuration.baseUrl = "https://demowebshop.tricentis.com";
    }

    @Test
    void addShoesToCard(){

        String data = "product_attribute_28_7_10=25&product_attribute_28_1_11=29&addtocart_28.EnteredQuantity=1";
        String authorizationCookie =
                given()
                        .spec(Spec.request)
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(302)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH");
//открываем минимальный юнит программы,т.к куки могут быть установлены только после открытия сайта
        open("/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(
                new Cookie("NOPCOMMERCE.AUTH", authorizationCookie));

                given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", authorizationCookie)
                .body(data)
                .when()
                .post("/addproducttocart/details/28/1")
                .then()
                .statusCode(200)
             .body("success",is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

//        Assertions.assertEquals(true, addToCard.getSuccess());
//        Assertions.assertEquals("The product has been added to your <a href=\"/cart\">shopping cart</a>", addToCard.getMessage());

    }

}
