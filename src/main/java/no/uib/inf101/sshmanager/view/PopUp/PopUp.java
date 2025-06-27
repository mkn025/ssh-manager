package no.uib.inf101.sshmanager.view.PopUp;


import javax.swing.JFrame;

import no.uib.inf101.sshmanager.controller.HostController;
import no.uib.inf101.sshmanager.controller.TerminalController;
import no.uib.inf101.sshmanager.view.ColorTheme;
import no.uib.inf101.sshmanager.view.HostTable;

public abstract class PopUp extends JFrame {

    // gjør protected 
    // kan de kun brukes av klasssen selv eller klasser som arver 
    // eller detsom
    protected  HostController hostController;
    protected TerminalController terminalController;
    protected  HostTable hostTable;
    protected  ColorTheme color;
    private final int height;
    private final int width;
    private final String title;

    /**
     * klassen fungere som en interfacce på steroiteder,
     * de andre klassen kan bare arve fra denne så får der perf popup 
     * 
     * @param height for vindu
     * @param width for vindu
     * @param title tittel på vindu
     */
    public PopUp(int height,int width,String title){
        this.color = new ColorTheme();
        this.hostController = new HostController();
        this.terminalController = new TerminalController();
        this.hostTable = new HostTable(hostController);
        this.height = height;
        this.width = width;
        this.title = title;
        mainFram();
    }

    /**
     * setter Grunnramme for framen, men speisfiserer ikke layout
     */
    private void mainFram(){
        setTitle(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        getContentPane().setBackground(color.getBackgroundColor());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
    
}
