/*------------------------------------------------------------------------------
 * Author:              Craig Gibson
 * Project:             Movie Store
 * File name:           Help Class
 * Date created:        16/11/2016
 * Operating System:    -Windows 7
 *                      -64 bit OS
 * 
 * Class Description :  This is the Help menu that gives information to the 
 *                      user of the program and gives hints as they go.
 -----------------------------------------------------------------------------*/
package vidstore.projectclient;

/**
 *
 * @author MB2013-0157
 */
public class Help extends javax.swing.JFrame {

    /**
     * Creates new form Help
     */
    public Help() {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        helpDesc = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        helpDesc.setColumns(20);
        helpDesc.setLineWrap(true);
        helpDesc.setRows(5);
        helpDesc.setText("Welcome to the help menu, this will explain how to operate the search bar, refresh button and then how to use admin privileges:\n\nNormal Usage:\nSearch Bar:\n\nType in the name of the movie/genre you wish to search for and press ‘Search’\nMake sure the ‘By Movie’ radio button is selected if you are searching for a movie, and vice versa for searching ‘By Genre’.\nSelect the movie you wish to view the description of, the description will pop up, in the description text box.\nWhen you are ready to go back to all of the movies on the database, press the ‘Refresh’ button this will bring back all of the movies in the database, along with their ID and description.\n\n\nAdmin Privileges:\n\nYou must log on with admin privileges before you can access the insert and delete buttons, otherwise they will remain “see through”.\n\nInsert button: When this is clicked it will bring up a window allowing you to type in   information with specifications that the movie name must always start with a capital letter the same goes for any more words the movie will have in the title. The genre ID is an ID that corresponds with a genre in the database, the description can be any amount of information on the movie.\n\nInserting a genre: If the genre exists then an error will be thrown, because there must be no duplicate values.\n\nDeleting a record: When you click this button it will delete any movie that is selected from the database. SO BE CAREFUL!\n");
        helpDesc.setWrapStyleWord(true);
        jScrollPane1.setViewportView(helpDesc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Help().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea helpDesc;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}