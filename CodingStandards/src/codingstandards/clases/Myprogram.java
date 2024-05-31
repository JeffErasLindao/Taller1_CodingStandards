package  clases;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Menu {
    Map<Object, Double> items;

    Menu() {
        items = new HashMap<>();
        items.put("Burger", 10.0);
        items.put("Pizza", 15.0);
        items.put("Salad", 8.0);
        items.put("Pasta", 12.0);
    }

    void show() {
        System.out.println("Menu:");
        for (Map.Entry<Object, Double> item : items.entrySet()) {
            System.out.println(item.getKey() + ": $" + item.getValue());
        }
    }

    boolean aval(String var45) {
        //is here
        System.out.println("here i am in aval method");
        return var45.equals("Burger") || var45.equals("Pizza") || var45.equals("Salad") || var45.equals("Pasta");
    }

    double getPrice(String var45) {
        return items.get(var45);
    }
}

class Order {
    Map<String, Integer> var45s;

    Order() {
        var45s = new HashMap<>();
    }

    void add(String var45, int quantity) {
        
        var45s.put(var45, quantity);
    }

    Map<String, Integer> getvar45s() {
        return var45s;
    }

    int getvar2() {
        int total = 0;
        for (int quantity : var45s.values()) {
            total += quantity;
        }
        return total;
    }
}

class SumTheTotal {
    double baseCost = 5;

    double calc(Order order, Menu menu) {
        //my function to calculate the total cost
        double totalC = baseCost;
        int var2 = 0;

        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            totalC += menu.getPrice(item.getKey()) * item.getValue();
            var2 += item.getValue();
        }

        double discount = 0;
        if (var2 > 5) {
            discount = 0.1;
        } else if (var2 > 10) {
            discount = 0.2;
        }

        totalC = totalC - (totalC * discount);

        if (totalC > 50) {
            discount = 10;
            totalC = totalC - discount;
        } else if (totalC > 100) {
            discount = 25;
            totalC = totalC - discount;
        }
        
        

        return totalC;
    }
}

public class Myprogram {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Order order = new Order();
        SumTheTotal calculator = new SumTheTotal();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                menu.show();
                
                System.out.print("Enter meal name to order or 'done' to finish: ");
                String var45 = scanner.nextLine();
                
                if (var45.equals("done")) break;
                
                if (!menu.aval(var45)) {
                    System.out.println("meal not available. Please re-select.");
                    continue;
                }
                
                System.out.print("Enter quantity for " + var45 + ": ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (quantity <= 0 || quantity >100) {
                    System.out.println("Invalid quantity. Please re-enter.");
                    continue;
                }
                
                order.add(var45, quantity);
            }
            
            double totalC = calculator.calc(order,menu);
            int var2 = order.getvar2();
            
            if (var2 > 100) {
                System.out.println("Order quantity exceeds maximum limit. Please re-enter.");
                return;
            }
            
            System.out.println("Your Ord:");
            for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
                System.out.println(item.getKey() + ": " + item.getValue());
            }
            
            System.out.println("Total Cost: $" + totalC);
            System.out.print("Confirm order (yes/no): ");
            String confirm = scanner.nextLine();
            
            if (!confirm.equals("yes") && !confirm.equals("YES")) {
                System.out.println("Order canceled.");
                System.out.println(-1);
                return;
            }
            
            System.out.println("Order confirmed. Total cost is: $" + totalC);
        }
    }
}