import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @author noxannan
 */
public class LinkedIn{
    public static final String url = "https://ua.linkedin.com/jobs/junior-java-developer-jobs?position=1&pageNum=0";
    public static final String fileName = "linkedIn.txt";
    public static final String siteName = "LinkedIn";

    static void getVacancies() throws IOException {
        Parse.createFile(LinkedIn.fileName);
        int previousVacancies = Parse.readPreviousCountFromFile(LinkedIn.fileName);
        int currentVacancies = LinkedIn.getCountVacanciesFromDoc();
        Parse.differenceBetweenVacanciesCount(currentVacancies, previousVacancies,LinkedIn.siteName);

    }

    public static int getCountVacanciesFromDoc() throws IOException {
        Document doc = Parse.getPage(url);
        Element jobCount=doc.selectFirst("span[class=\"results-context-header__job-count\"]");
        String downCast= String.valueOf(jobCount);
        String currentCount=downCast.substring(downCast.indexOf(">"),
                downCast.indexOf("</")).
                replace(">","").
                trim();
        Parse.writeCountToFile(currentCount, fileName);
        return Integer.parseInt(currentCount);
    }

}
