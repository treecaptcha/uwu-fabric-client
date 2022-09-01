package io.github.ran.uwu.client.mixins;

import com.google.common.collect.ImmutableMap;
import io.github.ran.uwu.client.Uwuifier;
import io.github.ran.uwu.client.config.UwuConfig;
import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = TranslationStorage.class, priority = -2)
public abstract class TranslationStorageMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private static Map<String, String> translations(Map<String, String> translations) {
        UwuConfig.load();
        if (UwuConfig.uwuifyMinecraft) {
            uwuifiedTranslations = new HashMap<>();
            translations.forEach((key, value) -> uwuifiedTranslations.put(key, Uwuifier.uwu(value)));
            if (!uwuifiedTranslations.isEmpty()) {
                uwuifiedTranslations = ImmutableMap.copyOf(uwuifiedTranslations);
                if (!uwuifiedTranslations.isEmpty()) translations = uwuifiedTranslations;
            }
        }
        return translations;
    }

    @Unique
    private static Map<String, String> uwuifiedTranslations = new HashMap<>();
}
