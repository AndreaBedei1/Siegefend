package sgf.helpers;

import java.util.HashMap;
import java.util.Map;
import sgf.model.enemies.EnemyType;
import sgf.model.map.TileType;

/**
 * This class is the implementation of the interface PathLinker, which links some entities with their image path names.
 */
public class PathLinkerImpl implements PathLinker {
    private final Map<EnemyType, String> mapEnemy;
    private final Map<TileType, String> mapTile;

    /**
     * Simple constructor that class methods to fill its fields.
     */
    public PathLinkerImpl() {
        this.mapEnemy = new HashMap<>();
        this.mapTile = new HashMap<>();
        this.createEnemyMap();
        this.createTileMap();
    }

    @Override
    public Map<EnemyType, String> getEnemyMap() {
        return Map.copyOf(this.mapEnemy);
    }

    @Override
    public Map<TileType, String> getTileMap() {
        return Map.copyOf(this.mapTile);
    }

    private void createEnemyMap() {
        // Links every enemy type with the correct image that will be shown.
        this.mapEnemy.put(EnemyType.TANK, "tank.png");
        this.mapEnemy.put(EnemyType.PLANE, "plane.png");
        this.mapEnemy.put(EnemyType.HELICOPTER, "helicopter.png");
    }

    private void createTileMap() {
        // Links every tile type with the correct image that will be shown.
        this.mapTile.put(TileType.GRASS, "grass.png");
        this.mapTile.put(TileType.PATH, "sand.png");
        this.mapTile.put(TileType.START_PATH, "sand.png");
        this.mapTile.put(TileType.END_PATH, "sand.png");
        this.mapTile.put(TileType.WATER, "water.png");
    }
}