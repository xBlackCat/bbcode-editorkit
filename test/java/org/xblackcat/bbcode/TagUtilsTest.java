package org.xblackcat.bbcode;

import junit.framework.TestCase;

public class TagUtilsTest extends TestCase {
    public void testGetTagName() {
        assertEquals("tag", BBDomParser.getTagName("[tag]"));
        assertEquals("tag", BBDomParser.getTagName("[TAG]"));
        assertEquals("tag", BBDomParser.getTagName("[/Tag]"));
        assertEquals("tag", BBDomParser.getTagName("[tag attribute='test']"));
        assertEquals("tag", BBDomParser.getTagName("[tag=parameter]"));
        assertEquals("tag", BBDomParser.getTagName("[tag'n'bug]"));
        assertEquals("tag", BBDomParser.getTagName("[/tag]"));
        assertEquals("таг", BBDomParser.getTagName("[Таг]"));
        assertEquals("таг", BBDomParser.getTagName("[/ТАГ]"));
        assertEquals("таг", BBDomParser.getTagName("[/таг]"));


        assertNull(BBDomParser.getTagName(""));
        assertNull(BBDomParser.getTagName("[]"));
        assertNull(BBDomParser.getTagName("[/]"));
        assertNull(BBDomParser.getTagName(" [tag]"));
        assertNull(BBDomParser.getTagName("[tag] "));
        assertNull(BBDomParser.getTagName("[ tag]"));
        assertNull(BBDomParser.getTagName("tag attribute='test'"));
        assertNull(BBDomParser.getTagName("[ /tag]"));
    }

    public void testParseOpenTag() {
        BBDomParser parser = new BBDomParser();

        assertEquals("Tag[tag]", TestUtils.toString(parser.parseOpenTag("[tag]")));
        assertEquals("Tag[tag]", TestUtils.toString(parser.parseOpenTag("[tag  ]")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(parser.parseOpenTag("[tag=1]")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(parser.parseOpenTag("[tag='1']")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(parser.parseOpenTag("[tag=\"1\"]")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(parser.parseOpenTag("[tag  =1]")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(parser.parseOpenTag("[tag  ='1']")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(parser.parseOpenTag("[tag  =\"1\"]")));
        assertEquals("Tag[tag name=\"value\"]", TestUtils.toString(parser.parseOpenTag("[tag name=value]")));
        assertEquals("Tag[tag name=\"value\"]", TestUtils.toString(parser.parseOpenTag("[tag name='value']")));
        assertEquals("Tag[tag name=\"value\"]", TestUtils.toString(parser.parseOpenTag("[tag name=\"value\"]")));
        assertEquals("Tag[tag name=\"\"]", TestUtils.toString(parser.parseOpenTag("[tag name]")));
        assertEquals("Tag[tag name=\"\"]", TestUtils.toString(parser.parseOpenTag("[tag name=]")));
        assertEquals("Tag[tag=\"1\" name=\"value\"]", TestUtils.toString(parser.parseOpenTag("[tag name=value =1]")));
        assertEquals("Tag[tag=\"1\" name=\"value\"]", TestUtils.toString(parser.parseOpenTag("[tag =1 name=value]")));
        assertEquals("Tag[tag=\"1\" name=\"\"]", TestUtils.toString(parser.parseOpenTag("[tag name= =1]")));

        assertNull(parser.parseOpenTag(""));
        assertNull(parser.parseOpenTag("[ tag]"));
        assertNull(parser.parseOpenTag("[/tag]"));
        assertNull(parser.parseOpenTag(" [tag]"));
        assertNull(parser.parseOpenTag("[tag] "));
        assertNull(parser.parseOpenTag("[10tag]"));
        assertNull(parser.parseOpenTag("[=tag]"));
    }

}
