package controller;

import model.Arena;
import model.VisibilityGraph;
import model.character.Blinky;
import model.character.Clyde;
import model.character.Inky;
import model.character.Pinky;
import model.character.Player;
import model.element.Cookie;
import view.CreditsView;
import view.GameOverView;
import view.GameView;
import view.LoadingView;
import view.TitleView;

/**
 * Kelas berisi semua aspek game BacMan.
 *
 * @author Oktavianus Handika - 13515035
 */
public class BacMan {

  /**
   * Score Player.
   */
  public static int score;
  /**
   * Controller untuk objek Player pada permainan.
   */
  private static PlayerController bac;

  /**
   * Arena permainan BacMan.
   */
  private static Arena gameArena;

  /**
   * Controller untuk objek Ghost pada permainan.
   */
  private static GhostController[] ghostList;
  /**
   * Penanda permainan sudah berakhir atau belum.
   */
  private static boolean gameEnd;
  /**
   * Tampilan permainan BacMan.
   */
  private GameView gameView;

  /**
   * Mengembalikan score Player.
   *
   * @return score yang didapat Player.
   */
  public static int getScore() {
    return score;
  }

  /**
   * Mengembalikan apakah permainan berakhir atau tidak.
   *
   * @return gameEnd permainan sudah berakhir atau belum.
   */
  public static boolean isGameEnd() {
    return gameEnd;
  }

  /**
   * Menambahkan score Player sejumlah value.
   *
   * @param value nilai yang ditambahkan ke score Player.
   */
  public static void addScore(int value) {
    score += value;
  }

  /**
   * Mengubah nilai gameEnd berdasarkan salah satu kondisi.
   * 1. Semua cookie telah dimakan.
   * 2. Player dan Ghost berada pada petak yang sama.
   *
   */
  public static void updateGameEnd() {
    if ((Cookie.getCookieLeft() == 0)
        || ((Player.getPlayerI() == ghostList[0].getGhost().getI())
            && (Player.getPlayerJ() == ghostList[0].getGhost().getJ()))
        || ((Player.getPlayerI() == ghostList[1].getGhost().getI())
            && (Player.getPlayerJ() == ghostList[1].getGhost().getJ()))
        || ((Player.getPlayerI() == ghostList[2].getGhost().getI())
            && (Player.getPlayerJ() == ghostList[2].getGhost().getJ()))
        || ((Player.getPlayerI() == ghostList[3].getGhost().getI())
            && (Player.getPlayerJ() == ghostList[3].getGhost().getJ()))) {
      gameEnd = true;
    }
  }

  /**
   * Program utama.
   *
   * @param args Command line argument.
   */
  public static void main(String[] args) {
    gameEnd = false;
    score = 0;
    gameArena = new Arena();
    new VisibilityGraph();
    bac = new PlayerController();
    ghostList = new GhostController[4];
    ghostList[0] = new GhostController('a');
    ghostList[1] = new GhostController('b');
    ghostList[2] = new GhostController('c');
    ghostList[3] = new GhostController('d');
    TitleView titleView = new TitleView();
    titleView.visibility = true;
    titleView.setVisible(true);
    while (TitleView.visibility) {
      System.out.println();
    }
    LoadingView loadingView = new LoadingView();
    loadingView.setVisible(true);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    GameView gameView = new GameView(bac, (Blinky) ghostList[0].getGhost(),
        (Inky) ghostList[2].getGhost(),
        (Pinky) ghostList[1].getGhost(), (Clyde) ghostList[3].getGhost());
    loadingView.setVisible(false);
    loadingView.dispose();
    gameView.setVisible(true);
    bac.start();
    gameView.start();
    for (int i = 0; i < 4; i++) {
      ghostList[i].start();
    }
    while (!gameEnd) {
      updateGameEnd();
    }
    GameOverView gameOverView = new GameOverView();
    titleView.dispose();
    gameView.setVisible(false);
    gameOverView.setVisible(true);
    gameView.dispose();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    CreditsView creditsView = new CreditsView();
    gameOverView.setVisible(false);
    gameOverView.dispose();
    creditsView.setVisible(true);
    creditsView.dispose();
  }
}
