CREATE TABLE categories
(
    id   uuid         not null primary key,
    code varchar(255) not null
);

CREATE TABLE products
(
    id          uuid         not null primary key,
    sku         varchar(255) not null,
    name        varchar(255) not null,
    category_id uuid         not null,
    price       int          not null,
    foreign key (category_id) references categories (id)
);

CREATE TABLE discounts
(
    id          uuid         not null primary key,
    entity      varchar(255) not null,
    sku         varchar(255),
    category_id uuid,
    discount    int          not null,
    foreign key (category_id) references categories (id)
);

insert into categories(id, code)
values ('57c6905d-bae5-40c5-8053-3d06cf01c325', 'boots'),
       ('ac742e15-2e03-41a1-a3b8-a6b43e34bcc4', 'sandals'),
       ('22a7fbc9-4909-4c72-b91b-7041e10b6320', 'sneakers');

insert into products(id, sku, name, category_id, price)
VALUES ('93929da4-dd29-4e0e-8ead-680d7de01c26', '000001', 'BV Lean leather ankle boots',
        '57c6905d-bae5-40c5-8053-3d06cf01c325', 89000),
       ('d8267c84-73dd-4178-868e-33de747b9d20', '000002', 'BV Lean leather ankle boots',
        '57c6905d-bae5-40c5-8053-3d06cf01c325', 99000),
       ('0986d22e-9f44-47ca-a7a6-28fbbd09d24b', '000003', 'Ashlington leather ankle boots',
        '57c6905d-bae5-40c5-8053-3d06cf01c325', 71000),
       ('5a9d2f10-d782-4e37-914b-c5255ae2de6c', '000004', 'Naima embellished suede sandals',
        'ac742e15-2e03-41a1-a3b8-a6b43e34bcc4', 79500),
       ('2d2df415-874a-42dc-bf9b-02fb16ac9647', '000005', 'Nathane leather sneakers',
        '22a7fbc9-4909-4c72-b91b-7041e10b6320', 59000);

insert into discounts(id, entity, sku, category_id, discount)
VALUES ('4927d5e5-2e4c-4da9-afbe-7f1d9c2d22e1', 'CATEGORY', null, '57c6905d-bae5-40c5-8053-3d06cf01c325', 30),
       ('ce1c8cd7-972d-4749-967e-9cbff78ae558', 'SKU', '000003', null, 15);