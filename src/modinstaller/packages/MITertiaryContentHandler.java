/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;
import java.util.ArrayList;
import org.xml.sax.ContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

/**
 *
 * @author kane
 */
public class MITertiaryContentHandler implements ContentHandler
{
    private Mod working;
    private ArrayList<Mod> modArray;
    private boolean finished=false;
    private String origin;
    private String mcVersion;
    private String subsection;
    
    private byte charAction; //5 means include whitespace. 1 means normal.
    private String workingString;
    private String toSet;
   
    
    public MITertiaryContentHandler(String origin, String mcVersion)
    {
        this.origin = origin;
        this.mcVersion = mcVersion;
    }
    
    public ArrayList<Mod> getMods()
    {
        if(!finished) throw new RuntimeException("Tried to get mod list before parsing complete");
        return modArray;
    }
    
    @Override
    public void startDocument()
    {
        modArray = new ArrayList<>(10);
    }
    @Override
    public void endDocument()
    {
        finished=true;
    }

    @Override
    public void skippedEntity(String name)
    {
        java.util.logging.Logger.getLogger("modPackageReader").log(java.util.logging.Level.WARNING, 
                "Skipped entity in file {0}/{1}/{2}", new Object[]{origin, mcVersion, subsection});
    }
    @Override
    public void processingInstruction(String target, String data)
    {
        java.util.logging.Logger.getLogger("modPackageReader").log(java.util.logging.Level.WARNING, 
                "Unexpected processing instruction in file {0}/{1}/{2}", new Object[]{origin, mcVersion, subsection});
    }
    @Override
    public void startElement(String uri, String localname, String qname, Attributes atts)
    {
        if(qname.equalsIgnoreCase("mod"))
        {
            working = new Mod(mcVersion, atts.getValue("id"));
        }
        else if(qname.startsWith("mod."))
        {
             charAction = 1;
                 if(qname.endsWith("name")) {toSet="name";}
            else if(qname.endsWith("shortdesc")) {toSet="shortdesc";}
            else if(qname.endsWith("author")) {toSet="author";}
            else if(qname.endsWith("version")) {toSet="version";}
            else if(qname.endsWith("forum")) {toSet="forum";}
            else if(qname.endsWith("description")) { charAction = 5; toSet = "description";}
            else if(qname.endsWith("dependencies")) {toSet = "dependencies";}
            else if(qname.endsWith("categories")) {toSet = "categories";}
            else if(qname.endsWith("download")) {toSet = "download";}
        }
    }
    @Override
    public void endElement(String uri, String localname, String qname)
    {
        if(qname.equalsIgnoreCase("mod"))
        {
            working.complete();
            modArray.add(working);
            working = null;
        }
        else if(qname.startsWith("mod"))
        {
            switch(toSet)
            {
                case "name":
                    working.prettyName = workingString; break;
                case "shortdesc":
                    working.shortDesc = workingString; break;
                case "author":
                    working.author = workingString; break;
                case "version":
                    working.modVersion = workingString; break;
                case "forum":
                    working.forumURL = workingString; break;
                case "description":
                    working.longDesc = workingString; break;
                case "dependencies":
                    working.addDependencies(workingString.split(",")); break;
                case "categories":
                    working.addDependencies(workingString.split(",")); break;
                case "download":
                    working.downloadURL = workingString; break;
                default:
                    throw new RuntimeException("Programmer failure");
            }
        }
        this.workingString = "";
        toSet = "";
    }
    @Override
    public void characters(char[] ch, int start, int length) 
    {
        workingString = workingString.concat(new String(ch,start,length));
    }
    
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
    {
        if(charAction == 4)
        {
            workingString = workingString.concat(new String(ch,start,length));
        }
    }
    
    @Override
    public void endPrefixMapping(String prefix) 
    {}
    //do nothing.
    @Override
    public void startPrefixMapping(String prefix, String uri) 
    {}
    //do nothing.
    @Override
    public void setDocumentLocator(Locator locator)
    {}
    //do nothing.
}
