INSERT INTO teacher(name) VALUES('John Doe');
INSERT INTO teacher(name) VALUES('Jane Doe');
INSERT INTO teacher(name) VALUES('Hans Schmidt');

INSERT INTO subject(name, teacher_id) VALUES('Service Oriented Computing', 1);
INSERT INTO subject(name, teacher_id) VALUES('Machine Learning', 1);
INSERT INTO subject(name, teacher_id) VALUES('Artificial Intelligence', 2);
INSERT INTO subject(name, teacher_id) VALUES('Physics', 3);

COMMIT;