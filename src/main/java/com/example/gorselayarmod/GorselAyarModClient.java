package com.example.gorselayarmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.AoMode;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.GraphicsMode;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

/**
 * C  -> Tum gorsel ayarlarini EN DUSUK seviyeye ceker.
 * V  -> Tum gorsel ayarlarini EN YUKSEK seviyeye ceker.
 * X  -> Ozel profil: Gorus mesafesi 32 chunk, parlaklik %100 (full),
 *       bulutlar kapali, parcaciklar en az, golgeler kapali,
 *       kare hizi sinirsiz. Bu bes ayar disindaki her sey
 *       Minecraft'in varsayilan (fabrika) degerlerine doner.
 *
 * NOT: Alan/metot adlari Yarn mappings 1.21.11 surumune gore yazilmistir.
 * Bu mod tamamen istemci (client) taraflidir; herhangi bir sunucuya
 * (vanilla, Fabric, Paper vb.) baglaninca da sorunsuz calisir, sunucunun
 * bu modu bilmesine gerek yoktur. Farkli bir Minecraft/Yarn surumu
 * kullanirsan bazi metot isimleri degismis olabilir; IDE'nin otomatik
 * tamamlamasi ile kontrol et.
 */
public class GorselAyarModClient implements ClientModInitializer {

    private static KeyBinding lowestKey;
    private static KeyBinding highestKey;
    private static KeyBinding customKey;

    // 1.21.9+ ile birlikte tus kategorileri artik string yerine
    // KeyBinding.Category nesnesi ile tanimlaniyor.
    private static final KeyBinding.Category CATEGORY =
            KeyBinding.Category.create(Identifier.of("gorselayarmod", "general"));

    @Override
    public void onInitializeClient() {
        lowestKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.gorselayarmod.lowest",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                CATEGORY
        ));

        highestKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.gorselayarmod.highest",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                CATEGORY
        ));

        customKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.gorselayarmod.custom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (lowestKey.wasPressed()) {
                setAllLowest(client);
                notifyPlayer(client, "Gorsel ayarlar EN DUSUK seviyeye cekildi.");
            }
            while (highestKey.wasPressed()) {
                setAllHighest(client);
                notifyPlayer(client, "Gorsel ayarlar EN YUKSEK seviyeye cekildi.");
            }
            while (customKey.wasPressed()) {
                setCustomProfile(client);
                notifyPlayer(client, "Ozel gorsel profil uygulandi (Chunk 32 / Parlaklik Full / Bulut Kapali / Parcacik Az / Golge Kapali / FPS Sinirsiz).");
            }
        });
    }

    private void notifyPlayer(MinecraftClient client, String message) {
        if (client.player != null) {
            client.player.sendMessage(Text.literal("[GorselAyarMod] " + message), true);
        } else if (client.inGameHud != null) {
            client.inGameHud.setOverlayMessage(Text.literal("[GorselAyarMod] " + message), false);
        }
    }

    /** C tusu: Tum gorsel ayarlari en dusuk / en performansli seviyeye ceker. */
    private void setAllLowest(MinecraftClient client) {
        GameOptions o = client.options;

        o.getViewDistance().setValue(2);
        o.getSimulationDistance().setValue(5);
        o.getGraphicsMode().setValue(GraphicsMode.FAST);
        o.getCloudRenderMode().setValue(CloudRenderMode.OFF);
        o.getParticles().setValue(ParticlesMode.MINIMAL);
        o.getEntityShadows().setValue(false);
        o.getMaxFps().setValue(30);
        o.getGamma().setValue(0.0);
        o.getBiomeBlendRadius().setValue(0);
        o.getAo().setValue(AoMode.OFF);
        o.getBobView().setValue(false);
        o.getAttackIndicator().setValue(AttackIndicator.OFF);

        client.options.write();
    }

    /** V tusu: Tum gorsel ayarlari en yuksek / en kaliteli seviyeye ceker. */
    private void setAllHighest(MinecraftClient client) {
        GameOptions o = client.options;

        o.getViewDistance().setValue(32);
        o.getSimulationDistance().setValue(32);
        o.getGraphicsMode().setValue(GraphicsMode.FABULOUS);
        o.getCloudRenderMode().setValue(CloudRenderMode.FANCY);
        o.getParticles().setValue(ParticlesMode.ALL);
        o.getEntityShadows().setValue(true);
        o.getMaxFps().setValue(260); // 260 = "Sinirsiz" (Unlimited) secenegi
        o.getGamma().setValue(1.0);
        o.getBiomeBlendRadius().setValue(7);
        o.getAo().setValue(AoMode.MAX);
        o.getBobView().setValue(true);
        o.getAttackIndicator().setValue(AttackIndicator.CROSSHAIR);

        client.options.write();
    }

    /**
     * X tusu: Istenen 5 ozel deger disindaki her sey
     * Minecraft'in varsayilan (fabrika ayari) degerlerine cekilir.
     */
    private void setCustomProfile(MinecraftClient client) {
        GameOptions o = client.options;

        // --- Istenen ozel ayarlar ---
        o.getViewDistance().setValue(32);                       // Chunk (gorus mesafesi) = 32
        o.getGamma().setValue(1.0);                              // Parlaklik = Full (%100)
        o.getCloudRenderMode().setValue(CloudRenderMode.OFF);    // Bulutlar kapali
        o.getParticles().setValue(ParticlesMode.MINIMAL);        // Parcaciklar en az
        o.getEntityShadows().setValue(false);                    // Golgeler kapali
        o.getMaxFps().setValue(260);                             // Kare hizi sinirsiz

        // --- Geri kalan her sey Minecraft'in varsayilan (fabrika) degeri ---
        o.getSimulationDistance().setValue(10);
        o.getGraphicsMode().setValue(GraphicsMode.FANCY);
        o.getBiomeBlendRadius().setValue(2);
        o.getAo().setValue(AoMode.MAX);
        o.getBobView().setValue(true);
        o.getAttackIndicator().setValue(AttackIndicator.CROSSHAIR);
        o.getFullscreen().setValue(false);
        o.getVsync().setValue(true);
        o.getGuiScale().setValue(0);

        client.options.write();
    }
}
