/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;

import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author kane
 */
public class Mod
{   
    public String mcVersion;
    public String subsection;
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
    public ArrayList<Mod> dependencies;
    
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
    }
    public void complete() { building = false; }
    
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
}
