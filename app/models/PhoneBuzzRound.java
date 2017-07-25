package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "PhoneBuzzRound")
@Table(name="phonebuzzrounds")
public class PhoneBuzzRound {

	@Id
	@GeneratedValue
	private long id;

	@Column
  private String phoneNumber;

  @Column
  private long secondsDelayed;

  @Column
  private long inputNumber;

  public PhoneBuzzRound(String phone, long seconds, long input) {
  	this.phoneNumber = phone;
  	this.secondsDelayed = seconds;
  	this.inputNumber = input;
  }

  public String getPhone() {
  	return this.phoneNumber;
  }

  public long getDelayedSeconds() {
  	return this.secondsDelayed;
  }

  public long getInput() {
  	return this.inputNumber;
  }
}
