import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Student.all().size());
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Student firstStudent = new Student("Tim", "12/12/2016");
    Student secondStudent = new Student("Tim", "12/12/2016");
    assertTrue(firstStudent.equals(secondStudent));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Student myStudent = new Student("Tim", "12/12/2016");
    myStudent.save();
    Student savedStudent = Student.all().get(0);
    assertTrue(savedStudent.equals(myStudent));
  }

  @Test
  public void save_assignsIdToObject() {
    Student myStudent = new Student("Buttah", "12/12/2016");
    myStudent.save();
    Student savedStudent = Student.all().get(0);
    assertEquals(myStudent.getId(), savedStudent.getId());
  }

  @Test
  public void find_findsStudentInDatabase_true() {
    Student myStudent = new Student("Butter", "12/12/2016");
    myStudent.save();
    Student savedStudent = Student.find(myStudent.getId());
    assertTrue(myStudent.equals(savedStudent));
  }

  @Test
  public void addCourse_addsCourseToStudent() {
    Course myCourse = new Course("History of Butter", "HIST100");
    myCourse.save();

    Student myStudent = new Student("Tim", "09/12/2016");
    myStudent.save();

    myStudent.addCourse(myCourse);
    Course savedCourse = myStudent.getCourses().get(0);
    assertTrue(myCourse.equals(savedCourse));
  }

  @Test
  public void getCourses_returnsAllCourses_List() {
    Course myCourse = new Course("History of Butter", "HIST100");
    myCourse.save();

    Student myStudent = new Student("Butter", "12/12/2016");
    myStudent.save();

    myStudent.addCourse(myCourse);
    List savedCourses = myStudent.getCourses();
    assertEquals(savedCourses.size(), 1);
  }

  @Test
  public void delete_deletesAllStudentsAndListsAssoicationes() {
    Course myCourse = new Course("History of Butter", "HIST100");
    myCourse.save();

    Student myStudent = new Student("Butter", "12/12/2016");
    myStudent.save();

    myStudent.addCourse(myCourse);
    myStudent.delete();
    assertEquals(myCourse.getStudents().size(), 0);
  }

  @Test
  public void update_updatesStudent() {
    Student myStudent = new Student("Jane","12/12/16");
    myStudent.save();
    String newName = "Sue";
    String newDateOfEnrollment = "10/10/2016";
    myStudent.update(newName, newDateOfEnrollment);
    assertTrue(Student.all().get(0).getName().equals(newName) && (Student.all().get(0).getDateOfEnrollment().equals(newDateOfEnrollment)));
  }

  @Test
  public void getName_returnsStudentName() {
    Student myStudent = new Student("Jane","12/12/16");
    myStudent.save();
    assertTrue(Student.all().get(0).getName().equals("Jane"));
  }

  @Test
  public void getId_returnsStudentId() {
    Student myStudent = new Student("Jane","12/12/16");
    myStudent.save();
    assertEquals(Student.all().get(0).getId(), myStudent.getId());
  }
}
