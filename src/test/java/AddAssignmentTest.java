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
}
