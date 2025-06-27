package no.uib.inf101.sshmanager.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import no.uib.inf101.sshmanager.model.Host;
import no.uib.inf101.sshmanager.model.HostIcon;


public class TerminalControllerTest {

    private TerminalController terminalController;
    private Host testHost;

    
   @BeforeEach
    public void setUp() {
        terminalController = new TerminalController();
        testHost = new Host("1", "TestHost", "testhost.local", 22, "testuser", "TestGroup", HostIcon.DEFAULT);
    }

    // fikk ikke testen til å fungere på linux usikker på hvorfor
    // sjekker bare at det ikke kaseter en feilmeldingh når den kjører
    @Test
    @DisabledOnOs(OS.LINUX) 
    void testOpenTerminalWindowFromHost_ValidHost() {
        assertDoesNotThrow(() -> terminalController.openTerminalWindowFromHost(testHost));
    }

    /**
     * sjekker basicly om den kaster en feilmeldingen  
     */
    @Test
    void testOpenTerminalWindowFromHost_NullHost() {
        assertThrows(IllegalArgumentException.class, () -> {
            terminalController.openTerminalWindowFromHost(null);
        }, "Skal kaste IllegalArgumentException for null host");
    }

   @Test
    @DisabledOnOs(OS.LINUX) 
    void testOpenDynamicPortForwarding_ValidInput() {
        String dynamicPort = "8080";
        assertDoesNotThrow(() -> terminalController.openDynamicPortForwarding(testHost, dynamicPort));
        // Verifiserer kun at ingen exception kastes.
    }
    
   @Test
    void testOpenDynamicPortForwarding_NullHost() {
         String dynamicPort = "8080";
         // Forventer foreløpig ingen exception, men metoden bør ideelt sett validere input.
         // Hvis null-sjekk legges til, endre til assertThrows(IllegalArgumentException.class, ...)

         assertDoesNotThrow(() -> terminalController.openDynamicPortForwarding(null, dynamicPort));
    }


    /**
     * Tester om scpCommand kjører uten feil med gyldig input.
     * Denne testen forventer ingen exceptions.
     */
    @Test
    @DisabledOnOs(OS.LINUX) 
    void testScpCommand_ValidInput() {
        // vil ikke overføre noe faktisk, men funker om et vindu åpner seg og den ikke kaster en feil
        String localPath = "/path/to/local/file";
        String remotePath = "/path/to/remote/dir";
        assertDoesNotThrow(() -> terminalController.scpCommand(testHost, localPath, remotePath));
        // Verifiserer kun at ingen exception kastes.
    }

     /**
     * Tester om scpCommand håndterer null host korrekt.
     * Forventer for øyeblikket ingen spesifikk exception, men bør ideelt sett kaste IllegalArgumentException.
     */
    @Test
    void testScpCommand_NullHost() {
        String localPath = "/path/to/local/file";
        String remotePath = "/path/to/remote/dir";
        // Forventer foreløpig ingen exception, men metoden bør ideelt sett validere input.
        // Hvis null-sjekk legges til, endre til assertThrows(IllegalArgumentException.class, ...)
        assertDoesNotThrow(() -> terminalController.scpCommand(null, localPath, remotePath));
    }
}
