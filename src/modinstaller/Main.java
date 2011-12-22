/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller;
import java.io.File;

/**
 *
 * @author kane
 */
public class Main {
    
    public static void main(String[] args)
    {
        //load settings file
        
        //if GUI launch
        if(args.length == 0)
        {
            modinstaller.gui.ModInstallerGui.main(args);
        }
        else
        {
            
        }
    }
}
