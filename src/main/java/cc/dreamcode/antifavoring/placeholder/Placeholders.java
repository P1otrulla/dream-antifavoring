package cc.dreamcode.antifavoring.placeholder;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Placeholders {

    private final Set<PlaceholderReplacer> replacers = new HashSet<>();

    public String format(Player target, String text) {
        for (PlaceholderReplacer replacer : this.replacers) {
            text = replacer.apply(target, text);
        }

        return text;
    }

    public void register(PlaceholderReplacer replacer) {
        this.replacers.add(replacer);
    }
}
