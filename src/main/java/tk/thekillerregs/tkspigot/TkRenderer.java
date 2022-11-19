package tk.thekillerregs.tkspigot;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class TkRenderer extends MapRenderer {


    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {

         try{
        BufferedImage image = ImageIO.read(new URL("https://64.media.tumblr.com/avatar_17667d80dd06_128.pnj"));
            mapCanvas.drawImage(0, 0, image);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
