delimiter ;;
create procedure idata()
begin
    declare i int;
    set i = 1;
    while(i <= 1000)
        do
            insert into test values (i, i, i, i);
            set i = i + 1;
        end while;
end;;
delimiter ;
call idata();

CREATE TABLE `test` (
`column1` int(32) NOT NULL,
`column2` int(32) NOT NULL,
`column3` int(32) NOT NULL,
`column4` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1