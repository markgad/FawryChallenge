
import java.util.*;


class ShippingService {
    public static void ship(List<Shippable> items) {
        if (items.isEmpty()) return;
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> itemCount = new LinkedHashMap<>();
        Map<String, Double> weights = new HashMap<>();

        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            itemCount.put(name, itemCount.getOrDefault(name, 0) + 1);
            if (!weights.containsKey(name)) weights.put(name, weight);
            totalWeight += weight;
        }

        itemCount.forEach((name, count) -> {
            int grams = (int)(weights.get(name) * 1000);
                    System.out.println(count + "x " + name + " " + grams + "g");
                });

            System.out.println("Total package weight " + String.format("%.1f", totalWeight) + "kg");
        }
    }