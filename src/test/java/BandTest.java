import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Band.all().size());
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Band firstBand = new Band("Four Tet", "Electronic, pioneer");
    Band secondBand = new Band("Four Tet", "Electronic, pioneer");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Band myBand = new Band("Four Tet", "Electronic, pioneer");
    myBand.save();
    Band savedBand = Band.all().get(0);
    assertTrue(savedBand.equals(myBand));
  }

  @Test
  public void save_assignsIdToObject() {
    Band myBand = new Band("Buttah", "Electronic, pioneer");
    myBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(myBand.getId(), savedBand.getId());
  }

  @Test
  public void find_findsBandInDatabase_true() {
    Band myBand = new Band("Butter", "Electronic, pioneer");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void addVenue_addsVenueToBand() {
    Venue myVenue = new Venue("Triple Nipple");
    myVenue.save();

    Band myBand = new Band("Four Tet", "new electro");
    myBand.save();

    myBand.addVenue(myVenue);
    Venue savedVenue = myBand.getVenues().get(0);
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void getVenues_returnsAllVenues_List() {
    Venue myVenue = new Venue("Paradox");
    myVenue.save();

    Band myBand = new Band("Lettuce", "contemporary funk");
    myBand.save();

    myBand.addVenue(myVenue);
    List savedVenues = myBand.getVenues();
    assertEquals(savedVenues.size(), 1);
  }

  @Test
  public void delete_deletesAllBandsAndListsAssoicationes() {
    Venue myVenue = new Venue("Triple Nickel");
    myVenue.save();

    Band myBand = new Band("The Wu-tang Clan", "contemporary poetry");
    myBand.save();

    myBand.addVenue(myVenue);
    myBand.delete();
    assertEquals(myVenue.getBands().size(), 0);
  }

  @Test
  public void update_updatesBand() {
    Band myBand = new Band("Frogstomp","old punk");
    myBand.save();
    String newName = "Sue";
    String newDescription = "A band named Sue";
    myBand.update(newName, newDescription);
    assertTrue(Band.all().get(0).getName().equals(newName) && (Band.all().get(0).getDescription().equals(newDescription)));
  }

  @Test
  public void getName_returnsBandName() {
    Band myBand = new Band("Jane's Addiction","a decent band");
    myBand.save();
    assertTrue(Band.all().get(0).getName().equals("Jane's Addiction"));
  }

  @Test
  public void getId_returnsBandId() {
    Band myBand = new Band("Jane","12/12/16");
    myBand.save();
    assertEquals(Band.all().get(0).getId(), myBand.getId());
  }
}
