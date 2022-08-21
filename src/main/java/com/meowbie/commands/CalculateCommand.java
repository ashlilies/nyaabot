package com.meowbie.commands;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CalculateCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        String[] messageContent = message.split(" ");
        MessageChannelUnion channel = e.getChannel();

        if (!message.startsWith("!calculate")) {
            return;
        }

        if (messageContent.length != 4) {
            String reply = "This is the calculate command!\n"
                    + "Usage: "
                    + "``!calculate [add/sub] <first_num> <second_num>``";
            channel.sendMessage(reply).queue();
            return;
        }

        try {
            String operator = messageContent[1];
            int n1 = Integer.parseInt(messageContent[2]);
            int n2 = Integer.parseInt(messageContent[3]);
            double result;

            switch (operator) {
            case "add":
                result = n1 + n2;
                break;
            case "sub":
                result = n1 - n2;
                break;
            default:
                channel.sendMessage("Invalid operator '" + operator + "'.")
                        .queue();
                return;
            }

            channel.sendMessage("The result is " + result).queue();
        } catch (Exception ex) {
            channel.sendMessage("Invalid calculation! Try again?").queue();
        }
    }
}
