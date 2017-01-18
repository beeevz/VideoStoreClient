/*------------------------------------------------------------------------------
 * Author:              Craig Gibson
 * Project:             Movie Store
 * File name:           Client Class
 * Date created:        16/11/2016
 * Operating System:    -Windows 7
 *                      -64 bit OS
 * 
 * Class Description :  This is the package that is run as soon as somebody 
 *                      executes the Client code/jar file, with all of the 
 *                      methods that are called from other classes in the
 *                      package.
 -----------------------------------------------------------------------------*/
package vidstore.projectclient;

import java.util.*;
import javax.swing.table.*;
import javax.swing.*;
import org.apache.xmlrpc.*;
import java.net.*;

/**
 *
 * @author MB2013-0157(Craig Gibson)
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static XmlRpcClient server;
    /**
     *
     */
    public Client clMethods;

    //
    /**
     * Creating the method to interact with the server to receive the
     * description of the movie.
     *
     * @param int row
     * @return
     */
    public String Selection(int row) {
        String desc = new String();
        Vector param = new Vector();
        param.addElement(row);
        try {
            Vector<String> returned = (Vector) server.execute("sample."
                    + "selectConnect", param);

            Iterator itrReturned = returned.iterator();

            if (itrReturned.hasNext()) {
                desc = (String) itrReturned.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desc;
    }

    /**
     * This method takes action when the search button is pressed and populates
     * the database with any records that contain the order of letters in the
     * search bar.
     *
     * @param String sch
     * @param ClientStartupGUI cl
     * @param String schOption
     */
    public void search(String sch, ClientStartupGUI cl, String schOption) {
        try {
            //Very much similar to the other checks in the database, difference
            //is that it returns a specific table Vector
            Vector param1 = new Vector();
            param1.addElement(sch);
            param1.addElement(schOption);

            Vector schdTable = (Vector) server.execute("sample.schdTable",
                    param1);

            Vector vctTable = (Vector) schdTable;

            Iterator itrTable = vctTable.iterator();

            DefaultTableModel mdl = new DefaultTableModel();

            mdl.addColumn("Movie ID");
            mdl.addColumn("Movie Name");
            mdl.addColumn("Genre");
            mdl.setNumRows(0);
            if (!itrTable.hasNext()) {
                JOptionPane.showMessageDialog(null, "No videos/"
                        + "genres in the database exist with that name,");
            } else {
                while (itrTable.hasNext()) {
                    Vector rw = (Vector) itrTable.next();
                    mdl.addRow(rw);
                }
                cl.movieTable.setModel(mdl);
                cl.setVisible(true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This opens the login window to allow the user to log in as an admin and
     * insert or delete records.
     *
     * @param ClientStartupGUI cls
     */
    public void newAdminGUI(ClientStartupGUI cls) {
        clMethods = new Client();
        adminEditGUI ad = new adminEditGUI(clMethods, cls);
        ad.setVisible(true);
    }

    /**
     * This checks the database to see if the values entered on the GUI exist on
     * the database.
     *
     * @param String name
     * @param String pass
     * @return
     */
    public boolean logon(String name, String pass) {
        boolean qAccess;
        try {
            String query = "SELECT * FROM adminUsers WHERE user_name = "
                    + "'" + name + "' AND password = '" + pass + "'";
            Vector param = new Vector();
            param.add(query);
            Vector result = (Vector) server.execute("sample.detCheck", param);

            Vector vctResult = (Vector) result;

            Iterator itrResult = vctResult.iterator();

            if (itrResult.hasNext()) {
                qAccess = (boolean) itrResult.next();
                if (qAccess == true) {
                    return qAccess;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        qAccess = false;
        return qAccess;
    }

    /**
     * This starts the admin rights, and enables the buttons to insert and
     * delete records.
     *
     * @param ClientStartupGUI clsGUI
     */
    public void enableAdmin(ClientStartupGUI clsGUI) {
        clsGUI.recDelete.setEnabled(true);
        clsGUI.recInsert.setEnabled(true);
        clsGUI.adLogin.setEnabled(false);
        clsGUI.adLogout.setEnabled(true);
        clsGUI.jMenuItem1.setEnabled(true);
        clsGUI.jMenuItem2.setEnabled(true);
        clsGUI.jMenuItem3.setEnabled(true);
    }

    /**
     * This is for when the admin is finished and wishes to log out of the admin
     * privileges.
     *
     * @param ClientStartupGUI clsGUI
     */
    public void disableAdmin(ClientStartupGUI clsGUI) {
        clsGUI.recDelete.setEnabled(false);
        clsGUI.recInsert.setEnabled(false);
        clsGUI.adLogin.setEnabled(true);
        clsGUI.adLogout.setEnabled(false);
        clsGUI.jMenuItem1.setEnabled(false);
        clsGUI.jMenuItem2.setEnabled(false);
        clsGUI.jMenuItem3.setEnabled(false);

    }

    /**
     * This is the method that inserts values into the database.
     *
     * @param movName
     * @param genNum
     * @param Desc
     */
    public void insertRec(String movName, int genNum, String Desc) {
        Vector param = new Vector();
        param.add(movName);
        param.add(genNum);
        param.add(Desc);
        try {
            //This allows us to iterate through the values in the Vector
            //that has been sent back to the caller of the method.
            Vector objInsertStatus = (Vector) server.execute("sample."
                    + "insertMovies", param);
            Vector vctInsertStatus = (Vector) objInsertStatus;
            Iterator itrInsertStatus = vctInsertStatus.iterator();

            int choice = (int) itrInsertStatus.next();
            //This switch case allows us to validate the values entered and
            //throw an error if they are not unique.
            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(null,
                            "Successfully inserted!\n Refresh Search for "
                            + "database to take effect.", "Inserted",
                            JOptionPane.PLAIN_MESSAGE);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "The Movie"
                            + " name entered is already inserted into the "
                            + "database\n please double check this is not the "
                            + "movie you are trying to enter and try again",
                            "Failed", JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "The Genre"
                            + " code entered does not exist", "Failed",
                            JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the method that allows us to insert a genre to the database and
     * call it in the future. it also checks to see if the genre is already in
     * the database.
     *
     * @param String genName
     */
    public void insertGen(String genName) {
        Vector param = new Vector();
        param.add(genName);
        try {
            //The vector received from the method contains a boolean value that
            //indicates whether or not the value is contained in the database.
            Vector objInsertGenre = (Vector) server.execute("sample."
                    + "insertGenres", param);
            Vector vctInsertGenre = (Vector) objInsertGenre;
            Iterator itrInsertGenre = vctInsertGenre.iterator();
            boolean success = (boolean) itrInsertGenre.next();
            if (success) {
                JOptionPane.showMessageDialog(null,
                        "Successfully inserted!", "Inserted",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Insertion unsuccessful, the genre inserted\n"
                        + "already exists in the database", "Inserted",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method calls to delete a record from the database,
     *
     * @param movID
     */
    public void deleteRec(int movID) {
        Vector param = new Vector();
        param.add(movID);
        try {
            Vector objDltStatus = (Vector) server.execute("sample.deleteMovies",
                    param);
            Vector vctDltStatus = (Vector) objDltStatus;
            Iterator itrDltStatus = vctDltStatus.iterator();
            boolean dltSuccess = (boolean) itrDltStatus.next();
            if (dltSuccess) {
                JOptionPane.showMessageDialog(null, "Deletion "
                        + "Successful!\n Refresh Search for "
                        + "database to take effect.", "Deleted",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Unsuccessful "
                        + "deletion", "Unsuccessful",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Unsuccessful "
                    + "deletion", "Unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * This is the main method that will start when the Client class is run.
     *
     * @param String[] args
     */
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Please "
                + "enter the host name of the server you wish to connect to.");
        //Creating a window that displays a please wait message.
        JDialog dialog = new JDialog();
        try {
            //This setting up an InetAddress and giving it a value of 
            //"localhost"
            InetAddress i = InetAddress.getByName("localhost");
            String hn = i.getHostName();
            while (!input.equals(hn)) {
                input = JOptionPane.showInputDialog(null, "Wrong server, "
                        + "please enter the host name of the server you wish "
                        + "to connect to.");
            }
            //Creating a window that displays a please wait message.
            JLabel label = new JLabel("Please Wait...");
            dialog.setLocationRelativeTo(null);
            dialog.setTitle("Please Wait...");
            dialog.add(label);
            dialog.setSize(100, 100);
            dialog.setVisible(true);

            //Open the ClientStartup window and set up a conenction.
            Client client = new Client();
            ClientStartupGUI cls = new ClientStartupGUI(client);

            //Connecting to the server
            server = new XmlRpcClient("http://localhost:8080/");

            //Send the parameters to the server using a vector
            Vector params = new Vector();
            params.addElement(3);

            //Execute method on the server
            Vector objBuildResult = (Vector) server.execute("sample.tableBuild",
                    params);

            //Cast back to a Vector
            Vector vctBuildResult = (Vector) objBuildResult;

            //Create an iterator to go through what the vector contains
            Iterator itrBuildResult = vctBuildResult.iterator();

            //Create a default table model to allow us to display the results
            DefaultTableModel model = new DefaultTableModel();

            //Adding the columns to the table
            model.addColumn("Movie ID"); // add the proper column names here
            model.addColumn("Movie Title");
            model.addColumn("Genre");
            model.setNumRows(0);

            //This populates the table by adding all of the specified records to
            //the model table
            while (itrBuildResult.hasNext()) {
                //Extracting each row from the iterator
                Vector row = (Vector) itrBuildResult.next();
                model.addRow(row);
            }
            //This is setting up the table that must be passed to the Client
            cls.movieTable.setModel(model);
            dialog.setVisible(false);
            cls.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            dialog.setVisible(false);
            JOptionPane.showMessageDialog(null, "The server seems "
                    + "to be down at the moment please try starting up the "
                    + "server and rerunning the client program.", "Cannot "
                    + "connect to the server", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}