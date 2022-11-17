package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GUICommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
      if(!(sender instanceof Player))
      {
        sender.sendMessage("§cThis is a player-only command!");
        return false;
      }
      Player player = (Player) sender;
      Inventory inv = Bukkit.createInventory(player, 45, "§bMenu");


      //Teleport
      ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
      ItemMeta pearlMeta = pearl.getItemMeta();
      pearlMeta.setDisplayName("§dRandom Teleport");
      pearlMeta.setLore(Arrays.asList("§6Teleports to a random player"));
      pearl.setItemMeta(pearlMeta);

      inv.setItem(20, pearl);

      //Kys
      ItemStack sword = new ItemStack(Material.GOLDEN_SWORD, 1);
      ItemMeta swordMeta = sword.getItemMeta();
      swordMeta.setDisplayName("§4Kill Yourself");
      swordMeta.setLore(Arrays.asList("§5kys - Kassadin"));
      sword.setItemMeta(swordMeta);

      inv.setItem(22, sword);

      //Clear Inventory
      ItemStack bucket = new ItemStack(Material.BUCKET);
      ItemMeta bucketMeta = bucket.getItemMeta();
      bucketMeta.setDisplayName("§aClear Inventory");
      bucketMeta.setLore(Arrays.asList("§7Take the trash away."));
      bucket.setItemMeta(bucketMeta);

      inv.setItem(24, bucket);

      //Close GUI
      ItemStack barrier = new ItemStack(Material.BARRIER);
      ItemMeta barrierMeta = barrier.getItemMeta();
      barrierMeta.setDisplayName("§cClose Inventory");
      barrierMeta.setLore(Arrays.asList("§cFecha aí taok"));
      barrier.setItemMeta(barrierMeta);

      inv.setItem(0, barrier);

      //Green Panels
      ItemStack pane = new ItemStack((Material.LIME_STAINED_GLASS_PANE));
      ItemMeta paneMeta = pane.getItemMeta();
      paneMeta.setDisplayName("");
      pane.setItemMeta(paneMeta);
      for(int i: new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44})
      {
      inv.setItem(i, pane);
      }


        player.openInventory(inv);
        return false;
    }
}
