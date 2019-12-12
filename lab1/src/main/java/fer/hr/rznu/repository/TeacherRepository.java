package fer.hr.rznu.repository;

import fer.hr.rznu.domain.Subject;
import fer.hr.rznu.domain.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Subject> findAllById(Long id);
}
