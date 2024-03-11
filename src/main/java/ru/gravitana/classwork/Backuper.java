package ru.gravitana.classwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Backuper {
    public static void main(String[] args) throws IOException {
        String targetDirName = "./src/main/java";
        String backupDirName = "./backup";

        backup(new File(targetDirName), new File(backupDirName));
    }

    static void backup(File targetDir, File backupDir) throws IOException {

        backupDir = new File(backupDir.getPath() + "/" + targetDir.getName());

        backupFile(targetDir, backupDir);


        File[] files = targetDir.listFiles();
        if (files == null) {
            return;
        }

        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory() || files[i].isFile())
            {
                backup(files[i], backupDir);
            }
        }
    }

    static void backupFile(File target, File backup) throws IOException {

        if (target.isDirectory() && backup.mkdirs()) {
            return;
        }

        if (target.isFile()) {
            try(FileOutputStream fileOutputStream = new FileOutputStream(backup)){
                int c;
                try (FileInputStream fileInputStream = new FileInputStream(target)){
                    while ((c = fileInputStream.read()) != -1){
                        fileOutputStream.write(c);
                    }
                }
            }
        }
//        System.out.println(target.getPath() + " => " + backup.getPath());
    }

}
