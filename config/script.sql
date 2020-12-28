create table if not exists article
(
    id               int auto_increment
        primary key,
    title            varchar(100)                           not null comment '文章标题',
    original_content longtext                               not null comment '原始文章内容(Markdown)',
    format_content   longtext                               not null comment '格式化后文章内容(HTML)',
    views            bigint       default 0                 not null comment '访问数量',
    likes            bigint       default 0                 not null comment '点赞数量',
    shares           bigint       default 0                 not null comment '分享数量',
    comments         bigint       default 0                 not null comment '评论数量',
    type             tinyint      default 0                 not null comment '0:公开,1:私密',
    create_time      timestamp    default CURRENT_TIMESTAMP not null comment '文章创建时间',
    update_time      timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '文章更新时间',
    cover_page       varchar(255) default ''                not null comment '文章封面url',
    category_id      int          default 1                 not null comment '文章类型外键',
    summary          varchar(255) default ''                null comment '文章预览'
);

create table if not exists attachment
(
    id              int auto_increment
        primary key,
    name            varchar(255)  not null comment '文件名称',
    path            varchar(1023) not null comment '文件路径',
    file_key        varchar(2047) null comment 'oss或本地文件key，用于删除文件',
    media_type      varchar(127)  not null comment '文件类型',
    attachment_type int default 0 null comment '文件存储类型(0：本地，1：七牛云)'
);

create table if not exists category
(
    id   int auto_increment
        primary key,
    name varchar(255) not null comment '类型名称',
    constraint name
        unique (name)
);

create table if not exists comment
(
    id          int auto_increment
        primary key,
    content     varchar(128)                        null comment '评论内容',
    nickname    varchar(32)                         null comment '评论人昵称',
    email       varchar(32)                         null comment '评论人邮箱',
    create_time timestamp default CURRENT_TIMESTAMP null comment '评论创建时间',
    article_id  int                                 not null comment '文章外键引用',
    parent_id   int       default 0                 null comment '评论父级引用',
    is_admin    int       default 0                 null comment '是否为管理员评论0:不是1:是'
);

create index idx_comment
    on comment (article_id, parent_id);

create table if not exists sys_log
(
    id          int auto_increment
        primary key,
    ip          int                                 null comment 'ip地址, int类型存储, sql时需要转换',
    country     varchar(127)                        null comment '国家名称',
    province    varchar(127)                        null comment '省份信息',
    city        varchar(127)                        null comment '城市名称',
    description varchar(127)                        null comment '操作说明',
    create_time timestamp default CURRENT_TIMESTAMP null comment '访问时间'
);

create table if not exists user
(
    id          int auto_increment
        primary key,
    username    varchar(32)                         not null comment '用户名',
    password    varchar(64)                         not null comment '密码',
    email       varchar(64)                         not null comment '邮箱',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    avatar      varchar(128)                        null comment '头像',
    description varchar(255)                        null comment '个人简介',
    constraint unq_nickname
        unique (username)
);


