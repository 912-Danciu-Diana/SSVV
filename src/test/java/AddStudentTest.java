import domain.Student;
import org.junit.*;
import repository.StudentXMLRepository;
import validation.StudentValidator;

public class AddStudentTest {

    private StudentXMLRepository srepo;
    private StudentValidator sval;

    @Before
    public void setup() {
        sval = new StudentValidator();
        srepo = new StudentXMLRepository(sval, "studenti.xml");
    }

    // EC 1: id != null
    @Test
    public void testAddStudentWithNullId() {
        Student student = new Student(null, "Cata Afra", 500);
        Assert.assertNull(srepo.save(student));
    }

    // EC 2: id != ""
    @Test
    public void testAddStudentWithEmptyId() {
        Student student = new Student("", "Cata Afra", 500);
        Assert.assertNull(srepo.save(student));
    }

    // EC 3: nume != null
    @Test
    public void testAddStudentWithNullName() {
        Student student = new Student("1", null, 500);
        Assert.assertNull(srepo.save(student));
    }

    // EC 4: nume != ""
    @Test
    public void testAddStudentWithEmptyName() {
        Student student = new Student("1", "", 500);
        Assert.assertNull(srepo.save(student));
    }

    // EC 5: grupa > 110
    @Test
    public void testAddStudentWithInvalidGroupNumberLowerBound() {
        Student student = new Student("1", "Cata Afra", 110);
        Assert.assertNull(srepo.save(student));
    }

    // EC 6: grupa < 939
    @Test
    public void testAddStudentWithInvalidGroupNumberUpperBound() {
        Student student = new Student("1", "Cata Afra", 939);
        Assert.assertNull(srepo.save(student));
    }

    // Test with valid data
    @Test
    public void testAddStudentWithValidData() {
        Student student = new Student("1", "Cata Afra", 500);
        srepo.save(student);
        Assert.assertNotNull(srepo.findOne("1"));
    }

    @Test
    public void testAddStudentWithValidData2() {
        Student student = new Student("2", "Diana Regina Mea", 420);
        srepo.save(student);
        Assert.assertNotNull(srepo.findOne("2"));
    }

    @Test
    public void testAddStudentWithValidData3() {
        Student student = new Student("3", "Razvan Farcas", 932);
        srepo.save(student);
        Assert.assertNotNull(srepo.findOne("3"));
    }
}
