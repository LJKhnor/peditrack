insert into "person" (id, name, first_name)
values (1, 'lejeune', 'joachim'),
       (2, 'lizen', 'valériane'),
       (3, 'smeets', 'morgane');

insert into patient (id, name, first_name, person_of_contact_id, referenced_by_id, doctor_id)
values (1, 'lejeune', 'joachim', 2, 2, 3),
       (2, 'lizen', 'valériane', 2,2,3);