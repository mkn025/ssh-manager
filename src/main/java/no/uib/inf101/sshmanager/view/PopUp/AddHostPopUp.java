package no.uib.inf101.sshmanager.view.PopUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import no.uib.inf101.sshmanager.model.Host;
import no.uib.inf101.sshmanager.model.HostIcon;

public class AddHostPopUp extends PopUp implements ActionListener{

    private final String[] iconList;
    
    private final JTextField hostname = new JTextField();
    private final  JTextField name = new JTextField();
    private final  JTextField port = new JTextField();
    private final  JTextField username = new JTextField();
    private final  JTextField group = new JTextField();

    private  JComboBox<String> dropDownMenuForIcon;
    private  JButton addHost;

    public AddHostPopUp() {
        super(600, 400, "Add Host");
        this.iconList = hostController.getEnumAsArray();
        setLayout(null);
        components();

    }


    /**
     *  methode som legger til alle kompoenter
     */
    private void components() {
        JLabel hostLabel = new JLabel("Host(IP)");
        hostLabel.setBounds(50, 50, 300, 30);
        hostLabel.setForeground(color.getTextColor());
        
        hostname.setBounds(50, 80, 300, 30);
        add(hostname);
        add(hostLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 120, 300, 30);
        nameLabel.setForeground(color.getTextColor());
        name.setBounds(50, 150, 300, 30);
        add(nameLabel);
        add(name);

        JLabel portLabel = new JLabel("Port");
        portLabel.setBounds(50, 190, 300, 30);
        portLabel.setForeground(color.getTextColor());
        port.setBounds(50, 220, 300, 30);
        add(portLabel);
        add(port);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 260, 300, 30);
        usernameLabel.setForeground(color.getTextColor());
        username.setBounds(50, 290, 300, 30);
        add(usernameLabel);
        add(username);

        JLabel groupLabel = new JLabel("Group");
        groupLabel.setBounds(50, 330, 300, 30);
        groupLabel.setForeground(color.getTextColor());
        group.setBounds(50, 360, 300, 30);
        add(groupLabel);
        add(group);


        JLabel dropDownLabel = new JLabel("Velg Icon");
        dropDownLabel.setBounds(50, 390, 300, 30);
        dropDownLabel.setForeground(color.getTextColor());
        this.dropDownMenuForIcon = new JComboBox<>(iconList);
        dropDownMenuForIcon.setBounds(50, 420, 300, 30);

        add(dropDownLabel);
        add(dropDownMenuForIcon);
        
        

        this.addHost = new JButton("Add Host");
        addHost.setBounds(150, 470, 100, 30);
        addHost.setAlignmentX(JButton.CENTER_ALIGNMENT);
        addHost.setFocusable(false);
        addHost.addActionListener(this);
        add(addHost);

    }



    /**
     * Logikk for det som skal skje når vi trykker på Jbutton
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addHost) {
            try {

                // legger til, host 
                int portNumber = Integer.parseInt(port.getText());
                String randomHostID = hostController.getRandomNumber();
                String enumIconSting = iconList[dropDownMenuForIcon.getSelectedIndex()];
                
                Host hostToAdd = new Host(
                    randomHostID,
                    name.getText(),
                    hostname.getText(), 
                    portNumber,
                    username.getText(),
                    group.getText(),
                    HostIcon.valueOf(enumIconSting));
                hostController.addHost(hostToAdd);
                // fjern vindu
                dispose();
                

            } catch (NumberFormatException ex) {
                JLabel errormassage = new JLabel("Invalid port number: " + port.getText());
                errormassage.setForeground(color.getTextColor());
                add(errormassage);
            }
        }
    }


}
