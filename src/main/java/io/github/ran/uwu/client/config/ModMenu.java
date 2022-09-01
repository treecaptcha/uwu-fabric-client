package io.github.ran.uwu.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (UwuConfig.INSTANCE == null) UwuConfig.load();
        return parent -> UwuConfig.INSTANCE.gui();
    }
}
