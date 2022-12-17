package tk.thekillerregs.tkspigot.enchantment;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class AutoSmelting extends Enchantment implements Listener {


    public AutoSmelting() {
        super(NamespacedKey.minecraft("auto_smelting"));
    }

    @Override
    public String getName() {
        return "§4Auto Smelting";
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return true;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInHand() == null) return;
        if (!e.isDropItems()) return;
        if (!e.getPlayer().getInventory().getItemInHand().getItemMeta().hasEnchant(this)) return;
        switch (e.getBlock().getType()) {
            case IRON_ORE:
                e.setDropItems(false);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                break;
            case GOLD_ORE:
                e.setDropItems(false);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                break;
        }


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ItemStack is = new ItemStack(Material.NETHERITE_PICKAXE);
        is.addUnsafeEnchantment(Enchantment.getByKey(getKey()), 1);
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList("§4Auto Smelting I"));
        is.setItemMeta(im);
        e.getPlayer().getInventory().addItem(is);
    }

}
