package com.capgemini.movies.database.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StaticConfigReader {
    private static StaticConfigReader instance;

    static final String APPLICATION_PROPERTIES = "application.properties";

    public final Properties props;
    private final ClassLoader loader;

    private StaticConfigReader() throws IOException {
        props = new Properties();
        loader = Thread.currentThread().getContextClassLoader();
        read();
    }

    public static void initilize() throws IOException {
        instance = new StaticConfigReader();
    }

    public static StaticConfigReader getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getNeo4jConfPath() {
        String neo4jConfKey = getProperty("neo4j.configFile");
        return String.format("src/main/resources/%s", neo4jConfKey);
    }

    private void read() throws IOException {
        try(InputStream resourceStream = loader.getResourceAsStream(APPLICATION_PROPERTIES)) {
            props.load(resourceStream);
        }
    }

    public String getNeo4jDir() {
        return getProperty("neo4j.dbDir");
    }
}
