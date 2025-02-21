// Voici ma version du code Refactorer 
import java.util.List;
import java.util.Map;

public interface PricingStrategy {
    double calculatePrice(Package pkg, boolean urgent);
}

public class StandardPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Package pkg, boolean urgent) {
        double price = pkg.getDistance() * 0.1;

        if (pkg.getWeight() > 10) price += 5;
        else if (pkg.getWeight() > 5) price += 3;

        if (urgent) price *= 1.5;
        
        return price;
    }
}

public class VIPPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Package pkg, boolean urgent) {
        return new StandardPricing().calculatePrice(pkg, urgent) * 0.8;
    }
}

public class BusinessPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Package pkg, boolean urgent) {
        return new StandardPricing().calculatePrice(pkg, urgent) * 0.9;
    }
}

public class PremiumPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Package pkg, boolean urgent) {
        return new StandardPricing().calculatePrice(pkg, urgent) * 0.7;
    }
}

public class Package {
    private final double weight;
    private final double distance;

    public Package(double weight, double distance) {
        if (weight < 0) throw new IllegalArgumentException("Invalid weight!");
        this.weight = weight;
        this.distance = distance;
    }

    public double getWeight() { return weight; }
    public double getDistance() { return distance; }
}



public class DeliveryService {
    private final Map<String, PricingStrategy> pricingStrategies;

    public DeliveryService(Map<String, PricingStrategy> strategies) {
        this.pricingStrategies = strategies;
    }

    public double calculateTotalPrice(List<Package> packages, String customerType, boolean urgent) {
        PricingStrategy pricingStrategy = pricingStrategies.getOrDefault(customerType, new StandardPricing());

        double total = packages.stream()
            .mapToDouble(pkg -> pricingStrategy.calculatePrice(pkg, urgent))
            .sum();

        if (packages.size() > 3) total *= 0.95;  // Réduction pour commandes en volume

        return total;
    }
}



public class InvoiceService {
    public void printInvoice(List<Package> packages, String customerType, DeliveryService deliveryService) {
        double price = deliveryService.calculateTotalPrice(packages, customerType, false);
        System.out.println("Total: " + price);

        if (price > 100) {
            System.out.println("Apply special discount next time!");
        }
    }
}

// Exemple d'utilisation du code 

public class Main {
    public static void main(String[] args) {

        Map<String, PricingStrategy> strategies = Map.of(
            "Standard", new StandardPricing(),
            "VIP", new VIPPricing(),
            "Business", new BusinessPricing(),
            "Premium", new PremiumPricing()
        );

        DeliveryService deliveryService = new DeliveryService(strategies);
        InvoiceService invoiceService = new InvoiceService();

        List<Package> packages = List.of(
            new Package(6, 50),
            new Package(12, 30),
            new Package(3, 10)
        );

        invoiceService.printInvoice(packages, "VIP", deliveryService);
    }
}
