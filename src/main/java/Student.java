import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Student {
  private int id;
  private String name;
  private String dateOfEnrollment;

  public Student(String name, String dateOfEnrollment) {
    this.id = id;
    this.name = name;
    this.dateOfEnrollment = dateOfEnrollment;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDateOfEnrollment() {
    return dateOfEnrollment;
  }


  @Override
  public boolean equals(Object otherStudent){
    if (!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
      return this.getName().equals(newStudent.getName()) &&
             this.getId() == newStudent.getId();
    }
  }

  public static List<Student> all() {
    String sql = "SELECT * FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students(name, dateOfEnrollment) VALUES (:name, :dateOfEnrollment)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("dateOfEnrollment", dateOfEnrollment)
        .executeUpdate()
        .getKey();
    }
  }

  public static Student find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students where id=:id";
      Student student = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Student.class);
      return student;
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE students SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addCourse(Course course) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students_courses (course_id, student_id) VALUES (:course_id, :student_id)";
      con.createQuery(sql)
        .addParameter("course_id", course.getId())
        .addParameter("student_id", this.getId())
        .executeUpdate();
    }
  }

  public List<Course> getCourses() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT courses.* FROM students JOIN students_courses ON (students.id = students_courses.student_id) JOIN courses ON (students_courses.course_id = courses.id) WHERE students.id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetch(Course.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM students WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM students_courses WHERE student_id = :student_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("student_id", this.getId())
          .executeUpdate();
    }
  }

  public static void clear(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM students";
      con.createQuery(sql)
      .executeUpdate();
    }
  }

  public void update(String newName, String newDateOfEnrollment) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE students SET name = :newName, dateOfEnrollment = :newDateOfEnrollment WHERE id = :id";
      this.name = newName;
      con.createQuery(sql)
      .addParameter("newName", newName)
      .addParameter("newDateOfEnrollment", newDateOfEnrollment)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
