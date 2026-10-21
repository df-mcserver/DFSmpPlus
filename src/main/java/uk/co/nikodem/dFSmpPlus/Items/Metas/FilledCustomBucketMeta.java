package uk.co.nikodem.dFSmpPlus.Items.Metas;
import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class FilledCustomBucketMeta extends CustomBucketCanFillMeta {
    private final String namedIdPrefix;

    public FilledCustomBucketMeta(String namedIdPrefix) {
        super(namedIdPrefix);
        this.namedIdPrefix = namedIdPrefix;
    }

    public void BucketEmptyEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketEmptyEvent event) {
        DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(namedIdPrefix+"bucket");
        if (newMaterial != null) {
            event.setItemStack(newMaterial.toItemStack());
        }
    }
}
