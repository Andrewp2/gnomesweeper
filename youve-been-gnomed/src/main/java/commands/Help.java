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
        this.name = "gnomeHelp";
        this.help = "Returns help";
        this.hidden = true;
        this.aliases = new String[]{""};
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
            String help = "Commands:\n" +
                    "```\n" +
                    "!gnomePlay | !gnoC : Starts a new game of gnomeSweeper.\n" +
                    "!gnomeCount | !gnoC : Says number of games served since last restart.\n" +
                    "Accepts all commands in any case.\n" +
                    "Does not work in any chat channel other than #botposting in d.gg." +
                    "```";
            event.reply(help);
        }
    }

    @Override
    public String getCooldownError(CommandEvent event, int remaining) {
        return "Hold on there <:gnome:542176480315179048>, you have to wait " + remaining + " more seconds to use that command in this channel.";
    }
}
