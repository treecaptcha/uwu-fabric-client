package io.github.ran.uwu.client.mixins;

import io.github.ran.uwu.client.Uwuifier;
import io.github.ran.uwu.client.config.UwuConfig;
import net.minecraft.client.network.ClientPlayerEntity;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.regex.Pattern;

@Mixin(value = ClientPlayerEntity.class, priority = -2)
public abstract class ClientPlayerEntityMixin {
    @ModifyVariable(method = "sendChatMessage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private String onSendChatMessage(String message) {
        if (UwuConfig.uwuifyCertainCommands && message.startsWith("/")) {
            if (!UwuConfig.uwuifyCommands.isEmpty()) {
                for (String regexCommands : UwuConfig.uwuifyCommands.split("\n")) {
                    try {
                        if (Pattern.compile(regexCommands).matcher(message).find()) {
                            String toBeUwuified;
                            return message.replaceAll(Pattern.quote(toBeUwuified = message.replaceAll(regexCommands, "")), Uwuifier.uwu(toBeUwuified));
                        }
                    } catch (Exception e) {
                        LogManager.getLogger("Uwuifer").error("Error while trying to compile regex command: " + regexCommands);
                    }
                }
            }
        }
        if (message.startsWith("/")) return message; // ignore commands
        return UwuConfig.uwuifyOutgoing ? Uwuifier.uwu(message) : message;
    }
}
