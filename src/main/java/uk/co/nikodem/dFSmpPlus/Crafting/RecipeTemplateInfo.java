package uk.co.nikodem.dFSmpPlus.Crafting;

import org.bukkit.NamespacedKey;

import java.util.List;

public class RecipeTemplateInfo {
    private final String idTag;
    private final List<NamespacedKey> keys;

    public RecipeTemplateInfo(String idTag, List<NamespacedKey> keys) {
        this.idTag = idTag;
        this.keys = keys;
    }

    public String getIdTag() {
        return this.idTag;
    }

    public List<NamespacedKey> getKeys() {
        return this.keys;
    }

    public void addKey(NamespacedKey key) {
        this.keys.add(key);
    }
}
