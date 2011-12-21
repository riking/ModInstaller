/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;

import java.util.HashMap;
import java.io.File;
/**
 *
 * @author kane
 */
public class ModPackage {
    
    public HashMap<String, ModPackage> dependencies;
    public String version;
    public String category1;
    public String name;
    public File downloadURL;
    
    
    public String getFullyQualifiedName()
    {
        return version +'.'+ category1 +'.'+ name;
    }
}
