package cc.dreamcode.antifavoring;

import cc.dreamcode.antifavoring.config.PluginConfig;
import cc.dreamcode.antifavoring.legacy.LegacyColorProcessor;
import cc.dreamcode.antifavoring.placeholder.Placeholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public final class AntiFavoringItemFactory {

    private final Placeholders placeholders;
    private final MiniMessage miniMessage;
    private final PluginConfig config;

    private final SimpleDateFormat dateFormat;

    public static final LegacyComponentSerializer AMPERSAND_SERIALIZER = LegacyComponentSerializer.builder()
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build();

    public AntiFavoringItemFactory(Placeholders placeholders, MiniMessage miniMessage, PluginConfig config) {
        this.placeholders = placeholders;
        this.miniMessage = miniMessage;
        this.config = config;

        this.dateFormat = new SimpleDateFormat(this.config.dateFormat);
    }

    public ItemStack createItemStack(Player player, ItemStack itemStack) {
        String formattedDate = this.dateFormat.format(System.currentTimeMillis());

        List<String> parsedLore = this.config.lore
                .stream()
                .map(lore -> lore.replace("{DATE}", formattedDate)
                        .replace("{ADMIN}", player.getName())
                        .replace("{AMOUNT}", String.valueOf(itemStack.getAmount())))
                .map(line -> this.placeholders.format(player, line))
                .map(line -> AMPERSAND_SERIALIZER.serialize(this.miniMessage.deserialize(line)))
                .collect(Collectors.toList());

        ItemStack newItemStack = itemStack.clone();

        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.setLore(parsedLore);

        newItemStack.setItemMeta(itemMeta);

        return newItemStack;
    }
}
