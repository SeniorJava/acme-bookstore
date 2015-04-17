--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: acme_bookstore; Type: SCHEMA; Schema: -; Owner: bookstore
--

CREATE SCHEMA acme_bookstore;


ALTER SCHEMA acme_bookstore OWNER TO bookstore;

SET search_path = acme_bookstore, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: authorities; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE authorities (
    username character varying NOT NULL,
    authority character varying(50) NOT NULL
);


ALTER TABLE authorities OWNER TO bookstore;

--
-- Name: batch_job_execution; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE batch_job_execution (
    job_execution_id bigint NOT NULL,
    version bigint,
    job_instance_id bigint NOT NULL,
    create_time timestamp without time zone NOT NULL,
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    status character varying(10),
    exit_code character varying(2500),
    exit_message character varying(2500),
    last_updated timestamp without time zone,
    job_configuration_location character varying(2500)
);


ALTER TABLE batch_job_execution OWNER TO bookstore;

--
-- Name: batch_job_execution_context; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE batch_job_execution_context (
    job_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE batch_job_execution_context OWNER TO bookstore;

--
-- Name: batch_job_execution_params; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE batch_job_execution_params (
    job_execution_id bigint NOT NULL,
    type_cd character varying(6) NOT NULL,
    key_name character varying(100) NOT NULL,
    string_val character varying(250),
    date_val timestamp without time zone,
    long_val bigint,
    double_val double precision,
    identifying character(1) NOT NULL
);


ALTER TABLE batch_job_execution_params OWNER TO bookstore;

--
-- Name: batch_job_execution_seq; Type: SEQUENCE; Schema: acme_bookstore; Owner: bookstore
--

CREATE SEQUENCE batch_job_execution_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batch_job_execution_seq OWNER TO bookstore;

--
-- Name: batch_job_instance; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE batch_job_instance (
    job_instance_id bigint NOT NULL,
    version bigint,
    job_name character varying(100) NOT NULL,
    job_key character varying(32) NOT NULL
);


ALTER TABLE batch_job_instance OWNER TO bookstore;

--
-- Name: batch_job_seq; Type: SEQUENCE; Schema: acme_bookstore; Owner: bookstore
--

CREATE SEQUENCE batch_job_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batch_job_seq OWNER TO bookstore;

--
-- Name: batch_step_execution; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE batch_step_execution (
    step_execution_id bigint NOT NULL,
    version bigint NOT NULL,
    step_name character varying(100) NOT NULL,
    job_execution_id bigint NOT NULL,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone,
    status character varying(10),
    commit_count bigint,
    read_count bigint,
    filter_count bigint,
    write_count bigint,
    read_skip_count bigint,
    write_skip_count bigint,
    process_skip_count bigint,
    rollback_count bigint,
    exit_code character varying(2500),
    exit_message character varying(2500),
    last_updated timestamp without time zone
);


ALTER TABLE batch_step_execution OWNER TO bookstore;

--
-- Name: batch_step_execution_context; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE batch_step_execution_context (
    step_execution_id bigint NOT NULL,
    short_context character varying(2500) NOT NULL,
    serialized_context text
);


ALTER TABLE batch_step_execution_context OWNER TO bookstore;

--
-- Name: batch_step_execution_seq; Type: SEQUENCE; Schema: acme_bookstore; Owner: bookstore
--

CREATE SEQUENCE batch_step_execution_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batch_step_execution_seq OWNER TO bookstore;

--
-- Name: book; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE book (
    id integer NOT NULL,
    title character varying,
    genre character varying,
    author character varying,
    price numeric,
    isbn character varying
);


ALTER TABLE book OWNER TO bookstore;

--
-- Name: book_id_seq; Type: SEQUENCE; Schema: acme_bookstore; Owner: bookstore
--

CREATE SEQUENCE book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE book_id_seq OWNER TO bookstore;

--
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: acme_bookstore; Owner: bookstore
--

ALTER SEQUENCE book_id_seq OWNED BY book.id;


--
-- Name: book_order; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE book_order (
    id integer NOT NULL,
    client_id integer,
    order_date date,
    comments character varying,
    order_status character varying
);


ALTER TABLE book_order OWNER TO bookstore;

--
-- Name: TABLE book_order; Type: COMMENT; Schema: acme_bookstore; Owner: bookstore
--

COMMENT ON TABLE book_order IS 'Заказ на книги';


--
-- Name: COLUMN book_order.comments; Type: COMMENT; Schema: acme_bookstore; Owner: bookstore
--

COMMENT ON COLUMN book_order.comments IS 'Комментарии к заказу';


--
-- Name: COLUMN book_order.order_status; Type: COMMENT; Schema: acme_bookstore; Owner: bookstore
--

COMMENT ON COLUMN book_order.order_status IS 'Состояние заказа';


--
-- Name: book_order_id_seq; Type: SEQUENCE; Schema: acme_bookstore; Owner: bookstore
--

CREATE SEQUENCE book_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE book_order_id_seq OWNER TO bookstore;

--
-- Name: book_order_id_seq; Type: SEQUENCE OWNED BY; Schema: acme_bookstore; Owner: bookstore
--

ALTER SEQUENCE book_order_id_seq OWNED BY book_order.id;


--
-- Name: book_order_line; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE book_order_line (
    id integer NOT NULL,
    order_id integer,
    book_id integer,
    price numeric,
    qty integer
);


ALTER TABLE book_order_line OWNER TO bookstore;

--
-- Name: TABLE book_order_line; Type: COMMENT; Schema: acme_bookstore; Owner: bookstore
--

COMMENT ON TABLE book_order_line IS 'Строки заказов на книги';


--
-- Name: COLUMN book_order_line.qty; Type: COMMENT; Schema: acme_bookstore; Owner: bookstore
--

COMMENT ON COLUMN book_order_line.qty IS 'Кол-во книг в строке';


--
-- Name: book_order_line_id_seq; Type: SEQUENCE; Schema: acme_bookstore; Owner: bookstore
--

CREATE SEQUENCE book_order_line_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE book_order_line_id_seq OWNER TO bookstore;

--
-- Name: book_order_line_id_seq; Type: SEQUENCE OWNED BY; Schema: acme_bookstore; Owner: bookstore
--

ALTER SEQUENCE book_order_line_id_seq OWNED BY book_order_line.id;


--
-- Name: client; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE client (
    id integer NOT NULL,
    first_name character varying,
    last_name character varying,
    phone character varying,
    address character varying
);


ALTER TABLE client OWNER TO bookstore;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: acme_bookstore; Owner: bookstore
--

CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE client_id_seq OWNER TO bookstore;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: acme_bookstore; Owner: bookstore
--

ALTER SEQUENCE client_id_seq OWNED BY client.id;


--
-- Name: dashboard_stats; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE dashboard_stats (
    stat_date date NOT NULL,
    orders_qty integer,
    total_sum numeric
);


ALTER TABLE dashboard_stats OWNER TO bookstore;

--
-- Name: TABLE dashboard_stats; Type: COMMENT; Schema: acme_bookstore; Owner: bookstore
--

COMMENT ON TABLE dashboard_stats IS 'Таблица статистики по работе магазина';


--
-- Name: users; Type: TABLE; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE TABLE users (
    password character varying NOT NULL,
    username character varying NOT NULL,
    enabled boolean DEFAULT true NOT NULL
);


ALTER TABLE users OWNER TO bookstore;

--
-- Name: id; Type: DEFAULT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY book ALTER COLUMN id SET DEFAULT nextval('book_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY book_order ALTER COLUMN id SET DEFAULT nextval('book_order_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY book_order_line ALTER COLUMN id SET DEFAULT nextval('book_order_line_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);


--
-- Name: batch_job_execution_context_pkey; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY batch_job_execution_context
    ADD CONSTRAINT batch_job_execution_context_pkey PRIMARY KEY (job_execution_id);


--
-- Name: batch_job_execution_pkey; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY batch_job_execution
    ADD CONSTRAINT batch_job_execution_pkey PRIMARY KEY (job_execution_id);


--
-- Name: batch_job_instance_pkey; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY batch_job_instance
    ADD CONSTRAINT batch_job_instance_pkey PRIMARY KEY (job_instance_id);


--
-- Name: batch_step_execution_context_pkey; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY batch_step_execution_context
    ADD CONSTRAINT batch_step_execution_context_pkey PRIMARY KEY (step_execution_id);


--
-- Name: batch_step_execution_pkey; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY batch_step_execution
    ADD CONSTRAINT batch_step_execution_pkey PRIMARY KEY (step_execution_id);


--
-- Name: job_inst_un; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY batch_job_instance
    ADD CONSTRAINT job_inst_un UNIQUE (job_name, job_key);


--
-- Name: pb_dashboard_stats; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY dashboard_stats
    ADD CONSTRAINT pb_dashboard_stats PRIMARY KEY (stat_date);


--
-- Name: pk_authority; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY authorities
    ADD CONSTRAINT pk_authority PRIMARY KEY (username, authority);


--
-- Name: pk_book; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY book
    ADD CONSTRAINT pk_book PRIMARY KEY (id);


--
-- Name: pk_client; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT pk_client PRIMARY KEY (id);


--
-- Name: pk_order; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY book_order
    ADD CONSTRAINT pk_order PRIMARY KEY (id);


--
-- Name: pk_order_line; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY book_order_line
    ADD CONSTRAINT pk_order_line PRIMARY KEY (id);


--
-- Name: pk_users_username; Type: CONSTRAINT; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_users_username PRIMARY KEY (username);


--
-- Name: fki_job_inst_exec_fk; Type: INDEX; Schema: acme_bookstore; Owner: bookstore; Tablespace: 
--

CREATE INDEX fki_job_inst_exec_fk ON batch_job_execution USING btree (job_instance_id);


--
-- Name: fk_authority_username; Type: FK CONSTRAINT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY authorities
    ADD CONSTRAINT fk_authority_username FOREIGN KEY (username) REFERENCES users(username);


--
-- Name: fk_order_client; Type: FK CONSTRAINT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY book_order
    ADD CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES client(id);


--
-- Name: fk_order_line_book; Type: FK CONSTRAINT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY book_order_line
    ADD CONSTRAINT fk_order_line_book FOREIGN KEY (book_id) REFERENCES book(id);


--
-- Name: fk_order_line_order; Type: FK CONSTRAINT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY book_order_line
    ADD CONSTRAINT fk_order_line_order FOREIGN KEY (order_id) REFERENCES book_order(id);


--
-- Name: job_inst_exec_fk; Type: FK CONSTRAINT; Schema: acme_bookstore; Owner: bookstore
--

ALTER TABLE ONLY batch_job_execution
    ADD CONSTRAINT job_inst_exec_fk FOREIGN KEY (job_instance_id) REFERENCES batch_job_instance(job_instance_id);


--
-- PostgreSQL database dump complete
--

