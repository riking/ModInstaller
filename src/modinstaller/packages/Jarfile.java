/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;
import java.util.Arrays;
import modinstaller.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Properties;
import java.awt.Color;

/**
 *
 * @author kane
 */
public class Jarfile {
    public String nick;
    protected Version version;
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
    public Color getButtonColor()
    {
        if(version.equals(PackageManager.currentVersion))
        {
            return SettingsContainer.colorUpToDate;
        }
        return new Color(255,255,255);
    }
    public Jarfile doSettingsFile()
    {
        try
        {
            Properties configReader = new Properties();
            configReader.load(new java.io.FileInputStream(settingsfile));
            nick = configReader.getProperty("name");
            version = Version.valueOf(configReader.getProperty("mcVersion"));
            String installedmods = configReader.getProperty("installedMods");
            String[] temp = installedmods.split(",");
            installed.addAll(Arrays.asList(temp));
        }
        catch(java.io.IOException e)
        {
            
        }
        return this;
    }
}
