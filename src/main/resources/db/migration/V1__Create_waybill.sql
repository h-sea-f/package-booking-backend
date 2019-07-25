CREATE TABLE waybill(
`id` VARCHAR primary key ,
`phone_number` VARCHAR NOT NULL ,
`consignee` VARCHAR NOT NULL ,
`weight` DOUBLE NOT NULL,
`status` INT NOT NULL,
`time` BIGINT ,
)