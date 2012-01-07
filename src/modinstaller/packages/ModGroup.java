/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;

import java.io.File;
/**
 *
 * @author kane
 */
public class ModGroup extends Mod
{
    public ModGroup(modinstaller.Version s1, String s3)
    {
        super(s1,s3);
    }
    @Override
    public java.net.URL getDownloadPath()
    {
        return null;
    }
}
