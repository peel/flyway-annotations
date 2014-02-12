create table ACCOUNT(
  ACCOUNT_NUMBER varchar(100) not null,
  ACCOUNT_TYPE varchar(100) not null,
  CURRENCY varchar(3) not null,
  OWNER_ID VARCHAR(10) not null,
  PRIMARY KEY(ACCOUNT_NUMBER)
);

