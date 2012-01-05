/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author kane
 */
public class SettingsContainer {

    public static Color colorUpToDate;
    public static Color colorMinorUpdate;
    public static Color colorMajorUpdate;
    public static String favA; // maybe should be jarfile. hmm.
    public static String favB; // nah.
    public static String favC;
    public static String favD;
    private static File settingsFile;
    private static Properties props;

    public SettingsContainer(String location) {
        settingsFile = new File(ApplicationInit.dirPath +"/options.properties");
        props = new Properties();
    }

    public void init() {
        try {
            props.load(new java.io.FileInputStream(settingsFile));
            try
            {
                String cTemp = props.getProperty("colorUpToDate", "147,196,125");
                String[] cTemp2 = cTemp.split(",");
                colorUpToDate = new Color(Integer.parseInt(cTemp2[0]),
                        Integer.parseInt(cTemp2[1]),
                        Integer.parseInt(cTemp2[2]));
                cTemp = props.getProperty("colorMinorUpdate", "204,0,0");
                cTemp2 = cTemp.split(",");
                colorMinorUpdate = new Color(Integer.parseInt(cTemp2[0]),
                        Integer.parseInt(cTemp2[1]),
                        Integer.parseInt(cTemp2[2]));
                cTemp = props.getProperty("colorMajorUpdate", "255,217,102");
                cTemp2 = cTemp.split(",");
                colorMajorUpdate = new Color(Integer.parseInt(cTemp2[0]),
                        Integer.parseInt(cTemp2[1]),
                        Integer.parseInt(cTemp2[2]));
            } catch (ArrayIndexOutOfBoundsException e) {
                warnMalformedColor();
            }
            favA = props.getProperty("favoriteModA",null);
            favB = props.getProperty("favoriteModB",null);
            favC = props.getProperty("favoriteModC",null);
            favD = props.getProperty("favoriteModD",null);
        } catch (java.io.FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    public String getProperty(String name) //for stuff we don't do in the init()
    {
        return props.getProperty(name);
    }
    public void setProperty(String name, String value)
    {
        props.setProperty(name, value);
    }
    
    private void warnMalformedColor()
    {
        System.out.println("WARNING: Malformed Color values in settings file");
    }
}
