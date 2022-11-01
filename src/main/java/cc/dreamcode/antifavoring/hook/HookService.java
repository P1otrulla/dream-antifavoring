package cc.dreamcode.antifavoring.hook;

import org.bukkit.Server;

public final class HookService {

    private boolean placeholderApiHook;
    private final Server server;

    public HookService(Server server) {
        this.server = server;
    }

    public void setup() {
        this.placeholderApiHook = this.server.getPluginManager().isPluginEnabled("PlaceholderAPI");

        if (this.placeholderApiHook) {
            this.server.getLogger().info("Hooked into PlaceholderAPI");
        }
    }

    public boolean isPlaceholderApiHook() {
        return this.placeholderApiHook;
    }
}
