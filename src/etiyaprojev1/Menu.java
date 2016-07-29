package etiyaprojev1;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import etiyaprojev1.ProjeJDBCTemplate;

public class Menu {
	   private int MenuId;
	
	   public int getId() {
			return MenuId;
	   }
	
	   public void setId(int MenuId) {
			this.MenuId = MenuId;
	   }
	
	   public void createStudent(){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   String newStudentName;
		   Scanner input = new Scanner(System.in);

		   System.out.println("Bir ��renci giriniz : ");
		   newStudentName = input.nextLine();
		   projeJDBCTemplate.createStudent(newStudentName);
		   input.close();
	   }
	   
	   public void createLecture(){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   Scanner input = new Scanner(System.in);
		   String newLectureName = "";
		   String newInstructureName = "";
		   
		   System.out.print("Bir ders giriniz : ");
		   newLectureName = input.nextLine();
		   System.out.print("Dersin ��retmenini giriniz : ");
		   newInstructureName = input.nextLine();
		   projeJDBCTemplate.createLecture(newLectureName,newInstructureName); 
		   input.close();
	   }
	   
	   public void assignLecture(){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   Scanner input = new Scanner(System.in);
		   Student student = context.getBean("student", Student.class);
		   Functions function = new Functions();	
		   int student_id;
		   int lecture_id;
		   boolean isStudent;
		   boolean isLecture;
		   
		   System.out.println("Ders atayaca��n�z ��renci numaras�n� giriniz:");
		   student_id = input.nextInt();
		   isStudent = function.isStudent(student_id);
		   //System.out.println(isStudent);
		   if(isStudent == true){
			   System.out.println("Ders kodu giriniz:");
			   lecture_id = input.nextInt();
			   isLecture = function.isLecture(lecture_id);
			   if(isLecture == true){
				   student.getLecture().take(student_id,lecture_id);
			   }else{
				   System.out.println("Ge�ersiz ders kodu");
			   }   
		   }else{
			   System.out.println("Ge�ersiz ��renci numaras�");
		   }  
		   input.close();
	   }
	   
	   public void listOfStudents(){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   System.out.println("------��renciler Listeleniyor--------" );
		   List<Student> students = projeJDBCTemplate.listStudents();
		   for (Student record : students) {
		      System.out.print("��renci Numaras� : " + record.getStudentId() );
		      System.out.println(", �sim: " + record.getStudentName() );
		   }
	   }
	   
	   public void listofLectures(){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   System.out.println("------Dersler Listeleniyor--------" );
		   List<Lecture> lectures = projeJDBCTemplate.listLectures();
		   for (Lecture record : lectures) {
		      System.out.print("Ders Kodu : " + record.getLectureId() );
		      System.out.print(", Ders �smi : " + record.getLectureName() );
		      System.out.println(", ��retmen �smi : " + record.getLectureInstructure() );
		   }
	   }
	   
	   public void getStudent(int id){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   System.out.println(id+" numaral� ��renci ara�t�r�l�yor-----" );
		   try{
			   Student student = projeJDBCTemplate.getStudent(id);
			   System.out.print("��renci Numaras�: " + student.getStudentId() );
			   System.out.println(", �sim : " + student.getStudentName() );
		   }catch(NullPointerException e){
			   System.out.println("Bu numara hi�bir ��renciye ait de�ildir.");
		   }
	   }
	   
	   public void getLecture(int id){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   System.out.println(id+" kodlu ders ara�t�r�l�yor-----");
		   try{
			   Lecture lecture = projeJDBCTemplate.getLecture(id);
			   System.out.print("Ders Kodu : " + lecture.getLectureId() );
			   System.out.print(", �sim : " + lecture.getLectureName() );
			   System.out.println(", ��retmen �smi : " + lecture.getLectureInstructure() );
		   }catch(NullPointerException e){
			   System.out.println("Bu kod hi�bir derse ait de�ildir.");
		   }
	   }
	   
	   public void listLecturesDetail(int studentId){ //��rencinin Ders Bilgisi
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   Functions function = new Functions();
		   System.out.println("��renci �smi : " + function.getStudentName(studentId));
		   
		   System.out.println("��rencinin dersleri getiriliyor.");
		   List<Relation> relations = projeJDBCTemplate.listLecturesDetail(studentId);
		   for (Relation record : relations) {
		      System.out.println("Ders Kodu : " + record.getLectureId() );     
		      System.out.println("Ders �smi : " + function.getLectureName(record.getLectureId()));
		   }
	   }
	   
	   
	   public void listStudentsDetail(int lectureId){ //Dersi Alan ��renci Bilgileri
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   Functions function = new Functions();	  
		   System.out.println("Ders �smi : " + function.getLectureName(lectureId));
		   
		   System.out.println("��rencinin dersleri getiriliyor.");
		   List<Relation> relations = projeJDBCTemplate.listStudentsDetail(lectureId);
		   for (Relation record : relations) {
			      System.out.println("��renci Numaras� : " + record.getStudentId() );
			      System.out.println("��renci �smi : " + function.getStudentName(record.getStudentId()));
		   }
	   }
}
