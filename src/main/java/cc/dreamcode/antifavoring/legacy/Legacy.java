package cc.dreamcode.antifavoring.legacy;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public final class Legacy {

    public static final LegacyComponentSerializer AMPERSAND_SERIALIZER = LegacyComponentSerializer.builder()
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build();

    private Legacy() {
    }
}
