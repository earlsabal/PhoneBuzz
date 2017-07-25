# --- First database schema

# --- !Ups

create table PhoneBuzzRound (
  id                    INTEGER PRIMARY KEY,
  phoneNumber           VARCHAR(255) NOT NULL,
  secondsDelayed				BIGINT NOT NULL,
  inputNumber						BIGINT NOT NULL
);

# --- !Downs

DROP TABLE PhoneBuzzRound;