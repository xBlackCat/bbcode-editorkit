package org.xblackcat.bbcode.dom;

import junit.framework.TestCase;

import java.io.StringReader;

/**
 * @author xBlackCat Date: 14.06.11
 */
public class BBParserTest extends TestCase {
    public void testParser() throws BBParserException {
        {
            BBTag doc = new BBDomParser().parse(new StringReader("plain text"));

            TestUtils.debugPrint(doc);
            assertEquals("[] { <plain text> }", doc.toString());
        }

        {
            BBTag doc = new BBDomParser().parse(new StringReader("[i]italic[/i]"));

            TestUtils.debugPrint(doc);
            assertEquals("[] { [i] { <italic> } }", doc.toString());
        }

        {
            BBTag doc = new BBDomParser().parse(new StringReader("[i]italic[/i] plain [b] bold[/b] [i][b]bold-italic[/b][/i]"));

            TestUtils.debugPrint(doc);
            assertEquals("[] { [i] { <italic> } < plain > [b] { < bold> } < > [i] { [b] { <bold-italic> } } }", doc.toString());
        }

        {
            BBTag doc = new BBDomParser().parse(new StringReader("[i][b]bold-italic[/i][/b]"));

            TestUtils.debugPrint(doc);
            assertEquals("[] { [i] { <[b]bold-italic> } <[/b]> }", doc.toString());
        }

        {
            BBTag doc = new BBDomParser().parse(new StringReader("[b][i]no italic[/b]"));

            TestUtils.debugPrint(doc);
            assertEquals("[] { [b] { <[i]no italic> } }", doc.toString());
        }

        {
            BBTag doc = new BBDomParser().parse(new StringReader("[i]italic"));

            TestUtils.debugPrint(doc);
            assertEquals("[] { [i] { <italic> } }", doc.toString());
        }

        {
            BBTag doc = new BBDomParser().parse(new StringReader("[/i] [/b] [/i]"));

            TestUtils.debugPrint(doc);
            assertEquals("[] { <[/i]> < > <[/b]> < > <[/i]> }", doc.toString());
        }

    }

}
