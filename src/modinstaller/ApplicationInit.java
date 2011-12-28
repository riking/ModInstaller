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
        int launchmode = 0b00000001;
        /**
         * f & 0b00000001: Use GUI
         * f & 0b00000010: Quick Launch
         * f & 0b00000100: Command-line Install
         * 
         */
        short i = 0;
        String s;
        while(i < args.length)
        {
            s = args[i];
            if(s.startsWith("-"))
            {
                if(s.equals("-l") && (i+1)>args.length)
                {
                    launchmode &= ~0b0001; // Just FYI - tilde is bitwise NOT.
                    launchmode |= 0b0010;
                    
                }
            }
        }
    }
}
