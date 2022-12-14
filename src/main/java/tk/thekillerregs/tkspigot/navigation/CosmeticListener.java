package tk.thekillerregs.tkspigot.navigation;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Cosmetic;

public class CosmeticListener implements Listener {
    private TkSpigot tkSpigot;

    public CosmeticListener(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() != null && e.getCurrentItem() != null) {
            if (e.getView().getTitle().contains("Cosméticos")) {

            }


        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (tkSpigot.getActiveCosmetics().containsKey(player.getUniqueId())) {
            for (Cosmetic cosmetic : tkSpigot.getActiveCosmetics().get(player.getUniqueId())) {
                cosmetic.disable();
            }

            tkSpigot.getActiveCosmetics().remove(player.getUniqueId());
        }

    }
}
