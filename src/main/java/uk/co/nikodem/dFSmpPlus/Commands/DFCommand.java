package uk.co.nikodem.dFSmpPlus.Commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;

import java.util.Collection;

public interface DFCommand {
    LiteralArgumentBuilder<CommandSourceStack> createCommand();
    String getDescription();
    Collection<String> getAliases();
}
