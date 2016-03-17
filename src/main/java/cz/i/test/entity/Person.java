package cz.i.test.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jan.hadas@i.cz
 */
public class Person implements Serializable {

  private static final long serialVersionUID = 8982553440436559783L;

  private Long id;
  private String firstName;
  private String surName;
  private LocalDate birthDate;
  private String address;


  public Long getId() {
    return id;
  }


  public void setId(final Long id) {
    this.id = id;
  }


  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }


  public String getSurName() {
    return surName;
  }


  public void setSurName(final String surName) {
    this.surName = surName;
  }


  public LocalDate getBirthDate() {
    return birthDate;
  }


  public void setBirthDate(final LocalDate birthDate) {
    this.birthDate = birthDate;
  }


  public String getAddress() {
    return address;
  }


  public void setAddress(final String address) {
    this.address = address;
  }


  @Override
  public String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("id", id).append("firstName", firstName).append("surName", surName).append("birthDate", birthDate)
        .append("address", address);
    return builder.toString();
  }
}
