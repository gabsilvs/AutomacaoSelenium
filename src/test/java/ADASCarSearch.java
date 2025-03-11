import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class ADASCarSearch {
    @Test
    public void pesquisarADASDoCarro() {
        String modeloCarro = "Honda Civic 2024"; // Insira aqui o modelo do carro

        System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");

        WebDriver navegador = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) navegador;
        js.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        navegador.get("https://www.google.com/");

        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        searchBox.sendKeys(modeloCarro + " sistema ADAS", Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3")));

        List<WebElement> titulos = navegador.findElements(By.cssSelector("h3"));
        List<WebElement> links = navegador.findElements(By.cssSelector(".tF2Cxc a"));

        System.out.println("\nResultados sobre ADAS para: " + modeloCarro);

        // Abrir os 3 primeiros links
        for (int i = 0; i < Math.min(3, titulos.size()); i++) {
            String titulo = titulos.get(i).getText();
            String link = links.get(i).getAttribute("href");

            System.out.println("\n" + (i + 1) + ". " + titulo);
            System.out.println("   Link: " + link);

            navegador.switchTo().newWindow(WindowType.TAB);
            navegador.get(link);

            try {
                Thread.sleep(3000);

                List<WebElement> paragrafos = navegador.findElements(By.tagName("p"));
                StringBuilder textoADAS = new StringBuilder();

                for (WebElement p : paragrafos) {
                    String texto = p.getText();
                    if (texto.toLowerCase().contains("adas") || texto.toLowerCase().contains("assistência") || texto.toLowerCase().contains("segurança")) {
                        textoADAS.append(texto).append("\n");
                    }
                }

                System.out.println("Informações sobre ADAS encontradas:");
                System.out.println("   " + (textoADAS.length() > 0 ? textoADAS.toString() : "Nenhuma informação relevante encontrada."));

            } catch (Exception e) {
                System.out.println("  Erro ao carregar a página: " + e.getMessage());
            }
        }

        navegador.quit();
    }
}
