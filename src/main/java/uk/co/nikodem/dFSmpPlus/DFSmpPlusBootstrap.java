package uk.co.nikodem.dFSmpPlus;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import uk.co.nikodem.dFSmpPlus.Enchantments.CreateEnchantments;
import uk.co.nikodem.dFSmpPlus.Player.DialogManager;

public class DFSmpPlusBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(BootstrapContext context) {
        CreateEnchantments.init(context);
        DialogManager.init(context);
    }
}
