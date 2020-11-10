CREATE TABLE public.users
(
    id uuid NOT NULL,
    first_name text NOT NULL,
    last_name text,
    birth_day date,
    sex character,
    interests text,
    city text,
    PRIMARY KEY (id)
);


CREATE TABLE public.friends
(
    "user_id" uuid NOT NULL,
    "friend_id" uuid NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY ("user_id")
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "fk_friend_id" FOREIGN KEY ("friend_id")
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

