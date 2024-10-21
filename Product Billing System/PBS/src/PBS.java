// import java.io.*;
// import java.util.Scanner;

// public class PBS {
//     public static void main(String[] args) {
//         Scanner sc=new Scanner(System.in);
//         System.out.println("Enter Customer Name : ");
//         String name=sc.nextLine();
//         ProductBilling pb=new ProductBilling();
//         pb.name=name;
//         int choice,id,quantity;
//         String p_name;
//         double price;
//         boolean b=true;
//         while(b){
//             System.out.println("Enter \n1.Add Product\n2.Remove Product\n3.Search Product\n4.View Bill\n5.Print Bill\n6.Exit");
//             try {
//                 choice=sc.nextInt();
//                 sc.nextLine();
//             } catch (NumberFormatException e) {
//                 System.out.println("Please Enter In Integer");
//                 choice=0;
//             }
//             switch(choice){
//                 case 1:
//                         System.out.print("Enter Product ID : ");
//                         id=sc.nextInt();
//                         sc.nextLine();
//                         System.out.println("Enter Product Name : ");
//                         p_name=sc.nextLine();
//                         System.out.println("Enter Product Price : ");
//                         price=sc.nextDouble();
//                         sc.nextLine();
//                         System.out.println("Enter Product Quantity : ");
//                         quantity=sc.nextInt();
//                         sc.nextLine();
//                         pb.addProduct(id, p_name, quantity, price);
//                 break;

//                 case 2:
//                         System.out.println("Enter Product ID to Remove It : ");
//                         id=sc.nextInt();
//                         sc.nextLine();
//                         pb.removeProduct(id);
//                 break;

//                 case 3:
//                         System.out.println("Enter Product Id : ");
//                         id=sc.nextInt();
//                         sc.nextLine();
//                         System.out.println("Enter Product Name : ");
//                         p_name=sc.nextLine();
//                         pb.searchProduct(id);
//                 break;

//                 case 4:
//                         System.out.println("Bill : ");
//                         pb.displayBill();
//                 break;

//                 case 5:
//                     try {
//                         pb.PrintBill();
//                     } catch (IOException e) {
//                         System.out.println("Printing Error!!!!!");
//                     }
//                 break;

//                 case 6:
//                     try {
//                         pb.PrintBill();
//                     } catch (IOException e) {
//                         System.out.println("Printing Error!!!!!");
//                     }
//                         b=false;
//                 break;

//                 default:
//                         System.out.println("Enter Valid Choice!!!");
//                 break;
//             }
//         }


//     }
// }
// class ProductBilling{
//     String name;
//     class Product{
//         Product link;
//         int p_id;
//         String p_name;
//         int p_qnt;
//         double p_price;
//         public Product(int p_id, String p_name, int p_qnt, double p_price) {
//             this.link=null;
//             this.p_id = p_id;
//             this.p_name = p_name;
//             this.p_qnt = p_qnt;
//             this.p_price = p_price;
//         }
//         @Override
//         public String toString() {
//             return "Product [p_id=" + p_id + ", p_name=" + p_name + ", p_qnt=" + p_qnt + ", p_price=" + p_price + "]";
//         }
//         public int getP_qnt() {
//             return p_qnt;
//         }
//         public void setP_qnt(int p_qnt) {
//             this.p_qnt = p_qnt;
//         }
        
//     }
//     Product first=null;
//     void addProduct(int p_id,String p_name,int p_qnt,double p_price){
//         Product p=repeatProduct(p_id, p_name);
//         if(p!=null){
//             int q=p_qnt+p.getP_qnt();
//             p.setP_qnt(q);
//             return;
//         }
//         Product product=new Product(p_id, p_name, p_qnt, p_price);
//         if(first==null){
//             first=product;
//         }
//         else{
//             Product temp=first;
//             while(temp.link!=null){
//                 temp=temp.link;
//             }
//             temp.link=product;
//         }
//     }

//     void removeProduct(int p_id){
//         if(first==null){
//             System.out.println("No Product Available!!!");
//         }
//         else if(first.p_id==p_id){
//             first=first.link;
//         }
//         else{
//         Product temp=first;
//         while(temp.link!=null&&temp.link.p_id!=p_id){
//             temp=temp.link;
//         }
//         if(temp.link!=null){
//             temp=temp.link.link;
//         }
//         else{
//             System.out.println("No Product Found!!!");
//         }
//         }
//     }

//     void searchProduct(int p_id){
//         if(first==null){
//             System.out.println("No Product Available!!!");
//         }
//         else if(first.p_id==p_id){
//             System.out.println(first.toString());
//         }
//         else{
//         Product temp=first;
//         while(temp.link!=null&&temp.link.p_id!=p_id){
//             temp=temp.link;
//         }
//         if(temp.link!=null){
//             System.out.println(temp.toString());
//         }
//         else{
//             System.out.println("No Product Found!!!");
//         }
//         }
//     }

//     void displayBill(){
//         Product temp=first;
//         if(temp==null){
//             System.out.println("Empty!!!");
//         }
//         else{
//            while(temp!=null){
//             System.out.println(temp.toString());
//             temp=temp.link;
//         } 
//         }
        
//     }

//     private Product repeatProduct(int p_id,String p_name){
//         Product temp=first;
//         while(temp!=null){
//             if(temp.p_id==p_id&&temp.p_name.equalsIgnoreCase(p_name)){
//                 return temp;
//             }
//             temp=temp.link;
//         }
//         return null;
//     }

//     void PrintBill() throws IOException{
//         File f=new File(name+".txt");
//         f.createNewFile();
//         PrintWriter pw=new PrintWriter(new FileWriter(f, false));
//         Product temp=first;
//         double total=0,ind_total;
//         pw.println("Name : "+name);
//         pw.println();
//         pw.printf("%-5s\s\s%-20s\t%-18s\s%-5s\s%-10s\s%-5s\n","ID","Name","Price","X","Qnt.","Total");
//         pw.println("----------------------------------------------------------------------------------------------");
//         while(temp!=null){
//             ind_total=temp.p_price*temp.p_qnt;
//             total+=ind_total;
//             pw.printf("%-5d\s\s%-20s\t%-16.2f\s%-5s\s%-10d\s%-5s\s%-10.2f\n",temp.p_id,temp.p_name,temp.p_price,"X",temp.p_qnt,"=",ind_total);
//             temp=temp.link;
//         }
//         pw.println("----------------------------------------------------------------------------------------------");
//         pw.println("Total : "+total);
//         pw.close();

//         // Just for FUN 
//         Pr p=new Pr();
//         p.start();
//         try {
//             p.join();
//             System.out.println("\nPrinting Successful");
//         } catch (InterruptedException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//     }
// }

// // Just for FUN
// class Pr extends Thread{
//     public void run(){
//         int i=(int)(Math.random()*25);
//         System.out.print("Printing Under Process.");
//         while(i>0){
//             System.out.print(".");
//             i--;
//           try {
//             sleep(300);
//         } catch (InterruptedException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }  
//         }
        
//     }
// }