package main;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import commands.Count;
import commands.Help;
import commands.Play;
import game.Board;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Main {
    public static int numberOfGamesServed = 0;
    public static void main(String[] args) throws LoginException {
        InputStream source = Main.class.getClassLoader().getResourceAsStream("token");
        BufferedReader bf = new BufferedReader(new InputStreamReader(source));
        String token = null;
        String ownerId = null;
        try {
            token = bf.readLine();
            ownerId = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();
        client.setGame(Game.playing("!gnomeHelp | By @Andrew Peterson#9999 "));
        client.setOwnerId(ownerId);
        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");
        client.setPrefix("!");

        client.setHelpWord("gnomeHelp");
        client.addCommands(new Play(Permission.MESSAGE_WRITE), new Count(Permission.MESSAGE_WRITE));
        java.util.function.Consumer<CommandEvent> helpConsumer = (event) -> {
            try {
                Method method = Help.class.getDeclaredMethod("execute", CommandEvent.class);
                method.setAccessible(true);
                method.invoke(new Help(Permission.MESSAGE_WRITE), event);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        };

        client.setHelpConsumer(helpConsumer);

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(token);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setGame(Game.playing("loading..."));

        builder.addEventListener(waiter).addEventListener(client.build());
        builder.build();
    }

}
