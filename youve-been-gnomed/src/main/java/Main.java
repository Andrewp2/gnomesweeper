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
    public static long lastUsed = 0L;
    public static void main(String[] args) throws LoginException {
        lastUsed = System.currentTimeMillis();
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
        builder.setGame(Game.playing("!gnomePlay | By @Andrew Peterson#9999 "));
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equals("!gnomePlay") && lastUsed + 3000 < System.currentTimeMillis()) {
            lastUsed = System.currentTimeMillis();
            Board board = new Board(10, 10);
            board.generate(14);
            event.getChannel().sendMessage(board.toDiscordString()).queue();
        }
    }

}
