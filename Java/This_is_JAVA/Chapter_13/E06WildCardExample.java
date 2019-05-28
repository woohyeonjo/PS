package Chapter_13;

import java.util.Arrays;

public class E06WildCardExample {
	public static void registerCourse( E06Course<?> course ){
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	public static void registerCourseStudent( E06Course<? extends Student> course ) {
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	public static void registerCourseWorker( E06Course<? super Worker> course) {
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args){
		E06Course<Person> personCourse = new E06Course<Person>("일반인과정", 5);
			personCourse.add(new Person("일반인"));
			personCourse.add(new Worker("직장인"));
			personCourse.add(new Student("학생"));
			personCourse.add(new HighStudent("고등학생"));
		
		E06Course<Worker> workerCourse = new E06Course<Worker>("직장인 과정", 5);
			workerCourse.add(new Worker("직장인"));
		E06Course<Student> studentCourse = new E06Course<Student>("학생 과정", 5);
			studentCourse.add(new Student("학생"));
			studentCourse.add(new HighStudent("고등학생"));
		E06Course<HighStudent> highStudentCourse = new E06Course<HighStudent>("고등학생과정", 5);
			highStudentCourse.add(new HighStudent("고등학생"));
			
			registerCourse(personCourse);
			registerCourse(workerCourse);
			registerCourse(studentCourse);
			registerCourse(highStudentCourse);
			System.out.println();
			
			//registerCourseStudent(personCourse);
			//registerCourseStudent(workerCourse);
			registerCourseStudent(studentCourse);
			registerCourseStudent(highStudentCourse);
			System.out.println();
			
			registerCourseWorker(personCourse);
			registerCourseWorker(workerCourse);
			//rigisterCourseWorker(studentCourse);
			//rigisterCourseWorker(highStudentCourse);
	}
}
