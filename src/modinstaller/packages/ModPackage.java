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
public class ModPackage {
    

    public String mcVersion;
    public String subsection;
    public String name;
    public int internalVersion;
    
    public String prettyName;
    public String shortDesc;
    public String longDesc;
    
    public ArrayList<String> categories;
    
    protected File downloadURL;
    protected boolean cached = false;
    protected File cacheFile;
    public HashMap<String, ModPackage> dependencies;
    
    public String getFullyQualifiedName()
    {
        return mcVersion +'.'+ subsection +'.'+ name;
    }
    public File getDownloadPath()
    {
        return cached ? cacheFile : downloadURL;
    }
}
