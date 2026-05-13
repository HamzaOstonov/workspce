declare
  seq number;
begin
  select nvl((max(id) + 1), 1) into seq from bf_olap;

  insert into bf_olap
    (id, cube_name, catalog, title_en, title_ru, title_uz, mdx)
  values
    (seq,
     'BF_AMLCFT_CUBE',
     '/WEB-INF/queries/amlcft.xml',
     'Сводная информация по событиям ОФАК',
     'Сводная информация по событиям ОФАК',
     'Сводная информация по событиям ОФАК',
     'select NON EMPTY Crossjoin({[Measures].[OPERATION_ID]}, Union({[FACTORNAME.H_FACTORNAME].[All_FACTOR]}, [FACTORNAME.H_FACTORNAME].[All_FACTOR].Children)) ON COLUMNS,
  NON EMPTY Order({[BRANCHCODE.H_BRANCHCODE].[All_H]}, ([Measures].[OPERATION_ID], [FACTORNAME.H_FACTORNAME].[All_FACTOR]), ASC) ON ROWS
from [BF_AMLCFT_CUBE]');

  insert into bf_modules
    (id, parentid, mtype, mname, icon, extparam, name_ru, name_uz, name_en)
  values
    ((select max(s.id) + 1 from bf_modules s),
     600,
     1,
     'mondrian.zul',
     '/images/burn.png',
     '&' || 'repid=' || seq,
     'Сводная информация по событиям ОФАК',
     'Сводная информация по событиям ОФАК',
     'Сводная информация по событиям ОФАК');

  commit;
end;
