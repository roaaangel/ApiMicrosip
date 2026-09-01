create or alter procedure complemento_pagos (
    docto_original_id integer,
    fecha_corte timestamp,
    ignorar_actual_docto_id integer,
    forma_cobro_cc_id integer,
    cuenta_ban_id integer)
returns (
    num_pagos integer,
    importe_saldo_anterior double precision,
    uuid varchar(40),
    serie varchar(3),
    consecutivosinceros integer,
    numero_operacion varchar(100),
    cta_ordenante varchar(50),
    nom_banco_ord_ext varchar(80),
    rfc_emisor_cta_ord varchar(13),
    cta_beneficiario varchar(50),
    rfc_emisor_cta_ben varchar(13))
as
declare variable importe_docto_original double precision;
declare variable importe_abonos double precision;
declare variable docto_id integer;
declare variable clave_fiscal varchar(2);
declare variable folio varchar(9);
begin
    --DOCUMENTO_ORIGINAL_ID = DOCTO_CC_ACR_ID Es el documento que genero la Venta y esta en DOCTOS_VE
    --IGNORAR_ACTUAL_DOCTO_ID = DOCTO_CC_ID Es el documento que se est? generando como movimiento
    SELECT NUM_PAGOS + 1 FROM get_num_pagos_cargo(:DOCTO_ORIGINAL_ID, :FECHA_CORTE, :IGNORAR_ACTUAL_DOCTO_ID )
    INTO NUM_PAGOS;

    SELECT SUM(IMPORTE+IMPUESTO) FROM IMPORTES_DOCTOS_CC
    WHERE DOCTO_CC_ID = :DOCTO_ORIGINAL_ID AND DOCTO_CC_ACR_ID = :DOCTO_ORIGINAL_ID
    INTO IMPORTE_DOCTO_ORIGINAL;

    SELECT CASE WHEN SUM(IMPORTE) IS NULL THEN 0 ELSE SUM(IMPORTE) END
    FROM IMPORTES_DOCTOS_CC A
    JOIN DOCTOS_CC B ON (A.DOCTO_CC_ID = B.DOCTO_CC_ID)
    JOIN CONCEPTOS_CC C ON (B.CONCEPTO_CC_ID = C.CONCEPTO_CC_ID)
    WHERE A.DOCTO_CC_ACR_ID = :DOCTO_ORIGINAL_ID
    AND A.DOCTO_CC_ID <> :IGNORAR_ACTUAL_DOCTO_ID
    AND C.TIPO = 'P'
    AND (B.FECHA + B.HORA) <= :FECHA_CORTE /* Ignorar los cobros posteriores a la fecha y hora dada */
    AND A.CANCELADO = 'N'
    AND A.APLICADO = 'S'
    INTO IMPORTE_ABONOS;

    IMPORTE_SALDO_ANTERIOR = :IMPORTE_DOCTO_ORIGINAL - :IMPORTE_ABONOS;

    SELECT UUID, DOCTO_ID FROM USOS_FOLIOS_FISCALES WHERE DOCTO_ID =
    (SELECT DOCTO_FTE_ID FROM DOCTOS_ENTRE_SIS  WHERE DOCTO_DEST_ID = :DOCTO_ORIGINAL_ID)
    INTO UUID, DOCTO_ID;

    --OBTENGO EL FOLIO DEL DOCUMENTO DE VENTA
    SELECT FOLIO FROM DOCTOS_VE WHERE DOCTO_VE_ID = :DOCTO_ID
    INTO FOLIO;

    SELECT SERIE, CONSECUTIVOSINCEROS FROM EXTRACT_SERIE_FOLIO(:FOLIO)
    INTO SERIE, CONSECUTIVOSINCEROS;

    --EXTRAEMSO INFORMACION BANCARIA
    SELECT CLAVE_FISCAL FROM FORMAS_COBRO WHERE FORMA_COBRO_ID = :FORMA_COBRO_CC_ID
    INTO CLAVE_FISCAL;

    NUMERO_OPERACION = '';

    CTA_ORDENANTE = '';
    NOM_BANCO_ORD_EXT = '';
    RFC_EMISOR_CTA_ORD = '';

    CTA_BENEFICIARIO = '';
    RFC_EMISOR_CTA_BEN = '';

/*    IF ((:CLAVE_FISCAL = '01') OR (:CLAVE_FISCAL = '03') OR (:CLAVE_FISCAL = '02')) THEN
    BEGIN
        SELECT REFERENCIA FROM FORMAS_COBRO_DOCTOS
        WHERE DOCTO_ID = :IGNORAR_ACTUAL_DOCTO_ID AND NOM_TABLA_DOCTOS = 'DOCTOS_CC'
        INTO:NUMERO_OPERACION;

        SELECT A.CUENTA_BAN_ORIG, B.NOMBRE BANCO_ORIGEN, B.RFC
        FROM DOCTOS_CC_INFO_BAN A
        LEFT JOIN BANCOS B ON (A.BANCO_ORIG_ID = B.BANCO_ID)
        WHERE DOCTO_CC_ID = :IGNORAR_ACTUAL_DOCTO_ID
        INTO CTA_ORDENANTE, NOM_BANCO_ORD_EXT, RFC_EMISOR_CTA_ORD;

        SELECT C.NUM_CUENTA, B.RFC
        FROM CUENTAS_BANCARIAS C
        LEFT JOIN BANCOS B ON C.BANCO_ID = B.BANCO_ID
        WHERE C.CUENTA_BAN_ID = :CUENTA_BAN_ID
        INTO CTA_BENEFICIARIO, RFC_EMISOR_CTA_BEN;
    END                                             */

    SUSPEND;
end
