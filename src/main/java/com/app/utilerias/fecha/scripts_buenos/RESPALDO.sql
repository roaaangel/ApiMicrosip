create or alter procedure articulos_mobil (fechainicio date, fechafin date)
returns (
    articulo_id integer,
    nombre_articulo varchar(200),
    codigo_articulo varchar(20),
    clave_articulo varchar(20),
    factor_venta double precision,
    unidad_venta varchar(20),
    porcentaje_impuesto double precision,
    precio double precision,
    descuento_promocion double precision,
    precio_neto double precision,
    unidad_minima_venta double precision,
    piezas_x_inner double precision,
    piezas_x_master double precision,
    codigo_barras varchar(20),
    codigo_barras_inner varchar(20),
    codigo_barras_master varchar(20))
as
declare variable rol_clave_art_id integer;
declare variable nombre_rol varchar(50);
declare variable clave_articulo_generico varchar(20);
/*VARIABLES TOMADAS DE LA CONFIGURACION*/
declare variable precio_empresa_id integer;
declare variable rol_art_clave_principal_id integer;
declare variable rol_art_clave_alterna_id integer;
declare variable rol_art_codigo_barra_id integer;
declare variable rol_art_codigo_barra_inner_id integer;
declare variable rol_art_codigo_barra_master_id integer;
BEGIN
    SELECT PRECIO_EMPRESA_ID, ROL_ART_CLAVE_PRINCIPAL_ID, ROL_ART_CLAVE_ALTERNA_ID, ROL_ART_CODIGO_BARRA_ID, 
    ROL_ART_CODIGO_BARRA_INNER_ID, ROL_ART_CODIGO_BARRA_MASTER_ID FROM CONFIGURACION_MOBIL
    INTO :PRECIO_EMPRESA_ID, :ROL_ART_CLAVE_PRINCIPAL_ID, :ROL_ART_CLAVE_ALTERNA_ID, :ROL_ART_CODIGO_BARRA_ID, 
    :ROL_ART_CODIGO_BARRA_INNER_ID, :ROL_ART_CODIGO_BARRA_MASTER_ID;

    FOR SELECT
        A.ARTICULO_ID, A.NOMBRE, A.FACTOR_VENTA, A.UNIDAD_VENTA
        ,I.PCTJE_IMPUESTO, PA.PRECIO, PA.PRECIO * (1 + (I.PCTJE_IMPUESTO / 100))
        FROM PRECIOS_EMPRESA PE
        INNER JOIN PRECIOS_ARTICULOS PA ON PA.PRECIO_EMPRESA_ID=PE.PRECIO_EMPRESA_ID
        INNER JOIN ARTICULOS A ON A.ARTICULO_ID=PA.ARTICULO_ID
        INNER JOIN IMPUESTOS_ARTICULOS IA ON IA.ARTICULO_ID=A.ARTICULO_ID
        INNER JOIN IMPUESTOS I ON IA.IMPUESTO_ID=I.IMPUESTO_ID        
        /*WHERE A.ESTATUS IN ('A','C') AND PE.NOMBRE LIKE '%Precio distribuidor%'*/
        WHERE A.ESTATUS IN ('A','C') AND PE.PRECIO_EMPRESA_ID = :PRECIO_EMPRESA_ID
        ORDER BY A.ARTICULO_ID
    INTO :ARTICULO_ID, :NOMBRE_ARTICULO, :FACTOR_VENTA, :UNIDAD_VENTA, :PORCENTAJE_IMPUESTO, :PRECIO, :PRECIO_NETO DO
    BEGIN                
        -- PROMOCIONES
        DESCUENTO_PROMOCION= 0.00;
        SELECT DPA.DESCUENTO FROM POLITICAS_DSCTOS_PROMOCION PDP
        INNER JOIN DSCTOS_PROMO_ARTS DPA ON PDP.POLITICA_DSCTO_PROMO_ID = DPA.POLITICA_DSCTO_PROMO_ID        
        WHERE DPA.ARTICULO_ID=:ARTICULO_ID AND PDP.FECHA_INI_VIGENCIA>=:FECHAINICIO AND PDP.FECHA_FIN_VIGENCIA<=:FECHAFIN AND PDP.HABILITADA='S'
        INTO :DESCUENTO_PROMOCION;
    
        IF (:DESCUENTO_PROMOCION IS NOT NULL)THEN
            PRECIO_NETO = :PRECIO_NETO * ( 1 - (:DESCUENTO_PROMOCION/100));
        
            CODIGO_ARTICULO = 'SIN_CODIGO';
            CLAVE_ARTICULO = 'SIN_CLAVE';
            CODIGO_BARRAS = 'SIN_CODIGO_BARRAS';
            CODIGO_BARRAS_INNER = 'SIN_CODIGO_INNER';
            CODIGO_BARRAS_MASTER = 'SIN_CODIGO_MASTER';
        FOR SELECT RCA.ROL_CLAVE_ART_ID, RCA.NOMBRE, CA.CLAVE_ARTICULO FROM CLAVES_ARTICULOS CA
            INNER JOIN ROLES_CLAVES_ARTICULOS RCA ON RCA.ROL_CLAVE_ART_ID=CA.ROL_CLAVE_ART_ID
            /*WHERE CA.ARTICULO_ID = :ARTICULO_ID AND RCA.ROL_CLAVE_ART_ID IN (17,18, 11719, 11720, 11721)*/
            WHERE CA.ARTICULO_ID = :ARTICULO_ID AND RCA.ROL_CLAVE_ART_ID IN (:ROL_ART_CLAVE_PRINCIPAL_ID,:ROL_ART_CLAVE_ALTERNA_ID, :ROL_ART_CODIGO_BARRA_ID, :ROL_ART_CODIGO_BARRA_INNER_ID, :ROL_ART_CODIGO_BARRA_MASTER_ID)
            ORDER BY RCA.ROL_CLAVE_ART_ID
        INTO :ROL_CLAVE_ART_ID, :NOMBRE_ROL, :CLAVE_ARTICULO_GENERICO DO
        BEGIN
           
            IF (:ROL_CLAVE_ART_ID = :ROL_ART_CLAVE_PRINCIPAL_ID) THEN        
                    CODIGO_ARTICULO = :CLAVE_ARTICULO_GENERICO;        
            ELSE IF (:ROL_CLAVE_ART_ID = :ROL_ART_CLAVE_ALTERNA_ID) THEN
                CLAVE_ARTICULO = :CLAVE_ARTICULO_GENERICO;
            ELSE IF (:ROL_CLAVE_ART_ID = :ROL_ART_CODIGO_BARRA_ID) THEN
                CODIGO_BARRAS = :CLAVE_ARTICULO_GENERICO;
            ELSE IF (:ROL_CLAVE_ART_ID = :ROL_ART_CODIGO_BARRA_INNER_ID) THEN
                CODIGO_BARRAS_INNER = :CLAVE_ARTICULO_GENERICO;
            ELSE IF (:ROL_CLAVE_ART_ID = :ROL_ART_CODIGO_BARRA_MASTER_ID) THEN
                CODIGO_BARRAS_MASTER = :CLAVE_ARTICULO_GENERICO;
        END
        
        SELECT UNI_MIN_VENTA, CANTIDAD_INNER_ALTER, MASTER_PVENTA FROM LIBRES_ARTICULOS
        WHERE ARTICULO_ID = :ARTICULO_ID
        INTO :UNIDAD_MINIMA_VENTA, :PIEZAS_X_INNER, :PIEZAS_X_MASTER;    
        
        IF (:UNIDAD_MINIMA_VENTA IS NULL) THEN
            :UNIDAD_MINIMA_VENTA = 0.00;
        IF (:PIEZAS_X_INNER IS NULL) THEN
            :PIEZAS_X_INNER = 0.00;
        IF (:PIEZAS_X_MASTER IS NULL) THEN
            :PIEZAS_X_MASTER = 0.00;
        
        SUSPEND;
    END
END