package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.List;

public class IngredientMeta implements DFMaterialMeta {
    public List<TextComponent> AddAdditionalLore(DFMaterial material) {
        return List.of(
                (TextComponent) MiniMessage.miniMessage().deserialize("<!italic><blue>Ingredients</blue>")
        );
    };
}
