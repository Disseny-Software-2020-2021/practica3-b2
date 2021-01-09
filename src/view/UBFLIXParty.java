package view;

/* Interfície Gràfica desenvolupada per: Nils Ballús, Joan Cano, David Rial i Miquel Guiot */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import controller.Controller;

/**
 * GUI bàsica de l'app UBFLIX on es mostraran les diferents llistes corresponent a cada client que hagi realitzat el Log In.
 * Aquesta classe hereta de JFrame i és la vista principal de l'aplicació.
 */
public class UBFLIXParty extends JFrame{

    private JPanel jPanel;
    private JTabbedPane llistes;
    private JPanel fieldAll;
    private JPanel fieldWatchNext;
    private JPanel fieldWatched;
    private JPanel fieldNotStarted;
    private JList listAll;
    private JList listMyList;
    private JList listWatched;
    private JList listContinueWatching;
    private JTable tableTopVisualitzacions;
    private JTable tableTopValoracions;
    private JLabel labelTopVisualitzacions;
    private JLabel labelTopValoracions;
    private JButton btnTancarSessio;
    private JButton btnCrearUsuari;
    private JButton btnAfegirMyList;
    private JComboBox comboBoxUsuaris;
    private JButton perfilButton;
    //Afegits manualment
    private HashMap<String, JPopupMenu> popupMenuTemporades;
    private DefaultTableModel tableModelVis;
    private DefaultTableModel tableModelVal;
    private Controller controlador;
    private String NomClient;


    /**
     * Constructor de la classe UBFLIX que crida initComponents()
     */
    public UBFLIXParty() {
        super("UBFLIXParty");
        this.setLocation(30, 30);
        this.setVisible(true);
        initComponents();

    }

    /**
     * Mètode que inicialitza tots els components de la GUI de l'APP UBFLIXParty i s'afegeixen els listeners dels events
     * per quan es fa l'acció sobre els diferents components de Java.
     */
    private void initComponents(){
        add(jPanel);
        setSize(800,700);
        setMinimumSize(new Dimension(800,700));
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
                ferLogIn();
            }
        });
        btnAfegirMyList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jPanel, "Serie afegida a My List!");
            }
        });
        btnCrearUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userActionPerformed();
            }
        });
        perfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPerfil();
            }
        });
        listAll.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarPopupMenuTemporades(listAll, fieldAll);
            }
        });
        listMyList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarPopupMenuTemporades(listMyList, fieldWatchNext);
            }
        });
        listWatched.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarPopupMenuTemporades(listWatched, fieldWatched);
            }
        });
        listContinueWatching.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarPopupMenuTemporades(listContinueWatching, fieldNotStarted);
            }
        });
        popupMenuTemporades = new HashMap<>();
        comboBoxUsuaris.addItem("Manuel");
        inicialitzarLlistaTopVisualitzacions();
        inicialitzarLlistaTopValoracions();
        controlador = Controller.getInstance();
    }

    private void mostrarPerfil() {
        FormPerfilUsuari perfil = new FormPerfilUsuari();
        perfil.pack();
        perfil.setVisible(true);
    }

    /**
     * Mètode que serveix per inicialitzar el Top10 de sèries més visualitzades
     */
    private void inicialitzarLlistaTopVisualitzacions() {
        tableModelVis = new DefaultTableModel(new Object[][]{}, new Object[]{"nomSerie","visualitzacions"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTopVisualitzacions.setModel(tableModelVis);
        tableTopVisualitzacions.setShowGrid(false);
        tableTopVisualitzacions.setFocusable(false);
        tableTopVisualitzacions.setRowSelectionAllowed(false);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableTopVisualitzacions.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }

    private void userActionPerformed() {
        FormUser dialog = new FormUser();
        dialog.setClient(this.NomClient);
        dialog.pack();
        dialog.setVisible(true);

    }

    /**
     * Mètode que serveix per inicialitzar el top10 de sèries més ben valorades
     */
    private void inicialitzarLlistaTopValoracions() {
        tableModelVal = new DefaultTableModel(new Object[][]{}, new Object[]{"nomSerie","valoracio"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTopValoracions.setModel(tableModelVal);
        tableTopValoracions.setFocusable(false);
        tableTopValoracions.setRowSelectionAllowed(false);
        tableTopValoracions.setShowGrid(false);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableTopValoracions.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }

    /**
     * Mètode que serveix per mostrar les temporades disponibles d'una sèrie concreta
     * @param llista llista que conté la sèrie seleccionada
     * @param panel panel de la vista que conté les llistes
     */
    private void mostrarPopupMenuTemporades(JList llista, JPanel panel) {
        int index = llista.getSelectedIndex();
        Object element = llista.getSelectedValue();
        llista.removeSelectionInterval(llista.getLastVisibleIndex()+1, llista.getLastVisibleIndex()+1);
        if (popupMenuTemporades.get(llista.getSelectedValue()) != null)
            mostrarTemporades(element, index, panel);
    }

    /**
     * Mètode que mostra les diferents temporades de cada sèrie
     * @param serie sèrie de la qual s'han de mostrar les temporades
     * @param index índex de la sèrie seleccionada
     */
    private void mostrarTemporades(Object serie, int index, JPanel panel) {
        String s = (String) serie;
        JPopupMenu pm = popupMenuTemporades.get(s);
        pm.show(panel, panel.getWidth()/2, index*18);
    }

    /**
     * Mètode que fa visible el formulari de LogIn
     */
    private void ferLogIn() {
        jPanel.setVisible(false);
        FrmLogIn dialog = new FrmLogIn();
        dialog.pack();
        dialog.setVisible(true);
        jPanel.setVisible(true);
        refreshLlistes();
        NomClient = dialog.getClient();
    }

    /**
     * Mètode que es crida quan s'obre la finestra i crida a ferLogIn()
     * @param evt event que es dóna a l'obrir l'aplicació
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        refreshListAll();
        ferLogIn();
    }

    /**
     * Mètode que es crida quan es tanca la finestra, fet click sobre la 'x' amb missatge de confirmació de sortida.
     * @param evt event que es dóna al tancar la finestra
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "VOLS SORTIR? ", "SORTIR APP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            System.exit(0);
    }

    /**
     * Mètode que actualitza les sèries del catàleg
     */
    private void refreshListAll() {
        String[] series = controlador.catalegSeries().toArray(new String[0]);
        listAll.setListData(series);
        refreshTemporades(series);
    }

    /**
     * Mètode que serveix per actualitzar les temporades de les sèries passades per paràmetres
     * @param series sèries de les quals s'han d'actualitzar les temporades
     */
    private void refreshTemporades(String[] series) {
        popupMenuTemporades.clear();
        for (String serie: series) {
            JPopupMenu s = new JPopupMenu();
            String[] temporades = controlador.getTemporades(serie).toArray(new String[0]);
            for (String temporada: temporades) {
                JMenu temp = new JMenu(temporada);
                refreshEpisodis(serie, temporada, temp);
                s.add(temp);
            }
            popupMenuTemporades.put(serie, s);
        }
    }

    /**
     * Mètode que serveix per actualitzar els episodis de la temporada de la sèrie indicats per paràmetres
     * @param serie sèrie de la qual s'ha d'actualitzar els episodis
     * @param temporada temporadad de la qual s'ha d'actualitzar els episodis
     * @param jm JMenu de l'episodi
     */
    private void refreshEpisodis(String serie, String temporada, JMenu jm) {
        String[] episodis = controlador.getEpisodis(serie, temporada).toArray(new String[0]);
        int cont = 1;
        for (String episodi: episodis) {
            String idSerie = serie;
            int numTemporada = cont;
            int duracio = controlador.getMinTotals(serie,temporada,episodi);
            int duracioVisualitzada = 0;
            String descripcio = controlador.mostrarDetallsSerie(serie);
            JMenuItem ep = new JMenuItem(episodi);
            ep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onEpisodi(idSerie, numTemporada, episodi, duracio, duracioVisualitzada, descripcio);
                }
            });
            jm.add(ep);
            cont++;
        }
    }

    /**
     * Mètode que serveix per actualitzar totes les llistes de la vista
     */
    private void refreshLlistes() {
        refreshWatched();
        refreshMyList();
        refreshContinueWatching();
        refreshTopValoracions();
        refreshTopVisualitzacions();
    }

    /**
     * Mètode que actualitza les sèries de la llista MyList
     */
    private void refreshMyList() {
        String[] series = controlador.getMyList("ajaleo",comboBoxUsuaris.getSelectedItem().toString()).toArray(new String[0]);
        listMyList.setListData(series);
    }

    /**
     * Mètode que actualitza les sèries de la llista Watched
     */
    private void refreshWatched() {
        String[] series = {"serie 21", "serie 22", "serie 23"};
        listWatched.setListData(series);
    }

    /**
     * Mètode que actualitza les sèries de la llista ContinueWatching
     */
    private void refreshContinueWatching() {
        String[] series = {"serie 31", "serie 32", "serie 33"};
        listContinueWatching.setListData(series);
    }

    /**
     * Mètode que actualitza les sèries del top10 de sèries més ben valorades
     */
    private void refreshTopValoracions() {
        //Fem el clear de la llista del top 10 de valoracions
        int numRows = tableModelVal.getRowCount();
        for (int i = numRows - 1; i >= 0; i--)
            tableModelVal.removeRow(i);
        String [] topTenVal = {"serie 1", "serie 2", "serie 3", "serie 4", "serie 5", "serie 6", "serie 7", "serie 8", "serie 9", "serie 10"};
        for (String serie: topTenVal) {
            tableModelVal.addRow(new String[]{serie, String.format("%.2f", 5.7)});
        }
    }

    /**
     * Mètode que actualitza les sèries del top10 de sèries més visualitzades
     */
    private void refreshTopVisualitzacions() {
        //Fem el clear de la llista del top 10 de visualitzacions
        int numRows = tableModelVis.getRowCount();
        for (int i = numRows - 1; i >= 0; i--)
            tableModelVis.removeRow(i);

        String [] topTenVisualitzacions = {"serie 1", "serie 2", "serie 3", "serie 4", "serie 5", "serie 6", "serie 7", "serie 8", "serie 9", "serie 10"};
        for (String serie: topTenVisualitzacions) {
            tableModelVis.addRow(new Object[]{serie, 10});
        }
    }

    /**
     * Mètode que serveix per obrir la finestra amb tota la informació i opcions disponibles d'un episodi seleccionat
     * @param idSerie identificador de la sèrie de l'episodi
     * @param temporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     * @param duracioVisualitzada duració ja visualitzada pel client de l'episodi seleccionat
     * @param descripcio descripció de l'episodi seleccionat
     */
    private void onEpisodi(String idSerie, int temporada, String episodi, int duracio, int duracioVisualitzada, String descripcio) {
        FormEpisodi dialog = new FormEpisodi(idSerie, temporada, episodi, duracio, descripcio);
        dialog.pack();
        dialog.setVisible(true);
    }


}
