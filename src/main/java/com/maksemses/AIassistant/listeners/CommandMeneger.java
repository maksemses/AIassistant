package com.maksemses.AIassistant.listeners;

import com.maksemses.AIassistant.OpenAIResponse;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class CommandMeneger extends ListenerAdapter {
    static MessageChannelUnion chatGPTChannel = null;
    OpenAIResponse openAIResponse = new OpenAIResponse();
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("setchannelforchatgpt")){
            OptionMapping option1 = event.getOption("channel");
            chatGPTChannel = (MessageChannelUnion) option1.getAsChannel();
            event.reply("Канал успешно установлен").queue();
        }
        else if (command.equals("saytochatgpt")) {
            if (chatGPTChannel != null) {
                OptionMapping option1 = event.getOption("text");
                String question = option1.getAsString();
                String answer = openAIResponse.getAnswer(question);
                chatGPTChannel.sendMessage(answer).queue();
            }
            else{
                event.reply("Не установлен канал").queue();
            }
        }
    }


    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        OptionData option1SetChannel = new OptionData(OptionType.CHANNEL, "channel", "Choose channel for ChatGPT", true);
        commandData.add(Commands.slash("setchannelforchatgpt","Выбрать канал для ChatGPT").addOptions(option1SetChannel));

        OptionData option1TextForAI = new OptionData(OptionType.STRING, "text","say to AI", true);
        commandData.add(Commands.slash("saytochatgpt","Спросить что-то у ИИ").addOptions(option1TextForAI));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
