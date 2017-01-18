/*------------------------------------------------------------------------------
 * Author:              Craig Gibson
 * Project:             Movie Store
 * File name:           adminEditGUI
 * Date created:        16/11/2016
 * Operating System:    -Windows 7
 *                      -64 bit OS
 * 
 * Class Description :  This is the class that allows us to be able to log in
 *                      and edit the infomation on the database. This checks
 *                      to see if the login details the client entered are 
 *                      correct against the database.
 -----------------------------------------------------------------------------*/
package vidstore.projectclient;

import javax.swing.JOptionPane;

/**
 *
 * @author MB2013-0157(Craig Gibson)
 */
public class adminEditGUI extends javax.swing.JFrame {

    String username;
    String password;
    Client client;
    ClientStartupGUI cStGUI;

    /**
     * This is the constructor for the login window and allows the user to type
     * in the details.
     *
     * @param Client cl
     * @param ClientStartupGUI clGUI
     */
    public adminEditGUI(Client cl, ClientStartupGUI clGUI) {
        this.client = cl;
        this.cStGUI = clGUI;
        initComponents();
        this.getRootPane().setDefaultButton(logButton);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        instruLabel = new javax.swing.JLabel();
        uNameLabel = new javax.swing.JLabel();
        pWordLabel = new javax.swing.JLabel();
        uNameTB = new javax.swing.JTextField();
        logButton = new javax.swing.JButton();
        pWordTB = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Window");

        instruLabel.setText("Please Enter your username and password");

        uNameLabel.setText("Username:");

        pWordLabel.setText("Password:");

        logButton.setText("Log in");
        logButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(instruLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uNameLabel)
                            .addComponent(pWordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(uNameTB)
                            .addComponent(pWordTB, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instruLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uNameLabel)
                    .addComponent(uNameTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pWordLabel)
                    .addComponent(pWordTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logButton, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Check the database for the username and password to log in as admin
    private void logButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logButtonActionPerformed
        username = uNameTB.getText();
        password = pWordTB.getText();

        boolean access = client.logon(username, password);
        if(access == true) {
            client.enableAdmin(cStGUI);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Username or"
                    + " password incorrect",
                    "Login Error", JOptionPane.ERROR_MESSAGE);
            pWordTB.setText("");
        }
    }//GEN-LAST:event_logButtonActionPerformed
    /**
     * These are the components created every time the program is run.
     * 
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel instruLabel;
    private javax.swing.JButton logButton;
    private javax.swing.JLabel pWordLabel;
    private javax.swing.JPasswordField pWordTB;
    private javax.swing.JLabel uNameLabel;
    private javax.swing.JTextField uNameTB;
    // End of variables declaration//GEN-END:variables
}
