package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "PhoneBuzzRound")
public class PhoneBuzzRound {

	@Id
	private long id;

	@Column
  private String phoneNumber;

  @Column
  private long secondsDelayed;

  @Column
  private long inputNumber;
}
