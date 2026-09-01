CREATE OR ALTER PROCEDURE ARTICULOS_ALMACEN (PRECIO_LISTA_ID INTEGER, V_ALMACEN_ID INTEGER, V_INTEG_IN CHAR(1), V_INCL_IMPTO CHAR(1), V_CAMPO_BUSQUEDA VARCHAR(6), V_COINCIDIR_MAY_MIN CHAR(1), V_CUALQUIER_POS CHAR(1), V_TEXTO_BUSQUEDA VARCHAR(200), V_TEXTO_CONDICION_BUSQUEDA VARCHAR(500))
RETURNS (
	ARTICULO_ID INTEGER,
	CLAVE_ARTICULO VARCHAR(20),
	NOMBRE_ARTICULO VARCHAR(200),
	UNIDAD_VENTA VARCHAR(20),
	ES_ALMACENABLE CHAR(1),
	ES_JUEGO CHAR(1),
	PRECIO NUMERIC(9,0),
	PRECIO_NETO NUMERIC(9,0),
	NOMBRE_PRECIO VARCHAR(30),
	EXISTENCIA NUMERIC(9,0),
	LOCALIZACION VARCHAR(15)
)
AS
declare variable PRECIO_EMPRESA_ID integer;
/*declare variable PRECIO_LISTA_ID integer;*/
declare variable FUE_TOPADO char(1);
declare variable PCTJE_DSCTO2 numeric(9,6);
declare variable IMPTE_DSCTO numeric(15,2);
declare variable EXIS_ACTUAL numeric(18,5);
declare variable EXIS_COMPROM numeric(18,5);
declare variable FACTOR_DSCTO double precision;
declare variable PRECIO_AUX numeric(18,6);
declare variable L_DSCTO_VOL_APL boolean;
BEGIN
  /* Obtener la lista de artículos que cumplan con los criterios dados,
     junto con los datos necesarios para consultar Precios y Existencias.
     
     El precio del artículo se determina según la lista de precios asignada
     al cliente en la política de precios vigente, a menos que alguna política 
     de descuentos por promoción indique una lista de precios diferente para
     el artículo.
    V_ALMACEN_ID
      Id del almacén sobre el cual se obtiene la existencia disponible
      y la localización.
    V_INTEG_IN
      S/N; indica si hay integración con Inventarios.
      Si hay integración se obtiene la existencia de los artículos.
    V_INCL_IMPTO
      S/N; indica si se desea el precio con el impuesto incluido.
    V_CAMPO_BUSQUEDA
      = ('CLAVE', 'NOMBRE').
    V_COINCIDIR_MAY_MIN
      S/N; Si 'S', la búsqueda se hace 'Case Sensitive'.
    V_CUALQUIER_POS
      S/N; Si 'S', el texto de búsqueda puede estar en cualquier posición.
    V_TEXTO_BUSQUEDA
      Es el texto que deben contener los artículos en el campo de búsqueda.
    V_TEXTO_CONDICION_BUSQUEDA
      Es el texto de la condición para la búsqueda de artículos que se pondrá en la cláusula
      Where del query cuando NO se desea que este Sp la genere.
       - Esto es útil cuando la condición de la búsqueda es compleja y requiere tener
         varias subexpresiones con AND u OR (este Sp sólo genera una condición simple),
         como es el típico caso en el que se desea que el campo contenga todas las palabras
         de un conjunto dado sin importar la posición de cada una de ellas en él. En ese caso
         la condición a pasar a este Sp tendría la forma:
           NOMBRE COLLATE ES_ES_CI_AI CONTAINING 'palabra1'
           AND NOMBRE COLLATE ES_ES_CI_AI CONTAINING 'palabra2'
           AND ... etc.
      NOTA: Se asume que la condición viene bien estructurada.
  */
  
  /* Corrección:
     - Se ajusta llamada de Sp GET_DSCTO_ART para considerar la nueva variable 
       de retorno DSCTO_VOL_APL.
  */
  
  /* Obtenemos el Id del precio de lista.
     Se regresa el precio de lista si el artículo no tiene
     definido el precio solicitado.
  */
  /*SELECT PRECIO_EMPRESA_ID
  FROM PRECIOS_EMPRESA
  WHERE ID_INTERNO = 'L'
  INTO PRECIO_LISTA_ID;

  IF (PRECIO_LISTA_ID IS NULL) THEN
    EXCEPTION EX_PRECIO_LISTA_INEXIS;*/
  /* Obtenemos los artículos que cumplen con los criterios */

  FOR SELECT FIRST 25 ARTICULO_ID, CLAVE_ARTICULO, NOMBRE_ARTICULO, UNIDAD_VENTA,
             ES_ALMACENABLE, ES_JUEGO
      FROM BUSCA_ARTICULOS(:V_CAMPO_BUSQUEDA,:V_COINCIDIR_MAY_MIN,
                           :V_CUALQUIER_POS, :V_TEXTO_BUSQUEDA, :V_TEXTO_CONDICION_BUSQUEDA)
      WHERE ESTATUS <> 'B'
      INTO ARTICULO_ID, CLAVE_ARTICULO, NOMBRE_ARTICULO, UNIDAD_VENTA,
           ES_ALMACENABLE, ES_JUEGO
  DO
  BEGIN


    /* Obtener el precio del artículo si la política de dscto no maneja
       lista de precios */

      SELECT PRECIO_UNITARIO, NOMBRE_PRECIO
      FROM GET_PRECIO_ART(:ARTICULO_ID, :PRECIO_LISTA_ID,
             1, current_date, :PRECIO_LISTA_ID)
      INTO PRECIO, NOMBRE_PRECIO;


    /* Agregar el impuesto al precio si es necesario */
    IF ((V_INCL_IMPTO = 'S')) THEN
      EXECUTE PROCEDURE PRECIO_CON_IMPTO ARTICULO_ID, PRECIO, 'S', 'P', 'S'
      RETURNING_VALUES PRECIO_NETO;
    else
      PRECIO_NETO= PRECIO;


    /* Existencia */
    EXISTENCIA = 0;
    IF (V_INTEG_IN = 'S') THEN
    BEGIN
      IF (ES_ALMACENABLE = 'S') THEN
        EXECUTE PROCEDURE CALC_DISP_ARTALM ARTICULO_ID, V_ALMACEN_ID
        RETURNING_VALUES EXIS_ACTUAL, EXIS_COMPROM, EXISTENCIA;
      ELSE
      IF (ES_JUEGO = 'S') THEN
        EXECUTE PROCEDURE CALC_ENSAMMAX_ARTALM :ARTICULO_ID, :V_ALMACEN_ID
        RETURNING_VALUES EXISTENCIA;
    END /* if IntegIn = 'S' */
    LOCALIZACION = NULL;
    SELECT LOCALIZACION
    FROM NIVELES_ARTICULOS
    WHERE ARTICULO_ID = :ARTICULO_ID
          AND ALMACEN_ID = :V_ALMACEN_ID
    INTO LOCALIZACION;
    
    SUSPEND;
  END /* for de artículos */
END
