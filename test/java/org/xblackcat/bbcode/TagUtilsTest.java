package org.xblackcat.bbcode;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        assertEquals("Tag[tag]", toString(TagUtils.parseOpenTag("[tag]")));
        assertEquals("Tag[tag]", toString(TagUtils.parseOpenTag("[tag  ]")));
        assertEquals("Tag[tag=\"1\"]", toString(TagUtils.parseOpenTag("[tag=1]")));
        assertEquals("Tag[tag=\"1\"]", toString(TagUtils.parseOpenTag("[tag='1']")));
        assertEquals("Tag[tag=\"1\"]", toString(TagUtils.parseOpenTag("[tag=\"1\"]")));
        assertEquals("Tag[tag=\"1\"]", toString(TagUtils.parseOpenTag("[tag  =1]")));
        assertEquals("Tag[tag=\"1\"]", toString(TagUtils.parseOpenTag("[tag  ='1']")));
        assertEquals("Tag[tag=\"1\"]", toString(TagUtils.parseOpenTag("[tag  =\"1\"]")));
        assertEquals("Tag[tag name=\"value\"]", toString(TagUtils.parseOpenTag("[tag name=value]")));
        assertEquals("Tag[tag name=\"value\"]", toString(TagUtils.parseOpenTag("[tag name='value']")));
        assertEquals("Tag[tag name=\"value\"]", toString(TagUtils.parseOpenTag("[tag name=\"value\"]")));
        assertEquals("Tag[tag name=\"\"]", toString(TagUtils.parseOpenTag("[tag name]")));
        assertEquals("Tag[tag name=\"\"]", toString(TagUtils.parseOpenTag("[tag name=]")));
        assertEquals("Tag[tag=\"1\" name=\"value\"]", toString(TagUtils.parseOpenTag("[tag name=value =1]")));
        assertEquals("Tag[tag=\"1\" name=\"value\"]", toString(TagUtils.parseOpenTag("[tag =1 name=value]")));
        assertEquals("Tag[tag=\"1\" name=\"\"]", toString(TagUtils.parseOpenTag("[tag name= =1]")));
    }

    private String toString(BBTag t) {
        StringBuilder out = new StringBuilder();
        out.append(t.getType().name());
        out.append('[');
        out.append(t.getName());
        List<BBAttribute> attributes = new ArrayList(t.attributes());
        Collections.sort(attributes, new Comparator<BBAttribute>() {
            @Override
            public int compare(BBAttribute a1, BBAttribute a2) {
                if (a1.getName() != null) {
                    if (a2.getName() != null) {
                        return a1.getName().compareTo(a2.getName());
                    } else {
                        return -1;
                    }
                } else if (a2.getName() != null) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        if (attributes.size() > 0) {
            String defAttribute = t.getAttributeValue(null);
            if (defAttribute != null) {
                out.append("=\"");
                out.append(defAttribute);
                out.append('"');
            }

            for (BBAttribute a : attributes) {
                if (a.getName() != null) {
                    out.append(' ');
                    out.append(a.getName());
                    out.append("=\"");
                    out.append(a.getValue());
                    out.append('"');
                }
            }
        }

        out.append(']');
        return out.toString();

    }
}
