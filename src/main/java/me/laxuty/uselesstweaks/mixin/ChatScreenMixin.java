package me.laxuty.uselesstweaks.mixin;

import me.laxuty.uselesstweaks.UselessTweaks;
import me.laxuty.uselesstweaks.config.categories.MiscellaneousCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {

    @Shadow
    protected EditBox input;

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/ChatScreen;addWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"))
    public void uselesstweaks$init$compactInputBox(CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.chat.compactInputBox) {
            input.setWidth(uselesstweaks$calculateWidthForInputBox() - 12);
        }
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"), index = 2)
    private int uselesstweaks$render$guiGraphics$fill$compactInputBox(int i) {
        if(UselessTweaks.config.miscellaneous.chat.compactInputBox) {
            return uselesstweaks$calculateWidthForInputBox();
        } else return i;
    }

    @Unique
    public int uselesstweaks$calculateWidthForInputBox() {
        var chatWidth = ChatComponent.getWidth(Minecraft.getInstance().options.chatWidth().get());

        final var minimumWidth = UselessTweaks.config.miscellaneous.chat.compactInputBoxMinimumWidth;
        final var maximumWidth = UselessTweaks.config.miscellaneous.chat.compactInputBoxMaximumWidth;
        final var isMinimumGreater = UselessTweaks.config.miscellaneous.chat.isInputBoxMinimumGreaterThanTheMaximum();

        if(chatWidth > maximumWidth) {
            chatWidth = isMinimumGreater ? minimumWidth : maximumWidth;
        } else if(chatWidth < minimumWidth) {
            chatWidth = minimumWidth;
        }

        final var chatScale = (float) Minecraft.getInstance().options.chatScale().get().doubleValue();
        final var accurateWidth = Mth.ceil((float) chatWidth / chatScale);
        return accurateWidth + 12;
    }
}