package cc.dreamcode.antifavoring.placeholder;

import org.bukkit.entity.Player;

public interface PlaceholderReplacer {

    String apply(Player target, String text);

}
