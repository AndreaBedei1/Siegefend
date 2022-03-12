package sgf.view.shop;

import sgf.controller.shop.ShopController;
import sgf.view.View;

/**
 *
 */
public interface ShopView extends View<ShopController> {

    /**
     * Disables all unselected options in the shop.
     */
    void disableAll();

    /**
     * Re-enables all options in the shop.
     */
    void enableAll();

}