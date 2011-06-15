package org.xblackcat.bbcode;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author xBlackCat Date: 14.06.11
 */
public class BBParserTest extends TestCase {
    public void testParser() throws IOException {
        BBTag doc = new BBDomParser().parse(new StringReader("[i]italic[/i] plain [b] bold[/b] [i][b]bold-italic[/b][/i]"));

        TestUtils.debugPrint(doc);
    }

}
