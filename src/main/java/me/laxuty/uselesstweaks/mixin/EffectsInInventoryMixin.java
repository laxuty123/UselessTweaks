package me.laxuty.uselesstweaks.mixin;

import me.laxuty.uselesstweaks.UselessTweaks;
import me.laxuty.uselesstweaks.config.categories.MiscellaneousCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EffectsInInventory.class)
public class EffectsInInventoryMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void uselesstweaks$render$hideStatusEffects(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        if(uselesstweaks$shouldHideStatusEffects()) {
            ci.cancel();
        }
    }

    @Unique
    public boolean uselesstweaks$shouldHideStatusEffects() {
        final var value = UselessTweaks.config.miscellaneous.hideStatusEffects;
        return value == MiscellaneousCategory.HideStatusEffects.BOTH ||
                value == MiscellaneousCategory.HideStatusEffects.INVENTORY;
    }
}