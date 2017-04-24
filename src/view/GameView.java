package view;

import controller.BacMan;
import model.Arena;
import model.character.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Holy on 20-Apr-17.
 */
public class GameView extends JFrame {
  private int scale;
  protected JLabel[][] mapLabel;
  private static final int DEFAULT_SCALE = 40;

  public GameView(Player bacman) {
    scale = DEFAULT_SCALE;
    setTitle("Game Screen");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JPanel mapPanel = setMapPanel(bacman);
    JPanel infoPanel = setInfoPanel();
    JPanel gamePanel = new JPanel();

    GridBagLayout gridBag = new GridBagLayout();
    gamePanel.setLayout(gridBag);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gridBag.setConstraints(mapPanel, gbc);
    gamePanel.add(mapPanel);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gridBag.setConstraints(infoPanel, gbc);
    gamePanel.add(infoPanel);

    add(gamePanel);
    pack();
  }

  public JPanel setMapPanel(Player bacman) {
    JPanel mapPanel = new JPanel();
    mapPanel.setPreferredSize(new Dimension(1100, 950));
    mapPanel.setBackground(Color.black);
    mapPanel.setLayout(new GridLayout(Arena.getMapWidth(), Arena.getMapLength()));
    mapLabel = new JLabel[Arena.getMapWidth()][Arena.getMapLength()];
    for (int i = 0; i < Arena.getMapWidth(); i++) {
      for (int j = 0; j < Arena.getMapLength(); j++) {
        if((i == bacman.getI()) && (j == bacman.getJ())) {
          mapLabel[i][j] = createLabel(bacman.getImgPath());
          mapLabel[i][j].add(createLabel(Arena.getGrid(i, j).getImgPath()));
        } else {
          mapLabel[i][j] = createLabel(Arena.getGrid(i, j).getImgPath());
        }
      }
    }
    for (int i = 0; i < Arena.getMapWidth(); i++) {
      for (int j = 0; j < Arena.getMapLength(); j++) {
        mapPanel.add(mapLabel[i][j]);
      }
    }

    return mapPanel;
  }

  public JPanel setInfoPanel() {
    Font screenFont = null;
    Font scoreFont = null;
    try {
      screenFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("\\assets\\font\\pac-font.ttf").openStream());
      scoreFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("\\assets\\font\\game_over.ttf").openStream());
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    JPanel infoPanel = new JPanel();
    infoPanel.setBackground(new Color(0, 0, 0));
    infoPanel.setPreferredSize(new Dimension(500, 950));

    JLabel title = new JLabel("BACMAN", JLabel.CENTER);
    title.setBorder(new EmptyBorder(60, 10, 10, 10));
    title.setFont(screenFont.deriveFont(60f));
    title.setForeground(Color.WHITE);
    infoPanel.add(title);

    URL mascot_path = getClass().getResource("\\assets\\hooray.gif");
    JLabel mascot = new JLabel(new ImageIcon(mascot_path), JLabel.CENTER);
    mascot.setBorder(new EmptyBorder(100, 40, 10, 10));
    infoPanel.add(mascot);

    JLabel name = new JLabel("NAME: BACMAN", JLabel.LEFT);
    name.setBorder(new EmptyBorder(150, 10, 10, 10));
    name.setFont(scoreFont.deriveFont(120f));
    name.setForeground(Color.WHITE);
    infoPanel.add(name);

    JLabel score = new JLabel("SCORE: " + BacMan.score, JLabel.LEFT);
    score.setBorder(new EmptyBorder(10, 10, 10, 10));
    score.setFont(scoreFont.deriveFont(120f));
    score.setForeground(Color.WHITE);
    infoPanel.add(score);

    return infoPanel;
  }

  public boolean isGIF(String image_path) {
    return(image_path.contains(".gif"));
  }

  public JLabel createLabel(String image_path) {
    URL img_path = getClass().getResource(image_path);
    if(isGIF(image_path)) {
      return new JLabel(new ImageIcon(img_path));
    } else {
      ImageIcon imageIcon = new ImageIcon(img_path);
      Image image = imageIcon.getImage();
      Image newImage = image.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
      return new JLabel(new ImageIcon(newImage));
    }
  }

  public static void main(String[] args) {
    Player bacman = new Player();
    Arena arena = new Arena();
    LoadingView loadingView = new LoadingView();
    loadingView.setVisible(true);
    GameView gameView = new GameView(bacman);
    loadingView.setVisible(false);
    gameView.setVisible(true);
  }
}
