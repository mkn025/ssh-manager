package no.uib.inf101.sshmanager.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonHostRepository implements HostRepository {
        
    private final int upperBoundForHostID = 1000; 
    private final String pathToJsonFile;
    private ArrayList<Host> hosts;

    /**
     * konstuktør som lar oss spesifisere json path 
     * bruker ikke til noe annet en teting enda:)     * 
     * 
     * @param pathToJsonFile path til json. Viktig å bruke relativ path fra roten til projektet
     */
    public JsonHostRepository(String pathToJsonFile){
        this.pathToJsonFile = pathToJsonFile;
        this.hosts = loadHosts();
        
    }


    // litt usikker på funksjon lånte fra internett
    // https://www.youtube.com/watch?v=LsiYA3MIAcs; 1.april 2025;
    private static class HostsWrapper {
        public List<Host> hosts;
    }


    @Override
    public ArrayList<Host> loadHosts() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(pathToJsonFile);
            if (file.exists()) {
                HostsWrapper wrapper = objectMapper.readValue(file, HostsWrapper.class);
                if (wrapper != null && wrapper.hosts != null) {
                    hosts = new ArrayList<>(wrapper.hosts);
                }
            } else {
                System.out.println("JSON file not found: " + pathToJsonFile);
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
        return hosts;
    }

 

    @Override
    public void addHost(Host host) {
        if (getHosteExistance(host.getId())){
            throw new IndexOutOfBoundsException("Host ID er tatt du må velge et annet");
        }
        hosts.add(host); 
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter();
        try {
            File file = new File(pathToJsonFile);
            HostsWrapper wrapper = new HostsWrapper();
            wrapper.hosts = hosts;
            // skriver til json filen
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
        } catch (IOException e) {
            System.err.println("Error writing to JSON file: " + e.getMessage());
        }
    }



    @Override
    public void deleteHost(String hostID) {
        boolean removed = hosts.removeIf(host -> host.getId().equals(hostID));
        if (removed) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                File file = new File(pathToJsonFile);
                HostsWrapper wrapper = new HostsWrapper();
                wrapper.hosts = hosts;
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, wrapper);
            } catch (IOException e) {
                System.err.println("Error writing to JSON file: " + e.getMessage());
            }
        } else {
            System.out.println("Host with ID " + hostID + " not found.");
        }
    }

    @Override
    public boolean getHosteExistance(String hostID) {
        for (Host h : hosts) {
            if (h.getId().equals(hostID)) {
                return true;
            }
        }
        return false;
    }

    public String getNextId() {
        String randomTall = randomNumber(1000);
        while (getHosteExistance(randomTall)) {
            randomTall = randomNumber(1000);
        }
        return randomTall;  
    }



    private String randomNumber(int upperBoundForHostID){
        return String.valueOf((int) (Math.random() * upperBoundForHostID) + 1);
    }


    public int getUpperBoundForHostID() {
        return upperBoundForHostID;
    }


    // burde kanske encalsualte bedre senre
    public ArrayList<Host> getHosts() {
        return hosts;
    }


    public Host getHostById(String hostID) {
        for (Host host : hosts) {
            if (host.getId().equals(hostID)) {
                return host;
            }
        }
        return null; 
    }

    
    


    

    
    
}
