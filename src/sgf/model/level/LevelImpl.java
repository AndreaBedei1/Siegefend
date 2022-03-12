package sgf.model.level;
import java.util.List;

import sgf.model.map.Map;
/**
 * Implementation of a level.
 *
 */
public class LevelImpl implements Level {

    private final List<Wave> waves;
    private int numberOfWave = 0;
    private final Map map;
    private final int levelID;

    /**
     * Creation of a level.
     * @param waves Are the waves in the level.
     * @param map Is the level map.
     */
    public LevelImpl(final List<Wave> waves, final Map map) {
        this.waves = waves;
        this.map = map;
        this.levelID = 1;
    }

    @Override
    public Map getMap() {
        return this.map;
    }

    @Override
    public List<Wave> getWaves() {
        return List.copyOf(this.waves);
    }

    @Override
    public int getLevelId() {
        return this.levelID;
    }

    @Override
    public int getCurrentWave() {
        return numberOfWave;
    }

    @Override
    public void setCurrentWave(final int currentWave) {
        this.numberOfWave = currentWave;
    }
}