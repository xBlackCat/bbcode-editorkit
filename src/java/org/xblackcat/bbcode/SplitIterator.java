package org.xblackcat.bbcode;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/**
* Created by IntelliJ IDEA. User: Alexey Date: 12.06.11 Time: 14:16 To change this template use File | Settings | File
* Templates.
*/
class SplitIterator implements Iterator<Part> {
    private StringBuilder buf = new StringBuilder();

    private boolean braceOpen = false;
    private boolean quoteOpen = false;

    private final Reader source;

    private Part nextPart;

    public SplitIterator(Reader source) {
        this.source = source;
    }


    @Override
    public boolean hasNext() {
        try {
            nextPart = readNextPart();
        } catch (IOException e) {
            return false;
        }

        return nextPart != null;
    }

    @Override
    public Part next() {
        return nextPart;
    }

    @Override
    public void remove() {
    }

    private Part readNextPart() throws IOException {
        Part result = null;

        int v;
        while ((v = source.read()) != -1) {
            char c = (char) v;

            if (c == '[') {
                if (!braceOpen) {
                    if (buf.length() > 0) {
                        result = new Part(buf.toString());
                        buf.setLength(0);
                    }
                    braceOpen = true;
                }
                buf.append(c);
            } else if (c == '"' && braceOpen) {
                quoteOpen = !quoteOpen;
                buf.append(c);
            } else if (c == ']' && braceOpen && !quoteOpen) {
                buf.append(c);
                if (buf.length() > 0) {
                    result = new Part(buf.toString());
                    buf.setLength(0);
                }
                braceOpen = false;
            } else {
                buf.append(c);
            }

            if (result != null) {
                return result;
            }
        }

        if (buf.length() > 0) {
            result = new Part(buf.toString());
            buf.setLength(0);
        }

        return result;
    }
}
