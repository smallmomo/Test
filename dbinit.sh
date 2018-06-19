#!/bin/bash
# yum install -y sqlite sqlite-devel

db_file="src/main/resources/javaweb.db"

if [[ -f ${db_file} ]]; then
    rm -f ${db_file}
fi

sqlite3 ${db_file}  "create table user (id INTEGER PRIMARY KEY,f TEXT,l TEXT);"
sqlite3 ${db_file}  "insert into user (f,l) values ('john', 'smith');"
sqlite3 ${db_file}  "select * from user;"

