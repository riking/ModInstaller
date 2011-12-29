/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller;

import java.awt.Color;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author kane
 */
public class SettingsContainer {

    static Color colorUpToDate;
    static Color colorMinorUpdate;
    static Color colorMajorUpdate;
    private static boolean init;
    private SettingsContainer inst;
    private File settingsFile;
    private Properties props;

    public SettingsContainer(File location) {
        settingsFile = location;
        props = new Properties();
    }

    public static Color getColorUpToDate() {
        return colorUpToDate;
    }

    public void init() {
        try {
            props.load(new java.io.FileInputStream(settingsFile));
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
        } catch (java.io.FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
