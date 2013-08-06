package org.zhwj184.telnet.command;

public class UnknownCommandHandler implements CommandHandler {

    private final String command;

    /**
     * @param command
     */
    public UnknownCommandHandler(final String command) {

        this.command = command;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.akhettar.telnet.command.CommandHandler#handle()
     */
    @Override
    public String handle() {

        String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r\n" : "\n";
        StringBuilder builder = new StringBuilder();
        builder.append("Unknown command [" + command + "]");
        builder.append(cr);
        builder.append("Here are the list of commands you could run:");
        builder.append(cr);
        builder.append("cd [directory name]");
        builder.append(cr);
        builder.append("ls");
        builder.append(cr);
        builder.append("mkdir");
        builder.append(cr);
        builder.append("status");
        builder.append(cr);
        builder.append("pwd");
        return builder.toString();
    }

}