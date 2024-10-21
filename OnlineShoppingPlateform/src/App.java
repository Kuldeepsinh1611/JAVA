// import java.util.Scanner;
// public class App {
//     public static void main(String[] args) throws Exception {
//         Scanner sc=new Scanner(System.in);
//         System.out.println("\nWelcome\n");
//         System.out.println("Enter Your Name : ");
//         String name=sc.nextLine();
//         System.out.println("Enter Your Mobile Number : ");
//         long mob_no=sc.nextLong(10);
//         sc.nextLine();
//         System.out.println("Enter Your Address : ");
//         String address=sc.nextLine();
//         System.out.println("Enter Your Area PIN Code No. : ");
//         int pin=sc.nextInt();
//         sc.nextLine();
//         Customer c=new Customer(name, address, mob_no, pin);
//         // Customer c=new Customer("Kuldeepsinh", "Patan", 9723390210l, 384255);
//         boolean b=true;
//         String s;
//         while(b){
//             System.out.println("Enter Choice\n1.Add\n2.Remove\n3.View Cart\n4.Exit");
//             int c1=sc.nextInt();
//             sc.nextLine();
//             switch(c1){
//                 case 1:Product p=c.getProduct();
//                 c.addProduct(p);
//                 break;
//                 case 2:
//                 System.out.println("Enter Which Product You Want to remove : ");
//                 String item=sc.nextLine();
//                 c.removeProduct(item);
//                 break;
//                 case 3:
//                 c.viewCart();
//                 break;
//                 case 4:
//                 b=false;
//                 break;
//                 default:System.out.println("Please enter Valid choice!!!");
//                 break;
//             }
            
//         }
//         c.printBill();
//         System.out.println("Thanks For Visit");
//     }
// }
