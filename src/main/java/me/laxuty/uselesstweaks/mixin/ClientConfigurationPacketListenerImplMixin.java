package me.laxuty.uselesstweaks.mixin;

import me.laxuty.uselesstweaks.UselessTweaks;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;
import net.minecraft.network.protocol.configuration.ClientboundResetChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConfigurationPacketListenerImpl.class)
public class ClientConfigurationPacketListenerImplMixin {

    @Inject(method = "handleResetChat", at = @At("HEAD"), cancellable = true)
    public void uselesstweaks$handleResetChat$ignoreResetChatPackets(ClientboundResetChatPacket clientboundResetChatPacket, CallbackInfo ci) {
        if(UselessTweaks.config.miscellaneous.chat.ignoreResetChatPackets) {
            ci.cancel();
        }
    }
}