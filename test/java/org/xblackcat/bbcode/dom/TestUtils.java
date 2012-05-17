package org.xblackcat.bbcode.dom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xBlackCat Date: 15.06.11
 */
class TestUtils {
    static String toString(BBTag t) {
        StringBuilder out = new StringBuilder();
        out.append(t.getType().name());
        out.append('[');
        out.append(t.getName());
        List<BBAttribute> attributes = new ArrayList(t.attributes());
        Collections.sort(attributes, new Comparator<BBAttribute>() {
            @Override
            public int compare(BBAttribute a1, BBAttribute a2) {
                if (a1.getName() != null) {
                    if (a2.getName() != null) {
                        return a1.getName().compareTo(a2.getName());
                    } else {
                        return -1;
                    }
                } else if (a2.getName() != null) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        if (attributes.size() > 0) {
            String defAttribute = t.getAttributeValue(null);
            if (defAttribute != null) {
                out.append("=\"");
                out.append(defAttribute);
                out.append('"');
            }

            for (BBAttribute a : attributes) {
                if (a.getName() != null) {
                    out.append(' ');
                    out.append(a.getName());
                    out.append("=\"");
                    out.append(a.getValue());
                    out.append('"');
                }
            }
        }

        out.append(']');
        return out.toString();

    }

    static void debugPrint(BBTag root) {
        debugPrint(root, "");
    }

    static void debugPrint(BBTag root, String prefix) {
        System.out.print(prefix);
        System.out.println(toString(root));

        for (BBTag t : root) {
            debugPrint(t, "  " + prefix);
        }
    }

}
