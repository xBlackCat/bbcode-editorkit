package org.xblackcat.bbcode;

import org.apache.commons.lang.StringUtils;

/**
 * @author xBlackCat
 */
class Part {
    private final String content;

    Part(String content) {
        if (StringUtils.isEmpty(content)) {
            throw new IllegalArgumentException("Part can not be empty.");
        }

        this.content = content;
    }

    String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }
}
