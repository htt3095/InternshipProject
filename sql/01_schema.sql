CREATE DATABASE InternshipDB;
GO

USE InternshipDB;
GO

CREATE TABLE roles (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT PK_user_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT FK_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE projects (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE,
    description VARCHAR(500)
);

CREATE TABLE project_members (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    project_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    active BIT NOT NULL DEFAULT 1,
    CONSTRAINT UQ_project_member UNIQUE(project_id, user_id),
    CONSTRAINT FK_project_members_project FOREIGN KEY (project_id) REFERENCES projects(id),
    CONSTRAINT FK_project_members_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE tasks (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    status VARCHAR(30) NOT NULL,
    deadline DATE NOT NULL,
    project_id BIGINT NOT NULL,
    assignee_id BIGINT NULL,
    CONSTRAINT FK_tasks_project FOREIGN KEY (project_id) REFERENCES projects(id),
    CONSTRAINT FK_tasks_assignee FOREIGN KEY (assignee_id) REFERENCES users(id),
    CONSTRAINT CK_tasks_status CHECK (status IN ('TODO', 'IN_PROGRESS', 'DONE')),
    CONSTRAINT CK_deadline CHECK (deadline >= CAST(GETDATE() AS DATE))
);

CREATE INDEX IX_tasks_project_id ON tasks(project_id);
CREATE INDEX IX_tasks_assignee_id ON tasks(assignee_id);
CREATE INDEX IX_tasks_status ON tasks(status);
GO
