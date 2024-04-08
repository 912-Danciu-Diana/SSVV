import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.*;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

public class AddGradeTest {

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

    @Test
    public void testAddStudentUnit() {
        Student student = new Student("1", "Test Student", 937);
        studentRepo.save(student);
        Assert.assertNotNull(studentRepo.findOne("1"));
    }

    @Test
    public void testAddAssignmentUnit() {
        Tema tema = new Tema("1", "Test Assignment", 14, 7);
        temaRepo.save(tema);
        Assert.assertNotNull(temaRepo.findOne("1"));
    }

    @Test
    public void testAddGradeUnit() {
        studentRepo.save(new Student("1", "Test Student", 937));
        temaRepo.save(new Tema("1", "Test Assignment", 14, 7));
        Nota nota = new Nota(new Pair<>("1", "1"), 9.5, 7, "Good Job");
        notaRepo.save(nota);
        Assert.assertNotNull(notaRepo.findOne(new Pair<>("1", "1")));
    }

    @Test
    public void testBigBangIntegration() {
        testAddStudentUnit();
        testAddAssignmentUnit();
        testAddGradeUnit();
    }

    @After
    public void tearDown() {
        studentRepo.delete("1");
        temaRepo.delete("1");
        notaRepo.delete(new Pair<>("1", "1"));
    }

}