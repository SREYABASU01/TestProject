package APIPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExamplesAPI {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzcxMzYyNjMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NzE3OTQ2MywidXNlcklkIjoiNTEwOSJ9.-mM2qmpixkezPSyKho7JjInUJ6EHqfTv-cfD4tNgJ5M";

    @Test
    public void createEmployee(){
        //prepare the request
        RequestSpecification request = given().header("Content-Type","application/json")
                .header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"ramanand\",\n" +
                        "  \"emp_lastname\": \"ashubh\",\n" +
                        "  \"emp_middle_name\": \"siddhant\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2021-01-12\",\n" +
                        "  \"emp_status\": \"probation\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}");

        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }

}
