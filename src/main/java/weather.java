import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class weather {

    private static Document getPage() throws IOException {
        String url = "http://www.m.pogodavtomske.ru";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private static Pattern pattern = Pattern.compile("\\d{1}");

    private static String searchDate(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if(matcher.find()){
            return matcher.group();
        }
        throw new Exception("Не удалось извлечь дату из строки");
    }

    public static void main(String[] args) throws Exception {
        Document page = getPage();

        Element tableWthr = page.select("table[class=whdisplay]").first(); //таблица погоды
        Elements values = tableWthr.select("tr[height=125px]"); //строка погоды

        for (Element name : values ) {
            String dateString = name.select("").toString();
            String date = searchDate(dateString);
            System.out.println("дата    осадки    температура    ветер    давление");
        }
    }


}
