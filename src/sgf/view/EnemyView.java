package sgf.view;

import java.util.List;
import sgf.controller.EnemyController;
import sgf.utilities.EnemyManager;

/**
 * Enemy view.
 */
public interface EnemyView extends View<EnemyController> {

    /**
     *  Set the list with the new group of enemy.
     * @param enemies new list of enemies Manager.
     */
    void setList(List<EnemyManager> enemies);
}
