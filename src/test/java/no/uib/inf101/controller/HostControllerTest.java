package no.uib.inf101.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sshmanager.controller.HostController;
import no.uib.inf101.sshmanager.model.HostIcon;

public class HostControllerTest {

    private HostController hostController;

    @BeforeEach
    public void setUp() {
        
        
        hostController = new HostController();
        hostController.setNewJsonPath("src/main/java/no/uib/resources/configCopyForTesting.json");
        
    } 

    @Test
    void testGetHostnameAndIdArray() {
        String[][] arrayTester = hostController.getHostnameAndIdArray();
        String[][] arrayExpected  = {{"martin","1"},{"nitram","2"}};
        assertArrayEquals(arrayTester, arrayExpected);
    }

    @Test
    void testGetEnumAsArray() {
        String[] arraysWithEnum = hostController.getEnumAsArray();

        // sjekker først lengden 
        assertEquals(arraysWithEnum.length, HostIcon.values().length);

        // sjekker så alle verdiene ved
        for (int i = 0; i < arraysWithEnum.length; i++) {
            assertEquals(HostIcon.values()[i].toString(), arraysWithEnum[i]);
            
        }
        
    }
}
