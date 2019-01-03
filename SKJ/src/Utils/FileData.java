package Utils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.security.MessageDigest;

public class FileData implements Serializable {
    private File file;
    private String md5sum;

    public FileData(File file) {
        this.file = file;
        this.md5sum = generateMD5Checksum(file);
    }

    private String generateMD5Checksum(File file){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] arr = md.digest(Files.readAllBytes(file.toPath()));
            return DatatypeConverter.printHexBinary(arr);

        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public File getFile() {
        return file;
    }

    public String getMd5sum(){
        return md5sum;
    }

    @Override
    public String toString() {
        return file.getName() + "  |||  " + md5sum;
    }
}
