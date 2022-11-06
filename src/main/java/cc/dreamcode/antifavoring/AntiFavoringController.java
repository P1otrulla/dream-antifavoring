package cc.dreamcode.antifavoring;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class AntiFavoringController implements Listener {

    private final AntiFavoringItemFactory itemFactory;

    public AntiFavoringController(AntiFavoringItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @EventHandler
    void onInventoryClick(InventoryClickEvent event) {
        final HumanEntity humanEntity = event.getWhoClicked();
        if (!(humanEntity instanceof Player)) {
            return;
        }

        final Player player = (Player) humanEntity;
        final Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null || !clickedInventory.getType().equals(InventoryType.CREATIVE)) {
            return;
        }

        final ItemStack holdingItem = event.getView().getCursor();
        if (holdingItem == null || player.hasPermission("antifavoring.spy")) {
            return;
        }

        final ItemStack replacedItem = this.itemFactory.createItemStack(player, holdingItem);
        event.getView().setCursor(replacedItem);
    }
}
