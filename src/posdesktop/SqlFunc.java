/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package posdesktop;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author angelchen
 */
public class SqlFunc {
    //constant declarations
    public static final boolean EXACT_MATCH=true;
    public static final boolean NOEXACT_MATCH=false;
    public static final byte NO_DUPLICATE=0;
    public static final byte PHONE_EXISTS=1;
    public static final boolean PHONE_EXISTS_BOOL=true;
    public static final byte NAME_EXISTS=2;
    public static final byte ADDRESS_EXISTS=3;
    /*
    public static final byte NOTHING_SETTLE=1;
    public static final byte WRONG_PASS=2;
     */ 
    public static final int NAME_MAX_LENGTH=40;
    public static final int ADDRESS_MAX_LENGTH=255;
    public static final int PHONE_LENGTH=10;
    public static final String AREACODE="562";
    private static final String customerOrderSql="CREATE TABLE `customerorder` ("+
                                                "`OrderID` int(11) NOT NULL auto_increment,"+
                                                "`CustomerName` varchar(40) collate utf8_unicode_ci default NULL,"+
                                                "`Phone` varchar(255) collate utf8_unicode_ci default NULL,"+
                                                "`Address` varchar(255) collate utf8_unicode_ci default NULL,"+
                                                "`OrderType` enum('here','pick up','delivery') collate utf8_unicode_ci default NULL,"+
                                                "`Subtotal` decimal(10,2) default NULL,"+
                                                "`Tax` decimal(10,2) default NULL,"+
                                                "`Total` decimal(10,2) default NULL,"+
                                                "`Time` timestamp NULL default NULL,"+
                                                "PRIMARY KEY  (`OrderID`)"+
                                                ") ENGINE=MyISAM";
    private static final String orderDetailSql="CREATE TABLE `orderdetail` ("+
                                              "`EntryID` int(11) NOT NULL auto_increment,"+
                                              "`OrderID` int(11) NOT NULL,"+
                                              "`ItemQuantity` int(11) NOT NULL,"+
                                              "`ItemName` varchar(40) collate utf8_unicode_ci NOT NULL,"+
                                              "`ItemNameCN` varchar(20) collate utf8_unicode_ci default NULL,"+
                                              "`ItemPrice` decimal(10,2) NOT NULL,"+
                                              "`MiscItem` tinyint(2) default NULL,"+
                                              "`notes` varchar(255) collate utf8_unicode_ci default NULL,"+
                                              "PRIMARY KEY  (`EntryID`)"+
                                              ") ENGINE=MyISAM";
    //private Statement stmt;
    private Connection con;
    public SqlFunc(Connection theCon) throws SQLException{
        this.con=theCon;
        //stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
        //    ResultSet.CONCUR_UPDATABLE);
    }

    public ResultSet searchbyPhone(String phone, boolean exact) throws SQLException{
        Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        if(!exact) return stmt.executeQuery(
            "select * from customer where phone LIKE \'%" + phone + "%\'");
        return stmt.executeQuery(
            "select * from customer where phone = \'" + phone + "\'");
    }

    public ResultSet searchbyName(String name, boolean exact) throws SQLException{
        Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        if(!exact) return stmt.executeQuery(
            "select * from customer where name LIKE \'%" + name + "%\'");
        return stmt.executeQuery(
            "select * from customer where name = \'" + name + "\'");
    }

    public ResultSet searchbyAddress(String address, boolean exact) throws SQLException{
        Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        if(!exact) return stmt.executeQuery(
            "select * from customer where address LIKE \'%" + address + "%\'");
        return stmt.executeQuery(
            "select * from customer where address = \'" + address + "\'");
    }

    public ResultSet searchbyID(int id) throws SQLException{
        Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        return stmt.executeQuery(
            "select * from customer where customerid = \'" + id + "\'");
    }

    private void helperCreateCustomer(String phone, String name, String address) throws InvalidPhoneException,SQLException{
        Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        //stmt.executeUpdate("insert into customer values(6323, 'Hemanth')");
        if (phone.length()!=PHONE_LENGTH&&phone.length()!=PHONE_LENGTH-AREACODE.length()) {
            throw new InvalidPhoneException("Invalid phone length");
        }else if (phone.length()!=PHONE_LENGTH&&phone.length()==PHONE_LENGTH-AREACODE.length()) {
            phone=AREACODE+phone;
        }
        if (!checkNum(phone)) throw new InvalidPhoneException("Invalid phone format");
        stmt.executeUpdate("INSERT INTO customer (Phone, Name, Address) VALUES ( \'" +
                phone + "\', \'" + name + "\', \'" + address + "\');");
    }

    private void helperEditCustomer(String phone, String name, String address, ResultSet srs) throws SQLException,InvalidPhoneException{
        //srs.next();
        //if (phone!=null && phone.length()!=PHONE_LENGTH || !checkNum(phone)) throw new InvalidPhoneException();
        if (phone!=null) srs.updateString("Phone", phone);
        if (name!=null) srs.updateString("Name", name);
        if (address!=null)srs.updateString("Address", address);
        srs.updateRow();
        System.out.println("row updated");
    }

    public static void DeleteCustomer(ResultSet srs) throws SQLException{
        //stmt.executeUpdate("insert into customer values(6323, 'Hemanth')");
        srs.deleteRow();
    }

    public static boolean checkNum(String num){
        for(int i=0;i<num.length();i++){
            if(num.charAt(i)<'0' || num.charAt(i)>'9') return false;
        }
        return true;
    }
    /*
    public byte checkForDuplicate(String phone, String name, String address) throws SQLException{
        Statement checkstmt=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        if(phone!=null && checkstmt.executeQuery(
            "select * from customer where phone = \'" + phone
            + "\'").next()) return PHONE_EXISTS;
        else if(name!=null && checkstmt.executeQuery(
            "select * from customer where name = \'" + name
            + "\'").next()) return NAME_EXISTS;
        else if(address!=null && checkstmt.executeQuery(
            "select * from customer where address = \'" + address
            + "\'").next()) return ADDRESS_EXISTS;
        return NO_DUPLICATE;
    }
    */
    /*
    public byte checkForOtherDuplicate(String phone, String name, String address,String idSkip) throws SQLException{
        Statement checkstmt=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        if(checkstmt.executeQuery(
            "select * from customer where phone = \'" + phone
            + "\' AND CustomerID != \'"+idSkip+"\'").next()) return PHONE_EXISTS;
        else if(checkstmt.executeQuery(
            "select * from customer where name = \'" + name
            + "\' AND CustomerID != \'"+idSkip+"\'").next()) return NAME_EXISTS;
        else if(checkstmt.executeQuery(
            "select * from customer where address = \'" + address
            + "\' AND CustomerID != \'"+idSkip+"\'").next()) return ADDRESS_EXISTS;
        return NO_DUPLICATE;
    }
    */
    public boolean checkForOtherPhoneDuplicate(String phone,String idSkip) throws SQLException{
        Statement checkstmt=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        if(checkstmt.executeQuery(
            "select * from customer where phone = \'" + phone
            + "\' AND CustomerID != \'"+idSkip+"\'").next()) return PHONE_EXISTS_BOOL;
        return !PHONE_EXISTS_BOOL;
    }

    public String getNextAvailableName() throws SQLException{
        String name="Customer ";
        int number;
        Statement checkstmt=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
        ResultSet.CONCUR_UPDATABLE);
        ResultSet srs=checkstmt.executeQuery("select max(CustomerID) as lastrow from customer");
        srs.next();
        number=srs.getInt(1)+1;
        System.out.println("select * from customer where name = \'"+name+number+"\'");
        while(checkstmt.executeQuery("select * from customer where name = \'"+name+number+"\'").next()){
            number++;
        }
        return name+number;
    }

     public static boolean checkIfPhoneValid(String phone) throws InvalidEntryException{
        if(phone==null) {
            throw new InvalidPhoneException("Phone can't be null");
        }
        else if(phone.length()==0){
            throw new InvalidPhoneException("Phone field can't be empty");
        }else if (phone.length()!=PHONE_LENGTH) {
            throw new InvalidPhoneException("Invalid phone length");
        }else if (!checkNum(phone)) {
            throw new InvalidPhoneException("Phone can only contain numbers");
        }
        return true;
    }

    public static String makePhoneValidIfPossible(String phone) throws InvalidEntryException{
        if(phone==null) {
            throw new InvalidPhoneException("Phone can't be null");
        }
        String toReturn="";
        //String tempPhone="";
            for(int i=0;i<phone.length();i++){
                char tempchar=phone.charAt(i);
                if(tempchar>='0'&&tempchar<='9'){
                    toReturn+=tempchar;
                }
            }
        System.out.println("to return length:"+toReturn.length());
        //String toReturn=phone.trim();
        if (toReturn.length()!=PHONE_LENGTH&&toReturn.length()==PHONE_LENGTH-AREACODE.length()) {
            toReturn=AREACODE+toReturn;
        }
        checkIfPhoneValid(toReturn);
        return toReturn;
    }

    public void createCustomer(String phone, String name, String address) throws SQLException,InvalidEntryException,DuplicateEntryException{
        // TODO add your handling code here:
        checkIfPhoneValid(phone);
        /*
        if(name==null||name.length()==0) {
            throw new InvalidNameException("Name cannot be null or empty string");
        }
        */
        if(searchbyPhone(phone,EXACT_MATCH).next()){
            throw new DuplicateEntryException("Phone Already Exists");
        }
        //ignore duplicate name and addresses
        /*
        byte duplicateLevel;
        duplicateLevel=checkForDuplicate(phone,name,address);
        if(duplicateLevel==PHONE_EXISTS){
            throw new DuplicateEntryException("Phone Already Exists");
        }
        else if(duplicateLevel==NAME_EXISTS){
            //throw new DuplicateEntryException("Name Already Exists");
        }
        else if(duplicateLevel==ADDRESS_EXISTS){
            //throw new DuplicateEntryException("Address Already Exists");
        }
        */
            helperCreateCustomer(phone, name, address);
    }
    /*
        public void editCustomer(String phone, String name, String address, ResultSet srs, int currentCusID) throws SQLException,InvalidEntryException,DuplicateEntryException{
            editCustomer(phone, name, address, srs, Integer.currentCusID+"");
        }
    */
    public void editCustomer(String phone, String name, String address, ResultSet srs, String currentCusID) throws SQLException,InvalidEntryException,DuplicateEntryException{
        checkIfPhoneValid(phone);
        /*
        if(name.length()==0) {
            throw new InvalidNameException("Name cannot be empty");
        }
        */
        if(checkForOtherPhoneDuplicate(phone,currentCusID)) {
            throw new DuplicateEntryException("Phone Already Exists for others");
        }
        //ignore duplicate name and addresses
        /*
        byte duplicateLevel;
        duplicateLevel=checkForOtherDuplicate(phone,name,address,currentCusID);
        if(duplicateLevel==PHONE_EXISTS){
            throw new DuplicateEntryException("Phone Already Exists for others");
        }

        else if(duplicateLevel==NAME_EXISTS){
            //throw new DuplicateEntryException("Name Already Exists");
        }
        else if(duplicateLevel==ADDRESS_EXISTS){
            //throw new DuplicateEntryException("Address Already Exists");
        }
        */
        helperEditCustomer(phone, name, address, srs);
    }
    
    public String getMD5sum(String input){
        String toReturn=null;
        try{
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(input.getBytes());
            byte messageDigest[] = algorithm.digest();
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex=Integer.toHexString(0xff & messageDigest[i]);
                if(hex.length()==1) sb.append('0');
                sb.append(hex);
            }
            toReturn=sb.toString();
            System.out.println("md5sum:"+toReturn);
        } catch (NoSuchAlgorithmException e){

        }
        return toReturn;
    }
    /*
    public byte settle(String pass) throws SQLException{
        final String rightpass;
        //System.out.println(getMD5sum(pass));
        Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_READ_ONLY);
        ResultSet passrs=stmt.executeQuery("select passvalue from pass where passtype='settle'");
        passrs.next();
        rightpass=passrs.getString(1);
        //System.out.println("right pass:"+rightpass);
        //System.out.println("equals?"+rightpass.equals(getMD5sum(pass)));
        if(!rightpass.equals(getMD5sum(pass))){
            return WRONG_PASS;
        }
        ResultSet srs=stmt.executeQuery("select max(orderID) as lastrow from customerorder");
        srs.next();
        int lastid=srs.getInt(1);
        
        ResultSet srslastsettle=stmt.executeQuery("select endorder from shifts order by shiftnumber desc limit 1");
        
        if(lastid==0||(srslastsettle.next()&&lastid==srslastsettle.getInt(1))){
            return NOTHING_SETTLE;
        } else {
            stmt.executeUpdate("insert into shifts (endorder) values ("+ lastid +");");
            return 0;
        }
        
        //stmt.executeUpdate("alter table pos.customerorder rename posarchive.customerorder20080723");
        //stmt.executeUpdate("alter table pos.orderdetail rename posarchive.orderdetail20080723");
        //stmt.executeUpdate(AREACODE)
    }
*/
    
}

class InvalidEntryException extends Exception {
    public InvalidEntryException(){
        super();
    }

    public InvalidEntryException(String message){
        super(message);
    }
}

class InvalidPhoneException extends InvalidEntryException {
    public InvalidPhoneException(){
        super();
    }

    public InvalidPhoneException(String message){
        super(message);
    }
}

class InvalidNameException extends InvalidEntryException {
    public InvalidNameException(){
        super();
    }

    public InvalidNameException(String message){
        super(message);
    }
}

class DuplicateEntryException extends Exception {
    public DuplicateEntryException(){
        super();
    }

    public DuplicateEntryException(String message){
        super(message);
    }
}

