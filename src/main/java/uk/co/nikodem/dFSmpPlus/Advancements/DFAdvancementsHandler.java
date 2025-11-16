package uk.co.nikodem.dFSmpPlus.Advancements;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Accessory.EquipAccessory;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Accessory.EquipAllAccessories;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Accessory.ITinkeredItOut;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Armour.IndecisiveWardrobe;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Armour.MatchingAttire;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.Bluebellsar;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.BluebellsarRun;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.BulliedByBluebellsar;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.BullyBluebellsar;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Combat.NotEvenCloseBaby;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Combat.WhatYouEgg;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Combat.WomboCombo;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.*;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc.*;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Vamp.*;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.HashSet;

public class DFAdvancementsHandler {
    public final UltimateAdvancementAPI api;
    public static final String ADVANCEMENT_NAMESPACE = "dfsmpplus";

    public static final HashSet<BaseAdvancement> advancements = new HashSet<>();

    public DFAdvancementsHandler(DFSmpPlus plugin) {
        api = UltimateAdvancementAPI.getInstance(plugin);

        createTab();
    }

    public void createTab() {
        AdvancementTab tab = api.createAdvancementTab(ADVANCEMENT_NAMESPACE);

        DFRootAdvancement main = new DFRootAdvancement(tab);

        NewWorld newWorld = new NewWorld(main);
        new UsedSpear(newWorld);
        new UselessFlex(newWorld);
        new IITNIG(newWorld);
        new NetheriteTech(newWorld); // TODO

        SoItBegins soItBegins = new SoItBegins(main);
        new OSFTSB(soItBegins); // TODO
        new Stage10(new Stage9(new Stage6(new Stage2(new Stage1(soItBegins)))));

        Bluebellsar bluebellsar = new Bluebellsar(main);
        BulliedByBluebellsar bulliedByBluebellsar = new BulliedByBluebellsar(bluebellsar);
        BullyBluebellsar bullyBluebellsar = new BullyBluebellsar(bulliedByBluebellsar);
        new BluebellsarRun(bullyBluebellsar);

        new WomboCombo(new NotEvenCloseBaby(main));

        new MagicMirror(main);
        new DoublingDown(main);
        new ITTDDTA(main); // TODO
        new WorldRecord(main); // TODO
        new WhatYouEgg(main);
        new I3Rocks(main); // TODO
        new BreakTargetDummy(main);
        new ConsciousWarden(main); // TODO
        new IYCBTJT(main);
        new VeinTool(main);
        new FiridiumTool(main);

        ObsidianItem obsidianItem = new ObsidianItem(main);
        new ObsidianArmour(obsidianItem); // TODO

        CompassCraft compassCraft = new CompassCraft(main);
        new CompassUse(compassCraft); // TODO

        EquipAccessory equipAccessory = new EquipAccessory(main); // TODO
        EquipAllAccessories equipAllAccessories = new EquipAllAccessories(equipAccessory); // TODO
        new ITinkeredItOut(equipAllAccessories); // TODO

        MatchingAttire matchingAttire = new MatchingAttire(main); // TODO
        new IndecisiveWardrobe(matchingAttire); // TODO

        tab.registerAdvancements(main, advancements);
        tab.automaticallyGrantRootAdvancement();
        tab.automaticallyShowToPlayers();
    }

    public static void registerAdvancement(BaseAdvancement advancement) {
        advancements.add(advancement);
    }

    @Nullable
    public static Advancement getAdvancement(AdvancementKey key) {
        return DFSmpPlus.advancements.api.getAdvancement(key);
    }

    @Nullable
    public static Advancement getAdvancement(String name) {
        return DFSmpPlus.advancements.api.getAdvancement(ADVANCEMENT_NAMESPACE, name);
    }

    @Nullable
    public static Advancement getAdvancement(Class<? extends BaseAdvancement> clazz) {
        try {
            Field keyField = clazz.getDeclaredField("KEY");
            AdvancementKey key = (AdvancementKey) keyField.get(null);
            return getAdvancement(key);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    @Nullable
    public static Boolean hasAdvancement(Player plr, Advancement advancement) {
        if (advancement == null) return null;
        return advancement.isGranted(plr);
    }

    @Nullable
    public static Boolean hasAdvancement(Player plr, String name) {
        return hasAdvancement(plr, getAdvancement(name));
    }

    @Nullable
    public static Boolean hasAdvancement(Player plr, Class<? extends BaseAdvancement> clazz) {
        return hasAdvancement(plr, getAdvancement(clazz));
    }

    public static void grantAdvancement(Player plr, Advancement advancement) {
        if (advancement == null) return;
        advancement.grant(plr);
    }

    public static void grantAdvancement(Player plr, String name) {
        grantAdvancement(plr, getAdvancement(name));
    }

    public static void grantAdvancement(Player plr, Class<? extends BaseAdvancement> clazz) {
        grantAdvancement(plr, getAdvancement(clazz));
    }
}
