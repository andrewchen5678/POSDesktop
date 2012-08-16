/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ordering;
import java.sql.*;
import java.util.*;
/**
 *
 * @author angelchen
 */
public class ResMenu {
    private Statement stmt;
    private Connection con;
    private int lastEntryNum;
    private MenuItem items[];
    private MenuItem multipleitems[][];
    //private MenuItem sortedItems[];
    private Condiment[] extraNoLittleCond;
    private Condiment[] subCond;
    public ResMenu(Connection theCon) throws Exception{
        this.con=theCon;
        stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_READ_ONLY);
        //select the last instead of count just in case something in the middle are deleted
        ResultSet srs=stmt.executeQuery("select max(MenuEntryID) as lastrow from menu");
        srs.next();
        lastEntryNum=srs.getInt(1);
        srs = stmt.executeQuery("SELECT * FROM menu");
        System.out.println("Entry count = " + lastEntryNum);
        items = new MenuItem[lastEntryNum];
        multipleitems=new MenuItem[lastEntryNum][];
        while(srs.next()){
            int j=srs.getInt("MenuEntryID")-1;
            boolean lunchSpecial=srs.getBoolean("LunchSpecial");
        Statement multiplestmt=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_READ_ONLY);
        ResultSet multiplecountrs=multiplestmt.executeQuery("select count(*) from multipleitem where MenuEntryID="+(j+1));
            multiplecountrs.next();
            int multiplecount=multiplecountrs.getInt(1);
            if(multiplecount>0){
                ResultSet multiplesrs=multiplestmt.executeQuery("select * from multipleitem where MenuEntryID="+(j+1));
                multipleitems[j]=new MenuItem[multiplecount];
                multiplesrs.next();
                System.out.println("multiplecount:"+multiplecount);
                for(int k=0;k<multiplecount;k++){
                    multipleitems[j][k]=new MenuItem(multiplesrs.getString("MenuEntryName"),
                               multiplesrs.getString("MenuEntryNameCN"),
                               multiplesrs.getBigDecimal("Price"),
                               lunchSpecial);
                    multiplesrs.next();
                }
            }
            items[j]= new MenuItem(srs.getString("MenuEntryName"),
                               srs.getString("MenuEntryNameCN"),
                               srs.getBigDecimal("Price"),
                               lunchSpecial);            
        }
        
        //initSortedItems();
        
       //printArray();
        
        //printMultiple();
        initCondiments();
    }
/*    
    private void initSortedItems(){
        int tempMulti=0;
        for(int i=0;i<multipleitems.length;i++){
            if(multipleitems[i]==null) continue;
            //System.out.println("multipleitems index:"+i);
            for(int j=0;j<multipleitems[i].length;j++){
                tempMulti++;
            }
        }
        MenuItem[] tempArr=new MenuItem[lastEntryNum+tempMulti];
        int itemCount=0;
        for(int i=0;i<lastEntryNum;i++){
            if(items[i]==null) continue;
            tempArr[itemCount++]=items[i];
        }
        for(int i=0;i<multipleitems.length;i++){
            if(multipleitems[i]==null) continue;
            for(int j=0;j<multipleitems[i].length;j++){
                tempArr[itemCount++]=multipleitems[i][j];
            }
        }
        sortedItems=new MenuItem[itemCount];
        for(int i=0;i<itemCount;i++){
            sortedItems[i]=tempArr[i];
        }
        improvedBubbleSort(sortedItems);
        System.out.println("itemcount:"+itemCount);
        for(int i=0;i<itemCount;i++){
            System.out.println(sortedItems[i]);
        }        
        
    }
    
    public static void improvedBubbleSort(MenuItem[] items) {
       int k = 0;
       boolean exchangeMade = true;
       // Make up to items.length - 1 passes through array, exit early if 
       // no exchanges are made on previous pass
       while (exchangeMade && k < items.length - 1) {
          exchangeMade = false;
          // Number of comparisons on kth pass
          for (int j = 0; j < items.length - (k+1); j++)
             if (items[j].getName().compareToIgnoreCase(items[j + 1].getName())>0) {
                 MenuItem temp=items[j];
                 items[j]=items[j+1];
                 items[j+1]=temp;
                //swap(items, j, j + 1); 
                exchangeMade = true;
              }
           k++;
       }
    }
*/    
    private void initCondiments(){
        LinkedList<Condiment> tempex=new LinkedList<Condiment>();
        LinkedList<Condiment> tempsub=new LinkedList<Condiment>();

        try{
        stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            ResultSet.CONCUR_UPDATABLE);
        //extraNoLittleCond=new Condiment[extraNoLittleCondCount];
        ResultSet rs=stmt.executeQuery("select * from condiment order by condName");
        while(rs.next()){ 
            if(rs.getBoolean("extranolittle")){
                System.out.println("adding for extranolittle");
                tempex.add(new Condiment(Condiment.TYPE_EXTRANOLITTLE,rs.getString("condName"),rs.getString("condNameCN"),rs.getBigDecimal("extraPrice")));
                //ExtraNoLittlePanel.add(new JButton(rs.getString("condName")));
            }
            if(rs.getBoolean("sub")){
                System.out.println("adding for sub");
                tempsub.add(new Condiment(Condiment.TYPE_SUBSTITUTE,rs.getString("condName"),rs.getString("condNameCN"),rs.getBigDecimal("subPrice")));
                //SubPanel.add(new JButton(rs.getString("condName")));
            }
        }
        } catch(SQLException e){
            e.printStackTrace();
        }
        extraNoLittleCond=new Condiment[tempex.size()];
        System.out.println("working extranolittlelist:");
        System.out.println("extranolittlelist size:"+extraNoLittleCond.length);
        int i=0;
        for(Iterator<Condiment> tempexIter=tempex.iterator(); tempexIter.hasNext();i++) {
            extraNoLittleCond[i]=tempexIter.next();
        }
        subCond=new Condiment[tempsub.size()];
        System.out.println("working substlist:");
        System.out.println("substlist size:"+subCond.length);
        i=0;
        for(Iterator<Condiment> tempsubIter=tempsub.iterator(); tempsubIter.hasNext();i++) {
            subCond[i]=tempsubIter.next();
        }
    }
    
    //from 1 to array size
    public MenuItem getItem(int index){
        if(index<0 || index>=lastEntryNum) return null;
        return items[index];
    }
/*    
    public final MenuItem[] getSortedItemArray(){
        return sortedItems;
    }
*/    
    public MenuItem[] getMultipleItem(int index){
        if(index<0 || index>=lastEntryNum) return null;
        return multipleitems[index];
    }
    
    public MenuItem[] getItemArray(){
        return items;
    }
    
    public int getTotalNumItem(){
        return lastEntryNum;
    }

    public Condiment[] getExtraNoLittleCond() {
        return extraNoLittleCond;
    }

    public Condiment[] getSubCond() {
        return subCond;
    }
    
    public void printMultiple(){
            for(int i=0;i<multipleitems.length;i++){
                System.out.println(i+1);
                if(multipleitems[i]!=null){
                for(int j=0;j<multipleitems[i].length;j++){
                System.out.println("item " + (j+1) + ": " + multipleitems[i][j].getName());
                System.out.println(" " + multipleitems[i][j].getNameCN());
                System.out.println(" " + multipleitems[i][j].getPrice());
                System.out.println();
                    }
                }
            }        
    }
    
    /*
    public void printArray(){
            for(int i=0;i<items.length;i++){
                System.out.println("item " + (i+1) + ": " + items[i].getName());
                System.out.println(" " + items[i].getNameCN());
                System.out.println(" " + items[i].getPrice());
                System.out.println();
            }
    }
    */
    public String toString(){
        String temp="";
            for(int i=0;i<items.length;i++){
                temp+="item " + (i+1) + ": " + items[i].getName()+"\n";
                temp+=" " + items[i].getNameCN()+"\n";
                temp+=" " + items[i].getPrice()+"\n";
                temp+=" lunch special?" + items[i].isLunchSpecial()+"\n";
            }
        return temp;
    }
}
