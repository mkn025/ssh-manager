package no.uib.inf101.sshmanager.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import no.uib.inf101.sshmanager.controller.HostController;

public class HostTable{


    HostController hostController;
    JTable hostJTable;
    JPanel hostTablePanel;

    /**
     * klasse som lager table med hosts 
     */
    public HostTable(HostController hostController) {
        this.hostController = hostController;
        this.hostJTable = new JTable();
        this.hostTablePanel = new JPanel();
    }

    /**
     * panel som lar oss se host og id
     * 
     * @return pane altså et panel som vi kan legge til i et frame
     */
    public JPanel getHostJPanel(){
        String[] columns = new String[] {"Name", "ID"};
        String[][] data = hostController.getHostnameAndIdArray();
        this.hostJTable = new JTable(data, columns);
        hostJTable.setRowSelectionAllowed(false);
        JScrollPane scrollPane = new JScrollPane(hostJTable);
        hostTablePanel.add(scrollPane);
        return hostTablePanel;

    }
    
    
    
}
