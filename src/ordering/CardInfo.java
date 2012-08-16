/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ordering;

/**
 *
 * @author angelchen
 */
public class CardInfo {
    private String cardNumber;
    private String expDate;

    public CardInfo(String theCardNumber, String theExpDate) {
        this.cardNumber = theCardNumber;
        this.expDate = theExpDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardNumberMasked(){
        char[] temp=cardNumber.toCharArray();
        int numCount=0;
        //mask first 12 number
        for(int i=0;i<temp.length&&numCount<12;i++){
            if(Character.isDigit(temp[i])){
                temp[i]='X';
                numCount++;
            }
        }
        return new String(temp);
    }
    
    public String getExpDate() {
        return expDate;
    }
    
}
