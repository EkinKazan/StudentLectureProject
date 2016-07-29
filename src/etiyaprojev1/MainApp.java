package etiyaprojev1;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
      Scanner input = new Scanner(System.in);
      Menu menu = new Menu();
      int selection = 0;
      boolean exit = true;

      System.out.println("Hoþgeldiniz! Aþaðýdaki Ýþlemlerden Birini Seçiniz");
      System.out.println("1) Öðrenci Ekleme ");
      System.out.println("2) Ders Ekleme");
      System.out.println("3) Öðrenciye Ders Atama");
      System.out.println("4) Öðrencileri Listeleme");
      System.out.println("5) Dersleri Listeleme");
      System.out.println("6) Numara ile öðrenci sorgulama");
      System.out.println("7) Numara ile ders sorgulama");
      System.out.println("8) Öðrencinin Ders Bilgisi");
      System.out.println("9) Dersi Alan Öðrenci Bilgileri");
      System.out.println("10) Menüyü görmek için");
      System.out.println("11) Çýkýþ");
      
      
      while(exit){
    	  System.out.println("Ýþlem numarasý giriniz");
    	  selection = input.nextInt();
    	  
          switch (selection) {
          case 1 :
        	  exit=true;
        	  menu.createStudent();
        	  break;

          case 2 :
        	  exit=true;
        	  menu.createLecture();
        	  break;
        	  
          case 3 :
        	  exit = true;
        	  menu.assignLecture();
        	  break;

          case 4 :
        	  exit=true;
        	  menu.listOfStudents();
        	  break;

          case 5 :
        	  exit=true;
        	  menu.listofLectures();
        	  break;
            
          case 6 :
        	  exit=true;
        	  int StudentId = 0;
        	  System.out.println("Öðrenci numarasý giriniz");
        	  StudentId = input.nextInt();
        	  menu.getStudent(StudentId);
        	  break;
            
          case 7 :
        	  exit=true;
        	  int LectureId = 0;
        	  System.out.println("Ders kodu giriniz");
        	  LectureId = input.nextInt();
        	  menu.getLecture(LectureId);
        	  break;
        	  
          case 8 :
        	  exit=true;
        	  int Student_Id = 0;
        	  System.out.println("Derslerini öðrenmek istediðiniz öðrenci numarasýný giriniz");
        	  Student_Id = input.nextInt();
        	  menu.listLecturesDetail(Student_Id);
        	  break;
        	  
          case 9 :
        	  exit=true;
        	  int Lecture_Id = 0;
        	  System.out.println("Öðrencilerini öðrenmek istediðiniz ders kodu giriniz");
        	  Lecture_Id = input.nextInt();
        	  menu.listStudentsDetail(Lecture_Id);
        	  break;
        	  
          case 10 :
        	  exit = false;
              System.out.println("1) Öðrenci Ekleme ");
              System.out.println("2) Ders Ekleme");
              System.out.println("3) Öðrencileri Listeleme");
              System.out.println("4) Dersleri Listeleme");
              System.out.println("5) Numara ile öðrenci sorgulama");
              System.out.println("6) Numara ile ders sorgulama");
              System.out.println("7) Menüyü görmek için");
              System.out.println("8) Çýkýþ");
              System.out.println("Ýþlem numarasý giriniz");
        	  selection = input.nextInt();
        	  break;
        	  
          case 11 :
        	  exit = false;
        	  System.out.println("Hoçakalýn!");
        	  break;
            
          default :
        	  exit=true;
        	  System.out.println("Geçersiz bir numara girdiniz!");
        	  break;
          }
      }
      input.close();
   } 
}