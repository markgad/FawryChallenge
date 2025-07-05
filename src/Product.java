import java.time.LocalDate;

class Product implements Shippable {
    String name;
    double price;
    int quantity;
    LocalDate expiryDate;
    Double weight;

    public Product(String name, double price, int quantity, LocalDate expiryDate, Double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    public Product(String name, double price, int quantity) {
        this(name, price, quantity, null, null);
    }

    public Product(String name, double price, int quantity, Double weight) {
        this(name, price, quantity, null, weight);
    }

    boolean isExpired() {
        return expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight != null ? weight : 0.0;
    }
}
