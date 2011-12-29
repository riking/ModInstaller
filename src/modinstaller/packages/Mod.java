/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;

import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kane
 */
public class Mod
{   
    public String mcVersion;
    public String name;
    public String modVersion;
    
    public String prettyName;
    public String shortDesc;
    public String longDesc;
    public String author;
    public String forumURL;
    
    public String downloadURL;    
    public boolean hasResources;
            
    private ArrayList<String> categories;
    public List<Mod> dependencies;
    
    public static HashMap<String,Mod> lookup;
    
    private boolean building;
    /**
     * 
     * @param s1 Minecraft version.
     * @param s3 Internal, unique mod name.
     */
    public Mod(String s1, String s3)
    {
        mcVersion = s1;
        name = s3;
        building = true;
        dependencies = new ArrayList<>();
    }
    public void complete()
            throws ModAlreadyDefinedException
    {
        building = false;
        if(!lookup.containsKey(getLookupName()))
            lookup.put(getLookupName(), this);
        else
            throw new ModAlreadyDefinedException(getLookupName());
    }
    
    public void addDependencies(String depends)
    {
        dependencies.add(modinstaller.PackageManager.getInstance().get(depends,mcVersion));
    }
    public void addDependencies(String[] depends)
    {
        for(String s : depends)
        {
            dependencies.add(modinstaller.PackageManager.getInstance().get(s,mcVersion));
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
        return mcVersion +'-'+ name;
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
        return mcVersion +'-'+name+'-'+modVersion;
    }
    public java.net.URL getDownloadPath()
            throws java.net.MalformedURLException
    {
        return new java.net.URL(downloadURL);
    }
    public static Mod get(String qualifiedName)
    {
        return lookup.get(qualifiedName);
    }
    public static Mod getByNameAndVersion(String name, String version)
    {
        return lookup.get(version+'-'+name);
    }
}
