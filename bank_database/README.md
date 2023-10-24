# Income-Expense Tracker Database Documentation

## Overview:
The Income-Expense Tracker Database is designed to support a web-based application for tracking user incomes and expenses. This documentation describes the tables, their columns, and their relationships.

## Tables:

### 1. `user_account`:
This table holds information about registered users.

**Columns**:
- `id`: Unique identifier for the user.
- `email`: Email address used for registration and communication.
- `password`: Hashed and salted password for user security.
- `salt`: A random string concatenated with the password before hashing for enhanced security.
- `first_name`: User's first name.
- `last_name`: User's last name.
- `created_at`: Date when the user account was created.
- `updated_at`: Date when any user's data was last updated.

**Additional Details**:
- `email` should be unique across the table to ensure that each email is associated with exactly one account.
- `password` is stored as a hash of the actual user password concatenated with the `salt` to provide an additional layer of security.
- `salt` is utilized to mitigate potential risks associated with hash attacks.
- `created_at` and `updated_at` are timestamps that allow for tracking of user account creation and the latest update times.

---

### 2. `bank_account`:
This table stores information about users' bank accounts.

**Columns**:
- `id`: Unique identifier for the bank account.
- `account_number`: Unique number associated with the bank account.
- `bank_name`: Name of the bank where the account is held.
- `account_type`: Type of account (e.g., Savings, Checking).
- `balance`: Current balance of the bank account.
- `status`: Current status of the account (e.g., active, inactive).
- `created_at`: Date when the bank account was added.
- `updated_at`: Date when any bank account data was last updated.

---

### 3. `user_bank_account_link`:
This table establishes a many-to-many relationship between users and bank accounts.

**Columns**:
- `link_id`: Unique identifier for the user bank account link
- `user_id`: Foreign key referencing the `user_account` table.
- `bank_account_id`: Foreign key referencing the `bank_account` table.
- `account_alias`: An optional name/alias given to the linked bank account.
- `date_linked`: Date when the user linked the bank account.

---

### 4. `transactions`:
Logs of deposits or withdrawals as incomes or expenses.

**Columns**:
- `id`: Unique identifier for the transaction.
- `bank_account_id`: Foreign key to the `bank_account` table.
- `amount`: Amount involved in the transaction.
- `transaction_type`: Type of transaction - income or expense.
- `category`: Category for expenses (e.g., food, bills).
- `date`: Date of the transaction.
- `description`: Optional description for the transaction.
- `created_at`: Date and time when the transaction was added.
- `updated_at`: Date and time when the transaction data was last updated.

---

### 5. `transaction_categories`:
This table stores the different categories for transactions.

**Columns**:
- `id`: Unique identifier for the category.
- `name`: Name of the category (e.g., Groceries, Utilities).
- `created_at`: Date and time when the category was added.
- `updated_at`: Date and time when the category data was last updated.

---

### 6. `budgets_goals`:
Information about users' budget and financial goals.

**Columns**:
- `id`: Unique identifier.
- `bank_account_id`: Foreign key to the `bank_account` table.
- `amount`: Amount set for the budget or goal.
- `description`: Description or purpose of the budget/goal.
- `type`: Specifies if it's a budget or a goal.
- `end_date`: Target date to achieve the goal.
- `created_at`: Date and time when the entry was added.
- `updated_at`: Date and time when the entry was last updated.

---

### 7. `reports`:
Generated reports comparing income and expenses.

**Columns**:
- `id`: Unique identifier for the report.
- `bank_account_id`: Foreign key referencing the `bank_account` table.
- `generated_date`: Date when the report was generated.
- `type`: Type of the report.
- `file_link`: Link to access the generated report file.
- `created_at`: Date and time when the report was added.
- `updated_at`: Date and time when the report data was last updated.

---

### 8. `notifications`:
Information about alerts sent to users.

**Columns**:
- `id`: Unique identifier for the notification.
- `user_id`: Foreign key to the `user_account` table.
- `report_id`: Foreign key to the `reports` table.
- `message`: Notification message content.
- `type`: Type of notification (e.g., report available).
- `date_created`: Date when the notification was created.

---

## Relationships:

1. A user can have multiple bank accounts, and a bank account can belong to multiple users.
2. Each transaction is associated with a specific bank account and can belong to a specific category.
3. Budgets or goals are associated with a specific bank account.
4. Reports are generated for a specific bank account.
5. Notifications are tied to a specific user and, when relevant, to a specific report.

---

## Design Principles:

1. **Normalization**: The database is normalized to reduce data redundancy and improve integrity.
2. **Security**: Passwords are stored as hashed values for security.
3. **Flexibility**: The design supports extensions in the future.

---

---

## How to Create Database using commerce_bank_script.sql:

1. Open mySQL Shell
2. Connect to your user/root and enter the below commands
   1.  \sql
   2.  \connect -u root --mysql
   3.  CREATE DATABASE `[your-database-name]`;
   4.  \source `[file-path\commerce_bank_script.sql]`

