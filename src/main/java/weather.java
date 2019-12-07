import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class weather {

    private static Document getPage() throws IOException {
        String url = "http://www.m.pogodavtomske.ru";
        return Jsoup.parse(new URL(url), 3000);
    }

    public static void main(String[] args) throws Exception {
        Document page = getPage();

        Element tableWthr = page.select("table[class=whdisplay]").first(); //таблица погоды
        Elements values = tableWthr.select("tr[height=125px]"); //строка погоды

        int index = 0;
        printWeatherValues(values, index);
    }

    private static void printWeatherValues(Elements values, int index){
        for (int i = 0; i < 4; i++){
            Element valueLine = values.get(index);
            for(Element td : valueLine.select("td")){ //вывод погоды на день
                System.out.print(td.text() + "    ");
            }
            System.out.println();
            index++; //переход на следующий день
        }
    }
}
