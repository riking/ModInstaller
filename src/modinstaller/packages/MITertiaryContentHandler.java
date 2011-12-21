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
    private String debugDesc;
    
    public MITertiaryContentHandler(String filename)
    {
        this.debugDesc=filename;
    }
    public MITertiaryContentHandler()
    {
        this.debugDesc = "N/A";
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
        java.util.logging.Logger.getLogger("modPackageReader").log(java.util.logging.Level.WARNING, "Skipped entity in file ''{0}'': {1}", new Object[]{debugDesc, name});
    }
    @Override
    public void processingInstruction(String target, String data)
    {
        java.util.logging.Logger.getLogger("modPackageReader").log(java.util.logging.Level.WARNING, "Unexpected processing instruction in file{0}", debugDesc);
    }
    @Override
    public void startElement(String uri, String localname, String qname, Attributes atts)
    {
        
    }
    @Override
    public void endElement(String uri, String localname, String qname)
    {
        
    }
    @Override
    public void characters(char[] ch, int start, int length) 
    {
    
    }
    
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length){}
    //do nothing.
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
