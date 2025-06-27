package no.uib.inf101.sshmanager.model;

import java.util.List;

public interface HostRepository {

    

    /** Skal laste inn json filens somm inneholder informasjon om hostene 
     * 
     * @return en liste med objekt host 
     */
    List<Host> loadHosts();


    /**
     * legger host til i listen med host, 
     * Viktig at host er en feltvaribel
     * 
     * 
     * @param host 
     */
    public void addHost(Host host);

    /**
     * fjerner host med gitt id
     *  
     * @param hostID 
     */
    public void deleteHost(String hostID);

    /**
     * Sjekker om host med gitt id eksiterer
     * 
     * @param hostID
     * @return hvis ja true 
     */
    public boolean getHosteExistance(String hostID);

}
