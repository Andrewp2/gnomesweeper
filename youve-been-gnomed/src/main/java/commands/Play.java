package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import game.Board;
import main.Main;
import net.dv8tion.jda.core.Permission;

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
        this.cooldownScope = CooldownScope.CHANNEL;
        this.cooldown = 30;
    }


    @Override
    protected void execute(CommandEvent event) {
        if (event.getGuild().getName().equals("destiny.gg") && !event.getChannel().getName().equals("botposting")) {
            event.reply("Can't use that command in this channel in d.gg <:gnome:542176480315179048>, try #botposting instead.");
        } else {
            Board board = new Board(10, 10);
            board.generate(16);
            Main.numberOfGamesServed++;
            event.reply(board.toDiscordString());
        }
    }

    @Override
    public String getCooldownError(CommandEvent event, int remaining) {

        return "Hold on there <:gnome:542176480315179048>, you have to wait " + remaining + " more seconds to use that command in this channel.";
    }

}
