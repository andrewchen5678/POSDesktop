/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ordering;
import posdesktop.Customer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import jpos.JposException;
import jpos.POSPrinterControl111;
import jpos.POSPrinterConst;
import java.io.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.math.*;
import java.sql.Connection;
import java.util.ArrayList;
/**
 *
 * @author angelchen
 */
public class POSPtrEntry extends Entry{
        
        public POSPtrEntry(Customer theCustomer, Connection con) throws InvalidQuantityException{
            super(theCustomer, con);
        }
        
        public POSPtrEntry(Customer theCustomer, Connection con, int orderID) throws InvalidQuantityException{
            super(theCustomer, con,orderID);
        }
        
        public void printToPrinter(POSPrinterControl111 ptr) throws JposException {
            /*
            helperPrintToPrinter(ptr);
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC\u001b|2C\n\n---Kitchen-Copy---");
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|fP");
             */
            if(orderType!=HERE){
                printOneReceipt(ptr,"---Kitchen-Copy---",true);
            }
            printOneReceipt(ptr,"---Outside-Copy---",false);
            if(orderType==DELIVERY){
                printOneReceipt(ptr,"---Delivery-Copy---",true);
            }
        }
        
        public void printOneReceipt(POSPrinterControl111 ptr, String footer, boolean printCard) throws JposException {
            System.out.println(footer);
            helperPrintToPrinter(ptr,printCard);
            if(footer!=null) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC\u001b|2C\n\n"+footer);
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|fP");
        }
        
        private void helperPrintToPrinter(POSPrinterControl111 ptr, boolean printCardMasked) throws JposException {
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|N" + Entry.RESTAURANT_NAME + "\n");
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|N" + Entry.RESTAURANT_PHONE + "\n");
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|N" + Entry.RESTAURANT_ADDR1 + "\n");
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|N" + Entry.RESTAURANT_ADDR2 + "\n\n");
            //ps.println(RESTAURANT_NAME);
            //ps.println(RESTAURANT_ADDR1);
            //ps.println(RESTAURANT_ADDR2);
            //ps.println();
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|NOrder Type: " + Entry.orderTypeStr[orderType]+"\n");
            printChinese("      "+Entry.orderTypeStrCN[orderType],ptr,42);
            if(orderID>0) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|2COrder Number: "+orderID+"\n");
            if (theCustomer != null) {
                ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|NCustomer Name: " + theCustomer.getName() + "\n");
                if (orderType != Entry.HERE)ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bCCustomer Phone: "+theCustomer.getPhone()+"\n");
                if (orderType == Entry.DELIVERY){
                    ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bCCustomer Address:" + theCustomer.getAddress()+"\n");
                }
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss aa");
            java.util.Date date = new java.util.Date();
            String datetime = dateFormat.format(date);
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|NTime ordered: " + datetime+"\n\n");
            if(card!=null && !printCardMasked){
                ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC\u001b|uCCredit Card#:" + card.getCardNumber()+"\n");
                ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC\u001b|uCExp Date:" + card.getExpDate()+"\n\n");
            }else if(card!=null && printCardMasked){
                ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC\u001b|uCCredit Card#:" + card.getCardNumberMasked()+"\n");
                ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC\u001b|uCExp Date:" + card.getExpDate()+"\n\n");                
            }
            helperPrintEntryPOS(ptr);
            //Feed the receipt to the cutter position automatically, and cut.
            //   ESC|#fP = Line Feed and Paper cut

        }

        private void helperPrintEntryPOS(POSPrinterControl111 ptr) throws JposException {
            ItemNode cur=head.getNext();
            for(int i=1;cur!=tail;i++,cur=cur.getNext()){
                int quantity=cur.getQuantity();
                Item curItem=cur.getItem();
                ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC" +quantity+" "+curItem.getNameWithTags());
                        //+ " @$" +curItem.getPrice()+ " each \n");
//                if((curItem instanceof MenuItem) && ((MenuItem)curItem).isLunchSpecial()){
//                    ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC(L)");
//                }
                ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC= $"+curItem.getPrice().multiply(new BigDecimal(quantity))+"\n");
                if(cur.getNotes()!=null){
                    ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|bC\u001b|uC"+Entry.FORMAT_SPACE_INDENT+"Notes:"+cur.getNotes());
                }
                
                printCond(ptr,cur);
                //printChinese(quantity+" "+curItem.getNameCN(),ptr,48);
//                printAlreadyCond(grp,ptr,true);
                //ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|N     "+curItem.getNameCN()+"\n");
            }
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|N\n");
            //BigDecimal tax=getTax();
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|NSubtotal: $"+getTotalB4Tax()+"\n");
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|NTax: $"+getTax()+"\n");
            ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT, "\u001b|2CTotal: "+getTotalAfterTax()+"\n");
        }

        private void printCond(POSPrinterControl111 ptr,ItemNode n) throws JposException{

            //            ItemNode cur=head.getNext();
            //           for(int i=1;cur!=tail;i++,cur=cur.getNext()){

                //Item curItem=cur.getItem();
                ArrayList<CondEn> tempcond=n.getCondim();
                StringBuffer tempEx=new StringBuffer(Entry.FORMAT_SPACE_INDENT+"Extra:");
                StringBuffer tempNo=new StringBuffer(Entry.FORMAT_SPACE_INDENT+"No:");
                StringBuffer tempLittle=new StringBuffer(Entry.FORMAT_SPACE_INDENT+"Little:");
                StringBuffer tempSub=new StringBuffer(Entry.FORMAT_SPACE_INDENT+"Sub:");                
                StringBuffer tempExCN=new StringBuffer(Entry.FORMAT_SPACE_INDENT+"加:");
                StringBuffer tempNoCN=new StringBuffer(Entry.FORMAT_SPACE_INDENT+"不:");
                StringBuffer tempLittleCN=new StringBuffer(Entry.FORMAT_SPACE_INDENT+"少:");
                StringBuffer tempSubCN=new StringBuffer(Entry.FORMAT_SPACE_INDENT+"跟:");                                
                boolean printEx=false,printNo=false,printLittle=false,printSub=false;
                if(tempcond!=null&&!tempcond.isEmpty()){
                    for(int j=0;j<tempcond.size();j++){
                        CondEn temp=tempcond.get(j);
                        int quantity=temp.getQuantity();
                        switch(temp.getCondType()){
                            case(CondEn.EXTRA):
                                if(quantity>1){
                                    tempEx.append(quantity+" "+temp.getCond().getCondName());
                                    tempExCN.append(quantity+temp.getCond().getCondNameCN());
                                } else {
                                    tempEx.append(temp.getCond().getCondName());
                                    tempExCN.append(temp.getCond().getCondNameCN());
                                }
                                if(temp.getCond().getAdditionalPrice().compareTo(new BigDecimal("0"))!=0){
                                    tempEx.append("($"+temp.getCond().getAdditionalPrice().multiply(new BigDecimal(quantity))+")");
                                }
                                tempEx.append(",");
                                tempExCN.append(",");
                                printEx=true;
                                break;
                            case(CondEn.NO):
                                if(quantity>1){
                                    tempNo.append(quantity+" "+temp.getCond().getCondName());
                                    tempNoCN.append(quantity+temp.getCond().getCondNameCN());
                                } else {
                                    tempNo.append(temp.getCond().getCondName());
                                    tempNoCN.append(temp.getCond().getCondNameCN());
                                }
                                tempNo.append(",");
                                tempNoCN.append(",");
                                printNo=true;
                                break;
                            case(CondEn.LITTLE):
                                //tempLittle.append(temp.getCond().getCondName()+",");
                                if(quantity>1){
                                    tempLittle.append(quantity+" "+temp.getCond().getCondName());
                                    tempLittleCN.append(quantity+temp.getCond().getCondNameCN());
                                } else {
                                    tempLittle.append(temp.getCond().getCondName());
                                    tempLittleCN.append(temp.getCond().getCondNameCN());
                                }
                                tempLittle.append(",");
                                tempLittleCN.append(",");
                                printLittle=true;
                                break;                                    
                            case(CondEn.SUB):
                                if(quantity>1){
                                    tempSub.append(quantity+" "+temp.getCond().getCondName());
                                    tempSubCN.append(quantity+temp.getCond().getCondNameCN());
                                } else {
                                    tempSub.append(temp.getCond().getCondName());
                                    tempSubCN.append(temp.getCond().getCondNameCN());
                                }
                                if(temp.getCond().getAdditionalPrice().compareTo(new BigDecimal("0"))!=0){
                                    tempSub.append("($"+temp.getCond().getAdditionalPrice().multiply(new BigDecimal(quantity))+")");
                                }
                                tempSub.append(",");
                                tempSubCN.append(",");
                                //tempSub.append(temp.getCond().getCondName()+",");
                                printSub=true;
                                break;    
                    }
                   
                  }
                }
                if(printEx) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+tempEx+"\n");
                if(printNo) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+tempNo+"\n");
                if(printLittle) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+tempLittle+"\n");
                if(printSub) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+tempSub+"\n");

                printChinese(n.getQuantity()+" "+n.getItem().getNameCN(),ptr,48);                    
                if(printEx) printChinese(tempExCN.toString(),ptr,36);
                if(printNo) printChinese(tempNoCN.toString(),ptr,36);
                if(printLittle) printChinese(tempLittleCN.toString(),ptr,36);
                if(printSub) printChinese(tempSubCN.toString(),ptr,36);
                /*
                CondGroupCN grp=new CondGroupCN();
                grp.printEx=printEx;
                grp.printNo=printNo;
                grp.printLittle=printLittle;
                grp.printSub=printSub;
                grp.tempExCN=tempExCN;
                grp.tempNoCN=tempNoCN;
                grp.tempLittleCN=tempLittleCN;
                grp.tempSubCN=tempSubCN;
                return grp;
                 */ 
        }    
/*        
        private void printAlreadyCond(CondGroup grp,POSPrinterControl111 ptr,boolean cn) throws JposException{
            if(!cn){    
                if(grp.printEx) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+grp.tempEx+"\n");
                if(grp.printNo) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+grp.tempNo+"\n");
                if(grp.printLittle) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+grp.tempLittle+"\n");
                if(grp.printSub) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+grp.tempSub+"\n");
            }else{
                if(grp.printEx) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+grp.tempExCN+"\n");
                if(grp.printNo) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+grp.tempNoCN+"\n");
                if(grp.printLittle) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+grp.tempLittleCN+"\n");
                if(grp.printSub) ptr.printNormal(POSPrinterConst.PTR_S_RECEIPT,"\u001b|bC\u001b|uC"+grp.tempSubCN+"\n");                
            }
        }
*/        
        
        private void printChinese(String str,POSPrinterControl111 ptr, int size) throws JposException{
                                    BufferedImage img = createTextImage(str, new Font("SimHei", Font.BOLD, size));
ByteArrayOutputStream bas =
new ByteArrayOutputStream();
try{
ImageIO.write(img, "bmp", bas);
}catch(IOException ex){
    ex.printStackTrace();
}
byte[] data = bas.toByteArray();
              ptr.printMemoryBitmap(POSPrinterConst.PTR_S_RECEIPT, data, POSPrinterConst.PTR_BMT_BMP, POSPrinterConst.PTR_BM_ASIS, POSPrinterConst.PTR_BM_LEFT);
        }
        
        public BufferedImage createTextImage(String text, Font font) {
        //You may want to change these setting, or make them parameters
        boolean isAntiAliased = true;
        boolean usesFractionalMetrics = false;
        FontRenderContext frc = new FontRenderContext(null, isAntiAliased, usesFractionalMetrics);
        TextLayout layout = new TextLayout(text, font, frc);
        Rectangle2D bounds = layout.getBounds();
        int w = (int) Math.ceil(bounds.getWidth());
        int h = (int) Math.ceil(bounds.getHeight());
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY); //for example;
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,w,h);
        g.setColor(Color.BLACK);
        g.setFont(font);
        /*
        Object antiAliased = isAntiAliased?
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAliased);
        Object fractionalMetrics = usesFractionalMetrics?
            RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF;
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics);
         */ 
        g.drawString(text, (float) - bounds.getX(), (float) - bounds.getY());
        g.dispose();
        return image;
    }

    private class CondGroupCN{
        public boolean printEx,printNo,printLittle,printSub;
        public StringBuffer tempExCN,tempNoCN,tempLittleCN,tempSubCN;
        public CondGroupCN(){
            
        }
    }
        
        
}


