package cc.dreamcode.antifavoring;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public final class AntiFavoringController implements Listener {

    private final AntiFavoringItemFactory itemFactory;

    public AntiFavoringController(AntiFavoringItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @EventHandler
    void onCreativeItemMove(InventoryCreativeEvent event) {
        final HumanEntity humanEntity = event.getWhoClicked();
        if (!(humanEntity instanceof Player)) {
            return;
        }

        final Player player = (Player) humanEntity;
        final ItemStack holdingItem = event.getCursor();
        if (player.hasPermission("antifavoring.spy")) {
            return;
        }

        final ItemStack replacedItem = this.itemFactory.createItemStack(player, holdingItem);
        event.setCursor(replacedItem);
    }
}
