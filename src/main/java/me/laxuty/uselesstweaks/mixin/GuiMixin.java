package me.laxuty.uselesstweaks.mixin;

import me.laxuty.uselesstweaks.UselessTweaks;
import me.laxuty.uselesstweaks.config.categories.MiscellaneousCategory;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
    public void uselesstweaks$renderEffects$hideStatusEffects(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if(uselesstweaks$shouldHideStatusEffects()) {
            ci.cancel();
        }
    }

    @Unique
    public boolean uselesstweaks$shouldHideStatusEffects() {
        final var value = UselessTweaks.config.miscellaneous.hideStatusEffects;
        return value == MiscellaneousCategory.HideStatusEffects.BOTH ||
                value == MiscellaneousCategory.HideStatusEffects.HUD;
    }
}