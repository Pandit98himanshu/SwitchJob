/*
 * 1741. Find Total Time Spent by Each Employee
 * https://leetcode.com/problems/find-total-time-spent-by-each-employee/
 */

select to_char(event_day, 'YYYY-MM-DD') as day, emp_id, (sum(out_time) - sum(in_time)) as total_time from Employees group by event_day, emp_id