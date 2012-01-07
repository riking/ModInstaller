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
    public static JarCacheManager jarcache;
    public static SettingsContainer settings;
    public static PackageManager packages;
    public static OSType OS;
    public static String dirPath;
    public static Version currentVersion;
    
    public static void main(String[] args)
    {
        int launchmode = /*0b00000000*/0;
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
            //do not make switch
            if(args[0].equals("launch"))
            {
                launchmode |= /*0b0011*/3;
            }
            else if(args[0].equals("install"))
            {
                launchmode |= /*0b0101*/5;
            }
        }
        setOS();
        dirPath = setPaths();
        settings = new SettingsContainer();
        settings.init();
        if((launchmode & /*0b0010*/2) == 0)
        {
            PackageManager.init();
        }
        jarcache = new JarCacheManager();
        jarcache.init();
    }
    public static void setOS()
    {
        String osStr = System.getProperty("os.name").toLowerCase();
        if(osStr.startsWith("win"))
        {
            if(osStr.equals("windows 7") || osStr.equals("windows 6.1"))
                OS = OSType.WINDOWS_7;
            else if(osStr.equals("windows xp"))
                OS = OSType.WINDOWS_XP;
            else
                OS = OSType.WINDOWS;
        }
        else if(osStr.startsWith("mac"))
            OS = OSType.MAC;
        else if(osStr.contains("nix") || osStr.contains("nux"))
            OS = OSType.LINUX;
        else if(osStr.contains("solaris"))
            OS = OSType.SOLARIS;
    }
            //note to self: to check archivers on linux, dpkg -s <package>
            //package is openjdk-6-jdk(jar)
            //also to install, gksudo.
    public static String setPaths()
    {
        if(OS.isMac()){
            System.out.println("Mac user!");
            return System.getProperty("user.home")+"/Library/Application Support/modinstall";
        }
        else if(OS.isLinux()){
            System.out.println("Linux/Unix/Solaris user!");
            return System.getProperty("user.home")+"/.modinstall";
        }
        else if(OS.isWindows()){
            System.out.println("Windows user!");
            return System.getenv("APPDATA")+"/.modinstall";
        }
        else
        {
            throw new RuntimeException("Unknown OS!");
        }
    }
}
