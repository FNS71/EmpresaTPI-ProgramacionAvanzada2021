--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;


---- drops
DROP TABLE IF EXISTS public.proyecto;
DROP TABLE IF EXISTS public.personal;
DROP TABLE IF EXISTS public.cliente;
DROP TABLE IF EXISTS public.contacto;
DROP TABLE IF EXISTS public.tipoproyecto;


--
-- TOC entry 200 (class 1259 OID 165540)
-- Name: personal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.personal (
    idpersonal integer NOT NULL,
    nombre text NOT NULL,
    apellido text NOT NULL,
    habilitado boolean NOT NULL,
    dni text NOT NULL
);


ALTER TABLE public.personal OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 165546)
-- Name: Personal_idPersonal_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Personal_idPersonal_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Personal_idPersonal_seq" OWNER TO postgres;

--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 201
-- Name: Personal_idPersonal_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Personal_idPersonal_seq" OWNED BY public.personal.idpersonal;


--
-- TOC entry 202 (class 1259 OID 165548)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    idcliente integer NOT NULL,
    razonsocial text NOT NULL,
    nombre text NOT NULL,
    apellido text NOT NULL,
    dni text NOT NULL,
    habilitado boolean NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 165554)
-- Name: cliente_idcliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_idcliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_idcliente_seq OWNER TO postgres;

--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 203
-- Name: cliente_idcliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_idcliente_seq OWNED BY public.cliente.idcliente;


--
-- TOC entry 204 (class 1259 OID 165556)
-- Name: contacto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contacto (
    idcontacto integer NOT NULL,
    nombre text NOT NULL,
    apellido text NOT NULL,
    telefono text,
    email text,
    habilitado boolean NOT NULL
);


ALTER TABLE public.contacto OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 165562)
-- Name: contacto_idcontacto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contacto_idcontacto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contacto_idcontacto_seq OWNER TO postgres;

--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 205
-- Name: contacto_idcontacto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contacto_idcontacto_seq OWNED BY public.contacto.idcontacto;


--
-- TOC entry 206 (class 1259 OID 165564)
-- Name: proyecto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.proyecto (
    idproyecto integer NOT NULL,
    nombre text NOT NULL,
    descripcionglobal text,
    fechacarga date NOT NULL,
    fechaconfirmacion date,
    fechatermino date,
    fechaentrega date,
    observaciones text,
    habilitado boolean NOT NULL,
    idtipoproyecto integer NOT NULL,
    idcliente integer NOT NULL
);


ALTER TABLE public.proyecto OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 165570)
-- Name: proyecto_idproyecto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.proyecto_idproyecto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.proyecto_idproyecto_seq OWNER TO postgres;

--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 207
-- Name: proyecto_idproyecto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.proyecto_idproyecto_seq OWNED BY public.proyecto.idproyecto;


--
-- TOC entry 208 (class 1259 OID 165572)
-- Name: tipoproyecto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipoproyecto (
    idtipoproyecto integer NOT NULL,
    nombre text NOT NULL,
    descripcion text,
    habilitado boolean NOT NULL
);


ALTER TABLE public.tipoproyecto OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 165578)
-- Name: tipoproyecto_idtipoproyecto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipoproyecto_idtipoproyecto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipoproyecto_idtipoproyecto_seq OWNER TO postgres;

--
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 209
-- Name: tipoproyecto_idtipoproyecto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipoproyecto_idtipoproyecto_seq OWNED BY public.tipoproyecto.idtipoproyecto;


--
-- TOC entry 2880 (class 2604 OID 165580)
-- Name: cliente idcliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN idcliente SET DEFAULT nextval('public.cliente_idcliente_seq'::regclass);


--
-- TOC entry 2881 (class 2604 OID 165581)
-- Name: contacto idcontacto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contacto ALTER COLUMN idcontacto SET DEFAULT nextval('public.contacto_idcontacto_seq'::regclass);


--
-- TOC entry 2879 (class 2604 OID 165582)
-- Name: personal idpersonal; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal ALTER COLUMN idpersonal SET DEFAULT nextval('public."Personal_idPersonal_seq"'::regclass);


--
-- TOC entry 2882 (class 2604 OID 165583)
-- Name: proyecto idproyecto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proyecto ALTER COLUMN idproyecto SET DEFAULT nextval('public.proyecto_idproyecto_seq'::regclass);


--
-- TOC entry 2883 (class 2604 OID 165584)
-- Name: tipoproyecto idtipoproyecto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipoproyecto ALTER COLUMN idtipoproyecto SET DEFAULT nextval('public.tipoproyecto_idtipoproyecto_seq'::regclass);

ALTER TABLE ONLY public.personal
    ADD CONSTRAINT "Personal_pkey" PRIMARY KEY (idpersonal);


--
-- TOC entry 2887 (class 2606 OID 165588)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);


--
-- TOC entry 2889 (class 2606 OID 165590)
-- Name: contacto contacto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contacto
    ADD CONSTRAINT contacto_pkey PRIMARY KEY (idcontacto);


--
-- TOC entry 2891 (class 2606 OID 165592)
-- Name: proyecto proyecto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (idproyecto);


--
-- TOC entry 2893 (class 2606 OID 165594)
-- Name: tipoproyecto tipoproyecto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipoproyecto
    ADD CONSTRAINT tipoproyecto_pkey PRIMARY KEY (idtipoproyecto);


--
-- TOC entry 2894 (class 2606 OID 165595)
-- Name: proyecto fk_idcliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_idcliente FOREIGN KEY (idcliente) REFERENCES public.cliente(idcliente);


--
-- TOC entry 2895 (class 2606 OID 165600)
-- Name: proyecto fk_idtipoproyecto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_idtipoproyecto FOREIGN KEY (idtipoproyecto) REFERENCES public.tipoproyecto(idtipoproyecto);
