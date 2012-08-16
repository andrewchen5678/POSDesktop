/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ordering;
import posdesktop.*;
import java.math.*;
import java.io.*;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author angelchen
 */
public class Entry {
    //private Customer customer;
    public static final BigDecimal TAX_RATE=new BigDecimal("0.0825");
    public static final String RESTAURANT_NAME="Golden Phoenix Restaurant";
    public static final String RESTAURANT_PHONE="562-423-8980";
    public static final String RESTAURANT_ADDR1="4819 Paramount Blvd.";
    public static final String RESTAURANT_ADDR2="Lakewood, CA 90712";
    public static final int HERE=0;
    public static final int PICKUP=1;
    public static final int DELIVERY=2;
    public static final String orderTypeStr[]={"Here","Pick up","Delivery"};
    public static final String orderTypeStrCN[]={"堂","取","外"};
    public static final String FORMAT_SPACE_INDENT="   ";
    protected Customer theCustomer;
        protected Date dateTime;
    	protected ItemNode head; // head node
        protected ItemNode tail; // head tail
	protected int size; // size of the node
        protected BigDecimal totalB4Tax;
        protected BigDecimal tax;
        protected Connection con;
        protected int orderID;
        protected int orderType;
        protected CardInfo card;
        
    public Entry(Customer theCustomer,Connection theCon) throws InvalidQuantityException{
        helperSetup(theCustomer);
        if(theCon==null){
            System.err.println("con is null");
            orderID=-1;
            return;
        }
        this.con=theCon;
        orderID=getNextOrderIDSql();
        //orderID=getOrderIDFromFileIncr();
    }
 
    public Entry(Customer theCustomer,Connection theCon, int theOrderID) throws InvalidQuantityException{
        helperSetup(theCustomer);
        this.con=theCon;
        this.orderID=theOrderID;
    }
    
    private void helperSetup(Customer customerParm)throws InvalidQuantityException{
        this.theCustomer=customerParm;
        head = new ItemNode(null,0,null,null,null,null); // dummy head
        tail = new ItemNode(null,0,head,null,null,null); // dummy tail
        head.setNext(tail);
	size = 0;
        totalB4Tax=new BigDecimal("0");
        tax=new BigDecimal("0");
        orderType=HERE;
        dateTime= new java.util.Date();
    }
/*    
    private boolean reserveRow(Statement stmt) throws SQLException{
        if(con==null) return false;
        
        ResultSet rs=stmt.executeQuery("SELECT * FROM customerorder WHERE OrderID=\'"+orderID+"\'");
        if(rs.next()){
            return false;
        }
        stmt.executeUpdate("INSERT INTO customerorder (orderID) VALUES ( \'" + 
            orderID + "\');");
        return true;
    }
*/
    
    public void setCard(CardInfo theCard) {
        card = theCard;
    }
    
    private int getNextOrderIDSql(){
        String INSERT_RECORD = "insert into customerorder (Time) VALUES(?);";
        try{
            PreparedStatement stmt = con.prepareStatement(INSERT_RECORD,Statement.RETURN_GENERATED_KEYS);
            //java.sql.Timestamp  sqlDate = new java.sql.Timestamp(dateTime.getTime());
    //        Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
    //            ResultSet.CONCUR_UPDATABLE);
    //        stmt.executeUpdate("insert into customerorder () VALUES();",Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, new Timestamp(dateTime.getTime()));
            stmt.executeUpdate();
            ResultSet srs=stmt.getGeneratedKeys();
            if(srs.next()){
                int returnID=srs.getInt(1);
                System.out.println("orderID gotten from sql:"+returnID);
                return returnID;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
        //orderID=srs.getInt(1)+1;
        //reserveRow(stmt);
    }

    /*
    private int getOrderIDFromFileIncr(){
        File idFile=new File(System.getProperty("user.home"), "orderID.txt");
//            fw.write("2");
            int value;
            Scanner sc=null;
            try{
                sc=new Scanner(idFile);
                value=sc.nextInt();
                sc.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
                value=1;
            }catch(Exception e){
                e.printStackTrace();
                //value=-1;
                return -1;
            }
            if(value>=Integer.MAX_VALUE){
                value=1;
            }
            FileWriter fw=null;
            try{
                fw=new FileWriter(idFile);
                fw.write((value+1)+"");
                fw.close();
            }catch(IOException e){
                e.printStackTrace();
                return -1;
            }
            return value;
    }
*/    

     
/*    
 * 
    public Entry(Customer theCustomer, Connection theCon) throws InvalidQuantityException{
        this(theCustomer);
        this.con=theCon;
    }
*/
    public Date getDateTime(){
        return dateTime;
    }
    
    public boolean saveToDatabase() throws SQLException{
        if(con==null) return false;
        if(orderID<1) return false;
        Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=stmt.executeQuery("SELECT * FROM customerorder WHERE OrderID=\'"+orderID+"\'");
        rs.next();
        if(theCustomer!=null) {
            rs.updateString("CustomerName", theCustomer.getName());
            rs.updateString("Phone", theCustomer.getPhone());
            rs.updateString("Address", theCustomer.getAddress());
        }
        rs.updateInt("OrderType", orderType+1);
        rs.updateBigDecimal("Subtotal", totalB4Tax);
        rs.updateBigDecimal("Tax", tax);
        rs.updateBigDecimal("Total", getTotalAfterTax());
        rs.updateTimestamp("Time", new java.sql.Timestamp(dateTime.getTime()));
        rs.updateRow();
        /*
        for(ItemNode cur=head.getNext();cur!=tail;cur=cur.getNext()){
            if(cur.getItem() instanceof MiscItem){
                boolean taxed=((MiscItem)(cur.getItem())).isTaxed();
                stmt.executeUpdate("INSERT INTO orderdetail (OrderID, ItemQuantity, ItemName, ItemPrice, MiscItem,notes) VALUES ( \'" + 
                    orderID + "\', \'" + cur.getQuantity() + "\', \'" + cur.getItem().getNameWithTags() + "\', \'" +cur.getItem().getPrice() + "\', \'" +
                    (taxed?"1":"2")+ "\', \'" + cur.getNotes() + "\');"
                    );
            } else{
                stmt.executeUpdate("INSERT INTO orderdetail (OrderID, ItemQuantity, ItemName, ItemPrice, notes) VALUES ( \'" + 
                    orderID + "\', \'" + cur.getQuantity() + "\', \'" + cur.getItem().getNameWithTags() + "\', \'" +cur.getItem().getPrice() + "\', \'" + cur.getNotes() + "\');"
                    );
            }
        }
        */
        return true;
   }

        public ItemNode addLast(Item i, int quantity) throws InvalidQuantityException {
            return addLast(i,quantity,null,null);
	}
        
        public ItemNode addLast(Item i, int quantity, String notes,ArrayList<CondEn> cond) throws InvalidQuantityException {
		if (i==null || quantity<1) return null;
                ItemNode temp=tail.getPrevious();
		ItemNode toBeAdded = new ItemNode(i,quantity, temp, tail,notes,cond);
                if(notes!=null) toBeAdded.setNotes(notes);
		temp.setNext(toBeAdded);
                tail.setPrevious(toBeAdded);
		size++;
                BigDecimal amount=i.getPrice().multiply(new BigDecimal(quantity));
                totalB4Tax=totalB4Tax.add(amount);
                if((i instanceof MiscItem)&&!((MiscItem)i).isTaxed()){
                    return toBeAdded;
                }
                tax=tax.add(amount.multiply(TAX_RATE));
		return toBeAdded;            
        }

        public ItemNode getNode(int index){
            ItemNode cur=head.getNext();
            if(cur==tail) return null;
            for(int i=0;i<index;i++){
                cur=cur.getNext();
            }
            return cur;
        }
        
        public boolean removeFirst() {
            ItemNode temp=head.getNext();
            if(temp==tail) return false;
                head.setNext(temp.getNext());
                temp.getNext().setPrevious(head);
		size--;
                BigDecimal amount=temp.getItem().getPrice().multiply(new BigDecimal(temp.getQuantity()));
                totalB4Tax=totalB4Tax.subtract(amount);
                if((temp.getItem() instanceof MiscItem)&&!((MiscItem)temp.getItem()).isTaxed()){
                    return true;
                }
                tax=tax.subtract(amount.multiply(TAX_RATE));
		return true;
	}
	
        public boolean removeLast() {
            ItemNode temp=tail.getPrevious();
            if(temp==head) return false;
                tail.setPrevious(temp.getPrevious());
                temp.getPrevious().setNext(tail);
		size--;
                BigDecimal amount=temp.getItem().getPrice().multiply(new BigDecimal(temp.getQuantity()));
                totalB4Tax=totalB4Tax.subtract(amount);
                if((temp.getItem() instanceof MiscItem)&&!((MiscItem)temp.getItem()).isTaxed()){
                    return true;
                }
                tax=tax.subtract(amount.multiply(TAX_RATE));
		return true;
	}

        //index from 1 to size
        public boolean removeItemFromFirst(int index) {
            if(index>size || index<1) return false;
                ItemNode temp=head.getNext();
                if(temp==tail) return false;
                for(int i=1;i<index;temp=temp.getNext(),i++){}
                return helperRemove(temp);
	}
        
        private boolean helperRemove(ItemNode temp){
                //for(int i=1;i<index;temp=temp.getPrevious(),i++){}
                //remove temp
                temp.getNext().setPrevious(temp.getPrevious());
                temp.getPrevious().setNext(temp.getNext());
		size--;
                BigDecimal amount=temp.getItem().getPrice().multiply(new BigDecimal(temp.getQuantity()));
                totalB4Tax=totalB4Tax.subtract(amount);
                if((temp.getItem() instanceof MiscItem)&&!((MiscItem)temp.getItem()).isTaxed()){
                    return true;
                }
                tax=tax.subtract(amount.multiply(TAX_RATE));
		return true;
        }
/*        
        public boolean voidLast() throws InvalidQuantityException{
            Node temp=tail.getPrevious();
            if(temp==head) return false;
            if(temp.getQuantity()<=1) return removeLast();
            temp.setQuantity(temp.getQuantity()-1);
            totalB4Tax=totalB4Tax.subtract(temp.getItem().getPrice());
            return true;
        }
        
        public boolean voidFirst() throws InvalidQuantityException{
            Node temp=head.getNext();
            if(temp==tail) return false;
            if(temp.getQuantity()<=1) return removeFirst();
            temp.setQuantity(temp.getQuantity()-1);
            totalB4Tax=totalB4Tax.subtract(temp.getItem().getPrice());
            return true;
        }
*/        
        /*
        public boolean voidItemFromLast(int index) throws InvalidQuantityException{
            if(index>size || index<1) return false;
            Node temp=tail.getPrevious();
            if(temp==head) return false;
            for(int i=1;i<index;temp=temp.getPrevious(),i++){}
            if(temp.getQuantity()<=1) return helperRemove(temp);
            temp.setQuantity(temp.getQuantity()-1);
            totalB4Tax=totalB4Tax.subtract(temp.getItem().getPrice());
            return true;
        }

        public boolean voidItemFromFirst(int index) throws InvalidQuantityException{
            if(index>size || index<1) return false;
            Node temp=head.getNext();
            if(temp==tail) return false;
            for(int i=1;i<index;temp=temp.getNext(),i++){}
            if(temp.getQuantity()<=1) return helperRemove(temp);
            temp.setQuantity(temp.getQuantity()-1);
            totalB4Tax=totalB4Tax.subtract(temp.getItem().getPrice());
            return true;
        }
        */        
//	return the size of the list 
	public int getSize() { return size; }
        
        public BigDecimal getTotalB4Tax(){ 
            BigDecimal temp=new BigDecimal("0");
            ItemNode cur=head.getNext();
            while(cur!=tail){
                if(cur.getCondim()!=null){
                    temp=temp.add(cur.getCondimAmount());
                }
                cur=cur.getNext();
            }
            return totalB4Tax.add(temp).setScale(2); 
        }
        
        public BigDecimal getTax(){ //return totalB4Tax.multiply(TAX_RATE).setScale(2,RoundingMode.HALF_UP); 
            BigDecimal temp=new BigDecimal("0");
            ItemNode cur=head.getNext();
            while(cur!=tail){
                boolean taxed=true;
                Item tempItem=cur.getItem();
                if(tempItem instanceof MiscItem){
                    taxed=((MiscItem)tempItem).isTaxed();            
                }                
                if(taxed&&cur.getCondim()!=null){
                    temp=temp.add(cur.getCondimTax());
                }
                cur=cur.getNext();
            }            
            return tax.add(temp).setScale(2,RoundingMode.HALF_UP);
        }
        
        protected BigDecimal getTotalAfterTax(){ 
            return getTotalB4Tax().add(getTax()).setScale(2); 
        }
        
        public void printEntry(PrintStream ps) {
            ps.print(getEntryStr());
            /*
            Node cur=head.getNext();
            for(int i=1;cur!=tail;i++,cur=cur.getNext()){
                int quantity=cur.getQuantity();
                Item curItem=cur.getItem();
                ps.print(i + ". " +quantity+" "+curItem.getName()
                        + " @$" +curItem.getPrice()+ " each ");
                ps.println("= $"+curItem.getPrice().multiply(new BigDecimal(quantity)));
                if(cur.getNotes()!=null){
                    ps.println(stringFormatter("Notes:"+cur.getNotes(),FORMAT_SPACE_INDENT));
                }
                ps.println("     "+curItem.getNameCN());
            }
            ps.println();
            //BigDecimal tax=getTax();
            ps.println("Subtotal: $"+getTotalB4Tax());
            ps.println("Tax: $"+getTax());
            ps.println("Total: "+getTotalAfterTax());
             */ 
        }
        
        public void printReceipt(PrintStream ps){
            ps.print(getReceiptStr(true));
            /*
            ps.println(RESTAURANT_NAME);
            ps.println(RESTAURANT_PHONE);
            ps.println(RESTAURANT_ADDR1);
            ps.println(RESTAURANT_ADDR2);
            ps.println();
            ps.println("Order Type: "+orderTypeStr[orderType]);
            if(orderID>0) ps.println("Order Number: "+orderID);
            if(theCustomer!=null){
                ps.println("Customer Name: "+theCustomer.getName());
                ps.println("Customer Phone: "+theCustomer.getPhone());
                if(orderType==DELIVERY) ps.println("Customer Address:"+theCustomer.getAddress());
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss aa");
            java.util.Date date = new java.util.Date();
            String datetime = dateFormat.format(date);
            ps.println("Time ordered: " + datetime);
            printEntry(ps);
             */ 
        }

        public static String formatEntryForList(ItemNode node){
            String formatted=node.getQuantity()+" "+node.getItem().getNameWithTags()+"("+node.getItem().getNameCN()+")"
                                + " @$" +node.getItem().getPrice()+ " each \n"
                                + " = $"+node.getItem().getPrice().multiply(new BigDecimal(node.getQuantity()));
            ArrayList<CondEn> tempconden=node.getCondim();
            if(tempconden!=null){
                for(int i=0;i<tempconden.size();i++){
                    CondEn temp2=tempconden.get(i);
                    formatted+='\n'+Entry.stringFormatter(temp2.toString(),Entry.FORMAT_SPACE_INDENT);
                }
            }
            if(node.getNotes()!=null){
                formatted+='\n'+Entry.stringFormatter("Notes:"+node.getNotes(),Entry.FORMAT_SPACE_INDENT);
            }
            return formatted;
        }        
        
        public String getEntryStr() {
            String temp="";
            ItemNode cur=head.getNext();
            for(int i=1;cur!=tail;i++,cur=cur.getNext()){
                /*int quantity=cur.getQuantity();
                Item curItem=cur.getItem();
                
                temp+= i + ". " +quantity+" "+curItem.getNameWithTags()+" @$" +curItem.getPrice()+ " each ";
                temp+= "= $"+curItem.getPrice().multiply(new BigDecimal(quantity))+"\n";
                if(cur.getNotes()!=null){
                    temp+=stringFormatter("Notes:"+cur.getNotes(),FORMAT_SPACE_INDENT)+"\n";
                }
                temp+="     "+curItem.getNameCN()+"\n";
                 */
                temp+=formatEntryForList(cur)+"\n";
            }
            temp+="\n";
            //BigDecimal tax=getTax();
            temp+="Subtotal: $"+getTotalB4Tax()+"\n";
            temp+="Tax: $"+getTax()+"\n";
            temp+="Total: "+getTotalAfterTax()+"\n";
            return temp;
        }
        
        public String getReceiptStr(boolean withHeading){
            String temp="";
            if(withHeading){
                temp+=RESTAURANT_NAME+"\n";
                temp+=RESTAURANT_PHONE+"\n";
                temp+=RESTAURANT_ADDR1+"\n";
                temp+=RESTAURANT_ADDR2+"\n";
                temp+="\n";
            }
            temp+="Order Type: "+orderTypeStr[orderType]+"\n";
            if(orderID>0) temp+="Order Number: "+orderID+"\n";
            if(theCustomer!=null){
                temp+="Customer Name: "+theCustomer.getName()+"\n";
                temp+="Customer Phone: "+theCustomer.getPhone()+"\n";
                if(orderType==DELIVERY) temp+="Customer Address:"+theCustomer.getAddress()+"\n";
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
            String datetime = dateFormat.format(dateTime);
            temp+="Time ordered: " + datetime+"\n\n";
            if(card!=null){
                temp+="Card number:"+card.getCardNumber()+"\n";
                temp+="Exp Date:"+card.getExpDate()+"\n\n";
            }
            return temp+getEntryStr();
        }
        
        
        public POSPtrEntry getFinalEntry() throws InvalidQuantityException{
            POSPtrEntry finalEn=new POSPtrEntry(theCustomer,MainProgram.getSqlConnection(),orderID);
            for(ItemNode cur=head.getNext();cur!=tail;cur=cur.getNext()){
/*
                ArrayList<CondEn> curCond=cur.getCondim();
                if(curCond!=null&&!curCond.isEmpty()){
                    for(int i=0;i<curCond.size();i++){
                        if(curCond.get(i).equalsExceptQuantity(c))
                    }
                }
 */ 
                ItemNode existing=finalEn.search(cur);
                if(existing!=null){
                    existing.setQuantity(cur.getQuantity()+existing.getQuantity());
                    /*
                    ArrayList<CondEn> tempCond=existing.getCondim();
                    if(tempCond!=null) {
                        for(int m=0;m<tempCond.size();m++){
                            tempCond.get(m).setQuantity(tempCond.get(m).getQuantity()*2);
                        }
                    }*/
                    continue;
                } else {
                    ItemNode temp=finalEn.tail.getPrevious();
                    ItemNode toBeAdded= new ItemNode(cur.getItem(),cur.getQuantity(), temp, finalEn.tail,cur.getNotes(),cur.getCondim());
                    /*
                    if(cur.getNotes()==null){
                        toBeAdded = new ItemNode(cur.getItem(),cur.getQuantity(), temp, finalEn.tail);
                    } else {
                        toBeAdded = new ItemNode(cur.getItem(),cur.getQuantity(), temp, finalEn.tail,cur.getNotes());
                    }
                     */ 
                    temp.setNext(toBeAdded);
                    finalEn.tail.setPrevious(toBeAdded);
                    finalEn.size++;
                }
            }
            //finalEn.orderID=orderID;
            finalEn.orderType=orderType;
            finalEn.tax=tax;
            finalEn.totalB4Tax=totalB4Tax;
            return finalEn;
        }
        
        public ItemNode search(ItemNode node){
            for(ItemNode cur=head.getNext();cur!=tail;cur=cur.getNext()){
                //else if()
                //if(cur.getItem().equals(i) && (cur.getNotes()==null && notes==null)) return cur;
                if(cur.contentSame(node)) return cur;
            }
            return null;
        }

        public Customer getCustomer(){
            return theCustomer;
        }
        
        public void setCustomer(Customer customerParm){
            this.theCustomer=customerParm;
        }
        
    public static String stringFormatter(String input, String add){
        if (input==null) return null;
        if (input.length()==0) return "";
        String output="";
        int strpos=0;
        int newstrpos;
        while((newstrpos=input.indexOf('\n', strpos))>=0){
            output+=add+input.substring(strpos,newstrpos+1);
            strpos=newstrpos+1;
        }
        return output+add+input.substring(strpos,input.length());
    }
    
    public int getOrderType(){
        return orderType;
    }
    
    public void setOrderType(int theType){
        this.orderType=theType;
    }
    
    public int getOrderID(){
        return orderID;
    }
/*
    public void setOrderID(int theId){
        this.orderID=theId;
    }
*/    
        public String toString(){
            ItemNode cur=head;
            String temp="[";
            while(cur!=null){
                temp+="("+cur.getItem()+","+cur.getQuantity()+"),";
                cur=cur.getNext();
            }
            temp+="]\n";
            cur=tail;
            temp+="[";
            while(cur!=null){
                temp+="("+cur.getItem()+","+cur.getQuantity()+"),";
                cur=cur.getPrevious();
            }
            return temp+"]";
        }
 
}
