package org.xblackcat.bbcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagUtils {
    private static final Pattern ATTRIBUTE_PARSER = Pattern.compile("(\\w*)=(['\"])(.+?)\2");

    public static boolean isTag(Part p) {
        return isTag(p.getContent());
    }

    public static boolean isTag(String content) {
        return content != null &&
                content.length() >= 3 &&
                content.charAt(0) == '[' &&
                content.charAt(content.length() - 1) == ']';

    }

    /**
     * Extracts a tag name from string. If string do not matched '[text]' or '[/text]' - <code>null</code> is returned.
     *
     * @param tag
     *
     * @return
     */
    public static String getTagName(String tag) {
        if (!isTag(tag)) {
            return null;
        }

        StringBuilder str = new StringBuilder(5);

        int size = tag.length();
        boolean closing = tag.charAt(1) == '/';
        int curr = closing ? 2 : 1;

        if (curr >= size) {
            return null;
        }

        char currChar = Character.toLowerCase(tag.charAt(curr++));
        while (curr < size && Character.isLetterOrDigit(currChar)) {
            str.append(currChar);
            currChar = Character.toLowerCase(tag.charAt(curr++));
        }

        if (str.length() == 0) {
            return null;
        }

        return str.toString();
    }

    public static BBTag parseOpenTag(String part) {
        String name = getTagName(part);
        if (name == null || part.charAt(1) == '/') {
            return null;
        }

        BBTag tag = new DefaultBBTag(name, BBTagType.Tag);

        Matcher attr = ATTRIBUTE_PARSER.matcher(part);
        if (attr.find(name.length() + 1)) {
            do {
                BBAttribute a = new DefaultBBAttribute(attr.group(1));
                a.setValue(attr.group(3));

                tag.add(a);
            } while (attr.find());
        }

        return tag;
    }
}
