import java.util.Arrays;
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

  // @Test
  // public void save_savesIntoDatabase_true() {
  //   Venue myVenue = new Venue("Olympian");
  //   myVenue.save();
  //   assertTrue(Venue.all().get(0).equals(myVenue));
  // }

  // @Test
  // public void find_findVenueInDatabase_true() {
  //   Venue myVenue = new Venue("Sociology of Food", "SOCY210");
  //   myVenue.save();
  //   Venue savedVenue = Venue.find(myVenue.getId());
  //   assertTrue(myVenue.equals(savedVenue));
  // }
  //
  // @Test
  // public void addStudent_addsStudentToVenue() {
  //   Venue myVenue = new Venue("Sociology of Food", "SOCY210");
  //   myVenue.save();
  //
  //   Student myStudent = new Student("Jamie", "01/01/2017");
  //   myStudent.save();
  //
  //   myVenue.addStudent(myStudent);
  //   Student savedStudent = myVenue.getStudents().get(0);
  //   assertTrue(myStudent.equals(savedStudent));
  // }
  //
  // @Test
  // public void getStudents_returnsAllStudents_List() {
  //   Venue myVenue = new Venue("Sociology of Food", "SOCY210");
  //   myVenue.save();
  //
  //   Student firstStudent = new Student("Anna", "01/01/2017");
  //   firstStudent.save();
  //   Student secondStudent = new Student("John", "01/01/2017");
  //   secondStudent.save();
  //
  //   myVenue.addStudent(firstStudent);
  //   myVenue.addStudent(secondStudent);
  //   List savedStudents = myVenue.getStudents();
  //   assertEquals(savedStudents.size(), 2);
  // }
  //
  //
  // @Test
  // public void getNumber_courseNumber(){
  //   Venue myVenue = new Venue("Sociology of Food", "SOCY210");
  //   myVenue.save();
  //   assertEquals(Venue.all().get(0).getNumber(), "SOCY210");
  // }
}
