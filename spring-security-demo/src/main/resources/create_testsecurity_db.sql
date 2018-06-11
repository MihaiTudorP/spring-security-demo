create database `testsecurity1` default charset=utf8 collate=utf8_romanian_ci;
use testsecurity;

create table `users` (
	`username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `enabled` tinyint(1) NOT NULL,
    
    PRIMARY KEY (`username`)
) ENGINE=InnoDB default charset=utf8;

create table `authorities` (
	`username` varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    
    unique key `authorize_idx_1` (`username`,`authority`),
    
    constraint `authorities_ibfk_1`
    foreign key (`username`) references `users`(`username`)
    ) ENGINE=InnoDB default charset=utf8;

#create user `testsecurity@localhost` identified by 'testsecurity';
grant all on testsecurity.* to `testsecurity`@`%` identified by 'testsecurity';
#drop user `testsecurity@localhost`;
    
insert into `users` values ('adrian','{noop}ady123',1), ('karl','{noop}karl123',1), ('michael','{noop}mike123',1);
insert into `authorities` values ('adrian','ROLE_EMPLOYEE'), ('karl','ROLE_EMPLOYEE'), ('karl','ROLE_MANAGER'), ('michael', 'ROLE_EMPLOYEE'), ('michael', 'ROLE_ADMIN');
alter table `users` modify `password` varchar(100);
update users set `password`='{bcrypt}$2a$04$.VzcBSbm70WPeRNx0pEwCe1gGrgPqNMv84utlzU.rH5RAjcgcSPI6' where `username`='adrian';
update users set `password`='{bcrypt}$2a$04$6J3JMOXrxQ9dMB6VG/8VDunghGw0n/BvhiRGG7atJtPISUgnlWlia' where `username`='karl';
update users set `password`='{bcrypt}$2a$04$AhPD57TVp/8SuFshABldXOwNFUcEpG9xJ9Td7fWu.JEQMhp9Z0eYO' where `username`='michael';
commit;