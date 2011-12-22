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
    public String name;
    public String modVersion;
    
    public String prettyName;
    public String shortDesc;
    public String longDesc;
    public String author;

    public String forumURL;
    protected File downloadURL; //for cached files, this will be a local filename.
    public boolean downloadSpecial; //mediafire etc etc 
            
    public ArrayList<String> categories;
    public ArrayList<Mod> dependencies;
    
    public static HashMap<String,Mod> lookup;
    
    private boolean building;
    public Mod(String s1, String s3)
    {
        mcVersion = s1;
        name = s3;
        building = true;
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
    
    public boolean equals(Mod other)
    {
        return getLookupName().equals(other.getLookupName());
    }
    
    void setFileInfo(File url, boolean special)
    {
        downloadURL = url;
        downloadSpecial = special;
    }
    public String getLookupName()
    {
        return mcVersion+'-'+name;
    }
    public File getDownloadPath()
    {
        return downloadURL;
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
