package etiyaprojev1;

import java.util.List;

import javax.sql.DataSource;

public interface ProjeDAO {
 
   public void setDataSource(DataSource ds);
  
   public void createStudent(String name);
   
   public void createLecture(String name, String instructure);
   
   public void assignLecture(Integer student_id, Integer lecture_id);
 
   public Student getStudent(Integer id);
   
   public Lecture getLecture(Integer id);

   public List<Student> listStudents();
   
   public List<Lecture> listLectures();
   
   public List<Relation> listLecturesDetail(Integer studentId);
   
   public List<Relation> listStudentsDetail(Integer lectureId);
}