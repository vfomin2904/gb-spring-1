package ru.fomin.servlets;

import ru.fomin.entities.Product;
import ru.fomin.repositories.ProductRepo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ProductListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepo repository = new ProductRepo();

        repository.save(new Product("Orange", 20.0));
        repository.save(new Product("Banana", 40.0));
        repository.save(new Product("Apple", 10.0));
        repository.save(new Product("Cherry", 30.0));
        sce.getServletContext().setAttribute("repository", repository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
