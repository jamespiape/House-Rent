import javax.swing.*;

import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
public class Dashboard extends javax.swing.JFrame {

       Connection con;
    PreparedStatement pst;

    // Labels for statistics
   
    public Dashboard() {
        
        
        setTitle("Dashboard");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Connect to database
        Connect();

        // Setup statistics labels
        setupLabels();

        // Setup buttons
        setupButtons();

        // Setup table
        setupTable();

        // Load data
        loadRoomStats();
        loadRoomTable();
    }

    private void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/houserent", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Database connection failed: " + ex.getMessage());
        }
    }

       private void setupLabels() {
        lblTotalRooms = new JLabel("Total Rooms: 0");
        lblTotalRooms.setBounds(50, 20, 150, 30);
        add(lblTotalRooms);

        lblAvailable = new JLabel("Available: 0");
        lblAvailable.setBounds(220, 20, 150, 30);
        add(lblAvailable);

        lblOccupied = new JLabel("Occupied: 0");
        lblOccupied.setBounds(390, 20, 150, 30);
        add(lblOccupied);

        lblMaintenance = new JLabel("Maintenance: 0");
        lblMaintenance.setBounds(560, 20, 150, 30);
        add(lblMaintenance);

        lblUnderRepair = new JLabel("Under Repair: 0");
        lblUnderRepair.setBounds(50, 60, 150, 30);
        add(lblUnderRepair);
    }

    private void setupButtons() {
        btnAddRoom = new JButton("Add Room");
        btnAddRoom.setBounds(220, 60, 120, 30);
        add(btnAddRoom);
        btnAddRoom.addActionListener(e -> {
            new AddRoom().setVisible(true);
            dispose();
        });

        btnHouseRent = new JButton("Go to Tenants");
        btnHouseRent.setBounds(390, 60, 150, 30);
        add(btnHouseRent);
        btnHouseRent.addActionListener(e -> {
            new HouseRent().setVisible(true);
            dispose();
        });

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(560, 60, 120, 30);
        add(btnRefresh);
        btnRefresh.addActionListener(e -> {
            loadRoomStats();
            loadRoomTable();
        });
    }

    private void setupTable() {
        tableRooms = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableRooms);
        scrollPane.setBounds(50, 110, 700, 400);
        add(scrollPane);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Room No", "Type", "Capacity", "Price", "Status"});
        tableRooms.setModel(model);
    }

    private void loadRoomStats() {
        try {
            Statement stmt = con.createStatement();

            ResultSet rsTotal = stmt.executeQuery("SELECT COUNT(*) FROM rooms");
            if (rsTotal.next()) lblTotalRooms.setText("Total Rooms: " + rsTotal.getInt(1));

            ResultSet rsAvailable = stmt.executeQuery("SELECT COUNT(*) FROM rooms WHERE status='Available'");
            if (rsAvailable.next()) lblAvailable.setText("Available: " + rsAvailable.getInt(1));

            ResultSet rsOccupied = stmt.executeQuery("SELECT COUNT(*) FROM rooms WHERE status='Occupied'");
            if (rsOccupied.next()) lblOccupied.setText("Occupied: " + rsOccupied.getInt(1));

            ResultSet rsMaint = stmt.executeQuery("SELECT COUNT(*) FROM rooms WHERE status='Maintenance'");
            if (rsMaint.next()) lblMaintenance.setText("Maintenance: " + rsMaint.getInt(1));

            ResultSet rsRepair = stmt.executeQuery("SELECT COUNT(*) FROM rooms WHERE status='Under Repair'");
            if (rsRepair.next()) lblUnderRepair.setText("Under Repair: " + rsRepair.getInt(1));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadRoomTable() {
        try {
            pst = con.prepareStatement("SELECT room_number, room_type, capacity, price, status FROM rooms");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tableRooms.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                    rs.getString("room_number"),
                    rs.getString("room_type"),
                    rs.getString("capacity"),
                    rs.getString("price"),
                    rs.getString("status")
                };
                model.addRow(row);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAvailable = new javax.swing.JLabel();
        lblTotalRooms = new javax.swing.JLabel();
        lblUnderRepair = new javax.swing.JLabel();
        lblMaintenance = new javax.swing.JLabel();
        lblOccupied = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRooms = new javax.swing.JTable();
        btnHouseRent = new javax.swing.JButton();
        btnAddRoom = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAvailable.setText("Available");

        lblTotalRooms.setText("Total Rooms");

        lblUnderRepair.setText("UnderRepair");

        lblMaintenance.setText("Maintenance");

        lblOccupied.setText("Occupied");

        tableRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableRooms);

        btnHouseRent.setText("HouseRent");
        btnHouseRent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHouseRentActionPerformed(evt);
            }
        });

        btnAddRoom.setText("AddRoom");
        btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblTotalRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(lblAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(lblOccupied, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(btnAddRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(163, 163, 163)
                                .addComponent(btnHouseRent, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addComponent(lblUnderRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(187, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOccupied, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnderRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHouseRent, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddRoomActionPerformed

    private void btnHouseRentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHouseRentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHouseRentActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnHouseRent;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvailable;
    private javax.swing.JLabel lblMaintenance;
    private javax.swing.JLabel lblOccupied;
    private javax.swing.JLabel lblTotalRooms;
    private javax.swing.JLabel lblUnderRepair;
    private javax.swing.JTable tableRooms;
    // End of variables declaration//GEN-END:variables
}
