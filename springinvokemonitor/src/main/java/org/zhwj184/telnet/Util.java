package org.zhwj184.telnet;

public final class Util {

    /**
     * Builds welcome screen.
     * 
     * @return
     */
    public static String buildWelcomeScreen() {
        String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r\n" : "\n";
        StringBuilder builder = new StringBuilder();
        builder.append(cr);
        builder.append("======================================================");
        builder.append(cr);
        builder.append(cr);
        builder.append("   Welcome to Telnet Server: Version 1.0   ");
        builder.append(cr);
        builder.append(cr);
        builder.append("======================================================");
        builder.append(cr);
        builder.append(cr);
        builder.append("List of possible commands:");
        builder.append(cr);
        builder.append(cr);
        builder.append("Status: displays the status of the server");
        builder.append(cr);
        builder.append("cd : [ cd /usr/local]");
        builder.append(cr);
        builder.append("pwd: displays the working directory");
        builder.append(cr);
        builder.append("ls: displays list of files in the working directory");
        builder.append(cr);
        builder.append("mkdir : [ mkdir /usr/local/tmp]");
        builder.append(cr);
        builder.append("exit : quit this programme");
        builder.append(cr);
        return builder.toString();
    }

}