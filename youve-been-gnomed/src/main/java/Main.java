import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        try {
            Scanner scan = new Scanner(new File("./youve-been-gnomed/src/main/resources/token"));
            String token = scan.nextLine();
            System.out.println(token);
            builder.setToken(token);
            builder.addEventListeners(new Main());
            builder.build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //System.out.println("We received a message from " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
        if(event.getMessage().getContentRaw().equals("!gnomePlay")) {
            Board board = new Board(10, 10);
            board.generate(12);
            event.getChannel().sendMessage(board.toDiscordString()).queue();
        }
    }
}
