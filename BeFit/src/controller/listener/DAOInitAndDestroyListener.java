package controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import command.impl.ApplicationSourceDestroy;
import command.impl.ApplicationSourceInit;

public class DAOInitAndDestroyListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ApplicationSourceDestroy destroy = new ApplicationSourceDestroy();
		destroy.execute(null, null);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationSourceInit init = new ApplicationSourceInit();
		init.execute(null, null);
	}

}
