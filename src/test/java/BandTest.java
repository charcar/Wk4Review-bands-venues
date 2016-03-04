import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BandTest {

  // @Rule
  // public DatabaseRule database = new DatabaseRule();
  //
  // @Test
  // public void all_emptyAtFirst() {
  //   assertEquals(0, Band.all().size());
  // }
  //
  // @Test
  // public void equals_returnsTrueIfDescriptionsAretheSame() {
  //   Band firstBand = new Band("Tim", "12/12/2016");
  //   Band secondBand = new Band("Tim", "12/12/2016");
  //   assertTrue(firstBand.equals(secondBand));
  // }
  //
  // @Test
  // public void save_savesObjectIntoDatabase() {
  //   Band myBand = new Band("Tim", "12/12/2016");
  //   myBand.save();
  //   Band savedBand = Band.all().get(0);
  //   assertTrue(savedBand.equals(myBand));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Band myBand = new Band("Buttah", "12/12/2016");
  //   myBand.save();
  //   Band savedBand = Band.all().get(0);
  //   assertEquals(myBand.getId(), savedBand.getId());
  // }
  //
  // @Test
  // public void find_findsBandInDatabase_true() {
  //   Band myBand = new Band("Butter", "12/12/2016");
  //   myBand.save();
  //   Band savedBand = Band.find(myBand.getId());
  //   assertTrue(myBand.equals(savedBand));
  // }
  //
  // @Test
  // public void addCourse_addsCourseToBand() {
  //   Course myCourse = new Course("History of Butter", "HIST100");
  //   myCourse.save();
  //
  //   Band myBand = new Band("Tim", "09/12/2016");
  //   myBand.save();
  //
  //   myBand.addCourse(myCourse);
  //   Course savedCourse = myBand.getCourses().get(0);
  //   assertTrue(myCourse.equals(savedCourse));
  // }
  //
  // @Test
  // public void getCourses_returnsAllCourses_List() {
  //   Course myCourse = new Course("History of Butter", "HIST100");
  //   myCourse.save();
  //
  //   Band myBand = new Band("Butter", "12/12/2016");
  //   myBand.save();
  //
  //   myBand.addCourse(myCourse);
  //   List savedCourses = myBand.getCourses();
  //   assertEquals(savedCourses.size(), 1);
  // }
  //
  // @Test
  // public void delete_deletesAllBandsAndListsAssoicationes() {
  //   Course myCourse = new Course("History of Butter", "HIST100");
  //   myCourse.save();
  //
  //   Band myBand = new Band("Butter", "12/12/2016");
  //   myBand.save();
  //
  //   myBand.addCourse(myCourse);
  //   myBand.delete();
  //   assertEquals(myCourse.getBands().size(), 0);
  // }
  //
  // @Test
  // public void update_updatesBand() {
  //   Band myBand = new Band("Jane","12/12/16");
  //   myBand.save();
  //   String newName = "Sue";
  //   String newDateOfEnrollment = "10/10/2016";
  //   myBand.update(newName, newDateOfEnrollment);
  //   assertTrue(Band.all().get(0).getName().equals(newName) && (Band.all().get(0).getDateOfEnrollment().equals(newDateOfEnrollment)));
  // }
  //
  // @Test
  // public void getName_returnsBandName() {
  //   Band myBand = new Band("Jane","12/12/16");
  //   myBand.save();
  //   assertTrue(Band.all().get(0).getName().equals("Jane"));
  // }
  //
  // @Test
  // public void getId_returnsBandId() {
  //   Band myBand = new Band("Jane","12/12/16");
  //   myBand.save();
  //   assertEquals(Band.all().get(0).getId(), myBand.getId());
  // }
}
