package no.uib.inf101.sshmanager.view.PopUp.HostInfomation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.uib.inf101.sshmanager.model.Host;
import no.uib.inf101.sshmanager.view.PopUp.PopUp;

public class HostInformationSpesfic extends PopUp{

    private final Host host;
    private JPanel mainPanel;

    public HostInformationSpesfic(Host host) {
        super(700, 700, host.getName()+" sin informarmasjon");
        this.host = host;

        add(mainPanel());
    }
    
    private JPanel mainPanel() {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(color.getBackgroundColor());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add title
        JLabel titleLabel = createLabel(host.getName() + " - Detaljert informasjon", 24);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel);
        
        // Display host icon if available
        if (host.getIcon() != null && host.getIcon().iconPath != null && !host.getIcon().iconPath.isEmpty()) {
            ImageIcon originalIcon = new ImageIcon(host.getIcon().iconPath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            mainPanel.add(iconLabel);
        }
        
        // Create a panel for basic information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(color.getSidebarColor());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        infoPanel.setMaximumSize(new Dimension(650, 300));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add host information with proper styling and spacing
        infoPanel.add(createInfoLabel("Host ID: ", host.getId()));
        addSpacer(infoPanel);
        
        infoPanel.add(createInfoLabel("Host Name: ", host.getName()));
        addSpacer(infoPanel);
        
        infoPanel.add(createInfoLabel("Host Address: ", host.getHostname()));
        addSpacer(infoPanel);
        
        infoPanel.add(createInfoLabel("Port: ", String.valueOf(host.getPort())));
        addSpacer(infoPanel);
        
        infoPanel.add(createInfoLabel("Username: ", host.getUsername()));
        addSpacer(infoPanel);
        
        infoPanel.add(createInfoLabel("Group: ", host.getGroup()));
        addSpacer(infoPanel);
        
        infoPanel.add(createInfoLabel("Icon Type: ", host.getIcon().toString()));
        
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setBorder(null);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        mainPanel.add(scrollPane);
        
        // Add connection button options panel
        JPanel buttonsPanel = new JPanel();
        
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBackground(color.getBackgroundColor());
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mainPanel.add(buttonsPanel);
        
        return mainPanel;
    }
    
    /**
     * laget en label type
     */
    private JLabel createLabel(String text, float fontSize) {
        JLabel label = new JLabel(text);
        label.setForeground(color.getTextColor());
        label.setFont(label.getFont().deriveFont(fontSize));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    
    /**
     * crate pandel som har jlabel med verdi og navn på label
     */
    private JPanel createInfoLabel(String labelText, String value) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(color.getSidebarColor());
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel label = new JLabel(labelText);
        // label farge
        label.setForeground(color.getLabelInfoColor());
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 14));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(color.getTextColor());
        valueLabel.setFont(new Font(valueLabel.getFont().getName(), Font.PLAIN, 14));
        
        panel.add(label);
        panel.add(valueLabel);
        
        return panel;
    }
    
    /**
     * Adds vertical spacing between components
     */
    private void addSpacer(JPanel panel) {
        panel.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
    }
}
