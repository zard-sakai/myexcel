## mysql测试：测试表
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`
(
    `column1` int(32) NOT NULL,
    `column2` int(32) NOT NULL,
    `column3` int(32) NOT NULL,
    `column4` int(32) NOT NULL
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

## mysql测试：存储过程，造数据测试
drop procedure if exists initdata;
DELIMITER ;;
CREATE PROCEDURE initdata()
BEGIN
    DECLARE i int;
    SET i = 1;
    WHILE(i <= 10000)
        DO
            INSERT INTO test VALUES (i, i, i, i);
            SET i = i + 1;
        END WHILE;
END;;
DELIMITER ;
call initdata();
################################################################################################################################

## pg测试：测试表
DROP TABLE IF EXISTS "public"."test";
CREATE TABLE "public"."test" (
 "column1" int4 NOT NULL,
 "column2" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
 "column3" date NOT NULL,
 "column4" varchar(64) COLLATE "pg_catalog"."default" NOT NULL
)
;

## 定义pg存储过程
create or replace function initData()
returns boolean AS
$BODY$
declare i integer;
begin
    truncate table public.test;
    i:=1;
    for i in 1..10000 loop
        insert into public.test(column1,column2,column3,column4) values(66,'str','2022-01-25','str');
    end loop;
    return true;
end;
$BODY$
language plpgsql;

## 调用pg存储过程
select * from initData();

## 删除pg存储过程定义
drop FUNCTION initData();