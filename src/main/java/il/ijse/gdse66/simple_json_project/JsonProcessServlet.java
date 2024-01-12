package il.ijse.gdse66.simple_json_project;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ProcessServlet" , urlPatterns = "/test" , loadOnStartup = 0 ,initParams = {
        @WebInitParam(name = "username" , value = "root"),
        @WebInitParam(name = "password" , value = "dragon25"),
        @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/aad")
})
public class JsonProcessServlet extends HttpServlet {
    private String username;
    private String password;
    private String url;

    @Override
    public void init() throws ServletException {
        /*ServletConfig is used to get configuration information such as database url, mysql username and password*/
        ServletConfig sc = getServletConfig();
        username = sc.getInitParameter("username");
        password = sc.getInitParameter("password");
        url = sc.getInitParameter("url");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//------------------------------------------------json read ----------------------------------------------
//        without json library
//        catch parameters without using getparameter method
     /*   BufferedReader reader = req.getReader();
        String line = "";
        String json = "";

        while ((line=reader.readLine())!= null){
            json += line +"\n";
        }
        System.out.println(json);*/



//        with json-p libraries
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        System.out.println(jsonObject);



//----------------------------------------------json write ------------------------------------------------

//        using Json -P (Processing) library
        //method 1
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", "c008");
        builder.add("name", "kamal");
        builder.add("address", "colombo");
        JsonObject build = builder.build();
        resp.getWriter().write(build.toString());

        //method 2

        JsonObject build1 = Json.createObjectBuilder()
                .add("id", "c008")
                .add("name", "kamal")
                .add("address", "colombo")
                .build();

        resp.getWriter().write(build1.toString());

    }
}
