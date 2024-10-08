package de.daver.build.hub.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileUtils {

    @SuppressWarnings({"invert", "BooleanMethodIsAlwaysInverted"})
    public static boolean deleteFile(File file) {
        if(file.isFile()) return file.delete();
        File[] children = file.listFiles();
        if(children == null) return file.delete();
        for(File child : children) if(!deleteFile(child)) return false;
        return true;
    }

    public static boolean copyFile(File srcFile, File destFile) {
        try {
            OutputStream outputStream = new FileOutputStream(destFile);
            return Files.copy(srcFile.toPath(), outputStream) > 0;
        } catch (IOException exception) {
            throw new RuntimeException(exception); //TODO Better Errorhandling
        }
    }

    public static File createFile(File file) {
        if(file.isDirectory()) {
            if(!file.mkdirs()) return null;
            return file;
        }
        try {
            File parent = file.getParentFile();
            if(!parent.exists() && !parent.mkdirs()) return null;
            file.createNewFile(); //Ignored Return result because the file can be existing
            return file;
        } catch (IOException exception) {
            return null;
        }
    }

}
