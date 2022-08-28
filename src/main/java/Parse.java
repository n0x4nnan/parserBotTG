import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author noxannan
 */
public class Parse {

    static String url = null;
    static String fileName = null;
    static String siteName = null;


    protected static int getCountVacanciesFromDoc() throws IOException {
        Document doc= getPage(url);
        String selectedLine=String.valueOf(doc.select("h1"));
        String currentCount=selectedLine.substring(selectedLine.indexOf("<h1>"),
                        selectedLine.indexOf(" "))
                .replace("<h1>","");
        return Integer.parseInt(currentCount);
    }


    protected static void getVacancies() throws IOException {
        createFile(fileName);
        int previousVacancies = readPreviousCountFromFile(fileName);
        int currentVacancies = getCountVacanciesFromDoc();
        differenceBetweenVacanciesCount(currentVacancies, previousVacancies,siteName);

    }


    protected static int readPreviousCountFromFile(final String fileName) throws IOException {
        List<String> contentFileList = Files.readAllLines(Path.of(fileName));
        if (contentFileList.isEmpty()) {
            return 0;
        } else {
            if (contentFileList.get(0).isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(contentFileList.get(0));
            }
        }
    }


    protected static File createFile(final String fileName) {
        try {
            File vacancyFile = new File(fileName);
            if (vacancyFile.createNewFile()) {
                System.out.println("File created: " + vacancyFile.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return null;
    }


    protected static Document getPage(String url) throws IOException {
        return Jsoup.connect(url).get();
    }


    protected static void differenceBetweenVacanciesCount(final int currentVacancyCount, final int previousVacancyCount, final String siteName) {
        System.out.println("ПОШУК ВАКАНСІЙ НА САЙТІ: " + siteName);
        int differenceVacancyNumber = currentVacancyCount - previousVacancyCount;
        if (differenceVacancyNumber < 0) {
            System.out.println("Ти втратив :" + Math.abs(differenceVacancyNumber) + " вакансій");
        } else {
            System.out.println("Нових вакансій знайдено: " + differenceVacancyNumber);
        }
    }


    protected static void writeCountToFile(final String vacancyCount, final String fileName) throws IOException {
        FileWriter wr=new FileWriter(fileName);
        wr.write(vacancyCount);
        wr.close();
    }
}
