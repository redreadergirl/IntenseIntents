package pantrytracker.util;

import org.apache.commons.logging.impl.WeakHashtable;

import java.util.HashMap;
import java.util.Map;

public class Conversion {
    Map<String, Double> units;

    public Conversion() {
        units = new HashMap<String, Double>();
        units.put("fluid ounce", 128.0);
        units.put("fluid ounces", 128.0);
        units.put("gallon", 1.0);
        units.put("gallons", 1.0);
        units.put("pint", 8.0);
        units.put("pints", 8.0);
        units.put("cup", 16.0);
        units.put("cups", 16.0);
        units.put("quart", 4.0);
        units.put("quarts", 4.0);
    }

    public double convert(String given, String needed, int amount) {
        double ret = amount / units.get(given).doubleValue();
        ret *= units.get(needed).doubleValue();
        return ret;
    }
}
