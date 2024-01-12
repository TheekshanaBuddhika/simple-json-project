package il.ijse.gdse66.simple_json_project;


import il.ijse.gdse66.model.customer;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "BindingServlet" , urlPatterns = "/test2", loadOnStartup = 0 ,initParams = {
        @WebInitParam(name = "username" , value = "root"),
        @WebInitParam(name = "password" , value = "dragon25"),
        @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/aad")
})
public class JsonBindServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

// ------------------------------------------------------------       Java Object to JSON     ------------------------------------------------------------------------
        /*
        customer customer = new customer("C001", "kamal", "galle");
        Jsonb jsonb = JsonbBuilder.create();
        String s = jsonb.toJson(customer);
        resp.getWriter().write(s);
*/


/*
        ArrayList<customer> customers = new ArrayList<>();
        customers.add(new customer("C001", "kamal", "galle"));
        customers.add(new customer("C002", "kamal", "galle"));
        Jsonb jsonb = JsonbBuilder.create();
       // String s = jsonb.toJson(customers);
       // resp.getWriter().write(s);


        //because getWriter also a writer we can use
        PrintWriter writer =  resp.getWriter();
        jsonb.toJson(customers,writer);
*/
//  ------------------------------------------------------------      JSON to Java Object    ------------------------------------------------------------------------

        Jsonb jsb = JsonbBuilder.create();
      //  customer customer = jsb.fromJson(req.getReader(), customer.class);
      //  System.out.println(customer.toString());

        ArrayList arrayList = jsb.fromJson(req.getReader(),ArrayList.class);
        System.out.println(arrayList.toString());


    }
}
