package etiyaprojev1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Functions {
	
	   private int FunctionId;
		
	   public int getId() {
			return FunctionId;
	   }
	
	   public void setId(int FunctionId) {
			this.FunctionId = FunctionId;
	   }
	   
		public String getLectureName(int id){
			   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
			   String LectureName;
			   try{
				   Lecture lecture = projeJDBCTemplate.getLecture(id);
				   LectureName = lecture.getLectureName();
			   }catch(NullPointerException e){
				   LectureName = "";
			   }
			   return LectureName;
		}
		
		public String getStudentName(int id){
			   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
			   String StudentName;
			   try{
				   Student student = projeJDBCTemplate.getStudent(id);
				   StudentName = student.getStudentName();
			   }catch(NullPointerException e){
				   StudentName = "";
			   }
			   return StudentName;
		}
		
		public boolean isStudent(int id){
			   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
			   boolean isStudent;
			   
			   try{
				   Student student = projeJDBCTemplate.getStudent(id);
				   if(student.getStudentId() > 0){
					   isStudent = true;
				   }else{
					   isStudent = false;
				   }
			   }catch(NullPointerException e){
				   isStudent = false;
			   }
			   
			   return isStudent;
		}
		
		public boolean isLecture(int id){
			   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
			   
			   boolean isLecture;
			   
			   try{
				   Lecture lecture = projeJDBCTemplate.getLecture(id);
				   if(lecture.getLectureId() > 0){
					   isLecture = true;
				   }else{
					   isLecture = false;
				   }
			   }catch(NullPointerException e){
				   isLecture = false;
			   }
			   
			   return isLecture;
		}
}
