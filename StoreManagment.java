import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class StoreManagment {

        public static void addProduct(List<Product> inventory, Scanner sc){
           try{
             System.out.println("add the id of your product");
            int id = sc.nextInt();
            sc.nextLine();
            for(Product p : inventory){
                if(p.getId() == id){ 
                    System.out.println("Product id must be unique");
                    return;
                }
            }
            System.out.println("Enter product name");
            String name = sc.nextLine();
            System.out.println("Enter the product category");
            String category = sc.nextLine();
            System.out.println("enter the price of the product");
            double price = sc.nextDouble();
            System.out.println("enter the qunatity of the product");
            int quantity = sc.nextInt();
            sc.nextLine();

            inventory.add(new Product(id, name, price, quantity, category));
            System.out.println("Product added successefully");}
            catch(InputMismatchException e){
                System.out.println("invalid input. Please try again");
                sc.nextLine();
            }

        }

        public static void updateProduct(List<Product> inventory, Scanner sc){
        System.out.println("Enter the id of the product you want to update");
        int id = sc.nextInt();
        sc.nextLine();
      
         Product product = inventory.stream()
                                .filter(p ->p.getId() == id)
                                .findFirst()
                                .orElse(null);

        if(product == null){
            System.out.println("the product with this id :"+ id+"not found");
            return;
        }

        System.out.println("What would you like to update");
        System.out.println("1. Price \n2. Quantity \n3. Category");
        System.out.println("choose your option :");
        int choice =sc.nextInt();

        switch (choice) {
            case 1:
            System.out.println("Enter the new price");
            double newPrice = sc.nextDouble();
            product.setPrice(newPrice);
            System.out.println("the price has been updated"); 
                break;
            case 2:
            System.out.println("Enter the new Quantity");
            int newQuantity = sc.nextInt();
            product.setQuantity(newQuantity);
            System.out.println("the quantity has been updated"); 
                    break;
            case 3:
            System.out.println("Enter the new price");
            String newCategory = sc.nextLine();
            product.setCategory(newCategory);
            System.out.println("the category has been updated"); 
                     break;
     
            default:
                System.out.println("invalid choice");
                
        }

      
    }
        public static void searchProduct(List<Product> inventory, Scanner sc){
        System.out.println("Serch by :\n1.id \n2.name");
        System.out.println("choose your option :");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
            System.out.println("enter the product id :");
            int id = sc.nextInt();
            sc.nextLine();
            Product productById = inventory.stream()
                                            .filter(p->p.getId()== id)
                                            .findFirst()
                                            .orElse(null);
            if(productById != null){
                System.out.println(productById);
            }else{
                System.out.println("Product not found");
            }
                break;
            case 2:
            System.out.println("enter the name of the product :");
            String name = sc.nextLine();
            inventory.stream()
                     .filter(p -> p.getName().equalsIgnoreCase(name))
                     .forEach(System.out::println);

                     break;
    
            default:
               System.out.println("invalid choice");
        }
    }
        public static void sortInventory(List<Product> inventory, Scanner sc){
        System.out.println("sort by: \n1. name\2. price");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
            inventory.sort(Comparator.comparing(Product::getName));
            System.out.println("Inventory sorted by name");
                break;
            case 2:
            inventory.sort(Comparator.comparing(Product::getPrice));
            System.out.println("Inventory sorted by price");
                break;
            default:
                System.out.println("invalid choice");
        }
                displayInventory(inventory);
            }
        
        private static void displayInventory(List<Product> inventory) {
                if(inventory.isEmpty()){
                    System.out.println("the inventory is empty");
                }else{
                    System.out.println("\nInventory");
                    for(Product product : inventory){
                        System.out.println(product);
                    }
                }    
            }
    public static void main(String[] args) {

     
        List<Product> inventory = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n------- Inventory MAnagement system------- ");
            System.out.println("1. add a product:");
            System.out.println("2. update a product:");
            System.out.println("3. search for a product:");
            System.out.println("4. sort  inventory:");
            System.out.println("5. Display inventory:");
            System.out.println("6. exit:");
            System.out.println("choose your option");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> addProduct(inventory, sc);
                case 2 -> updateProduct(inventory, sc);
                case 3 -> searchProduct(inventory, sc);
                case 4 -> sortInventory(inventory, sc);
                case 5 -> displayInventory(inventory);
                case 6 -> {
                    running = false;
                    System.out.println("Exiting the system. Have a good day");
                }
                default -> System.out.println("invalid choice. Please try again");
            }
            
        }

       
            
    } 
}

