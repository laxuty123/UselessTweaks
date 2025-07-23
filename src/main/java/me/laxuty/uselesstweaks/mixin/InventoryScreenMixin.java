package me.laxuty.uselesstweaks.mixin;

import me.laxuty.uselesstweaks.UselessTweaks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    @Redirect(method = "renderBg", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"))
    public void uselesstweaks$renderBg$graphicsFill$hideOffHandSlot(GuiGraphics instance, Function<ResourceLocation, RenderType> function, ResourceLocation resourceLocation, int i, int j, float f, float g, int k, int l, int m, int n) {
        instance.blit(function, resourceLocation, i, j, f, g, k, l, m, n);;

        if(UselessTweaks.config.miscellaneous.hideOffHandSlot) {
            instance.blit(
                    function,
                    resourceLocation,
                    i + 76,
                    j + 61,
                    78.0f,
                    4.0f,
                    18,
                    18,
                    m,
                    n);
        }
    }
}