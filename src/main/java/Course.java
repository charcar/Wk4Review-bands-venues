import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Course {
  private int id;
  private String name;
  private String number;

  public Course(String name, String number) {
    this.name = name;
    this.number = number;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getNumber() {
    return number;
  }


  public static List<Course> all() {
    String sql = "SELECT id, name, number FROM courses";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Course.class);
    }
  }

  @Override
  public boolean equals(Object otherCourse){
    if (!(otherCourse instanceof Course)) {
      return false;
    } else {
      Course newCourse = (Course) otherCourse;
      return this.getName().equals(newCourse.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO courses(name, number) VALUES (:name, :number)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("number", number)
        .executeUpdate()
        .getKey();
    }
  }

  public static Course find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM courses where id=:id";
      Course course = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Course.class);
      return course;
    }
  }

  public void addStudent(Student student) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students_courses (course_id, student_id) VALUES (:course_id, :student_id)";
      con.createQuery(sql)
        .addParameter("course_id", this.getId())
        .addParameter("student_id", student.getId())
        .executeUpdate();
    }
  }

  public List<Student> getStudents() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT students.* FROM courses JOIN students_courses ON (courses.id = students_courses.course_id) JOIN students ON (students_courses.student_id = students.id) WHERE courses.id = :id";
      return con.createQuery(sql).addParameter("id", this.getId()).executeAndFetch(Student.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM courses WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM students_courses WHERE course_id = :course_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("course_id", this.getId())
          .executeUpdate();
    }
  }

  public void update(String newName, String newNumber) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE courses SET name = :newName, number = :newNumber WHERE id = :id";
      this.name = newName;
      con.createQuery(sql)
      .addParameter("newName", newName)
      .addParameter("newNumber", newNumber)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public static void clear(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM courses";
      con.createQuery(sql)
      .executeUpdate();
    }
  }
}
