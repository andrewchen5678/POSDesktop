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
public class MiscItem extends Item {
    public static final boolean ITEM_TAXED=true;
    public static final boolean ITEM_NOT_TAXED=false;
    private boolean taxed;
    public MiscItem(BigDecimal price, boolean isTaxed) {
        super(isTaxed?"MISC TAXED":"MISC NONTAXED", "___", price);
        this.taxed=isTaxed;
    }
    
    public MiscItem(String itemName, BigDecimal price, boolean isTaxed) {
        super(itemName+(isTaxed?"(TAXED)":"(NONTAXED)"), "___", price);
        this.taxed=isTaxed;
    }
    
    public boolean isTaxed(){
        return taxed;
    }
    
    public boolean equals(MiscItem i){
        return (itemName.equals(i.itemName) && itemNameCN.equals(i.itemNameCN) && price.equals(i.price)&& taxed==i.taxed);
    }
}
