1.
(1)
select count(p.MSISDN) from PAGEVISIT p left join USER_INFO u on p.MSISDN=u.MSISDN  where p.RECORD_DAY>20171001 and p.RECORD_DAY<20171007  and p.pv>100;
(2)
SELECT distinct (c.MSISDN) AS MSISDN
  FROM (SELECT b.MSISDN,
               (MAX(to_char(b.RECORD_DAY, 'yyyymmdd')) -
               MIN(to_char(b.RECORD_DAY, 'yyyymmdd')) + 1) AS days
          FROM (SELECT a.MSISDN,
                       a.RECORD_DAY,
                       to_number(to_char(a.RECORD_DAY, 'yyyymmdd') - rownum) AS days
                  FROM (SELECT MSISDN,
                               to_date(to_char(RECORD_DAY, 'yyyymmdd'),
                                       'yyyymmdd') AS RECORD_DAY

                          FROM PAGEVISIT
                         WHERE to_char(RECORD_DAY, 'yyyymmdd') >=
                               '20171001'
                           AND to_char(RECORD_DAY, 'yyyymmdd') <=
                               '20171007'
                         GROUP BY MSISDN, to_char(RECORD_DAY, 'yyyymmdd')
                         ORDER BY MSISDN, to_char(RECORD_DAY, 'yyyymmdd')) a) b
         GROUP BY b.MSISDN, b.days) c
 WHERE c.days > 2
 ORDER BY c.MSISDN ASC
2.
(1)
select d.dept_name,e.name,e.salary from
	 (select name, salary,DENSE_RANK() OVER(
		PARTITION BY departmentid
		ORDER BY salary DESC) as ordernum
	 from EMPLOYEE) e, DEPARTMENT d
 where d.DEPARTMENTID = e.DEPARTMENTID
 and e.ordernum <= 3
 order by d.DEPARTMENTID
