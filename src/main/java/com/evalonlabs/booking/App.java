package com.evalonlabs.booking;

import com.evalonlabs.booking.engine.BookSystem;
import com.evalonlabs.booking.engine.protocol.http.HttpInitializer;
import com.evalonlabs.booking.http.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Evangelos
 */
public class App {
    private final static Logger log = LoggerFactory.getLogger(App.class);
    public final static Properties properties = new Properties();
    public final static ClassLoader loader = Thread.currentThread().getContextClassLoader();
    public static boolean isDev = false;

    static {
        try {
            properties.load(loader.getResourceAsStream("app.properties"));
            isDev = properties.getProperty("app.env", "enterprise").equals("dev");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        BookSystem bookSystem = new BookSystem();

        Thread serverThread = new Thread(
                new Server(Integer.valueOf(properties.getProperty("server.port", "2048")),
                        new HttpInitializer(bookSystem)
                ),
                "server"
        );
        serverThread.start();
        log.info("Server is running at " + properties.getProperty("server.port", "2048"));
    }
}
