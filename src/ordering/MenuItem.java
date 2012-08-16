/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ordering;
import java.math.*;

/**
 *
 * @author angelchen
 */
public class MenuItem extends Item{
    private boolean lunchSpecial;
    public MenuItem(String itemName, String itemNameCN, BigDecimal price, boolean theLunchSpecial) throws InvalidPriceException {
        super(itemName,itemNameCN,price);
        if((new BigDecimal("0")).compareTo(price)>0) throw new InvalidPriceException();
        this.lunchSpecial=theLunchSpecial;
    }
    
    public String getName(){
        return itemName;
    }
    
    public String getNameWithTags(){
        if(lunchSpecial) return itemName+"(Lunch Sp)";
        return itemName;
    }
    
    public boolean isLunchSpecial(){
        return lunchSpecial;
    }
    
    public String toString(){
        return "<"+getNameWithTags()+":"+getNameCN()+":$"+getPrice()+">";
    }
        
    public boolean equals(MenuItem i){
        return (itemName.equals(i.itemName) && itemNameCN.equals(i.itemNameCN) && price.equals(i.price) && lunchSpecial==i.lunchSpecial);
    }
}