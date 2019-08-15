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
        this.aliases = new String[]{"gnoc", "gnomec", "gnoC", "gnomeC"};
        this.guildOnly = false;
        this.botPermissions = perms;
        this.cooldownScope = CooldownScope.CHANNEL;
        this.cooldown = 30;
    }


    @Override
    protected void execute(CommandEvent event) {
        if (event.getGuild().getName().equals("destiny.gg") && !event.getChannel().getName().equals("botposting")) {
            event.reply("Can't use that command in this channel in d.gg <:gnome:542176480315179048>, try <#385085233717837837> instead.");
        } else {
            event.reply("There have been " + Main.numberOfGamesServed + " games served since last restart.");
        }
    }


    @Override
    public String getCooldownError(CommandEvent event, int remaining) {
        return "Hold on there <:gnome:542176480315179048>, you have to wait " + remaining + " more seconds to use that command in this channel.";
    }

}