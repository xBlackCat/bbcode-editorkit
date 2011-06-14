package org.xblackcat.bbcode;

import junit.framework.TestCase;

/**
 * @author xBlackCat Date: 14.06.11
 */
public class BBParserTest extends TestCase {
    public void testParser() {

    }

    private static void debugPrint(BBTag root) {
        debugPrint(root, "");

    }

    private static void debugPrint(BBTag root, String prefix) {
        System.out.print(prefix);
        System.out.println(root.toString());

    }
}
