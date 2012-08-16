/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package posdesktop;
import javax.swing.text.*;
/**
 *
 * @author angelchen
 */
public class FieldDocument extends PlainDocument
{
     private int limit;

     public FieldDocument(int theLimit)
     {
          super();
          this.limit = theLimit;
     }

     public void insertString(int offset, String s, AttributeSet attributeSet)
          throws BadLocationException
     {
          if (limit==0 || getLength() + s.length() <= limit)
          {
  // if we haven't reached the limit, insert the string
               super.insertString(offset, s, attributeSet);
          }
          else
          {
                        // otherwise, just lose the string

               java.awt.Toolkit.getDefaultToolkit().beep();
          }
     
     }
}