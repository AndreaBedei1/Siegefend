package sgf.managers;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import sgf.model.enemies.Enemy;
import sgf.model.level.Level;
import sgf.model.level.Wave;
import sgf.model.map.Map;

/**
 * Manager of one level it say what is the next wave and the next enemy of the single wave.
 */
public class LevelManagerImpl implements LevelManager {

    private final Level level;
    private final List<Wave> waveList;
    private final Map map;
    private final Iterator<Wave> waveIter;
    private Iterator<Enemy> enemyIter;
    private Optional<Wave> currentWave;

    /**
     * Initialize the fild for the single level. 
     * @param level Is the current level.
     */
    public LevelManagerImpl(final Level level) {
        this.level = level;
        this.map = level.getMap(); // Get the map from the current level.
        this.waveList = level.getWaves(); // Get the waves from the current level.
        waveIter = waveList.iterator();
    }

    @Override
    public List<Wave> getWaves() {
        return List.copyOf(this.level.getWaves());
    }

    @Override
    public int getTotalWaves() {
        return this.waveList.size();
    }

    @Override
    public Level getCurrentLevel() {
        return this.level;
    }

    @Override
    public Wave getCurrentWave() {
        return this.currentWave.get();
    }

    @Override
    public void nextWave() {
        if (this.waveIter.hasNext()) { // If there is an other wave.
            this.currentWave = Optional.of(this.waveIter.next()); // Take the new wave. 
            this.enemyIter = this.getCurrentWave().getEnemies().iterator(); // Set the enemy iterator.
            this.level.setCurrentWave(this.level.getCurrentWave() + 1); // Incrise the number of the current wave.
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasNextWave() {
        return this.waveIter.hasNext();
    }

    @Override
    public Optional<Enemy> getNextEnemy() {
        if (this.hasNextEnemy()) {
            return Optional.of(this.enemyIter.next());
        }
        return Optional.empty(); 
    }

    @Override
    public boolean hasNextEnemy() {
        return this.enemyIter.hasNext();
    }

    @Override
    public Map getMap() {
        return this.map;
    }
}