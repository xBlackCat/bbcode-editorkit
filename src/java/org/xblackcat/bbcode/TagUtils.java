package org.xblackcat.bbcode;

public class TagUtils {
    private enum ReadAttributeState {
        Null,
        Name,
        Quote,
        Value
    }

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
        String tagName = getTagName(part);
        if (tagName == null || part.charAt(1) == '/') {
            return null;
        }

        BBTag tag = new DefaultBBTag(tagName, BBTagType.Tag);

        ReadAttributeState state = ReadAttributeState.Null;

        String name = null;
        Character openQuote = null;

        StringBuilder buf = new StringBuilder();

        int pos = tagName.length() + 1;
        char c;
        if (pos != part.length() - 1) {
            while ((c = part.charAt(pos)) != ']') {
                pos++;
                switch (state) {
                    case Name:
                        if (c == '=') {
                            // Attribute name ended.
                            state = ReadAttributeState.Quote;
                            if (buf.length() > 0) {
                                name = buf.toString();
                                buf.setLength(0);
                            } else {
                                name = null;
                            }
                        } else if (Character.isSpaceChar(c)) {
                            // Attribute name ended - no value
                            state = ReadAttributeState.Null;
                            addAttribute(tag, buf.toString(), "");
                            buf.setLength(0);
                        } else {
                            buf.append(c);
                        }
                        break;
                    case Quote:
                        if (c == '"' || c == '\'') {
                            openQuote = c;
                            state = ReadAttributeState.Value;
                        } else if (!Character.isSpaceChar(c)) {
                            openQuote = null;
                            buf.setLength(0);
                            buf.append(c);
                            state = ReadAttributeState.Value;
                        } else {
                            state = ReadAttributeState.Null;
                            addAttribute(tag, name, "");
                        }
                        break;
                    case Value:
                        if (Character.isSpaceChar(c) || openQuote != null && openQuote == c) {
                            // Attribute value ended.
                            state = ReadAttributeState.Null;
                            addAttribute(tag, name, buf.toString());
                            buf.setLength(0);
                        } else {
                            buf.append(c);
                        }
                        break;
                    default:
                        if (Character.isLetter(c)) {
                            state = ReadAttributeState.Name;
                            buf.setLength(0);
                            buf.append(c);
                        } else if (c == '=') {
                            // Default attribute
                            state = ReadAttributeState.Quote;
                            buf.setLength(0);
                            name = null;
                        }
                        break;
                }
            }

            // Make last step according state
            switch (state) {
                case Name:
                    addAttribute(tag, buf.toString(), "");
                    break;
                case Value:
                    addAttribute(tag, name, buf.toString());
                    break;
                case Quote:
                    addAttribute(tag, name, "");
                    break;
            }
        }

        return tag;
    }

    private static void addAttribute(BBTag tag, String name, String value) {
        BBAttribute a = new DefaultBBAttribute(name);
        a.setValue(value);
        tag.add(a);
    }
}
