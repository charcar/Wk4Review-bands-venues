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
  //
  // @Test
  // public void studentIsCreatedAndDisplayed() {
  //   goTo("http://localhost:4567/students");
  //   fill("#name").with("Bob");
  //   fill("#enrollment").with("12/12/2016");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Bob");
  // }
  //
  //
  // @Test
  // public void courseIsCreatedAndDisplayed() {
  //   goTo("http://localhost:4567/courses");
  //   fill("#name").with("Economics of Butter");
  //   fill("#number").with("ECO111");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Economics of Butter");
  // }
  //
  // @Test
  // public void studentInfoIsDisplayed() {
  //   Student newStudent = new Student("Anne", "12/12/2016");
  //   newStudent.save();
  //   String studentPath = String.format("http://localhost:4567/students/%d", newStudent.getId());
  //   goTo(studentPath);
  //   assertThat(pageSource().contains("zy"));
  // }
}
