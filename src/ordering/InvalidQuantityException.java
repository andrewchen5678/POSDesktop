/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ordering;

/**
 *
 * @author angelchen
 */
public class InvalidQuantityException extends Exception{
        public InvalidQuantityException(){
            super();
        }

        public InvalidQuantityException(String message){
            super(message);
        }
}
