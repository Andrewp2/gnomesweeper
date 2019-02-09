package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.JDAUtilitiesInfo;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import game.Board;
import main.Main;
import net.dv8tion.jda.bot.entities.ApplicationInfo;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDAInfo;
import net.dv8tion.jda.core.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 *
 * @author Andrew Peterson(andrewp2)
 */
@CommandInfo(
        name = "gnomePlay",
        description = "Lets you play a game of gnomeSweeper."
)
@Author("Andrew Peterson (andrewp2)")
public class Play extends Command {

    public Play(Permission... perms)
    {
        this.name = "gnomePlay";
        this.help = "lets you play a game of gnomesweeper";
        this.aliases = new String[]{"gnop"};
        this.guildOnly = false;
        this.botPermissions = perms;
    }


    @Override
    protected void execute(CommandEvent event) {
        Board board = new Board(10,10);
        board.generate(16);
        Main.numberOfGamesServed++;
        event.reply(board.toDiscordString());
    }
}
