create table faculty(
    id bigserial primary key ,
    name varchar(100)
);
create table department(
   id bigserial primary key,
   name varchar(100)
);
create table specialty(
  id bigserial primary key,
  name varchar(100),
  degree varchar(100),
  edu_form varchar(100)
);
create table "group"(
    id bigserial primary key ,
    name varchar(50),
    specialty_id bigint references specialty(id)
);



create table specialty_admission(
    id bigserial primary key,
    specialty_id bigint references specialty(id),
    faculty_id bigint references faculty(id),
    department_id bigint references department(id),
    created_date timestamp,
    start_date timestamp,
    end_date timestamp,
    group_capacity int,
    min_score int,
    group_amount int
);
create table applicant_application_status(
    id bigserial primary key,
    name varchar(50)
);
create table  applicant_application(
    id bigserial primary key ,
    applicant_application_status_id bigint references applicant_application_status(id),
    application_date timestamp,
    test_score int,
    specialty_admission_id bigint references specialty_admission(id),
    specialty_id bigint references specialty(id),
    faculty_id bigint references faculty(id),
    department_id bigint references department(id),
    personal_number bigint,
    email varchar(100),
    nationality varchar(50),
    telegram_account varchar(50),
    passport_id bigint,
    country varchar(50),
    city varchar(50),
    date_of_birth timestamp,
    first_name varchar(50),
    last_name varchar(50),
    middle_name varchar(50)
);
create table verification_code(
    id bigserial primary key ,
    code int,
    expire_date timestamp,
    created_date timestamp,
    owner_personal_number bigint
);




create table candidate(
    id bigserial primary key,
    applicant_application_id bigint references applicant_application(id),
    test_score int,
    department_id bigint,
    specialty_admission_id bigint references specialty_admission(id)
);
create table person_data(
    id bigserial primary key ,
    first_name varchar(50),
    middle_name varchar(50),
    last_name varchar(50),
    date_of_birth timestamp,
    city varchar(50),
    country varchar(50),
    passport_id bigint,
    nationality varchar(50),
    email varchar(100),
    personal_number bigint
);
create table user_role(
    id bigserial primary key ,
    name varchar(50)
);

create table users(
    id bigserial primary key ,
    user_role_id bigint references user_role(id),
    person_data_id bigint references person_data(id),
    password varchar(50),
    login varchar(50)
);

create table employee_role(
    id bigserial primary key ,
    name varchar(50)
);



create table employee(
    id bigserial primary key ,
    user_id bigint references users(id),
    retire_date timestamp,
    isWorking boolean,
    take_date timestamp,
    person_data_id bigint references person_data(id),
    employee_role_id bigint references employee_role(id)
);




create table student(
    id bigserial primary key ,
    personal_data_id bigint references person_data(id),
    applicant_id bigint references applicant_application(id),
    group_id bigint references "group"(id),
    user_id bigint references users(id),
    is_studying bool not null,
    is_expelled bool not null,
    test_score int,
    enrollment_date timestamp,
    expulsion_date timestamp
);
create table student_order(
    id bigserial primary key ,
    student_id bigint references student(id),
    text varchar,
    date timestamp
);

