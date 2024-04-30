alter table faculty
add column dean_employee_id bigint references employee(id);

alter table department
add column head_employee_id bigint references employee(id);

alter table users
add column  isActive bool;