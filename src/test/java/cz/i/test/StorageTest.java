package cz.i.test;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import cz.i.test.dao.Storage;
import cz.i.test.entity.Person;

/**
 * @author jan.hadas@i.cz
 * @author David Matějček
 */
public class StorageTest {

  private final File rootDir = new File("target", "data");
  private Storage storage;


  @Before
  public void cleanup() throws Exception {
    if (rootDir.exists()) {
      // rootDir.delete();
      FileUtils.deleteDirectory(rootDir);
    }
    assertFalse("Directory exists before the start! " + rootDir.getAbsolutePath(), rootDir.exists());
    this.storage = new Storage(rootDir);
  }


  /**
   * Enhance test. How do you check that the person was written?
   *
   * @throws Exception
   */
  @Test
  public void write() throws Exception {
    final Person person = new Person();
    person.setId(6840L);

    storage.checkStorage();
    assertTrue(rootDir.exists());
    storage.write(person);
  }


  /**
   * Lazy developer. What should be better?
   *
   * @throws Exception
   */
  @Test
  public void reloadPerson() throws Exception {
    final Person personOrig = new Person();
    personOrig.setId(305L);
    personOrig.setFirstName("Pink");
    personOrig.setSurName("Floyd");
    personOrig.setBirthDate(LocalDate.of(1949, 3, 21));

    storage.checkStorage();
    storage.write(personOrig);

    final Person personRead = storage.read(personOrig.getId());
    assertEquals(personOrig.getId(), personRead.getId());
    assertEquals(personOrig.getFirstName(), personRead.getFirstName());
    assertEquals(personOrig.getSurName(), personRead.getSurName());
    assertEquals(personOrig.getBirthDate(), personRead.getBirthDate());
  }


  /**
   * Fix test!
   *
   * @throws Exception
   */
  @Test
  public void deletePerson() throws Exception {
    final Person person = new Person();
    person.setId(65408L);

    storage.write(person);
    storage.delete(person.getId());

    assertNull("Person wasn't deleted", storage.read(person.getId()));
  }
}
