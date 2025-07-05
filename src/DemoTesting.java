
import java.time.LocalDate;


public class DemoTesting {
    public static void main(String[] args) {
        Product cheese = new Product("Cheese", 100, 10, LocalDate.now().plusDays(5), 0.5);
        Product biscuits = new Product("Biscuits", 150, 5, LocalDate.now().plusDays(10), 0.4);
        Product tv = new Product("TV", 5000, 3, 15.0);
        Product scratchCard = new Product("scratch card", 50, 100);
        Product Mish = new Product("gebna adeema (mish)", 100, 5, LocalDate.now().minusDays(1), 0.4);

        Customer customer = new Customer("Mark", 1000);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        cart.checkout(customer);

        System.out.println("\n=== Empty cart test ===");
        new Cart().checkout(customer);

        System.out.println("\n=== Poor customer test ===");
        Customer markbardo = new Customer("mark bardo", 10);
        Cart expensiveCart = new Cart();
        expensiveCart.add(tv, 1);
        expensiveCart.checkout(markbardo);

        System.out.println("\n=== Out of stock test ===");
        try {
            Customer mark3 = new Customer("mark3", 10000);
            Cart bigCart = new Cart();
            bigCart.add(tv, 4);
            bigCart.checkout(mark3);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Expired product test ===");
        try {
            Cart badCart = new Cart();
            badCart.add(Mish, 1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}