package cc.dreamcode.antifavoring.config;

import net.dzikoysk.cdn.entity.Description;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PluginConfig implements ReloadableConfig {

    @Description({
            "# Konfiguracja pluginu dreamAntiFavoring",
            "# Wspieramy miniMessage: https://docs.adventure.kyori.net/minimessage/",
            " ",
            "# Format do placeholdera {DATE}"
    })
    public String dateFormat = "dd.MM.yyyy HH:mm:ss";

    @Description({ " ", "# Wygląd lore przedmiotu"})
    public List<String> lore = Arrays.asList(
            "<gold>Wyciągnął: <red>{ADMIN}",
            "<gold>Data: <red>{DATE}",
            "<gold>Ilość: <red>{AMOUNT}",
            "",
            "&c&l Jeżeli posiadasz ten item,",
            "&c&l to zgłoś to administracji!"
    );

    @Override
    public Resource resource(File folder) {
        return Source.of(folder, "config.yml");
    }
}
