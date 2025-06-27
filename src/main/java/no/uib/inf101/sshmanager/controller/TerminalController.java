package no.uib.inf101.sshmanager.controller;

import java.io.IOException;

import no.uib.inf101.sshmanager.model.Host;

public class TerminalController {
 
    /**
    * Oppretter en ny TerminalController.
    * Foreløpig ingen spesifikk initialisering nødvendig.
    */
    public TerminalController() {
        
    }

    /**
    * Åpner et nytt terminalvindu og starter en SSH-sesjon til den gitte hosten.
    * Kommandoen som kjøres er formatert som {@code ssh brukernavn@vertsnavn -p port}.
    *
    * @param host Host-objektet som inneholder tilkoblingsinformasjonen (brukernavn, vertsnavn, port).
    * @throws IllegalArgumentException dersom code er null 
    */
    public void openTerminalWindowFromHost(Host host) {
        if (host == null) {
            throw new IllegalArgumentException("Host kan ikke være null");
        }
        String sshCommand = String.format("ssh %s@%s -p %d", host.getUsername(), host.getHostname(), host.getPort());
        runCommandOnCorrectOS(sshCommand);
        
    }

    /**
     * Åpner et nytt terminalvindu og setter opp dynamisk port forwarding (SOCKS proxy)
     * via den gitte hosten på den spesifiserte lokale porten.
     * Inkluderer en {@code echo}-kommando for å holde sesjonen aktiv.
     *
     * @param host Host-objektet som trafikken skal rutes gjennom.
     * @param dynamicPort Den lokale porten (f.eks. "8080") som skal brukes for SOCKS proxy.
     */
    public void openDynamicPortForwarding(Host host, String dynamicPort) {
        String sshCommandPortForward = String.format(
        "echo 'keep sessson alive, for portwording to work ' && ssh -D %s %s@%s -p %d",
        dynamicPort,
        host.getUsername(),
        host.getHostname(),
        host.getPort());  
        runCommandOnCorrectOS(sshCommandPortForward);

    }


    /**
     * kjørere scp komandoen
     * 
     * @param host hosten du skal sende filer til
     * @param localPath stien til den lokale filen
     * @param remotePath stien på den ekstren pcen
     */
    public void scpCommand(Host host, String localPath, String remotePath){
        String scpCommand = String.format("scp -P %d %s %s@%s:%s",
        host.getPort(),
        localPath,
        host.getUsername(),
        host.getHostname(),
        remotePath
        );
        runCommandOnCorrectOS(scpCommand);
    
    }



    /**
     * Sjekker hvilken os å kjører riktig commando til rikrig oprativsystem
     * https://stackoverflow.com/questions/50778265/programmatically-launch-bash-shell-command-osascript-using-java/50779383; 13.04.25
     * har ikke testet med linux 
     * 
     * @param sshCommand kommandoen som skal bli kjørt i terminalvindu
     * @throws IOException forteller om det er noe galt med å kjøre kommandoen
     * @throws InterruptedException forteller om det er noe galt med å kjøre kommandoe
     */

    private void runCommandOnCorrectOS(String sshCommand){
        String osName = System.getProperty("os.name").toLowerCase();
        try {
            if (osName.contains("mac") || osName.contains("darwin")) {
                String[] command = {
                    "osascript", 
                    "-e", 
                    "tell application \"Terminal\" to do script \"" + sshCommand + "\""
                };
                Runtime.getRuntime().exec(command);
            } 
            else if (osName.contains("win")) {
                String[] command = {
                    "cmd.exe", 
                    "/c", 
                    "start cmd.exe /k " + sshCommand
                };
                Runtime.getRuntime().exec(command);
            } 
            else {
               
                try {
                    String[] gnomeCommand = {
                        "gnome-terminal", 
                        "--", 
                        "bash", 
                        "-c", 
                        sshCommand + "; exec bash"
                    };
                    Runtime.getRuntime().exec(gnomeCommand);
                } catch (IOException e) {
                    String[] xtermCommand = {
                        "xterm", 
                        "-e", 
                        sshCommand
                    };
                    Runtime.getRuntime().exec(xtermCommand);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to open terminal window for host: ");
        }
        
    }

}
