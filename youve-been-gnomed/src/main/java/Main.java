import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Main extends ListenerAdapter {
    private int numberOfGamesServed = 0;
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        InputStream source = Main.class.getClassLoader().getResourceAsStream("token");
        BufferedReader bf = new BufferedReader(new InputStreamReader(source));
        String token = null;
        try {
            token = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.setGame(Game.playing("!gnomeHelp | By @Andrew Peterson#9999 "));
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().toLowerCase();
        
        switch(message) {
            case "!gnomeplay":
            case "!gnop":
                Board board = new Board(10, 10);
                board.generate(16);
                numberOfGamesServed++;
                event.getChannel().sendMessage(board.toDiscordString()).queue();
                break;
            case "!gnomecount":
            case "!gnoc":
                event.getChannel().sendMessage("Number of gnomeSweeper games served: "+numberOfGamesServed).queue();
                break;
            case "!gnomehelp":
                String help = "Commands:\n" +
                        "```\n" +
                        "!gnomePlay : Starts a new game of gnomeSweeper.\n" +
                        "!gnomeCount : Says number of games served since last restart.\n" +
                        "Accepts all commands in any case."+
                        "```";
                event.getChannel().sendMessage(help).queue();
                break;

        }
    }

}
