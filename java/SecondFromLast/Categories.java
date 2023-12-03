package SecondFromLast;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Categories {

    Cookies cookies;

    @BeforeClass
    void Login() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "turkeyts");
        credentials.put("password", "TechnoStudy123");
        credentials.put("rememberMe", "true");

        cookies = given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .when()
                .post("https://test.mersys.io/auth/login")
                .then()
                .log().body()
                .extract().response().getDetailedCookies();

    }

    public String randomCategoryName() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    CategoriesClass positionResponse = new CategoriesClass();

    @Test( )
    void addPosition() {

        CategoriesClass positionRequest = new CategoriesClass();

        positionRequest.setName(randomCategoryName());



        positionResponse = given()
                .body(positionRequest)
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .when()
                .post("https://test.mersys.io/school-service/api/position-category")
                .then()
                .statusCode(201)
                .log().body()
                .extract().jsonPath().getObject("", CategoriesClass.class);

    }

    @Test(dependsOnMethods = "addPosition")
    void updatePosition() {

        positionResponse.setName(randomCategoryName());

        given()
                .body(positionResponse)
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .when()
                .put("https://test.mersys.io/school-service/api/position-category")
                .then()
                .statusCode(200);

    }

    @Test(dependsOnMethods = "addPosition")
    void deletePosition() {
        given()
                .pathParam("categoryId", positionResponse.getId())
                .cookies(cookies)
                .when()
                .delete("https://test.mersys.io/school-service/api/position-category/{categoryId}")
                .then()
                .statusCode(204);
    }

}
































