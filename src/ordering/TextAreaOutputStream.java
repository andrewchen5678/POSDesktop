/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ordering;
import java.io.OutputStream;
import java.io.IOException;
import javax.swing.JTextArea;
/**
 *
 * @author angelchen
 */
public class TextAreaOutputStream extends OutputStream {
    private JTextArea textControl;
    
    /**
     * Creates a new instance of TextAreaOutputStream which writes
     * to the specified instance of javax.swing.JTextArea control.
     *
     * @param control   A reference to the javax.swing.JTextArea
     *                  control to which the output must be redirected
     *                  to.
     */
    public TextAreaOutputStream( JTextArea control ) {
        textControl = control;
    }
    
    public void write(byte[] b){
        for(int i=0;i<b.length;i++)
            textControl.append( String.valueOf( ( char )b[i] ) );
    }
    
    public void write(byte[] b, int off, int len) {
        for(int i=off;i<len;i++)
        textControl.append( String.valueOf( ( char )b[i] ) );
    }
    
    /**
     * Writes the specified byte as a character to the
     * javax.swing.JTextArea.
     *
     * @param   b   The byte to be written as character to the
     *              JTextArea.
     */
    public void write( int b ) throws IOException {
        // append the data as characters to the JTextArea control
        textControl.append( String.valueOf( ( char )b ) );
    }  
}