package models;

import io.ebean.*;
import play.data.validation.Constraints;
import javax.persistence.Entity;

@Entity 
public class PhoneBuzzRound extends BaseModel {

  private static final long serialVersionUID = 1L;

  @Constraints.Required
  public String phoneNumber;

  @Constraints.Required
  public long secondsDelayed;

  @Constraints.Required
  public long inputNumber;
  
}
