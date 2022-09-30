insert into users (id, username, password, active)
    values (1, 'admin', '$2a$08$.PiqvCH13EZn6G4LgT0QAOw9/RGcB.clnpsvpfdLpwWztO1qoO7ja', true);

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');
