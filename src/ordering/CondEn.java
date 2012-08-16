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
public class CondEn {
    public static final byte EXTRA=1;
    public static final byte NO=2;
    public static final byte LITTLE=3;
    public static final byte SUB=4;
    private int quantity;
    private byte condType;
    private Condiment cond;

    public CondEn(int theQuantity, byte type,Condiment theCond){
        quantity=theQuantity;
        condType=type;
        cond=theCond;
    }

    public Condiment getCond() {
        return cond;
    }

    public byte getCondType() {
        return condType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public boolean equals(CondEn c){
        if(cond==null&&c.cond!=null) return false;
        return(quantity==c.quantity&&condType==c.condType&&(cond==null||cond.equals(c.cond)));
    }
    
    public boolean equalsExceptQuantity(int type, Condiment condim){
        if(cond==null&&condim!=null) return false;
        return(condType==type&&(cond==null||cond.equals(condim)));        
    }
    
    public String toString(){
        String heading="";
        if(quantity>1){
            heading+=quantity+" ";
        }
        BigDecimal addpri=cond.getAdditionalPrice().multiply(new BigDecimal(quantity));
             switch(condType){
                case CondEn.EXTRA:
                heading+="Extra "+cond.getCondName();
                if(addpri.compareTo(new BigDecimal("0"))!=0){
                        heading+=" $"+addpri;
                }
                return heading;
                case CondEn.NO:
                return heading+="No "+cond.getCondName();
                case CondEn.LITTLE:
                return heading+="Little "+cond.getCondName();
                case CondEn.SUB:
                heading+="Sub "+cond.getCondName();
                if(addpri.compareTo(new BigDecimal("0"))!=0){
                        heading+=" $"+addpri;
                }
                return heading;                
            }
            return null;
    }
}
