package cz.i.test.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import cz.i.test.dao.PersonDao;
import cz.i.test.entity.Person;

/**
 * @author jan.hadas@i.cz
 */
public class PersonService {

  private final Random random = new Random();
  private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
  private final PersonDao personDao;


  public PersonService(final PersonDao dao) {
    this.personDao = dao;
  }


  public Long createPersist(final String firstName, final String surName) throws IOException {
    final Long id = createPersist(firstName, surName, LocalDate.now());

    return null;
  }


  public Long createPersist(final String firstName, final String surName, final LocalDate birthDate)
    throws IOException {
    final Person person = createObyvatel(firstName, surName, birthDate);

    personDao.insert(person);

    return person.getId();
  }


  public void updateAddressPersist(final Long id, final String address) throws IOException, ClassNotFoundException {
    final Person person = personDao.get(id);

    person.setAddress(address);

    personDao.update(person);
  }


  public String print(final Long id) throws IOException, ClassNotFoundException {
    final Person person = personDao.get(id);
    return person.getFirstName() + " " + person.getSurName() + ", " + format.format(person.getBirthDate())
        + System.lineSeparator() + person.getAddress();
  }


  private Person createObyvatel(final String firstName, final String surName, final LocalDate birthDate) {
    final Person person = new Person();
    person.setId(random.nextLong());
    person.setFirstName(firstName);
    person.setSurName(surName);
    person.setBirthDate(birthDate);

    return person;
  }
}
