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
public class Condiment {
    public static final byte TYPE_EXTRANOLITTLE=1;
    public static final byte TYPE_SUBSTITUTE=2;
    private byte type;
    private String condName;
    private String condNameCN;
    private BigDecimal additionalPrice;
    /*
    public Condiment(byte theType,String theCondName,String theCondNameCN){
        if(theType!=TYPE_EXTRANOLITTLE && theType!=TYPE_SUBSTITUTE){
            throw new IllegalArgumentException("type can only be EXTRANOLITTLE or SUBSTITUTE");
        }
        type=theType;
        condName=theCondName;
        condNameCN=theCondNameCN;
    }
    */
    public Condiment(byte theType,String theCondName,String theCondNameCN, BigDecimal exPrice){
        if(theType!=TYPE_EXTRANOLITTLE && theType!=TYPE_SUBSTITUTE){
            throw new IllegalArgumentException("type can only be EXTRANOLITTLE or SUBSTITUTE");
        }
        type=theType;
        condName=theCondName;
        condNameCN=theCondNameCN;
        additionalPrice=exPrice;
    }
    
    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }

    public String getCondName() {
        return condName;
    }

    public String getCondNameCN() {
        return condNameCN;
    }

    public byte getType() {
        return type;
    }
    
    public boolean equals(Condiment c){
        if(c==null) return false;
        if(additionalPrice==null&&c.additionalPrice!=null) return false;
        if(condName==null&&c.condName!=null) return false;
        if(condNameCN==null&&c.condNameCN!=null) return false;
        return (type==c.type&&(condName==null||condName.equals(c.condName))
                &&(condNameCN==null||condNameCN.equals(c.condNameCN))
                &&(additionalPrice==null||additionalPrice.equals(c.additionalPrice)));
    }
    
}
