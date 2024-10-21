import java.util.*;

public class CNM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Diary d = new Diary();
        int i = 0;
        String name, mob_no;
        while (i != 6) {
            System.out.println("Enter \n1.Add Contact\n2.Delete Contact\n3.Search Contact\n4.Sort\n5.Display\n6.Exit");
            try {
                i = sc.nextInt();
                sc.nextLine();
                switch (i) {
                    case 1:
                        System.out.println("Enter Name : ");
                        name = sc.nextLine();
                        System.out.println("Enter Mobile Number : ");
                        mob_no = sc.nextLine();
                        if (checkMobnoValidity(mob_no)) {
                            d.addContact(name, mob_no);
                        }
                        break;

                    case 2:
                        System.out.println("Enter Name : ");
                        name = sc.nextLine();
                        d.deleteContact(name);
                        break;

                    case 3:
                        System.out.println("Enter Name : ");
                        d.searchContact(sc.nextLine());
                        break;

                    case 4:
                        System.out.println("Contact Diary Is Sorted");
                        d.sortDiary();
                        d.displayDiary();
                        break;

                    case 5:
                        System.out.println("Contact's : ");
                        d.displayDiary();
                        break;

                    case 6:
                        System.out.println("Exit...");
                        break;

                    default:
                        System.out.println("Enter Valid Choice!!!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Enter Valid Integer Choice!!!");
                break;
            }
        }
        sc.close();
    }

    public static boolean checkMobnoValidity(String mobNo) {
        if (mobNo.length() != 10) {
            System.out.println("Mobile Number Must Be 10 Digits!!!");
            return false;
        } else {
            for (int i = 0; i < 10; i++) {
                if (!(mobNo.charAt(i) >= '0' && mobNo.charAt(i) <= '9')) {
                    System.out.println("Enter Digit!!!");
                    return false;
                } else {
                    if (!(mobNo.charAt(0) > '5')) {
                        System.out.println("Contact Number Must Start with 9,8,7,6");
                        return false;
                    }
                }
            }
            return true;
        }
    }
}

class Diary {
    class Contact {
        String name, mob_no;
        Contact next, prev;

        public Contact(String name, String mob_no) {
            this.name = name;
            this.mob_no = mob_no;
            next = null;
            prev = null;
        }

        @Override
        public String toString() {
            return "Contact [name=" + name + ", mob_no=" + mob_no + "]";
        }
    }

    Contact first = null;

    void addContact(String name, String mob_no) {
        Contact newContact = new Contact(name, mob_no);
        if (first == null) {
            first = newContact;
        } else {
            Contact temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            newContact.prev = temp;
            temp.next = newContact;
            temp = null;
        }
    }

    Contact deleteContact(String name) {
        Contact c = null;
        if (first == null) {
            System.out.println("Diary Is Empty!!!");
        } else if (first.name.equalsIgnoreCase(name)) {
            c = first;
            if (first.next == null) {
                first = null;
            } else {
                first = first.next;
                first.prev.next = null;
                first.prev = null;
            }
        } else {
            Contact temp = first;
            Contact dContact = null;
            while (temp.next != null && !temp.next.name.equalsIgnoreCase(name)) {
                temp = temp.next;
            }
            if (temp.next != null) {
                dContact = temp.next;
                temp.next = temp.next.next;

                if (temp.next != null)
                    temp.next.prev = temp;

                dContact.next = null;
                dContact.prev = null;
                System.out.println(dContact.toString() + " is Deleted");
                c = dContact;
            } else {
                System.out.println("Contact Not Found!!!");
            }
        }
        return c;
    }

    void searchContact(String name) {
        if (first == null) {
            System.out.println("Contact Diary Is Empty!!!");
        } else if (first.name.equalsIgnoreCase(name)) {
            System.out.println(first.toString());
        } else {
            Contact temp = first;
            while (temp.next != null && !temp.next.name.equalsIgnoreCase(name)) {
                temp = temp.next;
            }
            if (temp.next != null) {
                System.out.println(temp.next.toString());
            }
        }
    }

    void sortDiary() {
        if (first == null) {
            System.out.println("Empty!!!");
        } else {
            Contact min;
            for (Contact i = first; i.next != null; i = i.next) {
                min = i;
                for (Contact j = i.next; j != null; j = j.next) {
                    if (j.name.compareToIgnoreCase(min.name) < 0) {
                        min = j;
                    }
                }
                String name = i.name;
                String mob_no = i.mob_no;
                i.name = min.name;
                i.mob_no = min.mob_no;
                min.name = name;
                min.mob_no = mob_no;
            }
        }
    }

    void displayDiary() {
        Contact temp = first;
        if (first == null) {
            System.out.println("Empty!!!");
        } else {
            while (temp != null) {
                System.out.println(temp.toString());
                temp = temp.next;
            }
        }
    }
}
