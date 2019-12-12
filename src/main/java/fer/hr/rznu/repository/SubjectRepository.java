package fer.hr.rznu.repository;

import fer.hr.rznu.domain.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    List<Subject> findByTeacherId(Long teacherId);
}
