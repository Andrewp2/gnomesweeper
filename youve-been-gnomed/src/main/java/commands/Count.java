package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import main.Main;
import net.dv8tion.jda.core.Permission;

/**
 *
 * @author Andrew Peterson(andrewp2)
 */
@CommandInfo(
        name = "gnomeCount",
        description = "Counts the number of games served."
)
@Author("Andrew Peterson (andrewp2)")
public class Count extends Command {

    public Count(Permission... perms)
    {
        this.name = "gnomeCount";
        this.help = "Counts number of games served.";
        this.aliases = new String[]{"gnoc"};
        this.guildOnly = false;
        this.botPermissions = perms;
    }


    @Override
    protected void execute(CommandEvent event) {
        event.reply("There have been " + Main.numberOfGamesServed + " games served.");
    }
}