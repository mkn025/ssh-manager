package no.uib.inf101.sshmanager.controller;

import java.util.ArrayList;

import no.uib.inf101.sshmanager.model.Host;
import no.uib.inf101.sshmanager.model.HostIcon;
import no.uib.inf101.sshmanager.model.JsonHostRepository;

public class HostController {


    String jsonPath;
    JsonHostRepository hostData; 


    public HostController() {
        this.jsonPath = "src/main/java/no/uib/resources/config.json";
        this.hostData = new JsonHostRepository(jsonPath);
    }



    /**
     * methode som lar deg sett ny json path
     * gunstig om jeg senere skal la funksjonalitet for custom path til json fil
     * 
     * @param path sti til filen 
     */
    public void setNewJsonPath(String path){
        this.jsonPath = path;
        this.hostData = new JsonHostRepository(path);
    }


    /**
     * methode som bruker modellen til å legge til i json filen
     * 
     * @param host host som skal legges til
     */
    public void addHost(Host host){
        hostData.addHost(host);
    }

    /**
     * 
     * @return returer hostdata 
     */
    public JsonHostRepository getHostData() {
        return hostData;

    }
    
    /**
     * bruker modellen til å finne random tall
     * 
     * @return returnere random tall som streng
     */
    public String getRandomNumber(){
        return hostData.getNextId();
    }


    /**
     * gir oss hostdata ved hjelp av modellen 
     *
     * @return liste med host, henstigmesig når vi skal lage kanppene
     */
    public ArrayList<Host> getHosts(){
        return hostData.getHosts();
    }

    /**
     * fjerne host med gitt ID
     * 
     * @param ID ID gitt som streng 
     */
    public void removeHostWithID(String ID){
        hostData.deleteHost(ID);
        
    }

    /**
     * 
     * @param ID id gitt som streng
     * @return retuner hostobjekt til korsponderende ID
     */
    public Host getHostbyID(String ID){
        return hostData.getHostById(ID);
    }

    /**
     * retunere antall hosts ved å ta størrelsen på arraylisten 
     * 
     * @return tall med størrelse
     */
    public int getCardinalatyOfHosts(){
        return (hostData.getHosts()).size();
    }



    /**
     * Litt usikker på om jeg skulle gjøre denne i modellen eller controller 
     * git host navn og id som en array slik at vi kan bruke JTable
     * 
     * @return 2d arrays som inneholder host en kolonne med navn og en med ID
     */
    public String[][] getHostnameAndIdArray(){
        ArrayList<Host> hosts = getHosts();
        String[][] hostArray = new String[hosts.size()][2];
        for (int i = 0; i < hosts.size(); i++) {
            hostArray[i][0] = hosts.get(i).getName();
            hostArray[i][1] = hosts.get(i).getId();
        }
        return hostArray;
    }

    /**
     * retuerne enumnavnen som en arrays

     * Da kandropdowd meny av lage en arraylist 
     * @return gir enum som en array med streng
     */
    public String[] getEnumAsArray(){
        int amountOfHostsTypes = HostIcon.values().length;
        String[] enumStrings = new String[amountOfHostsTypes];
        int counter = 0;

        for (HostIcon elem : HostIcon.values()) {
            enumStrings[counter] = elem.name();
            counter += 1;
        }

        return enumStrings;
    }
}
