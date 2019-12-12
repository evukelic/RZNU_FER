package fer.hr.rznu.service;

import fer.hr.rznu.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    List<Teacher> getAll();
    Optional<Teacher> getTeacherById(Long id);
    Teacher updateTeacher(Teacher teacher);
    Teacher createTeacher(Teacher teacher);
    void deleteTeacher(Long id);
}
