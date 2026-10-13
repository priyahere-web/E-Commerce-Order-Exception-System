
import java.util.HashMap;
import java.util.Map;

class InvalidProductException extends Exception {
    InvalidProductException(String message) {
        super(message);
    }
}

class InsufficientStockException extends Exception {
    InsufficientStockException(String message) {
        super(message);
    }
}

public class Main {
    static Map<Integer, Integer> stock = new HashMap<>();

    static {
        stock.put(101, 5);
        stock.put(102, 10);
        stock.put(103, 3);
    }

    static void placeOrder(int productId, int quantity)
            throws InvalidProductException,
                   InsufficientStockException {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero.");
        }

        if (!stock.containsKey(productId)) {
            throw new InvalidProductException(
                "Invalid product ID!");
        }

        if (quantity > stock.get(productId)) {
            throw new InsufficientStockException(
                "Insufficient stock!");
        }

        stock.put(productId, stock.get(productId) - quantity);
        System.out.println("Order placed successfully!");
        System.out.println("Product ID: " + productId);
        System.out.println("Quantity: " + quantity);
        System.out.println("Remaining stock: " + stock.get(productId));
    }

    public static void main(String[] args) {
        try {
            placeOrder(102, 2);
        } catch (InvalidProductException |
                 InsufficientStockException |
                 IllegalArgumentException e) {
            System.out.println("Order failed: " + e.getMessage());
        }
    }
}
