package Flyweight;

import java.util.HashMap;
import java.util.Map;

public class TreeFactory {
    
    private static Map<String ,TreeType> treeTypes = new HashMap<>();
    
    public static TreeType getTreeType(String name, String color, String texture) {
        // Key = unique combination of intrinsic state
        String key = name + "-" + color;

        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new TreeType(name, color, texture));
        } else {
            System.out.println("[Cache HIT] Reusing existing TreeType: " + name);
        }

        return treeTypes.get(key);
    }

    public static int getTotalTreeTypesCreated() {
        return treeTypes.size();
    }

}
