package org.zhwj184.telnet.command;

public class CommandHandlerFactory {

	private static CommandHandlerFactory factory;

	/**
	 * Factory method.
	 * 
	 * @return
	 */
	public static CommandHandlerFactory getInstance() {
		if (factory != null) {
			return factory;
		}
		return new CommandHandlerFactory();
	}

	/**
	 * Get handler for given command.
	 * 
	 * @param command
	 * @param workingDir
	 * @return
	 */
	public CommandHandler getHandler(final String command) {
		if (command.matches("^ls.*")) {
			return new LSHandler(command);
		} else if (command.equalsIgnoreCase("exit")) {
			return new ExitHandler();
		} else if (command.startsWith("invoke")) {
			return new InvokeHandler(command);
		}else {
			return new UnknownCommandHandler(command);
		}

	}

}
