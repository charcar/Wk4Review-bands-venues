import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Venue.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Venue firstVenue = new Venue("The Waypost");
    Venue secondVenue = new Venue("The Waypost");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesVenueIntoDatabase(){
    Venue firstVenue = new Venue("Olympian");
    firstVenue.save();
    assertEquals(Venue.all().get(0), firstVenue);
  }

  @Test
  public void find_findVenueInDatabase_true() {
    Venue myVenue = new Venue("The Reminder");
    myVenue.save();
    Venue savedVenue = Venue.find(myVenue.getId());
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void addBand_addsBandToVenue() {
    Venue myVenue = new Venue("The GoodFoot");
    myVenue.save();

    Band myBand = new Band("MotownThrowDown", "good mowtown");
    myBand.save();

    myVenue.addBand(myBand);
    Band savedBand = myVenue.getBands().get(0);
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void getBands_returnsAllBands_List() {
    Venue myVenue = new Venue("Hotel Bar");
    myVenue.save();

    Band firstBand = new Band("Old Men", "boring and dependable");
    firstBand.save();
    Band secondBand = new Band("Drunk Bachelor", "morally reprehensible");
    secondBand.save();

    myVenue.addBand(firstBand);
    myVenue.addBand(secondBand);
    List savedBands = myVenue.getBands();
    assertEquals(savedBands.size(), 2);
  }
}
