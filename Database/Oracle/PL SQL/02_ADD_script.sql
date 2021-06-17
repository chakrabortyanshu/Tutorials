-- Author AUTHOR

declare
var_sty_id INTEGER;
var_act_id INTEGER;

begin

select id INTO var_sty_id from table where sty_name like 'ABCD';-- 21.

insert into table (col_1,col_2,col_3,col_4)values ('VAL_1','VAL_2',var_sty_id,current_timestamp)
RETURNING ID INTO var_act_id;

end;
/
