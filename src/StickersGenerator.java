import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class StickersGenerator {

  public void generateSticker(InputStream inputStream, String nameFile) throws Exception {
    // Leitura de imagem
    // InputStream inputStream = new FileInputStream(new
    // File("../entrada/filme_maior.jpg"));
    /*
     * InputStream inputStream = new URL(
     * "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
     * .openStream();
     */
    BufferedImage originalImage = ImageIO.read(inputStream);

    // Cria nova imagem em memória com transparencia e com tamanho novo
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    int newHeigth = height + 200;
    BufferedImage newImage = new BufferedImage(width, newHeigth, BufferedImage.TRANSLUCENT);

    // Copiar a imagem original pra nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);

    // configurar a fonte
    var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    // escrever uma nova frase na nova imagem
    graphics.drawString("TOPZERA", 100, newHeigth - 100);

    // escrever a nova imagem em um arquivo
    ImageIO.write(newImage, "png", new File(nameFile));
  }

}
