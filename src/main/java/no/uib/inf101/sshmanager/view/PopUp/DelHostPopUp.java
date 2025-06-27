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

public class DelHostPopUp extends PopUp implements ActionListener{

    private JPanel bottomJPanel;
    private JLabel bottonText;
    private JTextField removeHost;
    private JButton submitButton;

    public DelHostPopUp() {
        super(600,600,"Delate host");
        setLayout(new BorderLayout());
        add(southPanel(), BorderLayout.SOUTH);
        add(hostTable.getHostJPanel(),BorderLayout.CENTER);
    }
    


    /**
     * lager et jpanel objekt som vi kan plaserer der vi måtte ønske
     * 
     * @return returen det som vi skal putte i sørpanel 
     */
    private JPanel southPanel() {
        this.bottomJPanel = new JPanel();
        bottomJPanel.setPreferredSize(new Dimension(130, 130));
        bottomJPanel.setBackground(color.getSidebarColor());
        bottomJPanel.setLayout(new BoxLayout(bottomJPanel, BoxLayout.Y_AXIS)); // Set vertical layout

        // Label
        this.bottonText = new JLabel("What host do you want to remove (hostID): ");
        bottonText.setForeground(color.getTextColor());
        bottonText.setFont(bottonText.getFont().deriveFont(14f));
        bottonText.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        bottomJPanel.add(bottonText);

        // Text 
        this.removeHost = new JTextField();
        removeHost.setMaximumSize(new Dimension(200, 30)); 
        removeHost.setAlignmentX(JTextField.CENTER_ALIGNMENT); 
        bottomJPanel.add(removeHost);

        //knapp
        this.submitButton = new JButton("Delate host");
        submitButton.setMaximumSize(new Dimension(230,30));
        submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        submitButton.addActionListener(this);
        bottomJPanel.add(submitButton);

        return bottomJPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submitButton) {
            String idToRemove = removeHost.getText();
            hostController.removeHostWithID(idToRemove);
            dispose();
        }
    }

   

}



