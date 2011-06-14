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
}
