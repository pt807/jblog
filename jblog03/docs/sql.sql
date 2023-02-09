show tables;

desc user;

select * from user;

delete from user where id = 'fnvl';

select * from blog ;

insert into blog values('testId', 'testTitle', 'testurl');
update blog set title = '' and profile = '/assets/images/spring-logo.jpg' where id = 'testId';

select * from category;
insert into category values(null, 'category1', 'testId');
insert into category values(null, 'category2', 'testId');
insert into category values(null, 'category3', 'testId');
insert into category values(null, 'category4', 'testId');

select no from category where id = 'testId' order by no asc limit 0, 1;


select * from post order by category_no desc, no desc;
insert into post values(null, 'testPost1', 'testContents1', now(), 8);
insert into post values(null, 'testPost2', 'testContents2', now(), 8);
insert into post values(null, 'testPost3', 'testContents3', now(), 8);
insert into post values(null, 'testPost4', 'testContents4', now(), 8);

insert into post values(null, 'testPost1', 'testContents1', now(), 9);
insert into post values(null, 'testPost2', 'testContents2', now(), 9);
insert into post values(null, 'testPost3', 'testContents3', now(), 9);
insert into post values(null, 'testPost4', 'testContents4', now(), 9);

select no, title, contents, reg_date, category_no as categoryNo from post where category_no = 8 order by no desc;
select * from post where no = 1 and category_no = 8;

select * 
from category;

select count(*)
from post
where category_no = 8;

select a.no as no, a.name as name, count(b.no) as postCount
			from category a, post b
			where a.no = b.category_no and id = 'testId'	
			group by b.category_no;
            

select no, name, (select count(*) from post where category_no = c.no) as postCount 
from category c where id = 'testId';
            
