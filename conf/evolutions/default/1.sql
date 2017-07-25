# --- First database schema

# --- !Ups

create table PhoneBuzzRound (
  id                    INTEGER PRIMARY KEY,
  phoneNumber           VARCHAR(255) NOT NULL,
  secondsDelayed				BIGINT(20) NOT NULL,
  inputNumber						BIGINT(20) NOT NULL
);

# --- !Downs

DROP TABLE PhoneBuzzRound;