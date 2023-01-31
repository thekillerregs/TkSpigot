package tk.thekillerregs.tkspigot;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;


public final class TkSpigot extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        redis();
    }

    private void redis()
    {
        Jedis jedis = new Jedis("localhost", 6379, 5000);


        Pipeline pipeline = jedis.pipelined();
        pipeline.set("tk", "thekillerregs");
        pipeline.expire("tk", 30);
        pipeline.sync();
        System.out.println(jedis.get("tk"));

        Map<String, String> fullNames = new HashMap<>();
        fullNames.put("Tk", "thekillerregs");
        jedis.hmset("full_names", fullNames);
        System.out.println(jedis.hgetAll("full_names"));


        Bukkit.getScheduler().runTaskLater(this, () -> {
            System.out.println(jedis.get("tk"));

        }, 31*20);



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}







