Income Expense Tracker Project Backend Documentation

Authors: Thien Phu Le, Jasmine Thai, Dan Nguyen
Last Updated: 10/31/2023

Introduction
This documentation is a resource for understanding and working with our Income-Expense Tracker. It's designed to help users effectively navigate the project's backend and contribute to its development. The goal of our backend is to provide a user-friendly and secure experience that operates at a high level of efficiency. Our backend should be able to easily manage income and expenses through and generate insights and reports based off of data we have logged from our database.

Objectives
Primary Objectives (Functional Scope):
    User Account Management:
        Allow users to register using a valid email address.
        Allow users to login using valid registered credentials.
        Allow users to reset or recover passwords.
        Update user personal information.
    Income-Expense Tracking:
        Allow users to log deposits as income.
        Allow users to log expenses and categorize the expenses by type.
        Provide real-time updates with each input (deposit or expense).
        Provide a history/log of deposits/expenses for the user.
    Reporting:
        Generate a report showing user spending compared to income, with a visual graph representation.
        Provide an option to print the report.
        Provide an option to send the report to a valid email address.

Secondary Objectives:
    Data Analysis & Insights:
        Offer insights on spending habits over time.
        Provide recommendations for savings or financial tips based on the user's spending behavior.
    Budgeting & Goals:
        Allow users to set monthly or yearly budget limits.
        Allow users to set financial goals (e.g., saving for a vacation) and track progress.
        Send notifications to alert users when they're nearing or surpassing their budget.
    Recurring Transactions:
        Have the option to set up recurring income (e.g., monthly salary) or expenses (e.g., monthly rent or subscription services).

Non-Functional Objectives:
    Performance:
        The application will provide real-time balance calculations without significant delay.
    Security:
        User data, especially financial information, will be securely stored and encrypted.
    Usability: 
        the application will be easy to use for the general user.

Back-End Technology:
    Programming Language: Java
    Web Framework: Spring Boot and React JS
    API:
    Database: MySQL
    Git: GitHub

Code Documentation:
    UserAccountController.java:
    This Java class serves as a controller in a Spring Boot application and is responsible for managing user accounts. It handles operations related to creating, retrieving, updating, and deleting user accounts.

    AuthController.java:
    This Java class serves as a controller in a Spring Boot application responsible for user authentication and registration. The controller exposes RESTful APIs for login and registration. 

    UserAccount.java:
    This class is an entity class used in the Income-Expense Tracker application to represent user accounts. It stores all the attributes of a user's account and keeps the data organized.

    LoginDto.java:
    This class represents a Data Transfer Object (DTO) that is used for transferring a user's email and password between the client and the server

    RegisterDto.java:
    This class represents a DTO that is used to transfer user-registration related data between the client and the server. This data includes the user's firstName, lastName, email, password, and the time the account was created.

    UserController.java:
    This class is a Spring Boot controller that is responsible for handling user login requests. It calls data from the LoginDto object to authenticate if the user's email and password are valid.

    PasswordUtil.java:
    This class is a utility class that works with user passwords. It is used to hash passwords and generate salt values. This class will help protect passwords from exposure if there happens to be a data breach.

    AuthService.java:
    This class handles user authentication and registration. It provides methods for authenticating users based on their login credentials and for registering new users. 

