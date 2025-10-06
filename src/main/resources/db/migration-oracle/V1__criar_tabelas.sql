-- EQUIPAMENTO
create table equipamento (
  id number primary key,
  tipo varchar2(40) not null,
  descricao varchar2(120) not null
);
create sequence seq_equipamento start with 1 increment by 1 nocache;
create or replace trigger trg_equipamento_bi
before insert on equipamento for each row
begin
  if :new.id is null then select seq_equipamento.nextval into :new.id from dual; end if;
end;
/
alter table equipamento add constraint uk_equipamento unique (tipo, descricao);

-- PROFESSOR
create table professor (
  id number primary key,
  nome varchar2(120) not null,
  email_institucional varchar2(160) unique
);
create sequence seq_professor start with 1 increment by 1 nocache;
create or replace trigger trg_professor_bi
before insert on professor for each row
begin
  if :new.id is null then select seq_professor.nextval into :new.id from dual; end if;
end;
/
-- RESERVA
create table reserva (
  id number primary key,
  equipamento_id number not null,
  professor_id   number not null,
  sala varchar2(20) not null,
  retirada timestamp not null,
  entrega  timestamp not null,
  constraint fk_reserva_equipamento foreign key (equipamento_id) references equipamento(id),
  constraint fk_reserva_professor  foreign key (professor_id)  references professor(id),
  constraint ck_reserva_periodo check (entrega > retirada)
);
create sequence seq_reserva start with 1 increment by 1 nocache;
create or replace trigger trg_reserva_bi
before insert on reserva for each row
begin
  if :new.id is null then select seq_reserva.nextval into :new.id from dual; end if;
end;
/
create index idx_reserva_equipamento on reserva (equipamento_id);
create index idx_reserva_professor  on reserva (professor_id);
