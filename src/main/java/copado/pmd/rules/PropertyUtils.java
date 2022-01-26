package com.accenture.pmd.lang.apex.rule.description;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import java.util.Objects;
import java.util.Properties;
import java.util.Set;

public class PropertyUtils {
    
    private PropertyUtils(){};

    private static final Set<String> PREFIXES;
    private static final String CLASSES_PATH_ENV_VAR_NAME = "APEX_CLASSES";
    private static Set<String> APEX_FILENAMES_CLS = null;

    static {
        PREFIXES =  PropertyUtils.createSetOfPrefixes();
        try {
            APEX_FILENAMES_CLS = PropertyUtils.listFilesUsingDirectoryStream(PropertyUtils.getEnvVar(CLASSES_PATH_ENV_VAR_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getPrefixes() {
        return PREFIXES;
    }
    public static Set<String> getClassFilenames() {
        return APEX_FILENAMES_CLS;
    }

    private static Set<String> createSetOfPrefixes() {
        Properties prop = new Properties();
        try(InputStream fio = Thread.currentThread().getContextClassLoader().getResourceAsStream("nameConventionPrefixes.properties")) {
            prop.load(fio);
            return prop.stringPropertyNames();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private static Set<String> listFilesUsingDirectoryStream(final String dir) throws IOException {
        Set<String> fileList = new HashSet<>();
        String filename;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    filename = path.getFileName().toString();
                    if ((filename.split("[.]").length == 2) && (Objects.equals(filename.split("[.]")[1], "cls"))) {
                        fileList.add(filename);
                    }
                }
            }
        }
        return fileList;
    }

    private static String getEnvVar(final String varName) {
        return System.getenv().get(varName);
    }

}
