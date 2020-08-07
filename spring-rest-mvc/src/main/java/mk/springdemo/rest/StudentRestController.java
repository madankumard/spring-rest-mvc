package mk.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	List<Student> students;

	@PostConstruct
	public void loadData() {
		students = new ArrayList<>();
		students.add(new Student("John", "Doe"));
		students.add(new Student("Mary", "fly"));
		students.add(new Student("Susan", "kot"));
	}

	@GetMapping("/students")
	public List<Student> getStudents() {

		return students;
	}

	@GetMapping("/student/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		if (studentId >= students.size() || studentId < 0) {
			throw new StudentNotFoundException("Student Id not found - " + studentId);
		}

		return students.get(studentId);
	}

}
