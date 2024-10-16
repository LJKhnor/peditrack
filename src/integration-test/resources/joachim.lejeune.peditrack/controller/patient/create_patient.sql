insert into patient (id, name, firstname, person_of_contact, referenced_by, doctor)
values (1, 'lejeune', 'joachim', 'lizen valériane', 'moi', 'Smeets Morgane'),
       (2, 'lizen', 'valériane', 'lejeune joachim','moi','Smeets Morgane');

alter sequence patient_id_seq restart with 3;