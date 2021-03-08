create table t_block_extrinsic_0
(
    blk_id bigint not null,
    ext_id bigint not null,
    key (blk_id)
);

create table t_block_header_0
(
    blk_id      bigint primary key,
    number      bigint not null,
    extrinsics  varchar(128),
    parent_hash varchar(128),
    state_root  varchar(128)
);

create table t_digest_log_0
(
    blk_id bigint not null,
    idx    int    not null,
    log    blob,
    key (blk_id)
);

create table t_extrinsics_0
(
    id   bigint primary key,
    data blob
);

create table t_hash_id_map_0
(
    hash varchar(128) not null,
    id   bigint       not null,
    type varchar(10)  not null,
    key (hash)
);

create table t_seq
(
    prefix varchar(40) primary key,
    value  bigint not null
);

create table t_table_detail
(
    prefix  varchar(40) not null,
    suffix  int         not null,
    backend int,
    min     bigint      not null,
    max     bigint      not null,
    state   varchar(20) not null
);

create table t_table_meta
(
    prefix    varchar(40) not null,
    version   bigint      not null,
    splitting boolean     not null
);

create table t_account_extrinsic_0
(
    account_id bigint not null,
    ext_id     bigint not null,
    key (account_id)
);

create table t_account_transaction_0
(
    account_id bigint not null,
    ext_id     bigint not null,
    key (account_id)
);

create table t_account_stash_0
(
    account_id bigint not null,
    ext_id     bigint not null,
    key (account_id)
);

create table t_event_0
(
    id   bigint primary key,
    data blob
);

create table t_ext_event_0
(
    ext_id   bigint not null,
    event_id bigint not null,
    key (ext_id)
);

insert into t_seq (prefix, value)
values ('t_block_header', 0);
insert into t_seq (prefix, value)
values ('t_digest_log', 0);
insert into t_seq (prefix, value)
values ('t_extrinsics', 0);
insert into t_seq (prefix, value)
values ('t_account', 0);
insert into t_seq (prefix, value)
values ('t_event', 0);

insert into t_table_meta (prefix, version, splitting)
values ('t_block_header', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_digest_log', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_extrinsics', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_hash_id_map', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_account_extrinsic', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_account_transaction', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_account_stash', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_block_extrinsic', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_event', 1, false);
insert into t_table_meta (prefix, version, splitting)
values ('t_ext_event', 1, false);

insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_block_header', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_digest_log', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_extrinsics', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_hash_id_map', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_account_extrinsic', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_account_transaction', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_account_stash', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_block_extrinsic', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_event', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
insert into t_table_detail(prefix, suffix, backend, min, max, state)
values ('t_ext_event', 0, null, 0, 0x7fffffffffffffff, 'NORMAL');
