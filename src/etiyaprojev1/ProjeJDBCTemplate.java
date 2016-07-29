package etiyaprojev1;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;

public class ProjeJDBCTemplate implements ProjeDAO {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }

   public void createStudent(String name) {
      String SQL = "insert into student (name) values (?)";
      
      jdbcTemplateObject.update( SQL, name);
      System.out.println("Created Student Record Name = " + name);
      return;
   }
   
   public void createLecture(String name, String instructure) {
	      String SQL = "insert into lecture (name, instructure) values (?, ?)";
	      
	      jdbcTemplateObject.update( SQL, name, instructure);
	      System.out.println("Created Lecture Record Name = " + name +" Instructure = " + instructure);
	      return;
   }
   
   public void assignLecture(Integer student_id, Integer lecture_id){
	   String SQL = "insert into relation (student_id, lecture_id) values (?, ?)";
	   
	   jdbcTemplateObject.update( SQL, student_id, lecture_id);
	      System.out.println("Kayýt girildi.");
	      return;
   }

   public Student getStudent(Integer id) {
      String SQL = "select * from student where id = ?";
      try{
	      Student student = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{id}, new StudentMapper());
	      return student;
      }catch (EmptyResultDataAccessException e) {
  		  return null;
  	  }  
   }
   
   public Lecture getLecture(Integer id) {
	      String SQL = "select * from lecture where id = ?";
	      try{
		      Lecture lecture = jdbcTemplateObject.queryForObject(SQL, 
		                        new Object[]{id}, new LectureMapper());
		      return lecture;
	      }catch (EmptyResultDataAccessException e) {
	  		  return null;
	  	  } 
   }

   public List<Student> listStudents() {
      String SQL = "select * from Student";
      List <Student> students = jdbcTemplateObject.query(SQL, 
                                new StudentMapper());
      return students;
   }
   
   public List<Lecture> listLectures() {
	      String SQL = "select * from lecture";
	      List <Lecture> lectures = jdbcTemplateObject.query(SQL, 
	                                new LectureMapper());
	      return lectures;
	   }
   
   public List<Relation> listLecturesDetail(Integer studentId){
	      String SQL = "select * from relation where student_id = ?";
	      
	      //çoklu obje alýyor mu?
	      List <Relation> relations = jdbcTemplateObject.query(SQL,  
                  new Object[]{studentId}, new RelationMapper());
	      return relations;
   }
   
   public List<Relation> listStudentsDetail(Integer lectureId){
	      String SQL = "select * from relation where lecture_id = ?";
	      
	    //çoklu obje alýyor mu?
	      List <Relation> relations = jdbcTemplateObject.query(SQL, 
               new Object[]{lectureId}, new RelationMapper());
	      return relations;
}
}