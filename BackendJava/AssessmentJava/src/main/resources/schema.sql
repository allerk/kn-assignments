DROP TABLE IF EXISTS quiz CASCADE;
DROP SEQUENCE IF EXISTS quiz_sequence;
DROP TABLE IF EXISTS questions CASCADE;
DROP SEQUENCE IF EXISTS questions_sequence;
DROP TABLE IF EXISTS responses;
DROP SEQUENCE IF EXISTS responses_sequence;

CREATE SEQUENCE quiz_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

CREATE SEQUENCE questions_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

CREATE SEQUENCE responses_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

CREATE TABLE quiz
(
    id bigint NOT NULL PRIMARY KEY DEFAULT nextval('quiz_sequence'),
    name character varying(256) NOT NULL
);

CREATE TABLE questions
(
    id bigint NOT NULL PRIMARY KEY DEFAULT nextval('questions_sequence'),
    difficultyRank bigint NOT NULL,
    topic character varying(128) NOT NULL,
    content character varying(256) NOT NULL,
    isManyCorrectResponsesAllowed BOOLEAN NOT NULL,
    quiz_id bigint NOT NULL
);

CREATE TABLE responses
(
    id bigint NOT NULL PRIMARY KEY DEFAULT nextval('responses_sequence'),
    text character varying(256) NOT NULL,
    isCorrect BOOLEAN NOT NULL,
    question_id bigint NOT NULL
);

ALTER TABLE ONLY responses
    ADD CONSTRAINT fk_response_question_id FOREIGN KEY (question_id) REFERENCES questions (id) ON DELETE CASCADE;

ALTER TABLE ONLY questions
    ADD CONSTRAINT fk_question_quiz_id FOREIGN KEY (quiz_id) REFERENCES quiz (id) ON DELETE CASCADE;