package sgf.view.game;

import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sgf.controller.game.PlayerController;
import sgf.utilities.ThreadAndViewObservable;

/**
 * Player View Class.
 */
public class PlayerViewImpl extends AbstractPlayerView {

    private static final int COLUMN = 5;
    private static final long serialVersionUID = -1277354176566275849L;
    private boolean isControllerSet;
    private PlayerController playerController;
    private JLabel labelHP;
    private JLabel labelMoney;
    private JLabel labelScore;

    /**
     * Constructor that set the View.
     */
    public PlayerViewImpl() {
        super();
        this.setVisible(false);
    }

    @Override
    public void setController(final PlayerController controller) {
        if (!isControllerSet) {
            this.playerController = controller;
            this.isControllerSet = true;
        }
    }

    @Override
    public void start() {
        if (isControllerSet) {
            this.setVisible(true);
            ThreadAndViewObservable.register(this);
            setup();
        } else {
            throw new IllegalStateException("Cannot invoke start() if the controller has not been set.");
        }
    }

    @Override
    public void stop() {
        this.setVisible(false);
    }

    private void setup() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.labelHP = new JLabel();
        this.labelMoney = new JLabel();
        this.labelScore = new JLabel();
        this.update();
        this.add(labelHP);
        this.add(labelMoney);
        this.add(labelScore);
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        ((FlowLayout) this.getLayout()).setHgap(this.getParent().getWidth() / COLUMN);
        this.revalidate();
    }

    @Override
    public void update() {
        this.labelHP.setText("HP: " + this.playerController.getPlayer().getCurrentHP() + "/" + this.playerController.getPlayer().getMaxHP());
        this.labelMoney.setText("Money: " + this.playerController.getPlayer().getMoney());
        this.labelScore.setText("Score: " + this.playerController.getPlayer().getScore());
    }

    @Override
    public void loseGame() {
        JOptionPane.showMessageDialog(new JFrame(), "You lost the game, your progress will be saved and the game will close!!!", "The end", JOptionPane.ERROR_MESSAGE);
        ThreadAndViewObservable.stop();
        System.exit(0); 
    }
}
