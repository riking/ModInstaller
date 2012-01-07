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
    private static HashMap<String,Mod> allMods;
    private static PackageManager instance;
    /**
     * Gets a Mod class by name and version.
     * @param name Internal name
     * @param ver Minecraft version.
     * @return 
     */
    public static Mod getMod(String name, Version ver)
    {
        return allMods.get(Mod.getIndexingName(name, ver));
    }
    public static void init()
    {
        
    }
    public static PackageManager getInstance()
    {
        return instance;
    }
    public static void addMod(Mod m)
    {
        allMods.put(m.getIndexingName(), m);
    }
}
