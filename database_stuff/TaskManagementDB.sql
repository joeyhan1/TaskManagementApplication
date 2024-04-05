-- Delete the database if it exists
DROP DATABASE IF EXISTS TaskManagementDB;
-- Create the database
CREATE DATABASE IF NOT EXISTS TaskManagementDB;

-- Use the database
USE TaskManagementDB;

-- Create the Team table
CREATE TABLE IF NOT EXISTS Team (
    TeamID INT AUTO_INCREMENT PRIMARY KEY,
    TeamName VARCHAR(100) NOT NULL
);

-- Create the User table
CREATE TABLE IF NOT EXISTS User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Role ENUM('ADMIN', 'EMPLOYEE') NOT NULL,
    TeamID INT,
    FOREIGN KEY (TeamID) REFERENCES Team(TeamID)
);

-- Create the Project table
CREATE TABLE IF NOT EXISTS Project (
    ProjectID INT AUTO_INCREMENT PRIMARY KEY,
    ProjectName VARCHAR(100) NOT NULL,
    Description TEXT,
    StartDate DATE,
    EndDate DATE,
    TeamID INT,
    FOREIGN KEY (TeamID) REFERENCES Team(TeamID)
);

-- Create the Task table
CREATE TABLE IF NOT EXISTS Task (
    TaskID INT AUTO_INCREMENT PRIMARY KEY,
    TaskName VARCHAR(100) NOT NULL,
    Description TEXT,
    Status ENUM('NOT-STARTED','IN-PROGRESS', 'COMPLETED'),
    Priority ENUM('LOW', 'MEDIUM', 'HIGH'),
    StartDate DATE,
    CompletionDate DATE,
    UserID INT,
    ProjectID INT,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (ProjectID) REFERENCES Project(ProjectID)
);

-- Create the Deadline table
CREATE TABLE IF NOT EXISTS Deadline (
    DeadlineID INT AUTO_INCREMENT PRIMARY KEY,
    DeadlineDate DATE,
    TaskID INT,
    FOREIGN KEY (TaskID) REFERENCES Task(TaskID)
);

-- Create the Progress table
CREATE TABLE IF NOT EXISTS Progress (
    ProgressID INT AUTO_INCREMENT PRIMARY KEY,
    PercentageComplete INT,
    TaskID INT,
    FOREIGN KEY (TaskID) REFERENCES Task(TaskID)
);

-- All indexes are b-trees

-- Indexes for User
CREATE INDEX idx_username ON User (Username);
CREATE INDEX idx_email ON User (Email);

-- Indexes for Task
CREATE INDEX idx_task_name ON Task (TaskName);
CREATE INDEX idx_status ON Task (Status);
CREATE INDEX idx_priority ON Task (Priority);
CREATE INDEX idx_start_date ON Task (StartDate);
CREATE INDEX idx_completion_date ON Task (CompletionDate);

-- Indexes for Project
CREATE INDEX idx_project_name ON Project (ProjectName);
CREATE INDEX idx_start_date_project ON Project (StartDate);
CREATE INDEX idx_end_date_project ON Project (EndDate);

-- Index for Team
CREATE INDEX idx_team_name ON Team (TeamName);

-- Index for Deadline
CREATE INDEX idx_deadline_date ON Deadline (DeadlineDate);

-- Index for Progress
CREATE INDEX idx_percentage_complete ON Progress (PercentageComplete);
