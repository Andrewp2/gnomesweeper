package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;

/**
 *
 * @author Andrew Peterson(andrewp2)
 */
public class Help extends Command {
    public Help(Permission... perms) {
        this.name = "gnomehelp";
        this.help = "Returns help";
        this.hidden = true;
        this.aliases = new String[]{""};
        this.guildOnly = false;
        this.botPermissions = perms;
    }

    @Override
    protected void execute(CommandEvent event) {
        String help = "Commands:\n" +
                "```\n" +
                "!gnomePlay : Starts a new game of gnomeSweeper.\n" +
                "!gnomeCount : Says number of games served since last restart.\n" +
                "Accepts all commands in any case."+
                "```";
        event.reply(help);
    }
}
