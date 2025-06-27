package no.uib.inf101.sshmanager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import no.uib.inf101.sshmanager.view.PopUp.AddHostPopUp;
import no.uib.inf101.sshmanager.view.PopUp.DelHostPopUp;
import no.uib.inf101.sshmanager.view.PopUp.HostInfo;
import no.uib.inf101.sshmanager.view.PopUp.PortForwardPopUp;
import no.uib.inf101.sshmanager.view.PopUp.ScpPopUp;


//import no.uib.inf101.sshmanager.controller.HostController;

public class Sidebar extends JPanel implements ActionListener {

    HostsView hostsView;    


    private final ColorTheme colorTheme;
    //private HostController hostController;
    private JButton buttonAddHost;
    private JButton buttonDelHost;
    private JButton buttonPortForward;
    private JButton buttonSCP;
    private JButton buttonHostInfo;
    private JButton reloadButton;
    private MainFrame mainFrame;
    


    /**
     * konsstuktør som lagger til nødvednig komponenter
     * 
     * @param colorTheme 
     * @param mainFrame hovedframet
     */
    public Sidebar(ColorTheme colorTheme, MainFrame mainFrame) {
        this.hostsView = new HostsView();
        this.colorTheme = colorTheme;
        this.mainFrame = mainFrame; 
        buttonLogic();
        setupPanel();
    }
    
    
    /**
     * methode som vi kan kalle på når vi skal legge til i main frame
     * 
     * @param colorTheme farger
     */
    public Sidebar(ColorTheme colorTheme) {
        this(colorTheme, null);    
    }
    
    /**
     * hoved kompoenter til panelet 
     */
    private void setupPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(colorTheme.getSidebarColor());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(250, 0)); 
        
    }

    /**
     *  knappe kompontenter
     */
    private void buttonLogic(){
        this.buttonAddHost = new JButton("Add host");
        buttonAddHost.setBounds(15, 10, 220, 50); 
        buttonAddHost.setFocusable(false);
        buttonAddHost.addActionListener(this);
        add(buttonAddHost);


        this.buttonDelHost = new JButton("Delate host");
        buttonDelHost.setBounds(15,70, 220, 50);
        buttonDelHost.setFocusable(false);
        buttonDelHost.addActionListener(this);
        add(buttonDelHost);



        this.buttonPortForward = new JButton("SSH port forwarding/tunneling");
        buttonPortForward.setBounds(15,130, 220, 50);
        buttonPortForward.setFocusable(false);
        buttonPortForward.addActionListener(this);
        add(buttonPortForward);

        this.buttonSCP = new JButton("SCP");
        buttonSCP.setBounds(15,190, 220, 50);
        buttonSCP.setFocusable(false);
        buttonSCP.addActionListener(this);
        add(buttonSCP);

        this.buttonHostInfo = new JButton("Host Info");
        buttonHostInfo.setBounds(15, 250, 220, 50);
        buttonHostInfo.setFocusable(false);
        buttonHostInfo.addActionListener(this);
        add(buttonHostInfo);

        this.reloadButton = new JButton("Reload Hosts");
        reloadButton.setBounds(15,600, 220, 50);
        reloadButton.setFocusable(false);
        reloadButton.addActionListener(this);
        add(reloadButton);

    }
    
   /**
    * hånderer av kanppetrykk
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAddHost) {
            AddHostPopUp addHostPopUp = new AddHostPopUp();
            addHostPopUp.setVisible(true);
        }else if (e.getSource() == buttonDelHost) {
            DelHostPopUp delHostPopUp = new DelHostPopUp();
            delHostPopUp.setVisible(true);
        }else if (e.getSource() == buttonPortForward) {
            PortForwardPopUp poeForwardPopUp = new PortForwardPopUp();
            poeForwardPopUp.setVisible(true);
        }else if (e.getSource() == buttonSCP){
            ScpPopUp scpPopUp = new ScpPopUp();
            scpPopUp.setVisible(true);
        }else if (e.getSource()== buttonHostInfo){
            HostInfo hostInfo = new HostInfo();
            hostInfo.setVisible(true);
        }else if (e.getSource() == reloadButton){
            mainFrame.repaintHostView();

            
        }
    }


    public HostsView getHostsView() {
        return hostsView;
    }

       
   
}
