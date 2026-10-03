
import java.util.Scanner;                        // allows the program to read what the user types on the keyboard
import java.security.SecureRandom;               // picks a random dessert from the desserts list

public class Lunch {                             // this class handles the main menu and manages an array of Lunchbox objects
  public static void main(String[] args) {
    // setting up Scanner for user input and SecureRandom for the free dessert
    Scanner inputScanner = new Scanner(System.in);
    SecureRandom randomGenerator = new SecureRandom();

    Lunchbox[] orders = new Lunchbox[100];      // creates an array to hold up to 100 Lunchbox objects 
    // list of possible free desserts 
    String[] desserts = {"Apple Strudel", "Tiramisu", "Creme Brulee", "Sticky Toffee Pudding", "Cookie", "Brownie", "Ice Cream", "Cake", "Donut", "Fruit cup"};

    int count = 0;                              // count starts at 0 since Lunchbox[] orders zero based!
    boolean running = true;                     // running is set to true so the menu keeps appearing until the user chooses to exit

    while (running) {                           // displaying the Main Menu Items
      System.out.println("\n" + "=".repeat(30));
      System.out.println("   LUNCH ORDER TRACKER     ");
      System.out.println("=".repeat(30));
      System.out.println("1. Add a NEW order!");
      System.out.println("2. Add quantity to an order");
      System.out.println("3. Mark an order as delivered");
      System.out.println("4. View all lunch orders");
      System.out.println("5. Calculate total sales");
      System.out.println("6. Exit");
      System.out.println("=".repeat(30));
      System.out.print("\nChoice: ");       // user manages lunch orders
      // getting the user's menu selection
      int choice = inputScanner.nextInt();    // captures the user's menu selection (1 through 6) as an integer
      inputScanner.nextLine();                // clears the buffer so the next String input works correctly

      switch (choice) {
        case 1:                               // building logic to create a brand new order
          System.out.println("\n--- New Order ---");
          System.out.print("\nCustomer Name: ");
          String name = inputScanner.nextLine();
          System.out.print("\nMain Item (Steak, Soup, Pizza, Pasta, Burger, Sandwich): ");
          String item = inputScanner.nextLine();
          System.out.print("\nQuantity: ");
          int qty = inputScanner.nextInt();
          System.out.print("\nPrice: ");
          double price = inputScanner.nextDouble();
          inputScanner.nextLine();              // Consume leftover newline (best practice)
          // picking a random dessert from the desserts list using SecureRandom
          String randomDessert = desserts[randomGenerator.nextInt(desserts.length)];
          // creating the object and storing it in array at the current 'count' position
          orders[count] = new Lunchbox(name, item, qty, price); 
          System.out.println("\n✅ Order #" + (count + 1) + " Created with a free " + randomDessert);
          count++;
          break;

        case 2:                                 // updating an existing order's quantity (needs order number)
          if (count == 0) {
            System.out.println("\n[!] No orders to update.");
            break; 
          }

          // shows the user WHAT they can update
          System.out.println("\n--- Current Orders ---");
          for (int i = 0; i < count; i++) {
            System.out.printf("  Order #%d: %s - %s (Current Qty: %d)%n", 
              (i + 1), orders[i].getCustomerName(), orders[i].getMainItem(), orders[i].getQuantity());
          }
          // getting the input
          System.out.print("\nEnter Order Number to update: ");
          int targetIdQty = inputScanner.nextInt();
          boolean foundQty = false;

          for (int i = 0; i < count; i++) {      // performing the update
            if ((i + 1) == targetIdQty) {
              System.out.print("Additional quantity to add: ");
              int extra = inputScanner.nextInt();

              orders[i].setQuantity(orders[i].getQuantity() + extra);
              System.out.println("\n✅ Updated! New quantity: " + orders[i].getQuantity());
              foundQty = true;
              break;
            }
          }

          if (!foundQty) System.out.println("\n❌ Error: Order #" + targetIdQty + " does not exist.");
          break;

        case 3:                                  // marking an order as delivered
          if (count == 0) {
            System.out.println("\n[!] No orders to mark as delivered.");
            break;
          }

          // 1. Show the user WHAT they can mark
          System.out.println("\n--- Current Orders ---");
          for (int i = 0; i < count; i++) {
            String status = orders[i].isDelivered() ? "✓ DELIVERED" : "⏳ PENDING";
            System.out.printf("  Order #%d: %s - %s [%s]%n", 
              (i + 1), orders[i].getCustomerName(), orders[i].getMainItem(), status);
          }

          // 2. Get the input
          System.out.print("\nEnter Order Number to mark as delivered: ");
          int targetIdDel = inputScanner.nextInt();
          boolean foundDel = false;

          // 3. Perform the update
          for (int i = 0; i < count; i++) {
            if ((i + 1) == targetIdDel) {
              if (orders[i].isDelivered()) {
                System.out.println("\n⚠️ Order #" + targetIdDel + " is already delivered!");
              } else {
                orders[i].markDelivered();
                System.out.println("\n✅ Order #" + targetIdDel + " marked as delivered!");
              }
              foundDel = true;
              break;
            }
          }

          if (!foundDel) System.out.println("\n❌ Error: Order #" + targetIdDel + " does not exist.");
          break;

        case 4:                                 // reporting center
          if (count == 0) {
            System.out.println("\n[!] No orders to display.");
          } else {
            System.out.println("\n" + "=".repeat(85));
            // print table headers for the order details (NO dessert column)
            System.out.printf("%-4s | %-25s | %-10s | %-5s | %-13s | %-15s%n",
              "ID", "CUSTOMER", "ITEM", "QTY", "TOTAL", "STATUS");
            System.out.println("-".repeat(85));
            // loop through the array and call the display method for each object
            for (int i = 0; i < count; i++) {
              orders[i].displayOrder(i + 1);
            }
            System.out.println("=".repeat(85));
          }
          break;

        case 5:                                   // view and calculate, loop through all orders and sum up their costs 
          double totalSales = 0;
          for (int i = 0; i < count; i++) {
            totalSales += orders[i].calculateCost();
          }
          System.out.printf("\nTOTAL SALES OF ALL ORDERS: $%,.2f%n", totalSales);
          break;

        case 6:                                 // stopping the loop to exit the program
          running = false;
          System.out.println("\nExiting... Good Bye!\n");
          break;

        default:
          System.out.println("\nInvalid choice. Please pick 1-6.");
      }
    }
    inputScanner.close();
  }
}