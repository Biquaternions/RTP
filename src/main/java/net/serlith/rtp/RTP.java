package net.serlith.rtp;

import org.bukkit.plugin.java.JavaPlugin;

public final class RTP extends JavaPlugin {

    private static RTP INSTANCE;
    public static RTP getInstance() {
        return INSTANCE;
    }

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

}
