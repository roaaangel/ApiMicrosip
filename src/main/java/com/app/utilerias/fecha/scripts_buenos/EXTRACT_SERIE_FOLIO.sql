create or alter procedure extract_serie_folio (
    folio varchar(9))
returns (
    serie varchar(9),
    consecutivo varchar(9),
    consecutivosinceros integer,
    folioconceros varchar(9),
    foliosinceros varchar(9))
as
declare variable s integer;
declare variable c integer;
begin
  serie = '';
  s = 1;
  while (s <= char_length(:folio)) do
  begin
    if (substring(:folio from s for 1) similar to '[ABCDEFGHIJKLMNOPQRSTUVWXYZ]') then
      serie = :serie || (substring(:folio from s for 1));
    s = :s
 + 1;
  end
  consecutivo = '';
  c = 1;
  while (c <= char_length(:folio)) do

  begin
    if (substring(:folio from c for 1) similar to '[0123456789]') then
      consecutivo = :consecutivo || (substring(:folio from c for 1));
    c = :c + 1;
  end
  folioconceros = serie || lpad(consecutivo, 9 - char_length(serie) , '0');
  consecutivosinceros = cast(consecutivo as integer);
  foliosinceros = serie || consecutivosinceros;
  suspend;
end
