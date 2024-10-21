import java.io.*;
import java.util.*;

class Customer {
    HashMap<Pizza, Integer> hm;
    HashMap<Burger,Integer> hm1;
    HashMap<Sandwitch , Integer> hm2;

    public Customer(String name) {
        this.hm = new HashMap<>();
        this.hm1 = new HashMap<>();
        this.hm2 = new HashMap<>();
        this.name = name;
    }

    String name;

}


class Burger
{
  double Price;
  String Name;
  public Burger(double Price,String Name)
  {
    this.Price=Price;
    this.Name=Name;
    }

    public String toString(){
    return "Burger [price=" + Price + ", name=" + Name + "]";

      }

  }

class Sandwitch
  {
    double PRICE;
    String NAME;
    public Sandwitch(double PRICE,String NAME)
    {
      this.PRICE=PRICE;
      this.NAME=NAME;
      }

      public String toString()
      {
      return "Sandwitch [price=" + PRICE + ", name=" + NAME + "]";

        }

    }

class Pizza {
    double price;
    String name;

    public Pizza(double price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pizza [price=" + price + ", name=" + name + "]";
    }
}


class CheeseBurger extends Burger
  {
    public CheeseBurger ()
    {
      super(99,"CheeseBurger");
      }

    }
class TandooriBurger extends Burger
  {
    public TandooriBurger()
    {
      super(110,"TandooriBurger");
      }
    }
class PeriperiBurger extends Burger
  {

    public PeriperiBurger()
    {
      super(80,"PeriperiBurger");

      }
    }


 class VegetableSandwitch extends Sandwitch
  {
    public VegetableSandwitch()
    {
      super(50,"VegetableSandwitch");
      }

    }


class GrillSandwitch extends Sandwitch
  {
    public GrillSandwitch()
    {
      super(80,"GrillSandwitch");
      }

    }

class Margherita extends Pizza {
    public Margherita() {
        super(150, "Margherita");
    }
}

class SevenCheesy extends Pizza {
    public SevenCheesy() {
        super(250, "SevenCheesy");
    }
}

class SweetCorn extends Pizza {
    public SweetCorn() {
        super(199, "SweetCorn");
    }
}

class PizzaManagement {
    static Scanner sc = new Scanner(System.in);

    static LinkedList<Customer> customer = new LinkedList<>();
    static Margherita margherita = new Margherita();
    static SweetCorn sweetCorn = new SweetCorn();
    static SevenCheesy sevenCheesy = new SevenCheesy();
    static CheeseBurger cheeseburger = new CheeseBurger();
    static TandooriBurger tandooriburger = new
    TandooriBurger();
    static PeriperiBurger periperiburger = new PeriperiBurger();
    static VegetableSandwitch vegetablesandwitch = new VegetableSandwitch();
    static GrillSandwitch grillsandwitch = new GrillSandwitch();


    public static void main(String[] args) throws IOException {

        boolean choice = true;
        while (choice) {
            System.out.println("Enter Choice \n1. Enter As User\n2. for exit");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    User();
                    break;
                case 2:
                    choice = false;
                    break;

                default:
                    break;
            }
        }

    }

    static void User() throws IOException {
        sc.nextLine();
        System.out.println("Enter Your Full Name");
        String name = sc.nextLine();
        boolean choice = true;
        while (choice) {
            System.out.println("Enter choice\n1. Add Item\n2. Remove Item\n3. show Menu\n4. exit\n5. Print Bill");
            int n = sc.nextInt();
            sc.nextLine();
            switch (n) {
                case 1:
                    addItem(name);
                    break;
                case 2:
                    removeItem(name);
                    break;
                case 3:
                    System.out.println(">> Menu Is Given Below <<");
                    System.out.println("Name                 Prize");
                    System.out.println(margherita.name + "           " + margherita.price);
                    System.out.println(sweetCorn.name + "            " + sweetCorn.price);
                    System.out.println(sevenCheesy.name + "          " + sevenCheesy.price);
                    System.out.println();
                    System.out.println(cheeseburger.Name +" " + cheeseburger.Price);
            System.out.println(tandooriburger.Name+" "+tandooriburger.Price);
            System.out.println(periperiburger.Name+" "+periperiburger.Price);
            System.out.println(vegetablesandwitch.NAME+" "+vegetablesandwitch.PRICE);
            System.out.println(grillsandwitch.NAME+" "+grillsandwitch.PRICE);


                    break;
                case 4:
                    choice = false;
                    break;
                case 5:
                    printBill(name);
                    choice = false;
                    break;

                default:
                    break;
            }
        }

    }

    static void addItem(String cname) {
        boolean found = false;
        Customer f = null;
        for (Customer customer2 : customer) {
            if (customer2.name.equalsIgnoreCase(cname)) {
                found = true;
                f = customer2;
            }
        }
        if (!(found)) {
            Customer c = new Customer(cname);
            customer.add(c);
            boolean choi = true;
            while (choi) {
                System.out.println("Enter Choice \n1. ADD MARGHERITA \n2. ADD SWEETCORN \n3. ADD SEVENCHEESY\n4. ADD CHEESEBURGER\n5. ADD TANDOORIBURGER\n6. ADD PRRIPERIBURGER\n7. ADD VEGETABLESANDWITCH\n8. ADD GRILLSANDWITCH \n9. Exit");
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        System.out.println("Enter Quantity");
                        int maq = sc.nextInt();
                        c.hm.put(margherita, maq);

                        break;
                    case 2:
                        System.out.println("Enter Quantity");
                        int swq = sc.nextInt();
                        c.hm.put(sweetCorn, swq);

                        break;
                    case 3:
                        System.out.println("Enter Quantity");
                        int seq = sc.nextInt();
                        c.hm.put(sevenCheesy, seq);

                        break;

                        case 4: System.out.println("Enter Quantity");
                        int cbq = sc.nextInt();
                        c.hm1.put(cheeseburger, cbq);

                        break;
                        case 5: System.out.println("Enter Quantity");
                        int tbq = sc.nextInt();
                        c.hm1.put(tandooriburger, tbq);

                        break;
                        case 6: System.out.println("Enter Quantity");
                        int pbq = sc.nextInt();
                        c.hm1.put(periperiburger, pbq);

                        break;
                        case 7: System.out.println("Enter Quantity");
                        int vsq= sc.nextInt();
                        c.hm2.put(vegetablesandwitch, vsq);
                        break;
                        case 8: System.out.println("Enter Quantity");
                        int gsq = sc.nextInt();
                        c.hm2.put(grillsandwitch, gsq);

                        break;
                    case 9:
                        choi = false;
                        break;

                    default:
                        System.out.println("Enter Valid choice");
                        break;
                }
            }
        } else {
            boolean choi = true;
            while (choi) {
                System.out
                        .println("Enter Choice \n1. ADD MARGHERITA \n2. ADD SWEETCORN \n3. ADD SEVENCHEESY \n4. ADD CHEESEBURGER \n5. ADD TANDOORIBURGER \n6. ADD PERIPERIBURGER \n7. ADD VEGETABLESANDWITCH \n8. ADD GRILLSANDWITCH \n9. Exit");
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        System.out.println("Enter Quantity");
                        int maq = sc.nextInt();
                        if (f.hm.containsKey(margherita)) {
                            f.hm.put(margherita, f.hm.get(margherita) + maq);
                        } else {
                            f.hm.put(margherita, maq);
                        }
                        break;

                    case 2:
                        System.out.println("Enter Quantity");
                        int swq = sc.nextInt();
                        if (f.hm.containsKey(sweetCorn)) {
                            f.hm.put(sweetCorn, f.hm.get(sweetCorn) + swq);
                        } else {
                            f.hm.put(sweetCorn, swq);
                        }
                        break;

                    case 3:
                        System.out.println("Enter Quantity");
                        int seq = sc.nextInt();
                        if (f.hm.containsKey(sevenCheesy)) {
                            f.hm.put(sevenCheesy, f.hm.get(sevenCheesy) + seq);
                        } else {
                            f.hm.put(sevenCheesy, seq);
                        }
                        break;
                        case 4: System.out.println("Enter Quantity");
                        int cbq = sc.nextInt();
                        if (f.hm1
                        .containsKey(cheeseburger)) {
                            f.hm1.put(cheeseburger, f.hm.get(cheeseburger) + cbq);
                        } else {
                            f.hm1.put(cheeseburger, cbq);
                        }

                        break;
                        case 5: System.out.println("Enter Quantity");
                        int tbq = sc.nextInt();
                        if (f.hm1.containsKey(tandooriburger)) {
                            f.hm1.put(tandooriburger, f.hm1.get(tandooriburger) + tbq);
                        } else {
                            f.hm1.put(tandooriburger, tbq);
                        }

                        break;
                        case 6: System.out.println("Enter Quantity");
                        int pbq = sc.nextInt();
                        if (f.hm1.containsKey(periperiburger)) {
                            f.hm1.put(periperiburger, f.hm1.get(periperiburger) + pbq);
                        } else {
                            f.hm1.put(periperiburger, pbq);
                        }


                        break;
                        case 7: System.out.println("Enter Quantity");
                        int vsq = sc.nextInt();
                        if (f.hm2.containsKey(vegetablesandwitch)) {
                            f.hm2.put(vegetablesandwitch, f.hm2.get(vegetablesandwitch) + vsq);
                        } else {
                            f.hm2.put(vegetablesandwitch, vsq);
                        }

                        break;
                        case 8: System.out.println("Enter Quantity");
                        int gsq = sc.nextInt();
                        if (f.hm2.containsKey(grillsandwitch)) {
                            f.hm2.put(grillsandwitch, f.hm2.get(grillsandwitch) + gsq);
                        } else {
                            f.hm2.put(grillsandwitch, gsq);
                        }
                        break;

                    case 9:
                        choi = false;
                        break;

                    default:
                        System.out.println("Enter Valid choice");
                        break;
                }
            }

        }

        System.out.println("You Want To Add More?? Yes Or No ??");
        String ans = sc.next();
        if (ans.equalsIgnoreCase("yes")) {
            addItem(cname);
        }

    }

    static void removeItem(String cname) {
        boolean found = false;
        Customer f = null;
        for (Customer customer2 : customer) {
            if (customer2.name.equalsIgnoreCase(cname)) {
                found = true;
                f = customer2;
            }
        }
        if (found) {
            boolean choi = true;
            while (choi) {
                System.out.println(
                        "Enter Choice \n1. REMOVE MARGHERITA \n2. REMOVE SWEETCORN \n3. REMOVE SEVENCHEESY \n4. REMOVE CHEESEBURGER \n5. REMOVE TANDOORIBURGER  \n6. REMOVE PERIPERIBURGER \n7.REMOVE VEGETABELSANDWITCH \n8. REMOVE GRILLSANDWITCH \n9. Exit");
                int n = sc.nextInt();
                switch (n) {
                    case 1:
                        System.out.println("How Much Quantity You Want To Delete");
                        int maq = sc.nextInt();
                        int fmaq = f.hm.get(margherita) - maq;
                        f.hm.put(margherita, fmaq);
                        break;
                    case 2:
                        System.out.println("How Much Quantity You Want To Delete");
                        int swq = sc.nextInt();
                        int fswq = f.hm.get(sweetCorn) - swq;
                        f.hm.put(sweetCorn, fswq);
                        break;
                    case 3:
                        System.out.println("How Much Quantity You Want To Delete");
                        int seq = sc.nextInt();
                        int fseq = f.hm.get(sevenCheesy) - seq;
                        f.hm.put(sevenCheesy, fseq);
                        break;

                        case 4: System.out.println("How Much Quantity You Want To Delete");
                        int cbq = sc.nextInt();
                        int fcbq = f.hm1.get(cheeseburger) - cbq;
                        f.hm1.put(cheeseburger, fcbq);

                        break;
                        case 5: System.out.println("How Much Quantity You Want To Delete");
                        int tbq = sc.nextInt();
                        int ftbq = f.hm1.get(tandooriburger) - tbq;
                        f.hm1.put(tandooriburger, ftbq);
                        break;
                        case 6: System.out.println("How Much Quantity You Want To Delete");
                        int pbq = sc.nextInt();
                        int fpbq = f.hm1.get(periperiburger) - pbq;
                        f.hm1.put(periperiburger, fpbq);


                        break;
                         case 7: System.out.println("How Much Quantity You Want To Delete");
                        int vsq = sc.nextInt();
                        int fvsq = f.hm2.get(vegetablesandwitch) - vsq;
                        f.hm2.put(vegetablesandwitch, fvsq);
                        break;
                        case 8: System.out.println("How Much Quantity You Want To Delete");
                        int gsq = sc.nextInt();
                        int fgsq = f.hm2.get(grillsandwitch) - gsq;
                        f.hm2.put(grillsandwitch, fgsq);


                        break;

                    case 9:
                        choi = false;
                        break;

                    default:
                        System.out.println("Enter Valid choice");
                        break;
                }
            }
        } else {
            System.out.println("Customer Not Found");
        }

    }

    static void printBill(String cname) throws IOException {

        boolean found = false;
        double price = 0;
        Customer f = null;
        for (Customer customer2 : customer) {
            if (customer2.name.equalsIgnoreCase(cname)) {
                found = true;
                f = customer2;
            }
        }
        if (found) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(cname + ".txt", false));
            bw.write(
                    "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= PIZZA HUT -=-=-==-=-==-==-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=");
            bw.newLine();
            bw.write("Customer Name : " + cname);
            bw.newLine();
            bw.write("Order Details Given Below  ");
            bw.newLine();
            double totalPrice = 0.0;
            for (Map.Entry<Pizza, Integer> q : f.hm.entrySet()) {
                if (q.getKey().name.equalsIgnoreCase("Margherita")) {
                    int q1 = q.getValue();
                    price = q1 * margherita.price;
                    totalPrice += price;
                    bw.write("Pizza Name : " + q.getKey().name + " Pizza Quantity : " + q1 + " Price : " + price);
                    bw.newLine();
                } else if (q.getKey().name.equalsIgnoreCase("Sweetcorn")) {
                    int q1 = q.getValue();
                    price = q1 * sweetCorn.price;
                    totalPrice += price;
                    bw.write("Pizza Name : " + q.getKey().name + " Pizza Quantity : " + q1 + " Price : " + price);
                    bw.newLine();
                } else if (q.getKey().name.equalsIgnoreCase("Sevencheesy")) {
                    int q1 = q.getValue();
                    price = q1 * sevenCheesy.price;
                    totalPrice += price;
                    bw.write("Pizza Name : " + q.getKey().name + " Pizza Quantity : " + q1 + " Price : " + price);
                    bw.newLine();
                }



                }
            for (Map.Entry<Burger, Integer> qq : f.hm1.entrySet()) {
                if (qq.getKey().Name.equalsIgnoreCase("CheeseBurger")) {
                    int q11 = qq.getValue();
                    price = q11 * cheeseburger.Price;
                    totalPrice += price;
                    bw.write("Burger Name : " + qq.getKey().Name + " Burger Quantity : " + q11 + " Price : " + price);
                    bw.newLine();
                } else if (qq.getKey().Name.equalsIgnoreCase("TandooriBurger")) {
                    int q11 = qq.getValue();
                    price = q11 * tandooriburger.Price;
                    totalPrice += price;
                    bw.write("Burger Name : " + qq.getKey().Name + " Burger Quantity : " + q11 + " Price : " + price);
                    bw.newLine();
                } else if (qq.getKey().Name.equalsIgnoreCase("PeriperiBurger")) {
                    int q11 = qq.getValue();
                    price = q11 * periperiburger.Price;
                    totalPrice += price;
                    bw.write("Burger Name : " + qq.getKey().Name + " Burger Quantity : " + q11 + " Price : " + price);
                    bw.newLine();
                }


                }
            for (Map.Entry<Sandwitch, Integer> h : f.hm2.entrySet()) {
                if (h.getKey().NAME.equalsIgnoreCase("VegetableSandwitch")) {
                    int h1 = h.getValue();
                    price = h1 * vegetablesandwitch.PRICE;
                    totalPrice += price;
                    bw.write("Sandwitch Name : " + h.getKey().NAME + " Sandwitch Quantity : " + h1 + " Price : " + price);
                    bw.newLine();
                } else if (h.getKey().NAME.equalsIgnoreCase("grillsandwitch")) {
                    int h1 = h.getValue();
                    price = h1 * grillsandwitch.PRICE;
                    totalPrice += price;
                    bw.write("Sandwitch Name : " + h.getKey().NAME + " Sandwitch Quantity : " + h1 + " Price : " + price);
                    bw.newLine();
                }
            }
            bw.newLine();
            bw.write("Total Price : " + totalPrice);
            bw.newLine();
            bw.write("-=-=-=-=-=-=-=--==-=-=-=-=-=-= THANK YOU FOR VISIT COME AGAIN -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-===");
            bw.close();
        } else {
            System.out.println("Customer Not Found");
        }
    }
}
