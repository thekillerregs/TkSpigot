package tk.thekillerregs.tkspigot.navigation;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Cosmetic;
import tk.thekillerregs.tkspigot.instance.hats.Hat;
import tk.thekillerregs.tkspigot.instance.hats.HatType;
import tk.thekillerregs.tkspigot.instance.hats.HatsUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CosmeticListener implements Listener {
    private TkSpigot tkSpigot;

    public CosmeticListener(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getInventory() != null && e.getCurrentItem() != null) {
            //Cosmetics UI
            if (e.getView().getTitle().contains("Cosméticos")) {
                switch (e.getCurrentItem().getItemMeta().getLocalizedName().toLowerCase()) {

                    case "hats":
                        new HatsUI(tkSpigot, player);
                        e.setCancelled(true);
                        break;

                    default:
                        e.setCancelled(true);
                }
            }
            //Hats UI
            if (e.getView().getTitle().contains("Hats")) {
                HatType type = HatType.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());
                List<Cosmetic> active;
                if (tkSpigot.getActiveCosmetics().containsKey(player.getUniqueId())) {
                    active = tkSpigot.getActiveCosmetics().get(player.getUniqueId());
                    Iterator<Cosmetic> itr = active.listIterator();
                    while (itr.hasNext()) {
                        Cosmetic cosmetic = itr.next();
                        if (cosmetic instanceof Hat) {
                            cosmetic.disable();
                            itr.remove();
                            if (((Hat) cosmetic).getHatType() == type) {
                                player.sendMessage("§cVocê desabilitou o chapéu " + type.getDisplay() + "!");
                                player.closeInventory();
                                return;
                            }
                        }
                    }
                } else {
                    active = new ArrayList<>();
                }

                Hat hat = new Hat(tkSpigot, player, type);
                hat.enable();
                active.add(hat);
                tkSpigot.getActiveCosmetics().put(player.getUniqueId(), active);
                player.sendMessage("§aVocê equipou o chapéu " + type.getDisplay()+"!");
                player.closeInventory();
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
