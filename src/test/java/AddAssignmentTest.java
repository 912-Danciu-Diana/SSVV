import domain.Tema;
import org.junit.*;
import repository.TemaXMLRepository;
import validation.TemaValidator;

public class AddAssignmentTest {

    private TemaXMLRepository trepo;
    private TemaValidator tval;

    @Before
    public void setup() {
        tval = new TemaValidator();
        trepo = new TemaXMLRepository(tval, "teme.xml");
    }

    // EC 1: id != null
    @Test
    public void testAddAssignmentWithNullId() {
        Tema tema = new Tema(null, "Descriere", 5, 3);
        Assert.assertNull(trepo.save(tema));
    }

    // EC 2: id != ""
    @Test
    public void testAddAssignmentWithEmptyId() {
        Tema tema = new Tema(null, "Descriere", 5, 3);
        Assert.assertNull(trepo.save(tema));
    }

    // EC 3: descriere != null
    @Test
    public void testAddAssignmentWithNullDescription() {
        Tema tema = new Tema("1", null, 5, 3);
        trepo.save(tema);
    }

    // EC 4: descriere != ""
    @Test
    public void testAddAssignmentWithEmptyDescription() {
        Tema tema = new Tema("1", "", 5, 3);
        Assert.assertNull(trepo.save(tema));
    }

    // EC 5: 1 <= deadline <= 14
    @Test
    public void testAddAssignmentWithInvalidDeadline() {
        Tema tema = new Tema("1", "Descriere", 0, 3);
        Assert.assertNull(trepo.save(tema));
    }

    // EC 6: 1 <= startline <= 14
    @Test
    public void testAddAssignmentWithInvalidStartline() {
        Tema tema = new Tema("1", "Descriere", 5, 15);
        Assert.assertNull(trepo.save(tema));
    }

    // EC 7: deadline < startline
    @Test
    public void testAddAssignmentWithDeadlineBeforeStartline() {
        Tema tema = new Tema("1", "Descriere", 3, 5);
        Assert.assertNull(trepo.save(tema));
    }

    // EC 8: Valid input
    @Test
    public void testAddAssignmentValid() {
        Tema tema = new Tema("1", "Descriere Valida", 5, 3);
        Assert.assertNotNull(trepo.findOne("1"));
    }
}