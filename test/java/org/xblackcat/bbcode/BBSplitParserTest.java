package org.xblackcat.bbcode;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xBlackCat
 */
public class BBSplitParserTest extends TestCase {
    private static String splitByPartsI(String text) {
        List<Part> result = new LinkedList<Part>();

        Iterator<Part> i = new SplitIterator(new StringReader(text));

        while (i.hasNext()) {
            result.add(i.next());
        }

        return result.toString();
    }

    public void testSplitByPartsI() throws IOException {
        assertEquals("[Test]", splitByPartsI("Test"));
        assertEquals("[Test , [b], code, [/b]]", splitByPartsI("Test [b]code[/b]"));
        assertEquals("[[img], Test, [/img]]", splitByPartsI("[img]Test[/img]"));
        assertEquals("[[url=link], Test, [/url]]", splitByPartsI("[url=link]Test[/url]"));
        assertEquals("[[url=\"link\"], Test, [/url]]", splitByPartsI("[url=\"link\"]Test[/url]"));
        assertEquals("[[url=\"link[]\"], Test, [/url]]", splitByPartsI("[url=\"link[]\"]Test[/url]"));
        assertEquals("[[b], Test, [/b], [i], test, [/i]]", splitByPartsI("[b]Test[/b][i]test[/i]"));
        assertEquals("[[b], [i], Test, [/i], [/b]]", splitByPartsI("[b][i]Test[/i][/b]"));
        assertEquals("[Test, [img], test, [/img], test]", splitByPartsI("Test[img]test[/img]test"));

        assertEquals("[[url=link[], ]Test, [/url]]", splitByPartsI("[url=link[]]Test[/url]"));
    }
}
