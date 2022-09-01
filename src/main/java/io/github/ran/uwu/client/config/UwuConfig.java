package io.github.ran.uwu.client.config;

import gg.essential.vigilance.Vigilance;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.IOException;

public class UwuConfig extends Vigilant {
    @Property(
            type = PropertyType.SWITCH,
            name = "Uwuify Outgoing Messages",
            description = "i-it uwuifies youw messages! >_<",
            category = "uwu"
    )
    public static boolean uwuifyOutgoing = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Uwuify Incoming Messages",
            description = "i-it uwuifies othew pwayew messages!",
            category = "uwu"
    )
    public static boolean uwuifyIncoming = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Uwuify Minecraft",
            description = "i-it uwuifies the minyecwaft cwient! >_<",
            category = "uwu"
    )
    public static boolean uwuifyMinecraft = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Uwuify Signs",
            description = "i-it uwuifies the signs!",
            category = "uwu"
    )
    public static boolean uwuifySigns = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Exempt Commands Using Regex",
            description = "you can use wegex to nyot uwui-ify cewtain pawts of a command!",
            category = "uwu", subcategory = "commands"
    )
    public static boolean uwuifyCertainCommands = false;
    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Regex Exemptions",
            description = "thi-is uses java wegex to nyot uwui-ify cewtain pawts of a command!",
            category = "uwu", subcategory = "commands"
    )
    public static String uwuifyCommands = """
            (\\/msg [0-9a-zA-Z_]{1,16})
            /me
            /say
            """;

    @Property(
            type = PropertyType.SWITCH,
            name = "Uwuify Font Renderer",
            description = "i-it uwuifies the font renderer! >_<",
            category = "uwu", subcategory = "font renderer"
    )
    public static boolean uwuifyFontRenderer = false;

    public static boolean isLoaded = false;

    public static UwuConfig INSTANCE;

    public static void load() {
        if (isLoaded) return;
        Vigilance.initialize();
        try {
            INSTANCE = new UwuConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UwuConfig() throws IOException {
        super(configFile(), "uwu");
        initialize();

        registerListener("uwuifyMinecraft", this::reloadResources);

        try {
            addDependency("uwuifyCommands", "uwuifyCertainCommands");
            addDependency("uwuifyFontRenderer", "uwuifyMinecraft");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        isLoaded = true;
    }

    public static File configFile() throws IOException {
        File file = new File("./config/uwu.toml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

    public void forceSaveConfig() {
        INSTANCE.markDirty();
        INSTANCE.writeData();
    }

    public void reloadResources(Object field) {
        uwuifyMinecraft = ((Boolean) field);
        forceSaveConfig();
        if (MinecraftClient.getInstance().currentScreen != null) {
            MinecraftClient.getInstance().currentScreen.close();
        }
        MinecraftClient.getInstance().setScreen(INSTANCE.gui());
        new Thread(() -> MinecraftClient.getInstance().reloadResources()).start();
    }
}
