package cl.talca.videogame.resources;

import cl.talca.videogame.component.CoinType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ResourcesManager {

    public static String BACKGROUND_IMG = "background";
    public static String AIRCRAFT_IMG = "aircraft";
    public static String ASTEROID_IMG = "asteroid";
    public static String COIN_TYPE_LIVE = CoinType.LIVE.toString();
    public static String COIN_TYPE_POINTS_25 = CoinType.POINTS_25.toString();
    public static String COIN_TYPE_POINTS_50 = CoinType.POINTS_50.toString();

    private Map<String, BufferedImage> imageMap;

    public ResourcesManager() {
        this.init();
    }

    private void init() {
        this.imageMap = new HashMap<String, BufferedImage>();
        try {
            this.imageMap.put(AIRCRAFT_IMG, ImageIO.read(getClass().getClassLoader().getResource("img/aircraft.png")));
            this.imageMap.put(ASTEROID_IMG, ImageIO.read(getClass().getClassLoader().getResource("img/asteroid.png")));
            this.imageMap.put(COIN_TYPE_LIVE, ImageIO.read(getClass().getClassLoader().getResource("img/lifecoin.png")));
            this.imageMap.put(COIN_TYPE_POINTS_25, ImageIO.read(getClass().getClassLoader().getResource("img/midcoin.png")));
            this.imageMap.put(COIN_TYPE_POINTS_50, ImageIO.read(getClass().getClassLoader().getResource("img/highcoin.png")));
            this.imageMap.put(BACKGROUND_IMG, ImageIO.read(getClass().getClassLoader().getResource("img/background.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public BufferedImage get(String key) {
        return this.imageMap.get(key);
    }
}
