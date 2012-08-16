/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package posdesktop;

/**
 *
 * @author angelchen
 */
public class Customer {
    private int cusID;
    private String phone;
    private String name;
    private String address;
    
    public Customer(int theCusID, String thePhone, String theName, String theAddress){
        this.cusID=theCusID;
        this.phone=thePhone;
        this.name=theName;
        this.address=theAddress;
    }
    
    public int getID(){
        return cusID;
    }
    
    public String getPhone(){
        return phone;
    }

    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setPhone(String thePhone){
        this.phone=thePhone;
    }
    
    public void setName(String theName){
        this.name=theName;
    }
    
    public void setAddress(String theAddress){
        this.address=theAddress;
    }
    
    public String toString(){
        return "Phone: "+phone+"\n"
              +"Name: "+name+"\n"
              +"Address: "+address+"\n";
    }
}
