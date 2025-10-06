package uk.co.nikodem.dFSmpPlus.Items;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Constants.IDGuide;

import java.util.*;

import static uk.co.nikodem.dFSmpPlus.Constants.Keys.createModelKey;

public class DFMaterialBuilder {

    private final Material base;
    private final String namedId;
    private final int version;

    private TextComponent displayName;
    private boolean markedForUuid;

    private NamespacedKey equipModel;
    private Sound equipSound;
    private EquipmentSlot equipSlot;

    private final List<DFMaterialMeta> metas = new ArrayList<>();
    private final List<TextComponent> lores = new ArrayList<>();
    private final List<ItemFlag> flags = new ArrayList<>();

    private final HashMap<Enchantment, Integer> enchants = new HashMap<>();
    private final HashMap<Attribute, AttributeModifier> attributes = new HashMap<>();
    private final HashMap<String, Map.Entry<PersistentDataType, Object>> persistentData = new HashMap<>();

    private boolean hasCustomModel = true;

    public DFMaterialBuilder(Material base, String namedId, int version) {
        this.base = base;
        this.namedId = namedId;
        this.version = version;
    }

    public DFMaterialBuilder setDisplayName(TextComponent name) {
        this.displayName = name;
        return this;
    }

    public DFMaterialBuilder setDisplayName(String name) {
        MiniMessage mm = MiniMessage.miniMessage();
        this.displayName = (TextComponent) mm.deserialize("<reset><!em>"+name);
        return this;
    }

    public DFMaterialBuilder markForUUID() {
        this.markedForUuid = true;
        return this;
    }

    public DFMaterialBuilder addPersistentData(String key, PersistentDataType type, Object val) {
        persistentData.put(key, new AbstractMap.SimpleEntry<>(
                type,
                val
        ));
        return this;
    }

//    public DFMaterialBuilder setID(int id) {
//        this.Id = id;
//        return this;
//    }
//
//    public DFMaterialBuilder setID(Enums.IdItemType type, Enums.IdItemClass clss) {
//        this.Id = IDGuide.toID(type, clss);
//        return this;
//    }

    public DFMaterialBuilder addMeta(DFMaterialMeta... metas) {
        this.metas.addAll(Arrays.asList(metas));
        return this;
    }

    public DFMaterialBuilder addLore(String lore) {
        MiniMessage mm = MiniMessage.miniMessage();
        lores.add((TextComponent) mm.deserialize("<reset><!em>"+lore));
        return this;
    }

    public DFMaterialBuilder addLore(TextComponent lore) {
        this.lores.add(lore);
        return this;
    }

    public DFMaterialBuilder addEnchantment(Enchantment ench) {
        this.enchants.put(ench, 1);
        return this;
    }

    public DFMaterialBuilder addEnchantment(Enchantment ench, int level) {
        this.enchants.put(ench, level);
        return this;
    }

    public DFMaterialBuilder addAttribute(Attribute attribute, AttributeModifier modifier) {
        this.attributes.put(attribute, modifier);
        return this;
    }

    public DFMaterialBuilder addItemFlag(ItemFlag flag) {
        this.flags.add(flag);
        return this;
    }

    public DFMaterialBuilder setEquippable(String equipModel, Sound equipSound, EquipmentSlot equipSlot) {
        this.equipModel = createModelKey(equipModel);
        this.equipSound = equipSound;
        this.equipSlot = equipSlot;
        return this;
    }

    public DFMaterialBuilder setEquippable(NamespacedKey equipModel, Sound equipSound, EquipmentSlot equipSlot) {
        this.equipModel = equipModel;
        this.equipSound = equipSound;
        this.equipSlot = equipSlot;
        return this;
    }

    public DFMaterialBuilder setEquippable(NamespacedKey equipModel, EquipmentSlot equipSlot) {
        this.equipModel = equipModel;
        this.equipSound = Sound.ITEM_ARMOR_EQUIP_IRON;
        this.equipSlot = equipSlot;
        return this;
    }

    public DFMaterialBuilder removeCustomModel() {
        this.hasCustomModel = false;
        return this;
    }

    public DFMaterial create() {
        DFMaterial newMaterial = new DFMaterial(
                base,
                namedId,
                displayName,
                lores,
                enchants,
                attributes,
                persistentData,
                markedForUuid,
                equipModel,
                equipSound,
                equipSlot,
                version,
                flags,
                metas,
                hasCustomModel
        );
        DFMaterial.DFMaterialIndex.add(newMaterial);
        return newMaterial;
    }
}
