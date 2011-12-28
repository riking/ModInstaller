/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller;

import modinstaller.packages.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
/**
 *
 * @author kane
 */
public class PackageManager {
    private HashMap<String,Mod> allMods;
    private static PackageManager instance;
    
    private LinkedList<Mod> installQueue;
    /**
     * Gets a Mod class by name and version.
     * @param name Internal name
     * @param version Minecraft version.
     * @return 
     */
    public Mod get(String name, String version)
    {
        return allMods.get(Mod.getIndexingName(name, version));
    }
    public static PackageManager getInstance()
    {
        return instance;
    }
    
    void markDependencies(Mod m)
    {
        if(installQueue.contains(m)) return;
        installQueue.addLast(m);
        for(Mod another : m.dependencies)
        {
            this.markDependencies(another);
        }
    }
}
