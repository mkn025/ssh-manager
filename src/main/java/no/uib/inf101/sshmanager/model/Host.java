package no.uib.inf101.sshmanager.model;
import com.fasterxml.jackson.annotation.JsonProperty;





public class Host {
    private final String id;
    private final String name;
    private final String hostname;
    private final int port;
    private final String username;
    private final String group;
    private final HostIcon icon;

    /**
     * klasse med nødvednig informasjon om host
     * 
     * @param id ID som vi kan bruke til unikt identifeiseer host
     * @param name 
     * @param hostname
     * @param port
     * @param username
     * @param group
     * @param icon
     */
    public Host(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("hostname") String hostname,
        @JsonProperty("port") int port,
        @JsonProperty("username") String username,
        @JsonProperty("group") String group,
        @JsonProperty("iconType") HostIcon icon
    ){
        
        this.id = id;
        this.name = name;
        this.hostname= hostname;
        this.port = port;
        this.username = username;
        this.group = group;
        this.icon = icon;

    }       
    

    public String getGroup() {
        return group;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // sjekker om obj er null og om de er av samme klasse
        // hvis de ikke er det så returner false
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // sjekker om de har samme id, navn, hostnavn, port, brukernavn og gruppe
        // hvis de har det så returner true
        Host other = (Host) obj;
        return port == other.port &&
               id.equals(other.id) &&
               name.equals(other.name) &&
               hostname.equals(other.hostname) &&
               username.equals(other.username) &&
               group.equals(other.group);
    }
    

    public HostIcon getIcon() {
        return icon;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, hostname, port, username, group);
    }

    @Override
    public String toString() {
        return "Host{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", hostname='" + hostname + '\'' +
               ", port=" + port +
               ", username='" + username + '\'' +
               ", group='" + group + '\'' +
               ", icon=" + icon +
               '}';
    }

}
