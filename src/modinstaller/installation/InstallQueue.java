/*
 * To change this template, choose Tools | Templates
 * and open the template zipIn the editor.
 */
package modinstaller.installation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import modinstaller.packages.Mod;
import modinstaller.packages.Jarfile;
import java.util.zip.ZipFile;
import java.util.zip.ZipException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import modinstaller.ApplicationInit;
import modinstaller.packages.DownloadType;
/**
 *
 * @author kane
 */
public class InstallQueue {
    public LinkedList<Mod> installList;
    public Jarfile dest;
    
    public InstallQueue(Jarfile target)
    {
        dest = target;
    }
    
    // This should walk the dependency tree and add all mods that aren't installed yet.
    public void addMod(Mod m)
    {
        if(!(dest.installed.contains(m.getIndexingName())))
        {
            addDependencies(m);
            installList.add(m);
        }
    }
    private void addDependencies(Mod m)
    {
        for (Mod d : m.dependencies)
        {
            addMod(d);
        }
    }
    
    public void install()
    {
        try(ZipFile destfile = new ZipFile(dest.jarlocation))
        {
            Mod current;
            current = installList.removeFirst();
            switch(current.downloadType)
            {
                case plainZip:
                case resourcesZip:
                    URL download = new URL(current.downloadURL);
                    BufferedReader in = new BufferedReader(new InputStreamReader(download.openStream()));
                    File temp = new File(ApplicationInit.dirPath + "/temp/download.zip");
                    temp.mkdirs();
                    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
                    {
                        String inputLine;
                        while((inputLine = in.readLine()) != null)
                        {
                            out.write(inputLine);
                        }
                    }
                    in.close();
                    in = null;
                    // Code copied from teh internetz
                    ZipInputStream zipIn = new ZipInputStream(new FileInputStream(temp));
                    ZipFile zf = new ZipFile(temp);
                    int fCount = 0;
                    for(Enumeration em = zf.entries(); em.hasMoreElements();)
                    {
                        String targetfile = em.nextElement().toString();
                        ZipEntry ze = zipIn.getNextEntry();
                        fout = new FileOutputStream(targetfile);
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = zipIn.read(buf)) > 0) {
                            fout.write(buf, 0, len);
                        }
                        fCount++;
                    }
            }
        }
        catch(MalformedURLException e)
        {
            //popup box
            System.out.println("Bad mod: Malformed URL!!");
        }
        catch(ZipException e)
        {
            
        }
        catch(IOException e)
        {
            
        }
    }
}
