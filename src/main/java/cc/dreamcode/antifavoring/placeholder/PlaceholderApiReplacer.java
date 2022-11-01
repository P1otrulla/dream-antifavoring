package cc.dreamcode.antifavoring.placeholder;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderApiReplacer implements PlaceholderReplacer {

    @Override
    public String apply(Player target, String text) {
        return PlaceholderAPI.setPlaceholders(target, text);
    }
}
