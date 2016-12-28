package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(description = "Orbit around the overworld!",
        usage = "/<command> [<<power> | stop>]")
public class Command_orbit extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }

        Player sender = getName();

       

        FPlayer playerdata = plugin.pl.getPlayer(player);

        double strength = 10.0;

        if (args.length >= 2)
        {
            if (args[1].equals("stop"))
            {
               
                playerdata.stopOrbiting();
                return true;
            }

            try
            {
                strength = Math.max(1.0, Math.min(150.0, Double.parseDouble(args[1])));
            }
            catch (NumberFormatException ex)
            {
                msg(ex.getMessage(), ChatColor.RED);
                return true;
            }
        }

        player.setGameMode(GameMode.SURVIVAL);
        playerdata.startOrbiting(strength);

        player.setVelocity(new Vector(0, strength, 0));
        

        return true;
    }
}
