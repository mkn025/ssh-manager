package no.uib.inf101.sshmanager.view.PopUp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import no.uib.inf101.sshmanager.view.PopUp.HostInfomation.HostInformationSpesfic;

public class HostInfo extends PopUp implements ActionListener{

    private JPanel southPanel;  
    private  JTextField hostNumber;
    private JButton openInfoButton;

    /**
     * Setter opp en pop up vindu som viser informasjon om en host
     */
    public HostInfo() {
        super(600,600, "HostInfo");
        setLayout(new BorderLayout());
        add(hostTable.getHostJPanel(),BorderLayout.CENTER);
        add(southPanel(),BorderLayout.SOUTH);
    }

    /**
     * Setter opp en panel som inneholder en tekstfelt og en knapp
     * 
     * @return jpanel som inneholder en tekstfelt og en knapp
     */
    private JPanel southPanel(){
        this.southPanel = new JPanel();

        southPanel.setPreferredSize(new Dimension(130, 180)); // Increased height for two fields
        southPanel.setBackground(color.getSidebarColor());
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS)); // Set vertical layout

        southPanel.add(javax.swing.Box.createVerticalStrut(20));

        JLabel infoJLabel = new JLabel("Hvilken host vil du hete ut informasjon om");
        infoJLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        infoJLabel.setForeground(color.getTextColor());
        infoJLabel.setFont(infoJLabel.getFont().deriveFont(14f));
        southPanel.add(infoJLabel);


        this.hostNumber = new JTextField();
        hostNumber.setMaximumSize(new Dimension(200,30));
        hostNumber.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        hostNumber.addActionListener(this);
        southPanel.add(hostNumber);
        


        this.openInfoButton = new JButton("Åpne info");
        openInfoButton.setBounds(150, 270, 100, 30);
        openInfoButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        openInfoButton.setFocusable(false);
        openInfoButton.addActionListener(this);
        southPanel.add(openInfoButton);


        
        return  southPanel;

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{

            // Sjekker om knappen er trykket og utfører logikken
            if (e.getSource() == openInfoButton) {
                HostInformationSpesfic hostInformationSpesfic = new HostInformationSpesfic(hostController.getHostbyID(hostNumber.getText()));
                hostInformationSpesfic.setVisible(true);
            }
        }catch(NumberFormatException ex){
            System.out.println("Du velge en id som eksisterer du");
        }
       
    }

    
}
