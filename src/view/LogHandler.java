/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Handles logging of errors and operation results.
 * @author ubro3
 */
public class LogHandler {
    private final String logPath;
    public LogHandler(String logPath) {
        this.logPath = logPath;
    }
    /**
     * Prints errors on the screen and writes them to the log file.
     *
     * @param content The error content to be logged.
     */
    public void printErr(String content) {
        try {
            content = "[ERROR]: " + content;
            System.out.println(content);
            writeLog(content);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    /**
     * Prints operation results on the screen and writes them to the log file.
     *
     * @param content The operation result content to be logged.
     */
    public void printInfo(String content) {
        try {
            content = "[INFO]: " + content;
            System.out.println(content);
            writeLog(content);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
        /**
     * Writes log content to a file.
     *
     * @param content  The content to write to the log file.
     * @throws IOException if there is an error writing the file.
     */
    public void writeLog(String content) throws IOException {
        try (FileWriter writer = new FileWriter(logPath, true)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date currentDate = new Date();
            String strDate = dateFormat.format(currentDate);
            String logContent = "[" + strDate + "] " + content + "\n";
            writer.write(logContent);
        }
    }
}
