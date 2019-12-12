package fer.hr.rznu;

import fer.hr.rznu.controller.TeacherController;
import fer.hr.rznu.domain.Subject;
import fer.hr.rznu.domain.Teacher;
import fer.hr.rznu.service.SubjectServiceImpl;
import fer.hr.rznu.service.TeacherServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TeacherControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    TeacherController teacherController;

    @Mock
    TeacherServiceImpl teacherService;

    @Mock
    SubjectServiceImpl subjectService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
    }

    @Test
    public void getAllTeachers() throws Exception {
        List<Teacher> teachers = new ArrayList<>();
        when(teacherService.getAll()).thenReturn(teachers);

        mockMvc.perform(get("/api/teachers")
                .accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(0))).andReturn();
    }

    @Test
    public void getTeacherById() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("John Doe");
        teacher.setId(1L);
        when(teacherService.getTeacherById(1L)).thenReturn(Optional.of(teacher));

        mockMvc.perform(get("/api/teachers/{id}", 1L)).andReturn();
    }

    @Test
    public void getSubjectsForTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("John Doe");
        teacher.setId(1L);
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");
        subject.setTeacher(teacher);
        Subject subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("Math2");
        subject2.setTeacher(teacher);
        List<Subject> list = new ArrayList<>();
        list.add(subject);
        list.add(subject2);
        when(subjectService.getByTeacherId(1L)).thenReturn(list);

        mockMvc.perform(get("/api/teachers/{id}/subjects", 1L)).andReturn();
    }

    @Test
    public void createTeacher() throws Exception {
        mockMvc.perform(post("/api/teachers").content("{\"id\":1,\"name\":\"Jane Doe\"}")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateTeacher() throws Exception {
        mockMvc.perform(put("/api/teachers/1").content("{\"id\":1,\"name\":\"Mary Doe\"}")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void removeTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        when(teacherService.getTeacherById(1L)).thenReturn(Optional.of(teacher));
        mockMvc.perform(delete("/api/teachers/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andReturn();
    }
}
