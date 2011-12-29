/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller;

/**
 *
 * @author kane
 */
public class ApplicationInit
{
    public static void main(String[] args)
    {
        int launchmode = 0b00000000;
        /**
         * f & 0b00000001: No GUI
         * f & 0b00000010: Quick Launch
         * f & 0b00000100: Command-line Install
         * 
         */
        short i = 0;
        String s;
        if(args.length != 0)
        {
            if(args[0].equals("launch"))
            {
                launchmode |= 0b0011;
            }
            else if(args[0].startsWith("URI"))
            {
                
            }
        }
        
    }
    
    private static void initJarfiles()
    {
    }
}
