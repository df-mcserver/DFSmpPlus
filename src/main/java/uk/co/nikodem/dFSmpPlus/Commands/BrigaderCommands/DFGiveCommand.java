package uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Commands.DFCommand;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.*;

public class DFGiveCommand implements DFCommand {
    private List<String> validOptions = new ArrayList<>();

    public DFGiveCommand() {
        for (Map.Entry<String, DFMaterial> entry : DFMaterial.DFMaterialIndex.entrySet()) {
            DFMaterial material = entry.getValue();
            validOptions.add(material.getNamedId());
        }
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> createCommand() {
        return Commands.literal("dfgive")
                .then(
                        Commands.argument("targets", ArgumentTypes.players())
                                .then(Commands.argument("dfmaterial", StringArgumentType.string())
                                        .suggests((ctx, builder) -> {
                                            for (String option : validOptions) {
                                                builder.suggest(option);
                                            }
                                            return builder.buildFuture();
                                        })
                                        .executes(ctx -> doGiveCommand(ctx, 1))
                                        .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                                                .executes(ctx -> {
                                                    final int amnt = IntegerArgumentType.getInteger(ctx, "amount");
                                                    return doGiveCommand(ctx, amnt);
                                                }))
                                )
                );
    }

    public int doGiveCommand(CommandContext<CommandSourceStack> ctx, int amnt) throws CommandSyntaxException {
        final List<Player> plrs = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class).resolve(ctx.getSource());
        final String dfmaterialName = StringArgumentType.getString(ctx, "dfmaterial");

        CommandSender sender = ctx.getSource().getSender();

        if (plrs.isEmpty()) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>No player was found"));
            return Command.SINGLE_SUCCESS;
        }

        DFMaterial mat = DFMaterial.DFMaterialIndex.get(dfmaterialName);

        if (mat == null) {
            sender.sendMessage("Invalid DFMaterial!");
            return Command.SINGLE_SUCCESS;
        }

        ItemStack item = mat.toItemStack(amnt);

        for (Player plr : plrs) {
            DFItemUtils.addUUIDIfMarked(item);
            plr.getInventory().addItem(item);
        }

        String names = plrs.size() == 1 ? plrs.getFirst().getName() : amnt+" targets";

        sender.sendMessage(Component.text("Gave "+amnt+" ").append(item.displayName()).append(Component.text(" to "+names)));

        return Command.SINGLE_SUCCESS;
    }

    @Override
    public String getDescription() {
        return "give command but for DFMaterials";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of("givedf");
    }
}
