package me.totalfreedom.totalfreedommod;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.util.FSync;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class AntiSpam extends FreedomService
{

    public static final int MSG_PER_CYCLE = 8;
    public static final int TICKS_PER_CYCLE = 2 * 10;
    //
    public BukkitTask cycleTask = null;

    public AntiSpam(TotalFreedomMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                cycle();
            }
        }.runTaskTimer(plugin, TICKS_PER_CYCLE, TICKS_PER_CYCLE);
    }

    @Override
    protected void onStop()
    {
        FUtil.cancel(cycleTask);
    }

    private void cycle()
    {
        for (Player player : server.getOnlinePlayers())
        {
            final FPlayer playerdata = plugin.pl.getPlayer(player);

            // TODO: Move each to their own section
            playerdata.resetMsgCount();
            playerdata.resetBlockDestroyCount();
            playerdata.resetBlockPlaceCount();
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event)
    {
        final Player player = event.getPlayer();
        String message = event.getMessage().trim();

        final FPlayer playerdata = plugin.pl.getPlayerSync(player);

        // Check for spam
        if (playerdata.incrementAndGetMsgCount() > MSG_PER_CYCLE)
        {
            FSync.bcastMsg(player.getName() + " was automatically kicked in the ass for spamming chat.", ChatColor.RED);
            FSync.autoEject(player, "Kicked in the ass for spamming chat.");

            playerdata.resetMsgCount();

            event.setCancelled(true);
            return;
        }

        // Check for message repeat
        if (playerdata.getLastMessage().equalsIgnoreCase(message))
        {
            FSync.playerMsg(player, "Try not to repeat. Ok?");
            event.setCancelled(false);
            return;
        }

        playerdata.setLastMessage(message);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        String command = event.getMessage();
        final Player player = event.getPlayer();
        final FPlayer fPlayer = plugin.pl.getPlayer(player);
        fPlayer.setLastCommand(command);

        if (fPlayer.allCommandsBlocked())
        {
            FUtil.playerMsg(player, "Your commands have been blocked by a fucking admin.", ChatColor.RED);
            event.setCancelled(true);
            return;
        }

        if (fPlayer.incrementAndGetMsgCount() > MSG_PER_CYCLE)
        {
            FUtil.bcastMsg(player.getName() + "was a dirty dirty Command spammer, lata bitch.", ChatColor.RED);
            plugin.ae.autoEject(player, "Banned for spamming commands.");

            fPlayer.resetMsgCount();
            event.setCancelled(true);
        }
    }

}
