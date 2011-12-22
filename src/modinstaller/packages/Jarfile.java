/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;
import java.util.ArrayList;
import java.io.File;

/**
 *
 * @author kane
 */
public class Jarfile {
    public String nick;
    protected String version;
    public ArrayList<Mod> installed;
    public File jarlocation;
    private File settingsfile;
    
    public Jarfile(File settings)
    {
        this.settingsfile = settings;
    }
    public String niceVersion()
    {
        return "Minecraft" + version;
    }
    public Jarfile doSettingsFile()
    {
        
        return this;
    }
}
