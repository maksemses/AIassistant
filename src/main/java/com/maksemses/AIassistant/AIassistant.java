package com.maksemses.AIassistant;

import com.maksemses.AIassistant.listeners.CommandMeneger;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class AIassistant {
    private static final String TOKEN = "MTEwNTgzMTAxODQ1NzQ3MzE2NA.Gg_o65.87nnJNGeKbaUfUsfGtdr9P4pqXo7NvacXxod40";
    private final ShardManager shardManager;

    public AIassistant() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(TOKEN);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.watching("gay porno"));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
        shardManager = builder.build();

        shardManager.addEventListener(new CommandMeneger());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args){
        try {
            AIassistant bot = new AIassistant();
        }catch (LoginException e){
            System.out.println("ERROR: bad TOKEN");
        }
    }

}