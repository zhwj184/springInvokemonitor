package org.zhwj184.telnet.command;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.beans.factory.BeanFactory;
import org.zhwj184.ServiceContext;

public class LSHandler implements CommandHandler {

    private final String command;
    private final Logger logger = Logger.getLogger(LSHandler.class.getName());

    public LSHandler(final String command) {
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
        logger.info("running the follwoing commnad:" + command);
        BeanFactory beanFactory = ServiceContext.getBeanFactory();
        String beanNameOrType = command.substring(command.indexOf(" ") + 1);
        Object obj = beanFactory.getBean(beanNameOrType);
        StringBuilder builder = new StringBuilder();
        Method[] methods = obj.getClass().getMethods();
        for(Method method: methods){
        	builder.append(method.getReturnType().getName() + " " + obj.getClass().getName() + "." + method.getName() + "(");
        	Class<?>[] cls = method.getParameterTypes();
        	for(int i = 0; i < cls.length; i++){
        		if(i != cls.length - 1){
        			builder.append(cls[i].getName() + ",");
        		}else{
        			builder.append(cls[i].getName());
        		}
        	}
        	builder.append(")" + cr);
        }
        return builder.toString();

    }
}