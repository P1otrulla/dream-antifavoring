package cc.dreamcode.antifavoring;

import cc.dreamcode.antifavoring.config.ConfigService;
import cc.dreamcode.antifavoring.config.PluginConfig;
import cc.dreamcode.antifavoring.hook.HookService;
import cc.dreamcode.antifavoring.legacy.LegacyColorProcessor;
import cc.dreamcode.antifavoring.placeholder.PlaceholderApiReplacer;
import cc.dreamcode.antifavoring.placeholder.Placeholders;
import com.google.common.base.Stopwatch;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bstats.bukkit.Metrics;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class AntiFavoring extends JavaPlugin {

    private ConfigService configService;
    private PluginConfig config;

    private MiniMessage miniMessage;

    private HookService hookService;

    private Placeholders placeholders;

    private AntiFavoringItemFactory itemFactory;

    @Override
    public void onEnable() {
        Stopwatch stopwatch = Stopwatch.createStarted();

        this.configService = new ConfigService(this.getDataFolder());
        this.config = this.configService.load(new PluginConfig());

        final Server server = this.getServer();

        this.miniMessage = MiniMessage.builder()
                .postProcessor(new LegacyColorProcessor())
                .build();

        this.hookService = new HookService(server);
        this.hookService.setup();

        this.placeholders = new Placeholders();

        if (this.hookService.isPlaceholderApiHook()) {
            this.placeholders.register(new PlaceholderApiReplacer());
        }

        this.itemFactory = new AntiFavoringItemFactory(this.placeholders, this.miniMessage, this.config);

        server.getPluginManager().registerEvents(new AntiFavoringController(this.itemFactory), this);

        Metrics metrics = new Metrics(this, 16768);

        this.getLogger().info("Successfully loaded in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }

    public ConfigService getConfigService() {
        return this.configService;
    }

    public PluginConfig getPluginConfig() {
        return this.config;
    }

    public MiniMessage getMiniMessage() {
        return this.miniMessage;
    }

    public HookService getHookService() {
        return this.hookService;
    }

    public Placeholders getPlaceholders() {
        return this.placeholders;
    }

    public AntiFavoringItemFactory getItemFactory() {
        return this.itemFactory;
    }
}
