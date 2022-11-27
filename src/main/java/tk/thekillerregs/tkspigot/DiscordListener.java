package tk.thekillerregs.tkspigot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordListener extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent e)
{
    if(e.getChannel().getId().equals("segredo") && !e.getAuthor().getId().equals("meia noite eu te conto"))
    {
        Bukkit.broadcastMessage("§7"+ e.getMember().getUser().getName() + "§f: " + e.getMessage().getContentRaw());
    }

}

}
