insert into patient (id, name, firstname, person_of_contact, referenced_by, doctor, user_id, pointX,pointY)
values (1, 'lejeune', 'joachim', 'lizen valériane', 'moi', 'Smeets Morgane',1,10.20, 2.45),
       (2, 'lizen', 'valériane', 'lejeune joachim','moi','Smeets Morgane',1,18.20, 6.45);

alter table patient p where p.id = 1