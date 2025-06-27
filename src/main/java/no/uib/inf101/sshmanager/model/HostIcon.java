package no.uib.inf101.sshmanager.model;


public enum HostIcon {

   UBUNTU("src/main/java/no/uib/resources/Icons/Ubuntu.png"),
   MACOS("src/main/java/no/uib/resources/Icons/MacOS.png"),
   WINDOWS("src/main/java/no/uib/resources/Icons/Windows.png"),
   NIXOS("src/main/java/no/uib/resources/Icons/Nix.png"),
   ARCH("src/main/java/no/uib/resources/Icons/Arch.png"),
   DEBIAN("src/main/java/no/uib/resources/Icons/Debian.png"), 
   LINUX("src/main/java/no/uib/resources/Icons/Linux.png"), 
   DEFAULT("src/main/java/no/uib/resources/Icons/-.png");

   // skal ikke være mulig å endre pathen 
    public final String iconPath;
 
    HostIcon(String iconPath) {
        this.iconPath = iconPath;
    }
}
