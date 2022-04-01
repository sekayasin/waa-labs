-- USERS

INSERT INTO users (id, name)
VALUES (301, 'jonah');

INSERT INTO users (id, name)
VALUES (302, 'josh');

INSERT INTO users (id, name)
VALUES (303, 'kelsey');

-- POST

INSERT INTO post (id, author, content, title, user_id)
VALUES (101, 'Jonah Codd', 'The Lazy Dragon turned into a beast ...', 'How to train your Dragon', 301);

INSERT INTO post (id, author, content, title, user_id)
VALUES (104, 'Jonah Codd', 'The Ice Age Adventures of Buck Wild ...', 'Buck and the first rule.', 301);

INSERT INTO post (id, author, content, title, user_id)
VALUES (102, 'Josh Khan', 'Learning Java, Java API from bottom up ...', 'Learn Java in 10 minutes', 302);

INSERT INTO post (id, author, content, title, user_id)
VALUES (103, 'kelsey Tower', 'Kubernetes - the container orchestration king ...', 'K8s the Hard way', 303);