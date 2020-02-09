package ru.mail.ilya6697089.app.controller.listener;

import java.lang.invoke.MethodHandles;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.ilya6697089.app.service.TableService;
import ru.mail.ilya6697089.app.service.impl.TableServiceImpl;

public class StartFinishListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private TableService tableService = TableServiceImpl.getInstance();

    public StartFinishListener() {}

    public void contextInitialized(ServletContextEvent sce) {
        tableService.createAllTables();
        logger.info("Tables created and users added");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        tableService.deleteAllTables();
        logger.info("Tables deleted");
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }

}
