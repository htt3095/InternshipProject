USE InternshipDB;
GO

INSERT INTO roles(name) VALUES ('USER'), ('MANAGER');

INSERT INTO users(username, email, password) VALUES
('manager1', 'manager1@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA1111111111111111111111'),
('user01', 'user01@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA2222222222222222222222'),
('user02', 'user02@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA3333333333333333333333'),
('user03', 'user03@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA4444444444444444444444'),
('user04', 'user04@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA5555555555555555555555'),
('user05', 'user05@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA6666666666666666666666'),
('user06', 'user06@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA7777777777777777777777'),
('user07', 'user07@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA8888888888888888888888'),
('user08', 'user08@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedA9999999999999999999999'),
('user09', 'user09@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedB1111111111111111111111'),
('user10', 'user10@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedB2222222222222222222222'),
('user11', 'user11@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedB3333333333333333333333'),
('user12', 'user12@mail.com', '$2a$10$hYfQCFakeHashOnlyForSeedB4444444444444444444444');

INSERT INTO user_roles(user_id, role_id) VALUES
(1, 2),
(2, 1),(3, 1),(4, 1),(5, 1),(6, 1),(7, 1),(8, 1),(9, 1),(10, 1),(11, 1),(12, 1),(13, 1);

INSERT INTO projects(name, description) VALUES
('Project Alpha', 'Internal management app'),
('Project Beta', 'Customer portal'),
('Project Gamma', 'Reporting module');

INSERT INTO project_members(project_id, user_id, active) VALUES
(1, 1, 1),(1, 2, 1),(1, 3, 1),(1, 4, 1),(1, 5, 1),
(2, 1, 1),(2, 6, 1),(2, 7, 1),(2, 8, 1),(2, 9, 1),
(3, 1, 1),(3, 10, 1),(3, 11, 1),(3, 12, 1),(3, 13, 1);

INSERT INTO tasks(title, description, status, deadline, project_id, assignee_id) VALUES
('Design DB schema', 'Create tables and constraints', 'TODO', DATEADD(day, 2, CAST(GETDATE() AS DATE)), 1, 2),
('Implement login API', 'JWT login endpoint', 'IN_PROGRESS', DATEADD(day, 3, CAST(GETDATE() AS DATE)), 1, 3),
('Write unit tests', 'TaskService tests', 'TODO', DATEADD(day, 4, CAST(GETDATE() AS DATE)), 1, 4),
('Refactor service layer', 'Naming and clean code', 'DONE', DATEADD(day, 1, CAST(GETDATE() AS DATE)), 1, 5),
('Build project API', 'CRUD project endpoints', 'TODO', DATEADD(day, 5, CAST(GETDATE() AS DATE)), 2, 6),
('Setup Swagger', 'OpenAPI documentation', 'IN_PROGRESS', DATEADD(day, 6, CAST(GETDATE() AS DATE)), 2, 7),
('Review SQL indexes', 'Performance tuning', 'TODO', DATEADD(day, 7, CAST(GETDATE() AS DATE)), 2, 8),
('Fix exception handling', 'Global error mapping', 'DONE', DATEADD(day, 2, CAST(GETDATE() AS DATE)), 2, 9),
('Add role check', 'Manager-only project creation', 'TODO', DATEADD(day, 3, CAST(GETDATE() AS DATE)), 3, 10),
('Task assign flow', 'Ensure assignee in project', 'IN_PROGRESS', DATEADD(day, 4, CAST(GETDATE() AS DATE)), 3, 11),
('Task status rule', 'Block update if DONE', 'TODO', DATEADD(day, 5, CAST(GETDATE() AS DATE)), 3, 12),
('Prepare README', 'Setup + run instructions', 'TODO', DATEADD(day, 6, CAST(GETDATE() AS DATE)), 3, 13),
('Add validation', 'NotBlank and Size', 'TODO', DATEADD(day, 7, CAST(GETDATE() AS DATE)), 1, 2),
('Insert seed users', 'DB test data', 'DONE', DATEADD(day, 1, CAST(GETDATE() AS DATE)), 1, 3),
('Query by project', 'Task list by project', 'TODO', DATEADD(day, 2, CAST(GETDATE() AS DATE)), 1, 4),
('Query by user', 'Task list by assignee', 'TODO', DATEADD(day, 3, CAST(GETDATE() AS DATE)), 1, 5),
('Query by status', 'Task list by status', 'TODO', DATEADD(day, 4, CAST(GETDATE() AS DATE)), 2, 6),
('Project member API', 'Add member endpoint', 'TODO', DATEADD(day, 5, CAST(GETDATE() AS DATE)), 2, 7),
('Security filter', 'JWT filter chain', 'DONE', DATEADD(day, 1, CAST(GETDATE() AS DATE)), 2, 8),
('Postman test run', 'End-to-end API test', 'TODO', DATEADD(day, 6, CAST(GETDATE() AS DATE)), 3, 9);
GO
