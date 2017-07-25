# --- First database schema

# --- !Ups

CREATE TABLE phonebuzzrounds (
  id SERIAL PRIMARY KEY,
  phoneNumber VARCHAR(255) NOT NULL,
  secondsDelayed BIGINT NOT NULL,
  inputNumber BIGINT NOT NULL
);

# --- !Downs

DROP TABLE if exists phonebuzzrounds;