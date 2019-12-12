package fer.hr.rznu.controller;

import fer.hr.rznu.domain.Subject;
import fer.hr.rznu.domain.Teacher;
import fer.hr.rznu.service.SubjectServiceImpl;
import fer.hr.rznu.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private SubjectServiceImpl subjectService;

    @GetMapping("")
    public List<Teacher> getTeachers() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable("id") Long id) {
        return teacherService.getTeacherById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @PostMapping("")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        return teacherService.updateTeacher(teacher);
    }

    @GetMapping("/{id}/subjects")
    public List<Subject> getSubjectsForTeacher(@PathVariable Long id) {
        return subjectService.getByTeacherId(id);
    }

}
