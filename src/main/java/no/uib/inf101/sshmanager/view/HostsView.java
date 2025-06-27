package no.uib.inf101.sshmanager.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.uib.inf101.sshmanager.controller.HostController;
import no.uib.inf101.sshmanager.controller.TerminalController;
import no.uib.inf101.sshmanager.model.Host;

/**
 * HostsView er en JPanel som viser en oversikt over alle tilgjengelige hosts.
 * Hver host vises som en knapp med navn og ikon, og brukeren kan klikke på knappen
 * for å åpne en terminal til den valgte hosten.
 */
public class HostsView extends JPanel {

    GridLayout grid;
    HostController hostController;
    TerminalController terminalController;

    /**
     * Konstruktør for HostsView.
     * Initialiserer HostController Aog TerminalController, og setter opp hovedrammen.
     */
    public HostsView() {
        this.hostController = new HostController();
        this.terminalController = new TerminalController();
        mainFrame();
    }

    /**
     * Setter opp hovedrammen for panelet.
     * Definerer størrelse, layout og bakgrunnsfarge, og legger til knapper for hosts.
     */
    private void mainFrame() {
        setSize(950, 650);
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html; 25.04.25
        this.grid = new GridLayout(6, 6, 10, 10);
        setLayout(grid);
        setBackground(new ColorTheme().getBackgroundColor());
        addButtons();
    }

    /**
     * Legger til knapper for alle hosts i panelet.
     * Henter listen over hosts fra HostController og oppretter en knapp for hver host.
     */
    private void addButtons() {
        ArrayList<Host> hosts = hostController.getHosts();

        // måtte få hjelp til sortering 
        // https://www.w3schools.com/java/ref_arraylist_sort.asp; 15.04.25
        hosts.sort((a, b) -> a.getGroup().compareTo(b.getGroup()));
        //hosts.sort((a,b)-> a.getId().compareTo(b.getId()));
        for (Host host : hosts) {
            add(buttonWithIconAndText(host));
        }
    }

    /**
     * Oppretter en knapp med navn og ikon for en gitt host.
     * Når knappen klikkes, åpnes en terminal til den valgte hosten.
     *
     * @param host Host-objektet som knappen representerer.
     * @return En JButton med navn og ikon for hosten.
     */
    private JButton buttonWithIconAndText(Host host) {
        JButton button = new JButton(host.getName());

        if (host.getIcon() != null && host.getIcon().iconPath != null && !host.getIcon().iconPath.isEmpty()) {
            javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(host.getIcon().iconPath);
            java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new javax.swing.ImageIcon(scaledImage));
        }

        // Legger til en ActionListener for å åpne terminalen når knappen klikkes
        button.addActionListener((ActionEvent e) -> {
            terminalController.openTerminalWindowFromHost(host);
        });

        return button;
    }
}
