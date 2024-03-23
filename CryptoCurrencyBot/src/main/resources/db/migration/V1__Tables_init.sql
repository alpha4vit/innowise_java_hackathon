create table if not exists users(
    chatId bigint primary key,
    userName varchar(255),
    firstName varchar(255),
    botState int default 0,
    rateDiff real
);

create table if not exists currency_rates(
    symbol varchar(100) primary key not null,
    price real not null
);