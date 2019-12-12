package fer.hr.rznu.service;

import fer.hr.rznu.domain.Subject;

import java.util.List;
import java.util.Optional;

public interface ISubjectService {
    List<Subject> getAll();
    Optional<Subject> getSubjectById(Long id);
    Subject updateSubject(Subject subject);
    Subject createSubject(Subject subject);
    void deleteSubject(Long id);
    List<Subject> getByTeacherId(Long teacherId);
}
