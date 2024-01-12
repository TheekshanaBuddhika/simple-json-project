package il.ijse.gdse66.simple_json_project;


import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "BindingServlet" , urlPatterns = "/test2", loadOnStartup = 0 ,initParams = {
        @WebInitParam(name = "username" , value = "root"),
        @WebInitParam(name = "password" , value = "dragon25"),
        @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/aad")
})
public class JsonBindServlet {
}
