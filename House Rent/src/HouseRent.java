import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class HouseRent extends javax.swing.JFrame {

    /**
     * Creates new form HouseRent
     */
    public HouseRent() {
      
    initComponents();
    Connect();
    Table();
    setupRoomNumberListener(); // ADD THIS LINE

    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            DefaultTableModel d1 = (DefaultTableModel) jTable1.getModel();
            int selectIndex = jTable1.getSelectedRow();

            txtName.setText(d1.getValueAt(selectIndex, 0).toString());
            txtContact.setText(d1.getValueAt(selectIndex, 1).toString());
            txtNo.setText(d1.getValueAt(selectIndex, 2).toString());
            txtNeedToPay.setText(d1.getValueAt(selectIndex, 3).toString()); // ADD THIS LINE
            cmbMonth.setSelectedItem(d1.getValueAt(selectIndex, 4).toString()); // ADD THIS LINE
        }
    });
}



    






   
    @SuppressWarnings("unchecked")
    Connection con;
PreparedStatement pst;

public void Connect()
    {
        try {
         Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/houserent","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HouseRent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HouseRent.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Bupdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Bdelete = new javax.swing.JButton();
        cmbMonth = new javax.swing.JComboBox<>();
        txtNeedToPay = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        dashboard = new javax.swing.JButton();
        addroom = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        PaymentRecords = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("House Rent");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Contact", "House no", "Neeet to Pay", "Month"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ADD/ Update /Delete Section"));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Contact");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Name");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Need to Pay");

        Bupdate.setText("Update");
        Bupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BupdateActionPerformed(evt);
            }
        });

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Bdelete.setText("Delete");
        Bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BdeleteActionPerformed(evt);
            }
        });

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmbMonth.setToolTipText("");
        cmbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonthActionPerformed(evt);
            }
        });

        txtNeedToPay.setText("Automatically Add");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Room no.");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Month");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(Bupdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bdelete)
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbMonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(txtNo)
                    .addComponent(txtContact)
                    .addComponent(txtNeedToPay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNeedToPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Add House Rooms"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        dashboard.setText("dashboard");
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });

        addroom.setText("Add Room");
        addroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addroomActionPerformed(evt);
            }
        });

        btnSearch.setText("search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        PaymentRecords.setText("Payment Records");
        PaymentRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentRecordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(dashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addroom)
                .addGap(18, 18, 18)
                .addComponent(PaymentRecords)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dashboard)
                            .addComponent(addroom)
                            .addComponent(PaymentRecords))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(409, 409, 409))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Setup listener for room number field to auto-populate price
private void setupRoomNumberListener() {
    txtNo.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            loadPriceForRoom();
        }
    });
}

// Method to load price based on room number
private void loadPriceForRoom() {
    String roomNo = txtNo.getText().trim();
    
    if (roomNo.isEmpty()) {
        txtNeedToPay.setText("");
        return;
    }

    try {
        pst = con.prepareStatement("SELECT price FROM rooms WHERE room_number = ?");
        pst.setString(1, roomNo);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String price = rs.getString("price");
            txtNeedToPay.setText(price != null ? price : "");
        } else {
            txtNeedToPay.setText("");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

public void Table() {

    try {
        pst = con.prepareStatement("SELECT name, contact, houseno, payment, month FROM records");
        ResultSet Rs = pst.executeQuery();
        DefaultTableModel DFT = (DefaultTableModel) jTable1.getModel();
        DFT.setRowCount(0);

        while (Rs.next()) {
            Vector v2 = new Vector();
            v2.add(Rs.getString("name")); 
            v2.add(Rs.getString("contact"));
            v2.add(Rs.getInt("houseno"));
            v2.add(Rs.getString("payment"));
            v2.add(Rs.getString("month"));
            DFT.addRow(v2);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}



    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                        
    int hnoInt = 0;
    try {
        hnoInt = Integer.parseInt(txtNo.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid House No.");
        return;
    }

   String name = txtName.getText();
String contact = txtContact.getText();
String payment = txtNeedToPay.getText();
String month = (String) cmbMonth.getSelectedItem();

    // 🔍 1. CHECK IF ROOM EXISTS AND IS AVAILABLE
    try {
        pst = con.prepareStatement("SELECT status FROM rooms WHERE room_number = ?");
        pst.setInt(1, hnoInt);
        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            JOptionPane.showMessageDialog(this,
                "Room does NOT exist in AddRoom.\nPlease add this room first.",
                "Room Not Found",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        String status = rs.getString("status");
    

        if (!status.equalsIgnoreCase("Available")) {
            JOptionPane.showMessageDialog(this,
                "This room is NOT available.\nStatus: " + status,
                "Room Not Available",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 🔍 2. CHECK IF ROOM IS ALREADY ASSIGNED TO SOMEONE
        pst = con.prepareStatement("SELECT * FROM records WHERE houseno = ?");
        pst.setInt(1, hnoInt);
        ResultSet rs2 = pst.executeQuery();

        if (rs2.next()) {
            JOptionPane.showMessageDialog(this,
                "Room already has a tenant!",
                "Room Occupied",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ ROOM IS VALID → INSERT NEW TENANT
     // ✅ ROOM IS VALID → INSERT NEW TENANT
pst = con.prepareStatement(
    "INSERT INTO records (name, contact, houseno, payment, month) VALUES(?,?,?,?,?)"
);
pst.setString(1, name);
pst.setString(2, contact);
pst.setInt(3, hnoInt);
pst.setString(4, payment);
pst.setString(5, month);
     
        pst.executeUpdate();

        // OPTIONAL: Set room status to OCCUPIED automatically
        pst = con.prepareStatement("UPDATE rooms SET status='Occupied' WHERE room_number=?");
        pst.setInt(1, hnoInt);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this,"Record Added Successfully");

        Table();

       txtName.setText("");
txtContact.setText("");
txtNo.setText("");
txtNeedToPay.setText("");
cmbMonth.setSelectedIndex(0);
txtName.requestFocus();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void BupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BupdateActionPerformed

                                      

   String name = txtName.getText();
String contact = txtContact.getText();
String hno = txtNo.getText();
String payment = txtNeedToPay.getText();
String month = (String) cmbMonth.getSelectedItem();
    try {
        pst = con.prepareStatement(
           "UPDATE records SET name=?, contact=?, payment=?, month=? WHERE houseno=?"
);

pst.setString(1, name);
pst.setString(2, contact);
pst.setString(3, payment);
pst.setString(4, month);
pst.setString(5, hno);

        pst.executeUpdate();
        JOptionPane.showMessageDialog(this, "Record Updated");
        Table();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }


    }//GEN-LAST:event_BupdateActionPerformed

    private void BdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BdeleteActionPerformed
                                      

    String hno = txtNo.getText();

    int dialogResult = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete this record?",
        "Warning",
        JOptionPane.YES_NO_OPTION
    );

    if (dialogResult == JOptionPane.YES_OPTION) {
    try {
        // Set room back to Available when tenant is deleted
        pst = con.prepareStatement("UPDATE rooms SET status='Available' WHERE room_number=?");
        pst.setString(1, hno);
        pst.executeUpdate();

        pst = con.prepareStatement("DELETE FROM records WHERE houseno=?");
            pst.setString(1, hno);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Deleted");
            Table();
            
           txtName.setText("");
txtContact.setText("");
txtNo.setText("");
txtNeedToPay.setText("");
cmbMonth.setSelectedIndex(0);
              
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    }//GEN-LAST:event_BdeleteActionPerformed

    private void txtNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        this.dispose(); // Close the current HouseRent window
    new Dashboard().setVisible(true); // Open Dashboard window
    }//GEN-LAST:event_dashboardActionPerformed

    private void addroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addroomActionPerformed
                                               
    this.dispose(); // Close current window
    new AddRoom().setVisible(true); // Open AddRoom window


    }//GEN-LAST:event_addroomActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
                                             
    String searchValue = txtSearch.getText().trim();

    if (searchValue.equals("")) {
        Table(); 
        return;
    }

    try {
       pst = con.prepareStatement(
    "SELECT name, contact, houseno, payment, month FROM records " +
    "WHERE name LIKE ? OR contact LIKE ? OR houseno LIKE ?"

);

        pst.setString(1, "%" + searchValue + "%");
        pst.setString(2, "%" + searchValue + "%");
        pst.setString(3, "%" + searchValue + "%");

        ResultSet rs = pst.executeQuery();
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);

   while (rs.next()) {
    Vector v = new Vector();
    v.add(rs.getString("name"));
    v.add(rs.getString("contact"));
    v.add(rs.getString("houseno"));
    v.add(rs.getString("payment"));
    v.add(rs.getString("month"));
    dt.addRow(v);
}

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }


    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMonthActionPerformed

    private void PaymentRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentRecordsActionPerformed
       this.dispose(); // Close current window
    new PaymentRecords().setVisible(true);
    }//GEN-LAST:event_PaymentRecordsActionPerformed
private void openHouseRent() {
    // this.dispose(); // Remove or comment this line
    new HouseRent().setVisible(true); // Open HouseRent window (keeps Dashboard open)
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HouseRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HouseRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HouseRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HouseRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HouseRent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bdelete;
    private javax.swing.JButton Bupdate;
    private javax.swing.JButton PaymentRecords;
    private javax.swing.JButton addroom;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JButton dashboard;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNeedToPay;
    private javax.swing.JTextField txtNo;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
