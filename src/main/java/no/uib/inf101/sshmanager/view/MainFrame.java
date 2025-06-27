package no.uib.inf101.sshmanager.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private final int width;
    private final int height;
    private final ColorTheme colorTheme;
    private final JPanel mainPanel;
    private final Sidebar sidebarLeft;


    // host
    HostsView hostsView;


    /**
     * konstukørr som legger til nødvedinge ting i main framet
     * 
     * @param width
     * @param height
     */
    public MainFrame(int width, int height) {
        this.width = width;
        this.height = height;
        this.colorTheme = new ColorTheme();
        this.sidebarLeft = new Sidebar(colorTheme, this);
        
        this.mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(colorTheme.getBackgroundColor());

        this.hostsView = sidebarLeft.getHostsView();
        mainPanel.add(hostsView);
        setupFrame();
    }


    /**
     * legge main kompoenener og forteller hvilken layout vi skal ha
     */
    private void setupFrame() {
        setLayout(new BorderLayout());
        add(sidebarLeft,BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
                
        setTitle("SSH Manager");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



    /**
     *  male om rommet slik at vi kan vise nye host som kommer inn
     */
    public void repaintHostView(){
        mainPanel.remove(hostsView);
        this.hostsView = new HostsView();
        // legger til det nye og tegner på nytt
        mainPanel.add(hostsView, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}

