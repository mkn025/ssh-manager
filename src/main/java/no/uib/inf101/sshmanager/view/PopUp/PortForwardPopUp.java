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

import no.uib.inf101.sshmanager.model.Host;

public class PortForwardPopUp extends PopUp implements ActionListener {


    // Bottom panel components
    private JPanel southPanel;
    private JLabel hostIdLabel;
    private JTextField hostIdField;
    private JLabel portLabel;
    private JTextField portField;
    private JButton submitButton;

    
    
    public PortForwardPopUp() {
        super(600, 600, "Dynamic Port Forwarding");
        setLayout(new BorderLayout());
        add(southPanel(), BorderLayout.SOUTH);
        add(hostTable.getHostJPanel(), BorderLayout.CENTER);
 
    }
    
    /**
     * 
     * @return JPanel som inneholder en tekstfelt og en knapp som vi legger til i framen
     */
    private JPanel southPanel() {
        this.southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(130, 180)); // Increased height for two fields
        southPanel.setBackground(color.getSidebarColor());
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS)); // Set vertical layout

        // Host ID Label
        this.hostIdLabel = new JLabel("Enter host ID:");
        hostIdLabel.setForeground(color.getTextColor());
        hostIdLabel.setFont(hostIdLabel.getFont().deriveFont(14f));
        hostIdLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        southPanel.add(hostIdLabel);

        // Host ID TextField
        this.hostIdField = new JTextField();
        hostIdField.setMaximumSize(new Dimension(200, 30));
        hostIdField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        southPanel.add(hostIdField);

        // Port Label
        this.portLabel = new JLabel("Enter port for forwarding (1024-65535):");
        portLabel.setForeground(color.getTextColor());
        portLabel.setFont(portLabel.getFont().deriveFont(14f));
        portLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        southPanel.add(portLabel);

        // Port TextField
        this.portField = new JTextField();
        portField.setMaximumSize(new Dimension(200, 30));
        portField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        southPanel.add(portField);

        // Submit Button
        this.submitButton = new JButton("Start Port Forwarding");
        submitButton.setMaximumSize(new Dimension(230, 30));
        submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        submitButton.addActionListener(this);
        southPanel.add(submitButton);

        return southPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        if (e.getSource() == submitButton) {
            String hostId = hostIdField.getText();
            String port = portField.getText();
         
            if (hostId.isEmpty() || port.isEmpty()) {
                System.err.println("Host ID and port are required.");
                return;
            }
            
            try {

                // burde kanskje gjort loggikken i controlleren
                int portNum = Integer.parseInt(port);
                // kan ikke har porter som er < 1024 pga. da ternger vi root accsess

                if (portNum < 1024 || portNum > 65535) {
                 
                    System.err.println("Port must be between 1024 and 65535.");
                    return;
                }
                
                Host host = hostController.getHostbyID(hostId);
                if (host != null) {
                    terminalController.openDynamicPortForwarding(host, port);
                    dispose(); 
                } else {
                    System.err.println("Host with ID " + hostId + " not found.");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Port must be a valid number.");
            }
        }
    }
}
