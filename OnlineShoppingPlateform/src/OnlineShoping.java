import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class OnlineShoping {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome\n");
        System.out.println("Enter Your Name : ");
        String name = sc.nextLine();
        String mob_no;
        while (true) {
            System.out.println("Enter Your Mobile Number ");
            mob_no = sc.nextLine();
            int flag=0;
            if (mob_no.length() == 10 && mob_no.charAt(0) <='9' && mob_no.charAt(0) >= '6') {
                    for(int i=0;i<mob_no.length();i++){
                        if(!(mob_no.charAt(i)<='9'&& mob_no.charAt(i)>='0')){
                            System.out.println("Character Not Allowed!!!");
                            flag=-1;
                            break;
                        }
                    }
                    if(flag==0){
                       System.out.println("Mobile Number is valid");
                    break; 
                    }
                    
            } else {
                if(mob_no.length()==10){
                    System.out.println("Mobile Number Must Start With 9,8,7,6");
                }else{
                    System.out.println("Mobile Number must be 10 digits!!!");
                }
            }
        }

        System.out.println("Enter Your Address : ");
        String address = sc.nextLine();
        String pin;
        while(true){
            System.out.println("Enter Your Area PIN Code No. : ");
            pin = sc.next().trim();
            int flag=0;
            if(pin.length()==6)
            {
                for(int i=0;i<pin.length();i++){
                    if(!(pin.charAt(i)>='1'&& pin.charAt(i)<='9')){
                        flag=-1;
                    }
                }
            }
            if(flag==0){
                System.out.println("PIN is Valid");
                break;
            } else{
                System.out.println("Not Valid!!!");
            }
        }
        sc.nextLine();
        Customer c = new Customer(name, address, mob_no, pin);
        // Customer c=new Customer("Kuldeepsinh", "Patan", "9723390210", "384255");
        boolean b = true;
        while (b) {
            System.out.println("Enter Choice\n1.Add Product\n2.Search Product\n3.Remove Product\n4.View Cart\n5.Exit & Print");
            System.out.print("Choice : ");
            try{
            int choice = Integer.parseInt(sc.next().trim());
            Long.parseLong("1234567890");
            sc.nextLine();
            Product p=null;
            switch (choice) {
                case 1:
                    p = OSP.getProduct();
                    if(p!=null)
                    c.addProduct(p);
                    break;

                case 2:
                    System.out.println("Enter To Search : ");
                    p=OSP.searchProduct(sc.next().trim());
                    if(p!=null){
                    System.out.println("Enter \n1.Add\nElse Not Add");
                    int k=sc.nextInt();
                    if(k==1){
                       c.addProduct(p); 
                    }else{
                        sc.nextLine();
                        p=null;
                    }
                    }
                    break;

                case 3:
                    System.out.println("Enter Product ID Which You Want to remove : ");
                    int item_id = sc.nextInt();
                    c.removeProduct(item_id);
                    break;

                case 4:
                    c.viewCart();
                    break;

                case 5:
                    b = false;
                    break;

                default:
                    System.out.println("Please enter Valid choice!!!");
                    break;
            }
            }catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Invalid Choice!!!\n");
            }
            catch(Exception e){
                System.out.println("Error!!!");
            }
        }
        c.printBill();
        System.out.println("Thanks For Visit");
        sc.close();
    }
}

class Product {
    int p_id, p_quantity;
    String p_name, p_type;
    double p_price;

    public Product(int p_id, String p_name, String p_type, double p_price, int p_quantity) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_type = p_type;
        this.p_price = p_price;
        this.p_quantity = p_quantity;
    }

    @Override
    public String toString() {
        return "ID : " + p_id + ", Name : " + p_name + ", Type : " + p_type + ", Price : " + p_price + ", Quantity : "
                + p_quantity;
    }

    public int getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(int p_quantity) {
        this.p_quantity = p_quantity;
    }
}

class OSP {
    static Connection con;
    static Scanner sc = new Scanner(System.in);
    String bankDetails,upiID;
    String paymentType;
    OSP() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingplatform", "root", "");
    }

    static String getType() throws SQLException {
        PreparedStatement pst = con.prepareStatement("select p_type from product_details group by p_type");
        ResultSet rst = pst.executeQuery();
        ArrayList<String> temp = new ArrayList<>();
        int i = 1;
        String p;
        System.out.println();
        while (rst.next()) {
            p = rst.getString("p_type");
            System.out.println(i + ". " + p);
            temp.add(p);
            i++;
        }
        int n=0;
        try {
            System.out.println("Select Type");
            n = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            n=0;
        }
        
        return temp.get(n - 1);
    }

    static Product getProduct() throws SQLException {
        String type = getType();
        PreparedStatement pst = con.prepareStatement("select p_id,p_name,p_price from product_details where p_type=?");
        pst.setString(1, type);
        ResultSet rst = pst.executeQuery();
        int i = 1, id;
        String name;
        double price;
        ArrayList<Product> a = new ArrayList<>();
        System.out.println();
        while (rst.next()) {
            name = rst.getString("p_name");
            id = rst.getInt("p_id");
            price = rst.getDouble("p_price");
            System.out.println(i + ". " + name + " , Price : " + price);
            a.add(new Product(id, name, type, price, 0));
            i++;
        }
        int n=0;
        try {
            System.out.println("Select Type(Enter 0 TO Exit)");
            n = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("Enter Valid Choice!!!");
            n=0;
        }
        
        if(n==0){
            return null;
        }
        else{
            return a.get(n - 1);
        }
    }

    public static Product searchProduct(String name) throws SQLException{
        CallableStatement cst=con.prepareCall("call searchProduct(?);");
        cst.setString(1,"%"+name+"%");
        ArrayList <Product> temp=new ArrayList<>();
        ResultSet rs=cst.executeQuery();
        if(rs==null){
            return null;
        }
        int i=1;
        while(rs.next()){
            int id=rs.getInt(1);
            String p_name=rs.getString(2);
            String p_type=rs.getString(3);
            double price=rs.getDouble(4);
            System.out.println(i++ +" "+p_name+" "+p_type+" "+price);
            temp.add(new Product(id,p_name,p_type,price,0));
        }
        int n=0;
        try {
            System.out.println("Select Type(Enter 0 TO Exit)");
            n = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            System.out.print("Enter Valid Choice!!!");
            return null;
        }
        if(n==0)
        return null;
        
        return temp.get(n - 1);
    }

    public  void payment(){
        System.out.println("Enter Payment Method : ");
        System.out.println("\n1.UPI\n2.Net Banking\n3.Cash On Delivery");
        int i=sc.nextInt();
        sc.nextLine();
        switch(i){
            case 1:
                System.out.println("Enter Your UPI ID : ");
                upiID=sc.next().trim();
                paymentType="By UPI";
            break;

            case 2:
                System.out.println("Enter Your Bank Name : ");
                String name=sc.nextLine();
                System.out.println("Enter Bank Account Number : ");
                String accNo=sc.nextLine();
                System.out.println("Enter Your Name According to Bank : ");
                String nameBank=sc.nextLine();
                bankDetails=name+"  \n"+accNo+"  \n"+nameBank;
                paymentType="By Net Banking";
            break;

            case 3:
                System.out.println("Payment Receved on Delivery");
                paymentType="Cash On Delivery";
            break;

            default:
                System.out.println("Enter Valid Choice!!!");
            break;
        }
    }
}

class Customer extends OSP{
    Scanner sc = new Scanner(System.in);
    private String c_name, address;
    private String mob_no;
    private String PIN;
    private double discount=Math.random()*30;
    private ArrayList<Product> cart = new ArrayList<>();

    public Customer(String c_name, String address, String mob_no, String PIN) throws SQLException {
        super();
        this.c_name = c_name;
        this.address = address;
        this.mob_no = mob_no;
        this.PIN = PIN;
    }

    public void viewCart() {
        for (Product p : cart) {
            System.out.println(p.toString());
        }
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void addProduct(Product product) {
        int flag = 0;
        System.out.println("Enter Quantity : ");
        try {
            product.setP_quantity(sc.nextInt());
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("Enter Valid Quantity!!!");
            return;
        }
        sc.nextLine();
        for (Product p : cart) {
            if (p.p_id == product.p_id) {
                System.out.println("Product Is Already in Cart");
                System.out.println("Quantity Updated");
                p.setP_quantity(p.p_quantity + product.p_quantity);
                flag = -1;
            }
        }
        if (flag == 0){
            System.out.println("Product Added in Cart");
            cart.add(product);
        }
            
    }

    public boolean removeProduct(int p_id) {
        Product remove=null;
        for (Product p : cart) {
            if (p.p_id==p_id) {
                if (p.p_quantity > 1) {
                    System.out.println("Enter Number Of Quantity You Want to remove : ");
                    int n =0;
                    n= sc.nextInt();
                    n = p.p_quantity - n;
                    if (n == 0) {
                        remove=p;
                    } else if (n < 0) {
                        System.out.println("Invalid Quantity!!!\nPlease Try Again!!!");
                    } else {
                        p.setP_quantity(n);
                        System.out.println("Quantity Updated");
                        return true;
                    }
                } else {
                    remove=p;
                }
            }
        }
        if(cart.remove(remove)){
            System.out.println("Removed Successfully");
            return true;
        }
        else{
            System.out.println("Not Removed");
            return false;
        }
    }
    /**
     * @param customerName
     * @param pw to Print Header of Bill in txt file
     */
    void billHeader( String customerName,PrintWriter pw) {
        pw.println("\t\t\t\t\t----------------------------------------------------Bill----------------------------------------------------------");
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        Date d = new Date();
        pw.println("Date     : "+date.format(d) + "\nTime     : " + time.format(d));
        pw.printf("\nCustomer : %-20s", customerName);
        pw.printf("\nADDRESS  :  "+address);
        pw.printf("\nPIN      : %-6s   \nContact  : %-10s\n",PIN,mob_no);
        pw.flush();
    }

    // displayFormat of bill
    static void billFormat(PrintWriter pw) {
        pw.format("\n--------------------------------------------------------------------------------------------------------------------");
        pw.print("\nPRODUCT ID\t\tPRODUCT        \t\tQUANTITY\t\tRATE    \t\tTOTAL PRICE\n");
        pw.format("--------------------------------------------------------------------------------------------------------------------\n");
        pw.flush();
    }

    /**
     * To Print Bill in txt file
     * @throws IOException
     */
    public void printBill() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(c_name + ".txt", false));
        billHeader(c_name, pw);
        billFormat(pw);
        double overAllPrice = 0.0, discount_amount = 0.0, subtotal;
        for(Product p:cart) {
            double p_totalPrice=p.p_price*p.p_quantity;
            overAllPrice += p_totalPrice;
            pw.printf(" %-10d\t\t%-15s\t\t%-8d\t\t%-10.2f\t\t%-14.2f\n",p.p_id,p.p_name,p.p_quantity,p.p_price,p_totalPrice);
        }
        pw.print("--------------------------------------------------------------------------------------------------------------------");
        // display overall price
        pw.println("\nTotal Amount  :\t" + overAllPrice);
        // calculate & display discount
        discount_amount = overAllPrice * discount / 100;
        pw.printf("\nDiscount      :\t%-10.2f\n",discount);
        pw.printf("\nYou Save      :\t%-10.2f\n", discount_amount);
        // display total amount with discount
        subtotal = overAllPrice - discount;
        // display net payable amount
        pw.printf("\nInvoice Total :\t%-10.2f\n", (subtotal));
        pw.println("--------------------------------------------------------------------------------------------------------------------\n");
        pw.println("\t\t\t\t----------------Thank You for Shopping!!-----------------");
        pw.println("\t\t\t\t          ----------- Visit Again -----------");

        // Just for FUN
        // Printing p = new Printing();
        // p.start();
        // try {
        //     p.join();
        //     System.out.println("\nPrinting Successful");
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        pw.close();
    }
}

// // Just for FUN
// class Printing extends Thread {
//     public void run() {
//         int i = (int) (Math.random() * 30);
//         System.out.print("Printing Under Process.");
//         while (i > 0) {
//             System.out.print(".");
//             i--;
//             try {
//                 Thread.sleep(300);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }

//     }
// }