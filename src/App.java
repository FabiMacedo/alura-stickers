import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://alura-linguagens-api-fabi.herokuapp.com/languages";
        ContentExtractor extractor = new IMDBContentExtractor();

        /*
         * String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
         * ContentExtractor extractor = new IMDBContentExtractor();
         */

        /*
         * String url =
         * "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
         * ContentExtractor extractor = new NasaContentExtractor();
         */

        var http = new ClientHttp();
        String json = http.searchData(url);

        // exibir e manipular os dados
        List<Content> contents = extractor.extractContent(json);

        var generate = new StickersGenerator();

        for (int i = 0; i < 4; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();

            String nameFile = "../saida/" + content.getTitle().replace(":", "-") + ".png";

            generate.generateSticker(inputStream, nameFile);

            System.out.println("Título: " + content.getTitle());
            // System.out.println("Classificação: " + content.get("imDbRating"));
            System.out.println();
        }

    }

}
