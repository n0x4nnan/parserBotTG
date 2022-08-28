import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author noxannan
 */
public class JobsDOU extends Parse {
    public static final String url = "https://jobs.dou.ua/vacancies/?category=Java";
    public static final String fileName = "jobsDou.txt";
    public static final String siteName = "Jobs DOU";

     public static void getVacancies() throws IOException {
        Parse.createFile(JobsDOU.fileName);
        int previousVacancies = Parse.readPreviousCountFromFile(JobsDOU.fileName);
        int currentVacancies = JobsDOU.getCountVacanciesFromDoc();
        Parse.differenceBetweenVacanciesCount(currentVacancies, previousVacancies,JobsDOU.siteName);
    }

    public static int getCountVacanciesFromDoc()throws IOException{
        Document doc= Parse.getPage(url);
        String selectedLine=String.valueOf(doc.select("h1"));
        String currentCount=selectedLine.substring(selectedLine.indexOf("<h1>"),
                selectedLine.indexOf(" "))
                .replace("<h1>","");
        Parse.writeCountToFile(currentCount, fileName);
        return Integer.parseInt(currentCount);
    }


    public static void main(String[] args) throws IOException {
        System.out.println(getCountVacanciesFromDoc());
    }





}
