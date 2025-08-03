package net.serlith.rtp;

import com.mojang.brigadier.Command;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class RTPBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(@NotNull BootstrapContext bootstrapContext) {
        bootstrapContext.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(Commands.literal("rtp")
                    .executes(ctx -> {
                        CommandSender sender =  ctx.getSource().getSender();

                        if (!(sender instanceof Player player)) {
                            sender.sendMessage("You must be a player to run this command.");
                            return Command.SINGLE_SUCCESS;
                        }

                        Bukkit.getScheduler().runTaskAsynchronously(RTP.getInstance(), () -> {

                            ThreadLocalRandom random = ThreadLocalRandom.current();
                            World world = player.getWorld();
                            Block block = world.getHighestBlockAt(random.nextInt(-500, 500), random.nextInt(-500, 500));

                            Bukkit.getScheduler().runTask(RTP.getInstance(), () -> player.teleport(block.getLocation().add(0.0, 1.0, 0.0)));

                        });

                        return Command.SINGLE_SUCCESS;
                    })
                    .build(), "Random teleport using World$getHighestBlockAt");
        });
    }

}
