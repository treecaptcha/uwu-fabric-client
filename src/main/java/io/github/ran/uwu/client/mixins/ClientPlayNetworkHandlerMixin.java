package io.github.ran.uwu.client.mixins;

import io.github.ran.uwu.client.Uwuifier;
import io.github.ran.uwu.client.config.UwuConfig;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Optional;

// Am too lazy... Someone fix this
@Mixin(value = ClientPlayNetworkHandler.class, priority = -2)
public abstract class ClientPlayNetworkHandlerMixin {
    @ModifyVariable(method = "onGameMessage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private GameMessageS2CPacket onGameMessage(GameMessageS2CPacket packet) {
        if (UwuConfig.uwuifyIncoming) {
            try {
                packet = new GameMessageS2CPacket(uwufiedText(packet.content()), false);
            } catch (Exception ignored) { }
        }
        return packet;
    }

    @ModifyVariable(method = "onChatMessage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private ChatMessageS2CPacket onGameMessage(ChatMessageS2CPacket packet) {
        if (UwuConfig.uwuifyIncoming) {
            try {
                Optional<Text> optional = packet.message().unsignedContent();
                if (optional.isPresent()) optional = Optional.of(uwufiedText(optional.get()));
                SignedMessage a = new SignedMessage(packet.message().signedHeader(), packet.message().headerSignature(), packet.message().signedBody(), Optional.of(uwufiedText(packet.message().getContent())), packet.message().filterMask());
                packet = new ChatMessageS2CPacket(a, packet.serializedParameters());
            } catch (Exception ignored) { }
        }
        return packet;
    }

    @Unique
    private Text uwufiedText(Text text) {
        return Text.literal(Uwuifier.uwu(text.getString())).setStyle(text.getStyle());
    }
}
