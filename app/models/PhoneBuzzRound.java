package models;

import play.data.validation.Constraints;
import javax.persistence.Entity;

@Entity 
public class PhoneBuzzRound extends BaseModel {

  private static final long serialVersionUID = 1L;

  @Constraints.Required
  public String phoneNumber;

  @Constraints.Required
  public int secondsDelayed;

  @Constraints.Required
  public int inputNumber;
  
}
