package org.xblackcat.bbcode;

import junit.framework.TestCase;

public class TagUtilsTest extends TestCase {
    public void testGetTagName() {
        assertEquals("tag", TagUtils.getTagName("[tag]"));
        assertEquals("tag", TagUtils.getTagName("[TAG]"));
        assertEquals("tag", TagUtils.getTagName("[/Tag]"));
        assertEquals("tag", TagUtils.getTagName("[tag attribute='test']"));
        assertEquals("tag", TagUtils.getTagName("[tag=parameter]"));
        assertEquals("tag", TagUtils.getTagName("[tag'n'bug]"));
        assertEquals("tag", TagUtils.getTagName("[/tag]"));
        assertEquals("таг", TagUtils.getTagName("[Таг]"));
        assertEquals("таг", TagUtils.getTagName("[/ТАГ]"));
        assertEquals("таг", TagUtils.getTagName("[/таг]"));


        assertNull(TagUtils.getTagName(""));
        assertNull(TagUtils.getTagName("[]"));
        assertNull(TagUtils.getTagName("[/]"));
        assertNull(TagUtils.getTagName(" [tag]"));
        assertNull(TagUtils.getTagName("[tag] "));
        assertNull(TagUtils.getTagName("[ tag]"));
        assertNull(TagUtils.getTagName("tag attribute='test'"));
        assertNull(TagUtils.getTagName("[ /tag]"));
    }

    public void testParseOpenTag() {
        assertEquals("Tag[tag]", TestUtils.toString(TagUtils.parseOpenTag("[tag]")));
        assertEquals("Tag[tag]", TestUtils.toString(TagUtils.parseOpenTag("[tag  ]")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag=1]")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag='1']")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag=\"1\"]")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag  =1]")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag  ='1']")));
        assertEquals("Tag[tag=\"1\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag  =\"1\"]")));
        assertEquals("Tag[tag name=\"value\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag name=value]")));
        assertEquals("Tag[tag name=\"value\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag name='value']")));
        assertEquals("Tag[tag name=\"value\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag name=\"value\"]")));
        assertEquals("Tag[tag name=\"\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag name]")));
        assertEquals("Tag[tag name=\"\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag name=]")));
        assertEquals("Tag[tag=\"1\" name=\"value\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag name=value =1]")));
        assertEquals("Tag[tag=\"1\" name=\"value\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag =1 name=value]")));
        assertEquals("Tag[tag=\"1\" name=\"\"]", TestUtils.toString(TagUtils.parseOpenTag("[tag name= =1]")));

        assertNull(TagUtils.parseOpenTag(""));
        assertNull(TagUtils.parseOpenTag("[ tag]"));
        assertNull(TagUtils.parseOpenTag("[/tag]"));
        assertNull(TagUtils.parseOpenTag(" [tag]"));
        assertNull(TagUtils.parseOpenTag("[tag] "));
        assertNull(TagUtils.parseOpenTag("[10tag]"));
        assertNull(TagUtils.parseOpenTag("[=tag]"));
    }

}
