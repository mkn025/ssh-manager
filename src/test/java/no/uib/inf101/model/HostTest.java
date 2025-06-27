package no.uib.inf101.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sshmanager.model.Host;
import no.uib.inf101.sshmanager.model.HostIcon;

public class HostTest {

    private Host host1;
    private Host host2;
    private Host host3;

    @BeforeEach
    public void setUp() {
        host1 = new Host("1", "TestHost1", "host1.local", 22, "user1", "Group1", HostIcon.UBUNTU);
        host2 = new Host("1", "TestHost1", "host1.local", 22, "user1", "Group1", HostIcon.UBUNTU); // Equal to host1
        host3 = new Host("2", "TestHost2", "host2.local", 2222, "user2", "Group2", HostIcon.WINDOWS); // Different from host1
    }

    @Test
    void testConstructorAndGetters() {
        assertNotNull(host1);
        assertEquals("1", host1.getId());
        assertEquals("TestHost1", host1.getName());
        assertEquals("host1.local", host1.getHostname());
        assertEquals(22, host1.getPort());
        assertEquals("user1", host1.getUsername());
        assertEquals("Group1", host1.getGroup());
        assertEquals(HostIcon.UBUNTU, host1.getIcon());
    }

    @Test
    void testEquals() {
        assertEquals(host1, host1);

        assertEquals(host1, host2);
        assertEquals(host2, host1);

        assertNotEquals(host1, host3);
        assertNotEquals(host2, host3);

        assertNotEquals(host1, null);
        assertNotEquals(host1, "a string");

        Host diffId = new Host("99", "TestHost1", "host1.local", 22, "user1", "Group1", HostIcon.UBUNTU);

        assertNotEquals(host1, diffId);

        Host diffName = new Host("1", "DifferentName", "host1.local", 22, "user1", "Group1", HostIcon.UBUNTU);
        assertNotEquals(host1, diffName);

        Host diffHostname = new Host("1", "TestHost1", "different.host", 22, "user1", "Group1", HostIcon.UBUNTU);
        assertNotEquals(host1, diffHostname);

        Host diffPort = new Host("1", "TestHost1", "host1.local", 9999, "user1", "Group1", HostIcon.UBUNTU);
        assertNotEquals(host1, diffPort);

        Host diffUsername = new Host("1", "TestHost1", "host1.local", 22, "diffUser", "Group1", HostIcon.UBUNTU);
        assertNotEquals(host1, diffUsername);

        Host diffGroup = new Host("1", "TestHost1", "host1.local", 22, "user1", "DiffGroup", HostIcon.UBUNTU);
        assertNotEquals(host1, diffGroup);

        Host diffIcon = new Host("1", "TestHost1", "host1.local", 22, "user1", "Group1", HostIcon.MACOS);
        assertEquals(host1, diffIcon, "Icon difference should not affect equality based on current equals implementation");
    }

    @Test
    void testHashCode() {
        assertEquals(host1.hashCode(), host1.hashCode());

        assertEquals(host1.hashCode(), host2.hashCode());

    }

   
}
