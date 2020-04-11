package com.SP1CYH0T.github.jojomod.object.command;

import com.SP1CYH0T.github.jojomod.object.BloodType;
import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class BloodResetCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> literalargumentbuilder = Commands.literal("bloodreset").requires((p_198485_0_) -> {
            return p_198485_0_.hasPermissionLevel(1);
        });
        for (BloodType value : BloodType.values()) {
            String valueName = value.name().toLowerCase();
            literalargumentbuilder.then(Commands.literal(valueName).executes((source) -> {
                return resetBlood(source.getSource(), value);
            }).then(Commands.argument("target", EntityArgument.players()).executes((p_198486_1_) -> {
                return resetBlood(p_198486_1_.getSource(), EntityArgument.getPlayer(p_198486_1_, "target"), value);
            })));
        }
        dispatcher.register(literalargumentbuilder);
    }

    private static int resetBlood(CommandSource source, ServerPlayerEntity playerEntity, BloodType type) {
        PlayerBlood.get(playerEntity).ifPresent(playerBlood -> {
            if(type.equals(BloodType.BLOOD)) {
                playerBlood.resetBlood();
                JojoUtility.sendMessage(playerEntity, new TranslationTextComponent("command_blood_reset", playerEntity.getName().getFormattedText()), false);
            } else if(type.equals(BloodType.MAXBLOOD)) {
                playerBlood.resetMaxBlood();
                JojoUtility.sendMessage(playerEntity, new TranslationTextComponent("command_maxBlood_reset", playerEntity.getName().getFormattedText()), false);
            }
            playerBlood.sync(playerEntity);
        });
        return 0;
    }

    private static int resetBlood(CommandSource source, BloodType type) {
        if(source.getEntity() instanceof ServerPlayerEntity) {
            resetBlood(source, (ServerPlayerEntity) source.getEntity(), type);
        }
        return 0;
    }
}
