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

-- COMMENTS
INSERT INTO comment(id, comment, post_id)
VALUES (201, 'its all right, buddy, lets see...', 101);

INSERT INTO comment(id, comment, post_id)
VALUES (202, 'yo aint a viking mehhnn..', 101);

INSERT INTO comment(id, comment, post_id)
VALUES (203, 'The name is Buck! welcome to my world, now, uh Go home', 104);

INSERT INTO comment(id, comment, post_id)
VALUES (204, 'Rule number 1 - Always listen to Buck, Rule number 2 - Stay in the middle of the trail', 104);

INSERT INTO comment(id, comment, post_id)
VALUES (205, 'I woke up one morning married to a pineapple.. an ugly pineapple..', 104);

INSERT INTO comment(id, comment, post_id)
VALUES (206, 'kubectl get kelsey', 103);