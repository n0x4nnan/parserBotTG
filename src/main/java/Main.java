import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author noxannan
 */


public class Main {
    public static void main(String[] args) throws Exception {
        LinkedIn.getVacancies();
        JobsDOU.getVacancies();
    }
}
