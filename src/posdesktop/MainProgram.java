/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package posdesktop;

import java.sql.*;
import javax.swing.JOptionPane;
import java.io.*;
import jpos.JposException;
import jpos.POSPrinterControl111;
import jpos.POSPrinter;
import ordering.POSPtrEntry;
//import ordering.*;
//import java.math.*;
/**
 *
 * @author angelchen
 */
public class MainProgram {
    private static Connection con;
    private static POSPrinterControl111 ptr;
    private static final int MAX_PREV=8;
    //private static POSPtrEntry prevEntry;
    private static POSPtrEntry prevEntry[];
    private static SqlFunc sqlcus;
    private static boolean runConsole;
    public static POSPtrEntry[] getPrevEntryArray(){
        return prevEntry;
    }

    public static POSPrinterControl111 getPtr(){
        return ptr;
    }
    
    public static void pushPrevEntry(POSPtrEntry en){
        //prevEntry=en;
        if(prevEntry==null) prevEntry=new POSPtrEntry[MAX_PREV];
        for(int i=1;i<MAX_PREV;i++){
            prevEntry[i-1]=prevEntry[i];
        }
        prevEntry[MAX_PREV-1]=en;
    }
    
    public static void main(String args[]) throws Exception {
        runConsole=false;
        FileWriter fwlock=null;
        File lockFile=new File(System.getProperty("user.home"), "POSDesktop.lock");
if (lockFile.exists()&&!lockFile.delete()) {
    if(args.length != 0 && args[0].equals("--console")){
        System.out.println("Application Already Running!");
    }
    else{
        JOptionPane.showMessageDialog(null,"Application already running!");
    }
    System.exit(1);
} else{
    lockFile.createNewFile();
    lockFile.deleteOnExit();
    fwlock=new FileWriter(lockFile);
} 
        /*
        MenuItem test=null;
        try{
            test = new MenuItem("haha1","haha2",new BigDecimal(-12));
        } catch (InvalidPriceException e){
            System.out.println(test);
            return;
        }
         */ 
        final String CONFIGFILE = "pos.conf";
        Class.forName("com.mysql.jdbc.Driver");
        String line, sqlhost = null, sqlport = null, sqldatabase = null, sqluser = null, sqlpass = null;
        BufferedReader input = null;
        boolean useconfig = true; //default use config file

        try {
            input = new BufferedReader(new FileReader(CONFIGFILE));
        } catch (FileNotFoundException e) {
            System.out.println("The config file " + CONFIGFILE + " doesn't exist");
            useconfig = false;
        }
        while (useconfig && (line = input.readLine()) != null) {
            if (line.indexOf("=") < 0) {
                System.out.println("Invalid config file");
                return;
            } else if (line.trim().equals("")) {
                continue;
            }
            String arg1 = line.substring(0, line.indexOf("="));
            String arg2 = line.substring(line.indexOf("=") + 1);
            if (arg1.equals("sqlhost")) {
                sqlhost = arg2;
            } else if (arg1.equals("sqlport")) {
                sqlport = arg2;
            } else if (arg1.equals("sqldatabase")) {
                sqldatabase = arg2;
            } else if (arg1.equals("sqluser")) {
                sqluser = arg2;
            } else if (arg1.equals("sqlpass")) {
                sqlpass = arg2;
            } else {
                System.out.println("Invalid config option " + arg1);
                return;
            }
        }

        //System.out.println(sqlhost+sqlport+sqldatabase+sqluser+sqlpass);

        try {
            if (useconfig) {
                con = DriverManager.getConnection("jdbc:mysql://" + sqlhost + ":" + sqlport + "/" + sqldatabase,
                        sqluser, sqlpass);
            } else {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos",
                        "pos", "qwertasdfg");
            }
        } catch (Exception e) {
            if (args.length != 0 && args[0].equals("--console")) {
                System.out.println("Error when getting connection to database \n" + e.getMessage() + "Caused by " + e.getCause());
            } else {
                JOptionPane.showMessageDialog(null, "Error when getting connection to database \n" + e.getMessage() + "Caused by " + e.getCause(), "Error connecting", JOptionPane.ERROR_MESSAGE);
            }
            //System.err.println("Caused by " + e.getCause());
            System.exit(1);
        }
        sqlcus=new SqlFunc(con);
        runConsole=args.length != 0 && args[0].equals("--console");
        if (runConsole) {
            RunConsole runcon = new RunConsole(con);
            runcon.run();
            //closeSqlConnection();
        } else {
            ptr=(POSPrinterControl111)new POSPrinter();
            initPrinter();
            //RunGui mp = new RunGui(null,true,con,ptr);
            RunGui mp = new RunGui(null,true,con,sqlcus);
            mp.setVisible(true);
        }
        closeSqlConnection();
        closePrinter();
        fwlock.close();
        //System.out.println("end of main");
    }
   
    public static SqlFunc getSqlcus(){
        return sqlcus;
    }
    
    public static boolean restartPrinter(){
        closePrinter();
/*        ptr=(POSPrinterControl111)new POSPrinter();
        if(initPrinter())
            return ptr;
        else
            return null;
 */     
        return initPrinter();
    }
    
    private static boolean initPrinter(){
        try{
        ptr.open("POSPrinter");
        //Get the exclusive control right for the opened device.
        //Then the device is disable from other application.
        ptr.claim(1000);
        //Enable the device.
        ptr.setDeviceEnabled(true);
        }catch(JposException e){
            e.printStackTrace();
            if(runConsole) System.err.println("Error enabling the printer:\n"+e.getMessage());
            else JOptionPane.showMessageDialog(null, "Error enabling the printer:\n"+e.getMessage(), null, JOptionPane.ERROR_MESSAGE);;
            return false;
        }
        return true;
    }
    
    public static void closePrinter(){
        try{
        System.out.println("disabling printer");
        ptr.setDeviceEnabled(false);
        }catch(JposException e){
            //e.printStackTrace();
            System.err.println("Error disabling the printer:\n"+e.getMessage());
        }
        try{
        System.out.println("releasing printer");
        ptr.release();
        }catch(JposException e){
            //e.printStackTrace();
        System.err.println("Error releasing the printer:\n"+e.getMessage());
        }
        try{
        System.out.println("closing printer");
        ptr.close();
        System.out.println("Printer closed");
        }catch(JposException e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error closing the printer:\n"+e.getMessage(), null, JOptionPane.ERROR_MESSAGE);;
        }
    } 
   
    public static Connection getSqlConnection(){
        return con;
    }
    public static void closeSqlConnection() {
        System.out.println("Closing the sql connection");
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error closing sql connection");
        }
    }
    
}
