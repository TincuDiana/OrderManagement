package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public WriteFile(String str) throws IOException {
        writeFile(str);
    }

    public WriteFile() {

    }

    public void writeFile(String str) throws IOException {
        FileWriter fileWriter= new FileWriter("bill.txt");
        fileWriter.write(str);
        fileWriter.close();
    }
}

