package objectorienteddesign.ShoppingCart.src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart myCart = new ShoppingCart();

        System.out.println("Choose the items from below list.");
        myCart.showAvailableItems();

        while (true) {
            System.out.print("Enter item number you want to add to the cart: ");
            int itemNo = sc.nextInt();
            System.out.print("Enter the quantity for the item: ");
            int quantity = sc.nextInt();
            if (myCart.addItemToMyCart(itemNo, quantity)) {
                System.out.println("Item " + itemNo + " is added to your cart.");
            }

            System.out.println("Want to add more items to your cart? y/n: ");
            String done = sc.next().trim().toLowerCase();
            if (!done.equals("y"))
                break;
        }

        System.out.print("Enter your PIN Code: ");
        int pinCode = sc.nextInt();
        myCart.checkItemsDeliverableToPIN(pinCode);

        System.out.println("Your updated cart:");
        myCart.printMyCart();
    }
}
