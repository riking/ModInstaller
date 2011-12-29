/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller;

import java.awt.Color;
import java.io.File;
import java.io.BufferedReader;

/**
 *
 * @author kane
 */
public class SettingsContainer {
    static Color colorUpToDate = Color.GREEN.darker();
    static Color colorMinorUpdate = Color.RED.darker();
    static Color colorMajorUpdate = Color.YELLOW.darker();
    
    private static boolean init;
    private SettingsContainer inst;
    private File settingsFile;
    
    public SettingsContainer(File location)
    {
        settingsFile = location;
    }
    
    public Color getColorUpToDate()
    {
        if(!init) init();
        return colorUpToDate;
    }
    public void init()
    {
        Properties pReader = new Properties(settingsFile);
    }
}
