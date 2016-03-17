package cz.i.test.dao;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import cz.i.test.entity.Person;

public class PersonDaoTest {

  private Storage storage;
  private PersonDao dao;


  @Before
  public void init() {
    this.storage = EasyMock.createStrictMock(Storage.class);
    this.storage.checkStorage();
    EasyMock.expectLastCall().andVoid().anyTimes();
    this.dao = new PersonDao(this.storage);
  }


  /**
   * Example of a working test.
   *
   * @throws Exception
   */
  @Test
  public void writeNewPerson() throws Exception {
    this.storage.write(EasyMock.anyObject(Person.class));
    EasyMock.expectLastCall().andVoid().once();
    EasyMock.replay(this.storage);

    final Person person = new Person();
    person.setId(1L);
    this.dao.insert(person);
    EasyMock.verify(this.storage);
  }


  /**
   * Broken test.
   * 1) Why? Is broken test, mock or the dao?
   * 2) Fix it!
   *
   * @throws Exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void updateNonExistingPerson() throws Exception {
    this.storage.write(EasyMock.anyObject(Person.class));
    EasyMock.expectLastCall().andThrow(new IllegalStateException("Person has left the building.")).once();
    EasyMock.replay(this.storage);

    final Person person = new Person();
    person.setId(2L);

    this.dao.update(person);
    EasyMock.verify(this.storage);
  }


  /**
   * Broken test.
   * 1) Why? Is broken test, mock or the dao?
   * 2) Fix it!
   *
   * @throws Exception
   */
  @Test
  public void updateNonExistingPerson2() throws Exception {
    this.storage.write(EasyMock.anyObject(Person.class));
    EasyMock.expectLastCall().andThrow(new IllegalArgumentException("Person does not exist.")).once();
    EasyMock.replay(this.storage);

    final Person person = new Person();
    person.setId(2L);

    try {
      this.dao.update(person);
    } catch (final IllegalArgumentException e) {
      assertEquals("Person exists.", e.getMessage());
    }
    EasyMock.verify(this.storage);
 }


  /**
   * Broken test.
   * 1) Why? Is broken test, mock or the dao?
   * 2) Fix it!
   *
   * @throws Exception
   */
  @Test
  public void updateExistingPerson() throws Exception {
    this.storage.write(EasyMock.anyObject(Person.class));
    EasyMock.expectLastCall().andVoid().once();
    EasyMock.replay(this.storage);

    final Person person = new Person();
    this.dao.update(person);
    EasyMock.verify(this.storage);
  }


  /**
   * Broken test.
   * 1) Why? Is broken test, mock or the dao?
   * 2) Fix it!
   *
   * @throws Exception
   */
  @Test
  public void deleteExistingPerson() throws Exception {
    this.storage.write(EasyMock.anyObject(Person.class));
    EasyMock.expectLastCall().andVoid().once();
    EasyMock.replay(this.storage);

    final Person person = new Person();
    person.setId(3L);
    this.dao.delete(person);
    EasyMock.verify(this.storage);
 }


  /**
   * Broken test.
   * 1) Why? Is broken test, mock or the dao?
   * 2) Fix it!
   *
   * @throws Exception
   */
 @Test
  public void searchNonExistingPerson() throws Exception {
    final Person person = this.dao.search("Martin", null);
    assertNotNull("person not found", person);
    EasyMock.verify(this.storage);
  }
}
