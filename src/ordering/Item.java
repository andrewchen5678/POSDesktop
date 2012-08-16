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
public abstract class Item{
    protected String itemName;
    protected String itemNameCN;
    protected BigDecimal price;
    
    public Item(String theItemName, String theItemNameCN, BigDecimal thePrice){
        this.itemName=theItemName;
        this.itemNameCN=theItemNameCN;
        this.price=thePrice.setScale(2);
    }
    
    public String getName(){
        return itemName;
    }
    
    public String getNameWithTags(){
        return itemName;
    }

    public String getNameCN(){
        return itemNameCN;
    }
    
    public BigDecimal getPrice(){
        return price;
    }
    
    public boolean equals(MenuItem i){
        return (itemName.equals(i.itemName) && itemNameCN.equals(i.itemNameCN) && price.equals(i.price));
    }
    
    public String toString(){
        return "<"+itemName+":"+itemNameCN+":$"+price+">";
    }
}