package ru.fomin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.fomin.entities.Cart;
import ru.fomin.entities.Product;
import ru.fomin.repositories.ProductRepo;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Scanner scanner = new Scanner(System.in);
        ProductRepo repository = context.getBean("repository", ProductRepo.class);
        repository.save(new Product("Orange", 20.0));
        repository.save(new Product("Banana", 30.0));
        repository.save(new Product("Apple", 10.0));
        repository.save(new Product("Cherry", 40.0));
        repository.save(new Product("Water", 15.0));
        Cart cart = context.getBean("cart", Cart.class);
        while (scanner.hasNext()){
            String command = scanner.nextLine();

            if(command.equals("exit")){
                break;
            } else if(command.equals("list")){
               cart.showCart();
            } else if(command.startsWith("add")){
                String[] commandArgs = command.split(" ", 2);
                cart.add(Long.parseLong(commandArgs[1]));
            } else if(command.startsWith("remove")){
                String[] commandArgs = command.split(" ", 2);
                cart.delete(Long.parseLong(commandArgs[1]));
            }
        }
    }
}
