package me.laxuty.uselesstweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.laxuty.uselesstweaks.UselessTweaks;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookComponent.class)
public abstract class RecipeBookComponentMixin {

    @Shadow protected abstract void setVisible(boolean bl);

    @Shadow public abstract boolean isVisible();

    @Inject(method = "updateScreenPosition", at = @At("HEAD"))
    public void uselesstweaks$updateScreenPosition$hideRecipeBook(int i, int j, CallbackInfoReturnable<Integer> cir) {
        if(UselessTweaks.config.miscellaneous.hideRecipeBook && isVisible()) {
            setVisible(false);
        }
    }

    @ModifyReturnValue(method = "updateScreenPosition", at = @At("RETURN"))
    public int uselesstweaks$updateScreenPosition$noRecipeBookShifting(int original, int width, int imageWidth) {
        if(UselessTweaks.config.miscellaneous.noRecipeBookShift) {
            return (width - imageWidth) / 2;
        } else {
            return original;
        }
    }

    @ModifyReturnValue(method = "getXOrigin", at = @At("RETURN"))
    public int uselesstweaks$getXOrigin$noRecipeBookShifting(int original) {
        if(UselessTweaks.config.miscellaneous.noRecipeBookShift) {
            return original - 77;
        } else {
            return original;
        }
    }

    @ModifyArg(method = "updateTabs", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookTabButton;setPosition(II)V"),
            index = 0)
    public int uselesstweaks$updateTabs$setPosition$noRecipeBookShifting(int original) {
        if(UselessTweaks.config.miscellaneous.noRecipeBookShift) {
            return original - 77;
        } else return original;
    }
}