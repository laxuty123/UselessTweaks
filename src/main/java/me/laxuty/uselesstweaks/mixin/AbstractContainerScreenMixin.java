package me.laxuty.uselesstweaks.mixin;

import me.laxuty.uselesstweaks.UselessTweaks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    @Shadow
    @Nullable
    protected Slot hoveredSlot;

    @Inject(method = "renderSlot", at = @At("HEAD"), cancellable = true)
    public void uselesstweaks$renderSlot$hideOffHandSlot(GuiGraphics guiGraphics, Slot slot, CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.hideOffHandSlot) {
            if(slot == null) return;

            if(slot.index == 45) {
                ci.cancel();
            }

        }
    }

    @Inject(method = "mouseReleased", at = @At("HEAD"), cancellable = true)
    public void uselesstweaks$mouseReleased$hideOffHandSlot(double d, double e, int i, CallbackInfoReturnable<Boolean> cir) {
        if(UselessTweaks.config.miscellaneous.hideOffHandSlot) {
            if(hoveredSlot == null) return;

            if(hoveredSlot.index == 45) {
                cir.cancel();
            }
        }
    }

    @Inject(method = "slotClicked", at = @At("HEAD"), cancellable = true)
    public void uselesstweaks$slotClicked$hideOffHandSlot(Slot slot, int i, int j, ClickType clickType, CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.hideOffHandSlot) {
            if(slot == null) return;

            if(slot.index == 45) {
                ci.cancel();
            }
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Inject(method = "renderSlotHighlightBack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"), cancellable = true)
    public void uselesstweaks$renderSlotHighlightBack$hideOffHandSlot(GuiGraphics guiGraphics, CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.hideOffHandSlot) {
            if(hoveredSlot.index == 45) {
                ci.cancel();
            }
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Inject(method = "renderSlotHighlightFront", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"), cancellable = true)
    public void uselesstweaks$renderSlotHighlightFront$hideOffHandSlot(GuiGraphics guiGraphics, CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.hideOffHandSlot) {
            if(hoveredSlot.index == 45) {
                ci.cancel();
            }
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Inject(method = "renderTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;getItem()Lnet/minecraft/world/item/ItemStack;"), cancellable = true)
    public void uselesstweaks$renderTooltip$hideOffHandSlot(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.hideOffHandSlot) {
            if(hoveredSlot.index == 45) {
                ci.cancel();
            }
        }
    }
}