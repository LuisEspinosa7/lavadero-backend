PGDMP         9                w            lavadero    10.6    10.6 ?    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            ?           1262    18696    lavadero    DATABASE     ?   CREATE DATABASE lavadero WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE lavadero;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            ?           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            ?           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            ?            1259    21606    cliente_vehiculo    TABLE     ]  CREATE TABLE public.cliente_vehiculo (
    cve_codigo bigint NOT NULL,
    usu_codigo integer NOT NULL,
    tve_codigo integer NOT NULL,
    mar_codigo integer NOT NULL,
    cve_placa character varying(15) NOT NULL,
    cve_kilometraje bigint NOT NULL,
    cve_fecha_creacion timestamp without time zone NOT NULL,
    cve_estado integer NOT NULL
);
 $   DROP TABLE public.cliente_vehiculo;
       public         postgres    false    3            ?            1259    21604    cliente_vehiculo_cve_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.cliente_vehiculo_cve_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.cliente_vehiculo_cve_codigo_seq;
       public       postgres    false    237    3            ?           0    0    cliente_vehiculo_cve_codigo_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.cliente_vehiculo_cve_codigo_seq OWNED BY public.cliente_vehiculo.cve_codigo;
            public       postgres    false    236            ?            1259    21573    funcionario_servicio    TABLE     Z  CREATE TABLE public.funcionario_servicio (
    fse_codigo bigint NOT NULL,
    usu_codigo integer NOT NULL,
    tse_codigo integer NOT NULL,
    tli_codigo integer NOT NULL,
    fse_fecha_creacion timestamp without time zone NOT NULL,
    fse_estado integer NOT NULL,
    tpa_codigo integer NOT NULL,
    fse_valor_pago numeric(10,2) NOT NULL
);
 (   DROP TABLE public.funcionario_servicio;
       public         postgres    false    3            ?            1259    21571 #   funcionario_servicio_fse_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.funcionario_servicio_fse_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.funcionario_servicio_fse_codigo_seq;
       public       postgres    false    233    3            ?           0    0 #   funcionario_servicio_fse_codigo_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.funcionario_servicio_fse_codigo_seq OWNED BY public.funcionario_servicio.fse_codigo;
            public       postgres    false    232            ?            1259    21660    item_funcionario    TABLE     ?   CREATE TABLE public.item_funcionario (
    ifu_codigo bigint NOT NULL,
    oit_codigo integer NOT NULL,
    fse_codigo integer NOT NULL,
    ifu_pago_funcionario numeric(10,2) NOT NULL,
    ifu_estado integer NOT NULL
);
 $   DROP TABLE public.item_funcionario;
       public         postgres    false    3            ?            1259    21658    item_funcionario_ifu_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.item_funcionario_ifu_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.item_funcionario_ifu_codigo_seq;
       public       postgres    false    243    3            ?           0    0    item_funcionario_ifu_codigo_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.item_funcionario_ifu_codigo_seq OWNED BY public.item_funcionario.ifu_codigo;
            public       postgres    false    242            ?            1259    18759    lavadero    TABLE     M  CREATE TABLE public.lavadero (
    lav_codigo bigint NOT NULL,
    lav_nombre character varying(200) NOT NULL,
    lav_descripcion character varying(500) NOT NULL,
    lav_estado integer NOT NULL,
    lav_imagen character varying(50),
    lav_fecha_creacion timestamp without time zone NOT NULL,
    lav_nit character varying(50)
);
    DROP TABLE public.lavadero;
       public         postgres    false    3            ?            1259    18757    lavadero_lav_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.lavadero_lav_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.lavadero_lav_codigo_seq;
       public       postgres    false    3    217            ?           0    0    lavadero_lav_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.lavadero_lav_codigo_seq OWNED BY public.lavadero.lav_codigo;
            public       postgres    false    216            ?            1259    21550    lavadero_servicio    TABLE       CREATE TABLE public.lavadero_servicio (
    lse_codigo bigint NOT NULL,
    lav_codigo integer NOT NULL,
    tse_codigo integer NOT NULL,
    lse_aplica_comision smallint NOT NULL,
    lse_valor_comision numeric(10,2) NOT NULL,
    lse_aplica_promocion smallint NOT NULL,
    tpr_codigo integer NOT NULL,
    lse_promocion_numero_ref integer,
    lse_promocion_valor integer,
    lse_precio_estandar numeric(10,2) NOT NULL,
    lse_fecha_creacion timestamp without time zone NOT NULL,
    lse_estado integer NOT NULL
);
 %   DROP TABLE public.lavadero_servicio;
       public         postgres    false    3            ?            1259    21548     lavadero_servicio_lse_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.lavadero_servicio_lse_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.lavadero_servicio_lse_codigo_seq;
       public       postgres    false    231    3            ?           0    0     lavadero_servicio_lse_codigo_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.lavadero_servicio_lse_codigo_seq OWNED BY public.lavadero_servicio.lse_codigo;
            public       postgres    false    230            ?            1259    21771    liquidacion_comision    TABLE     ?  CREATE TABLE public.liquidacion_comision (
    lic_codigo bigint NOT NULL,
    lse_codigo integer NOT NULL,
    lic_fecha_creacion timestamp without time zone NOT NULL,
    lic_fecha_inicio timestamp without time zone NOT NULL,
    lic_fecha_fin timestamp without time zone NOT NULL,
    lic_valor_liquidacion numeric(10,2) NOT NULL,
    lic_estado integer NOT NULL,
    lic_n_servicios integer NOT NULL
);
 (   DROP TABLE public.liquidacion_comision;
       public         postgres    false    3            ?            1259    21769 #   liquidacion_comision_lic_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.liquidacion_comision_lic_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.liquidacion_comision_lic_codigo_seq;
       public       postgres    false    3    247            ?           0    0 #   liquidacion_comision_lic_codigo_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.liquidacion_comision_lic_codigo_seq OWNED BY public.liquidacion_comision.lic_codigo;
            public       postgres    false    246            ?            1259    21735    liquidacion_funcionario    TABLE     ?  CREATE TABLE public.liquidacion_funcionario (
    lif_codigo bigint NOT NULL,
    fse_codigo integer NOT NULL,
    lav_codigo integer NOT NULL,
    lif_fecha_creacion timestamp without time zone NOT NULL,
    lif_fecha_inicio timestamp without time zone NOT NULL,
    lif_fecha_fin timestamp without time zone NOT NULL,
    lif_valor_pago numeric(10,2) NOT NULL,
    lif_estado integer NOT NULL,
    lif_valor_servicio numeric(10,2) NOT NULL,
    lif_numero_servicios integer NOT NULL
);
 +   DROP TABLE public.liquidacion_funcionario;
       public         postgres    false    3            ?            1259    21733 &   liquidacion_funcionario_lif_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.liquidacion_funcionario_lif_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.liquidacion_funcionario_lif_codigo_seq;
       public       postgres    false    3    245                        0    0 &   liquidacion_funcionario_lif_codigo_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.liquidacion_funcionario_lif_codigo_seq OWNED BY public.liquidacion_funcionario.lif_codigo;
            public       postgres    false    244            ?            1259    18803    marca    TABLE     ?   CREATE TABLE public.marca (
    mar_codigo bigint NOT NULL,
    mar_nombre character varying(300) NOT NULL,
    mar_estado integer NOT NULL,
    mar_imagen character varying(50)
);
    DROP TABLE public.marca;
       public         postgres    false    3            ?            1259    18801    marca_mar_codigo_seq    SEQUENCE     }   CREATE SEQUENCE public.marca_mar_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.marca_mar_codigo_seq;
       public       postgres    false    225    3                       0    0    marca_mar_codigo_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.marca_mar_codigo_seq OWNED BY public.marca.mar_codigo;
            public       postgres    false    224            ?            1259    21629    orden    TABLE     ?   CREATE TABLE public.orden (
    ord_codigo bigint NOT NULL,
    cve_codigo integer NOT NULL,
    ord_fecha_creacion timestamp without time zone NOT NULL,
    ord_estado integer NOT NULL,
    lav_codigo integer NOT NULL
);
    DROP TABLE public.orden;
       public         postgres    false    3            ?            1259    21642 
   orden_item    TABLE     )  CREATE TABLE public.orden_item (
    oit_codigo bigint NOT NULL,
    ord_codigo integer NOT NULL,
    oit_precio numeric(10,2) NOT NULL,
    oit_estado integer NOT NULL,
    tse_codigo integer NOT NULL,
    oit_precio_servicio numeric(10,2) NOT NULL,
    oit_aplica_promocion smallint NOT NULL
);
    DROP TABLE public.orden_item;
       public         postgres    false    3            ?            1259    21640    orden_item_oit_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.orden_item_oit_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.orden_item_oit_codigo_seq;
       public       postgres    false    241    3                       0    0    orden_item_oit_codigo_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.orden_item_oit_codigo_seq OWNED BY public.orden_item.oit_codigo;
            public       postgres    false    240            ?            1259    21627    orden_ord_codigo_seq    SEQUENCE     }   CREATE SEQUENCE public.orden_ord_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.orden_ord_codigo_seq;
       public       postgres    false    3    239                       0    0    orden_ord_codigo_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.orden_ord_codigo_seq OWNED BY public.orden.ord_codigo;
            public       postgres    false    238            ?            1259    21491    personal_lavadero    TABLE     ?   CREATE TABLE public.personal_lavadero (
    pla_codigo bigint NOT NULL,
    lav_codigo integer NOT NULL,
    usu_codigo integer NOT NULL,
    pla_estado integer NOT NULL
);
 %   DROP TABLE public.personal_lavadero;
       public         postgres    false    3            ?            1259    21489     personal_lavadero_pla_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.personal_lavadero_pla_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.personal_lavadero_pla_codigo_seq;
       public       postgres    false    3    227                       0    0     personal_lavadero_pla_codigo_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.personal_lavadero_pla_codigo_seq OWNED BY public.personal_lavadero.pla_codigo;
            public       postgres    false    226            ?            1259    21800    promocion_aplicada    TABLE     ?   CREATE TABLE public.promocion_aplicada (
    pap_codigo bigint NOT NULL,
    cve_codigo integer NOT NULL,
    pap_fecha_hora timestamp without time zone NOT NULL,
    lav_codigo integer NOT NULL,
    oit_codigo integer NOT NULL
);
 &   DROP TABLE public.promocion_aplicada;
       public         postgres    false    3            ?            1259    21798 !   promocion_aplicada_pap_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.promocion_aplicada_pap_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.promocion_aplicada_pap_codigo_seq;
       public       postgres    false    3    249                       0    0 !   promocion_aplicada_pap_codigo_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.promocion_aplicada_pap_codigo_seq OWNED BY public.promocion_aplicada.pap_codigo;
            public       postgres    false    248            ?            1259    18699    rol    TABLE     ?   CREATE TABLE public.rol (
    rol_codigo integer NOT NULL,
    rol_nombre character varying(70) NOT NULL,
    rol_estado integer NOT NULL
);
    DROP TABLE public.rol;
       public         postgres    false    3            ?            1259    18697    rol_rol_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.rol_rol_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.rol_rol_codigo_seq;
       public       postgres    false    209    3                       0    0    rol_rol_codigo_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.rol_rol_codigo_seq OWNED BY public.rol.rol_codigo;
            public       postgres    false    208            ?            1259    18707    tipo_identificacion    TABLE     ?   CREATE TABLE public.tipo_identificacion (
    tii_codigo integer NOT NULL,
    tii_nombre character varying(80) NOT NULL,
    tii_acronimo character varying(6) NOT NULL,
    tii_estado integer NOT NULL
);
 '   DROP TABLE public.tipo_identificacion;
       public         postgres    false    3            ?            1259    18705 "   tipo_identificacion_tii_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.tipo_identificacion_tii_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.tipo_identificacion_tii_codigo_seq;
       public       postgres    false    3    211                       0    0 "   tipo_identificacion_tii_codigo_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.tipo_identificacion_tii_codigo_seq OWNED BY public.tipo_identificacion.tii_codigo;
            public       postgres    false    210            ?            1259    18781    tipo_liquidacion    TABLE     ?   CREATE TABLE public.tipo_liquidacion (
    tli_codigo bigint NOT NULL,
    tli_nombre character varying(300) NOT NULL,
    tli_descripcion character varying(500) NOT NULL,
    tli_estado integer NOT NULL
);
 $   DROP TABLE public.tipo_liquidacion;
       public         postgres    false    3            ?            1259    18779    tipo_liquidacion_tli_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.tipo_liquidacion_tli_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.tipo_liquidacion_tli_codigo_seq;
       public       postgres    false    3    221                       0    0    tipo_liquidacion_tli_codigo_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.tipo_liquidacion_tli_codigo_seq OWNED BY public.tipo_liquidacion.tli_codigo;
            public       postgres    false    220            ?            1259    21596 	   tipo_pago    TABLE     ?   CREATE TABLE public.tipo_pago (
    tpa_codigo integer NOT NULL,
    tpa_nombre character varying(50) NOT NULL,
    tpa_estado integer NOT NULL,
    tpa_descripcion character varying(300) NOT NULL
);
    DROP TABLE public.tipo_pago;
       public         postgres    false    3            ?            1259    21594    tipo_pago_tpa_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.tipo_pago_tpa_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.tipo_pago_tpa_codigo_seq;
       public       postgres    false    3    235            	           0    0    tipo_pago_tpa_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.tipo_pago_tpa_codigo_seq OWNED BY public.tipo_pago.tpa_codigo;
            public       postgres    false    234            ?            1259    21541    tipo_promocion    TABLE     ?   CREATE TABLE public.tipo_promocion (
    tpr_codigo integer NOT NULL,
    tpr_nombre character varying(60) NOT NULL,
    tpr_descripcion character varying(400) NOT NULL,
    tpr_estado integer NOT NULL
);
 "   DROP TABLE public.tipo_promocion;
       public         postgres    false    3            ?            1259    21539    tipo_promocion_tpr_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.tipo_promocion_tpr_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.tipo_promocion_tpr_codigo_seq;
       public       postgres    false    3    229            
           0    0    tipo_promocion_tpr_codigo_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.tipo_promocion_tpr_codigo_seq OWNED BY public.tipo_promocion.tpr_codigo;
            public       postgres    false    228            ?            1259    18770    tipo_servicio    TABLE     ?   CREATE TABLE public.tipo_servicio (
    tse_codigo bigint NOT NULL,
    tse_nombre character varying(200) NOT NULL,
    tse_descripcion character varying(500) NOT NULL,
    tse_estado integer NOT NULL,
    tse_imagen character varying(50)
);
 !   DROP TABLE public.tipo_servicio;
       public         postgres    false    3            ?            1259    18768    tipo_servicio_tse_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.tipo_servicio_tse_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.tipo_servicio_tse_codigo_seq;
       public       postgres    false    219    3                       0    0    tipo_servicio_tse_codigo_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.tipo_servicio_tse_codigo_seq OWNED BY public.tipo_servicio.tse_codigo;
            public       postgres    false    218            ?            1259    18792    tipo_vehiculo    TABLE     ?   CREATE TABLE public.tipo_vehiculo (
    tve_codigo bigint NOT NULL,
    tve_nombre character varying(300) NOT NULL,
    tve_descripcion character varying(500) NOT NULL,
    tve_imagen character varying(30),
    tve_estado integer NOT NULL
);
 !   DROP TABLE public.tipo_vehiculo;
       public         postgres    false    3            ?            1259    18790    tipo_vehiculo_tve_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.tipo_vehiculo_tve_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.tipo_vehiculo_tve_codigo_seq;
       public       postgres    false    223    3                       0    0    tipo_vehiculo_tve_codigo_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.tipo_vehiculo_tve_codigo_seq OWNED BY public.tipo_vehiculo.tve_codigo;
            public       postgres    false    222            ?            1259    18728    usuario    TABLE     X  CREATE TABLE public.usuario (
    usu_codigo bigint NOT NULL,
    usu_email character varying(80) NOT NULL,
    usu_password character varying(60) NOT NULL,
    usu_estado integer NOT NULL,
    usu_imagen character varying(100),
    usu_nombre_1 character varying(50) NOT NULL,
    usu_nombre_2 character varying(50),
    usu_apellido_1 character varying(50) NOT NULL,
    usu_apellido_2 character varying(50),
    tii_codigo integer NOT NULL,
    usu_identificacion character varying(50) NOT NULL,
    usu_direccion character varying(50) NOT NULL,
    usu_telefono character varying(50) NOT NULL
);
    DROP TABLE public.usuario;
       public         postgres    false    3            ?            1259    18741    usuario_rol    TABLE     ?   CREATE TABLE public.usuario_rol (
    uro_codigo integer NOT NULL,
    usu_codigo integer NOT NULL,
    rol_codigo integer NOT NULL
);
    DROP TABLE public.usuario_rol;
       public         postgres    false    3            ?            1259    18739    usuario_rol_uro_codigo_seq    SEQUENCE     ?   CREATE SEQUENCE public.usuario_rol_uro_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.usuario_rol_uro_codigo_seq;
       public       postgres    false    3    215                       0    0    usuario_rol_uro_codigo_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.usuario_rol_uro_codigo_seq OWNED BY public.usuario_rol.uro_codigo;
            public       postgres    false    214            ?            1259    18726    usuario_usu_codigo_seq    SEQUENCE        CREATE SEQUENCE public.usuario_usu_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.usuario_usu_codigo_seq;
       public       postgres    false    213    3                       0    0    usuario_usu_codigo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.usuario_usu_codigo_seq OWNED BY public.usuario.usu_codigo;
            public       postgres    false    212                       2604    21609    cliente_vehiculo cve_codigo    DEFAULT     ?   ALTER TABLE ONLY public.cliente_vehiculo ALTER COLUMN cve_codigo SET DEFAULT nextval('public.cliente_vehiculo_cve_codigo_seq'::regclass);
 J   ALTER TABLE public.cliente_vehiculo ALTER COLUMN cve_codigo DROP DEFAULT;
       public       postgres    false    236    237    237                       2604    21576    funcionario_servicio fse_codigo    DEFAULT     ?   ALTER TABLE ONLY public.funcionario_servicio ALTER COLUMN fse_codigo SET DEFAULT nextval('public.funcionario_servicio_fse_codigo_seq'::regclass);
 N   ALTER TABLE public.funcionario_servicio ALTER COLUMN fse_codigo DROP DEFAULT;
       public       postgres    false    232    233    233                       2604    21663    item_funcionario ifu_codigo    DEFAULT     ?   ALTER TABLE ONLY public.item_funcionario ALTER COLUMN ifu_codigo SET DEFAULT nextval('public.item_funcionario_ifu_codigo_seq'::regclass);
 J   ALTER TABLE public.item_funcionario ALTER COLUMN ifu_codigo DROP DEFAULT;
       public       postgres    false    242    243    243            ?
           2604    18762    lavadero lav_codigo    DEFAULT     z   ALTER TABLE ONLY public.lavadero ALTER COLUMN lav_codigo SET DEFAULT nextval('public.lavadero_lav_codigo_seq'::regclass);
 B   ALTER TABLE public.lavadero ALTER COLUMN lav_codigo DROP DEFAULT;
       public       postgres    false    216    217    217                       2604    21553    lavadero_servicio lse_codigo    DEFAULT     ?   ALTER TABLE ONLY public.lavadero_servicio ALTER COLUMN lse_codigo SET DEFAULT nextval('public.lavadero_servicio_lse_codigo_seq'::regclass);
 K   ALTER TABLE public.lavadero_servicio ALTER COLUMN lse_codigo DROP DEFAULT;
       public       postgres    false    230    231    231            
           2604    21774    liquidacion_comision lic_codigo    DEFAULT     ?   ALTER TABLE ONLY public.liquidacion_comision ALTER COLUMN lic_codigo SET DEFAULT nextval('public.liquidacion_comision_lic_codigo_seq'::regclass);
 N   ALTER TABLE public.liquidacion_comision ALTER COLUMN lic_codigo DROP DEFAULT;
       public       postgres    false    247    246    247            	           2604    21738 "   liquidacion_funcionario lif_codigo    DEFAULT     ?   ALTER TABLE ONLY public.liquidacion_funcionario ALTER COLUMN lif_codigo SET DEFAULT nextval('public.liquidacion_funcionario_lif_codigo_seq'::regclass);
 Q   ALTER TABLE public.liquidacion_funcionario ALTER COLUMN lif_codigo DROP DEFAULT;
       public       postgres    false    244    245    245            ?
           2604    18806    marca mar_codigo    DEFAULT     t   ALTER TABLE ONLY public.marca ALTER COLUMN mar_codigo SET DEFAULT nextval('public.marca_mar_codigo_seq'::regclass);
 ?   ALTER TABLE public.marca ALTER COLUMN mar_codigo DROP DEFAULT;
       public       postgres    false    225    224    225                       2604    21632    orden ord_codigo    DEFAULT     t   ALTER TABLE ONLY public.orden ALTER COLUMN ord_codigo SET DEFAULT nextval('public.orden_ord_codigo_seq'::regclass);
 ?   ALTER TABLE public.orden ALTER COLUMN ord_codigo DROP DEFAULT;
       public       postgres    false    238    239    239                       2604    21645    orden_item oit_codigo    DEFAULT     ~   ALTER TABLE ONLY public.orden_item ALTER COLUMN oit_codigo SET DEFAULT nextval('public.orden_item_oit_codigo_seq'::regclass);
 D   ALTER TABLE public.orden_item ALTER COLUMN oit_codigo DROP DEFAULT;
       public       postgres    false    240    241    241                        2604    21494    personal_lavadero pla_codigo    DEFAULT     ?   ALTER TABLE ONLY public.personal_lavadero ALTER COLUMN pla_codigo SET DEFAULT nextval('public.personal_lavadero_pla_codigo_seq'::regclass);
 K   ALTER TABLE public.personal_lavadero ALTER COLUMN pla_codigo DROP DEFAULT;
       public       postgres    false    227    226    227                       2604    21803    promocion_aplicada pap_codigo    DEFAULT     ?   ALTER TABLE ONLY public.promocion_aplicada ALTER COLUMN pap_codigo SET DEFAULT nextval('public.promocion_aplicada_pap_codigo_seq'::regclass);
 L   ALTER TABLE public.promocion_aplicada ALTER COLUMN pap_codigo DROP DEFAULT;
       public       postgres    false    249    248    249            ?
           2604    18702    rol rol_codigo    DEFAULT     p   ALTER TABLE ONLY public.rol ALTER COLUMN rol_codigo SET DEFAULT nextval('public.rol_rol_codigo_seq'::regclass);
 =   ALTER TABLE public.rol ALTER COLUMN rol_codigo DROP DEFAULT;
       public       postgres    false    209    208    209            ?
           2604    18710    tipo_identificacion tii_codigo    DEFAULT     ?   ALTER TABLE ONLY public.tipo_identificacion ALTER COLUMN tii_codigo SET DEFAULT nextval('public.tipo_identificacion_tii_codigo_seq'::regclass);
 M   ALTER TABLE public.tipo_identificacion ALTER COLUMN tii_codigo DROP DEFAULT;
       public       postgres    false    210    211    211            ?
           2604    18784    tipo_liquidacion tli_codigo    DEFAULT     ?   ALTER TABLE ONLY public.tipo_liquidacion ALTER COLUMN tli_codigo SET DEFAULT nextval('public.tipo_liquidacion_tli_codigo_seq'::regclass);
 J   ALTER TABLE public.tipo_liquidacion ALTER COLUMN tli_codigo DROP DEFAULT;
       public       postgres    false    220    221    221                       2604    21599    tipo_pago tpa_codigo    DEFAULT     |   ALTER TABLE ONLY public.tipo_pago ALTER COLUMN tpa_codigo SET DEFAULT nextval('public.tipo_pago_tpa_codigo_seq'::regclass);
 C   ALTER TABLE public.tipo_pago ALTER COLUMN tpa_codigo DROP DEFAULT;
       public       postgres    false    234    235    235                       2604    21544    tipo_promocion tpr_codigo    DEFAULT     ?   ALTER TABLE ONLY public.tipo_promocion ALTER COLUMN tpr_codigo SET DEFAULT nextval('public.tipo_promocion_tpr_codigo_seq'::regclass);
 H   ALTER TABLE public.tipo_promocion ALTER COLUMN tpr_codigo DROP DEFAULT;
       public       postgres    false    229    228    229            ?
           2604    18773    tipo_servicio tse_codigo    DEFAULT     ?   ALTER TABLE ONLY public.tipo_servicio ALTER COLUMN tse_codigo SET DEFAULT nextval('public.tipo_servicio_tse_codigo_seq'::regclass);
 G   ALTER TABLE public.tipo_servicio ALTER COLUMN tse_codigo DROP DEFAULT;
       public       postgres    false    218    219    219            ?
           2604    18795    tipo_vehiculo tve_codigo    DEFAULT     ?   ALTER TABLE ONLY public.tipo_vehiculo ALTER COLUMN tve_codigo SET DEFAULT nextval('public.tipo_vehiculo_tve_codigo_seq'::regclass);
 G   ALTER TABLE public.tipo_vehiculo ALTER COLUMN tve_codigo DROP DEFAULT;
       public       postgres    false    222    223    223            ?
           2604    18731    usuario usu_codigo    DEFAULT     x   ALTER TABLE ONLY public.usuario ALTER COLUMN usu_codigo SET DEFAULT nextval('public.usuario_usu_codigo_seq'::regclass);
 A   ALTER TABLE public.usuario ALTER COLUMN usu_codigo DROP DEFAULT;
       public       postgres    false    212    213    213            ?
           2604    18744    usuario_rol uro_codigo    DEFAULT     ?   ALTER TABLE ONLY public.usuario_rol ALTER COLUMN uro_codigo SET DEFAULT nextval('public.usuario_rol_uro_codigo_seq'::regclass);
 E   ALTER TABLE public.usuario_rol ALTER COLUMN uro_codigo DROP DEFAULT;
       public       postgres    false    215    214    215            ?          0    21606    cliente_vehiculo 
   TABLE DATA               ?   COPY public.cliente_vehiculo (cve_codigo, usu_codigo, tve_codigo, mar_codigo, cve_placa, cve_kilometraje, cve_fecha_creacion, cve_estado) FROM stdin;
    public       postgres    false    237   (?       ?          0    21573    funcionario_servicio 
   TABLE DATA               ?   COPY public.funcionario_servicio (fse_codigo, usu_codigo, tse_codigo, tli_codigo, fse_fecha_creacion, fse_estado, tpa_codigo, fse_valor_pago) FROM stdin;
    public       postgres    false    233   H?       ?          0    21660    item_funcionario 
   TABLE DATA               p   COPY public.item_funcionario (ifu_codigo, oit_codigo, fse_codigo, ifu_pago_funcionario, ifu_estado) FROM stdin;
    public       postgres    false    243   ??       ?          0    18759    lavadero 
   TABLE DATA               ?   COPY public.lavadero (lav_codigo, lav_nombre, lav_descripcion, lav_estado, lav_imagen, lav_fecha_creacion, lav_nit) FROM stdin;
    public       postgres    false    217   ?       ?          0    21550    lavadero_servicio 
   TABLE DATA               ?   COPY public.lavadero_servicio (lse_codigo, lav_codigo, tse_codigo, lse_aplica_comision, lse_valor_comision, lse_aplica_promocion, tpr_codigo, lse_promocion_numero_ref, lse_promocion_valor, lse_precio_estandar, lse_fecha_creacion, lse_estado) FROM stdin;
    public       postgres    false    231   ??       ?          0    21771    liquidacion_comision 
   TABLE DATA               ?   COPY public.liquidacion_comision (lic_codigo, lse_codigo, lic_fecha_creacion, lic_fecha_inicio, lic_fecha_fin, lic_valor_liquidacion, lic_estado, lic_n_servicios) FROM stdin;
    public       postgres    false    247   (?       ?          0    21735    liquidacion_funcionario 
   TABLE DATA               ?   COPY public.liquidacion_funcionario (lif_codigo, fse_codigo, lav_codigo, lif_fecha_creacion, lif_fecha_inicio, lif_fecha_fin, lif_valor_pago, lif_estado, lif_valor_servicio, lif_numero_servicios) FROM stdin;
    public       postgres    false    245   |?       ?          0    18803    marca 
   TABLE DATA               O   COPY public.marca (mar_codigo, mar_nombre, mar_estado, mar_imagen) FROM stdin;
    public       postgres    false    225   ??       ?          0    21629    orden 
   TABLE DATA               c   COPY public.orden (ord_codigo, cve_codigo, ord_fecha_creacion, ord_estado, lav_codigo) FROM stdin;
    public       postgres    false    239   ;?       ?          0    21642 
   orden_item 
   TABLE DATA               ?   COPY public.orden_item (oit_codigo, ord_codigo, oit_precio, oit_estado, tse_codigo, oit_precio_servicio, oit_aplica_promocion) FROM stdin;
    public       postgres    false    241   ??       ?          0    21491    personal_lavadero 
   TABLE DATA               [   COPY public.personal_lavadero (pla_codigo, lav_codigo, usu_codigo, pla_estado) FROM stdin;
    public       postgres    false    227   )?       ?          0    21800    promocion_aplicada 
   TABLE DATA               l   COPY public.promocion_aplicada (pap_codigo, cve_codigo, pap_fecha_hora, lav_codigo, oit_codigo) FROM stdin;
    public       postgres    false    249   h?       ?          0    18699    rol 
   TABLE DATA               A   COPY public.rol (rol_codigo, rol_nombre, rol_estado) FROM stdin;
    public       postgres    false    209   ??       ?          0    18707    tipo_identificacion 
   TABLE DATA               _   COPY public.tipo_identificacion (tii_codigo, tii_nombre, tii_acronimo, tii_estado) FROM stdin;
    public       postgres    false    211   ?       ?          0    18781    tipo_liquidacion 
   TABLE DATA               _   COPY public.tipo_liquidacion (tli_codigo, tli_nombre, tli_descripcion, tli_estado) FROM stdin;
    public       postgres    false    221   g?       ?          0    21596 	   tipo_pago 
   TABLE DATA               X   COPY public.tipo_pago (tpa_codigo, tpa_nombre, tpa_estado, tpa_descripcion) FROM stdin;
    public       postgres    false    235   ??       ?          0    21541    tipo_promocion 
   TABLE DATA               ]   COPY public.tipo_promocion (tpr_codigo, tpr_nombre, tpr_descripcion, tpr_estado) FROM stdin;
    public       postgres    false    229   Y?       ?          0    18770    tipo_servicio 
   TABLE DATA               h   COPY public.tipo_servicio (tse_codigo, tse_nombre, tse_descripcion, tse_estado, tse_imagen) FROM stdin;
    public       postgres    false    219   ?       ?          0    18792    tipo_vehiculo 
   TABLE DATA               h   COPY public.tipo_vehiculo (tve_codigo, tve_nombre, tve_descripcion, tve_imagen, tve_estado) FROM stdin;
    public       postgres    false    223   ??       ?          0    18728    usuario 
   TABLE DATA               ?   COPY public.usuario (usu_codigo, usu_email, usu_password, usu_estado, usu_imagen, usu_nombre_1, usu_nombre_2, usu_apellido_1, usu_apellido_2, tii_codigo, usu_identificacion, usu_direccion, usu_telefono) FROM stdin;
    public       postgres    false    213   %?       ?          0    18741    usuario_rol 
   TABLE DATA               I   COPY public.usuario_rol (uro_codigo, usu_codigo, rol_codigo) FROM stdin;
    public       postgres    false    215   M?                  0    0    cliente_vehiculo_cve_codigo_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.cliente_vehiculo_cve_codigo_seq', 12, true);
            public       postgres    false    236                       0    0 #   funcionario_servicio_fse_codigo_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.funcionario_servicio_fse_codigo_seq', 9, true);
            public       postgres    false    232                       0    0    item_funcionario_ifu_codigo_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.item_funcionario_ifu_codigo_seq', 39, true);
            public       postgres    false    242                       0    0    lavadero_lav_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.lavadero_lav_codigo_seq', 7, true);
            public       postgres    false    216                       0    0     lavadero_servicio_lse_codigo_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.lavadero_servicio_lse_codigo_seq', 19, true);
            public       postgres    false    230                       0    0 #   liquidacion_comision_lic_codigo_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.liquidacion_comision_lic_codigo_seq', 3, true);
            public       postgres    false    246                       0    0 &   liquidacion_funcionario_lif_codigo_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.liquidacion_funcionario_lif_codigo_seq', 15, true);
            public       postgres    false    244                       0    0    marca_mar_codigo_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.marca_mar_codigo_seq', 14, true);
            public       postgres    false    224                       0    0    orden_item_oit_codigo_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.orden_item_oit_codigo_seq', 39, true);
            public       postgres    false    240                       0    0    orden_ord_codigo_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.orden_ord_codigo_seq', 35, true);
            public       postgres    false    238                       0    0     personal_lavadero_pla_codigo_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.personal_lavadero_pla_codigo_seq', 11, true);
            public       postgres    false    226                       0    0 !   promocion_aplicada_pap_codigo_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.promocion_aplicada_pap_codigo_seq', 3, true);
            public       postgres    false    248                       0    0    rol_rol_codigo_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.rol_rol_codigo_seq', 5, true);
            public       postgres    false    208                       0    0 "   tipo_identificacion_tii_codigo_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.tipo_identificacion_tii_codigo_seq', 2, true);
            public       postgres    false    210                       0    0    tipo_liquidacion_tli_codigo_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.tipo_liquidacion_tli_codigo_seq', 18, true);
            public       postgres    false    220                       0    0    tipo_pago_tpa_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.tipo_pago_tpa_codigo_seq', 2, true);
            public       postgres    false    234                       0    0    tipo_promocion_tpr_codigo_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.tipo_promocion_tpr_codigo_seq', 3, true);
            public       postgres    false    228                        0    0    tipo_servicio_tse_codigo_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.tipo_servicio_tse_codigo_seq', 10, true);
            public       postgres    false    218            !           0    0    tipo_vehiculo_tve_codigo_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.tipo_vehiculo_tve_codigo_seq', 20, true);
            public       postgres    false    222            "           0    0    usuario_rol_uro_codigo_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.usuario_rol_uro_codigo_seq', 104, true);
            public       postgres    false    214            #           0    0    usuario_usu_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.usuario_usu_codigo_seq', 48, true);
            public       postgres    false    212            )           2606    21611 &   cliente_vehiculo cliente_vehiculo_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.cliente_vehiculo
    ADD CONSTRAINT cliente_vehiculo_pkey PRIMARY KEY (cve_codigo);
 P   ALTER TABLE ONLY public.cliente_vehiculo DROP CONSTRAINT cliente_vehiculo_pkey;
       public         postgres    false    237            %           2606    21578 .   funcionario_servicio funcionario_servicio_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.funcionario_servicio
    ADD CONSTRAINT funcionario_servicio_pkey PRIMARY KEY (fse_codigo);
 X   ALTER TABLE ONLY public.funcionario_servicio DROP CONSTRAINT funcionario_servicio_pkey;
       public         postgres    false    233            /           2606    21665 &   item_funcionario item_funcionario_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.item_funcionario
    ADD CONSTRAINT item_funcionario_pkey PRIMARY KEY (ifu_codigo);
 P   ALTER TABLE ONLY public.item_funcionario DROP CONSTRAINT item_funcionario_pkey;
       public         postgres    false    243                       2606    18767    lavadero lavadero_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.lavadero
    ADD CONSTRAINT lavadero_pkey PRIMARY KEY (lav_codigo);
 @   ALTER TABLE ONLY public.lavadero DROP CONSTRAINT lavadero_pkey;
       public         postgres    false    217            #           2606    21555 (   lavadero_servicio lavadero_servicio_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.lavadero_servicio
    ADD CONSTRAINT lavadero_servicio_pkey PRIMARY KEY (lse_codigo);
 R   ALTER TABLE ONLY public.lavadero_servicio DROP CONSTRAINT lavadero_servicio_pkey;
       public         postgres    false    231            3           2606    21776 .   liquidacion_comision liquidacion_comision_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public.liquidacion_comision
    ADD CONSTRAINT liquidacion_comision_pkey PRIMARY KEY (lic_codigo);
 X   ALTER TABLE ONLY public.liquidacion_comision DROP CONSTRAINT liquidacion_comision_pkey;
       public         postgres    false    247            1           2606    21740 4   liquidacion_funcionario liquidacion_funcionario_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public.liquidacion_funcionario
    ADD CONSTRAINT liquidacion_funcionario_pkey PRIMARY KEY (lif_codigo);
 ^   ALTER TABLE ONLY public.liquidacion_funcionario DROP CONSTRAINT liquidacion_funcionario_pkey;
       public         postgres    false    245                       2606    18808    marca marca_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (mar_codigo);
 :   ALTER TABLE ONLY public.marca DROP CONSTRAINT marca_pkey;
       public         postgres    false    225            -           2606    21647    orden_item orden_item_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.orden_item
    ADD CONSTRAINT orden_item_pkey PRIMARY KEY (oit_codigo);
 D   ALTER TABLE ONLY public.orden_item DROP CONSTRAINT orden_item_pkey;
       public         postgres    false    241            +           2606    21634    orden orden_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.orden
    ADD CONSTRAINT orden_pkey PRIMARY KEY (ord_codigo);
 :   ALTER TABLE ONLY public.orden DROP CONSTRAINT orden_pkey;
       public         postgres    false    239                       2606    21496 (   personal_lavadero personal_lavadero_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.personal_lavadero
    ADD CONSTRAINT personal_lavadero_pkey PRIMARY KEY (pla_codigo);
 R   ALTER TABLE ONLY public.personal_lavadero DROP CONSTRAINT personal_lavadero_pkey;
       public         postgres    false    227            5           2606    21805 *   promocion_aplicada promocion_aplicada_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.promocion_aplicada
    ADD CONSTRAINT promocion_aplicada_pkey PRIMARY KEY (pap_codigo);
 T   ALTER TABLE ONLY public.promocion_aplicada DROP CONSTRAINT promocion_aplicada_pkey;
       public         postgres    false    249                       2606    18704    rol rol_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (rol_codigo);
 6   ALTER TABLE ONLY public.rol DROP CONSTRAINT rol_pkey;
       public         postgres    false    209                       2606    18712 ,   tipo_identificacion tipo_identificacion_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.tipo_identificacion
    ADD CONSTRAINT tipo_identificacion_pkey PRIMARY KEY (tii_codigo);
 V   ALTER TABLE ONLY public.tipo_identificacion DROP CONSTRAINT tipo_identificacion_pkey;
       public         postgres    false    211                       2606    18789 &   tipo_liquidacion tipo_liquidacion_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.tipo_liquidacion
    ADD CONSTRAINT tipo_liquidacion_pkey PRIMARY KEY (tli_codigo);
 P   ALTER TABLE ONLY public.tipo_liquidacion DROP CONSTRAINT tipo_liquidacion_pkey;
       public         postgres    false    221            '           2606    21601    tipo_pago tipo_pago_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.tipo_pago
    ADD CONSTRAINT tipo_pago_pkey PRIMARY KEY (tpa_codigo);
 B   ALTER TABLE ONLY public.tipo_pago DROP CONSTRAINT tipo_pago_pkey;
       public         postgres    false    235            !           2606    21546 "   tipo_promocion tipo_promocion_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.tipo_promocion
    ADD CONSTRAINT tipo_promocion_pkey PRIMARY KEY (tpr_codigo);
 L   ALTER TABLE ONLY public.tipo_promocion DROP CONSTRAINT tipo_promocion_pkey;
       public         postgres    false    229                       2606    18778     tipo_servicio tipo_servicio_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.tipo_servicio
    ADD CONSTRAINT tipo_servicio_pkey PRIMARY KEY (tse_codigo);
 J   ALTER TABLE ONLY public.tipo_servicio DROP CONSTRAINT tipo_servicio_pkey;
       public         postgres    false    219                       2606    18800     tipo_vehiculo tipo_vehiculo_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.tipo_vehiculo
    ADD CONSTRAINT tipo_vehiculo_pkey PRIMARY KEY (tve_codigo);
 J   ALTER TABLE ONLY public.tipo_vehiculo DROP CONSTRAINT tipo_vehiculo_pkey;
       public         postgres    false    223                       2606    18733    usuario usuario_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usu_codigo);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         postgres    false    213                       2606    18746    usuario_rol usuario_rol_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_pkey PRIMARY KEY (uro_codigo);
 F   ALTER TABLE ONLY public.usuario_rol DROP CONSTRAINT usuario_rol_pkey;
       public         postgres    false    215            C           2606    21622 1   cliente_vehiculo cliente_vehiculo_mar_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.cliente_vehiculo
    ADD CONSTRAINT cliente_vehiculo_mar_codigo_fkey FOREIGN KEY (mar_codigo) REFERENCES public.marca(mar_codigo);
 [   ALTER TABLE ONLY public.cliente_vehiculo DROP CONSTRAINT cliente_vehiculo_mar_codigo_fkey;
       public       postgres    false    2845    237    225            B           2606    21617 1   cliente_vehiculo cliente_vehiculo_tve_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.cliente_vehiculo
    ADD CONSTRAINT cliente_vehiculo_tve_codigo_fkey FOREIGN KEY (tve_codigo) REFERENCES public.tipo_vehiculo(tve_codigo);
 [   ALTER TABLE ONLY public.cliente_vehiculo DROP CONSTRAINT cliente_vehiculo_tve_codigo_fkey;
       public       postgres    false    237    223    2843            A           2606    21612 1   cliente_vehiculo cliente_vehiculo_usu_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.cliente_vehiculo
    ADD CONSTRAINT cliente_vehiculo_usu_codigo_fkey FOREIGN KEY (usu_codigo) REFERENCES public.usuario(usu_codigo);
 [   ALTER TABLE ONLY public.cliente_vehiculo DROP CONSTRAINT cliente_vehiculo_usu_codigo_fkey;
       public       postgres    false    213    237    2833            @           2606    21589 9   funcionario_servicio funcionario_servicio_tli_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.funcionario_servicio
    ADD CONSTRAINT funcionario_servicio_tli_codigo_fkey FOREIGN KEY (tli_codigo) REFERENCES public.tipo_liquidacion(tli_codigo);
 c   ALTER TABLE ONLY public.funcionario_servicio DROP CONSTRAINT funcionario_servicio_tli_codigo_fkey;
       public       postgres    false    221    233    2841            ?           2606    21584 9   funcionario_servicio funcionario_servicio_tse_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.funcionario_servicio
    ADD CONSTRAINT funcionario_servicio_tse_codigo_fkey FOREIGN KEY (tse_codigo) REFERENCES public.tipo_servicio(tse_codigo);
 c   ALTER TABLE ONLY public.funcionario_servicio DROP CONSTRAINT funcionario_servicio_tse_codigo_fkey;
       public       postgres    false    233    2839    219            >           2606    21579 9   funcionario_servicio funcionario_servicio_usu_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.funcionario_servicio
    ADD CONSTRAINT funcionario_servicio_usu_codigo_fkey FOREIGN KEY (usu_codigo) REFERENCES public.usuario(usu_codigo);
 c   ALTER TABLE ONLY public.funcionario_servicio DROP CONSTRAINT funcionario_servicio_usu_codigo_fkey;
       public       postgres    false    233    213    2833            H           2606    21671 1   item_funcionario item_funcionario_fse_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.item_funcionario
    ADD CONSTRAINT item_funcionario_fse_codigo_fkey FOREIGN KEY (fse_codigo) REFERENCES public.funcionario_servicio(fse_codigo);
 [   ALTER TABLE ONLY public.item_funcionario DROP CONSTRAINT item_funcionario_fse_codigo_fkey;
       public       postgres    false    2853    243    233            G           2606    21666 1   item_funcionario item_funcionario_oit_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.item_funcionario
    ADD CONSTRAINT item_funcionario_oit_codigo_fkey FOREIGN KEY (oit_codigo) REFERENCES public.orden_item(oit_codigo);
 [   ALTER TABLE ONLY public.item_funcionario DROP CONSTRAINT item_funcionario_oit_codigo_fkey;
       public       postgres    false    241    243    2861            ;           2606    21556 3   lavadero_servicio lavadero_servicio_lav_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.lavadero_servicio
    ADD CONSTRAINT lavadero_servicio_lav_codigo_fkey FOREIGN KEY (lav_codigo) REFERENCES public.lavadero(lav_codigo);
 ]   ALTER TABLE ONLY public.lavadero_servicio DROP CONSTRAINT lavadero_servicio_lav_codigo_fkey;
       public       postgres    false    217    231    2837            =           2606    21566 3   lavadero_servicio lavadero_servicio_tpr_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.lavadero_servicio
    ADD CONSTRAINT lavadero_servicio_tpr_codigo_fkey FOREIGN KEY (tpr_codigo) REFERENCES public.tipo_promocion(tpr_codigo);
 ]   ALTER TABLE ONLY public.lavadero_servicio DROP CONSTRAINT lavadero_servicio_tpr_codigo_fkey;
       public       postgres    false    229    2849    231            <           2606    21561 3   lavadero_servicio lavadero_servicio_tse_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.lavadero_servicio
    ADD CONSTRAINT lavadero_servicio_tse_codigo_fkey FOREIGN KEY (tse_codigo) REFERENCES public.tipo_servicio(tse_codigo);
 ]   ALTER TABLE ONLY public.lavadero_servicio DROP CONSTRAINT lavadero_servicio_tse_codigo_fkey;
       public       postgres    false    231    2839    219            K           2606    21777 9   liquidacion_comision liquidacion_comision_lse_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.liquidacion_comision
    ADD CONSTRAINT liquidacion_comision_lse_codigo_fkey FOREIGN KEY (lse_codigo) REFERENCES public.lavadero_servicio(lse_codigo);
 c   ALTER TABLE ONLY public.liquidacion_comision DROP CONSTRAINT liquidacion_comision_lse_codigo_fkey;
       public       postgres    false    231    247    2851            I           2606    21741 ?   liquidacion_funcionario liquidacion_funcionario_fse_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.liquidacion_funcionario
    ADD CONSTRAINT liquidacion_funcionario_fse_codigo_fkey FOREIGN KEY (fse_codigo) REFERENCES public.funcionario_servicio(fse_codigo);
 i   ALTER TABLE ONLY public.liquidacion_funcionario DROP CONSTRAINT liquidacion_funcionario_fse_codigo_fkey;
       public       postgres    false    233    2853    245            J           2606    21746 ?   liquidacion_funcionario liquidacion_funcionario_lav_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.liquidacion_funcionario
    ADD CONSTRAINT liquidacion_funcionario_lav_codigo_fkey FOREIGN KEY (lav_codigo) REFERENCES public.lavadero(lav_codigo);
 i   ALTER TABLE ONLY public.liquidacion_funcionario DROP CONSTRAINT liquidacion_funcionario_lav_codigo_fkey;
       public       postgres    false    2837    245    217            D           2606    21635    orden orden_cve_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.orden
    ADD CONSTRAINT orden_cve_codigo_fkey FOREIGN KEY (cve_codigo) REFERENCES public.cliente_vehiculo(cve_codigo);
 E   ALTER TABLE ONLY public.orden DROP CONSTRAINT orden_cve_codigo_fkey;
       public       postgres    false    2857    237    239            E           2606    21648 %   orden_item orden_item_ord_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.orden_item
    ADD CONSTRAINT orden_item_ord_codigo_fkey FOREIGN KEY (ord_codigo) REFERENCES public.orden(ord_codigo);
 O   ALTER TABLE ONLY public.orden_item DROP CONSTRAINT orden_item_ord_codigo_fkey;
       public       postgres    false    239    241    2859            F           2606    21680 %   orden_item orden_item_tse_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.orden_item
    ADD CONSTRAINT orden_item_tse_codigo_fkey FOREIGN KEY (tse_codigo) REFERENCES public.tipo_servicio(tse_codigo);
 O   ALTER TABLE ONLY public.orden_item DROP CONSTRAINT orden_item_tse_codigo_fkey;
       public       postgres    false    241    219    2839            9           2606    21497 3   personal_lavadero personal_lavadero_lav_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.personal_lavadero
    ADD CONSTRAINT personal_lavadero_lav_codigo_fkey FOREIGN KEY (lav_codigo) REFERENCES public.lavadero(lav_codigo);
 ]   ALTER TABLE ONLY public.personal_lavadero DROP CONSTRAINT personal_lavadero_lav_codigo_fkey;
       public       postgres    false    227    217    2837            :           2606    21502 3   personal_lavadero personal_lavadero_usu_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.personal_lavadero
    ADD CONSTRAINT personal_lavadero_usu_codigo_fkey FOREIGN KEY (usu_codigo) REFERENCES public.usuario(usu_codigo);
 ]   ALTER TABLE ONLY public.personal_lavadero DROP CONSTRAINT personal_lavadero_usu_codigo_fkey;
       public       postgres    false    2833    227    213            L           2606    21806 5   promocion_aplicada promocion_aplicada_cve_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.promocion_aplicada
    ADD CONSTRAINT promocion_aplicada_cve_codigo_fkey FOREIGN KEY (cve_codigo) REFERENCES public.cliente_vehiculo(cve_codigo);
 _   ALTER TABLE ONLY public.promocion_aplicada DROP CONSTRAINT promocion_aplicada_cve_codigo_fkey;
       public       postgres    false    249    2857    237            M           2606    21811 5   promocion_aplicada promocion_aplicada_lav_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.promocion_aplicada
    ADD CONSTRAINT promocion_aplicada_lav_codigo_fkey FOREIGN KEY (lav_codigo) REFERENCES public.lavadero(lav_codigo);
 _   ALTER TABLE ONLY public.promocion_aplicada DROP CONSTRAINT promocion_aplicada_lav_codigo_fkey;
       public       postgres    false    217    2837    249            N           2606    21816 5   promocion_aplicada promocion_aplicada_oit_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.promocion_aplicada
    ADD CONSTRAINT promocion_aplicada_oit_codigo_fkey FOREIGN KEY (oit_codigo) REFERENCES public.orden_item(oit_codigo);
 _   ALTER TABLE ONLY public.promocion_aplicada DROP CONSTRAINT promocion_aplicada_oit_codigo_fkey;
       public       postgres    false    241    2861    249            8           2606    18752    usuario_rol rol_codigo    FK CONSTRAINT     ~   ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT rol_codigo FOREIGN KEY (rol_codigo) REFERENCES public.rol(rol_codigo);
 @   ALTER TABLE ONLY public.usuario_rol DROP CONSTRAINT rol_codigo;
       public       postgres    false    2829    209    215            7           2606    18747    usuario_rol usu_codigo    FK CONSTRAINT     ?   ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usu_codigo FOREIGN KEY (usu_codigo) REFERENCES public.usuario(usu_codigo);
 @   ALTER TABLE ONLY public.usuario_rol DROP CONSTRAINT usu_codigo;
       public       postgres    false    213    2833    215            6           2606    21484    usuario usuario_tii_codigo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_tii_codigo_fkey FOREIGN KEY (tii_codigo) REFERENCES public.tipo_identificacion(tii_codigo);
 I   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_tii_codigo_fkey;
       public       postgres    false    213    2831    211            ?     x?m?KN1D??S?b????"6?l????)????^U??? :^rbBJ?8????P??ғD?q<6?3?p鰘ڈ!QB#0?ϫ? ??P?Z?R???:P?=Ո:?#?[`?|??.5ZאI?u?x?????ܭE???BR?
????????.?s??` ?t??????d?u?@??E??H???x<???	?????H??=v]ן:3P????<Ϥ?>.????;`A?:$9j?/??ʶ??q?D?:5Wذ???_1???i&      ?   q   x?m???@?7[E?&@-鿎[????????E??? ?Spje(O3Ҏ??Ș??Wk??a??x?M?ݚ??????wy~?? ????P?)??ݎd????G?c??1>?('      ?   E   x?Uʱ?0??<Ld?8?]?~?b?U?? ?b??p','?J"?\H+????Vnd???g??????>J	      ?   ?   x?mλ?@?z?+?6;?}???v66?b?%`?~EQC?Nwr-4???_??X}?O@??pn\?>??u $?.%?+e?)#?6c5?}զ?eȚ?mIfc?⿁P/^30??.?Ǡ)????x^?@pK d?p??̀??P?C??i??k?DB?v"??ꨕRO<4R      ?   >  x???Q?? D???@?ml>???;??U?tWQB<y<?h??'#*D???!?N?x<?>?܂4??.?$3???Jخ0?s??2:O?P??Ć?XoX
??T?+?t qz??~A%???C??X/???܋@Yn@Ǻ??U?*?F??Sre??,?/mI?}BN?v??Q?v?G?q?V?,zw?m???`?9??6?S2?V?b????mv˺?????d7?{a_d??Q&[???&?V?Q??|?Ji?????b??J?x??Y|ڌ4?to????)??kE?V??	??U2?L돺#u?L?p?)9?_68?      ?   D   x?=??	?@?o??P\Wg-鿎p!?d`[?E.pPzuN?^?????????z??>??      ?   Z   x?????@E?5TaCx?0j??:?Q?^?⠐S%S??%???h???Uc޳Y????s?????vAx?Q?o7H}٫0???!=      ?   E   x?34?J?K,?)?4?44??*H?24???,.N?	A??9???R@?N????D??	X$F??? 4??      ?   v   x?e???0C?s<E?`R?-k???G?8?T?? ?????uv;?,??]?????(?L?`??j??)?? ?(R)???????a?+?4?/?c??H7?NX+U??Nz?SM????־?l3?      ?   X   x?u?A? ??c???_??w???ƤܘL?q?5X%YH(?????ac??!~???*\s{???}m??s?? vo6?Au?:r??')N      ?   /   x???4?4??4?21́K??04 ??@,CNsNc??=... ?{?      ?   G   x?eʱ?0??p???!a??C???gd?j{h?ˢ?[E? ?_@v??	?A?	???L???f~ ?
?      ?   O   x?-???0 ?sƤ~ -?
9?????;?Z\=?O?±??
k??S????2????s??p???h?I?f? ???      ?   9   x?3?tvu	?qTpqUp?uqtq??t?tv?4?2B?s?	r??rI?%c???? ???      ?   g   x?34?t?L,?L?tI-N.?,H???SHIU??r?p??&?%??(???q?@'?@???9?kVjnAN>??)?oj^q)?i01?i?????qqq ??4?      ?   k   x?3?ru??Wp????4?t?Q(K??/RHT(HLO,RH-VH????2??rv?q?rš?4O? ?(95?$1+U!%5-3/3%$??????P?ZT????????? ??%?      ?   ?   x???=?0F?p
? ??1PU@??.&XU????)*C?N?$~~????|/*?ˌ0:X??p#4y?У???ŖH4?C?<??	??S?Fv??????CK?يX??,??˄?U?T]]?????????;\???&;cG?o??&?Wei}*?T??!K_h(<?(??f?ta      ?   `   x?]ʱ@@ й??~????Bba1[?iBO??~b???=߼$????Z?r?!????+hx?-ѢT?ڥ?V}-?0?m?5T?M?C?ͩ@???&p      ?   ?   x?]?K? D??)8?a???O????h??E??--??~ݯ???Z8??8??d???YZ'? [???????X&?$/?3e???7d??ؽfZ`'.??.k??G???b????4?????l?nԔ?쬄F\B      ?     x???˖?H???.z?@&?m7?????3?TA???????*{f3$?Ȣ*?;?H ????K?"?I7??????Dހ?
?N2?
?J???~????'?(9ϻc?i?Y??C ??['3?tܽn'?6~?d??!?0D x?-?N3?i{?+R?Z?? _??Tl;kL*3?cMu????????p1?ng??]ml??`?%?Ӯ?????? ?O??????mA??C????u=??Ŷ33,?T?u??w????gw????X?I??}u?z??:??* ???b??????0?'qܹ?????,q???6d~B???	؂U??????sLO8?I*r?I??<???Ȳ?V7??????;?????C???V&s?=?ґ$פBC*?!j?^?7q????gE?!["?g??Ty?X?\rD?Ǒs??c??a???;???????Q:
??????!??Vlk???;?o?t\ag??4?p?Zr?0??4??#?KO?1NB:?0???k1i???<???f???Г???"byX??b?g???o?輻?9u}Z??L?????Y?v?0vARn?8?}o??5??*k??????Z?q?Q????j+V??J???b??Qv?㹵?O?%?CM???G??????<??N ??:??]$??{Y>? ??x?MF ???-<?????}ãfŐ???Wp`?w?~0,?KW???}UE??|]??*HĲ9kOq^? D????n?C?Z?t??Ԕ?S??.?g?E??Ɋg??Z8???3??C?G??E[4R=?ud??SF}Hh
?"???6??d?_$syc~?t????0dW-???c???µo}?͘A$_7?e?
?v׷zz?=??+qo?|?????I>??4???ً???%?D?J???$$;?g?ͅ??+??_z??g??;?C3?|????h?w4??,ś?sLe???????Z8[0??c_??q?????e?4'???f??~U:?:i????b?'?Jc??Fj_O?2?c?P\???p??S?᮳?͐4a????k*??>w?????g??o[2z^"?8???)d3џ?La??c???ٶ???j?????#?D$?\l???̧?x͵?,B\}Z???????i?%c3?4{?8{n'??]???K?͌????E???\M???Ŧ??~3h??yyòLe?W?v{???ħj5??Op?Nwh?5!r퓱?4#?M?X0(?;Ԏ?U???b?,;?fM????g???o??^?Uc?? ?V??&????t?$??a-??]b????٥D??R??Y??@ff????d??&???_ؤ&?4?Ӌ	???&??I????2'Ip]?j?ӷ?N\"?̫fWaQ??~J??g???	?	b4'??R?(Gg??I1g??ơ+???APK?q??Hr????M?/??
???߭??Cs?2???եX?'???]w?P????y??(??l?h?NM????F?W=????h?᳽T?e?6??!n??????????}?q???Y?i2?n/:(?i?????Nd?a???y㌜E??F?r???????j5?+Ǧ?_Ց;??0~ӫ???VԬ?<???d^|=??s??Z???J?&?ӻ?z	??.9??K??Y?b??ȷ"p??ɇ?W??2?ɉ=!y7;?Α????=C8O)~??4?/g?P
V?????G??`???|>??,U????ɋb???N?[?U????n.O?tjd?>O&ZB??U!?Ow????G?kgc?;s׉??jf??>-Z??V????      ?   ?   x??? ?r1l??^???Zd`??[?XV???a??>bY;?!?C?L?(???@q@?+I????3޽?7?G?)?|?H?f????o???-?J?.????/D?#??S??	???PMvK?ߏ?? ??&?     