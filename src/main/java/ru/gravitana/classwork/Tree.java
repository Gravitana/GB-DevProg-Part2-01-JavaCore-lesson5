package ru.gravitana.classwork;

import java.io.File;

public class Tree {

    /**
     * Распечатывает директории и файлы в виде дерева
     * @param args
     */
    public static void main(String[] args) {
        print(new File("./src"), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "   ";
        }
        else {
            System.out.print("├─");
            indent += "│  ";
        }

        if (file.isDirectory()) {
            System.out.print("[");
        }
        System.out.print(file.getName());
        if (file.isDirectory()) {
            System.out.print("]");
        }
        System.out.println();

        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory() || files[i].isFile())
            {
                subDirTotal++;
            }
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory() || files[i].isFile())
            {
                print(files[i], indent, subDirTotal == ++subDirCounter);
            }
        }
    }
}
