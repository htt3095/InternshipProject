USE InternshipDB;
GO

-- Task theo user
SELECT t.*
FROM tasks t
WHERE t.assignee_id = 2;

-- Task theo project
SELECT t.*
FROM tasks t
WHERE t.project_id = 1;

-- Task theo status
SELECT t.*
FROM tasks t
WHERE t.status = 'IN_PROGRESS';

-- Query join review
SELECT t.id, t.title, t.status, p.name AS project_name, u.username AS assignee
FROM tasks t
JOIN projects p ON p.id = t.project_id
LEFT JOIN users u ON u.id = t.assignee_id
WHERE t.status IN ('TODO', 'IN_PROGRESS')
ORDER BY p.name, t.deadline;
GO
