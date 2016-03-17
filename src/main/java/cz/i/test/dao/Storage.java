package cz.i.test.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import cz.i.test.entity.Person;

/**
 * @author jan.hadas@i.cz
 * @author David Matějček
 */
public class Storage {

  private final File directory;


  public Storage(final File rootDir) {
    this.directory = rootDir;
  }


  /**
   * Creates empty directory for storage (or does nothing if the directory already exists)
   */
  public void checkStorage() {
    directory.mkdir();
  }


  public void write(final Person person) throws IOException {
    final ObjectOutput stream = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(createPath(person.getId()))));
    try {
      stream.writeObject(person);
    } finally {
      stream.close();
    }
  }


  public void delete(final Long id) {
    final File file = createPath(id);
    if (file.exists()) {
      file.delete();
    }
  }


  public Person read(final Long id) throws IOException, ClassNotFoundException {
    final File file = createPath(id);
    if (!file.exists()) {
      return null;
    }

    final ObjectInput stream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
    try {
      return (Person) stream.readObject();
    } finally {
      stream.close();
    }
  }


  private File createPath(final Long id) {
    return new File(directory, String.valueOf(id));
  }
}
