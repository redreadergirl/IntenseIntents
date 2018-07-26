package pantrytracker.util;

import org.apache.commons.logging.impl.WeakHashtable;

import java.util.HashMap;
import java.util.Map;

public class Conversion {
    Map<String, Double> units;

    public Conversion() {
        units = new HashMap<String, Double>();
        units.put("fluid ounce", 128.0);
        units.put("gallon", 1.0);
        units.put("pint", 8.0);
        units.put("cup", 16.0);
        units.put("quart", 4.0);
    }

    public double convert(String given, String needed, int amount) {
        return
    }
}
