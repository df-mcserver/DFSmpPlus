package uk.co.nikodem.dFSmpPlus.Items.Metas.Bucket;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

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
