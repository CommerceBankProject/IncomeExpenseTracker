# Income-Expense Tracker Database Documentation

## Overview:
The Income-Expense Tracker Database is designed to support a web-based application for tracking user incomes and expenses. This documentation describes the tables, their columns, and their relationships.

---

## Tables:

### 1. `user_account`:
This table holds information about registered users.

**Columns**:
- `id`: Unique identifier for the user.
- `email`: Email address used for registration and communication.
- `hashed_password`: Encrypted password for user security.
- `full_name`: User's full name.
- `date_created`: Date when the user account was created.
- `last_updated`: Date when any user's data was last updated.

---

### 2. `bank_account`:
This table stores information about users' bank accounts.

**Columns**:
- `id`: Unique identifier for the bank account.
- `account_name`: A name or label for the bank account.
- `date_created`: Date when the bank account was added.
- `last_updated`: Date when any bank account data was last updated.

---

### 3. `user_bank_account_link`:
This table establishes a many-to-many relationship between users and bank accounts.

**Columns**:
- `user_id`: Foreign key to the `user_account` table.
- `bank_account_id`: Foreign key to the `bank_account` table.

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

### 5. `budgets_goals`:
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

### 6. `reports`:
Generated reports comparing income and expenses.

**Columns**:
- `id`: Unique identifier for the report.
- `bank_account_id`: Foreign key to the `bank_account` table.
- `content`: Content of the report.
- `date_generated`: Date when the report was generated.

---

### 7. `notifications`:
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

1. A user can have multiple bank accounts, and a bank account can belong to multiple users (`user_account` to `bank_account` is a many-to-many relationship facilitated by `user_bank_account_link`).

2. Each transaction, budget/goal, and report is associated with a specific bank account.

3. Notifications are tied to a specific user and, when relevant, to a specific report.

---

## Design Principles:

1. **Normalization**: The database is normalized up to Boyce Codd's Normal Form (BCNF) to reduce data redundancy and improve integrity.

2. **Security**: Passwords are stored as hashed values for security. 

3. **Flexibility**: The design supports extensions like multi-currency, integration with banks, or introduction of other financial tracking features in the future.
