package login;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;

/**
 * @author pino
 *
 */
public class MyClassTest {

  ProfilesIni allProfiles = null;
  FirefoxProfile profile = null;
  WebDriver driver = null;
  WebElement enter = null;
  String OS = System.getProperty("os.name").toLowerCase();

  Hashtable<String, String> contexto = new Hashtable<String, String>();


  /**
   * 
   */
  @Before
  public void setUp() {
    if (OS.contains("windows")) {
      System.setProperty("webdriver.gecko.driver",
          "src/test/resources/com/selenium/example/windows/geckodriver.exe");
    } else if (OS.contains("linux")) {
      System.setProperty("webdriver.gecko.driver",
          "src/test/resources/com/selenium/example/linux/geckodriver");
    }
    allProfiles = new ProfilesIni();
    profile = allProfiles.getProfile("skipssl");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("firefox");
    capabilities.setCapability("acceptInsecureCerts", true);

    driver = new FirefoxDriver(capabilities);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  /**
   * 
   */
  @After
  public void afterTest() {
    driver.close();
  }


  /**
   * @param busqueda
   * @return
   */
  public int ordenesBusqueda(String busqueda) {
    testOrdenes();

    String cantidad = driver.findElement(By.id("ORDENES_CANTIDAD")).getText().replace('(', ' ')
        .replace(')', ' ').trim();
    int cantindadOrdenes = Integer.parseInt(cantidad);

    contexto.put("ORDENES", cantindadOrdenes + "");

    driver.findElement(By.id("ORDENES_BUSQUEDA")).sendKeys(busqueda);
    driver.findElement(By.id("ORDENES_BUSCAR")).click();

    int cantindadOrdenesBusquda = Integer.parseInt(driver.findElement(By.id("ORDENES_CANTIDAD"))
        .getText().replace('(', ' ').replace(')', ' ').trim());

    return cantindadOrdenesBusquda;


  }

  /**
   * Se realiza una busqueda en el listado de ordenes del texto 'iphone' y se verifica que existan resultados.
   */
  @Test
  public void testOrdenesBusquedaOK() {

    int cantOrdenesHalladas = ordenesBusqueda("iphone");
    int cantidadOrdenes = Integer.parseInt(contexto.get("ORDENES"));
    assert (cantOrdenesHalladas != 0);
    assert (cantidadOrdenes != cantOrdenesHalladas);

  }

  /**
   * 
   */
  @Test
  public void testOrdenesBusquedaWRONG() {

    int cantOrdenesHalladas = ordenesBusqueda("texto donde no deberia aparecer ninguna orden");
    assert (cantOrdenesHalladas == 0);

  }

  /**
   * 
   */
  @Test
  public void testLogin() {
    driver.get("http://localhost:4200/home");
    driver.findElement(By.id("LOGING_USER")).sendKeys("user@falabella.com");
    driver.findElement(By.id("LOGING_PASSWORD")).sendKeys("stefano");
    driver.findElement(By.id("LOGING_BUTTON")).click();;
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.elementToBeClickable(By.id("DASHBOARD_TITULO")));
    assert (driver.getCurrentUrl().endsWith("dashboard"));
  }

  /**
   * 
   */
  @Test
  public void testOrdenes() {
    testLogin();
    driver.get("http://localhost:4200/ordenes");
    driver.findElement(By.id("ORDENES_CANTIDAD")).getText();

  }

}
