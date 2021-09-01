package objectorienteddesign.ShoppingCart.src;

public interface Cart {
    void showAvailableItems();
    boolean addItemToMyCart(int itemNo, int quantity);
    void checkItemsDeliverableToPIN(int pinCode);
    void printMyCart();
}
