import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public/");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/students", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("students", Student.all());
      model.put("template", "templates/students.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/students", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String enrollment = request.queryParams("enrollment");
      Student newStudent = new Student(name, enrollment);
      newStudent.save();
      response.redirect("/students");
      return null;
    });

    get("/courses", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Course> courses = Course.all();
      model.put("courses", courses);
      model.put("template", "templates/courses.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/courses", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String number = request.queryParams("number");
      Course newCourse = new Course(name, number);
      newCourse.save();
      response.redirect("/courses");
      return null;
    });

    get("/students/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Student student = Student.find(id);
      model.put("student", student);
      model.put("allCourses", Course.all());
      model.put("template", "templates/student.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/courses/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Course course = Course.find(id);
      model.put("course", course);
      model.put("allStudents", Student.all());
      model.put("template", "templates/course.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add_students", (request, response) -> {
      int student_id = Integer.parseInt(request.queryParams("student_id"));
      int course_id = Integer.parseInt(request.queryParams("course_id"));
      Course course = Course.find(course_id);
      Student student = Student.find(student_id);
      course.addStudent(student);
      response.redirect("/courses/" + course_id);
      return null;
    });

    post("/add_courses", (request, response) -> {
      int student_id = Integer.parseInt(request.queryParams("student_id"));
      int course_id = Integer.parseInt(request.queryParams("course_id"));
      Course course = Course.find(course_id);
      Student student = Student.find(student_id);
      student.addCourse(course);
      response.redirect("/students/" + student_id);
      return null;
    });

    get("/students/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Student student = Student.find(Integer.parseInt(request.params("id")));
      model.put("student", student);
      model.put("template", "templates/student-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/students/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Student student = Student.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      String enrollment = request.queryParams("enrollment");
      student.update(name, enrollment);
      response.redirect("/students");
      return null;
      });

    post("/students/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Student student = Student.find(Integer.parseInt(request.params("id")));
      student.delete();
      response.redirect("/students");
      return null;
    });

    get("/courses/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Course course = Course.find(Integer.parseInt(request.params("id")));
      model.put("course", course);
      model.put("template", "templates/course-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/courses/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Course course = Course.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      String number = request.queryParams("number");
      course.update(name, number);
      response.redirect("/courses");
      return null;
      });

    post("/courses/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Course course = Course.find(Integer.parseInt(request.params("id")));
      course.delete();
      response.redirect("/courses");
      return null;
    });
  }
}
