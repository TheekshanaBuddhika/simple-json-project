package il.ijse.gdse66.simple_json_project;


import jakarta.json.*;
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

/*

//        with json-p libraries
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        System.out.println(jsonObject);

        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        JsonObject address = jsonObject.getJsonObject("address");

        int no = address.getInt("no");
        String street = address.getString("street");
        String city = address.getString("city");

        JsonArray contacts = jsonObject.getJsonArray("contacts");

        String string = contacts.getString(0);
        String string1 = contacts.getString(1);

        System.out.println(id + "\n" + name + "\n" + no + "\n" + street + "\n" + city + "\n" + string + "\n" + string1);

*/
//----------------------------------------------json write ------------------------------------------------

//        using Json -P (Processing) library
        //method 1
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", "c008");
        builder.add("name", "kamal");

        JsonObjectBuilder address = Json.createObjectBuilder();
        address.add("no", 1);
        address.add("street","street");
        address.add("city","galle");

        builder.add("address",address);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        arrayBuilder.add("0123456789");
        arrayBuilder.add("5555555555");

        builder.add("contacts",arrayBuilder);

        JsonObject build = builder.build();
        resp.getWriter().write(build.toString());

        //method 2

        JsonObject build1 = Json.createObjectBuilder()
                .add("id", "c008")
                .add("name", "kamal")
                .add("address", Json.createObjectBuilder().add("no", 1).add("street","street").add("city","galle"))
                .add("contacts",Json.createArrayBuilder().add("0123456789").add("5555555555"))
                .build();

        resp.getWriter().write(build1.toString());

    }
}
