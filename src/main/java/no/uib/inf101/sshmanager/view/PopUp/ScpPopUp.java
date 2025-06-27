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

import no.uib.inf101.sshmanager.controller.HostController;
import no.uib.inf101.sshmanager.controller.TerminalController;
import no.uib.inf101.sshmanager.model.Host;



public class ScpPopUp extends PopUp implements ActionListener {

    // sørpanel
    private JPanel southJPanel;
    private JLabel angiHost;
    private JTextField scpHostField;
    private JLabel localHostPathLabel;
    private JTextField localHostPathTextField;
    private JLabel remoteHostPathLabel;
    private JTextField remoteHostPathTextField;
    private JButton submitButton;



    
    public ScpPopUp(){
        super(600, 600, "SCP");
        this.terminalController = new TerminalController();
        this.hostController = new HostController();
        setLayout(new BorderLayout());
        add(hostTable.getHostJPanel(),BorderLayout.CENTER);
        add(southPanel(),BorderLayout.SOUTH);

    }

    /**
     * jpanel som inneholder en tekstfelt og en knapp
     * 
     * @return JPanel som inneholder en tekstfelt og en knapp som vi legger til i framen
     */
    private JPanel southPanel(){
        this.southJPanel = new JPanel();
        
        southJPanel.setPreferredSize(new Dimension(130,175));
        southJPanel.setBackground(color.getBackgroundColor());
        southJPanel.setLayout(new BoxLayout(southJPanel, BoxLayout.Y_AXIS));

        this.angiHost = new JLabel("Angi host (id)");
        angiHost.setForeground((color.getTextColor()));
        angiHost.setFont(angiHost.getFont().deriveFont(14f));
        angiHost.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        southJPanel.add(angiHost);

        this.scpHostField = new JTextField();
        scpHostField.setMaximumSize(new Dimension(200,30));
        scpHostField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        scpHostField.addActionListener(this);
        southJPanel.add(scpHostField);
 
        this.localHostPathLabel = new JLabel("Bane på lokal pc");
        localHostPathLabel.setForeground((color.getTextColor()));
        localHostPathLabel.setFont(localHostPathLabel.getFont().deriveFont(14f));
        localHostPathLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        southJPanel.add(localHostPathLabel);

        this.localHostPathTextField = new JTextField();
        localHostPathTextField.setMaximumSize(new Dimension(200,30));
        localHostPathTextField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        localHostPathTextField.addActionListener(this);
        southJPanel.add(localHostPathTextField);
  
        this.remoteHostPathLabel = new JLabel("Bane på ekstern pc");
        remoteHostPathLabel.setForeground((color.getTextColor()));
        remoteHostPathLabel.setFont(remoteHostPathLabel.getFont().deriveFont(14f));
        remoteHostPathLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        southJPanel.add(remoteHostPathLabel);

        this.remoteHostPathTextField = new JTextField();
        remoteHostPathTextField.setMaximumSize(new Dimension(200,30));
        remoteHostPathTextField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        remoteHostPathTextField.addActionListener(this);
        southJPanel.add(remoteHostPathTextField);
        

        this.submitButton = new JButton("Submit");
        submitButton.setMaximumSize(new Dimension(230,30));
        submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        submitButton.addActionListener(this);
        southJPanel.add(submitButton);
        return southJPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == submitButton) {
            String lPath = localHostPathTextField.getText();
            String rPath = remoteHostPathTextField.getText();
            String id = scpHostField.getText();
            Host host = hostController.getHostbyID(id);
            terminalController.scpCommand(host, lPath, rPath);

        }
    }

}
