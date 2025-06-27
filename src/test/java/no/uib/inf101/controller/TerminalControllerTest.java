package no.uib.inf101.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import no.uib.inf101.sshmanager.controller.TerminalController;
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

    @Test
    @DisabledOnOs(OS.LINUX)
    void testOpenTerminalWindowFromHost_ValidHost() {
        assertDoesNotThrow(() -> terminalController.openTerminalWindowFromHost(testHost));
    }

    @Test
    void testOpenTerminalWindowFromHost_NullHost() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            terminalController.openTerminalWindowFromHost(null);
        });
        assertDoesNotThrow(() -> exception.getMessage());
    }

    @Test
    @DisabledOnOs(OS.LINUX)
    void testOpenDynamicPortForwarding_ValidInput() {
        String dynamicPort = "8080";
        assertDoesNotThrow(() -> terminalController.openDynamicPortForwarding(testHost, dynamicPort));
    }

    @Test
    @DisabledOnOs(OS.LINUX)
    void testScpCommand_ValidInput() {
        String localPath = "/path/to/local/file";
        String remotePath = "/path/to/remote/dir";
        assertDoesNotThrow(() -> terminalController.scpCommand(testHost, localPath, remotePath));
    }

}
