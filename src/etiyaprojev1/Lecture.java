package etiyaprojev1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lecture implements TakeLecture {
	private int LectureId;
	private String LectureName;
	private String LectureInstructure;
	
	public int getLectureId() {
		return LectureId;
	}
	public void setLectureId(int lectureId) {
		LectureId = lectureId;
	}
	public String getLectureName() {
		return LectureName;
	}
	public void setLectureName(String lectureName) {
		LectureName = lectureName;
	}
	public String getLectureInstructure() {
		return LectureInstructure;
	}
	public void setLectureInstructure(String lectureInstructure) {
		LectureInstructure = lectureInstructure;
	}
	public void take(int student_id, int lecture_id){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");

		   projeJDBCTemplate.assignLecture(student_id,lecture_id);
	}
}
