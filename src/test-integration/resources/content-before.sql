delete from content;

insert into content(id, content, title, user_id, uploaded_file_id, creation_date) values
(1, 'first test content', 'first test title', 1, null, null),
(2, 'second test content', 'second test title', 1, null, null),
(3, 'third test content', 'third test title', 1, null, null),
(4, 'fourth test content', 'fourth test title', 2, null, null);

alter sequence hibernate_sequence restart with 10;

