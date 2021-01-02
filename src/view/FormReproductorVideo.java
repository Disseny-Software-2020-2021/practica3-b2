package view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;

public class FormReproductorVideo extends JDialog {
    private  JPanel panelReproduccio;
    private int duracioVisualitzacio;
    private int duracioVisualitzada;
    private String serie;
    private int numTemporada;
    private String episodi;
    private JFXPanel fxPanel;
    private  Scene scene = null;
    private  MediaPlayer mediaPlayer;
    private  MediaControl mediaControl;

    private static final String MEDIA_URL = "assets/sample-mp4-file.mp4";

    private void initAndShowGUI() {
        // This method is invoked on the EDT thread
        this.setLocation(20, 20);
        panelReproduccio.setSize(530, 375);
        fxPanel = new JFXPanel();

        if (scene == null || !scene.getWindow().isShowing()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    initFX(fxPanel);
                }
            });
            panelReproduccio.add(fxPanel);
        }

    }

    /**
     * Mètode que es crida quan es tanca la finestra de reproducció
     * @param evt event del mètode
     * @param serie identificador de la sèrie de l'episodi a reproduir
     * @param numTemporada número de temporada de l'episodi a reproduir
     * @param episodi títol de l'episodi a reproduir
     */
    private void formWindowClosing(WindowEvent evt, String serie, int numTemporada, String episodi) {
        if (mediaPlayer!=null) {
            int tempsVisualitzacio = (int) ((mediaPlayer.getCurrentTime().toMillis() * duracioVisualitzacio) / 100.0);
            String estat = "Visualització tancada del reproductor";
            JOptionPane.showMessageDialog(panelReproduccio, estat);
            scene = null;
            mediaPlayer.stop();
            mediaControl.setVisible(false);

        }

    }


    private  void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
            try {
                scene= createScene();
                fxPanel.setScene(scene);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

    }

    private  Scene createScene() throws MalformedURLException {
        Group root = new Group();
        scene = new Scene(root, 729, 405);

        File mediaFile = new File(MEDIA_URL);
        Media media = new Media(mediaFile.toURI().toURL().toString());
        if (mediaPlayer==null)  {
            mediaPlayer = new MediaPlayer(media);
            // Sempre comença a reproduir des de 0
            mediaPlayer.setAutoPlay(true);

            mediaControl = new MediaControl(mediaPlayer);

            scene.setRoot(mediaControl);
        }
        mediaPlayer.play();
        mediaControl.setVisible(true);
        return scene;
    }



    public FormReproductorVideo (String serie, int numTemporada, String episodi, int duracioEpisodi, int duracioVisualitzada) {
        duracioVisualitzacio = duracioEpisodi;
        this.duracioVisualitzada = duracioVisualitzada;
        this.serie = serie;
        this.numTemporada = numTemporada;
        this.episodi = episodi;
        setContentPane(panelReproduccio);
        setModal(true);
        setResizable(true);

        setTitle("Play");

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt, serie, numTemporada, episodi);
            }
            public void windowClosing(WindowEvent evt) { formWindowClosing(evt, serie, numTemporada, episodi);
            }
        });
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void formWindowOpened(WindowEvent evt, String serie, int numTemporada, String episodi) {
            if (scene == null || !scene.getWindow().isShowing()) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        initAndShowGUI();
                    }
                });
            }
    }
}
