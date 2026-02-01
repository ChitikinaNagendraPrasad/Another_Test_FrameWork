package com.optim.Utilities;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.stream.Stream;

import com.optim.Listeners.ExtentTestManager;

public class Utils
{
    public static String generateEmail()
    {
        String returnValue = null;
        try
        {
            long randomNumber = random10_DigitNumber();
            returnValue = "Nagendra-" + randomNumber + "@nag.com";
            printMessageWithValue("Generated Email", returnValue);
            printMessageWithValue("Password", Long.toString(randomNumber));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnValue;
    }

    public static long random10_DigitNumber()
    {
        long returnValue = 0;
        try
        {
            returnValue = 1000000000L + (long) (Math.random() * 9000000000L);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnValue;
    }

    public static void printMessageWithValue(String heading, String message)
    {
        try
        {
            System.out.printf("%-30s:	%s%n", heading, message);
        } catch (Exception e)
        {

        }
    }
    
    public static void displayInfoInExtentReport(String message)
    {
        ExtentTestManager.getTest().info(message);
    }
    

    public static void copyPngFromReportsToRootScreenshots() 
    {
        Path sourceDir = Paths.get(System.getProperty("user.dir"), "Reports", "Screenshots");
        Path destDir   = Paths.get(System.getProperty("user.dir"), "Screenshots");

        //copyFilesByGlob(sourceDir, destDir, "*.png", true);
        
        String globPattern="*.png";
        Boolean overwrite=true;
        
        try {
            if (!Files.exists(sourceDir)) {
                System.out.println("Source folder not found: " + sourceDir.toAbsolutePath());
                return;
            }

            Files.createDirectories(destDir);

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir, globPattern)) {
                int count = 0;

                for (Path file : stream) {
                    if (!Files.isRegularFile(file)) continue;

                    Path target = destDir.resolve(file.getFileName());
                    if (overwrite) {
                        Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        if (!Files.exists(target)) {
                            Files.copy(file, target);
                        }
                    }
                    count++;
                }

                System.out.println("Copied " + count + " file(s) from " + sourceDir + " to " + destDir);

                //deleteDirectoryRecursively(sourceDir);
                //System.out.println("Deleted source folder: " + sourceDir.toAbsolutePath());
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to copy files from " + sourceDir + " to " + destDir, e);
        }
    
    }
    
    @SuppressWarnings("unused") private static void deleteDirectoryRecursively(Path dir) throws IOException {
        if (!Files.exists(dir)) return;

        try (Stream<Path> walk = Files.walk(dir)) {
            walk.sorted(Comparator.reverseOrder())   // delete children first, then parent
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to delete: " + path, e);
                    }
                });
        }
    }
    
    
    

    /**
     * Generic helper method (reused internally).
     */
    public static void copyFilesByGlob(Path sourceDir,Path destDir,String globPattern,boolean overwrite) 
    {
        try {
            if (!Files.exists(sourceDir)) {
                System.out.println("Source folder not found: " + sourceDir.toAbsolutePath());
                return;
            }

            Files.createDirectories(destDir);

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir, globPattern)) {
                int count = 0;

                for (Path file : stream) {
                    if (!Files.isRegularFile(file)) continue;

                    Path target = destDir.resolve(file.getFileName());
                    if (overwrite) {
                        Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        if (!Files.exists(target)) {
                            Files.copy(file, target);
                        }
                    }
                    count++;
                }

                System.out.println("Copied " + count + " file(s) from " + sourceDir + " to " + destDir);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to copy files from " + sourceDir + " to " + destDir, e);
        }
    }





}
