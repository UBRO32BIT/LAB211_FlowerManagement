/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;
import business.FileHandler;
/**
 * Handles logging of errors and operation results.
 * @author ubro3
 */
public class LogHandler {
    private String fileName;
    public LogHandler(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Prints errors on the screen and writes them to the log file.
     *
     * @param content The error content to be logged.
     * @throws Exception If an error occurs while writing to the log file.
     */
    public void printErr(String content) throws Exception {
        content = "[ERROR]: " + content;
        System.out.println(content);
        FileHandler.writeLog(fileName, content);
    }
    /**
     * Prints operation results on the screen and writes them to the log file.
     *
     * @param content The operation result content to be logged.
     * @throws Exception If an error occurs while writing to the log file.
     */
    public void printInfo(String content) throws Exception {
        content = "[INFO]: " + content;
        System.out.println(content);
        FileHandler.writeLog(fileName, content);
    }
}
