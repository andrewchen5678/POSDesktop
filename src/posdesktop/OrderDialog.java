/*
 * NewJDialog.java
 *
 * Created on June 14, 2008, 4:12 PM
 */

package posdesktop;
import java.awt.Component;
import ordering.*;
import javax.swing.*;
import java.awt.CardLayout;
import javax.swing.text.*;
import java.math.BigDecimal;
import jpos.JposException;
import jpos.POSPrinterControl111;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.util.Calendar;
import java.util.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

/**
 *
 * @author  angelchen
 */
public class OrderDialog extends JDialog {
    private Customer cus;
    private ResMenu theMenu;
    private Entry en;
    //private POSPrinterControl111 ptr;
    private static final String PTR_READY_TXT="Printer is Ready";
    private static final String PTR_NOT_READY_TXT="Printer is Not Ready";
    private static final int BEGINLUNCHHR=11;
    private static final int ENDLUNCHHR=15;
    //private static final int MAX_SINGLE_ENTRY=14;
    private RunGui theParent;
    private java.awt.Color autolistColor;
    private JButton[] exButtons;
    private JButton[] subButtons;
    private Condiment[] extraNoLittleCond;
    private Condiment[] subCond;
    //private ArrayList<CondEn> condimEdit;

    /*
    private static final byte REGULAR_ITEM=1;
    private static final byte MISC_NONTAX=2;
    private static final byte MISC_TAX=3;
     */ 
//    private int orderType;
    
    //public OrderDialog(RunGui parent, boolean modal, String phone, String name, String address, ResMenu theMenuParm) 
    //        throws InvalidQuantityException {
        public OrderDialog(RunGui parent, boolean modal, String phone, ResMenu theMenuParm) 
            throws InvalidQuantityException {        
        super(parent, modal);
        this.theMenu=theMenuParm;
        theParent=parent;
        initComponents();
        cusPhoneField.setText(phone);
        //cusNameField.setText(name);
        //cusAddressField.setText(address);
        helperInit(parent);
    }
    
    
    public OrderDialog(RunGui parent, boolean modal, Customer theCus, ResMenu theMenuParm) 
            throws InvalidQuantityException {
        super(parent, modal);
        this.cus=theCus;
        this.theMenu=theMenuParm;
        initComponents();
        helperInit(parent);
    }
    
    private void helperInit(RunGui parent) throws InvalidQuantityException{
        boolean autoToggle=parent.isAutoToggleSelected();
        int curHour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        System.out.println("current hour:"+curHour);
        System.out.println("autotoggle:"+autoToggle);
        if(!autoToggle){
            System.out.println("no autotoggle");
            this.lunchSpecialChk.setSelected(parent.isLunchSpecialSelected());
        }else if(curHour>=BEGINLUNCHHR&&curHour<ENDLUNCHHR){
                System.out.println("curHour>=BEGINLUNCHHR&&curHour<ENDLUNCHHR");
                this.lunchSpecialChk.setSelected(true);
                parent.toggleLunchSpecial(true);
        }else{
            System.out.println("not lunch");
                this.lunchSpecialChk.setSelected(false);
                parent.toggleLunchSpecial(false);
        }
        autoCompleteDoc.showLunchSpecial(lunchSpecialChk.isSelected());
        /*
        for(int i=1;i<=theMenu.getTotalNumItem();i++){
            jComboBox1.addItem(theMenu.getItem(i));
        }
        */ 
        itemField.requestFocus();
        if(cus!=null) {
            cusNameField.setText(cus.getName());
            cusAddressField.setText(cus.getAddress());
            cusPhoneField.setText(cus.getPhone());
            cusPhoneField.setEditable(false);
            searchBtn.setEnabled(false);
        }
        en=new Entry(cus,MainProgram.getSqlConnection());
        en.setOrderType(Entry.DELIVERY);
        orderNumField.setText(""+en.getOrderID());
            try{
                if(MainProgram.getPtr()!=null && MainProgram.getPtr().getDeviceEnabled()){
                    changePrinterLabelReady(true);
                }
            }catch(JposException e){
                changePrinterLabelReady(false);
            }
        autolistColor=autocompleteList.getSelectionBackground();
        /*
        boolean success=false;
        try{
            success=en.reserveRow();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error reserving row");
            success=false;
            e.printStackTrace();
            return;
        }
        if(!success){
            JOptionPane.showMessageDialog(null, "Error reserving row");
            
        }
         */ 
        initExtra();
    }
/*
    public orderDialog(RunGui parent, boolean modal, Customer cus, Menu theMenu, POSPrinterControl111 ptr) 
            throws InvalidQuantityException {    
            this(parent,modal,cus,theMenu);
            if(ptr!=null) setPrinter(ptr);
            try{
                if(ptr!=null && ptr.getDeviceEnabled()){
                    changePrinterLabelReady(true);
                }
            }catch(JposException e){
                e.printStackTrace();
                changePrinterLabelReady(false);
            }
    }
 */
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        entryPanel = new javax.swing.JPanel();
        subtotalField = new javax.swing.JTextField();
        totalField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        taxField = new javax.swing.JTextField();
        voidButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        entryListModel = new DefaultListModel();
        entryList = new javax.swing.JList(entryListModel);
        jLabel5 = new javax.swing.JLabel();
        editNotesBtn = new javax.swing.JButton();
        restartPrinterBtn = new javax.swing.JButton();
        removeCondButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        toGoBtn = new javax.swing.JToggleButton();
        pickupBtn = new javax.swing.JToggleButton();
        forHereBtn = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        finishButton = new javax.swing.JButton();
        lunchSpecialChk = new javax.swing.JCheckBox();
        cancelBtn = new javax.swing.JButton();
        orderPanel = new javax.swing.JPanel();
        itemField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        clearButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        miscNonTaxButton = new javax.swing.JButton();
        miscTaxButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        orderNumField = new javax.swing.JTextField();
        extraToggle = new javax.swing.JToggleButton();
        noToggle = new javax.swing.JToggleButton();
        littleToggle = new javax.swing.JToggleButton();
        subToggle = new javax.swing.JToggleButton();
        backBtn = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        autocompleteListModel = new DefaultListModel();
        autocompleteList = new javax.swing.JList(autocompleteListModel);
        ExtraNoLittlePanel = new javax.swing.JPanel();
        subPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        customerLabel = new javax.swing.JLabel();
        cusPhoneField = new javax.swing.JTextField();
        cusAddressField = new javax.swing.JTextField();
        customerLabel2 = new javax.swing.JLabel();
        customerLabel1 = new javax.swing.JLabel();
        cusNameField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        printerReadyLabel = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cardNumField = new javax.swing.JTextField();
        expField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                OrderDialog.this.windowClosing(evt);
            }
        });

        subtotalField.setEditable(false);
        subtotalField.setFont(new java.awt.Font("DejaVu Sans", 0, 18));

        totalField.setEditable(false);
        totalField.setFont(new java.awt.Font("DejaVu Sans", 0, 18));

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        jLabel2.setText("Subtotal:");

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        jLabel3.setText("Tax:");

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        jLabel4.setText("Total:");

        taxField.setEditable(false);
        taxField.setFont(new java.awt.Font("DejaVu Sans", 0, 18));

        voidButton.setFont(new java.awt.Font("SansSerif", 0, 18));
        voidButton.setMnemonic('V');
        voidButton.setText("Void");
        voidButton.setEnabled(false);
        voidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voidButtonActionPerformed(evt);
            }
        });

        entryList.setFont(new java.awt.Font("Monospaced", 0, 18));
        entryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        entryList.setCellRenderer(new JTextAreaRenderer());
        entryList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                entryListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(entryList);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        jLabel5.setText("Entries:");

        editNotesBtn.setFont(new java.awt.Font("SansSerif", 0, 18));
        editNotesBtn.setMnemonic('N');
        editNotesBtn.setText("Edit Notes");
        editNotesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNotesBtnActionPerformed(evt);
            }
        });

        restartPrinterBtn.setText("Restart Printer");
        restartPrinterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartPrinterBtnActionPerformed(evt);
            }
        });

        removeCondButton.setFont(new java.awt.Font("SansSerif", 1, 14));
        removeCondButton.setText("Remove Cond");
        removeCondButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCondButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout entryPanelLayout = new javax.swing.GroupLayout(entryPanel);
        entryPanel.setLayout(entryPanelLayout);
        entryPanelLayout.setHorizontalGroup(
            entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryPanelLayout.createSequentialGroup()
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalField)
                            .addComponent(taxField)
                            .addComponent(subtotalField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(removeCondButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editNotesBtn)
                            .addComponent(restartPrinterBtn)
                            .addComponent(voidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
                .addContainerGap())
        );
        entryPanelLayout.setVerticalGroup(
            entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryPanelLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(subtotalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(voidButton)
                            .addComponent(removeCondButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(taxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(editNotesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restartPrinterBtn)))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        buttonGroup1.add(toGoBtn);
        toGoBtn.setFont(new java.awt.Font("SansSerif", 0, 24));
        toGoBtn.setForeground(new java.awt.Color(255, 0, 0));
        toGoBtn.setSelected(true);
        toGoBtn.setText("To Go");
        toGoBtn.setUI(new MyToggleUI());
        toGoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toGoBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(pickupBtn);
        pickupBtn.setFont(new java.awt.Font("SansSerif", 0, 24));
        pickupBtn.setText("Pick Up");
        pickupBtn.setUI(new MyToggleUI());
        pickupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickupBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(forHereBtn);
        forHereBtn.setFont(new java.awt.Font("SansSerif", 0, 24));
        forHereBtn.setText("For Here");
        forHereBtn.setUI(new MyToggleUI());
        forHereBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forHereBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(forHereBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pickupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toGoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(forHereBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pickupBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toGoBtn)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 24));
        jLabel7.setText("Order Type:");

        finishButton.setFont(new java.awt.Font("SansSerif", 0, 36));
        finishButton.setMnemonic('F');
        finishButton.setText("Finish Ordering");
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        lunchSpecialChk.setFont(new java.awt.Font("SansSerif", 0, 24));
        lunchSpecialChk.setText("Lunch Special");
        lunchSpecialChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunchSpecialChkActionPerformed(evt);
            }
        });

        cancelBtn.setFont(new java.awt.Font("SansSerif", 0, 36));
        cancelBtn.setMnemonic('C');
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lunchSpecialChk)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(finishButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lunchSpecialChk)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cancelBtn)
                            .addComponent(finishButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        autoCompleteDoc=new AutocompleteDocument(theMenu,autocompleteList);
        itemField.setDocument(autoCompleteDoc);
        itemField.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        itemField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFieldActionPerformed(evt);
            }
        });
        itemField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemFieldKeyReleased(evt);
            }
        });

        addButton.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        addButton.setMnemonic('d');
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel1.setDisplayedMnemonic('Q');
        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        jLabel1.setLabelFor(quantityField);
        jLabel1.setText("Quantity:");

        quantityField.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        quantityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityFieldActionPerformed(evt);
            }
        });
        quantityField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quantityFieldKeyPressed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        clearButton.setMnemonic('l');
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel6.setDisplayedMnemonic('E');
        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        jLabel6.setLabelFor(itemField);
        jLabel6.setText("Item:");

        miscNonTaxButton.setFont(new java.awt.Font("SansSerif", 0, 14));
        miscNonTaxButton.setText("Misc NonTaxed");
        miscNonTaxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miscNonTaxButtonActionPerformed(evt);
            }
        });

        miscTaxButton.setFont(new java.awt.Font("SansSerif", 1, 14));
        miscTaxButton.setMnemonic('T');
        miscTaxButton.setText("Misc Taxed");
        miscTaxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miscTaxButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel10.setText("Order #:");

        orderNumField.setEditable(false);
        orderNumField.setFont(new java.awt.Font("SansSerif", 0, 18));

        buttonGroup2.add(extraToggle);
        extraToggle.setFont(new java.awt.Font("SansSerif", 0, 18));
        extraToggle.setText("Extra");
        extraToggle.setPreferredSize(new java.awt.Dimension(80, 33));
        extraToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extraToggleActionPerformed(evt);
            }
        });

        buttonGroup2.add(noToggle);
        noToggle.setFont(new java.awt.Font("SansSerif", 0, 18));
        noToggle.setText("No");
        noToggle.setPreferredSize(new java.awt.Dimension(80, 33));
        noToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noToggleActionPerformed(evt);
            }
        });

        buttonGroup2.add(littleToggle);
        littleToggle.setFont(new java.awt.Font("SansSerif", 0, 18));
        littleToggle.setText("Little");
        littleToggle.setPreferredSize(new java.awt.Dimension(80, 33));
        littleToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                littleToggleActionPerformed(evt);
            }
        });

        buttonGroup2.add(subToggle);
        subToggle.setFont(new java.awt.Font("SansSerif", 0, 18));
        subToggle.setText("Sub");
        subToggle.setPreferredSize(new java.awt.Dimension(80, 33));
        subToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subToggleActionPerformed(evt);
            }
        });

        backBtn.setFont(new java.awt.Font("SansSerif", 0, 18));
        backBtn.setText("back");
        backBtn.setPreferredSize(new java.awt.Dimension(80, 33));
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        topPanel.setLayout(new java.awt.CardLayout());

        autocompleteList.setFont(new java.awt.Font("SansSerif", 0, 18));
        autocompleteList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        autocompleteList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                autocompleteListMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                autocompleteListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                autocompleteListMouseReleased(evt);
            }
        });
        autocompleteList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                autocompleteListKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(autocompleteList);

        topPanel.add(jScrollPane2, "card4");

        ExtraNoLittlePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        topPanel.add(ExtraNoLittlePanel, "extraNoLittleCard");

        subPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        topPanel.add(subPanel, "subCard");

        javax.swing.GroupLayout orderPanelLayout = new javax.swing.GroupLayout(orderPanel);
        orderPanel.setLayout(orderPanelLayout);
        orderPanelLayout.setHorizontalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orderNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(itemField, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(miscTaxButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(miscNonTaxButton)
                                .addGap(39, 39, 39))))
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(extraToggle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noToggle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(littleToggle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subToggle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        orderPanelLayout.setVerticalGroup(
            orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(orderNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(miscNonTaxButton)
                    .addComponent(miscTaxButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(addButton)
                    .addComponent(itemField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subToggle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(littleToggle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noToggle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(extraToggle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        customerLabel.setDisplayedMnemonic('M');
        customerLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        customerLabel.setLabelFor(cusNameField);
        customerLabel.setText("Customer Name:");

        cusPhoneField.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        cusPhoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusPhoneFieldActionPerformed(evt);
            }
        });

        cusAddressField.setDocument(new FieldDocument(posdesktop.SqlFunc.ADDRESS_MAX_LENGTH));
        cusAddressField.setFont(new java.awt.Font("DejaVu Sans", 0, 18));

        customerLabel2.setDisplayedMnemonic('P');
        customerLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        customerLabel2.setLabelFor(cusPhoneField);
        customerLabel2.setText("Customer Phone:");

        customerLabel1.setDisplayedMnemonic('A');
        customerLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        customerLabel1.setLabelFor(cusAddressField);
        customerLabel1.setText("Customer Address:");

        cusNameField.setDocument(new FieldDocument(posdesktop.SqlFunc.NAME_MAX_LENGTH));
        cusNameField.setFont(new java.awt.Font("DejaVu Sans", 0, 18));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 24));
        jLabel9.setText("Printer Status:");

        printerReadyLabel.setBackground(new java.awt.Color(0, 255, 102));
        printerReadyLabel.setFont(new java.awt.Font("SansSerif", 0, 18));
        printerReadyLabel.setForeground(new java.awt.Color(255, 0, 0));
        printerReadyLabel.setText(PTR_NOT_READY_TXT);

        searchBtn.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        searchBtn.setMnemonic('S');
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Monospaced", 1, 14));
        jLabel8.setText("Number:");

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 14));
        jLabel11.setText("Exp Date:");

        cardNumField.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        expField.setFont(new java.awt.Font("Monospaced", 1, 14));

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 18));
        jLabel12.setText("Credit Card Info:");

        javax.swing.GroupLayout cardPanelLayout = new javax.swing.GroupLayout(cardPanel);
        cardPanel.setLayout(cardPanelLayout);
        cardPanelLayout.setHorizontalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(cardPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardNumField, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cardPanelLayout.setVerticalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cardNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(expField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(customerLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cusAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(customerLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusPhoneField, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(customerLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(printerReadyLabel)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(customerLabel)
                                    .addComponent(cusNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cusPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(customerLabel2))
                                    .addComponent(searchBtn)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printerReadyLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerLabel1)
                            .addComponent(cusAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(orderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void voidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voidButtonActionPerformed
// TODO add your handling code here:
    if(entryListModel.getSize()==0){
        System.out.println("Trying to void empty list");
        return;
    }
    int index=entryListModel.getSize()-1;
    if(!entryList.isSelectionEmpty()) index = entryList.getSelectedIndex();
    entryListModel.remove(index);
    boolean didRemove=en.removeItemFromFirst(index+1);
    if(!didRemove) System.out.println("Nothing is removed from the entry class");
    en.printEntry(System.out);
    int size = entryListModel.getSize();
    updateTotalFields();
    if (size == 0) { //Nobody's left, disable firing.
        voidButton.setEnabled(false);
        return;
    } else if (index == entryListModel.getSize()) {
            //removed item in last position
            index--;
    }
    entryList.setSelectedIndex(index);
    entryList.ensureIndexIsVisible(index);
}//GEN-LAST:event_voidButtonActionPerformed

private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
// TODO add your handling code here:
    quantityField.setText("");
    itemField.setText("");
    //noteArea.setText("");
    autocompleteListModel.clear();
    itemField.requestFocus();
}//GEN-LAST:event_clearButtonActionPerformed

private void quantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityFieldActionPerformed
// TODO add your handling code here:
    enterItemFromList();
}//GEN-LAST:event_quantityFieldActionPerformed

private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
// TODO add your handling code here:
    enterItemFromList();
    itemField.setText("");
    autocompleteListModel.clear();
}//GEN-LAST:event_addButtonActionPerformed

private void itemFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFieldActionPerformed
// TODO add your handling code here:
    enterItemFromList();
}//GEN-LAST:event_itemFieldActionPerformed

private void autocompleteListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autocompleteListMouseClicked
// TODO add your handling code here:
    //if(evt.getClickCount()%2==0){
        enterItemFromList();
    //}
}//GEN-LAST:event_autocompleteListMouseClicked

private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
// TODO add your handling code here:
    POSPtrEntry ptren;
    //Customer tempcus=null;
    boolean receiptPrinted=false;
    boolean allTextEmpty=cusNameField.getText().equals("")&&cusPhoneField.getText().equals("")&&cusAddressField.getText().equals("");
    try{
    System.out.println("Original size: "+en.getSize());
    en.printEntry(System.out);
    ptren=en.getFinalEntry();
    
    if(cus==null&&!allTextEmpty){
        cus=new Customer(0,cusPhoneField.getText(),cusNameField.getText(),cusAddressField.getText());
        ptren.setCustomer(cus);
    }else if(cus!=null){
        cus.setName(cusNameField.getText());
        cus.setAddress(cusAddressField.getText());
    }
    
    //credit card info
    if(cardNumField.getText().length()!=0||expField.getText().length()!=0){
        ptren.setCard(new CardInfo(cardNumField.getText(),expField.getText()));
    }
    
    System.out.println("final entry size: "+ptren.getSize());
    }catch(InvalidQuantityException e){
        e.printStackTrace();
        return;
    }
    System.out.println("Final Receipt:");
    ptren.printReceipt(System.out);
    try{
        if(MainProgram.getPtr()!=null && MainProgram.getPtr().getDeviceEnabled()){
            //ptr.
            ptren.printToPrinter(MainProgram.getPtr());
            receiptPrinted=true;
        } else{
            JOptionPane.showMessageDialog(null, "Receipt not printed because printer not enabled", null, JOptionPane.ERROR_MESSAGE);
            receiptPrinted=false;
        }
    }catch(JposException e){
        //e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error printing\n"+e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        changePrinterLabelReady(false);
        receiptPrinted=false;
    }
    boolean savedDatabase=false;
    try{
        savedDatabase=ptren.saveToDatabase();
        if(cus!=null){
            String formattedPhone=SqlFunc.makePhoneValidIfPossible(cusPhoneField.getText());
            cusPhoneField.setText(formattedPhone);
            cus.setPhone(formattedPhone);
            ResultSet rs=MainProgram.getSqlcus().searchbyPhone(formattedPhone,SqlFunc.EXACT_MATCH);
            if(!rs.next()){
                MainProgram.getSqlcus().createCustomer(formattedPhone, cusNameField.getText(), cusAddressField.getText());
            }else{
                MainProgram.getSqlcus().editCustomer(formattedPhone, cusNameField.getText(), cusAddressField.getText(), rs, rs.getString("CustomerID"));
            }
        }
        if(!savedDatabase){
            JOptionPane.showMessageDialog(null, "Error saving to database", null, JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error saving to database:" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    } catch (RuntimeException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error saving to database:" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    } catch(InvalidEntryException e){
        e.printStackTrace();
        if(e instanceof InvalidPhoneException)
            JOptionPane.showMessageDialog(null, "Customer not saved: invalid phone number");
        else
            JOptionPane.showMessageDialog(null, "Customer not saved: "+e.getMessage());
    } catch(DuplicateEntryException e){
        e.printStackTrace();
    }
    
    MainProgram.pushPrevEntry(ptren);

    /*
    if(!receiptPrinted){
        Object[] options = { "Yes", "No"};
        int btnClicked=JOptionPane.showOptionDialog(rootPane, "Quit without printing receipt?",null,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if(btnClicked==0) {
            this.dispose();
        }
    }else{
        this.dispose();
    }
     */
    if(!receiptPrinted){
        EntryReceiptDialog d = new EntryReceiptDialog(this,true,ptren);
        d.showReceipt(false);
        d.setVisible(true);
    }
    testPrintCond(ptren);
    this.dispose();
}//GEN-LAST:event_finishButtonActionPerformed

private void testPrintCond(POSPtrEntry ptren){
//            ItemNode cur=head.getNext();
//           for(int i=1;cur!=tail;i++,cur=cur.getNext()){
             ItemNode cur=ptren.getNode(0);
             for(int i=1;cur!=null&&cur.getNext()!=null;i++,cur=cur.getNext()){    
                
                //Item curItem=cur.getItem();
                StringBuffer tempEx=new StringBuffer(" Extra:");
                StringBuffer tempNo=new StringBuffer(" No:");
                StringBuffer tempLittle=new StringBuffer(" Little:");
                StringBuffer tempSub=new StringBuffer(" Sub:");                
                ArrayList<CondEn> tempcond=cur.getCondim();
                boolean printEx=false,printNo=false,printLittle=false,printSub=false;
                if(tempcond!=null){ 
                    for(int j=0;j<tempcond.size();j++){
                        CondEn temp=tempcond.get(j);
                        int quantity=temp.getQuantity();
                        switch(temp.getCondType()){
                            case(CondEn.EXTRA):
                                if(quantity>1){
                                    tempEx.append(quantity+" "+temp.getCond().getCondName());
                                } else {
                                    tempEx.append(temp.getCond().getCondName());
                                }
                                if(temp.getCond().getAdditionalPrice().compareTo(new BigDecimal("0"))!=0){
                                    tempEx.append("($"+temp.getCond().getAdditionalPrice().multiply(new BigDecimal(quantity))+")");
                                }
                                tempEx.append(",");
                                printEx=true;
                                break;
                            case(CondEn.NO):
                                if(quantity>1)tempNo.append(quantity+" "+temp.getCond().getCondName());
                                else tempNo.append(temp.getCond().getCondName());
                                tempNo.append(",");
                                printNo=true;
                                break;
                            case(CondEn.LITTLE):
                                //tempLittle.append(temp.getCond().getCondName()+",");
                                if(quantity>1)tempLittle.append(quantity+" "+temp.getCond().getCondName());
                                else tempLittle.append(temp.getCond().getCondName());
                                tempLittle.append(",");
                                printLittle=true;
                                break;                                    
                            case(CondEn.SUB):
                                if(quantity>1)tempSub.append(quantity+" "+temp.getCond().getCondName());
                                else tempSub.append(temp.getCond().getCondName());
                                if(temp.getCond().getAdditionalPrice().compareTo(new BigDecimal("0"))!=0){
                                    tempSub.append("($"+temp.getCond().getAdditionalPrice().multiply(new BigDecimal(quantity))+")");
                                }
                                tempSub.append(",");                                
                                //tempSub.append(temp.getCond().getCondName()+",");
                                printSub=true;
                                break;    
                    }
                   }
                }
                System.out.println("i:"+i);
                if(printEx) System.out.println(tempEx);
                if(printNo) System.out.println(tempNo);
                if(printLittle) System.out.println(tempLittle);
                if(printSub) System.out.println(tempSub);

            }    
}

private void initExtra(){
        extraNoLittleCond=theMenu.getExtraNoLittleCond();
        //MainProgram.
        System.out.println("working exbtn:");
        exButtons=new JButton[extraNoLittleCond.length];
        for(int i=0; i<extraNoLittleCond.length;i++) {
            exButtons[i]=new JButton(extraNoLittleCond[i].getCondName());
            exButtons[i].setFont(new java.awt.Font("SansSerif", 0, 18));
            helperAdd(ExtraNoLittlePanel,exButtons,i);
        }
        subCond=theMenu.getSubCond();
        System.out.println("working subbtn:");
        subButtons=new JButton[subCond.length];
        for(int i=0; i<subCond.length;i++) {
            subButtons[i]=new JButton(subCond[i].getCondName());
            subButtons[i].setFont(new java.awt.Font("SansSerif", 0, 18));
            helperAdd(subPanel,subButtons,i);
        }
        
        //JButton[] subButtons=new JButton[subCond.length];
        //this.pack();
        //jPanel1.removeAll();
    }

    private void helperAdd(JPanel panel,JButton[] btn, final int num){
            btn[num].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ButtonAction(evt,num);
                }
            });
            panel.add(btn[num]);
            System.out.println(extraNoLittleCond[num].getCondName());
    }
    
    private void ButtonAction(java.awt.event.ActionEvent evt,int in){
    if(entryListModel.isEmpty()){
            JOptionPane.showMessageDialog(this, "entry list empty");
            goBack();
            return;
    } 
    int quantityAdd=getRightQuantity();  
    if(quantityAdd<1){
            JOptionPane.showMessageDialog(this, "invalid quantity");
            goBack();
            return;
    }
    int index=entryListModel.getSize()-1;
    if(!entryList.isSelectionEmpty()) {
        index = entryList.getSelectedIndex();
        //entryList.setSelectedIndex(index);
    }else{
        entryList.setSelectedIndex(index);
        entryList.ensureIndexIsVisible(index);
    }
    ItemNode theNode=en.getNode(index);
    ArrayList<CondEn> condim=theNode.getCondim();
    if(condim==null){
        condim=new ArrayList<CondEn>();
    }
        if(extraToggle.isSelected()){
            System.out.println("action performed, extra :"+exButtons[in].getText());
            int searchIndex=theNode.searchCond(CondEn.EXTRA, extraNoLittleCond[in]);
            if(searchIndex<0)
                condim.add(new CondEn(quantityAdd,CondEn.EXTRA,extraNoLittleCond[in]));
            else
                condim.get(searchIndex).setQuantity(condim.get(searchIndex).getQuantity()+quantityAdd);
        } else if(noToggle.isSelected()){
            System.out.println("action performed, no :"+exButtons[in].getText());
            //condim.add(new CondEn(quantityAdd,CondEn.NO,extraNoLittleCond[in]));
            int searchIndex=theNode.searchCond(CondEn.NO, extraNoLittleCond[in]);
            if(searchIndex<0)
                condim.add(new CondEn(quantityAdd,CondEn.NO,extraNoLittleCond[in]));
            else
                condim.get(searchIndex).setQuantity(condim.get(searchIndex).getQuantity()+quantityAdd);
        } else if(littleToggle.isSelected()){
            System.out.println("action performed, little :"+exButtons[in].getText());
            //condim.add(new CondEn(1,CondEn.LITTLE,extraNoLittleCond[in]));
            int searchIndex=theNode.searchCond(CondEn.LITTLE, extraNoLittleCond[in]);
            if(searchIndex<0)
                condim.add(new CondEn(quantityAdd,CondEn.LITTLE,extraNoLittleCond[in]));
            else
                condim.get(searchIndex).setQuantity(condim.get(searchIndex).getQuantity()+quantityAdd);            
        } else if(subToggle.isSelected()){
            System.out.println("action performed, sub :"+subButtons[in].getText());
            //condim.add(new CondEn(quantityAdd,CondEn.SUB,subCond[in]));
            int searchIndex=theNode.searchCond(CondEn.SUB, subCond[in]);
            if(searchIndex<0)
                condim.add(new CondEn(quantityAdd,CondEn.SUB,subCond[in]));
            else
                condim.get(searchIndex).setQuantity(condim.get(searchIndex).getQuantity()+quantityAdd);            
        }
        theNode.setCondim(condim);
        updateTotalFields();
        entryListModel.setElementAt(Entry.formatEntryForList(theNode), index);
        entryList.ensureIndexIsVisible(index);
        quantityField.setText("");
        goBack();
    }

    
    
private void autocompleteListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_autocompleteListKeyPressed
// TODO add your handling code here:
    if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER)
        enterItemFromList();
}//GEN-LAST:event_autocompleteListKeyPressed

private void itemFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemFieldKeyPressed
// TODO add your handling code here:
    moveAutocompleteField(evt);
    
}//GEN-LAST:event_itemFieldKeyPressed

private void quantityFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityFieldKeyPressed
// TODO add your handling code here:
    moveAutocompleteField(evt);
}//GEN-LAST:event_quantityFieldKeyPressed

private void miscNonTaxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miscNonTaxButtonActionPerformed
// TODO add your handling code here:
    inputMisc(MiscItem.ITEM_NOT_TAXED);
}//GEN-LAST:event_miscNonTaxButtonActionPerformed

private void miscTaxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miscTaxButtonActionPerformed
// TODO add your handling code here:

    /*
    if(str==null||str.length()==0) {
        System.out.println("Cancel pressed");
        inputMisc(null,MiscItem.ITEM_TAXED);
        return;
    }
     */
    inputMisc(MiscItem.ITEM_TAXED);
}//GEN-LAST:event_miscTaxButtonActionPerformed

private void windowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosing
// TODO add your handling code here:
    doClose();
}//GEN-LAST:event_windowClosing

private void doClose(){
        Object[] options = {"Yes", "No"};
        int btnClicked=JOptionPane.showOptionDialog(rootPane, "Cancel ordering?",null,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if(btnClicked==0) {
            this.dispose();
        }
}

private void restartPrinterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartPrinterBtnActionPerformed
// TODO add your handling code here:
    boolean success=MainProgram.restartPrinter();
    try{
        if(!success) changePrinterLabelReady(false);
        else if(MainProgram.getPtr().getDeviceEnabled()){
            changePrinterLabelReady(true);
            return;
        }
    }catch(JposException e){
        e.printStackTrace();
    }
    changePrinterLabelReady(false);
}//GEN-LAST:event_restartPrinterBtnActionPerformed

private void lunchSpecialChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunchSpecialChkActionPerformed
// TODO add your handling code here:
    theParent.toggleLunchSpecial(lunchSpecialChk.isSelected());
    autoCompleteDoc.showLunchSpecial(lunchSpecialChk.isSelected());
    if(autocompleteListModel.isEmpty()&&itemField.getText().length()==0) return;
    try{
        autoCompleteDoc.helperUpdate();
    }catch(BadLocationException e){
        System.out.println(e.getMessage());
    }
}//GEN-LAST:event_lunchSpecialChkActionPerformed

private void forHereBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forHereBtnActionPerformed
// TODO add your handling code here:
    JOptionPane.showMessageDialog(this, "Calculate final receipt only for order type HERE");

        cusNameField.setEditable(false);
        cusPhoneField.setEditable(false);
        searchBtn.setEnabled(false);
        cusAddressField.setEditable(false);

    forHereBtn.setForeground(new java.awt.Color(255,0,0));
    pickupBtn.setForeground(new java.awt.Color(0,0,0));
    toGoBtn.setForeground(new java.awt.Color(0,0,0));
    en.setOrderType(Entry.HERE);
}//GEN-LAST:event_forHereBtnActionPerformed

private void pickupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickupBtnActionPerformed
// TODO add your handling code here:
    if(cus==null){
        cusNameField.setEditable(true);
        cusPhoneField.setEditable(true);
        searchBtn.setEnabled(true);
        cusAddressField.setEditable(false);    
    }else{
        cusNameField.setEditable(true);
        cusPhoneField.setEditable(false);
        searchBtn.setEnabled(false);
        cusAddressField.setEditable(false);            
    }
    forHereBtn.setForeground(new java.awt.Color(0,0,0));
    pickupBtn.setForeground(new java.awt.Color(255,0,0));
    toGoBtn.setForeground(new java.awt.Color(0,0,0));
    en.setOrderType(Entry.PICKUP);
}//GEN-LAST:event_pickupBtnActionPerformed

private void toggleExSubColor(){
    System.out.println("toggling");
    if(extraToggle.isSelected()){
        extraToggle.setForeground(new java.awt.Color(255,0,0));
    }else{
        extraToggle.setForeground(new java.awt.Color(0,0,0));
    }
    if(noToggle.isSelected()){
        noToggle.setForeground(new java.awt.Color(255,0,0));
    }else{
        noToggle.setForeground(new java.awt.Color(0,0,0));
    }
    if(littleToggle.isSelected()){
        littleToggle.setForeground(new java.awt.Color(255,0,0));
    }else{
        littleToggle.setForeground(new java.awt.Color(0,0,0));
    }
    if(subToggle.isSelected()){
        subToggle.setForeground(new java.awt.Color(255,0,0));
    }else{
        subToggle.setForeground(new java.awt.Color(0,0,0));
    }
}

private void toGoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toGoBtnActionPerformed
// TODO add your handling code here:
    if(cus==null){
        cusNameField.setEditable(true);
        cusPhoneField.setEditable(true);
        searchBtn.setEnabled(true);
        cusAddressField.setEditable(true);        
    }else{
        cusNameField.setEditable(true);
        cusPhoneField.setEditable(false);
        searchBtn.setEnabled(false);
        cusAddressField.setEditable(true);                
    }
    forHereBtn.setForeground(new java.awt.Color(0,0,0));
    pickupBtn.setForeground(new java.awt.Color(0,0,0));
    toGoBtn.setForeground(new java.awt.Color(255,0,0));
    en.setOrderType(Entry.DELIVERY);
}//GEN-LAST:event_toGoBtnActionPerformed

private void entryListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entryListKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_entryListKeyPressed

private void editNotesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNotesBtnActionPerformed
// TODO add your handling code here:
    editNotes();
}//GEN-LAST:event_editNotesBtnActionPerformed

private void editNotes(){
    int index=helpGetSelect();
    if(index<0) return;
    ItemNode theNode=en.getNode(index);
    String newNotes=JOptionPane.showInputDialog("Enter new note", theNode.getNotes());
    if(newNotes==null) return;  //cancel pressed
    else if(newNotes.length()==0) newNotes=null; //is empty string, delete note
    theNode.setNotes(newNotes);
    entryListModel.setElementAt(Entry.formatEntryForList(theNode), index);
    entryList.ensureIndexIsVisible(index);
}

private void cusPhoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusPhoneFieldActionPerformed
// TODO add your handling code here:
    getcusinfo();
}//GEN-LAST:event_cusPhoneFieldActionPerformed

private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
// TODO add your handling code here:
    getcusinfo();
}//GEN-LAST:event_searchBtnActionPerformed

private void autocompleteListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autocompleteListMousePressed
// TODO add your handling code here:
    autocompleteList.setSelectionBackground(java.awt.Color.RED);
}//GEN-LAST:event_autocompleteListMousePressed

private void autocompleteListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autocompleteListMouseReleased
// TODO add your handling code here:
    autocompleteList.setSelectionBackground(autolistColor);
}//GEN-LAST:event_autocompleteListMouseReleased

private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
// TODO add your handling code here:
    doClose();
}//GEN-LAST:event_cancelBtnActionPerformed

private void removeCondButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCondButtonActionPerformed
// TODO add your handling code here:
    helperOpenRemove();
}//GEN-LAST:event_removeCondButtonActionPerformed

private void helperOpenRemove(){
    int index=helpGetSelect();
    if(index<0) return;
    ItemNode theNode=en.getNode(index);
    ArrayList<CondEn> condim=theNode.getCondim();
    if(condim==null||condim.size()==0){
        JOptionPane.showMessageDialog(this, "nothing to remove");
        return;
    }else{
        RemoveCondDialog diag;
        try{
            diag=new RemoveCondDialog(this,true,condim);
            diag.setVisible(true);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        theNode.setCondim(condim);
        updateTotalFields();
        entryListModel.setElementAt(Entry.formatEntryForList(theNode), index);
        entryList.ensureIndexIsVisible(index);
    }
}

private int helpGetSelect(){
    if(entryListModel.isEmpty()){
            JOptionPane.showMessageDialog(this, "entry list empty");
            return -1;
    } 
        
    int index=entryListModel.getSize()-1;
    if(!entryList.isSelectionEmpty()) {
        index = entryList.getSelectedIndex();
        return index;
        //entryList.setSelectedIndex(index);
    }else{
        JOptionPane.showMessageDialog(this, "Select something first");
        return -1;
    }
}

private void extraToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extraToggleActionPerformed
// TODO add your handling code here:
    toggleExtraNoLittle();
}//GEN-LAST:event_extraToggleActionPerformed

private void noToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noToggleActionPerformed
// TODO add your handling code here:
    toggleExtraNoLittle();
}//GEN-LAST:event_noToggleActionPerformed

private void littleToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_littleToggleActionPerformed
// TODO add your handling code here:
    toggleExtraNoLittle();
}//GEN-LAST:event_littleToggleActionPerformed

private void toggleExtraNoLittle(){
    toggleExSubColor();
    if(ExtraNoLittlePanel.isVisible()) return;
    System.out.println(ExtraNoLittlePanel.isVisible());
    System.out.println(subPanel.isVisible());
    CardLayout c1=(CardLayout)(topPanel.getLayout());
    //c1.first(jPanel1);
    c1.show(topPanel, "extraNoLittleCard");
}

private void subToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subToggleActionPerformed
// TODO add your handling code here:
    toggleSub();
}//GEN-LAST:event_subToggleActionPerformed

private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
// TODO add your handling code here:
    goBack();
}//GEN-LAST:event_backBtnActionPerformed

private void itemFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemFieldKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_itemFieldKeyReleased

private void goBack(){
      buttonGroup2.clearSelection();
//    extraToggle.setSelected(false);
//    noToggle.setSelected(false);
//    littleToggle.setSelected(false);
//    subToggle.setSelected(false);
    CardLayout c1=(CardLayout)(topPanel.getLayout());
    //c1.first(jPanel1);
    c1.show(topPanel, "card4");
    toggleExSubColor();
}

private void toggleSub(){
    toggleExSubColor();
    if(subPanel.isVisible()) return;
    System.out.println(ExtraNoLittlePanel.isVisible());
    System.out.println(subPanel.isVisible());
    //changeButton();
    CardLayout c1=(CardLayout)(topPanel.getLayout());
    c1.show(topPanel, "subCard");
    //c1.last(jPanel1);
}


private void getcusinfo(){
    try{
        String phone=SqlFunc.makePhoneValidIfPossible(cusPhoneField.getText());
        System.out.println(phone);
        ResultSet result=MainProgram.getSqlcus().searchbyPhone(phone, SqlFunc.EXACT_MATCH);
        if(result.next()){
            cusNameField.setText(result.getString("Name"));
            cusAddressField.setText(result.getString("Address"));
        }else{
            JOptionPane.showMessageDialog(this, "no result found");
        }
    }catch(InvalidEntryException e){
        JOptionPane.showMessageDialog(this, e.getMessage());
        e.printStackTrace();
    }catch(SQLException e){
        e.printStackTrace();
    }
}

/*
public boolean setPrinter(POSPrinterControl111 ptr){
    try{
        if(!ptr.getDeviceEnabled()) {
            return false;
        }
    }catch(JposException e){
        System.out.println("setPrinter: "+e.getMessage());
        return false;
    }
    this.ptr=ptr;
    return true;
}
*/
private void inputMisc(boolean taxed){
    String name=null;
    BigDecimal amount=null;
    String[] tempname=new String[1];
    BigDecimal[] tempamount=new BigDecimal[1];
    CustomAmountDialog dialog=new CustomAmountDialog(this,true,tempname,tempamount,taxed);
    dialog.setVisible(true);
    if(tempamount[0]==null){
        System.out.println("Cancel pressed");
        return;
    }
    if(!("".equals(tempname[0]))) name=tempname[0];
    amount=tempamount[0];
    /*
    String name=JOptionPane.showInputDialog("Please enter the name of item:");
    if("".equals(name)) name=null;    //if string empty, change it to null
    String amountStr=JOptionPane.showInputDialog("Please enter the amount in dollar(can be negative):");
    if(amountStr==null) {
        System.out.println("Cancel pressed");
        return;
    }
    BigDecimal amount;
     */ 
/*    try{
        amount=new BigDecimal(amountStr);
    }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(null, "Invalid price entered!");
        return;
    }
 */ 
    MiscItem item=null;
    if(name!=null)
        item=new MiscItem(name,amount,taxed);
    else
        item=new MiscItem(amount,taxed);
    /*
    Item item=null;
    try{
    if(menuItem&&name!=null){
        item=new MenuItem(name,"---",amount,false);
        System.out.println("menu item:"+name);
    }
    else if(menuItem&&name==null){
        item=new MenuItem("No Name","---",amount,false);
        System.out.println("menu item:"+name);
    }
    else if(!menuItem&&name!=null){
        item=new MiscItem(name,amount,taxed);
        System.out.println("misc item:"+name+"taxed?"+taxed);
    }
    else{
        item=new MiscItem(amount,taxed);
        System.out.println("misc item:"+name+"taxed?"+taxed);
    }
    }catch(InvalidPriceException e){
        e.printStackTrace();
    }
     */ 
    System.out.println(item);
    enterItem(item);

}

private void moveAutocompleteField(java.awt.event.KeyEvent evt) {
    System.out.println("keycode: "+evt.getKeyCode());
    if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN 
            && autocompleteList.getSelectedIndex()>=0 
            && autocompleteList.getSelectedIndex()+1<autocompleteListModel.getSize()){
        autocompleteList.setSelectedIndex(autocompleteList.getSelectedIndex()+1);
        autocompleteList.ensureIndexIsVisible(autocompleteList.getSelectedIndex()+1);
    } else if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_UP 
            && autocompleteList.getSelectedIndex()>=1){
        autocompleteList.setSelectedIndex(autocompleteList.getSelectedIndex()-1);
        autocompleteList.ensureIndexIsVisible(autocompleteList.getSelectedIndex()-1);
    }
}

private void updateTotalFields(){
    BigDecimal tempB4=en.getTotalB4Tax();
    BigDecimal tax=en.getTax();
    subtotalField.setText(""+tempB4);
    taxField.setText(""+tax);
    totalField.setText(""+tempB4.add(tax).setScale(2));
    //return getTotalB4Tax().add(getTax()).setScale(2)
}

private void enterItemFromList(){
    if(autocompleteListModel.isEmpty()) return;
    if(!(autocompleteList.getSelectedValue() instanceof Item)) return;
    enterItem((MenuItem)autocompleteList.getSelectedValue());
    //noteArea.setText("");
    //enterItem(null);
}

private int getRightQuantity(){
    int quantity;
    if(quantityField.getText().equals("")) {
        //quantityField.setText("1");
        quantity=1;
    }
    else try{
        quantity=Integer.parseInt(quantityField.getText());
    }catch(NumberFormatException e){
        //JOptionPane.showMessageDialog(autocompleteList, "Please enter a valid quantity");
        return -1;
    }    
    return quantity;
}

private void enterItem(Item item){
    if(item==null) return;
    int quantity=getRightQuantity();

    if(quantity<1){
        JOptionPane.showMessageDialog(autocompleteList, "Please enter a valid quantity");
        return;
    }
    //int index = menuComboBox.get;
    ItemNode addedNode;
        try {
            //if(noteArea.getText().equals("")) addedNode=en.addLast(item , quantity);
            //else addedNode=en.addLast(item, quantity, noteArea.getText());
            addedNode=en.addLast(item, quantity);
        } catch (InvalidQuantityException ex) {
            JOptionPane.showMessageDialog(autocompleteList, "Please enter a valid quantity");
            return;
        }
    String toAdd;
    /*
    if(!noteArea.getText().equals(""))
        toAdd=formatEntryForList(quantity,item,noteArea.getText());
    else
        toAdd=formatEntryForList(quantity,item,null);
     */
    toAdd=Entry.formatEntryForList(addedNode);
    entryListModel.addElement(toAdd);
 
    updateTotalFields();
    entryList.setSelectedIndex(entryListModel.getSize()-1);
    entryList.ensureIndexIsVisible(entryListModel.getSize()-1);
    en.printEntry(System.out);
    quantityField.setText("");
    if(!voidButton.isEnabled()) voidButton.setEnabled(true);
}


private void changePrinterLabelReady(boolean ready){
    if(ready) {
        printerReadyLabel.setForeground(new java.awt.Color(0, 0, 255));
        printerReadyLabel.setText(PTR_READY_TXT);
    }else{
        printerReadyLabel.setForeground(new java.awt.Color(255, 0, 0));
        printerReadyLabel.setText(PTR_NOT_READY_TXT);        
    }
}

	    private class JTextAreaRenderer extends JTextArea implements ListCellRenderer {
	        public JTextAreaRenderer() {
                    //int orientation=list.getLayoutOrientation();
	            //setLineWrap(true);
	            //setWrapStyleWord(true);
	            //setPreferredSize(list.getPreferredSize());
	        }
	        public Component getListCellRendererComponent(JList list, Object value,
	                int index, boolean isSelected, boolean cellHasFocus) {
                    setFont(list.getFont());
	            setText(value.toString());
                    setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
	            return this;
	        }
	    }
    
   /**
    * @param args the command line arguments
    */
/*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewJDialog dialog = new NewJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ExtraNoLittlePanel;
    private javax.swing.JButton addButton;
    private javax.swing.JList autocompleteList;
    private javax.swing.JButton backBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField cardNumField;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField cusAddressField;
    private javax.swing.JTextField cusNameField;
    private javax.swing.JTextField cusPhoneField;
    private javax.swing.JLabel customerLabel;
    private javax.swing.JLabel customerLabel1;
    private javax.swing.JLabel customerLabel2;
    private javax.swing.JButton editNotesBtn;
    private javax.swing.JList entryList;
    private javax.swing.JPanel entryPanel;
    private javax.swing.JTextField expField;
    private javax.swing.JToggleButton extraToggle;
    private javax.swing.JButton finishButton;
    private javax.swing.JToggleButton forHereBtn;
    private javax.swing.JTextField itemField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton littleToggle;
    private javax.swing.JCheckBox lunchSpecialChk;
    private javax.swing.JButton miscNonTaxButton;
    private javax.swing.JButton miscTaxButton;
    private javax.swing.JToggleButton noToggle;
    private javax.swing.JTextField orderNumField;
    private javax.swing.JPanel orderPanel;
    private javax.swing.JToggleButton pickupBtn;
    private javax.swing.JLabel printerReadyLabel;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton removeCondButton;
    private javax.swing.JButton restartPrinterBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JPanel subPanel;
    private javax.swing.JToggleButton subToggle;
    private javax.swing.JTextField subtotalField;
    private javax.swing.JTextField taxField;
    private javax.swing.JToggleButton toGoBtn;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField totalField;
    private javax.swing.JButton voidButton;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel entryListModel;
    private DefaultListModel autocompleteListModel;
    private AutocompleteDocument autoCompleteDoc;

    private class AutocompleteDocument extends PlainDocument
    {
         private ResMenu theMenu;
         private DefaultListModel autocompleteListModel;
         private JList autocompleteList;
         private boolean showLunchSpecial=true;
         public AutocompleteDocument(ResMenu theMenuParm, JList theAutocompleteList)
         {
              super();
              this.theMenu=theMenuParm;
              this.autocompleteList=theAutocompleteList;
              this.autocompleteListModel=(DefaultListModel)autocompleteList.getModel();
         }

         public void insertString(int offset, String s, AttributeSet attributeSet)
              throws BadLocationException
         {
             super.insertString(offset, s, attributeSet);   
             helperUpdate();
         }

         public void remove(int offs, int len) throws BadLocationException {
            // return immediately when selecting an item

            super.remove(offs, len);
            if(getLength()==0) {
                autocompleteListModel.clear();
                return;
            }
            
            helperUpdate();
        }

        public void helperUpdate() throws BadLocationException {
            long timeBefore=System.currentTimeMillis();
             autocompleteListModel.clear();
             
             try{
                int number=Integer.parseInt(getText(0,getLength()));
                if(number>=1||number<=theMenu.getTotalNumItem()) {
                    MenuItem tempItem=theMenu.getItem(number-1);
                    if(tempItem==null) return;
                    if(showLunchSpecial||!tempItem.isLunchSpecial()){
                        helperEnter(number-1,tempItem);
                        long timeAfter=System.currentTimeMillis();
                        System.out.println("it took "+(timeAfter-timeBefore)+" ms to update");
                    }
                    return;
                }
             }catch(NumberFormatException e){
                 System.out.println("no number entered");
             }
             /*
                    StringBuffer tempstr=new StringBuffer();
                    //String[] tempstrtoken=getText(0,getLength()).toLowerCase().split("\\.");
                    String[] tempstrtoken=getText(0,getLength()).toLowerCase().split(" ");
                    if(tempstrtoken.length>0){
                         for(int k=0;k<tempstrtoken.length;k++){
                             if(k!=0) tempstr.append(".*");
                             tempstr.append(tempstrtoken[k]);
                         }
                         tempstr.append(".*");
                    }
                    System.out.println("temp string:"+tempstr);    
              */  
             /*
             final MenuItem[] sorted=theMenu.getSortedItemArray();
            for(int i=0;i<sorted.length;i++){
                if(!showLunchSpecial && sorted[i].isLunchSpecial()) continue;
                if(helperCompare(sorted[i].getName(),getText(0,getLength()))) {                            
                        autocompleteListModel.addElement(sorted[i]);
                }
            }
              */ 
             
             for(int i=0;i<theMenu.getTotalNumItem();i++){
                 MenuItem temp=theMenu.getItem(i);
                 if(temp==null) continue;

                 if(!showLunchSpecial && temp.isLunchSpecial()) continue;
                    MenuItem[] tempmultiple;
                    if((tempmultiple=theMenu.getMultipleItem(i))!=null){
                        for(int a=0;a<tempmultiple.length;a++){
                            //if(tempmultiple[a].getName().toLowerCase().startsWith(getText(0,getLength()).toLowerCase())){
                            //if(tempmultiple[a].getName().toLowerCase().matches(tempstr.toString())){
                            if(helperCompare(tempmultiple[a].getName(),getText(0,getLength()))){                            
                                autocompleteListModel.addElement(tempmultiple[a]);
                            }
                        }
                    //}else if(temp.getName().toLowerCase().startsWith(getText(0,getLength()).toLowerCase())) {
                    //}else if(temp.getName().toLowerCase().matches(tempstr.toString())) {    
                    }else if(helperCompare(temp.getName(),getText(0,getLength()))) {                            
                        autocompleteListModel.addElement(temp);
                    }
                    /*
                    if(autocompleteListModel.size()>=MAX_SINGLE_ENTRY){
                        autocompleteListModel.addElement("Some Omitted . . . ");
                        break;
                    }      
                    */            
                }
             
                    autocompleteList.setSelectedIndex(0);
                    autocompleteList.ensureIndexIsVisible(0);
            long timeAfter=System.currentTimeMillis();
            System.out.println("it took "+(timeAfter-timeBefore)+" ms to update");
        }
        
        private void helperEnter(int i,MenuItem temp){
                    MenuItem[] tempmultiple;
                    if((tempmultiple=theMenu.getMultipleItem(i))!=null){
                        for(int a=0;a<tempmultiple.length;a++){
                            autocompleteListModel.addElement(tempmultiple[a]);
                        }
                    }else {
                        autocompleteListModel.addElement(temp);
                    }
                    autocompleteList.setSelectedIndex(0);
                    autocompleteList.ensureIndexIsVisible(0);
        }
        
        private boolean helperCompare(String str, final String str2){
            //String[]
            String[] token=str2.split(" ");
            for(int x=0;x<token.length;x++){
                //System.out.println(token[x]);
            }            
            if(str==null||token==null||token.length==0) return false;
            String strlower=str.toLowerCase();
            //System.out.println("strlower:"+strlower);            
            int tempIndex=0;
            //boolean equal=false;
            if(token[0].length()!=0){
                if(!strlower.startsWith(token[0].toLowerCase())){
                    return false;
                }else{
                    tempIndex+=token[0].length();
                }
            }
            
            if(tempIndex>str.length()) return false;            
            
            for(int i=1;i<token.length;i++){
                //System.out.println("tempIndex:"+tempIndex);
                if(token[i].length()==0) continue;
                tempIndex=strlower.indexOf(token[i], tempIndex);
                if(tempIndex<0) {
                    //System.out.println("i return false:"+i+"value:"+token[i]+"tempIndex:"+tempIndex);
                    return false;
                }
                tempIndex+=token[i].length();
                //curIndex+=tempIndex;
                //if(curIndex>str.length()) return false;
            }
            return true;
        }
        
        /*
        private boolean helperMatch(String text,String pattern){
            int curIndex=1;
            if(pattern.charAt(0)!='*'){
                curIndex=pattern.indexOf('*', 1);
                String temppattern=pattern.substring(0,curIndex);
                if(!text.startsWith(temppattern)){
                    return false;
                }
            }else{
                //text.ma
            }
            pattern=pattern.substring(1);
        }
*/
/*        
        private boolean helperCompare(String first,String second){
            if(second.length()>first.length()) return false;
            int count;
            char char1,char2;
            for(count=0;count<=second.length()-1;count++){
                char1=second.charAt(count);
                char2=first.charAt(count);
                //if(){}
                if(char1==char2){
                    continue;
                } else if(char1>char2){
                    if(!Character.isLetter(char1)) return false;
                    else if((char1-char2)!=32) return false;
                    else continue;
                } else if(char1<char2){
                    if(!Character.isLetter(char2)) return false;
                    else if((char2-char1)!=32) return false;
                    else continue;
                }
            }
            return true;
        }
*/        
        public void showLunchSpecial(boolean show){
            showLunchSpecial=show;
        }
    }
}
class MyToggleUI extends MetalToggleButtonUI{
    public java.awt.Color getSelectColor(){return java.awt.Color.blue;}
}