package me.laxuty.uselesstweaks.mixin;

import me.laxuty.uselesstweaks.UselessTweaks;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundDeleteChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(method = "handleDeleteChat", at = @At("HEAD"), cancellable = true)
    public void uselesstweaks$handleDeleteChat$ignoreDeleteChatPackets(ClientboundDeleteChatPacket clientboundDeleteChatPacket, CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.chat.ignoreDeleteChatPackets) {
            ci.cancel();
        }
    }
}