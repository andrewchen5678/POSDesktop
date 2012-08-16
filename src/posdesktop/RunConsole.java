package posdesktop;

import ordering.*;
import java.sql.*;
import java.util.Scanner;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angelchen
 */
public class RunConsole {

    private SqlFunc sqlcus;
    private ResultSet srs;
    private Connection con;

    public RunConsole(Connection theCon) throws Exception {
        sqlcus = new SqlFunc(con);
        this.con = theCon;
    //run();
    }

    public void run() throws Exception {

        Scanner kb = new Scanner(System.in);
        String phone;
        ResMenu theMenu = new ResMenu(con);
        
        while (true) {
            System.out.print("Enter s to search, c to create new customer, q to quit, p to print menu: ");
            char choice = kb.nextLine().charAt(0);

            if (choice == 's') {

                System.out.print("Please enter phone number: ");
                phone = kb.nextLine();
                if (phone.length() > SqlFunc.PHONE_LENGTH) {
                    System.out.println("Phone number length cannot be > " + SqlFunc.PHONE_LENGTH);
                    continue;
                }
                srs = sqlcus.searchbyPhone(phone, SqlFunc.EXACT_MATCH);
                //System.out.println("is before first? "+srs.isBeforeFirst());
                //printResultLoop(srs);
                if(srs.next()){
                    printCurrentCursor(srs);
                    orderPage(theMenu,srs);
                }
            } else if (choice == 'c') {
                //System.out.println("Before creating: ");
                //printResult(srs);
                System.out.print("Please enter phone number for new customer: ");
                phone = kb.nextLine();
                if (phone.length() != SqlFunc.PHONE_LENGTH) {
                    System.out.println("phone number length has to be " + SqlFunc.PHONE_LENGTH);
                    continue;
                }
                srs = sqlcus.searchbyPhone(phone, SqlFunc.EXACT_MATCH);
                if (srs.isBeforeFirst()) {
                    System.out.println("phone number already exist for :");
                    printResultLoop(srs);
                    continue;
                }
                System.out.print("Please enter name for new customer: ");
                String name = kb.nextLine();
                if (name.length() > SqlFunc.NAME_MAX_LENGTH) {
                    System.out.println("Name can't be more than " + SqlFunc.NAME_MAX_LENGTH + " characters");
                    continue;
                }
                srs = sqlcus.searchbyName(name, SqlFunc.EXACT_MATCH);
                if (srs.isBeforeFirst()) {
                    System.out.println("Name already exist for :");
                    printResultLoop(srs);
                    continue;
                }
                System.out.print("Please enter address for new customer: ");
                String address = kb.nextLine();
                if (address.length() > SqlFunc.ADDRESS_MAX_LENGTH) {
                    System.out.println("Address can't be more than " + SqlFunc.ADDRESS_MAX_LENGTH + " characters");
                    continue;
                }
                srs = sqlcus.searchbyAddress(address, SqlFunc.EXACT_MATCH);
                if (srs.isBeforeFirst()) {
                    System.out.println("Address already exist for :");
                    printResultLoop(srs);
                    continue;
                }
                sqlcus.createCustomer(phone, name, address);
            //System.out.println("After creating: ");
            //printResult(srs);
            } else if (choice == 'q') {
                break;
            } else if (choice == 'p') {
                //PrintStream ps = new PrintStream(System.out, true, "UTF-8")
                System.out.println(theMenu);
            } else {
                System.out.println("Invalid choice");
            }
        }

        //printResult(srs);
    }

    private static void printResultLoop(ResultSet srs) throws Exception {
        if (srs.next()) {
            do {
                System.out.println("Customer ID = " + srs.getInt("CustomerID"));
                System.out.println("Phone number = " + srs.getString("Phone"));
                System.out.println("Customer name = " + srs.getString("Name"));
                System.out.println("Customer Address = " + srs.getString("Address"));
            } while (srs.next());
        } else {
            System.out.println("no result found");
        }
    }

    private static void printCurrentCursor(ResultSet srs) throws Exception {
        System.out.println("Customer ID = " + srs.getInt("CustomerID"));
        System.out.println("Phone number = " + srs.getString("Phone"));
        System.out.println("Customer name = " + srs.getString("Name"));
        System.out.println("Customer Address = " + srs.getString("Address"));
    }
    /*    
    private static boolean getCurrentCustomer(ResultSet srs) throws Exception{
    
    if(srs.next()) {
    System.out.println("Customer ID = " + srs.getInt("CustomerID"));
    System.out.println("Phone number = " + srs.getString("Phone"));
    System.out.println("Customer name = " + srs.getString("Name"));
    System.out.println("Customer Address = " + srs.getString("Address"));
    }
    }
     */

    private static void orderPage(ResMenu theMenu, ResultSet srs) throws Exception {
        int cusID = srs.getInt("CustomerID");
        String phone = srs.getString("Phone");
        String name = srs.getString("Name");
        String address = srs.getString("Address");
        Customer theCustomer=new Customer(cusID,phone,name,address);
        Entry en=new Entry(theCustomer,MainProgram.getSqlConnection());
        Scanner kb = new Scanner(System.in);
        while(true){
            System.out.print("Enter p to print current customer info, m to print menu, o to order, e to print current entry, q to quit, v to void:");
            char choice = kb.nextLine().charAt(0);
            if(choice=='p'){
                System.out.println(theCustomer);
            } else if(choice=='m'){
                System.out.println(theMenu);
            } else if(choice=='o'){
                System.out.print("Please enter the number of the item: ");
                int itemNum=kb.nextInt(); kb.nextLine();
                System.out.print("Please enter the quantity: ");
                int quantity=kb.nextInt(); kb.nextLine();
                en.addLast(theMenu.getItem(itemNum-1), quantity);
                System.out.println("current entry: ");
                en.printEntry(System.out);
            } else if(choice=='q'){
                return;
            } 
            else if(choice=='e'){
                //System.out.println("current entry: " + en);
                System.out.println("current entry: ");
                en.printEntry(System.out);
            } else if(choice=='v'){
                System.out.print("which one to void, l for last one? ");
                String which=kb.nextLine();
                int num;
                if(which.charAt(0)=='l')
                    en.removeLast();
                else {
                    num=Integer.parseInt(which);
                    en.removeItemFromFirst(num);
                }
                System.out.println("Entry after void: ");
                en.printEntry(System.out);
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
