package ru.fomin.servlets;

import ru.fomin.entities.Product;
import ru.fomin.repositories.ProductRepo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    private ProductRepo repository;

    @Override
    public void init() throws ServletException {
        repository = (ProductRepo)getServletContext().getAttribute("repository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getPathInfo() == null){
            resp.getWriter().println("<table><thead><tr><td>Product</td><td>Price</td></tr></thead><tbody>");
            Map<Long, Product> products = repository.getRepository();
            for(Map.Entry<Long, Product> entry : products.entrySet()){
                Product product = entry.getValue();
                String line = String.format("<tr><td><a href='%s/products/%s'>%s</a></td><td>%s</td></tr>",
                        req.getContextPath(), product.getId(), product.getName(), product.getPrice());
                resp.getWriter().println(line);

            }
            resp.getWriter().println("</tbody></table>");
        } else{
            Long id = Long.parseLong(req.getPathInfo().substring(1));
            Product product = repository.getProductById(id);

            resp.getWriter().println("Name: "+product.getName());
            resp.getWriter().println("Price: "+product.getPrice());
        }

    }
}
