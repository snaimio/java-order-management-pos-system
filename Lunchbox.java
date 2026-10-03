
public class Lunchbox {
  private String customerName;
  private String mainItem;
  private int quantity;
  private double price;
  private boolean isDelivered;

  public Lunchbox() {                                               // default constructor
    this.customerName = "Unknown";
    this.mainItem = "None";
    this.quantity = 0;
    this.price = 0.0;
    this.isDelivered = false;
  }

  public Lunchbox(String name, String item, int qty, double pr) {   // constructor with arguments
    this.customerName = name;
    this.mainItem = item;
    this.quantity = qty;
    this.price = pr;
    this.isDelivered = false;                                      // default is false
  }

  public String getCustomerName() {                                // getters and setters (for all fields) 
    return customerName; 
  }
  public void setCustomerName(String name) { 
    this.customerName = name; 
  }
  public String getMainItem() { 
    return mainItem; 
  }
  public void setMainItem(String item) { 
    this.mainItem = item; 
  }
  public int getQuantity() { 
    return quantity; 
  }
  public void setQuantity(int qty) { 
    this.quantity = qty; 
  }
  public double getPrice() { 
    return price; 
  }
  public void setPrice(double pr) { 
    this.price = pr; 
  }
  public boolean isDelivered() { 
    return isDelivered; 
  }
  public void setDelivered(boolean status) { 
    this.isDelivered = status; 
  }

  public double calculateCost() {                                  // logic Methods
    return this.price * this.quantity;
  }

  public void markDelivered() {
    this.isDelivered = true;
  }

  public void displayOrder(int id) {
    String status = isDelivered ? "Delivered" : "Not Delivered";
    System.out.printf("#%-2d | %-25s | %-10s | %-5d | $%-12.2f | %-15s%n",
      id, customerName, mainItem, quantity, calculateCost(), status);
  }
}