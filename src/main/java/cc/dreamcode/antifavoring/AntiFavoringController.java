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
        HumanEntity humanEntity = event.getWhoClicked();
        if (!(humanEntity instanceof Player)) {
            return;
        }

        Player player = (Player) humanEntity;
        ItemStack holdingItem = event.getCursor();

        event.setCursor(this.itemFactory.createItemStack(player, holdingItem));
    }
}
