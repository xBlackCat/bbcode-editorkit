package org.xblackcat.bbcode;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class TextBBTag extends ABBTag {
    public static final Iterator<BBTag> NO_ELEMENTS;

    static {
        Iterable<BBTag> bbTagIterable = Collections.emptySet();
        NO_ELEMENTS = bbTagIterable.iterator();
    }

    private String content;

    public TextBBTag(String content) {
        super(BBTagType.Text, "");
        this.content = content;
    }

    public TextBBTag(BBTag parent, String content) {
        super(parent, BBTagType.Text, "");
        this.content = content;
    }

    @Override
    public boolean add(BBTag bbTag) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public BBTag get(int index) {
        return null;
    }

    @Override
    public BBTag remove(int index) {
        return null;
    }

    @Override
    public boolean remove(BBTag o) {
        return false;
    }

    @Override
    public void add(int index, BBTag element) {

    }

    @Override
    public BBTag set(int index, BBTag element) {
        return null;
    }

    @Override
    public String getAttributeValue(String attributeName) {
        return null;
    }

    @Override
    public boolean add(BBAttribute bbTag) {
        return false;
    }

    @Override
    public boolean remove(BBAttribute o) {
        return false;
    }

    @Override
    public Collection<BBAttribute> attributes() {
        return Collections.emptySet();
    }

    @Override
    public Iterator<BBTag> iterator() {
        return NO_ELEMENTS;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return '<' + content + '>';
    }
}
