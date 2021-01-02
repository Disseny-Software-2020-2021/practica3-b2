package view;

import javax.swing.*;
import java.awt.event.*;

public class FerPost extends JDialog {
    private JTextArea AreaMissatge;
    private JButton sendButton;
    private JButton cancelButton;
    private JPanel jpanel;

    protected FerPost() {
        setContentPane(jpanel);
        setModal(true);
        getRootPane().setDefaultButton(cancelButton);

        initComponents();
        setResizable(true);
        setTitle("FER POST");
        this.setLocationRelativeTo(null);

    }

    private void initComponents() {
        setSize(250,300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPost();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
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
        jpanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        AreaMissatge.requestFocusInWindow();
    }

    private void onCancel() {
        dispose();
    }

    private void onPost() {
        String estat = AreaMissatge.getText();
        JOptionPane.showMessageDialog(jpanel, estat);
        dispose();
    }
}
