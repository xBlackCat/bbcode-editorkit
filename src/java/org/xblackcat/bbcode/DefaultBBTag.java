package org.xblackcat.bbcode;

import java.util.*;

/**
 * @author xBlackCat
 */
public class DefaultBBTag extends ABBTag {
    protected List<BBTag> children = new ArrayList<BBTag>();
    protected Set<BBAttribute> attributes = new TreeSet<BBAttribute>(new Comparator<BBAttribute>() {
        @Override
        public int compare(BBAttribute a1, BBAttribute a2) {
            if (a1.getName() == null) {
                return a2.getName() == null ? 0 : 1;
            } else {
                return a2.getName() == null ? -1 : a1.getName().compareTo(a2.getName());
            }
        }
    });

    protected DefaultBBTag(String name, BBTagType type) {
        this(null, name, type);
    }

    protected DefaultBBTag(BBTag parent, String name, BBTagType type) {
        super(parent, type, name);
    }

    @Override
    public Iterator<BBTag> iterator() {
        return children.iterator();
    }

    @Override
    public boolean isEmpty() {
        return children.isEmpty();
    }

    @Override
    public int size() {
        return children.size();
    }

    @Override
    public BBTag get(int index) {
        return children.get(index);
    }

    @Override
    public BBTag remove(int index) {
        BBTag bbTag = children.remove(index);
        if (bbTag != null) {
            removeParent(bbTag);
        }
        return bbTag;
    }

    @Override
    public boolean remove(BBTag o) {
        if (children.remove(o)) {
            removeParent(o);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(BBAttribute bbAttribute) {
        return attributes.add(bbAttribute);
    }

    @Override
    public boolean remove(BBAttribute o) {
        return attributes.remove(o);
    }

    @Override
    public Collection<BBAttribute> attributes() {
        return attributes;
    }

    @Override
    public boolean add(BBTag bbTag) {
        updateParent(bbTag);
        return children.add(bbTag);
    }

    @Override
    public void add(int index, BBTag bbTag) {
        updateParent(bbTag);
        children.add(index, bbTag);
    }

    @Override
    public BBTag set(int index, BBTag bbTag) {
        updateParent(bbTag);
        return children.set(index, bbTag);
    }

    @Override
    public String getContent() {
        StringBuilder content = new StringBuilder();
        return "";
    }

    public String asPlainText() {
        StringBuilder out = new StringBuilder();

        asPlainText(out);

        return out.toString();
    }

    @Override
    public String toString() {
        return
    }

    @Override
    protected void asPlainText(StringBuilder out) {
        out.append('[');
        out.append(name);
        if (attributes.size() > 0) {

        }

        out.append(']');
        out.append('[');
        out.append('[');
    }

    private void updateParent(BBTag bbTag) {
        if (bbTag == null) {
            throw new NullPointerException("Can not add null");
        }

        if (bbTag.getParent() != null) {
            bbTag.getParent().remove(bbTag);
        }

        bbTag.setParent(this);
    }

    private void removeParent(BBTag bbTag) {
        if (bbTag == null) {
            throw new NullPointerException("Can not add null");
        }

        bbTag.setParent(null);
    }
}
