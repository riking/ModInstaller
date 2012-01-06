/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;
import modinstaller.PackageManager;
import modinstaller.Version;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kane
 */
public class Mod
{   
    public Version mcVersion;
    public String name;
    public String modVersion;
    
    public String prettyName;
    public String shortDesc;
    public String longDesc;
    public String author;
    public String forumURL;
    
    public String downloadURL;    
    public DownloadType downloadType;
    public boolean hasResources;
            
    private ArrayList<String> categories;
    public List<Mod> dependencies;
    
    private boolean building;
    /**
     * 
     * @param s1 Minecraft version.
     * @param s3 Internal, unique mod name.
     */
    public Mod(Version version, String id)
    {
        mcVersion = version;
        name = id;
        building = true;
        dependencies = new ArrayList<Mod>();
    }
    public void complete()
            throws ModAlreadyDefinedException
    {
        building = false;
        PackageManager.addMod(this);
    }
    
    public void addDependencies(String depends)
    {
        dependencies.add(PackageManager.getMod(depends,mcVersion));
    }
    public void addDependencies(String[] depends)
    {
        for(String s : depends)
        {
            dependencies.add(PackageManager.getMod(s,mcVersion));
        }
    }
    public void addDependencies(java.util.Collection<Mod> depends)
    {
        dependencies.addAll(depends);
    }
    
    
    public boolean equals(Mod other)
    {
        return (this.getIndexingName().equals(other.getIndexingName()));
    }
    public boolean equalsNeedsUpdate(Mod other)
    {
        return (this.getJarfileName().equals(other.getJarfileName()));
    }
    /**
     * Gets the name of the mod used in lookups.
     * @return Lookup-able name.
     */
    public String getIndexingName()
    {
        return mcVersion.name() +'-'+ name;
    }
    public static String getIndexingName(String name, Version mcVersion)
    {
        return mcVersion.name() +'-'+ name;
    }
    public static String getIndexingName(String name, String mcVersion)
    {
        return mcVersion +'-'+ name;
    }
    /**
     * Gets the name of the mod used for writing jarfile descriptors.
     * Includes mod version so that updates can be performed.
     * @return 
     */
    public String getJarfileName()
    {
        return mcVersion.name() +'-'+name+'-'+modVersion;
    }
    /**
     * Used for download caches.
     * @return 
     */
    public String getFileName()
    {
        return getJarfileName();
    }
    public java.net.URL getDownloadPath()
            throws java.net.MalformedURLException
    {
        return new java.net.URL(downloadURL);
    }
}
