/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Project2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        RegularUser mall = new RegularUser();
        Shop s1 = new Shop("Zara", "Clothing", "Alhokair", false, null);
        Shop s2 = new Shop("Nike", "Sports", "Alshaya", false, s1);
        Shop s3 = new Shop("Apple_store", "Electronics", "Alhokair", true, s2);
        Shop s4 = new Shop("Sephora", "Beauty", "Alhokair", false, s3);

        String filename = "Mall.txt";

        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Welcome. Here's a list for all the shops in the mall:  \n");
            bw.write("1. Sephora / Beauty \t 2. Apple_store / Electronics \t  3. Nike / Sports \t 4. Zara / Clothing ");
            bw.close();

        } catch (IOException ex) {
            System.out.println("error writing a file" + filename + "'");
        }

        String line = null;
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

        } catch (FileNotFoundException ex) {
            System.out.println("unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("error reading file " + filename);
        }

        MallOwner mo = new MallOwner();
        Text t = new Text();

        int user;
        int chooseOwner;
        int chooseRegular;
        int choose;

        do {
            System.out.println("\nAre you a mall owner or regular user?\n1. owner \n2. regular");
            user = input.nextInt();
            do {
                switch (user) {
                    case 1:
                        System.out.println("You're now logged in as Mall owner.");
                        t.showOwner();
                        chooseOwner = input.nextInt();

                        switch (chooseOwner) {
                            case 1:
                                System.out.println("Enter the new shop you want to add:");
                                mo.insertLast(s4);
                                mo.modisplay(s4);
                                break;
                            case 2:
                                System.out.println("Enter the position of shop you want to delete:");
                                mo.deleteAtPosition(s4, chooseOwner);
                                mo.modisplay(s4);
                                break;
                            case 3:
                                System.out.println("Enter shop number to add employees:");
                                int shop = input.nextInt();
                                System.out.println("Enter the number of employees you want to increase:");
                                int num = input.nextInt();
                                if (num <= 0) {
                                    mo.empdisplay2(s4, shop, num, true);
                                } else {
                                    System.out.println("Enter shop number to add employees:");
                                    mo.empdisplay(s4, shop, num);
                                }
                                break;
                            case 4:
                                System.out.print("open shops: ");
                                mall.displayTimeTrue(s4);
                                break;

                            case 5:
                                System.out.print("closed shops: ");
                                mall.displayTimeFalse(s4);

                                break;
                            case 6:
                                System.out.println("opened shops: ");
                                mall.displayTimeTrue(s4);

                                System.out.println("\nclosed shops: ");
                                mall.displayTimeFalse(s4);

                                System.out.println("\nShops with the name of the owner: ");

                                mo.modisplay(s4);
                                break;
                            case 7:
                                System.out.println("choose 1 to change time / choose 2 to change number of employee, and 3 to exit: ");
                                choose = input.nextInt();
                                if (choose == 1) {
                                    System.out.println("enter shop name to change the time:  ");
                                    String t1 = input.next();
                                    System.out.println("choose 1 to close shop / choose 2 to open shop ");
                                    int choice = input.nextInt();
                                    if (choice == 1) {
                                        t1.equalsIgnoreCase(s4.shop_name);
                                        mo.empdisplay3(s4, t1);

                                    } else if (choice == 2) {
                                        t1.equalsIgnoreCase(s4.shop_name);
                                        mo.empdisplay4(s4, t1);
                                    }
                                    break;

                                } else if (choose == 2) {
                                    System.out.println("enter shop number to change number of employees:  ");
                                    shop = input.nextInt();
                                    System.out.println("enter the number of employees you want to increase:");
                                    num = input.nextInt();
                                    if (num <= 0) {
                                        mo.empdisplay2(s4, shop, num, false);
                                    }
                                    if (num > 0) {
                                        System.out.println("enter shop number to add employees ");
                                        mo.empdisplay(s4, shop, num);
                                    }
                                    if (choose == 3) {
                                        break;
                                    }
                                    break;

                                }
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("You're now logged in as Regular user.");
                        t.showRegular();
                        chooseRegular = input.nextInt();

                        switch (chooseRegular) {
                            case 1:
                                System.out.print("open shops: ");
                                mall.displayTimeTrue(s4);
                                break;

                            case 2:
                                System.out.print("closed shops: ");
                                mall.displayTimeFalse(s4);

                                break;
                            case 3:
                                System.out.println("opened shops: ");
                                mall.displayTimeTrue(s4);

                                System.out.println("\nclosed shops:");
                                mall.displayTimeFalse(s4);
                                break;
                            case 4:
                                System.out.println("Enter shop name to view details: ");
                                String s = input.next();
                                mall.findPosition(s4, s);
                                break;
                        }
                    default:
                }

            } while (user == 0);
            System.out.println();
            System.out.println("\ngo back to the main menu? \n1. yes \n0. stop the program");
            user = input.nextInt();

        } while (user != 0);
    }

}

class Text {

    void showOwner() {
        System.out.println("Choose: \n1. for adding shop"
                + "\n2. for deleting shop "
                + "\n3. to increase employee. "
                + "\n4. generate a report for all opened shops "
                + "\n5. generate a report for all closed shops "
                + "\n6. show all opened and closed shops "
                + "\n7. to change shop information"
                + "\n8. exit to the menu");
    }

    void showRegular() {
        System.out.println("Choose: \n1. generate a report for all opened shops "
                + "\n2. generate a report for all closed shops "
                + "\n3. generate a report for all open and closed shops "
                + "\n4. enter shop number to find its details"
                + "\n5. exit to the menu");
    }
}

class Shop {

    String shop_name;
    String shop_owner;
    String type;
    int num_emp;
    boolean time;
    Shop link;
    int count;

    Shop() {
        shop_name = "-";
        shop_owner = "-";
        type = "-";
        num_emp = 0;
        time = true;
        link = null;
        count = 1;

    }

    Shop(String shop_name, String shop_owner, String type, int num_emp, boolean time, Shop link) {
        this.shop_name = shop_name;
        this.shop_owner = shop_owner;
        this.type = type;
        this.time = time;
        this.link = link;
    }

    Shop(String shop_name, int num_emp) {
        this.shop_name = shop_name;
        this.num_emp = num_emp;
    }

    Shop(String shop_name, String type, boolean time, Shop link) {

        this.shop_name = shop_name;
        this.type = type;
        this.link = link;
        this.time = time;

    }

    Shop(String shop_name, String type, String shop_owner, boolean time, Shop link) {
        this.shop_name = shop_name;
        this.shop_owner = shop_owner;
        this.type = type;
        this.time = time;
        this.link = link;

    }

}

class RegularUser {

    Scanner input = new Scanner(System.in);

    void displayList(Shop head) {
        Shop current = head;
        int count = 1;
        while (current.link != null) {

            System.out.print(count + ". " + current.shop_name + " / " + current.type + " \t");
            current = current.link;
            count++;
        }
        System.out.println(count + ". " + current.shop_name + " / " + current.type);
    }

    void displayTimeTrue(Shop head) {
        Shop current = head;
        int count = 1;
        while (current != null) {
            if (current.time == true) {
                System.out.print(count + ". " + current.shop_name + " \t");
            }
            current = current.link;
            count++;
        }
    }

    void displayTimeFalse(Shop head) {
        Shop current = head;
        int count = 1;
        while (current != null) {
            if (current.time == false) {
                System.out.print(count + ". " + current.shop_name + " \t");
            }
            current = current.link;
            count++;
        }
    }

    void findPosition(Shop head, String position) {

        Shop current = head;
        while (current != null) {

            if (current.shop_name.equalsIgnoreCase(position)) {

                if (current.time == false) {
                    System.out.print("shop name: " + current.shop_name + " / type: " + current.type + " / owner: " + current.shop_owner + " / time: closed \t");
                } else {
                    System.out.println("shop name: " + current.shop_name + " / type: " + current.type + " / owner: " + current.shop_owner + " / time: open \t");
                }
            }

            current = current.link;

        }

    }
}

class MallOwner {

    Scanner input = new Scanner(System.in);
    int num;
    String shop_name;
    String owner_name;
    String type;
    boolean time;
    Shop head;
    int count;

    MallOwner() {

    }

    MallOwner(String shop_name, String type, String owner_name, boolean time, Shop head) {
        this.shop_name = shop_name;
        this.owner_name = owner_name;
        this.type = type;
        this.time = time;
        this.head = head;

    }

    Shop insertLast(Shop head) {

        System.out.print("Shop name: ");
        shop_name = input.next();
        System.out.print("\nShop type: ");
        type = input.next();
        System.out.print("\nShop owner: ");
        owner_name = input.next();
        time = true;
        
        Shop NewStore = new Shop(shop_name, type, owner_name, false, null);
        Shop previous = head;
        while (previous.link != null) {
            previous = previous.link;
        }
        NewStore.link = previous.link;
        previous.link = NewStore;
      

        return head;
    }

    Shop deleteAtPosition(Shop head, int position) {
        position = input.nextInt();
        Shop previous = head;
        int count = 1;
        while (count < position - 1) {
            previous = previous.link;
            count++;
        }
        previous.link = previous.link.link;
        return head;
    }

    void modisplay(Shop head) {
        Shop current = head;
        int count = 1;
        while (current.link != null) {
            System.out.print(count + ". " + current.shop_name + " / Type: " + current.type + " / Owner: " + current.shop_owner + " / number of employees: "+current.num_emp+" \t");
            current = current.link;
            count++;
        }
        System.out.println(count + ". " + current.shop_name + " / Type: " + current.type + " / Owner: " + current.shop_owner + " / number of employees: "+current.num_emp+" \t");
    }

    void empdisplay(Shop head, int num, int emp) {
        Shop current = head;
        Shop prev = head;
        int count = 1;
        while (current.link != null && count < num) {
            prev = prev.link;
            if (current.num_emp == num) {
                System.out.print(count + ". " + current.shop_name + " / " + emp + " \t");
            }
            current = current.link;
            count++;
        }
        System.out.println(count + ". " + current.shop_name + " / " + emp + " employee(s) ");
    }

    void empdisplay2(Shop head, int num, int emp, boolean time) {
        Shop current = head;
        Shop prev = head;
        int count = 1;
        while (current.link != null && count < num) {
            prev = prev.link;
            if (current.num_emp == num) {
                System.out.print(count + ". " + current.shop_name + " / " + emp + " \t");
            }
            current = current.link;
            count++;
            current.time = false;
        }
        System.out.println(count + ". " + current.shop_name + " / open shop: " + current.time + " / employee(s): 0 ");
    }

    void empdisplay3(Shop head, String t1) {
        Shop current = head;
        while (current != null) {
            if (current.shop_name.equalsIgnoreCase(t1)) {

                System.out.println(current.shop_name + " / shop time set to be closed." + " \t");
            }
            current = current.link;
        }
    }

    void empdisplay4(Shop head, String t1) {
        Shop current = head;
        while (current != null) {
            if (current.shop_name.equalsIgnoreCase(t1)) {

                System.out.println(current.shop_name + " / shop time set to be open." + " \t");
            }
            current = current.link;
        }
    }
}
