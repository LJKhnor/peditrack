INSERT INTO public."user" (id, username, email, password, role)
VALUES(1, 'admin', 'lejeunejoachim@hotmail.com', '1234', 'USER');


insert into patient (id, name, firstname, person_of_contact, referenced_by, doctor, user_id)
values (1, 'lejeune', 'joachim', 'lizen valériane', 'moi', 'Smeets Morgane',1),
       (2, 'lizen', 'valériane', 'lejeune joachim','moi','Smeets Morgane',1);

alter sequence user_id_seq restart with 2;
alter sequence patient_id_seq restart with 3;