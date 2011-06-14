package org.xblackcat.bbcode;

public abstract class ABBTag implements BBTag {
    protected String name;
    protected BBTagType type;
    protected BBTag parent;

    public ABBTag(BBTagType type, String name) {
        this(null, type, name);
    }

    public ABBTag(BBTag parent, BBTagType type, String name) {
        this.parent = parent;
        this.type = type;
        this.name = name;
    }

    @Override
    public BBTagType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BBTag getParent() {
        return parent;
    }

    @Override
    public void setParent(BBTag parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ABBTag)) return false;

        ABBTag abbTag = (ABBTag) o;

        return type == abbTag.type && name.equals(abbTag.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    protected abstract void asPlainText(StringBuilder out);
}
