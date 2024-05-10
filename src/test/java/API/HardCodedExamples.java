package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    static String employee_id;
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzcxMzYyNjMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NzE3OTQ2MywidXNlcklkIjoiNTEwOSJ9.-mM2qmpixkezPSyKho7JjInUJ6EHqfTv-cfD4tNgJ5M";

    @Test
    public void bGetCreatedEmployee(){
        //storing the base uri to use further

        //preparing the request to get an employee
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

      //  System.out.println(response.asString());

        //prettyprint does the same as system.out.println
        response.prettyPrint();
        String emdId = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpIDs = emdId.contentEquals(employee_id);

        //adding assertion to get true return from boolean
        Assert.assertTrue(comparingEmpIDs);
        response.then().assertThat().statusCode(200);

        String middleName = response.jsonPath().getString("employee.emp_middle_name");
        Assert.assertTrue(middleName.contentEquals("MS"));
    }

    //to create an employee
    @Test
    public void aCreateEmployee(){

        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                                "  \"emp_firstname\": \"Zema\",\n" +
                                "  \"emp_lastname\": \"Hasim\",\n" +
                                "  \"emp_middle_name\": \"MS\",\n" +
                                "  \"emp_gender\": \"M\",\n" +
                                "  \"emp_birthday\": \"1998-02-18\",\n" +
                                "  \"emp_status\": \"Employee\",\n" +
                                "  \"emp_job_title\": \"QA\"\n" +
                                "}");

        Response response = preparedRequest.when().post("/createEmployee.php");

        response.prettyPrint();

        //we use jsonPath() to get specific information from the json object
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        //Assertion
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("MS"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
        response.then().assertThat().body("Employee.emp_job_title", equalTo("QA"));
    }

    @Test
    public void cUpdateEmployee(){
        //update the existing employee
        RequestSpecification preparedRequest = given().header("Authorization", token).
                header("Content-Type", "application/json").body("{\n" +
                        "  \"employee_id\": \""+ employee_id +"\",\n" +
                        "  \"emp_firstname\": \"Ecat\",\n" +
                        "  \"emp_lastname\": \"nico\",\n" +
                        "  \"emp_middle_name\": \"MSA\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2002-02-19\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"Consultant\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();

        //verification and validation
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void dGetUpdatedEmployee(){
        //storing the base uri to use further

        //preparing the request to get an employee
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id",employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        //  System.out.println(response.asString());

        //prettyprint does the same as system.out.println
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        String middleName = response.jsonPath().getString("employee.emp_middle_name");
        Assert.assertTrue(middleName.contentEquals("MSA"));
    }


    @Test
    public void eGetAllEmployees(){

        RequestSpecification preparedRequest = given().header("Authorization", token).
                header("Content-Type", "application/json");

        Response response = preparedRequest.when().get("/getAllEmployees.php");

        String allEmployees = response.prettyPrint();

        //creating the object of jsonpath class
        JsonPath js = new JsonPath(allEmployees);

        //retrieving the number of employees in response body
        int count = js.getInt("Employees.size()");
        System.out.println(count);

        //print all the employee id's from response
        for (int i =0; i<count; i++){
           String employeeIds =  js.getString("Employees["+ i +"].employee_id");
            System.out.println(employeeIds);


        }
    }

}
