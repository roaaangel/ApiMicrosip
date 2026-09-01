CREATE OR ALTER PROCEDURE EXISTENCIA_ARTICULO (V_ARTICULO_ID INTEGER, V_ALMACEN_ID INTEGER, V_FECHA DATE, V_ES_ULTIMO_COSTO CHAR(1))
RETURNS (
	ARTICULO_ID INTEGER,
	ALMACEN_ID INTEGER,
	EXISTENCIA NUMERIC,
	VALOR_UNITARIO NUMERIC,
	VALOR_TOTAL NUMERIC
)
AS
BEGIN
  /* Calcular la existencia y valor del artículo en un almacén o consolidado
     (pasando almacen_id = 0), a la fecha dada. Opcionalmente la existencia
     se puede valuar al último costo del artículo.
     Nota: este procedimiento se implementa como si fuera una tabla de
     1 solo registro para poder hacerle join en el programa. */
  /* Corrección:
     El costo de la ultima compra ahora se obtiene del sp Get_Ultcom_Art. 
  */
  ARTICULO_ID = V_ARTICULO_ID;
  IF (V_ALMACEN_ID = 0) THEN /* es consolidado */
    EXECUTE PROCEDURE CALCULA_EXISTENCIA_ARTICULO V_ARTICULO_ID, V_FECHA
    RETURNING_VALUES ALMACEN_ID, EXISTENCIA, VALOR_TOTAL;
  ELSE /* es un almacén específico */
    EXECUTE PROCEDURE CALC_EXIS_ARTALM V_ARTICULO_ID, V_ALMACEN_ID,
     V_FECHA
    RETURNING_VALUES EXISTENCIA, VALOR_TOTAL;
  /* Si es a último costo, obtener éste y recalcular el valor total */
  IF (V_ES_ULTIMO_COSTO = 'S') THEN
  BEGIN
    SELECT COSTO_ULTIMA_COMPRA
    FROM GET_ULTCOM_ART(:V_ARTICULO_ID)
    INTO VALOR_UNITARIO;
    SELECT PRODUCTO
    FROM ROUND_PROD(:EXISTENCIA, :VALOR_UNITARIO, 0.01)
    INTO VALOR_TOTAL;
  END /* if es a último costo */
  ELSE /* es a costo real; sólo calcular el unitario */
    EXECUTE PROCEDURE DIV_DOUBLE VALOR_TOTAL, EXISTENCIA
    RETURNING_VALUES VALOR_UNITARIO;
  SUSPEND;
END
