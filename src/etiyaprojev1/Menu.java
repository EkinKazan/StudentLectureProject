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

		   System.out.println("Bir öðrenci giriniz : ");
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
		   System.out.print("Dersin öðretmenini giriniz : ");
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
		   
		   System.out.println("Ders atayacaðýnýz öðrenci numarasýný giriniz:");
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
				   System.out.println("Geçersiz ders kodu");
			   }   
		   }else{
			   System.out.println("Geçersiz öðrenci numarasý");
		   }  
		   input.close();
	   }
	   
	   public void listOfStudents(){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   System.out.println("------Öðrenciler Listeleniyor--------" );
		   List<Student> students = projeJDBCTemplate.listStudents();
		   for (Student record : students) {
		      System.out.print("Öðrenci Numarasý : " + record.getStudentId() );
		      System.out.println(", Ýsim: " + record.getStudentName() );
		   }
	   }
	   
	   public void listofLectures(){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   System.out.println("------Dersler Listeleniyor--------" );
		   List<Lecture> lectures = projeJDBCTemplate.listLectures();
		   for (Lecture record : lectures) {
		      System.out.print("Ders Kodu : " + record.getLectureId() );
		      System.out.print(", Ders Ýsmi : " + record.getLectureName() );
		      System.out.println(", Öðretmen Ýsmi : " + record.getLectureInstructure() );
		   }
	   }
	   
	   public void getStudent(int id){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   System.out.println(id+" numaralý öðrenci araþtýrýlýyor-----" );
		   try{
			   Student student = projeJDBCTemplate.getStudent(id);
			   System.out.print("Öðrenci Numarasý: " + student.getStudentId() );
			   System.out.println(", Ýsim : " + student.getStudentName() );
		   }catch(NullPointerException e){
			   System.out.println("Bu numara hiçbir öðrenciye ait deðildir.");
		   }
	   }
	   
	   public void getLecture(int id){
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   System.out.println(id+" kodlu ders araþtýrýlýyor-----");
		   try{
			   Lecture lecture = projeJDBCTemplate.getLecture(id);
			   System.out.print("Ders Kodu : " + lecture.getLectureId() );
			   System.out.print(", Ýsim : " + lecture.getLectureName() );
			   System.out.println(", Öðretmen Ýsmi : " + lecture.getLectureInstructure() );
		   }catch(NullPointerException e){
			   System.out.println("Bu kod hiçbir derse ait deðildir.");
		   }
	   }
	   
	   public void listLecturesDetail(int studentId){ //Öðrencinin Ders Bilgisi
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   
		   Functions function = new Functions();
		   System.out.println("Öðrenci Ýsmi : " + function.getStudentName(studentId));
		   
		   System.out.println("Öðrencinin dersleri getiriliyor.");
		   List<Relation> relations = projeJDBCTemplate.listLecturesDetail(studentId);
		   for (Relation record : relations) {
		      System.out.println("Ders Kodu : " + record.getLectureId() );     
		      System.out.println("Ders Ýsmi : " + function.getLectureName(record.getLectureId()));
		   }
	   }
	   
	   
	   public void listStudentsDetail(int lectureId){ //Dersi Alan Öðrenci Bilgileri
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		   ProjeJDBCTemplate projeJDBCTemplate = (ProjeJDBCTemplate)context.getBean("projeJDBCTemplate");
		   Functions function = new Functions();	  
		   System.out.println("Ders Ýsmi : " + function.getLectureName(lectureId));
		   
		   System.out.println("Öðrencinin dersleri getiriliyor.");
		   List<Relation> relations = projeJDBCTemplate.listStudentsDetail(lectureId);
		   for (Relation record : relations) {
			      System.out.println("Öðrenci Numarasý : " + record.getStudentId() );
			      System.out.println("Öðrenci Ýsmi : " + function.getStudentName(record.getStudentId()));
		   }
	   }
}
