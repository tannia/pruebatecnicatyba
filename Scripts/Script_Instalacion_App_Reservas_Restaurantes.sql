create schema ms_co_tyba;
-----------------------------SECUENCIA TYBA_T_USERS ------------------------
create sequence ms_co_tyba.TYBA_S_USERS
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 1
	cache 1
	no cycle;
-----------------------------TABLA TYBA_T_USERS ----------------------------------
create table ms_co_tyba.TYBA_T_USERS (
	
	
	 "id" integer not null default nextval('ms_co_tyba.TYBA_S_USERS'),
	 register_date timestamp not null default now(),
	 username varchar not null,
	 pass varchar not null,
	 role varchar not null,
	 constraint TYBA_PK_USERS primary key("id")
	 
);
-----------------------------COMMENTS ----------------------------------

comment on
table ms_co_tyba.TYBA_T_USERS is 'tabla donde se almacenaran los datos de login';

comment on
column ms_co_tyba.TYBA_T_USERS.id is 'Llave primaria de la tabla de login';

comment on
column ms_co_tyba.TYBA_T_USERS.register_date is 'Fecha de registro del usuario';

comment on
column ms_co_tyba.TYBA_T_USERS.username is 'Nombre de usuario';

comment on
column ms_co_tyba.TYBA_T_USERS.pass is 'Contrasenia de usuario';

comment on
column ms_co_tyba.TYBA_T_USERS.role is 'Rol del usuario';

-----------------------------FUNCTION ms_co_tyba.tyba_f_insert_user----------------------------------

create or replace
function ms_co_tyba.tyba_f_insert_user(p_username character varying,
p_password character varying)
 returns table(po_response_code character varying,
po_response_msg character varying)
 language plpgsql
as $function$

declare v_count integer;

begin
		
		select
	count(preq.id)
into
	v_count
from
	ms_co_tyba.TYBA_T_USERS preq
where
	preq.username = p_username;

if(v_count = 0) then
		
		insert
	into
	ms_co_tyba.TYBA_T_USERS (register_date,
	username,
	pass)
values(to_timestamp(now(), 'YYYY-MM-DD HH24:MI:SS'),
p_username,
p_password);

po_response_code := '0000';

po_response_msg := 'Usuario registrado exitosamente';

elsif(v_count >= 1) then
	
	    po_response_code := '0001';

po_response_msg := 'Ya existe un usuario registrado con ese nombre de usuario';
end if;

return next;

exception
when others then 
		po_response_code := '0005';

po_response_msg := 'ERROR: ' || sqlerrm;

return next;
end;

$function$
;

-----------------------------FUNCTION ms_co_tyba.tyba_f_login----------------------------------

create or replace
function ms_co_tyba.tyba_f_login(p_username character varying,
p_password character varying)
 returns table(po_response_code character varying,
po_response_msg character varying)
 language plpgsql
as $function$

declare v_count integer;

begin
		
		select
	count(preq.id)
into
	v_count
from
	ms_co_tyba.TYBA_T_USERS preq
where
	preq.username = p_username and preq.pass = p_password;

if(v_count = 1) then
		
po_response_code := '0000';

po_response_msg := 'Login correcto';

else
	  po_response_code := '0001';

	  po_response_msg := 'Datos incorrectos';
end if;

return next;

exception
when others then 
		po_response_code := '0005';

po_response_msg := 'ERROR: ' || sqlerrm;

return next;
end;

$function$
;

----------------------------SECUENCIA TYBA_T_USERS ------------------------
create sequence ms_co_tyba.tyba_s_query_history
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 1
	cache 1
	no cycle;
-----------------------------TABLA TYBA_T_USERS ----------------------------------
create table ms_co_tyba.tyba_t_query_history (
	
	
	 "id" integer not null default nextval('ms_co_tyba.tyba_s_query_history'),
	 query_date timestamp not null default now(),
	 latitude varchar not null,
	 longitude varchar not null,
	 constraint TYBA_PK_QUERY_HISTORY primary key("id")
	 
);