package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TestCommand extends Command{


    protected TestCommand() {
        super("test",
                 new String[]{"tst"},
                "Teste o comando!",
                "tk.test");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Bukkit.broadcastMessage("Deu certo chat");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }


}
