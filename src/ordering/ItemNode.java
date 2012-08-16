/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordering;
import java.util.ArrayList;
import java.math.*;

public class ItemNode {
	private Item item;
        private int quantity;
	private ItemNode previous, next;
	private String notes;
        ArrayList<CondEn> condim;
        BigDecimal condimAmount;
//        BigDecimal condimTax;
	public ItemNode(Item t, int q, ItemNode p, ItemNode n,String theNotes,ArrayList<CondEn> theCondim) throws InvalidQuantityException {
            if(t!=null && q<1) throw new InvalidQuantityException("Quantity cannot be less than 1");
		item = t;
                quantity=q;                
                previous=p;
		next = n;
                notes=theNotes;
                setCondim(theCondim);
	}
        /*
        public ItemNode(Item t, int q, ItemNode p, ItemNode n,String theNotes) throws InvalidQuantityException {
            this(t,q,p,n);
            this.notes=theNotes;
        }
	*/
	public Item getItem() {
		return item;
	}
        
        public int getQuantity() {
		return quantity;
	}
	
        public ItemNode getPrevious() {
		return previous;
	}
                
	public ItemNode getNext() {
		return next;
	}
	
	public void setItem(MenuItem t) {
		item = t;
	}
        
	public void setQuantity(int q) throws InvalidQuantityException {
            if(item!=null && q<1) throw new InvalidQuantityException("Quantity cannot be less than 1");
		quantity = q;
	}
        
	public void setNext(ItemNode n){
		next = n;
	}
        
        public void setPrevious(ItemNode p) {
		previous = p;
	}
        
        public int searchCond(byte type,Condiment c){
            if(condim==null) return -1;
            for(int i=0;i<condim.size();i++){
                if(condim.get(i).equalsExceptQuantity(type,c)){
                    return i;
                }
            }
            return -1;
        }
        
    public String getNotes(){
        return notes;
    }
    
    public void setNotes(String theNotes){
        this.notes=theNotes;
    }
    
    public void setCondim(ArrayList<CondEn> theCondim){
        condim=theCondim;
        condimAmount=new BigDecimal("0");
        if(theCondim==null||theCondim.isEmpty()){
            return;
        }
        /*
        boolean taxed=true;
        if(item instanceof MiscItem){
            taxed=((MiscItem)item).isTaxed();            
        }
         */ 
        //if(taxed){
            for(int i=0;i<theCondim.size();i++){
                CondEn tempcond=theCondim.get(i);
                if(tempcond.getCondType()==CondEn.EXTRA||tempcond.getCondType()==CondEn.SUB){
                    //System.out.println("additional price"+tempcond.getCond().getAdditionalPrice());
                    condimAmount=condimAmount.add(tempcond.getCond().getAdditionalPrice()).multiply(new BigDecimal(tempcond.getQuantity()));
                }
            }
        //}
    }
    
    public BigDecimal getCondimAmount(){
        return condimAmount;
    }

    public BigDecimal getCondimTax(){
        return condimAmount.multiply(Entry.TAX_RATE);
    }
    
    public ArrayList<CondEn> getCondim(){
        return condim;
    }
    
    public boolean contentSame(ItemNode theNode){
        /*
         * 	private Item Item;
        private int quantity;
	private ItemNode previous, next;
	private String notes;
         */ 
        boolean noteNullEmpty=false;
        boolean condimNullEmpty=false;
        if(theNode==null) return false;
        if(item==null&&theNode.item!=null) return false;
        if(notes==null&&theNode.notes==null){
            noteNullEmpty=true;
        }else if(notes==null&&theNode.notes!=null){
            if(theNode.notes.equals(""))
                noteNullEmpty=true;
            else return false;
        }else if(theNode.notes==null&&notes!=null&&notes.equals("")){
            condimNullEmpty=true;
        }
        if(condim==null&&theNode.condim==null){
            condimNullEmpty=true;
        }else if(condim==null&&theNode.condim!=null){
            if(theNode.condim.isEmpty())
                condimNullEmpty=true;
            else return false;
        }else if(theNode.condim==null&&condim!=null&&condim.isEmpty()){
            if(condim.isEmpty())
                condimNullEmpty=true;
            else return false;
        }
        //System.out.println("condnullempty?"+condimNullEmpty);
        //System.out.println("condqeual:0?"+condim.get(0).equals(theNode.condim.get(0)));
        return((item==null||item.equals(theNode.item))
                &&(noteNullEmpty||notes.equals(theNode.notes))
                //&&(condimNullEmpty||(quantity==theNode.quantity&&checkCondimArrEqual(condim,theNode.condim))));
                &&(condimNullEmpty));
    }
    /*
    private boolean checkCondimArrEqual(ArrayList<CondEn> condim1,ArrayList<CondEn> condim2){
        if(condim2==null&&condim1!=null) return false;
        if(condim1.size()!=condim2.size()) return false;
        for(int i=0;i<condim1.size();i++){
            if(!condim1.get(i).equals(condim2.get(i))) return false;
        }
        return true;
    }*/
/*        
        public String toString(){
            return "[" + menuItem + "," + quantity +
        }
 */ 
}
