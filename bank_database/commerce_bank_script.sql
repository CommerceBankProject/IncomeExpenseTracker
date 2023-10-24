CREATE TABLE user_account (
  id int PRIMARY KEY AUTO_INCREMENT,
  email varchar(255) UNIQUE NOT NULL,
  password varchar(255) NOT NULL,
  salt varchar(255),
  first_name varchar(255),
  last_name varchar(255),
  created_at datetime,
  updated_at datetime
);

CREATE TABLE bank_account (
  id int PRIMARY KEY AUTO_INCREMENT,
  account_number varchar(255) UNIQUE NOT NULL,
  bank_name varchar(255) NOT NULL,
  account_type varchar(255) NOT NULL,
  balance decimal,
  status varchar(255) NOT NULL DEFAULT "active",
  created_at datetime,
  updated_at datetime
);

CREATE TABLE user_bank_account_link (
  link_id int PRIMARY KEY AUTO_INCREMENT,
  user_id int NOT NULL,
  bank_account_id int NOT NULL,
  account_alias varchar(255),
  date_linked date,
);

CREATE TABLE transactions (
  id int PRIMARY KEY AUTO_INCREMENT,
  bank_account_id int NOT NULL,
  type varchar(255) NOT NULL,
  amount decimal NOT NULL,
  description varchar(255),
  category_id int,
  date date NOT NULL,
  recurring boolean DEFAULT false,
  created_at datetime,
  updated_at datetime
);

CREATE TABLE transaction_categories (
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) UNIQUE NOT NULL,
  created_at datetime,
  updated_at datetime
);

CREATE TABLE reports (
  id int PRIMARY KEY AUTO_INCREMENT,
  bank_account_id int NOT NULL,
  generated_date date NOT NULL,
  type varchar(255) NOT NULL,
  file_link varchar(255),
  created_at datetime,
  updated_at datetime
);

CREATE TABLE budgets_goals (
  id int PRIMARY KEY AUTO_INCREMENT,
  bank_account_id int,
  amount decimal NOT NULL,
  description varchar(255),
  type varchar(255) NOT NULL,
  end_date date,
  created_at datetime,
  updated_at datetime
);

CREATE TABLE notifications (
  id int PRIMARY KEY AUTO_INCREMENT,
  user_id int,
  report_id int,
  message varchar(255),
  type varchar(255) NOT NULL,
  date_created date NOT NULL,
  created_at datetime,
  updated_at datetime
);

ALTER TABLE user_bank_account_link ADD FOREIGN KEY (user_id) REFERENCES user_account (id);

ALTER TABLE user_bank_account_link ADD FOREIGN KEY (bank_account_id) REFERENCES bank_account (id);

ALTER TABLE transactions ADD FOREIGN KEY (bank_account_id) REFERENCES bank_account (id);

ALTER TABLE transactions ADD FOREIGN KEY (category_id) REFERENCES transaction_categories (id);

ALTER TABLE reports ADD FOREIGN KEY (bank_account_id) REFERENCES bank_account (id);

ALTER TABLE budgets_goals ADD FOREIGN KEY (bank_account_id) REFERENCES bank_account (id);

ALTER TABLE notifications ADD FOREIGN KEY (user_id) REFERENCES user_account (id);

ALTER TABLE notifications ADD FOREIGN KEY (report_id) REFERENCES reports (id);
