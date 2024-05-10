--Убрал связь фальтетов и кафедр с employee

-- alter table faculty
--     drop constraint faculty_dean_employee_id_fkey;
--
-- alter table department
--     drop constraint department_head_employee_id_fkey;

--Скрипт закоментировал по причине того что faculty_dean_employee_id_fkey
-- и department_head_employee_id_fkey стали подсвечиваться красным