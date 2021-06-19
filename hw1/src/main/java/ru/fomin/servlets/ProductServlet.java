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

@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductRepo repository;

    @Override
    public void init() throws ServletException {
        repository = (ProductRepo)getServletContext().getAttribute("repository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<table><thead><tr><td>Product</td><td>Price</td></tr></thead><tbody>");
        Map<Long, Product> products = repository.getRepository();
        for(Map.Entry<Long, Product> entry : products.entrySet()){
            String line = String.format("<tr><td>%s</td><td>%s</td></tr>", entry.getValue().getName(), entry.getValue().getPrice());
            resp.getWriter().println(line);

        }
        resp.getWriter().println("</tbody></table>");
    }
}
