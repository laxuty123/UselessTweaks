package me.laxuty.uselesstweaks.config;

import me.laxuty.uselesstweaks.SharedConstants;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class UselessTweaksConfig {

    public static UselessTweaksConfig load(@NotNull Path folder) {
        if(Files.exists(folder)) {
            final var configPath = folder.resolve("uselesstweaks.json");

            if(Files.exists(configPath)) {
                try {
                    return loadFrom(configPath);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to load config from '%s'"
                            .formatted(configPath.toString()), e);
                }
            }
        }
        return new UselessTweaksConfig();
    }

    private static UselessTweaksConfig loadFrom(@NotNull Path path) throws IOException {
        String serialized = Files.readString(path);
        return SharedConstants.GSON.fromJson(serialized, UselessTweaksConfig.class);
    }

    public synchronized void save(@NotNull Path folder) {
        final var configPath = folder.resolve("uselesstweaks.json");

        if(!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create directories for config '%s'"
                        .formatted(configPath.toString()), e);
            }
        }

        saveTo(configPath);
    }

    private void saveTo(@NotNull Path path) {
        String serialized = SharedConstants.GSON.toJson(this, UselessTweaksConfig.class);

        try {
            Files.writeString(path, serialized,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.DSYNC);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save config to %s"
                    .formatted(path.toString()), e);
        }
    }
}