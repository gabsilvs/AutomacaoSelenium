import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class ADASCarSearch {
    @Test
    public void pesquisarADASDoCarro() {
        // Primeiro, pede o modelo do carro ao usuário antes de abrir o navegador
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o modelo do carro (ex: Toyota Corolla 2023): ");
        String modeloCarro = scanner.nextLine();
        scanner.close();

        System.setProperty("webdriver.chrome.driver", "src\\drive\\chromedriver.exe");

        // Configuração para evitar detecção
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");

        WebDriver navegador = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));

        // Removendo detecção de WebDriver via JavaScript
        JavascriptExecutor js = (JavascriptExecutor) navegador;
        js.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        // Acessa a página inicial do Google
        navegador.get("https://www.google.com/");

        // Aguarda o campo de pesquisa e faz a busca
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        searchBox.sendKeys(modeloCarro + " sistema ADAS", Keys.ENTER);

        // Aguarda os resultados carregarem
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3")));

        // Captura os títulos e links dos primeiros resultados
        List<WebElement> titulos = navegador.findElements(By.cssSelector("h3"));
        List<WebElement> links = navegador.findElements(By.cssSelector(".tF2Cxc a"));

        // Exibe os primeiros 5 resultados
        System.out.println("\nResultados sobre ADAS para: " + modeloCarro);
        for (int i = 0; i < Math.min(5, titulos.size()); i++) {
            System.out.println((i + 1) + ". " + titulos.get(i).getText());
            System.out.println("   Link: " + links.get(i).getAttribute("href"));
        }

        // Fecha o navegador
        navegador.quit();
    }
}
