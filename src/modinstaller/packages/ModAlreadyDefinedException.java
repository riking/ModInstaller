/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;

/**
 *
 * @author kane
 */
public class ModAlreadyDefinedException extends Exception
{
    public ModAlreadyDefinedException(String s)
    {
        super(s);
    }
            
    public ModAlreadyDefinedException()
    {
        super();
    }
}
