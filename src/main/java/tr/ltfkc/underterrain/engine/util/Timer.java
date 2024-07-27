package tr.ltfkc.underterrain.engine.util;

import java.util.HashMap;
import java.util.Map;

public class Timer {

    private Map<String, Float> timeRequiredMap = new HashMap<>();
    private Map<String, Float> elapsedTimeMap = new HashMap<>();
    private Map<String, Boolean> ringingMap = new HashMap<>();

    public void createChannel(String name, float timeRequired) {
        timeRequiredMap.put(name, timeRequired);
        elapsedTimeMap.put(name, 0f);
        ringingMap.put(name, false);
    }

    public void removeChannel(String name) {
        timeRequiredMap.remove(name);
        elapsedTimeMap.remove(name);
        ringingMap.remove(name);
    }

    public void clear() {
        timeRequiredMap.clear();
        elapsedTimeMap.clear();
        ringingMap.clear();
    }

    public void update(float delta) {
        for (String channel : timeRequiredMap.keySet()) {
            float elapsedTime = elapsedTimeMap.get(channel) + delta;
            if (elapsedTime >= timeRequiredMap.get(channel)) {
                elapsedTimeMap.put(channel, 0f);
                ringingMap.put(channel, true);
            } else {
                elapsedTimeMap.put(channel, elapsedTime);
                ringingMap.put(channel, false);
            }
        }
    }

    public boolean isRinging(String channel) {
        return ringingMap.get(channel);
    }
}
