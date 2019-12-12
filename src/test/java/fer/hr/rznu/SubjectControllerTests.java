package fer.hr.rznu;

import fer.hr.rznu.controller.SubjectController;
import fer.hr.rznu.domain.Subject;
import fer.hr.rznu.service.SubjectServiceImpl;
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
public class SubjectControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    SubjectController subjectController;

    @Mock
    SubjectServiceImpl subjectService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).build();
    }

    @Test
    public void getAllSubjects() throws Exception {
        List<Subject> subjects = new ArrayList<>();
        when(subjectService.getAll()).thenReturn(subjects);

        mockMvc.perform(get("/api/subjects")
                .accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(0))).andReturn();
    }

    @Test
    public void getSubjectById() throws Exception {
        Subject subject = new Subject();
        subject.setName("Object oriented programming");
        subject.setId(1L);
        when(subjectService.getSubjectById(1L)).thenReturn(Optional.of(subject));

        mockMvc.perform(get("/api/subjects/{id}", 1L)).andReturn();
    }

    @Test
    public void createSubject() throws Exception {
        mockMvc.perform(post("/api/subjects").content("{\"id\":1,\"name\":\"Math\"}")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateSubject() throws Exception {
        mockMvc.perform(put("/api/subjects/1").content("{\"id\":1,\"name\":\"Physics\"}")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void removeSubject() throws Exception {
        Subject subject = new Subject();
        subject.setId(1L);
        when(subjectService.getSubjectById(1L)).thenReturn(Optional.of(subject));
        mockMvc.perform(delete("/api/subjects/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andReturn();
    }
}
