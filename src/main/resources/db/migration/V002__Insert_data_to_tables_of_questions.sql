INSERT INTO difficulties (threshold) VALUES (0.8);

INSERT INTO posts (name, description, diff_id) VALUES ('Пост1', 'Описание Пост1', 1);
INSERT INTO posts (name, description, diff_id) VALUES ('Пост2', 'Описание Пост2', 1);

INSERT INTO test_questions (text,
    answer_option1, answer_accuracy1, answer_option2, answer_accuracy2,
    answer_option3, answer_accuracy3, answer_option4, answer_accuracy4)
VALUES ('Вопрос1',
    'Вариант1', true, 'Вариант2', false, 'Вариант3', false, 'Вариант4', false);
INSERT INTO test_questions (text,
    answer_option1, answer_accuracy1, answer_option2, answer_accuracy2,
    answer_option3, answer_accuracy3, answer_option4, answer_accuracy4)
VALUES ('Вопрос2',
    'Вариант1', true, 'Вариант2', false, 'Вариант3', false, 'Вариант4', false);
INSERT INTO test_questions (text,
    answer_option1, answer_accuracy1, answer_option2, answer_accuracy2,
    answer_option3, answer_accuracy3, answer_option4, answer_accuracy4)
VALUES ('Вопрос3',
    'Вариант1', true, 'Вариант2', false, 'Вариант3', false, 'Вариант4', false);
INSERT INTO test_questions (text,
    answer_option1, answer_accuracy1, answer_option2, answer_accuracy2,
    answer_option3, answer_accuracy3, answer_option4, answer_accuracy4)
VALUES ('Вопрос4',
    'Вариант1', true, 'Вариант2', false, 'Вариант3', false, 'Вариант4', false);
INSERT INTO test_questions (text,
    answer_option1, answer_accuracy1, answer_option2, answer_accuracy2,
    answer_option3, answer_accuracy3, answer_option4, answer_accuracy4)
VALUES ('Вопрос5',
    'Вариант1', true, 'Вариант2', false, 'Вариант3', false, 'Вариант4', false);


INSERT INTO post_question_map (post_id, question_id) VALUES (1, 1);
INSERT INTO post_question_map (post_id, question_id) VALUES (1, 2);
INSERT INTO post_question_map (post_id, question_id) VALUES (1, 3);
INSERT INTO post_question_map (post_id, question_id) VALUES (1, 4);
INSERT INTO post_question_map (post_id, question_id) VALUES (2, 5);
INSERT INTO post_question_map (post_id, question_id) VALUES (2, 4);
INSERT INTO post_question_map (post_id, question_id) VALUES (2, 3);
INSERT INTO post_question_map (post_id, question_id) VALUES (2, 2);