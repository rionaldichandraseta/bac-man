package model.element;

import model.GameElement;

/**
 * Kelas berisi Cookie yang tersebar pada Grid dalam Arena.
 *
 * @author Rionaldi Chandraseta - 13515077
 */
public class Cookie extends GameElement {

  /**
   * Atribut statis banyaknya Cookie yang tersisa.
   */
  protected static int cookieLeft = 0;
  /**
   * Atribut Cookie ada pada Arena atau tidak.
   */
  protected boolean available;
  /**
   * Atribut nilai Cookie yang akan ditambahkan ke score Player.
   */
  protected int value;

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Cookie dalam permainan.
   * </p>
   */
  public Cookie() {
    super("\\assets\\cookie.png");
    available = true;
    value = 10;
    cookieLeft++;
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Cookie dengan parameter available.
   * </p>
   *
   * @param available Cookie dapat dimakan atau tidak oleh Player.
   */
  public Cookie(boolean available) {
    super("\\assets\\cookie.png");
    this.available = available;
    value = 10;
    if (available) {
      cookieLeft++;
    }
  }

  /**
   * <p>
   * Constructor
   *
   * Menciptakan Cookie dengan parameter sprite dan value.
   * </p>
   *
   * @param sprite Path direktori sprite Cookie.
   * @param value Nilai Cookie yang akan ditambah ke score Player apabila Cookie tersebut dimakan.
   */
  public Cookie(String sprite, int value) {
    super(sprite);
    available = true;
    this.value = value;
    cookieLeft++;
  }

  /**
   * Fungsi getter jumlah cookie yang tersisa.
   *
   * @return Nilai cookieLeft.
   */
  public static int getCookieLeft() {
    return cookieLeft;
  }

  /**
   * Fungsi mengembalikan Cookie dapat dimakan atau tidak.
   *
   * @return Cookie dapat dimakan atau tidak.
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   * Fungsi mengembalikan nilai Cookie.
   *
   * @return Nilai Cookie.
   */
  public int getValue() {
    return value;
  }

  /**
   * Memasang value pada Cookie.
   *
   * @param value Nilai Cookie.
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Menghapus Cookie pada arena.
   * Cookie dihapus dengan mengubah nilai atribut available.
   */
  public void remove() {
    available = false;
    cookieLeft--;
  }
}
