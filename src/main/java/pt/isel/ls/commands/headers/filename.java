package pt.isel.ls.commands.headers;

import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.annotations.HeaderInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@HeaderInfo(header = "file-name")
public class filename extends AbstractHeader {
    private static final String DEFAULT_FILENAME = "NO_FILE_SPECIFIED";
    private File file;

    @Override
    protected void headerExecute(String value, Command c) {
        if (value.equals(DEFAULT_FILENAME)) {
            System.out.println(c.parsedIterableResult);
            return;
        }

        file = new File(value);

        try(FileOutputStream fout = new FileOutputStream(file);){
            if (!file.exists()) file.createNewFile();

            byte[] cInBytes = c.parsedIterableResult.getBytes();
            fout.write(cInBytes);
            fout.flush();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
