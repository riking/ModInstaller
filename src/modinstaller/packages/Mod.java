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
public class Mod {
    

    public String mcVersion;
    public String subsection;
    public String name;
    public String modVersion;
    
    public String prettyName;
    public String shortDesc;
    public String longDesc;
    public String author;
    
    public boolean downloaded;

    public String forumURL;
    protected File downloadURL; //for cached files, this will be a local filename.
    
    public ArrayList<String> categories;
    public HashMap<String, Mod> dependencies;
    
    private boolean building;
    public Mod(String s1,String s2, String s3)
    {
        mcVersion = s1;
        subsection = s2;
        name = s3;
        building = true;
    }
    public void complete() { building = false; }
    void setFileInfo(File url)
    {
        downloadURL = url;
    }
    public String getFullyQualifiedName()
    {
        return mcVersion +'.'+ subsection +'.'+ name;
    }
    public File getDownloadPath()
    {
        return downloadURL;
    }
}
