/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;
import java.util.ArrayList;
import java.io.File;
import java.util.Properties;

/**
 *
 * @author kane
 */
public class Jarfile {
    public String nick;
    protected String version;
    public ArrayList<String> installed;
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
        try
        {
            Properties configReader = new Properties();
            configReader.load(new java.io.FileInputStream(settingsfile));
            nick = configReader.getProperty("name");
            version = configReader.getProperty("mcVersion");
            String installedmods = configReader.getProperty("installedMods");
            String[] temp = installedmods.split(",");
            
        }
        catch(java.io.IOException e)
        {
            
        }
        return this;
    }
}
