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


    public FormUser() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        setTitle("LOG IN");
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
            String username = textFieldUsername.getName();
            controlador.addUsuari(this.client, username, username);
            JOptionPane.showMessageDialog(this, "Usuari registrat correctament");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onCancel() {
        dispose();
    }
}
