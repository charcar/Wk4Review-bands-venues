import java.util.Arrays;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class CourseTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Course.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Course firstCourse = new Course("History of Butter", "HIST100");
    Course secondCourse = new Course("History of Butter", "HIST100");
    assertTrue(firstCourse.equals(secondCourse));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Course myCourse = new Course("Family Trouble/Car Trouble", "HOMEC300");
    myCourse.save();
    assertTrue(Course.all().get(0).equals(myCourse));
  }

  @Test
  public void find_findCourseInDatabase_true() {
    Course myCourse = new Course("Sociology of Food", "SOCY210");
    myCourse.save();
    Course savedCourse = Course.find(myCourse.getId());
    assertTrue(myCourse.equals(savedCourse));
  }

  @Test
  public void addStudent_addsStudentToCourse() {
    Course myCourse = new Course("Sociology of Food", "SOCY210");
    myCourse.save();

    Student myStudent = new Student("Jamie", "01/01/2017");
    myStudent.save();

    myCourse.addStudent(myStudent);
    Student savedStudent = myCourse.getStudents().get(0);
    assertTrue(myStudent.equals(savedStudent));
  }

  @Test
  public void getStudents_returnsAllStudents_List() {
    Course myCourse = new Course("Sociology of Food", "SOCY210");
    myCourse.save();

    Student firstStudent = new Student("Anna", "01/01/2017");
    firstStudent.save();
    Student secondStudent = new Student("John", "01/01/2017");
    secondStudent.save();

    myCourse.addStudent(firstStudent);
    myCourse.addStudent(secondStudent);
    List savedStudents = myCourse.getStudents();
    assertEquals(savedStudents.size(), 2);
  }

  @Test
  public void delete_deletesAllStudentsAndListsAssoicationes() {
    Course myCourse = new Course("Sociology of Food", "SOCY210");
    myCourse.save();

    Student myStudent = new Student("Jamie", "01/01/2017");
    myStudent.save();

    myCourse.addStudent(myStudent);
    myCourse.delete();
    assertEquals(myStudent.getCourses().size(), 0);
  }

  @Test
  public void update_updatesCourse() {
    Course myCourse = new Course("Sociology of Food", "SOCY210");
    myCourse.save();
    String newName = "History of RecReRate";
    String newNumber = "REC800";
    myCourse.update(newName, newNumber);
    assertTrue(Course.all().get(0).getName().equals(newName) && (Course.all().get(0).getNumber().equals(newNumber)));
  }

  @Test
  public void getNumber_courseNumber(){
    Course myCourse = new Course("Sociology of Food", "SOCY210");
    myCourse.save();
    assertEquals(Course.all().get(0).getNumber(), "SOCY210");
  }
}
