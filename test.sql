--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Debian 15.2-1.pgdg110+1)
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-19 15:34:38

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

--
-- TOC entry 8 (class 2615 OID 45318)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 46420)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 46403)
-- Name: location; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.location (
    location_id integer NOT NULL,
    geometry public.geometry(Geometry,4326),
    weather_configuration_id bigint NOT NULL
);


ALTER TABLE public.location OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 46402)
-- Name: location_location_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.location_location_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.location_location_id_seq OWNER TO postgres;

--
-- TOC entry 4247 (class 0 OID 0)
-- Dependencies: 223
-- Name: location_location_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.location_location_id_seq OWNED BY public.location.location_id;


--
-- TOC entry 225 (class 1259 OID 46411)
-- Name: weather_configuration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.weather_configuration (
    id bigint NOT NULL,
    config_validity integer,
    date_time timestamp without time zone,
    service_name character varying(255),
    use_case character varying(255),
    weather_minutes_interval integer
);


ALTER TABLE public.weather_configuration OWNER TO postgres;

--
-- TOC entry 4079 (class 2604 OID 46406)
-- Name: location location_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.location ALTER COLUMN location_id SET DEFAULT nextval('public.location_location_id_seq'::regclass);


--
-- TOC entry 4238 (class 0 OID 46403)
-- Dependencies: 224
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.location (location_id, geometry, weather_configuration_id) FROM stdin;
\.


--
-- TOC entry 4078 (class 0 OID 45638)
-- Dependencies: 219
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- TOC entry 4239 (class 0 OID 46411)
-- Dependencies: 225
-- Data for Name: weather_configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.weather_configuration (id, config_validity, date_time, service_name, use_case, weather_minutes_interval) FROM stdin;
\.


--
-- TOC entry 4248 (class 0 OID 0)
-- Dependencies: 226
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 4249 (class 0 OID 0)
-- Dependencies: 223
-- Name: location_location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.location_location_id_seq', 1, false);


--
-- TOC entry 4084 (class 2606 OID 46410)
-- Name: location location_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (location_id);


--
-- TOC entry 4086 (class 2606 OID 46419)
-- Name: location uk_k42mdvsr96c4je1rsomoq18ir; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT uk_k42mdvsr96c4je1rsomoq18ir UNIQUE (weather_configuration_id);


--
-- TOC entry 4088 (class 2606 OID 46417)
-- Name: weather_configuration weather_configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.weather_configuration
    ADD CONSTRAINT weather_configuration_pkey PRIMARY KEY (id);


--
-- TOC entry 4089 (class 2606 OID 46421)
-- Name: location fkojxy9e5qxrsd37sb5ld8er6so; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT fkojxy9e5qxrsd37sb5ld8er6so FOREIGN KEY (weather_configuration_id) REFERENCES public.weather_configuration(id);


--
-- TOC entry 4246 (class 0 OID 0)
-- Dependencies: 8
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2023-05-19 15:34:38

--
-- PostgreSQL database dump complete
--

