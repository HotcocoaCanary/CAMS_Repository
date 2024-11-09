insert into users
values ('admin', '123456', 'admin', 'admin', 'ADMIN'),
       ('T001', 'T001', '陈', '女', 'TEACHER'),
       ('T002', 'T002', '乌', '男', 'TEACHER');

insert into teachers
values ('T001', '计算机与信息工程学院'),
       ('T002', '计算机与信息工程学院');

insert into classes
values ('22软工1', '2022', '计算机与信息工程学院', 'T001'),
       ('22软工2', '2022', '计算机与信息工程学院', 'T002')
