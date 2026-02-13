package fr.epita.assistants.yakamon_testsuite;

import fr.epita.assistants.yakamon.presentation.api.request.MoveRequest;
import fr.epita.assistants.yakamon.utils.Direction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YakamonTest {


    @Test
    @Order(1)
    public void testStartGame() {
        given()
                .when().post("/start")
                .then()
                .statusCode(200)
                .body("map", notNullValue());
    }

    @Test
    @Order(2)
    public void testInitialState() {
        given()
                .when().get("/player")
                .then()
                .statusCode(200)
                .body("x", is(0))
                .body("y", is(0));
    }

    @Test
    @Order(3)
    public void testInitialInventory() {
        given()
                .when().get("/inventory")
                .then()
                .statusCode(200)
                .body("$", notNullValue())
                .body("size()", is(1));
    }

    @Test
    @Order(4)
    public void testYakadexContent() {
        given()
                .when().get("/yakadex")
                .then()
                .statusCode(200)
                .body("size()", org.hamcrest.Matchers.greaterThanOrEqualTo(5));
    }

    @Test
    @Order(5)
    public void testMoveEast() {
        MoveRequest req = new MoveRequest();
        req.direction = Direction.RIGHT;

        given()
                .contentType(ContentType.JSON)
                .body(req)
                .when().post("/move")
                .then()
                .statusCode(200)
                .body("x", is(1))
                .body("y", is(0));
    }

    @Test
    @Order(6)
    public void testMoveTooFast_Returns429() {
        MoveRequest req = new MoveRequest();
        req.direction = Direction.RIGHT;

        given()
                .contentType(ContentType.JSON)
                .body(req)
                .when().post("/move")
                .then()
                .statusCode(429);
    }

    @Test
    @Order(7)
    public void testMoveInvalidJson_Returns400() {
        String badJson = "{\"direction\": \"NOWHERE\"}";

        given()
                .contentType(ContentType.JSON)
                .body(badJson)
                .when().post("/move")
                .then()
                .statusCode(400);
    }

    @Test
    @Order(8)
    public void testTeamInitiallyEmpty() {
        given()
                .when().get("/team")
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }

    @Test
    @Order(9)
    public void testCatchWhereNoYakamon_Returns400() {
        given()
                .when().post("/catch")
                .then()
                .statusCode(400);
    }
/*
    @Test
    @Order(10)
    public void testRenameUnknownYakamon_Returns404() {
        String fakeUuid = UUID.randomUUID().toString();

        RenameYakamonRequest req = new RenameYakamonRequest();
        req.name = "Rex";

        given()
                .contentType(ContentType.JSON)
                .body(req)
                .when().patch("/team/" + fakeUuid + "/rename")
                .then()
                .statusCode(404);
    }*/

    @Test
    @Order(11)
    public void testReleaseUnknownYakamon_Returns404() {
        String fakeUuid = UUID.randomUUID().toString();

        given()
                .when().delete("/release/" + fakeUuid)
                .then()
                .statusCode(404);
    }
}