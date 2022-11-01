package cc.dreamcode.antifavoring;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public final class AntiFavoringController implements Listener {

    private final AntiFavoringItemFactory itemFactory;

    public AntiFavoringController(AntiFavoringItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @EventHandler
    void onFavoring(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (event.getItemDrop().getItemStack() == null) {
            return;
        }

        if (player.hasPermission("antifavoring.spy")) {
            event.getItemDrop().setItemStack(this.itemFactory.createItemStack(player, event.getItemDrop().getItemStack()));
        }
    }
}
