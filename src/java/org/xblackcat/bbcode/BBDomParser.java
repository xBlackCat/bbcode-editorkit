package org.xblackcat.bbcode;

import java.io.IOException;
import java.io.Reader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author xBlackCat
 */
public class BBDomParser {
    public BBTag parse(Reader r) throws IOException {
        Iterator<Part> i = new SplitIterator(r);

        BBTag root = new DefaultBBTag("", BBTagType.Root);

        BBTag currentTag = root;

        Deque<BBTag> tagStack = new LinkedList<BBTag>();

        while (i.hasNext()) {
            Part p = i.next();

            if (TagUtils.isTag(p)) {
                String tagName = TagUtils.getTagName(p.getContent());
                if (p.getContent().charAt(1) == '/') {
                    // The tag is closing tag.

                    // Look for open tag in stack. If match is not found - treat the tag as plain text.

                    // Simple check - is closing current tag?
                    if (tagName.equals(currentTag.getName())) {
                        BBTag tag = tagStack.pollFirst();

                        if (tag != currentTag) {
                            throw new BBParserException("Last tag in stack is not current tag!");
                        }

                        // Checks successful - ignore text content.
                        continue;
                    }

                    // Worse way - one of tags is not closed or open.
                    boolean hasOpenTag = false;
                    for (BBTag t : tagStack) {
                        if (tagName.equals(t.getName())) {
                            hasOpenTag = true;
                            break;
                        }
                    }

                    if (!hasOpenTag) {
                        // The tag has not been open: treat as plain text
                        currentTag.add(new TextBBTag(p.getContent()));
                        continue;
                    }

                    BBTag lastTag = tagStack.pollFirst();
                    while (lastTag != null && !lastTag.getName().equals(tagName)) {
                        BBTag lastTagParent = lastTag.getParent();
                        lastTagParent.remove(lastTag);

                        lastTagParent.add(new TextBBTag(lastTag.getContent()));

                        lastTag = tagStack.pollFirst();
                    }

                    if (lastTag == null) {
                        // No more tags in stack: it is can not be at all.
                        throw new BBParserException("Expecting open tag in stack.");
                    }
                } else {
                    currentTag = new DefaultBBTag(currentTag, tagName, BBTagType.Tag);

                    tagStack.addFirst(currentTag);
                }
            } else {
                currentTag.add(new TextBBTag(p.getContent()));
            }
        }

        return root;
    }
}
