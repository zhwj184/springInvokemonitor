package org.zhwj184.telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.zhwj184.telnet.command.CommandHandler;
import org.zhwj184.telnet.command.CommandHandlerFactory;
import org.zhwj184.telnet.command.ExitHandler;

public class ClientWorker implements Runnable {

    private final Socket socket;
    private final Logger logger = Logger.getLogger(ClientWorker.class.getName());

    /**
     * @param socket
     */
    public ClientWorker(final Socket socket) {
        this.socket = socket;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // display welcome screen
            out.println(Util.buildWelcomeScreen());

            boolean cancel = false;
            CommandHandlerFactory fac = CommandHandlerFactory.getInstance();
            while (!cancel) {

                final String command = reader.readLine();
                if (command == null) {
                    continue;
                }

                //handle the command
                final CommandHandler handler = fac.getHandler(command);
                String response = handler.handle();

                out.println(response);

                // command issuing an exit.
                if (handler instanceof ExitHandler) {
                    cancel = true;
                }

            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to close the socket", e);

            }
        }
    }

}