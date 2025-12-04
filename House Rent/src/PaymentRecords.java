import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
public class PaymentRecords extends javax.swing.JFrame {

     
    Connection con;
    PreparedStatement pst;

   
    public PaymentRecords() {
        initComponents();
        
         Connect();
        setupComponents();
        loadPaymentRecords();
    }
    
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/houserent", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentRecords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setupComponents() {
        // Setup table columns
        DefaultTableModel model = (DefaultTableModel) jTablePayments.getModel();
        model.setColumnIdentifiers(new String[]{"Payment ID", "Name", "Room No", "Amount Paid", "Payment Type", "Remaining", "Month", "Date"});
        
        // Setup payment type combo box
        cmbPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(
            new String[] { "Full Payment", "Down Payment" }
        ));
        
        // Create payments table if not exists
        createPaymentsTable();
        
        // Load tenant info on room number change
        txtRoomNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                loadTenantInfo();
            }
        });
    }
    
    private void createPaymentsTable() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS payments (" +
                "payment_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "tenant_name VARCHAR(100), " +
                "room_number VARCHAR(50), " +
                "amount_paid DECIMAL(10,2), " +
                "payment_type VARCHAR(50), " +
                "remaining_balance DECIMAL(10,2), " +
                "month VARCHAR(20), " +
                "payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
            
            Statement stmt = con.createStatement();
            stmt.execute(createTableSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadTenantInfo() {
        String roomNo = txtRoomNo.getText().trim();
        
        if (roomNo.isEmpty()) {
            txtName.setText("");
            txtTotalRent.setText("");
            txtRemainingBalance.setText("");
            return;
        }
        
        try {
            // Get tenant info from records
            pst = con.prepareStatement("SELECT name, payment, month FROM records WHERE houseno = ?");
            pst.setString(1, roomNo);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                txtName.setText(rs.getString("name"));
                txtTotalRent.setText(rs.getString("payment"));
                cmbMonth.setSelectedItem(rs.getString("month"));
                
                // Calculate remaining balance
                calculateRemainingBalance(roomNo, rs.getString("payment"));
            } else {
                JOptionPane.showMessageDialog(this, "No tenant found for this room number");
                txtName.setText("");
                txtTotalRent.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void calculateRemainingBalance(String roomNo, String totalRent) {
        try {
            double total = Double.parseDouble(totalRent);
            
            // Get total paid amount for this room
            pst = con.prepareStatement(
                "SELECT SUM(amount_paid) as total_paid FROM payments WHERE room_number = ?"
            );
            pst.setString(1, roomNo);
            ResultSet rs = pst.executeQuery();
            
            double totalPaid = 0;
            if (rs.next()) {
                totalPaid = rs.getDouble("total_paid");
            }
            
            double remaining = total - totalPaid;
            txtRemainingBalance.setText(String.format("%.2f", remaining));
            
        } catch (NumberFormatException | SQLException ex) {
            txtRemainingBalance.setText("0.00");
        }
    }
    
    private void loadPaymentRecords() {
        try {
            pst = con.prepareStatement(
                "SELECT payment_id, tenant_name, room_number, amount_paid, payment_type, " +
                "remaining_balance, month, payment_date FROM payments ORDER BY payment_date DESC"
            );
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) jTablePayments.getModel();
            model.setRowCount(0);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("payment_id"),
                    rs.getString("tenant_name"),
                    rs.getString("room_number"),
                    String.format("%.2f", rs.getDouble("amount_paid")),
                    rs.getString("payment_type"),
                    String.format("%.2f", rs.getDouble("remaining_balance")),
                    rs.getString("month"),
                    sdf.format(rs.getTimestamp("payment_date"))
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading payment records: " + ex.getMessage());
        }
    }
    
    

        
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtRemainingBalance = new javax.swing.JTextField();
        txtRoomNo = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtTotalRent = new javax.swing.JTextField();
        txtAmountPaid = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnProcessPayment = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePayments = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        cmbPaymentType = new javax.swing.JComboBox<>();
        cmbMonth = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Payment Records");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Room No");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Total Rent");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Amount to Pay:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Tenant Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Month");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Remaining Balance:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Payment Type");

        txtRemainingBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRemainingBalanceActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Search");

        btnProcessPayment.setText("ProcessPayment");
        btnProcessPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessPaymentActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jTablePayments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "nul", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane1.setViewportView(jTablePayments);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        cmbPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Full Payment", "Down Payment" }));

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnProcessPayment)
                                .addGap(49, 49, 49)
                                .addComponent(btnRefresh)
                                .addGap(58, 58, 58)
                                .addComponent(btnBack))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(btnClear)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTotalRent, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAmountPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtRemainingBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalRent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAmountPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRemainingBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProcessPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRemainingBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRemainingBalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRemainingBalanceActionPerformed

    private void btnProcessPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessPaymentActionPerformed
         if (txtRoomNo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Room Number");
            return;
        }
        
        if (txtAmountPaid.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Amount to Pay");
            return;
        }
        
        try {
            String roomNo = txtRoomNo.getText().trim();
            String tenantName = txtName.getText().trim();
            double totalRent = Double.parseDouble(txtTotalRent.getText().trim());
            double amountPaid = Double.parseDouble(txtAmountPaid.getText().trim());
            String paymentType = (String) cmbPaymentType.getSelectedItem();
            String month = (String) cmbMonth.getSelectedItem();
            
            // Validate amount
            double currentRemaining = Double.parseDouble(txtRemainingBalance.getText().trim());
            
            if (amountPaid <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0");
                return;
            }
            
            if (amountPaid > currentRemaining) {
                JOptionPane.showMessageDialog(this, 
                    "Amount exceeds remaining balance!\nRemaining: " + currentRemaining);
                return;
            }
            
            // Calculate new remaining balance
            double newRemaining = currentRemaining - amountPaid;
            
            // Insert payment record
            pst = con.prepareStatement(
                "INSERT INTO payments (tenant_name, room_number, amount_paid, payment_type, " +
                "remaining_balance, month) VALUES (?, ?, ?, ?, ?, ?)"
            );
            
            pst.setString(1, tenantName);
            pst.setString(2, roomNo);
            pst.setDouble(3, amountPaid);
            pst.setString(4, paymentType);
            pst.setDouble(5, newRemaining);
            pst.setString(6, month);
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, 
                "Payment Processed Successfully!\n" +
                "Amount Paid: " + amountPaid + "\n" +
                "Remaining Balance: " + String.format("%.2f", newRemaining),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Refresh data
            loadPaymentRecords();
            clearFields();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for amount");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error processing payment: " + ex.getMessage());
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnProcessPaymentActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
         loadPaymentRecords();
        txtSearch.setText("");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
          this.dispose();
        new HouseRent().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
         String searchValue = txtSearch.getText().trim();
        
        if (searchValue.isEmpty()) {
            loadPaymentRecords();
            return;
        }
        
        try {
            pst = con.prepareStatement(
                "SELECT payment_id, tenant_name, room_number, amount_paid, payment_type, " +
                "remaining_balance, month, payment_date FROM payments " +
                "WHERE tenant_name LIKE ? OR room_number LIKE ? OR month LIKE ? " +
                "ORDER BY payment_date DESC"
            );
            
            pst.setString(1, "%" + searchValue + "%");
            pst.setString(2, "%" + searchValue + "%");
            pst.setString(3, "%" + searchValue + "%");
            
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) jTablePayments.getModel();
            model.setRowCount(0);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("payment_id"),
                    rs.getString("tenant_name"),
                    rs.getString("room_number"),
                    String.format("%.2f", rs.getDouble("amount_paid")),
                    rs.getString("payment_type"),
                    String.format("%.2f", rs.getDouble("remaining_balance")),
                    rs.getString("month"),
                    sdf.format(rs.getTimestamp("payment_date"))
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
         clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

     private void clearFields() {
        txtRoomNo.setText("");
        txtName.setText("");
        txtTotalRent.setText("");
        txtAmountPaid.setText("");
        txtRemainingBalance.setText("");
        cmbPaymentType.setSelectedIndex(0);
        cmbMonth.setSelectedIndex(0);
        txtRoomNo.requestFocus();
    }

    
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
            java.util.logging.Logger.getLogger(PaymentRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnProcessPayment;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JComboBox<String> cmbPaymentType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePayments;
    private javax.swing.JTextField txtAmountPaid;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtRemainingBalance;
    private javax.swing.JTextField txtRoomNo;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalRent;
    // End of variables declaration//GEN-END:variables
}
