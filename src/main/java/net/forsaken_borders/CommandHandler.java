package net.forsaken_borders;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;

import net.forsaken_borders.commands.HomeCommand;
import net.forsaken_borders.commands.SetHomeCommand;
import net.forsaken_borders.commands.UnbanCommand;
import net.forsaken_borders.commands.UnbanIpCommand;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class CommandHandler {
	public static void registerCommands(CommandDispatcher<ServerCommandSource> commandDispatcher) {
		RootCommandNode<ServerCommandSource> rootNode = commandDispatcher.getRoot();

		// Unban Command - Alies for /pardon
		{
			// TODO: Add Suggestions for banned players
			LiteralCommandNode<ServerCommandSource> unbancommandNode = CommandManager.literal("unban")
				.then(RequiredArgumentBuilder.argument("player", StringArgumentType.greedyString()))
				.executes(new UnbanCommand()::run).build();

			rootNode.addChild(unbancommandNode);
		}

		// Unban IP Command - Alias for /pardon-ip
		{
			// TODO: Add Suggestions for banned IPs
			LiteralCommandNode<ServerCommandSource> unbanIpCommandNode = CommandManager.literal("unban-ip")
				.then(RequiredArgumentBuilder.argument("ip", StringArgumentType.greedyString()))
				.executes(new UnbanIpCommand()::run).build();

			rootNode.addChild(unbanIpCommandNode);
		}

		// Sethome Command - Set a Home Point
		{
			LiteralCommandNode<ServerCommandSource> sethomeCommandNode = CommandManager.literal("sethome")
				.then(RequiredArgumentBuilder.argument("name", StringArgumentType.greedyString()))
				.executes(new SetHomeCommand()::run).build();

			rootNode.addChild(sethomeCommandNode);
		}

		// Home Command - Teleport to a Home Point
		{
			LiteralCommandNode<ServerCommandSource> homeCommandNode = CommandManager.literal("home")
				.then(RequiredArgumentBuilder.argument("name", StringArgumentType.greedyString()))
				.executes(new HomeCommand()::run).build();

			rootNode.addChild(homeCommandNode);
		}
	}
}
