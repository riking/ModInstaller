/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modinstaller.packages;
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
    
    @Override
    public void startDocument()
    {
    }
    @Override
    public void endDocument()
    {
        
    }
    @Override
    public void skippedEntity(String name)
    {
        
    }
    @Override
    public void processingInstruction(String target, String data)
    {
        
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
    {}
    
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length){}
    
    @Override
    public void endPrefixMapping(String prefix) 
    {}
    @Override
    public void startPrefixMapping(String prefix, String uri) 
    {}
    @Override
    public void setDocumentLocator(Locator locator)
    {
    }
}
