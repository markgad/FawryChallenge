import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Cart {
    Map<Product, Integer> items = new LinkedHashMap<>();

    void add(Product product, int qty) {
        if (product.isExpired()) throw new RuntimeException( "expired");
        if (qty > product.quantity) throw new RuntimeException( "out of stock");
        items.put(product, items.getOrDefault(product, 0) + qty);
    }

    void checkout(Customer customer) {
        if (items.isEmpty()) {
            System.out.println("empty cart");
            return;
        }

        List<Shippable> toShip = new ArrayList<>();
        double subtotal = 0;

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            subtotal += p.price * qty;

            if (p.weight != null) {
                for (int i = 0; i < qty; i++) {
                    toShip.add(p);
                }
            }
        }

        double totalWeight = 0;
        for (Shippable item : toShip) {
            totalWeight = item.getWeight();
        }
        double shipping = totalWeight * 30; //idk what rate to use for the shipping so used one that gave closest to the example

        double total = subtotal + shipping;

        if (customer.balance < total) {
            System.out.println("low balance");
            return;
        }

        customer.balance -= total;
        items.forEach((p, qty) -> p.quantity -= qty);
        ShippingService.ship(toShip);
        printReceipt(subtotal, shipping, total);
    }

    void printReceipt(double subtotal, double shipping, double total) {
        System.out.println("** Checkout receipt **");
        items.forEach((p, qty) ->
                System.out.println(qty + "x " + p.name + " " + (int)(p.price * qty)));
        System.out.println("----------------------");
        System.out.println("Subtotal         " + (int)subtotal);
        System.out.println("Shipping         " + (int)shipping);
        System.out.println("Amount           " + (int)total);
    
    }
    }
