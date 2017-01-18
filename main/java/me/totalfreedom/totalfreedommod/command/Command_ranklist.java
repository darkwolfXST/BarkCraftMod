package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(
        description = "a ranklist",
        usage = "/<command> [message...]",
        aliases = "rlist,ralist")
    public class Command_ranklist extends FreedomCommand
    {

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
            sender.sendMessage(
            " IMPOSTER" +
            " OP" +
            " SUPER_ADMIN" +
            " TELNET_ADMIN" +
            " SENIOR_ADMIN" +
            " SYSTEM_ADMIN" +
            " CO-OWNER" +
            " PLUGIN_DEV" +
            " STAFF_MANAGER" +
            " M1CE" +
            " OWNER");
    return true; 
    }
}