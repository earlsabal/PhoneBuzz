# --- First database schema

# --- !Ups

create table PhoneBuzzRound (
  id                        bigint not null,
  phoneNumber               varchar(255) not null,
  secondsDelayed						bigint not null,
  inputNumber						bigint not null
;

# --- !Downs

DROP TABLE PhoneBuzzRound;