package cl.talca.videogame.resources;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ResourcesManager {

    public static String AIRCRAFT_IMG = "aircraft";

    private Map<String, BufferedImage> imageMap;

    public ResourcesManager() {
        this.init();
    }

    private void init() {
        this.imageMap = new HashMap<String, BufferedImage>();
        try {
            this.imageMap.put(AIRCRAFT_IMG, ImageIO.read(getClass().getClassLoader().getResource("aircraft.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public BufferedImage get(String key) {
        return this.imageMap.get(key);
    }
}
