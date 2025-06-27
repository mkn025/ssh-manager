package no.uib.inf101.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sshmanager.model.Host;
import no.uib.inf101.sshmanager.model.HostIcon;
import no.uib.inf101.sshmanager.model.JsonHostRepository;


public class JsonHostRepositoryTest {
    


    @Test
    public void loadHostsTest(){
        Host host1 = new Host("1", "martin", "test", 666, "nitram", "UIB", HostIcon.WINDOWS);
        Host host2 = new Host("2", "nitram", "test", 999, "martin", "BIU", HostIcon.MACOS);
        ArrayList<Host> hardCodeLst = new ArrayList<>();
        hardCodeLst.add(host1);
        hardCodeLst.add(host2);

        JsonHostRepository data = new JsonHostRepository("src/main/java/no/uib/resources/configCopyForTesting.json");
        ArrayList<Host> listeFromMethode = data.loadHosts();

        assertTrue(hardCodeLst.equals(listeFromMethode));
            
    }


    @Test
    public void addHostsTest(){
        
    }
    
    @Test
    public void getHosteExistance() {

        JsonHostRepository repo = new JsonHostRepository("src/main/java/no/uib/resources/configCopyForTesting.json");
        assertTrue(repo.getHosteExistance("1"));
        assertTrue(repo.getHosteExistance("2"));
        

        // Host ID > Upperbound for ID
        
        int upperBoundForHostID = repo.getUpperBoundForHostID()+10;
        String upperBoundForHostIDString = Integer.toString(upperBoundForHostID);
        assertFalse(repo.getHosteExistance(upperBoundForHostIDString));
    }

    @Test
    public void getNextId() {
        JsonHostRepository repo = new JsonHostRepository("src/main/java/no/uib/resources/configCopyForTesting.json");
        
        String newId = repo.getNextId();
        
        assertNotNull(newId);
        assertFalse(newId.isEmpty());
        
        assertFalse(repo.getHosteExistance(newId));
        
        String anotherId = repo.getNextId();
        assertNotEquals(newId, anotherId);
    }

    @Test
    public void randomNumberTest() {

        JsonHostRepository repo = new JsonHostRepository("src/main/java/no/uib/resources/configCopyForTesting.json");
       
        

        // set pga den skal ha unike verdier 
        Set<String> generatedIds = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            generatedIds.add(repo.getNextId());
        }
       
        
        // sjekker basicly om den lager 10 randome verdier
        assertEquals(10, generatedIds.size());
       
        // sjekker at alle verdien som er laget er større en 0 og mindre 1000 som er upper bound 
        for (String id : generatedIds) {
            int numericId = Integer.parseInt(id);
            assertTrue(numericId >= 0 && numericId < 1000);
        }
    }
}
