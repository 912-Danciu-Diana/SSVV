import org.junit.*;
import domain.Student;
import domain.Tema;
import domain.Nota;
import domain.Pair;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import repository.NotaXMLRepository;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.NotaValidator;

public class IncrementalIntegrationTests {

    private StudentXMLRepository studentRepo;
    private TemaXMLRepository temaRepo;
    private NotaXMLRepository notaRepo;
    private StudentValidator studentValidator;
    private TemaValidator temaValidator;
    private NotaValidator notaValidator;

    @Before
    public void setup() {
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();
        studentRepo = new StudentXMLRepository(studentValidator, "studenti.xml");
        temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
        notaRepo = new NotaXMLRepository(notaValidator, "note.xml");
    }

    // Test Case 1: Test addStudent functionality alone
    @Test
    public void testAddStudentIncremental() {
        Student student = new Student("1", "Incremental Student", 500);
        studentRepo.save(student);
        Assert.assertNotNull(studentRepo.findOne("1"));
    }

    // Integration Test 2: Test addAssignment after addStudent
    @Test
    public void testAddStudentAddAssignmentIncremental() {
        testAddStudentIncremental();
        Tema tema = new Tema("1", "Incremental Assignment", 7, 3);
        temaRepo.save(tema);
        Assert.assertNotNull(temaRepo.findOne("1"));
    }

    // Integration Test 3: Test addGrade after addStudent and addAssignment
    @Test
    public void testAddStudentAddAssignmentAddGradeIncremental() {
        testAddStudentAddAssignmentIncremental();
        Nota nota = new Nota(new Pair<>("1", "1"), 10, 7, "Excellent");
        notaRepo.save(nota);
        Assert.assertNotNull(notaRepo.findOne(new Pair<>("1", "1")));
    }

    @After
    public void tearDown() {
        studentRepo.delete("1");
        temaRepo.delete("1");
        notaRepo.delete(new Pair<>("1", "1"));
    }
}
