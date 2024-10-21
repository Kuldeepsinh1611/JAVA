import java.io.*;
import java.util.*;
import java.sql.*;

class Test {
    static Scanner sc = new Scanner(System.in);

    /**
     * To run the program of Bank Management System
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        int i = 0, PIN;
        String ID;
        boolean bank_loop = true, loop = true, mainLoop = true;
        System.out.println("Welcome to BANK");
        while (mainLoop) {
            bank_loop = true;
            loop = true;
            System.out.println("""
                    \nEnter What you want to do?
                    1 - BANK
                    2 - ATM
                    3 - ONLINE TRANSFER
                    4 - Instant Cash
                    5 - EXIT
                    """);
            System.out.print("Choice : ");
            try{
            int x = Integer.parseInt(sc.nextLine());
            switch (x) {
            case 1:
                Bank bank = new Bank();
                while (bank_loop) {
                    System.out.println("1. Add Account \n2. Withdraw\n3. Deposit\n4. Exit");
                    try {
                        i = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Please enter Integer Choice");
                    }
                    sc.nextLine();
                    switch (i) {
                    case 1:
                        bank.createAccount();
                        break;
                    case 2:
                        System.out.print("Enter Account ID: ");
                        ID = sc.next();
                        System.out.print("Enter Amount you want withdraw : ");
                        double w;
                        try {
                            w = sc.nextDouble();
                            sc.nextLine();
                            bank.withdraw(ID, w, "Cash");
                        } catch (Exception e) {
                            sc.nextLine();
                            System.out.println("Enter Number!!!");
                        }
                        break;
                    case 3:
                        System.out.print("Enter Account ID: ");
                        ID = sc.nextLine();
                        System.out.print("Enter amount to deposit: ");
                        double d;
                        try {
                            d = sc.nextDouble();
                            sc.nextLine();
                            bank.deposit(ID, d, "Cash");
                        } catch (Exception e) {
                            sc.nextLine();
                            System.out.println("Enter Number!!!");
                        }
                        break;
                    case 4:
                        bank_loop = false;
                        break;
                    default:
                        System.out.println("Enter Valid Number");
                        break;
                    }
                }
                break;
            case 2:
                ATM atm = new ATM();
                while (loop) {
                    System.out.println("1. Withdraw Amount\n2. exit");
                    int y = sc.nextInt();
                    sc.nextLine();
                    switch (y) {
                    case 1:
                        System.out.print("Enter Account ID: ");
                        ID = sc.nextLine();
                        System.out.print("Enter PIN : ");
                        try {
                            PIN = sc.nextInt();
                            sc.nextLine();
                            atm.withdraw(ID, PIN);
                        } catch (Exception e) {
                            sc.nextLine();
                            System.out.println("Enter PIN in Integer!!!");
                        }
                        
                        break;
                    case 2:
                        loop = false;
                        break;
                    default:
                        System.out.println("Please enter valid choice");
                        break;
                    }
                }
                break;
            case 3:
                OnlineTransaction a1 = new OnlineTransaction();
                while (loop) {
                    System.out.println("1. Transfer\n2. exit");
                    int z = sc.nextInt();
                    sc.nextLine();
                    switch (z) {
                    case 1:
                        System.out.print("Enter Account ID: ");
                        ID = sc.nextLine();
                        System.out.print("Enter PIN : ");
                        try {
                            PIN = sc.nextInt();
                            sc.nextLine();
                            a1.transfer(ID, PIN);
                        } catch (Exception e) {
                            sc.nextLine();
                            System.out.println("Enter PIN in Integer!!!");
                        }
                        break;
                    case 2:
                        loop = false;
                        break;
                    default:
                        System.out.println("Please enter valid choice");
                        break;
                    }
                }
                break;

            case 4:
                InstantCash ic=new InstantCash();
                System.out.println("Enter \n1. Apply for Instant Cash\n2. Pay Instant Cash");
                int f = sc.nextInt();
                sc.nextLine();
                switch (f) {
                case 1:
                    System.out.print("Enter Account ID : ");
                    String Id = sc.nextLine();
                        ic.getIC(Id);
                    
                    break;
                case 2:
                    System.out.print("Enter Instant Cash ID to PAY : ");
                    try {
                        int loan_id = sc.nextInt();
                        sc.nextLine();
                        ic.payIC(loan_id);
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Enter Cash ID in Integer!!!");
                    }
                    
                    break;
                default:
                    System.out.println("Enter Valid Choice!!!");
                    break;
                }
                break;

            case 5:
                mainLoop = false;
                break;
            default:
                System.out.println("Enter valid choice!!");
                break;
            }
        }
        catch(Exception e){
            System.out.println("Invalid Choice!!!");
        }
        }
    }
}

class Bank {
    PreparedStatement pst;
    static Connection con;
    static Scanner sc = new Scanner(System.in);
    // According to your Project location
    String projectPath = "C:\\Users\\Rahulsinh\\Desktop\\Project\\JAVA";

    /**
     * default constructor for connection with database
     * 
     * @throws SQLException,IOException
     */
    public Bank() throws SQLException, IOException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
    }

    /**
     * Creates New Account in the bank
     * 
     * @throws IOException,SQLException
     */
    void createAccount() throws IOException, SQLException {
        System.out.print("Enter Your First Name : ");
        String first_name = sc.nextLine();
        System.out.print("Enter Your last Name : ");
        String last_name = sc.nextLine();
        System.out.print("Enter Account Type: ");
        String account_type = sc.nextLine();
        String mob_no=null;
        while (true) {
            System.out.println("Enter Your Mobile Number : ");
            mob_no = sc.nextLine();
            int flag=0;
            if (mob_no.length() == 10 && mob_no.charAt(0) <='9' && mob_no.charAt(0) >='7') {
                for(int i=0;i<mob_no.length();i++){
                    if(!(mob_no.charAt(i)>='0'&&mob_no.charAt(i)<='9')){
                        flag=-1;
                    }
                }
                if(flag ==0){
                    System.out.println("Mobile Number is Valid");
                    break;
                }
            } else {
                if(mob_no.length()!=10){
                    System.out.println("Mobile Number must be 10 digits!!!");
                }
                else{
                    System.out.println("Mobile Number Starts with 9,8,7");
                }
            }
        }
        System.out.print("Enter Your Age : ");
        int age=0;
        try {
            age = sc.nextInt();
        } catch (Exception e) {

        }
        if(age<10){
            System.out.println("minimum Age Required is 10!!!");
            return;
        }
        sc.nextLine();
        System.out.print("Enter your Address : ");
        String address = sc.nextLine();
        new Account(first_name, last_name, account_type, age, mob_no, address);
    }

    /**
     * To insert details of account in the database
     * 
     * @param ID,Name,balance
     * @throws SQLException
     */
    void insert(String ID, String Name, int PIN, double balance) throws SQLException {
        String sql = "INSERT INTO account_balance VALUES (?,?,?,?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, Name);
        pst.setString(2, ID);
        pst.setInt(3, PIN);
        pst.setDouble(4, balance);
        pst.executeUpdate();
    }

    /**
     * Updates the balance of account having ID
     * 
     * @param ID,balance
     * @throws SQLException
     */
    void setBalance(String ID, double balance) throws SQLException {
        String sql = "UPDATE account_balance SET balance= ? WHERE ID= ?";
        pst = con.prepareStatement(sql);
        pst.setDouble(1, balance);
        pst.setString(2, ID);
        pst.executeUpdate();
    }

    /**
     * @param ID
     * @return balance in account of particular ID
     * @throws SQLException
     */
    double getBalance(String ID) throws SQLException {
        String sql = "SELECT balance FROM account_balance WHERE ID=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, ID);
        ResultSet rs = pst.executeQuery();
        double d = 0;
        while (rs.next()) {
            d = rs.getDouble("balance");
        }
        return d;
    }

    /**
     * @param ID acc_ID
     * @return boolean value that account having ID exists in the database or not
     * @throws SQLException
     */
    boolean idExists(String ID) throws SQLException {
        String sql = "SELECT ID FROM account_balance WHERE ID=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, ID);
        ResultSet rs = pst.executeQuery();
        String d = null;
        while (rs.next()) {
            d = rs.getString("ID");
        }
        if (d != null) {
            return true;
        }
        return false;
    }

    /**
     * Withdraws given amount from account of given ID
     * 
     * @param ID,amount,medium
     * @return withdraw amount
     * @throws SQLException
     */

    double withdraw(String ID, double amount, String medium) throws SQLException {
        if (idExists(ID)) {
            double balance = getBalance(ID);
            double newBalance = balance - amount;
            if (newBalance > 1000) {
                try {
                    writeToPassBook(ID, medium, "WithDraw", amount, newBalance);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setBalance(ID, newBalance);
                System.out.println(amount + " Withdrawn Successful");
                return amount;
            } else {
                System.out.println("Not Enough Balance!!!!");
            }
        } else {
            System.out.println("Invalid ID!!!!");
        }
        return 0.0;
    }

    /**
     * Deposits given amount in account of given ID
     * 
     * @param ID,amount,medium
     * @throws IOException,SQLException
     */
    void deposit(String ID, double amount, String medium) throws IOException, SQLException {
        if (idExists(ID)) {
            double balance = getBalance(ID);
            double newBalance = balance + amount;
            writeToPassBook(ID, medium, "Deposit", amount, newBalance);
            setBalance(ID, newBalance);
            System.out.println(amount + " Deposited Successful");
        } else {
            System.out.println("Not Valid ID!!!!");
        }
    }

    /**
     * Writes status of Account of given ID in txt file
     * 
     * @param ID,medium,type,amount,balance
     * @throws IOException
     */
    void writeToPassBook(String ID, String medium, String type, double amount, double balance) throws IOException {
        String folderPath = projectPath + "\\" + ID;
        File f = new File(folderPath, ID + ".txt");
        PrintWriter pw = new PrintWriter(new FileWriter(f, true), true);
        pw.printf("%-8s\t\t\t%-10.2f\t\t\t%-10.2f\t\t%-20s\n", type, amount, balance, medium);
        pw.close();
    }
}

class Account extends Bank {
    String name, ID, mob_no, account_type;
    private String address;
    private int age;
    protected int PIN;
    double balance;

    /**
     * parameterized constructor to fill up Account Details
     * 
     * @param name,account_type,age,mob_no,address,ID
     * @throws IOException,SQLException
     */
    public Account(String first_name, String last_name, String account_type, int age, String mob_no, String address)
            throws IOException, SQLException {
        this.name = first_name + " " + last_name;
        this.account_type = account_type;
        this.address = address;
        this.age = age;
        this.mob_no = mob_no;
        this.ID = first_name + (int) (Math.random() * 1000);
        this.balance = 0.0;
        while (true) {
            int randomPIN = (int) (Math.random() * 10000);
            String temp = randomPIN + "";
            if (temp.length() == 4) {
                this.PIN = randomPIN;
                break;
            }
        }
        File a = new File(projectPath, "BankDetails.txt");
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(a, true));
        bw1.write(toString());
        bw1.newLine();
        bw1.close();

        File f1 = new File(projectPath, ID);
        f1.mkdir();
        File f2 = new File(f1, ID + ".txt");
        f2.createNewFile();
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(f2, true));
        bw2.write(toString());
        bw2.newLine();
        bw2.write("Cause\t\t\t\tAmount\t\t\t\tBalance\t\t\tMedium\n");
        bw2.write("--------------------------------------------------------------------\n");
        bw2.close();
        insert(ID, name, PIN, 0);
        double amount = 0.0;
        while (amount < 1000) {
            System.out.println("Enter Opening Amount(Must be > 1000) : ");
            try {
                amount = sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Enter Number!!!");
            }
            sc.nextLine();
        }
        deposit(ID, amount, "Opening");

        System.out.println("Account Has been Created");
        System.out.println(toString());

    }

    /**
     * @Override
     * @return details of particular account
     */
    public String toString() {
        return "ID       : " + ID + "\n" + "NAME     : " + name + "\n" + "ACC_TYPE : " + account_type + "\n"
                + "PIN      : " + PIN + "\n" + "AGE      : " + age + "\n" + "MOBILE   : " + mob_no + "\n"
                + "ADDRESS  : " + address + "\n";
    }
}

class ATM extends Bank {
    int maxTry;
    ATM() throws SQLException, IOException {
        super();
    }

    /**
     * Withdraws amount from ATM using acc_ID and PIN
     * 
     * @param ID,PIN
     * @throws SQLException
     */
    void withdraw(String ID, int PIN) throws SQLException {
        String sql = "SELECT ID FROM account_balance WHERE ID=? AND PIN=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, ID);
        pst.setInt(2, PIN);
        ResultSet rs = pst.executeQuery();
        String id = null;
        while (rs.next()) {
            id = rs.getString("ID");
        }
        if (id != null  && maxTry<3) {
            System.out.print("Enter Amount you want withdraw : ");
            double amount = sc.nextDouble();
            sc.nextLine();
            super.withdraw(ID, amount, "ATM");
        } else {
            maxTry++;
            if(maxTry>=3){
                System.out.println("MAximum Try Reached");
            }
            else{
                System.out.println("ID or PIN is Invalid!!");
            }
            
        }
    }
}

class OnlineTransaction extends Bank {
    int maxTry;
    OnlineTransaction() throws SQLException, IOException {
        super();
    }

    void transfer(String ID, int PIN) throws SQLException, IOException {
        if (!idExists(ID)) {
            return;
        }
        String sql = "SELECT ID FROM account_balance WHERE ID=? AND PIN=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, ID);
        pst.setInt(2, PIN);
        ResultSet rs = pst.executeQuery();
        String d = null;
        while (rs.next()) {
            d = rs.getString("ID");
        }
        if (d != null && maxTry<5) {
            System.out.print("Enter ID of Transfer Account : ");
            String id = sc.nextLine();
            if (super.idExists(id)) {
                System.out.print("Enter Amount you want withdraw : ");
                double w = sc.nextDouble();
                sc.nextLine();
                double t = super.withdraw(ID, w, "Online");
                super.deposit(id, t, "Online");
            }
        } else {
            maxTry++;
            if(maxTry>=5){
                System.out.println("Maximum Try Reached!!!");
            }else{
                System.out.println("ID or PIN is Invalid!!");
            }
            return;
        }
    }
}

class InstantCash extends Bank {
    int ic_id;
    double ic_amount, interest, payable_amount;
    double rate = 9.5;

    InstantCash() throws SQLException, IOException {
        super();
    }

    /**
     * Tries to approve Instant Cash for account Holder of given acc_id
     * 
     * @param acc_id
     * @throws SQLException
     * @throws IOException
     */
    void getIC(String acc_id) throws SQLException, IOException {
        if (idExists(acc_id) && !instantCashExists(acc_id)) {
            double maxAllowedIC=getBalance(acc_id)*0.6;
            System.out.println("Max Instant Cash : "+maxAllowedIC);
            System.out.print("Enter Amount You Want : ");
            try{
            ic_amount = sc.nextDouble();
            sc.nextLine();
            if(ic_amount>maxAllowedIC){
                System.out.println("You are not allowed to get Instant Cash");
                return;
            }
            System.out.println("Interest Rate is : " + rate + " Per Month");
            System.out.print("Enter Duration in month : ");
            double duration = sc.nextDouble();
            sc.nextLine();
            interest = ((ic_amount * rate * duration) / 100);
            payable_amount = ic_amount + interest;
            System.out.println("Total Amount to Pay is : " + payable_amount);

            System.out.println("Enter \n1. Accept\n2. Reject");
            int i = sc.nextInt();
            sc.nextLine();
            if (i == 1) {
                while (true) {
                    ic_id = (int) (1000 * Math.random());
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM instantcash WHERE ic_id=" + ic_id);
                    if (!rs.next()) {
                        break;
                    }
                }

                deposit(acc_id, ic_amount, "Loan Approval");
                pst = con.prepareStatement("INSERT INTO instantcash VALUES (?,?,?,?)");
                pst.setInt(1, ic_id);
                pst.setString(2, acc_id);
                pst.setDouble(3, ic_amount);
                pst.setDouble(4, payable_amount);
                pst.executeUpdate();
                System.out.println("Loan Approved successfully");
                System.out.println("Loan ID : " + ic_id + "\nAccount ID : " + acc_id + "\nAmount : " + ic_amount
                        + "\nPayable Amount : " + payable_amount);

            } else {
                System.out.println("Thanks to visit");
            }
            } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("Enter Valid Number!!!");
                    } catch(Exception e){
                        sc.nextLine();
                        System.out.println("Error In Get Instant Cash!!!");
                    }
        } else {
            if(instantCashExists(acc_id)) {
                System.out.println("Instant Cash already taken on this account.\nFirst pay existing amount!!");
            } else {
                System.out.println("Sorry!!!,Try Again ID not exist!!!");
            }
        }
    }

    private boolean instantCashExists(String acc_id) throws SQLException {
        String sql = "SELECT ic_id FROM instantcash WHERE acc_id=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, acc_id);
        ResultSet rs = pst.executeQuery();
        String d = null;
        while (rs.next()) {
            d = rs.getString(1);
        }
        if (d != null) {
            return true;
        }
        return false;
    }

    /**
     * get payment of loan of given loan_id
     * 
     * @param ic_id
     * @throws SQLException
     * @throws IOException
     */
    void payIC(int ic_id) throws SQLException, IOException {
        pst = con.prepareStatement("SELECT * FROM instantcash WHERE ic_id=?");
        pst.setInt(1, ic_id);
        ResultSet rst = pst.executeQuery();
        String acc_id = null;
        double pay_amount = 0;
        while (rst.next()) {
            acc_id = rst.getString(2);
            pay_amount = rst.getDouble(4);
        }

        double amount = withdraw(acc_id, pay_amount, "Instant Cash Payed");
        if (amount != 0.0) {
            System.out.println("Instatnt Cash Payed SuccessFully!!!");
            PreparedStatement pst = con.prepareStatement("delete from instantcash where ic_id=?");
            pst.setInt(1, ic_id);
            pst.executeUpdate();
        } else {
            System.out.println("Not Valid Balance In Your Account!!!");
        }
    }
}