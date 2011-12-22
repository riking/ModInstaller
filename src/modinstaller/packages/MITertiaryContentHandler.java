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
    
    private byte charAction;
    /**
     * 0: ignore
     * 1: is a string
     * 2: is a boolean
     * 3: is a URL
     * 4: DO NOT USE
     * 5: comma separated values
     */
    private String temp;
    private boolean tempBool;
    private String toSet;
    
    public MITertiaryContentHandler(String origin, String mcVersion, String subsection)
    {
        this.origin = origin;
        this.mcVersion = mcVersion;
        this.subsection = subsection;
    }
    @Override
    public void startDocument()
    {
        modArray = new ArrayList<Mod>(10);
    }
    @Override
    public void endDocument()
    {
        finished=true;
    }
    public ArrayList<Mod> getMods()
    {
        if(!finished) throw new RuntimeException("Tried to get mod list before parsing complete");
        return modArray;
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
            working = new Mod(mcVersion, subsection, atts.getValue("id"));
        }
        else if(qname.startsWith("mod."))
        {
                 if(qname.endsWith("name")) { charAction = 1; toSet="name";}
            else if(qname.endsWith("shortdesc")) { charAction = 1; toSet="shortdesc";}
            else if(qname.endsWith("author")) { charAction = 1; toSet="author";}
            else if(qname.endsWith("version")) { charAction = 1; toSet="version";}
            
            else if(qname.endsWith("cache")) { charAction = 2; toSet="cache";}
            
            else if(qname.endsWith("download")) {
                charAction = 3;
                toSet="download";
                if(atts.getLength() != 0)
                {
                    if("special".equals(atts.getLocalName(0)))
                        tempBool = Boolean.parseBoolean(atts.getValue(0));
                }
            }
            else if(qname.endsWith("forum")) { charAction = 3; toSet="forum";}
            
            else if(qname.endsWith("description")) { charAction = 1; toSet = "description";}
            
            else if(qname.endsWith("dependencies")) { charAction = 5; toSet = "dependencies";}
            else if(qname.endsWith("categories")) { charAction = 5; toSet = "categories";}
        }
    }
    @Override
    public void endElement(String uri, String localname, String qname)
    {
        if(qname.equalsIgnoreCase("mod"))
        {
            modArray.add(working);
            working = null;
        }
        else if(qname.startsWith("mod"))
        {
            if("name".equals(toSet)) working.prettyName = temp;
            else if("shortdesc".equals(toSet)) working.shortDesc = temp;
            else if("author".equals(toSet)) working.author = temp;
            else if("version".equals(toSet)) working.modVersion = temp;
            else if("description".equals(toSet)) working.longDesc = temp;
            else if("download".equals(toSet)) working.setFileInfo(new java.io.File(temp));
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) 
    {
    
    }
    
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
    {
        if(charAction == 4)
        {
            temp = temp.concat(new String(ch,start,length));
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
