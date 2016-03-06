import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import java.util.ArrayList;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Track Your Favorites Bands Favorite Venues");
  }

  @Test
  public void bandIsCreatedAndDisplayed() {
    Band myBand = new Band("The Pretenders", "Definitely not fake");
    myBand.save();
    goTo("http://localhost:4567/bands");
    assertThat(pageSource()).contains("The Pretenders");
  }

  @Test
  public void venueIsCreatedAndDisplayed() {
    Band myBand = new Band("The Pretenders", "Definitely not fake");
    myBand.save();
    Venue myVenue = new Venue("Laurelthirst");
    myVenue.save();
    String newVenueBandPath = String.format("http://localhost:4567/bands/%d", myBand.getId());
    goTo(newVenueBandPath);
    assertThat(pageSource()).contains("Laurelthirst");
  }

  @Test
  public void deleteBand() {
    Band newBand = new Band("Flogging Molly's", "good stuff");
    newBand.save();
    String bandPath = String.format("http://localhost:4567/bands/%d/update", newBand.getId());
    goTo(bandPath);
    submit("#deleteBand");
    goTo("http://localhost:4567/bands");
    assertFalse(pageSource().contains("Floggin Molly's"));
  }
}
