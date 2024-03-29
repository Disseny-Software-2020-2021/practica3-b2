package view;


import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Formulari d'inici de sessió de la APP, es demana l'usuari i la contrassenya. Aquesta classe hereta de JDialog
 */
class FrmLogIn extends JDialog {
    private JPanel contentPane;
    private JButton btnLogIn;
    private JButton buttonCancel;
    private JPasswordField textPassword;
    private JTextField textUsername;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JButton btnRegistrar;
    private Controller controlador;
    private String idClient;
    /**
     * Constructor de la finestra del LogIn on es fixa l'aspecte d'aquesta i s'inicialitzen els components
     */

    protected FrmLogIn() {
        this.controlador = Controller.getInstance();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnLogIn);
        initComponents();
        setResizable(false);
        this.setLocation(50, 50);
        setTitle("LOG IN");
    }

    public String getClient(){
        return this.idClient;
    }

    /**
     * Mètode que inicialitza tots els components de la GUI del LogIn i s'afegeixen els listeners dels events per quan es fa la acció sobre els botons.
     */

    private void initComponents() {
        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegistrar();
            }
        });
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Log In' per accedir al contingut en cas que l'usuari estigui registrat i la contrassenya sigui correcta.
     * En cas que salti una excepció es mostra per pantalla el missatge d'error corresponent.
     */
    private void onOK() {
        try {
            idClient = textUsername.getText();
            String psw = new String(textPassword.getPassword());
            if (this.controlador.LoginClient(idClient, psw)) {
                String info = "Log-in correcte";
                this.idClient = idClient;
                JOptionPane.showMessageDialog(this, info, "INFORMACIÓ LOG-IN", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else{
                String info = "Log-in incorrecte";
                textPassword.setText("");
                textUsername.setText("");
                JOptionPane.showMessageDialog(this, info, "INFORMACIÓ LOG-IN", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "FINESTRA ERROR", JOptionPane.YES_NO_OPTION);
        } catch (Exception e){
            textPassword.setText("");
            textUsername.setText("");
            JOptionPane.showMessageDialog(this, e.getMessage(), "FINESTRA ERROR", JOptionPane.YES_NO_OPTION);
        }
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Cancel' per tancar la finestra i alhora sortir de l'APP amb missatge de confirmació.
     */
    private void onCancel() {
        // add your code here if necessary
        if (JOptionPane.showConfirmDialog(this, "VOLS SORTIR? ", "SORTIR APP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            System.exit(0);
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Registrar' per obrir la finestra de registre d'usuari.
     */
    private void onRegistrar() {
        FrmRegistre dialog = new FrmRegistre();
        dialog.pack();
        dialog.setVisible(true);
    }

}
