package org.xblackcat.bbcode;

import org.apache.commons.collections.iterators.EmptyIterator;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA. User: Alexey Date: 12.06.11 Time: 14:41 To change this template use File | Settings | File
 * Templates.
 */
public class TextBBTag extends ABBTag {
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
    public Iterator<BBTag> iterator() {
        return EmptyIterator.INSTANCE;
    }

    @Override
    public String getContent() {
        return content;
    }
}
