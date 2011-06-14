package org.xblackcat.bbcode;

/**
 * @author xBlackCat Date: 14.06.11
 */
public class BBParserTest {
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
