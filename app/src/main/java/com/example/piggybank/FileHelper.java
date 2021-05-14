package com.example.piggybank;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHelper {
    // fileHelper.copyRawFile(raw, CategoryManagement.this);

    private String fileDirPath;
   private String filename;
    private File directory;
    private File file;
    private Context context;
    public FileHelper() {
    }

    public FileHelper(String fileDirPath, String filename, Context context) {
        this.fileDirPath = fileDirPath;
        this.filename = filename;
        this.context = context;
        directory = new File(fileDirPath);
        file = new File(fileDirPath+"/"+filename);    }

    public void createFolder(){
directory.mkdir();
    }

    public void createFile(){
        try {
            file.createNewFile();

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public void fileAddLineToTheEnd(String newLine){
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(filename, Context.MODE_APPEND);
            fos.write(newLine.getBytes());
            fos.write("\r\n".getBytes());//newline
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void copyRawFile(int raw){
        try {
            createFile();
            InputStream input =  context.getResources().openRawResource(raw);
            FileOutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = input.read(buffer)) > 0) {
                output.write(buffer, 0, count);
            }
            output.write("\r\n".getBytes());//newline

            output.close();
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public boolean checkFileExists(){
        return file.exists();
    }
    public boolean checkFolderExists(){
        return directory.exists();
    }
}
