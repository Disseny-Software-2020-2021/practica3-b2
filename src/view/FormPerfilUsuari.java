package view;

/* Interfície Gràfica desenvolupada per: Nils Ballús, Joan Cano, David Rial i Miquel Guiot */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Formulari del Perfil d'usuari. Aquesta classe permet veure el perfil de l'usuari, el seus Followings i els seus
 * Followers i deixa fer un post: obrint un altre formulari
 */
public class FormPerfilUsuari extends JFrame {

    private JPanel jPanel;
    private JPanel Following;
    private JPanel Followers;
    private JList listFollowing;
    private JList listFollowers;
    private JButton btnTancarSessio;
    private JPanel Posts;
    private JTabbedPane llistes;
    private JList listPost;
    private JList listPostRebuts;
    private JPanel PostRebuts;
    private JButton ferPostButton;
    private JPanel jpanelRoot;


    /**
     * Constructor de la classe PerfilUsuari que crida initComponents()
     */
    protected FormPerfilUsuari(){
        setContentPane(jpanelRoot);
       // setModal(true);
        getRootPane().setDefaultButton(btnTancarSessio);
        setResizable(false);

        setTitle("Detall del perfil d'usuari");
        this.setLocationRelativeTo(null);
        initComponents();
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de l'APP UBFlix-Party i
     * s'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     */
    private void initComponents(){
        setSize(358,399);
        setMinimumSize(new Dimension(358,314));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        btnTancarSessio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        ferPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFerPost();
            }
        });

    }

    private void onFerPost() {
        FerPost view = new FerPost();
        view.pack();
        view.setVisible(true);
    }


    private void userActionPerformed() {
        FormUser dialog = new FormUser();
        dialog.pack();
        dialog.setVisible(true);
    }


    /**
     * Mètode que es crida quan es prem el botó Cancel que tanca la finestra
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * Mètode que es crida quan s'obre la finestra i crida a ferLogIn()
     * @param evt event que es dóna a l'obrir l'aplicació
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        refreshListAll();
    }

    /**
     * Mètode que es crida quan es tanca la finestra, fet click sobre la 'x' amb missatge de confirmació de sortida.
     * @param evt event que es dóna al tancar la finestra
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        dispose();
    }

    /**
     * Mètode que actualitza les sèries del catàleg
     */
    private void refreshListAll() {
        refreshFollowing();
        refreshFollowers();
        refreshPosts();
        refreshPostRebuts();
    }
    /**
     * Mètode que actualitza tots els posts Rebuts
     */
    private void refreshPostRebuts() {
        String[] postsRebuts ={"post1:lol","post23:xD"};
        listPostRebuts.setListData(postsRebuts);
    }

    /**
     * Mètode que actualitza tots els posts enviats
     */
    private void refreshPosts() {
        String[] posts ={"post1:mmmmm", "post2:blablabla @usuari11"};
        listPost.setListData(posts);
        listPost.setVisible(true);
    }

    /**
     * Mètode que actualitza la llista de Followers
     */
    private void refreshFollowers() {
        String[] followers = {"usuari 11","usuari 22", "usuari 33"};
        listFollowers.setListData(followers);
        listFollowers.setVisible(true);
    }
    /**
     * Mètode que actualitza la llista de Following
     */
    private void refreshFollowing() {
        String[] following = {"usuari 1","usuari 2", "usuari 3"};
        listFollowing.setListData(following);
        listFollowing.setVisible(true);
    }
}
