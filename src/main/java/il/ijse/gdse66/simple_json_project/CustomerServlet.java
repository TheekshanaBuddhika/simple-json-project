package il.ijse.gdse66.simple_json_project;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "Customer", urlPatterns = "/customers", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "username" , value = "root"),
        @WebInitParam(name = "password" , value = "dragon25"),
        @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/aad")
})
public class CustomerServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        /*catch request parameter as a String*/
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        System.out.printf("id=%s, name=%s, address=%s\n", id,name,address);

        /*create a database connection and save data in database*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            PreparedStatement stm = connection.prepareStatement("INSERT INTO customer(id, name, address) VALUES (?,?,?)");

            stm.setString(1,id);
            stm.setString(2, name);
            stm.setString(3, address);

            stm.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(connection !=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from customer");
            String JsonArray = "";
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                System.out.println(id + " " + name + " " + address);
                String jsonobj = "{\"id\": \""+id +"\","+"\"name\":\""+name+"\","+"\"address\":\""+address + "\"}";
                JsonArray += jsonobj + ",";
            }
            JsonArray = "["+JsonArray.substring(0,JsonArray.length()-1)+"]";
            System.out.println(JsonArray);
            writer.write(JsonArray);
            resp.setContentType("application/json");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(connection !=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}