package me.laxuty.uselesstweaks.mixin;

import me.laxuty.uselesstweaks.UselessTweaks;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractRecipeBookScreen.class)
public class AbstractRecipeBookScreenMixin {

    @Inject(method = "initButton", at = @At("HEAD"), cancellable = true)
    public void uselesstweaks$initButton$hideRecipeBook(CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.hideRecipeBook) {
            ci.cancel();
        }
    }
}