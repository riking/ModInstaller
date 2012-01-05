/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller;

/**
 * Minecraft version enum.
 * @author kane
 */
public class Version {
    private String word;
    private String major;
    private String minor;
    public final int CURRENT = 4;
    public final int OUTDATED_URGENT = 3;
    public final int OUTDATED_PREFER = 2;
    public final int OUTDATED_MOLD = 1;
    public Version(String wrd, String majr, String minr)
    {
        word = wrd;
        major = majr;
        minor = minr;
        // Example usage: wrd = "Beta" majr = "1.8" minr = ".1"
        // Example usage: wrd = "" majr = "1.0" minr = ".0"
    }
    public String name()
    {
        return "Minecraft" + major+' '+minor;
    }
}
