package sgf.utilities;

import java.util.concurrent.Semaphore;

/**
 * Class which contains semaphores shared by other classes.
 */
public final class LockClass {
    private static final Semaphore ENEMY_SEMAPHORE = new Semaphore(1);
    private static final Semaphore TURRET_SEMAPHORE = new Semaphore(1);
    private static final Semaphore BULLET_SEMAPHORE = new Semaphore(1);

    private LockClass() { }

    /**
     * Returns the enemies' semaphore.
     * @return the enemies' semaphore
     */
    public static Semaphore getEnemySemaphore() {
        return ENEMY_SEMAPHORE;
    }

    /**
     * Returns the turrets' semaphore.
     * @return the turrets' semaphore
     */
    public static Semaphore getTurretSemaphore() {
        return TURRET_SEMAPHORE;
    }

    /**
     * Returns the bullets' semaphore.
     * @return the bullets' semaphore
     */
    public static Semaphore getBulletSemaphore() {
        return BULLET_SEMAPHORE;
    }
}
