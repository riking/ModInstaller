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
    public Version version;
    public ArrayList<String> installed;
    public File jarlocation;
    private File settingsfile;
    private Properties props = new Properties();
    
    public Jarfile(File settings)
    {
        this.settingsfile = settings;
    }
    public String niceVersion()
    {
        return version.dispName();
    }
    public Color getButtonColor()
    {
        if(version.equals(Version.current))
        {
            return SettingsContainer.colorUpToDate;
        }
        return new Color(255,255,255);
    }
    public Jarfile doSettingsFile()
    {
        try
        {
            props = new Properties();
            props.load(new java.io.FileInputStream(settingsfile));
            nick = props.getProperty("name");
            version = new Version(props.getProperty("vWord"),props.getProperty("vMajor"),props.getProperty("vMinor"));
            String installedmods = props.getProperty("installedMods");
            String[] temp = installedmods.split(",");
            installed.addAll(Arrays.asList(temp));
        }
        catch(java.io.IOException e)
        {
        }
        return this;
    }
    public void save()
    {
        
    }
}
