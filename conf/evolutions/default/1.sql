# --- First database schema

# --- !Ups

create table PhoneBuzzRound (
  id                    BIGINT(20) NOT NULL AUTO_INCREMENT,
  phoneNumber           VARCHAR(255) NOT NULL,
  secondsDelayed				BIGINT(20) NOT NULL,
  inputNumber						BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE PhoneBuzzRound;