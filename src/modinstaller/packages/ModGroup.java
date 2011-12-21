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
    public ModGroup(String s1,String s2, String s3)
    {
        super(s1,s2,s3);
    }
    @Override
    public File getDownloadPath()
    {
        return null;
    }
}
