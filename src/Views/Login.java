/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Views.Home.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.concurrent.*;
import databaseObjects.beans.PersonMVC.*;
import Views.forms.*;

/**
 *
 * @author xuelixiao
 */
public class Login extends  JFrame {
	
	private static PersonController loginController;
	
	private JFrame current;
	private PersonModel user;
	
    /**
     * Creates new form Login
     */
    public Login() {
    	
    	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

    	loginController = new PersonController();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        current = this;

    	jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	ConcurrentHashMap<String, String> attributes = new ConcurrentHashMap<String, String>();
            	if (jTextField1.getText() != null || new String(jPasswordField1.getPassword()) != null) {
	            	attributes.put("username", jTextField1.getText());
	            	attributes.put("password", new String(jPasswordField1.getPassword()));
	            	try {
	            		loginController.authenticate(attributes);
	            	} catch (Exception e) {
	            		PopUp error = new PopUp();
	            		error.setText(e.getMessage());
	            		error.setVisible(true);
	            		return;
	            	}
	                user = loginController.getUser();
	                if (user == null) {
	                	PopUp error = new PopUp();
	            		error.setText("invalid username and password");
	            		error.setVisible(true);
	                	attributes.remove("username");
	                	attributes.remove("password");
	                	jPasswordField1.setText("");
	                	return;
	                } else {
	                	loginController.addToPrevious(current);
	                	if (user.getTitle().equals("doctor")) {
	                		DoctorHome page = new DoctorHome(loginController);
	                		page.setVisible(true);
	                	} else {
	                		NurseHome page = new NurseHome(loginController);
	                		page.setVisible(true);
	                	}
	                }
            	}
           }
        });
    	
    	jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	new PasswordRecoveryForm(loginController).setVisible(true);
            	loginController.addToPrevious(current);
            	setVisible(false);
           }
        });
        initComponents();
        loginController.resetForwardAndBack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setMinimumSize(new java.awt.Dimension(480, 350));

        jButton2.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Forgot Password");
        getContentPane().add(jButton2);
        jButton2.setBounds(30, 260, 204, 30);

        jButton1.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 102, 102));
        jButton1.setText("Sign In");
        getContentPane().add(jButton1);
        jButton1.setBounds(30, 230, 204, 30);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("User Name");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 160, 80, 16);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 200, 70, 16);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(120, 150, 110, 28);

        jLabel3.setFont(new java.awt.Font("Krungthep", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("Onibaba");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 40, 250, 62);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(120, 190, 110, 28);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Screen Shot 2014-11-28 at 5.24.58 PM.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 470, 330);

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
