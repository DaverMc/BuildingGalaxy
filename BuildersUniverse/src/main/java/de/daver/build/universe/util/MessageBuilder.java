package de.daver.build.universe.util;

public class MessageBuilder {

    private static String prefix = null;
    private static String suffix = null;
    private final StringBuilder message;


    private MessageBuilder() {
        message = new StringBuilder();
    }

    public MessageBuilder prefix(String prefix, String borderFront, String borderBack) {
        if(prefix != null) message.append(borderFront).append(prefix).append(borderBack);
        return this;
    }

    public MessageBuilder prefix(String prefix) {
        return prefix(prefix, "[", "]");
    }

    public MessageBuilder prefix() {
        return prefix(MessageBuilder.prefix);
    }

    public MessageBuilder suffix(String suffix, String borderFront, String borderBack) {
        if(suffix != null) message.append(borderFront).append(suffix).append(borderBack);
        return this;
    }

    public MessageBuilder suffix(String prefix) {
        return suffix(prefix, "[", "]");
    }

    public MessageBuilder suffix() {
        return suffix(MessageBuilder.suffix);
    }

    public MessageBuilder message(String beginner, String message, String end) {
        this.message.append(beginner).append(message).append(end);
        return this;
    }

    public MessageBuilder message(String message) {
        return message(message, ": ", "");
    }

    public String toString() {
        return message.toString();
    }

    public static MessageBuilder create() {
        return new MessageBuilder();
    }

    public static String createMessage(String message) {
        return new MessageBuilder().prefix().message(message).suffix().toString();
    }

    public static void setDefaultPrefix(String prefix) {
        MessageBuilder.prefix = prefix;
    }

    public static void setDefaultSuffix(String suffix) {
        MessageBuilder.suffix = suffix;
    }
}
