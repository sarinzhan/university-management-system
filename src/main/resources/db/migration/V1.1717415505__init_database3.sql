create table curriculum(
    id bigserial primary key,
    semester_number int,
    specialty_id bigint references specialty(id),
    created_date timestamp,
    name varchar(100)
);

create table semester(
    id bigserial primary key,
    group_id bigint references "group"(id),
    start_date timestamp,
    end_date timestamp,
    curriculum_id bigint references curriculum(id),
    number int
);

create table discipline(
    id bigserial primary key,
    name varchar(75)
);

create table curriculum_discipline(
    id bigserial primary key,
    curriculum_id bigint references curriculum(id),
    hour int,
    discipline_id bigint references discipline(id)
);

create table teacher(
    id bigserial primary key,
    employee_id bigint references employee(id),
    department_id bigint references department(id),
    is_active boolean,
    employment_date timestamp,
    termination_date timestamp
);

create table course(
    id bigserial primary key,
    curriculum_discipline_id bigint references curriculum_discipline(id),
    semester_id bigint references semester(id),
    group_id bigint references "group"(id),
    teacher_id bigint references teacher(id)
);

create table assignment(
    id bigserial primary key,
    course_id bigint references course(id),
    description varchar(100),
    subject varchar(50),
    max_scope int,
    deadline timestamp,
    assignment_date timestamp
);

create table report(
    id bigserial primary key,
    assignment_id bigint references assignment(id),
    link varchar(200),
    scope int,
    student_id bigint references student(id),
    assignment_date timestamp
);

create table m2m_discipline_teacher(
    discipline_id bigint references discipline(id),
    teacher_id bigint references teacher(id),
    is_active boolean,
    assigned_date timestamp,
    revoke_data timestamp
);

create table intermediate_result(
    id bigserial primary key,
    discipline_id bigint references discipline(id),
    student_id bigint references student(id),
    teacher_id bigint references teacher(id),
    grading_date timestamp,
    grade int,
    scope int,
    curriculum_id bigint references curriculum(id)
);