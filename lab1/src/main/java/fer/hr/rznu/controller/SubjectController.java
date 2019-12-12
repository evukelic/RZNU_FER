package fer.hr.rznu.controller;

import fer.hr.rznu.domain.Subject;
import fer.hr.rznu.service.SubjectServiceImpl;
import fer.hr.rznu.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private TeacherServiceImpl teacherService;

    @GetMapping("")
    public List<Subject> getSubjects() {
        return subjectService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Subject> getSubjectById(@PathVariable("id") Long id) {
        return subjectService.getSubjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);
    }

    @PostMapping("")
    public Subject createSubject(@RequestParam Long teacherId, @RequestBody Subject subject) {
        teacherService.getTeacherById(teacherId).ifPresent(
                teacher -> {
                    subject.setTeacher(teacher);
                }
        );
        return subjectService.createSubject(subject);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable("id") Long id, @RequestParam Long teacherId, @RequestBody Subject subject) {
        subject.setId(id);
        teacherService.getTeacherById(teacherId).ifPresent(
                teacher -> {
                    subject.setTeacher(teacher);
                }
        );
        return subjectService.updateSubject(subject);
    }

}
