package me.laxuty.uselesstweaks;

import com.moulberry.lattice.Lattice;
import com.moulberry.lattice.element.LatticeElements;
import me.laxuty.uselesstweaks.config.UselessTweaksConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.nio.file.Path;

public final class UselessTweaks implements ClientModInitializer {

    public static Path configFolder;
    public static UselessTweaksConfig config;
    public static LatticeElements configElements;

    @Override
    public void onInitializeClient() {
        configFolder = FabricLoader.getInstance().getConfigDir().resolve("uselesstweaks");
        config = UselessTweaksConfig.load(FabricLoader.getInstance().getConfigDir().resolve("uselesstweaks"));
        configElements = LatticeElements.fromAnnotations(Component.literal("UselessTweaks"), config);

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("uselesstweaks")
                    .executes(commandContext -> {
                        Minecraft.getInstance().schedule(() -> Minecraft.getInstance().setScreen(
                                Lattice.createConfigScreen(
                                        configElements,
                                        () -> config.save(configFolder),
                                        Minecraft.getInstance().screen
                                )
                        ));
                        return 0;
                    }));
        });
    }
}