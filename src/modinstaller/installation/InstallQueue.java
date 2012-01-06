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
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
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
    private String downDir;
    private String tempDir;
    private String stageDir;
    private File stageDirFile;
    private File tempDirFile;
    
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
        Mod current;
        stageDir = ApplicationInit.dirPath + "/staging/";
        stageDirFile =new File(stageDir);
        tempDir = ApplicationInit.dirPath + "/temp/";
        tempDirFile = new File(tempDir);
        try {
            stageDirFile.mkdir(); tempDirFile.mkdir();
        }
        catch(IOException e)
        {
            throw new RuntimeException("Failed to make staging directory");
        }
        while(!installList.isEmpty())
        {
            try
            {
                current = installList.removeFirst();
                switch(current.downloadType)
                {
                    case plainZip:
                    case resourcesZip:
                        File temp = new File(downDir+current.getFileName()+".zip");
                        if(!temp.exists())
                        {
                            URL download = new URL(current.downloadURL);
                            BufferedReader in = new BufferedReader(new InputStreamReader(download.openStream()));
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
                            out.close();
                        }
                        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(temp));
                        ZipFile zf = new ZipFile(temp);
                        int fCount = 0;
                        for(Enumeration em = zf.entries(); em.hasMoreElements();)
                        {
                            String targetfile = em.nextElement().toString();
                            ZipEntry ze = zipIn.getNextEntry();
                            new File(targetfile).mkdirs();
                            FileOutputStream fout = new FileOutputStream(tempDir+targetfile);
                            byte[] buf = new byte[1024];
                            int len;
                            while ((len = zipIn.read(buf)) > 0) {
                                fout.write(buf, 0, len);
                            }
                            fCount++;
                        }
                        if(current.downloadType == DownloadType.plainZip)
                        {
                            File dir = tempDirFile;
                            File[] fList = dir.listFiles();
                            File tmp;
                            for(File f : fList)
                            {
                                if((tmp = new File(stageDir + f.getName())).exists())
                                {
                                    tmp.delete();
                                }
                            
                                dir.renameTo(stageDirFile);
                        }
                        break;
                    case plain7z:
                        break;
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
}
