package cz.i.test.dao;

import java.io.IOException;

import cz.i.test.entity.Person;

/**
 * @author jan.hadas@i.cz
 * @author David Matějček
 */
public class PersonDao {

  private final Storage storage;


  public PersonDao(final Storage storage) {
    this.storage = storage;
  }


  public void insert(Person person) throws IOException {
    checkId(person);
    storage.checkStorage();
    storage.write(person);
  }


  public void update(Person person) throws IOException {
    checkId(person);
    storage.write(person);
  }


  public void delete(Person person) {
    checkId(person);
    storage.delete(person.getId());
  }


  public Person search(String firstName, String lastName) {
    throw new IllegalStateException("Unimplemented");
  }


  public Person get(Long id) throws IOException, ClassNotFoundException {
    return storage.read(id);
  }


  private void checkId(Person person) {
    if (person.getId() == null) {
      throw new IllegalArgumentException("Person id is null");
    }
  }
}
