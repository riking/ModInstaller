/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;

/**
 *
 * @author kane
 */
public class Jarfile {
    String nick;
    String mcVersion;
    String niceVersion;
    
    public String getButtonDisplay()
    {
        return "<html>"+nick+"<br/>"+niceVersion+"</html>";
    }
}
