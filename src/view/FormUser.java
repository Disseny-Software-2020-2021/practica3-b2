package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.*;

public class FormUser extends JDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldUsername;
    private JLabel usernameLabel;
    private Controller controlador;
    private String client;
    private String usuari;


    public FormUser() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        setTitle("Usuari nou");
        controlador = Controller.getInstance();
        this.setLocationRelativeTo(null);
        initComponents();
    }

    public void setClient(String nom){
        this.client = nom;
    }


    private void initComponents() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRegister();
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

        textFieldUsername.requestFocusInWindow();
    }

    private void onRegister() {
        try {
            String username = textFieldUsername.getText();
            controlador.addUsuari(this.client, username, username);
            this.setUsuari(username);
            JOptionPane.showMessageDialog(this, "Usuari registrat correctament");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR AL REGISTRAR", JOptionPane.YES_NO_OPTION);
        }
    }

    private void onCancel() {
        dispose();
    }

    public String getUsuari(){
        return this.usuari;
    }

    public void setUsuari(String username){
        this.usuari = username;
    }
}
