CREATE DATABASE school_db_s
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE public.t_schools
(
    school_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    school_name text NOT NULL,
    school_address text NOT NULL,
    PRIMARY KEY (school_id)
);

ALTER TABLE public.t_schools
    OWNER to postgres;

COMMENT ON COLUMN public.t_schools.school_name
    IS 'название школы';

COMMENT ON COLUMN public.t_schools.school_address
    IS 'адрес школы';

CREATE TABLE public.t_students
(
    student_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    student_name text NOT NULL,
    student_age integer NOT NULL,
    student_sex boolean NOT NULL,
    student_school_id bigint NOT NULL,
    PRIMARY KEY (student_id),
    CONSTRAINT fk_students_school FOREIGN KEY (student_school_id)
    REFERENCES public.t_schools (school_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
);

ALTER TABLE public.t_students
    OWNER to postgres;

COMMENT ON COLUMN public.t_students.student_name
    IS 'ФИО ученика';

COMMENT ON COLUMN public.t_students.student_age
    IS 'возраст';

COMMENT ON COLUMN public.t_students.student_sex
    IS 'пол';

CREATE TABLE public.t_teachers
(
    teacher_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    teacher_name text NOT NULL,
    teacher_age integer NOT NULL,
    teacher_sex boolean NOT NULL,
    teacher_school_id bigint NOT NULL,
    PRIMARY KEY (teacher_id),
	CONSTRAINT fk_teachers_school FOREIGN KEY (teacher_school_id)
	    REFERENCES public.t_schools (school_id) MATCH SIMPLE
	    ON UPDATE NO ACTION
	    ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.t_teachers
    OWNER to postgres;

COMMENT ON COLUMN public.t_teachers.teacher_name
    IS 'ФИО учителя';

COMMENT ON COLUMN public.t_teachers.teacher_age
    IS 'возраст учителя';

COMMENT ON COLUMN public.t_teachers.teacher_sex
    IS 'пол';

CREATE TABLE public.t_subjects
(
    subject_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    subject_name text NOT NULL,
    PRIMARY KEY (subject_id)
);

ALTER TABLE public.t_subjects
    OWNER to postgres;

COMMENT ON COLUMN public.t_subjects.subject_name
    IS 'название предмета';

CREATE TABLE public.t_students_subjects
(
    student_id bigint NOT NULL,
    subject_id bigint NOT NULL,
    PRIMARY KEY (student_id, subject_id)
);

ALTER TABLE public.t_students_subjects
    OWNER to postgres;

ALTER TABLE public.t_students_subjects
    ADD CONSTRAINT students_fk FOREIGN KEY (student_id)
    REFERENCES public.t_students (student_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE public.t_students_subjects
    ADD CONSTRAINT subject_fk FOREIGN KEY (subject_id)
    REFERENCES public.t_subjects (subject_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

CREATE TABLE public.t_teacher_subject
(
    teacher_id bigint NOT NULL,
    subject_id bigint NOT NULL,
    PRIMARY KEY (teacher_id, subject_id)
);

ALTER TABLE public.t_teacher_subject
    OWNER to postgres;    

ALTER TABLE public.t_teacher_subject
    ADD CONSTRAINT fk_teacher FOREIGN KEY (teacher_id)
    REFERENCES public.t_teachers (teacher_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE public.t_teacher_subject
    ADD CONSTRAINT fk_subject FOREIGN KEY (subject_id)
    REFERENCES public.t_subjects (subject_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;    